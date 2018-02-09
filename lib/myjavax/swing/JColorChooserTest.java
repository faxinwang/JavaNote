package myjavax.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class JColorChooserTest {
	//画图区的大小
	private final int AREA_WIDTH = 500;
	private final int AREA_HEIGHT = 400;
	//上一次鼠标的位置
	private int preX = -1;
	private int preY = -1;
	//定义一个右键菜单用于设置画笔颜色
	JPopupMenu pop = new JPopupMenu();
	JMenuItem cc_item = new JMenuItem("选择颜色");
	JMenuItem clear_item=new JMenuItem("清屏");
	BufferedImage image  = new BufferedImage(AREA_WIDTH,AREA_HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	//获取image对象的Graphics
	Graphics g =image.getGraphics();
	private JFrame jf = new JFrame("简单手绘程序");
	private DrawCanvas canvas = new DrawCanvas();
	//用于保存画笔颜色
	private Color foreColor = new Color(255,0,0);
	
	public void init(){
		//将菜单项组合成右键菜单
		pop.add(cc_item);
		pop.add(clear_item);
		canvas.setComponentPopupMenu(pop);
		//将image对象的颜色填充成白色
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		canvas.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));
		g.setColor(foreColor);
		jf.add(canvas);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		
		add_actions();
	}
	//给各组件设置事件监听器
	private void add_actions(){
		//为ColorChooser菜单项添加事件监听器
		cc_item.addActionListener(listener -> {
			//下面代码直接弹出一个模式对话框,并返回用户选择的颜色
		//	foreColor = JColorChooser.showDialog(jf, "选择画笔颜色", foreColor);
			//下面代码则弹出一个非模式的颜色选择对话框
			//并可以分别为"确定","取消"按钮指定事件监听器
			final JColorChooser colorPane = new JColorChooser(foreColor);
			JDialog jd = JColorChooser.createDialog(jf, "选择画笔颜色", 
					false, colorPane, lis-> foreColor=colorPane.getColor(), null);
			g.setColor(foreColor);
			jd.setVisible(true);
			System.out.println("当前画笔颜色:" + g.getColor());
		});
		clear_item.addActionListener( listener ->{
			g.setColor(new Color(255,255,255));
			g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
			canvas.repaint();
			g.setColor(foreColor);
		});
		//监听鼠标移动动作
		canvas.addMouseMotionListener(new MouseMotionAdapter(){
			//实现按下鼠标并拖动的事件处理器
			@Override
			public void mouseDragged(MouseEvent e){
				if(preX < 0 && preY <0){
					preX = e.getX();
					preY = e.getY();
				}
				//设置当前颜色
			//	g.setColor(foreColor);
				g.drawLine(preX, preY, e.getX(), e.getY());
				preX = e.getX();
				preY = e.getY();
				canvas.repaint();
			}
		});
		
		//监听鼠标事件
		canvas.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				g.setColor(foreColor);
				g.drawLine(0, 0, e.getX(),e.getY());
				canvas.repaint();
			}
			//实现鼠标松开的事件处理器
			@Override
			public void mouseReleased(MouseEvent e){				
				//松开鼠标键时,把上次鼠标拖动事件的X,Y坐标设为-1
				preX = preY = -1;
				System.out.println("MouseReleased!");
			}
		});
	}
	
	@SuppressWarnings("serial")
	//让画图区域继承JPanel类
	class DrawCanvas extends JPanel{
		//重写Jpanel的paint()方法,实现重绘
		@Override
		public void paint(Graphics g){
			g.drawImage(image, 0, 0, null);
		}
	}
	
	public static void main(String[] args){
		new JColorChooserTest().init();
	}
}
