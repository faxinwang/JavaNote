package myjavax.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

@SuppressWarnings("serial")
public class MyTextPane extends JTextPane{
	protected StyledDocument doc;
	protected SyntaxFormatter formatter = new SyntaxFormatter("./src/files/mySyntax.stx");
	private SimpleAttributeSet normalAttr = formatter.getNormalAttributeSet();
	private SimpleAttributeSet quotAttr = new SimpleAttributeSet();
	//保存文档改变的开始位置
	private int docChangeStart = 0;
	//保存文档改变的长度
	private int docChangeLength = 0;
	public MyTextPane(){
		StyleConstants.setForeground(quotAttr, new Color(255,0,255));
		StyleConstants.setFontSize(quotAttr,16);
		doc = getStyledDocument();
		//设置该文档的页边距
		setMargin(new Insets(3,40,0,0));
		//添加按键监听器,当松开时进行语法分析
		this.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent evt){
				syntaxParse();
			}
		});
		//添加文档监听器
		doc.addDocumentListener(new DocumentListener(){
			@Override
			public void insertUpdate(DocumentEvent e) {
				docChangeStart = e.getOffset();
				docChangeLength= e.getLength();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {}
			@Override
			public void changedUpdate(DocumentEvent e) {}
		});
	}
		
	public void syntaxParse(){
		try{
			//获取文档根元素,即文档内的全部内容
			Element root = doc.getDefaultRootElement();
			//获取文档中光标插入符的位置
			int cursorPos = this.getCaretPosition();
			//获取光标所在位置的行
			int line = root.getElementIndex(cursorPos);
			Element para = root.getElement(line);
			//定义光标所在行的开头位置
			int start = para.getStartOffset();
			//让start等于start与docChangeStart中的较小值
			start = start > docChangeStart? docChangeStart:start;
			//定义被修改部分的长度
			int length = para.getEndOffset() - start;
			length = length < docChangeLength? docChangeLength+1 : length;
			//取出所有可能被修改的字符串
			String s = doc.getText(start, length);
			//以空格,点号,括号作为分隔符
			String[] tokens = s.split("\\s+|\\.|\\(|\\)|\\{|\\}|\\[|\\]");
			//定义当前分析单词在s字符串中的开始位置
			int curStart = 0;
			//定义单词是否处于引号内
			boolean isQuot=false;
			for(String token : tokens){
				//找出当前分析单词在s字符串中的位置
				int tokenPos = s.indexOf(token,curStart);
				if(isQuot && (token.endsWith("\"") || token.endsWith("\'")) ){
					doc.setCharacterAttributes(start+ tokenPos,token.length(),quotAttr,false);
					isQuot = false;
				}
				else if(isQuot && !(token.endsWith("\"") || token.endsWith("\'")) ){
					doc.setCharacterAttributes(start+tokenPos, token.length(), quotAttr, false);
				}
				else if( (token.startsWith("\"") || token.startsWith("\'"))  && 
						 (token.endsWith("\"") || token.endsWith("\'")) )
				{
					doc.setCharacterAttributes(start+tokenPos, token.length(), quotAttr, false);
				}
				else if( (token.startsWith("\"") || token.startsWith("\'")) && 
						!(token.endsWith("\"") || token.endsWith("\'")) )
				{
					doc.setCharacterAttributes(start+tokenPos, token.length(), quotAttr, false);
					isQuot = true;
				}
				else{
					//使用格式器对当前单词设置颜色
					formatter.setHightLight(doc,token,start+tokenPos,token.length());
				}
				//开始分析下一个单词
				curStart = tokenPos + token.length();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	//重画该组件,设置行号
	public void paint(Graphics g){
		super.paint(g);
		Element root = doc.getDefaultRootElement();
		//获取行号
		int line= root.getElementIndex(doc.getLength());
		//设置颜色
		g.setColor(new Color(220,230,240));
		//绘制显示行数的矩形框
		g.fillRect(0, 0, this.getMargin().left-10, getHeight());
		//设置行号的颜色
		g.setColor(new Color(40,50,60));
		//每行绘制一个行号
		for(int count=0,j=1; count<=line; ++count,++j){
			g.drawString(String.valueOf(j),3,
					(int)(j*1.535*StyleConstants.getFontSize(normalAttr)));
		}
	}
	public static void main(String[] args){
		JFrame jf= new JFrame("文本编辑器");
		//使用MyTextPane
		jf.getContentPane().add(new JScrollPane(new MyTextPane()) );
		final int inset = 200;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setBounds(inset,inset, screenSize.width-inset*2,screenSize.height-inset*2);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}

//定义语法格式器
class SyntaxFormatter{
	//以一个Map保存关键字和颜色的对应关系
	private Map<SimpleAttributeSet,ArrayList<String>> attMap = new HashMap<>();
	//定义文档的正常文本的外观
	SimpleAttributeSet normalAttr = new SimpleAttributeSet();
	public SyntaxFormatter(String syntaxFile){
		//设置正常文本的颜色,大小
		StyleConstants.setForeground(normalAttr, Color.black);
		StyleConstants.setFontSize(normalAttr, 16);
		//创建Scanner,负责加载语法文件信息
		Scanner scaner = null;
		try{
			scaner = new Scanner(new File(syntaxFile));
		}catch(FileNotFoundException e){
			throw new RuntimeException("语法文件丢失:" + e.getMessage());
		}
		int color = -1;
		ArrayList<String> keywords = new ArrayList<>();
		//不断读取语法文件的内容
		while(scaner.hasNextLine()){
			String line = scaner.nextLine();
			//如果当前行以"#"开头
			if(line.startsWith("#")){
				if(keywords.size()>0 && color>-1){
					//取出当前行的颜色,并封装成SimpleAttributeSet对象
					SimpleAttributeSet att = new SimpleAttributeSet();
					StyleConstants.setForeground(att, new Color(color));
					StyleConstants.setFontSize(att, 16);
					//将当前颜色和关键字list对应起来
					attMap.put(att, keywords);
				}
				//重新创建一个关键字list,为下一个语法格式做准备
				keywords = new ArrayList<>();
				color = Integer.valueOf(line.substring(1),16);
				
			}else{
				//对于普通文本行,将每行内容添加到关键字list里面
				if(line.trim().length() > 0){
					keywords.add(line.trim());
				}
			}
		}
		
		scaner.close();
		//把所有的关键字和颜色对应起来
		if(keywords.size()>0 && color>-1){
			SimpleAttributeSet att = new SimpleAttributeSet();
			StyleConstants.setForeground(att, new Color(color));
			StyleConstants.setFontSize(att,16);
			attMap.put(att, keywords);
		}
	}
	
	//返回该格式器里正常文本的外观属性
	public SimpleAttributeSet getNormalAttributeSet(){
		return normalAttr;
	}
	//设置语法高亮
	public void setHightLight(StyledDocument doc,String token,int start,int length){
		//保存当前单词对应的外观属性
		SimpleAttributeSet curAttr = null;
		outer:
		for(SimpleAttributeSet att:attMap.keySet()){
			//取出当前单词对应的外观属性
			ArrayList<String> keywords = attMap.get(att);
			//遍历所有关键字
			for(String keyword:keywords){
				if(keyword.equals(token)){
					//跳出循环,并设置当前单词对应 的外观属性
					curAttr = att;
					break outer;
				}
			}
		}
		if(curAttr != null){
			//设置当前单词的颜色
			doc.setCharacterAttributes(start, length, curAttr, false);
		}else{
			//否则使用普通外观来设置该单词
			doc.setCharacterAttributes(start, length, normalAttr, false);
		}
	}
}