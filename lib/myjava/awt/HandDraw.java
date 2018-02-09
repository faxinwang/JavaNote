package myjava.awt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class HandDraw {
	//画图区的大小
	final int AREA_WIDTH = 500;
	final int AREA_HEIGHT = 400;
	//上一次鼠标的位置
	int preX = -1;
	int preY = -1;
	//定义一个右键菜单用于设置画笔颜色
	PopupMenu pop = new PopupMenu();
	MenuItem redItem = new MenuItem("红色");
	MenuItem greenItem = new MenuItem("绿色");
	MenuItem blueItem = new MenuItem("蓝色");
	MenuItem clear = new MenuItem("清屏");
	//定义一个BufferedImage对象
	BufferedImage image = new BufferedImage(AREA_WIDTH,AREA_HEIGHT,BufferedImage.TYPE_INT_RGB);
	//获取image对象的Graphics
	Graphics g = image.getGraphics();
	Frame f = new Frame("简单手绘程序");
	DrawCanvas drawArea = new DrawCanvas();
	//用于保存画笔颜色
	Color Red = new Color(255,0,0);
	Color Green = new Color(0,255,0);
	Color Blue = new Color(0,0,255);
	Color White = new Color(255,255,255);
	Color foreColor = Red;
	
	void init(){
		//定义右键菜单的事件监听器
		ActionListener menuListener = e->{
			if(e.getActionCommand().equals("绿色")){
				foreColor = Green;
			}
			else if(e.getActionCommand().equals("红色")){
				foreColor = Red;
			}else if(e.getActionCommand().equals("蓝色")){
				foreColor = Blue;
			}else{
				g.setColor(White);
				g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
				drawArea.repaint();
			}
		};
		redItem.addActionListener(menuListener);
		greenItem.addActionListener(menuListener);
		blueItem.addActionListener(menuListener);
		clear.addActionListener(menuListener);
		//将菜单项添加到右键菜单中
		pop.add(blueItem);
		pop.add(greenItem);
		pop.add(redItem);
		pop.add(clear);
		//将右键菜单添加到drawArea菜单中
		drawArea.add(pop);
		//将image对象的背景色填充成白色
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		drawArea.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));
		drawArea.addMouseMotionListener(new MouseMotionAdapter(){
			//实现鼠标按下并拖动的事件处理
			public void mouseDragged(MouseEvent e){
//				System.out.println(g.getColor());
				if(preX>0 && preY>0){
					//设置当前颜色
					g.setColor(foreColor);
					//绘制上一次鼠标拖动事件点到本次鼠标拖动事件点的线段
					g.drawLine(preX, preY, e.getX(),e.getY());
				}
				preX = e.getX();
				preY = e.getY();
				drawArea.repaint();
			}
		});
		drawArea.addMouseListener(new MouseAdapter(){
			//实现鼠标松开时的事件处理
			public void mouseReleased(MouseEvent e){
				//弹出右键菜单
				if(e.isPopupTrigger()){
					pop.show(drawArea, e.getX(), e.getY());
				}
				//松开鼠标时把上一次鼠标拖动事件的X，Y坐标设为-1
				preX = -1;
				preY = -1;
//				System.out.println("MouseReleased!");
			}
		});
		f.add(drawArea);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.pack();
		f.setVisible(true);
	}
	
	
	@SuppressWarnings("serial")
	class DrawCanvas extends Canvas{
		public void paint(Graphics g){
			//将image绘制到该组件上
			g.drawImage(image, 0, 0, null);
		}
	}
	
	public static void main(String[] args){
		new HandDraw().init();
	}
}
