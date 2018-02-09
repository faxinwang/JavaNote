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
	//�����ĵ��ı�Ŀ�ʼλ��
	private int docChangeStart = 0;
	//�����ĵ��ı�ĳ���
	private int docChangeLength = 0;
	public MyTextPane(){
		StyleConstants.setForeground(quotAttr, new Color(255,0,255));
		StyleConstants.setFontSize(quotAttr,16);
		doc = getStyledDocument();
		//���ø��ĵ���ҳ�߾�
		setMargin(new Insets(3,40,0,0));
		//��Ӱ���������,���ɿ�ʱ�����﷨����
		this.addKeyListener(new KeyAdapter(){
			public void keyReleased(KeyEvent evt){
				syntaxParse();
			}
		});
		//����ĵ�������
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
			//��ȡ�ĵ���Ԫ��,���ĵ��ڵ�ȫ������
			Element root = doc.getDefaultRootElement();
			//��ȡ�ĵ��й��������λ��
			int cursorPos = this.getCaretPosition();
			//��ȡ�������λ�õ���
			int line = root.getElementIndex(cursorPos);
			Element para = root.getElement(line);
			//�����������еĿ�ͷλ��
			int start = para.getStartOffset();
			//��start����start��docChangeStart�еĽ�Сֵ
			start = start > docChangeStart? docChangeStart:start;
			//���屻�޸Ĳ��ֵĳ���
			int length = para.getEndOffset() - start;
			length = length < docChangeLength? docChangeLength+1 : length;
			//ȡ�����п��ܱ��޸ĵ��ַ���
			String s = doc.getText(start, length);
			//�Կո�,���,������Ϊ�ָ���
			String[] tokens = s.split("\\s+|\\.|\\(|\\)|\\{|\\}|\\[|\\]");
			//���嵱ǰ����������s�ַ����еĿ�ʼλ��
			int curStart = 0;
			//���嵥���Ƿ���������
			boolean isQuot=false;
			for(String token : tokens){
				//�ҳ���ǰ����������s�ַ����е�λ��
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
					//ʹ�ø�ʽ���Ե�ǰ����������ɫ
					formatter.setHightLight(doc,token,start+tokenPos,token.length());
				}
				//��ʼ������һ������
				curStart = tokenPos + token.length();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	//�ػ������,�����к�
	public void paint(Graphics g){
		super.paint(g);
		Element root = doc.getDefaultRootElement();
		//��ȡ�к�
		int line= root.getElementIndex(doc.getLength());
		//������ɫ
		g.setColor(new Color(220,230,240));
		//������ʾ�����ľ��ο�
		g.fillRect(0, 0, this.getMargin().left-10, getHeight());
		//�����кŵ���ɫ
		g.setColor(new Color(40,50,60));
		//ÿ�л���һ���к�
		for(int count=0,j=1; count<=line; ++count,++j){
			g.drawString(String.valueOf(j),3,
					(int)(j*1.535*StyleConstants.getFontSize(normalAttr)));
		}
	}
	public static void main(String[] args){
		JFrame jf= new JFrame("�ı��༭��");
		//ʹ��MyTextPane
		jf.getContentPane().add(new JScrollPane(new MyTextPane()) );
		final int inset = 200;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		jf.setBounds(inset,inset, screenSize.width-inset*2,screenSize.height-inset*2);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
}

//�����﷨��ʽ��
class SyntaxFormatter{
	//��һ��Map����ؼ��ֺ���ɫ�Ķ�Ӧ��ϵ
	private Map<SimpleAttributeSet,ArrayList<String>> attMap = new HashMap<>();
	//�����ĵ��������ı������
	SimpleAttributeSet normalAttr = new SimpleAttributeSet();
	public SyntaxFormatter(String syntaxFile){
		//���������ı�����ɫ,��С
		StyleConstants.setForeground(normalAttr, Color.black);
		StyleConstants.setFontSize(normalAttr, 16);
		//����Scanner,��������﷨�ļ���Ϣ
		Scanner scaner = null;
		try{
			scaner = new Scanner(new File(syntaxFile));
		}catch(FileNotFoundException e){
			throw new RuntimeException("�﷨�ļ���ʧ:" + e.getMessage());
		}
		int color = -1;
		ArrayList<String> keywords = new ArrayList<>();
		//���϶�ȡ�﷨�ļ�������
		while(scaner.hasNextLine()){
			String line = scaner.nextLine();
			//�����ǰ����"#"��ͷ
			if(line.startsWith("#")){
				if(keywords.size()>0 && color>-1){
					//ȡ����ǰ�е���ɫ,����װ��SimpleAttributeSet����
					SimpleAttributeSet att = new SimpleAttributeSet();
					StyleConstants.setForeground(att, new Color(color));
					StyleConstants.setFontSize(att, 16);
					//����ǰ��ɫ�͹ؼ���list��Ӧ����
					attMap.put(att, keywords);
				}
				//���´���һ���ؼ���list,Ϊ��һ���﷨��ʽ��׼��
				keywords = new ArrayList<>();
				color = Integer.valueOf(line.substring(1),16);
				
			}else{
				//������ͨ�ı���,��ÿ��������ӵ��ؼ���list����
				if(line.trim().length() > 0){
					keywords.add(line.trim());
				}
			}
		}
		
		scaner.close();
		//�����еĹؼ��ֺ���ɫ��Ӧ����
		if(keywords.size()>0 && color>-1){
			SimpleAttributeSet att = new SimpleAttributeSet();
			StyleConstants.setForeground(att, new Color(color));
			StyleConstants.setFontSize(att,16);
			attMap.put(att, keywords);
		}
	}
	
	//���ظø�ʽ���������ı����������
	public SimpleAttributeSet getNormalAttributeSet(){
		return normalAttr;
	}
	//�����﷨����
	public void setHightLight(StyledDocument doc,String token,int start,int length){
		//���浱ǰ���ʶ�Ӧ���������
		SimpleAttributeSet curAttr = null;
		outer:
		for(SimpleAttributeSet att:attMap.keySet()){
			//ȡ����ǰ���ʶ�Ӧ���������
			ArrayList<String> keywords = attMap.get(att);
			//�������йؼ���
			for(String keyword:keywords){
				if(keyword.equals(token)){
					//����ѭ��,�����õ�ǰ���ʶ�Ӧ ���������
					curAttr = att;
					break outer;
				}
			}
		}
		if(curAttr != null){
			//���õ�ǰ���ʵ���ɫ
			doc.setCharacterAttributes(start, length, curAttr, false);
		}else{
			//����ʹ����ͨ��������øõ���
			doc.setCharacterAttributes(start, length, normalAttr, false);
		}
	}
}