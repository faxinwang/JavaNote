package myjavax.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/* Swing提供了StyleConstants工具类,该工具类里大致包含如下常用静态方法来设置MutableAttributeSet里的字体,颜色等
 * setAlignment(MutableAttributeSet a,int align):		设置文本对齐方式
 * setBackground(MutableAttributeSet a,Color bg):		设置背景色
 * setForeground(MutableAttributeSet a,Color fg):		设置文字前景色
 * setBold(MutableAttributeSet a,boolean b):			设置是否使用粗体字
 * setFirstLineIndent(MutableAttributeSet a,float i):	设置首行缩进的大小
 * setFontFamily(MutableAttributeSet a,String fam):		设置字体
 * setFontSize(MutableAttributeSet a,int s):			设置文字大小
 * setItalic(MutableAttributeSet a,boolean b):			设置文字是否斜体
 * setLeftIndent(MutableAttributeSet a,float i):		设置左缩进
 * setRightIndent(MutableAttributeSet a,float i):		设置右缩进
 * setLineSpacing(MutableAttributeSet a,float s):		设置行间距
 * setStrikeThrough(MutableAttributeSet a,boolean b):	设置是否为文字添加删除线
 * setSubscript(MutableAttributeSet a,boolean b):		将指定文字设置成上标
 * setSuperscript(MutableAttributeSet a,boolean b):		将指定文字设置成下标
 * setUnderline(MutableAttributeSet a, boolean b):		设置是否添加下划线
 */

@SuppressWarnings("unused")
public class JTextPaneTest {
	JFrame  mainWin = new JFrame("测试JTextPane");
//	StyleContext context = new StyleContext();
	StyledDocument doc = new DefaultStyledDocument();
//	Style android = context.getStyle(StyleContext.DEFAULT_STYLE);
//	Style java = context.getStyle(StyleContext.DEFAULT_STYLE);
	
	//定义三个SimpleAttributeSet对象
	SimpleAttributeSet android = new SimpleAttributeSet();
	SimpleAttributeSet java = new SimpleAttributeSet();
	SimpleAttributeSet javaee = new SimpleAttributeSet();
	public void init(){
		//为android属性集设置颜色,字体,字号和下划线,对其方式
		StyleConstants.setForeground(android, Color.red);
		StyleConstants.setFontSize(android, 24);
		StyleConstants.setFontFamily(android,"Dialog");
		StyleConstants.setUnderline(android,true);
		StyleConstants.setAlignment(android,StyleConstants.ALIGN_RIGHT);
		StyleConstants.setLeftIndent(android, 20);
		
		//为java属性集设置颜色,字号,字体何粗体字,对其方式
		StyleConstants.setForeground(java, Color.BLUE);
		StyleConstants.setFontSize(java, 30);
		StyleConstants.setFontFamily(java, "Arial Black");
		StyleConstants.setBold(java, true);
		StyleConstants.setAlignment(java,StyleConstants.ALIGN_CENTER);
		
		//为javaee属性集设置颜色,字号,斜体字
		StyleConstants.setForeground(javaee, Color.green);
		StyleConstants.setFontSize(javaee,32);
		StyleConstants.setItalic(javaee, true);
		
		try {
			doc.insertString(doc.getLength(), "疯狂Android讲义\n", null);
			doc.insertString(doc.getLength(), "疯狂Java讲义\n", null);
			
		} catch (BadLocationException e){
			e.printStackTrace();
		}
		
		JTextPane txtPane = new JTextPane(doc);
		//设置不允许编辑
		txtPane.setEditable(true);
		
		//分别为文档中三段文字设置不同的外观样式,true表示替换掉原来的属性,false表示将新属性添加到原来的属性上
		int last=0,cur=0;
		String content=null;
		try {
			content = doc.getText(0, doc.getLength());
		} catch (BadLocationException e) {e.printStackTrace();}
		doc.setParagraphAttributes(last,(cur=content.indexOf('\n', last))-last,android,true);
		last = cur+1;
		doc.setParagraphAttributes(last,(cur=content.indexOf('\n', last))-last , java, true);
		//也可以在JTextPane创建完之后再插入文本并设置其格式
		try {
			doc.insertString(doc.getLength(), "轻量级Java EE企业应用实战\n", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		last = cur+1;
		doc.setParagraphAttributes(last, doc.getLength()-last, javaee, true);
		
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontFamily(attr, "汉仪蝶语体简");
		StyleConstants.setFontSize(attr, 20);
		StyleConstants.setUnderline(attr, true);
		StyleConstants.setForeground(attr, new Color(111,222,111));
		try {
			doc.insertString(doc.getLength(), "汉仪蝶语体简,右对齐,下划线,20号", attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		mainWin.add(new JScrollPane(txtPane));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int inset = 200;
		//设置主窗口大小
		mainWin.setBounds(inset,inset,screenSize.width - inset*2, screenSize.height  - inset*2);
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setVisible(true);
		
		AttributeSet attrs =txtPane.getCharacterAttributes();
		System.out.println(attr.getAttribute("FontFamily"));
		System.out.println(attr.getAttribute("Alignment"));
		System.out.println(attr.getAttribute("Underline"));
		System.out.println(attr.getAttribute("Foreground"));
	}
	public static void main(String[] args){
		JTextPaneTest test = new JTextPaneTest();
		test.init();
		
	}
}
