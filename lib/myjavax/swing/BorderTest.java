package myjavax.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

/* 为组件设置边框可按如下两步进行:
 * 使用BorderFactory或者XxxBorder创建XxxBorder实例
 * 调用Swing组件的setBorder(Border b)方法为该组件设置边框
 */

public class BorderTest {
	private JFrame jf = new JFrame("测试边框");
	
	public JPanel getPanelWithBorder(Border b,String BorderName){
		JPanel p = new JPanel();
		p.add(new JLabel(BorderName));
		p.setBorder(b);
		return p;
	}
	public void init(){
		jf.setLayout(new GridLayout(2,4));
		//创建BevelBorder
		Border bb = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.red, 
				Color.green,Color.BLUE,Color.GRAY);
		jf.add(getPanelWithBorder(bb,"BevelBorder"));
		//创建LineBorder
		Border lb = BorderFactory.createLineBorder(Color.ORANGE, 10);
		jf.add(getPanelWithBorder(lb,"LineBorder"));
		//创建EmptyBorder,EmptyBorder就是在组件周围留空
		Border eb = BorderFactory.createEmptyBorder(20, 5, 10, 30);
		jf.add(getPanelWithBorder(eb,"EmptyBorder"));
		//创建EtchedBorder
		Border etb =BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.RED, Color.GREEN);
		jf.add(getPanelWithBorder(etb,"EtchedBorder"));
		//直接创建TitledBorder,TitledBorder就是为原有边框增加标题
		TitledBorder tb=new TitledBorder(lb,"测试标题",TitledBorder.LEFT,TitledBorder.BOTTOM,
				new Font("StSong",Font.BOLD,18),Color.BLUE);
		jf.add(getPanelWithBorder(tb,"TitledBorder_lb"));
		//直接创建MatteBorder,MatteBorder是EmptyBorder的子类
		//它可以指定留空区域的颜色或背景,此处指定的是颜色
		MatteBorder mb = new MatteBorder(20,5,10,30,Color.GREEN);
		jf.add(getPanelWithBorder(mb,"MatteBorder"));
		//直接创建CompoundBorder,CompoundBorder将两个边框组合成新边框
		CompoundBorder cb1 =new CompoundBorder(new LineBorder(Color.RED,8),bb);
		jf.add(getPanelWithBorder(cb1,"CompoundBorder_lb_bb"));
		CompoundBorder cb2 =new CompoundBorder(tb,bb);
		jf.add(getPanelWithBorder(cb2,"CompoundBorder_tb_bb"));
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args){
		new BorderTest().init();
	}
}
