package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

import javax.swing.Box;

/* BoxLayout保留CardBagLayout的优点，但没有它那么复杂,
 * BoxLayout(container target,int axis):指定创建基于target的布局管理器
 * axis指定组件在容器中的排列方向:BoxLayout.X_AXIS(横向),BoxLayout.Y_AXIS(纵向);
 * 
 * BoxLayout通常和Box容器一起使用,Box容器默认使用BoxLayout布局管理器,Box提供了下面两个方法
 * 来创建Box对象:
 * createHorizontalBox():创建一个水平排列组件的Box容器
 * createVerticalBox():创建一个垂直排列组件的Box容器
 * Box提供了如下5个方法来创建Glue,Strut,RigidArea.
 * createHorizontalGlue():创建一条水平的glue(可在两个方向上同时拉伸的间距)
 * createVerticalGlue():创建一条垂直glue(可在两个方向上同时拉伸的间距)
 * createHorizontalStrut(int width):创建一条指定宽度的水平strut(可在水平方向上拉伸的间距)
 * createVerticalStrut(int height):创建一条指定高度的垂直strut(可在水平方向上拉伸的间距)
 * createRigidArea(Dimension d):创建指定宽度，高度的RigidArea(不可拉伸的间距)
 */
public class BoxLayoutTest {

	Frame f = new Frame("AWT:BoxLayout");
	//定义水平摆放组件的box容器对象
	Box horizontal = Box.createHorizontalBox();
	//定义垂直摆放组件的box容器对象
	Box vertical = Box.createVerticalBox();
	void init(){
		horizontal.add(new Button("水平按钮1"));
		horizontal.add(Box.createHorizontalGlue());
		horizontal.add(new Button("水平按钮2"));
		//添加水平方向不可拉伸的间距，其宽度为10
		horizontal.add(Box.createHorizontalStrut(10));
		horizontal.add(new Button("水平按钮3"));
		vertical.add(new Button("垂直按钮1"));
		vertical.add(Box.createVerticalGlue());
		vertical.add(new Button("垂直按钮2"));
		vertical.add(Box.createVerticalStrut(15));
		vertical.add(new Button("垂直按钮3"));
		f.add(horizontal,BorderLayout.NORTH);
		f.add(vertical);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new BoxLayoutTest().init();
	}
}
