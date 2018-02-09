package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.Box;

class ComponentsTest {
	Frame f = new Frame("AWT:ComponentsTest");
	Button ok = new Button("确认");
	CheckboxGroup cbg = new CheckboxGroup();
	//定义一个单选框,处于cbg组中，初始处于被选中状态
	Checkbox male = new Checkbox("女",cbg,true);
	Checkbox female = new Checkbox("男",cbg,false);
	//复选框，初始未被选中
	Checkbox married = new Checkbox("是否已婚?",false);
	//下拉选择框
	Choice colorChooser = new Choice();
	//列表选择框
	List  colorList = new List(6,true);
	//5行， 30列的文本域
	TextArea txts = new TextArea(5,30);
	//50列的单行文本框
	TextField txt = new TextField(50);
	
	void init(){
		colorChooser.add("红色");
		colorChooser.add("绿色");
		colorChooser.add("蓝色");
		colorList.add("红色");
		colorList.add("绿色");
		colorList.add("蓝色");
		//创建一个装载了文本框，按钮的Panel
		Panel bottom = new Panel();
		bottom.add(txt);
		bottom.add(ok);
		f.add(bottom,BorderLayout.SOUTH);//放在下面
		//创建一个装载了下拉列表框，三个checkbox的panel
		Panel checkPanel = new Panel();
		checkPanel.add(colorChooser);
		checkPanel.add(male);
		checkPanel.add(female);
		checkPanel.add(married);
		//创建一个垂直排列组件的Box,盛装多行文本
		Box topLeft = Box.createVerticalBox();
		topLeft.add(txts);
		topLeft.add(checkPanel);
		//创建一个水平排列的Box,盛装topLeft,colorList
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		f.add(top);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new ComponentsTest().init();
	}
}
