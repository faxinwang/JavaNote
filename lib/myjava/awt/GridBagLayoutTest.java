package myjava.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/* 使用GridBagLayout的步骤:
 * 1.创建GridBagLayout布局管理器，并指定容器使用该布局管理器
 * 	 GridBagLayout gbl = new GridBagLayout();
 *   container.setLayout(gbl);
 * 2.创建GridBagConstraints对象，并设置该对象的相关属性
 *   GridBagConstraints gbc = new GridBagConstraints();
 *   gbc.gridx = 1;//设置受控制组件在网格中的横向索引(索引从0开始)
 *   gbc.gridwidth = 2;//设置受控制的组件横向跨越多少个网格
 *   gbc.fill = GridBagConstraints.NONE:组件不扩大
 *   gbc.fill = GridBagConstraints.HORIZONTAL:组件水平扩大以占据空白空间
 *   gbc.fill = GridBagConstraints.VERTICAL:组件垂直扩大以占据空白空间
 *   gbc.fill = GridBagConstraints.BOTH:组件水平，垂直同时扩大以占据空白空间
 *   gbc.ipadx = 5 ; //组件内文字与组件边际的水平距离
 *   gbc.insets;//设置组件的外部填充，即组件边际与显示区域边界之间的距离
 *   gbc.anchor;//设置组件在其显示区域中的定位方式,其值为GridBagConstraints类中的枚举值
 *   gbc.weightx; //设置组件在水平方向上与其他组件相比占据的空间比例,该属性的默认值为0,即默认不占据多余空间
 *   ...
 * 3.调用GridBagLayout对象的方法来建立GridBagConstraints对象和受控制的组件之间的关联
 *   gbl.setConstraints(widget gbc);设置wedget组件受gbc对象控制
 * 4.将组件添加到容器
 *   container.add(widget);
 */


public class GridBagLayoutTest {
	Frame f = new Frame("AWT:GridBagLayout");
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	Button[] btns = new Button[10];
	void addButton(Button btn){
		gbl.setConstraints(btn, gbc);
		f.add(btn);
	}
	void init(){
		f.setLayout(gbl);
		for(int i=0;i<btns.length;++i){
			btns[i] = new Button("按钮"+ i);
		}
		//组件可以在横向,纵向上扩大
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.ipadx = 30;//文字与组件间的距离
		gbc.ipady = 5;
	//	gbc.gridx = 2;//在网格中的横向索引
		addButton(btns[0]);
	//	gbc.gridx = 0;
		addButton(btns[1]);
		addButton(btns[2]);
		addButton(btns[3]);
		//该constraint控制的组件将会成为横向上最后一个组件
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 2;
		
		addButton(btns[4]);
		//该constraints控制的组件将会跨越两个网格
		gbc.gridwidth = 2;
		addButton(btns[5]);
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		addButton(btns[6]);
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		//改GridBagConstraints控制的组件纵向扩大的权重是1
		gbc.weighty = 1;
		addButton(btns[7]);
		//设置下面的按钮纵向上不会扩大
		gbc.weighty = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		//纵向跨越1个网格
		gbc.gridheight = 1;
		addButton(btns[8]);
		addButton(btns[9]);
		f.pack();
		f.setVisible(true);
	}
	
	
	public static void main(String[] args){
		new GridBagLayoutTest().init();
	}
}
