package myjavax.swing;

import java.awt.BorderLayout;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

class TransferHandlerTest{
	private JFrame jf = new JFrame("测试TransferHandler");
	JColorChooser chooser = new JColorChooser();
	JTextArea jta = new JTextArea(8,30);
	
	public void init(){
		jta.append("测试TransferHandler\n直接将下面颜色拖入以改变文本颜色");
		//启动颜色选择器面板和文本域的拖放功能
		chooser.setDragEnabled(true);
		jta.setDragEnabled(true);
		jf.add(chooser,BorderLayout.SOUTH);
		//允许直接将一个Color对象拖入该JTextArea对象
		//并赋值给它的foregound属性
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
		jta.append("Swing的拖放支持\n");
		jta.append("将该文本域的内容拖入其他程序!");
		//启动文本域和单行文本框的拖放支持
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
