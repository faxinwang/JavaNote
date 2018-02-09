package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class JSliderTest {
	JFrame jf = new JFrame("滑动条示范");
	Box sliderBox = new Box(BoxLayout.Y_AXIS);
	JTextField showVal = new JTextField();
	ChangeListener listener;
	public void init(){
		//定义一个监听器,用于监听所有的滑动条
		listener = event ->{
			//取出滑动条中的值,并在文本中显示出来
			JSlider source =(JSlider)event.getSource();
			showVal.setText("当前滑动条的值:" + source.getValue());
		};
		//----------添加一个普通滑动条
		JSlider slider = new JSlider();
		addSlider(slider,"普通滑动条");
		//-----------添加保留区为30的滑动条
		slider = new JSlider();
		slider.setExtent(30);
		addSlider(slider,"保留区为30");
		//-----------添加带主次刻度的滑动条,并设置其最大值,最小值
		slider = new JSlider(30,200);
		//设置绘制刻度
		slider.setPaintTicks(true);
		//设置主次刻度的间距
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider,"有刻度");
		//-----------添加滑块必须停在有刻度地方的滑动条
		slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(25);
		slider.setMinorTickSpacing(5);
		addSlider(slider,"滑块停在刻度处");
		//-----------添加没有滑轨的滑动条
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(30);
		slider.setMinorTickSpacing(15);
		//设置不绘制滑轨
		slider.setPaintTrack(false);
		addSlider(slider,"无滑轨");
		//------------添加方向饭转的滑动条
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(30);
		slider.setMinorTickSpacing(10);
		//设置方向反转
		slider.setInverted(true);
		addSlider(slider,"方向反转");
		//------------添加绘制默认刻度的滑动条
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		//设置绘制刻度标签,默认绘制数值刻度标签
		slider.setPaintLabels(true);
		addSlider(slider,"数值刻度标签");
		//-----------添加绘制Label类型的刻度标签的滑动条
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		//设置绘制刻度标签
		slider.setPaintLabels(true);
		Dictionary<Integer,Component> labelTable = new Hashtable<>();
		labelTable.put(0,new JLabel("A"));
		labelTable.put(20, new JLabel("B"));
		labelTable.put(40, new JLabel("C"));
		labelTable.put(60, new JLabel("D"));
		labelTable.put(80, new JLabel("E"));
		labelTable.put(100, new JLabel("F"));
		//指定刻度标签,标签是JLabel
		slider.setLabelTable(labelTable);
		addSlider(slider,"JLabel标签");
		//--------------添加绘制Label类型的刻度标签的滑动条
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintLabels(true);
		String src="./src/icon/";
		labelTable = new Hashtable<Integer,Component>();
		labelTable.put(0, new JLabel(new ImageIcon(src+"new.png")));
		labelTable.put(20, new JLabel(new ImageIcon(src+"copy.png")));
		labelTable.put(40, new JLabel(new ImageIcon(src+"paste.png")));
		labelTable.put(60, new JLabel(new ImageIcon(src+"save.png")));
		labelTable.put(80, new JLabel(new ImageIcon(src+"exit.png")));
		//指定刻度标签,标签是ImageIcon
		slider.setLabelTable(labelTable);
		addSlider(slider,"Icon标签");
		jf.add(sliderBox);
		jf.add(showVal,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	//定义一个方法,用于将滑动条添加到容器中
	public void addSlider(JSlider slider,String description){
		slider.addChangeListener(listener);
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(new JLabel(description+":"));
		box.add(slider);
		sliderBox.add(box);
	}
	
	public static void main(String[] args){
		new JSliderTest().init();
	}
}
