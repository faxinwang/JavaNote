package myjava.awt;

import static java.awt.BorderLayout.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

/* Frame,Dialog,ScrollPane默认都使用了BorderLayout布局管理器
 * BorderLayout()
 * BordreLayout(int hgap,int vgap)
 * 向BorderLayout中添加组件时,应使用其静态常量指定组件加入的位置:
 * EAST(右，东),NORTH(上，北),WEST(左，西),SOUTH(下，南),CNETER(中)
 */
public class BorderLayoutTest {
	static void test1(){
		Frame f = new Frame("AWT-Freame:BorderLayout");
		f.setLayout(new BorderLayout(30,5));//设置布局管理器
		f.add(new Button("南"),SOUTH);
		f.add(new Button("北"),NORTH);
		f.add(new Button("东"),EAST);
		f.add(new Button("西"),WEST);
		f.add(new Button("中"));//默认添加到中间区域
		//调整窗口到最佳大小
		f.pack();
		f.setVisible(true);
	}
	static void test2(){
		Frame f = new Frame("AWT-Frame:BorderLayout");
		f.setLayout(new BorderLayout(30,5));
		f.add(new Button("南"), SOUTH);
		f.add(new Button("北"),NORTH);
		Panel p = new Panel();
		p.add(new TextField(20));
		p.add(new Button("btn"));
		f.add(p);
		f.add(new Button("东"),EAST);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args){
		test1();
		test2();
	}
}
