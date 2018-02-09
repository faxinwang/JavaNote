package myjava.awt.datatransfer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;

/* AWT�м�������ز����Ľӿں��౻����java.awt.datastransfer���£������Ǹð��µ���Ҫ�ӿ�
 * ��������˵��.
 * Clipboard:����һ��������ʵ���������Ǳ��ؼ������ϵͳ������
 * ClipboardOwner:���������ݵ������ߣ������������ݵ�����Ȩ���޸�ʱ��ϵͳ���ᴥ���������ߵ�lostOwnership�¼�������
 * Transferable:�ýӿ�ʵ������Ž��������еĴ������
 * DataFlavor:���ڱ��������������ݵĸ�ʽ
 * StringSelection:Transferable��ʵ���࣬���ڴ����ı��ַ���
 * FlavorListener:���ݸ�ʽ�������ӿ�
 * FlavorEvent:�����ʵ����װ�����ݸ�ʽ�ı���¼���
 */


public class SimpleClipboard {
	Frame f = new Frame("�򵥵ļ��������");
	//��ȡϵͳ������
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	//�����Ǵ������ؼ�����Ĵ���
	//Clipboard clipboard = new Clipboard("clipboardname");
	//���ڸ����ı����ı���
	TextArea txtsCopy = new TextArea(6,30);
	//����ճ�����ı���
	TextArea txtsPaste = new TextArea(6,30);
	Button btCopy = new Button("����");
	Button btPaste = new Button("ճ��");
	
	void init(){
		Panel p = new Panel();
		p.add(btCopy);
		p.add(btPaste);
		btCopy.addActionListener(event -> {
			//��һ�������ı�������ַ�����װ��һ��StringSelection����
			StringSelection contents = new StringSelection(txtsCopy.getText());
			//��StringSelection������������
			clipboard.setContents(contents, null);//��������ݣ����ݳ�����,һ�᲻��Ҫ�������ݳ����ߣ�����Ϊnull
		});
		btPaste.addActionListener(event ->{
			//����������а���StringFlavor��ʽ������
			if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
				try{
					//ȡ���������е�stringFlavor����
					String content = (String)clipboard.getData(DataFlavor.stringFlavor);
					txtsPaste.append(content);
				}catch(Exception e){
					System.out.println("��ȡ����������ʧ��!");
					e.printStackTrace();
				}
			}
		});
		//����һ��ˮƽ���е�box����
		Box box = new Box(BoxLayout.X_AXIS);
		//�����������ı������box������
		box.add(txtsCopy);
		box.add(txtsPaste);
		//����ť���ڵ�panel��box������ӵ�Frame������
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.add(box);//center
		f.add(p,BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new SimpleClipboard().init();
	}
}
