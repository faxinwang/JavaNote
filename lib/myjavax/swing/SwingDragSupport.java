package myjavax.swing;

import java.awt.BorderLayout;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

class TransferHandlerTest{
	private JFrame jf = new JFrame("����TransferHandler");
	JColorChooser chooser = new JColorChooser();
	JTextArea jta = new JTextArea(8,30);
	
	public void init(){
		jta.append("����TransferHandler\nֱ�ӽ�������ɫ�����Ըı��ı���ɫ");
		//������ɫѡ���������ı�����ϷŹ���
		chooser.setDragEnabled(true);
		jta.setDragEnabled(true);
		jf.add(chooser,BorderLayout.SOUTH);
		//����ֱ�ӽ�һ��Color���������JTextArea����
		//����ֵ������foregound����
		jta.setTransferHandler(new TransferHandler("foreground"));
		jf.add(new JScrollPane(jta));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
}

public class SwingDragSupport {
	JFrame jf = new JFrame();
	JTextArea jta = new JTextArea(8,30);
	JTextField jtf = new JTextField(34);
	
	public void init(){
		jta.append("Swing���Ϸ�֧��\n");
		jta.append("�����ı��������������������!");
		//�����ı���͵����ı�����Ϸ�֧��
		jta.setDragEnabled(true);
		jtf.setDragEnabled(true);
//		jta.setTransferHandler(new TransferHandler("foreground"));
		jf.add(new JScrollPane(jta));
		jf.add(jtf,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	public static void main(String[] args){
		new SwingDragSupport().init();
		new TransferHandlerTest().init();
	}
}
