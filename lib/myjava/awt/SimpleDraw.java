package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

/* Graphics是一个抽象的画笔对象，Graphics可以在组件上绘制丰富多彩的几何图形和位图:
 * drawLine()绘制直线
 * drawString()绘制字符串
 * drawRect()绘制矩形
 * drawRoundRect()绘制圆角矩形
 * drawOval()绘制椭圆形
 * drawPolygon()绘制多边形边框
 * drawArc()绘制一段圆弧
 * drawPolyline()绘制折线
 * fillRect()填充一个矩形区域
 * fillFoundRect()填充一个圆角矩形区域
 * fillOval()填充一个椭圆形区域
 * fillPolygon()填充一个多边形区域
 * fillArc()填充圆弧和圆弧两个端点到中心连线所包围的区域
 * drawImage()绘制位图
 * setColor()设置画笔颜色
 * setFount()设置字体，仅对drawString()有效
 */
public class SimpleDraw {
	final String RECT_SHAPE = "rect";
	final String OVAL_SHAPE = "oval";
	Frame  f = new Frame("简单绘图");
	Button rect = new Button("绘制矩形");
	Button oval = new Button("绘制圆形");
	MyCanvas drawArea = new MyCanvas();
	Panel south = new Panel();
	Random rand = new Random();
	//用于保存需要绘制什么图形的变量
	String shape = "";
	public void init(){
		rect.addActionListener( e ->{
			//设置shape为RECT_SHAPE
			shape = RECT_SHAPE;
			for(int i=0;i<rand.nextInt(20);++i)
				drawArea.repaint();
			//repaint()会调用undate(Graphice g)
			//undate()会调用paint(Graphice g)
		});
		oval.addActionListener(e ->{
			shape = OVAL_SHAPE;
			for(int i=0;i<rand.nextInt(20);++i)
				drawArea.repaint();
		});
		south.add(rect);
		south.add(oval);
		drawArea.setPreferredSize(new Dimension(500,300));
		f.add(drawArea);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.add(south,BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class MyCanvas extends Canvas{
		//重写Canvas的paint()方法,实现重绘
		public void paint(Graphics g){
			//生成随机颜色
			g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			int x = rand.nextInt(drawArea.getWidth());
			int y = rand.nextInt(drawArea.getWidth());
			int width = 30 + rand.nextInt(70);
			int height = 30 + rand.nextInt(70);
			//越界处理
			if( x > drawArea.getWidth() - width)
				x = drawArea.getWidth() - width;
			if( y > drawArea.getHeight() - height) 
				y = drawArea.getHeight() - height;
			
			if(shape.equals(RECT_SHAPE)){
				//随机绘制一个矩形，位置和大小都是随机值
				g.fillRect(x, y, width, height);
			}
			if(shape.equals(OVAL_SHAPE)){
				//随机绘制一个椭圆，位置和大小都是随机值
				g.fillOval(x, y, width, height);
			}
		}
	}
	
	
	public static void main(String[] args){
		new SimpleDraw().init();
	}
}
