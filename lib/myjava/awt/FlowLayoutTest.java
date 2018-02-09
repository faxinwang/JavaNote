package myjava.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

//AWT提供了FlowLayout,BorderLayout,GridLayout,GirdBagLayout,CardLayout
//Swing 还提供了一个BoxLayout

/* FlowLayout提供了三个构造函数: 
 * FlowLayout():使用默认的对其方式，水平间距，垂直间距
 * FlowLayout(int align):使用指定的对其方式，默认的间距
 * FlowLayout(int align,int hgap,int vgap):
 * align参数指定组件的排列方向(从左向右，从右向左，从中间向两边等)，
 * 该参数应该使用FlowLayout的静态常量：FlowLayout.LEFT,--.CENTER,--.RIGHT
 */

public class FlowLayoutTest {
	
	static void test1(){
		//Frame的默认布局管理器是BorderLayout
		Frame f =new Frame("AWT-Frame1:FlowLayout.LEFT");
		f.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("按钮" + i));
		}
		//设置窗口为最佳大小
		f.pack();
		f.setVisible(true);
	}
	static void test2(){
		//Frame的默认布局管理器是BorderLayout
		Frame f =new Frame("AWT-Frame2:FlowLayout.RIGHT");
		f.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("按钮" + i));
		}
		//设置窗口为最佳大小
		f.pack();
		f.setVisible(true);
	}
	static void test3(){
		//Frame的默认布局管理器是BorderLayout
		Frame f =new Frame("AWT-Frame3:FlowLayout.CENTER");
		f.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("按钮" + i));
		}
		//设置窗口为最佳大小
		f.pack();
		f.setVisible(true);
	}
	static void test4(){
		//Frame的默认布局管理器是BorderLayout
		Frame f =new Frame("AWT-Frame4:FlowLayout.LEADING");
		f.setLayout(new FlowLayout(FlowLayout.LEADING,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("按钮" + i));
		}
		//设置窗口为最佳大小
		f.pack();
		f.setVisible(true);
	}
	static void test5(){
		//Frame的默认布局管理器是BorderLayout
		Frame f =new Frame("AWT-Frame5:FlowLayout.TRAILING");
		f.setLayout(new FlowLayout(FlowLayout.TRAILING,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("按钮" + i));
		}
		//设置窗口为最佳大小
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		test1();
		test2();
		test3();
		test4();
		test5();
	}
}
