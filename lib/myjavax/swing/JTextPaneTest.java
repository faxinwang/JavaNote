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

/* Swing�ṩ��StyleConstants������,�ù���������°������³��þ�̬����������MutableAttributeSet�������,��ɫ��
 * setAlignment(MutableAttributeSet a,int align):		�����ı����뷽ʽ
 * setBackground(MutableAttributeSet a,Color bg):		���ñ���ɫ
 * setForeground(MutableAttributeSet a,Color fg):		��������ǰ��ɫ
 * setBold(MutableAttributeSet a,boolean b):			�����Ƿ�ʹ�ô�����
 * setFirstLineIndent(MutableAttributeSet a,float i):	�������������Ĵ�С
 * setFontFamily(MutableAttributeSet a,String fam):		��������
 * setFontSize(MutableAttributeSet a,int s):			�������ִ�С
 * setItalic(MutableAttributeSet a,boolean b):			���������Ƿ�б��
 * setLeftIndent(MutableAttributeSet a,float i):		����������
 * setRightIndent(MutableAttributeSet a,float i):		����������
 * setLineSpacing(MutableAttributeSet a,float s):		�����м��
 * setStrikeThrough(MutableAttributeSet a,boolean b):	�����Ƿ�Ϊ�������ɾ����
 * setSubscript(MutableAttributeSet a,boolean b):		��ָ���������ó��ϱ�
 * setSuperscript(MutableAttributeSet a,boolean b):		��ָ���������ó��±�
 * setUnderline(MutableAttributeSet a, boolean b):		�����Ƿ�����»���
 */

@SuppressWarnings("unused")
public class JTextPaneTest {
	JFrame  mainWin = new JFrame("����JTextPane");
//	StyleContext context = new StyleContext();
	StyledDocument doc = new DefaultStyledDocument();
//	Style android = context.getStyle(StyleContext.DEFAULT_STYLE);
//	Style java = context.getStyle(StyleContext.DEFAULT_STYLE);
	
	//��������SimpleAttributeSet����
	SimpleAttributeSet android = new SimpleAttributeSet();
	SimpleAttributeSet java = new SimpleAttributeSet();
	SimpleAttributeSet javaee = new SimpleAttributeSet();
	public void init(){
		//Ϊandroid���Լ�������ɫ,����,�ֺź��»���,���䷽ʽ
		StyleConstants.setForeground(android, Color.red);
		StyleConstants.setFontSize(android, 24);
		StyleConstants.setFontFamily(android,"Dialog");
		StyleConstants.setUnderline(android,true);
		StyleConstants.setAlignment(android,StyleConstants.ALIGN_RIGHT);
		StyleConstants.setLeftIndent(android, 20);
		
		//Ϊjava���Լ�������ɫ,�ֺ�,����δ�����,���䷽ʽ
		StyleConstants.setForeground(java, Color.BLUE);
		StyleConstants.setFontSize(java, 30);
		StyleConstants.setFontFamily(java, "Arial Black");
		StyleConstants.setBold(java, true);
		StyleConstants.setAlignment(java,StyleConstants.ALIGN_CENTER);
		
		//Ϊjavaee���Լ�������ɫ,�ֺ�,б����
		StyleConstants.setForeground(javaee, Color.green);
		StyleConstants.setFontSize(javaee,32);
		StyleConstants.setItalic(javaee, true);
		
		try {
			doc.insertString(doc.getLength(), "���Android����\n", null);
			doc.insertString(doc.getLength(), "���Java����\n", null);
			
		} catch (BadLocationException e){
			e.printStackTrace();
		}
		
		JTextPane txtPane = new JTextPane(doc);
		//���ò�����༭
		txtPane.setEditable(true);
		
		//�ֱ�Ϊ�ĵ��������������ò�ͬ�������ʽ,true��ʾ�滻��ԭ��������,false��ʾ����������ӵ�ԭ����������
		int last=0,cur=0;
		String content=null;
		try {
			content = doc.getText(0, doc.getLength());
		} catch (BadLocationException e) {e.printStackTrace();}
		doc.setParagraphAttributes(last,(cur=content.indexOf('\n', last))-last,android,true);
		last = cur+1;
		doc.setParagraphAttributes(last,(cur=content.indexOf('\n', last))-last , java, true);
		//Ҳ������JTextPane������֮���ٲ����ı����������ʽ
		try {
			doc.insertString(doc.getLength(), "������Java EE��ҵӦ��ʵս\n", null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		last = cur+1;
		doc.setParagraphAttributes(last, doc.getLength()-last, javaee, true);
		
		SimpleAttributeSet attr = new SimpleAttributeSet();
		StyleConstants.setAlignment(attr, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontFamily(attr, "���ǵ������");
		StyleConstants.setFontSize(attr, 20);
		StyleConstants.setUnderline(attr, true);
		StyleConstants.setForeground(attr, new Color(111,222,111));
		try {
			doc.insertString(doc.getLength(), "���ǵ������,�Ҷ���,�»���,20��", attr);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		mainWin.add(new JScrollPane(txtPane));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int inset = 200;
		//���������ڴ�С
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
