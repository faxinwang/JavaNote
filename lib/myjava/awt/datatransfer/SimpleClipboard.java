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

/* AWT中剪贴板相关操作的接口和类被放在java.awt.datastransfer包下，下面是该包下的重要接口
 * 和类的相关说明.
 * Clipboard:代表一个剪贴板实例，可以是本地剪贴板或系统剪贴板
 * ClipboardOwner:剪贴板内容的所有者，当剪贴板内容的所有权被修改时，系统将会触发该所有者的lostOwnership事件监听器
 * Transferable:该接口实例代表放进剪贴板中的传输对象
 * DataFlavor:用于表述剪贴板中数据的格式
 * StringSelection:Transferable的实现类，用于传输文本字符串
 * FlavorListener:数据格式监听器接口
 * FlavorEvent:该类的实例封装了数据格式改变的事件。
 */


public class SimpleClipboard {
	Frame f = new Frame("简单的剪贴板程序");
	//获取系统剪贴板
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	//下面是创建本地剪贴板的代码
	//Clipboard clipboard = new Clipboard("clipboardname");
	//用于复制文本的文本框
	TextArea txtsCopy = new TextArea(6,30);
	//用于粘贴的文本框
	TextArea txtsPaste = new TextArea(6,30);
	Button btCopy = new Button("复制");
	Button btPaste = new Button("粘贴");
	
	void init(){
		Panel p = new Panel();
		p.add(btCopy);
		p.add(btPaste);
		btCopy.addActionListener(event -> {
			//将一个多行文本域里的字符串封装成一个StringSelection对象
			StringSelection contents = new StringSelection(txtsCopy.getText());
			//将StringSelection对象放入剪贴板
			clipboard.setContents(contents, null);//放入的内容，内容持有者,一搬不需要关心内容持有者，故设为null
		});
		btPaste.addActionListener(event ->{
			//如果剪贴板中包含StringFlavor格式的内容
			if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
				try{
					//取出剪贴板中的stringFlavor内容
					String content = (String)clipboard.getData(DataFlavor.stringFlavor);
					txtsPaste.append(content);
				}catch(Exception e){
					System.out.println("读取剪贴板内容失败!");
					e.printStackTrace();
				}
			}
		});
		//创建一个水平排列的box容器
		Box box = new Box(BoxLayout.X_AXIS);
		//将两个多行文本域放在box容器中
		box.add(txtsCopy);
		box.add(txtsPaste);
		//将按钮所在的panel，box容器添加到Frame窗口中
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
