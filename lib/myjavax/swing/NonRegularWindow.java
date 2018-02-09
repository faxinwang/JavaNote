package myjavax.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class NonRegularWindow extends JFrame implements ActionListener{
	//定义4个窗口
	JFrame transWin = new JFrame("透明窗口");
	JFrame gradientWin = new JFrame("渐变透明窗口");
	JFrame bgWin = new JFrame ("背景图片窗口");
	JFrame shapeWin = new JFrame("椭圆窗口");
	
	public NonRegularWindow(){
		super("不规则窗口测试");
		setLayout(new FlowLayout());
		JButton transBn = new JButton("透明窗口");
		JButton gradientBn = new JButton("渐变透明窗口");
		JButton bgBn = new JButton("背景图片窗口");
		JButton shapeBn = new JButton("椭圆窗口");
		//为4个按钮添加事件监听器
		transBn.addActionListener(this);
		gradientBn.addActionListener(this);
		bgBn.addActionListener(this);
		shapeBn.addActionListener(this);
		add(transBn);
		add(gradientBn);
		add(bgBn);
		add(shapeBn);
		/*******设置透明窗口*********/
		transWin.setLayout(new GridBagLayout());
		transWin.setSize(300, 200);
		transWin.add(new JButton("透明窗口里的简单按钮"));
		//设置透明度为0.65f,透明度为1f时完全不透明
		transWin.setOpacity(0.65f);
		/********设置渐变透明的窗口********/
		gradientWin.setBackground(new Color(0,0,0,0));
		gradientWin.setSize(new Dimension(300,200));
		//使用一个JPanel对象作为渐变透明的背景
		JPanel panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				if(g instanceof Graphics2D){
					final int R = 120;
					final int G = 190;
					final int B = 240;
					//创建一个渐变画笔
					Paint p = new GradientPaint(0.0f,0.0f,
							new Color(R,G,B,0),
							0.0f,getHeight(),
							new Color(R,G,B,255),true);
					Graphics2D g2d = (Graphics2D)g;
					g2d.setPaint(p);
					g2d.fillRect(0, 0, getWidth(), getHeight());
				}
			}
		};
		//使用JPanel对象作为JFrame的contentPane
		gradientWin.setContentPane(panel);
		panel.setLayout(new GridBagLayout());
		gradientWin.add(new JButton("渐变透明窗口里的简单按钮"));
		
		/*******设置有背景图片的窗口*********/
		bgWin.setBackground(new Color(0,0,0,0));
		bgWin.setSize(new Dimension(300,200));
		//使用一个JPanel作为背景图片
		JPanel bgPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				try{
					Image bg = ImageIO.read(new File("./src/img/wfx.jpg"));
					//绘制一张图片作为背景
					g.drawImage(bg, 0, 0, getWidth(),getHeight(),null);
				}catch(IOException e){ e.printStackTrace();}
			}
		};
		//使用bgPanel作为JFrame的contentPane
		bgWin.setContentPane(bgPanel);
		bgPanel.setLayout(new GridBagLayout());
		bgWin.add(new JButton("有背景图片窗口里的简单按钮"));
		
		/*******设置椭圆形窗口*********/
		shapeWin.setLayout( new GridBagLayout());
		shapeWin.addComponentListener( new ComponentAdapter(){
			//当窗口大小被改变时,椭圆的大小也会相应的改变
			@Override
			public void componentResized(ComponentEvent e){
				//设置窗口形状
				shapeWin.setShape(new Ellipse2D.Double(0, 0,
						shapeWin.getWidth(), shapeWin.getHeight()));
			}
		});
		shapeWin.setSize(300, 200);
		shapeWin.add(new JButton("椭圆形窗口里的简单按钮"));
		
		/********设置主程序窗口********/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "透明窗口":
			transWin.setVisible(true);
			break;
		case "渐变透明窗口":
			gradientWin.setVisible(true);
			break;
		case "背景图片窗口":
			bgWin.setVisible(true);
			break;
		case "椭圆窗口":
			shapeWin.setVisible(true);
			break;
		}
	}
	
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new NonRegularWindow();
	}
}
