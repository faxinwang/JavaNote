package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/* GridLayout将容器分割成纵横线分割的网格，每个网格占的区域大小相同，与FlowLayout不同的是
 * GridLayout的每个网格里的组件大小由网格觉定，组件将自动占满整个网格区域
 * GridLayout();
 * GridLayout(int rows,int cols);
 * GridLayout(int rows,int cols,int hgap,int vgap);
 */
public class GridLayoutTest {

	
	
	public static void main(String[] args){
		Frame f =new Frame("AWT:计算器");
		//添加文本框
		Panel p1 = new Panel();
		TextField text = new TextField(30);
		p1.add(text);
		f.add(p1, BorderLayout.NORTH);
		//添加按钮
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(3,5,4,4));
		String[] name = {
				"0","1","2","3","4",
				"5","6","7","8","9",
				"+","-","*","/","="
		};
		//向Panel中添加15个按钮
		for(int i=0;i<name.length;++i){
			p2.add(new Button(name[i]));
		}
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.add(p2);
		f.pack();
		f.setVisible(true);
	}
}
