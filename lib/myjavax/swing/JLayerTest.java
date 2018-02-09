package myjavax.swing;

import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.beans.PropertyChangeEvent;
import java.lang.reflect.Method;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

public class JLayerTest {
	LayerUI<JComponent> layerUI=null;
	JLayer<JComponent> layer=null;
	JButton orderBn;
	private Timer stopper;
	
	public void init(){
		JFrame jf = new JFrame();
		JPanel jp = new JPanel();
		ButtonGroup group = new ButtonGroup();
		JRadioButton jrb;
		//创建三个JRadioButton,并将他们添加成一个组
		jp.add(jrb=new JRadioButton("网上购买"),true);
		group.add(jrb);
		jp.add(jrb= new JRadioButton("书店购买"));
		group.add(jrb);
		jp.add(jrb=new JRadioButton("图书馆借阅"));
		group.add(jrb);
		//添加三个JCheckBox组件
		jp.add(new JCheckBox("疯狂java讲义"));
		jp.add(new JCheckBox("疯狂android讲义"));
		jp.add(new JCheckBox("轻量级Java EE企业应用实战"));
		orderBn = new JButton("投票");
		jp.add(orderBn);
		//添加右键菜单用于修改程序界面风格
		jp.setComponentPopupMenu(new MyUIManager(jf,null).getJPopupMenu());;
		//创建LayerUI对象
//		layerUI = new FirstLayerUI();
//		layerUI = new BlurLayerUI();
		layerUI = new SpotlightLayerUI();
//		layerUI = new WaitingLayerUI();
		//使用layerUI来装饰指定的JPanel组件
		layer= new JLayer<JComponent>(jp,layerUI);
		//将装饰后的JPanel组件添加到容器
		jf.add(layer); 
		jf.setSize(300, 170);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		add_actions();
	}
	@SuppressWarnings("unchecked")
	private void add_actions(){
		orderBn.addActionListener(event ->{
			Class<WaitingLayerUI> clazz= (Class<WaitingLayerUI>)layerUI.getClass();
			try {
				Method start = clazz.getMethod("start",new Class<?>[]{});
				start.invoke(layerUI, new Object[]{});

				Method stop = clazz.getMethod("stop", new Class<?>[]{});
				if(stopper==null){
					System.out.println("创建stopper...");
					//设置4秒后执行指定动作,调用LayerUI的stop方法
					stopper= new Timer(4*1000,ae -> {
						try {
							stop.invoke(layerUI, new Object[]{});
						} catch (Exception e) {	e.printStackTrace();}
					});
					//设置定时器只触发一次
					stopper.setRepeats(false);
				}
				
				//如果stopper定时器已停止,则启动它
				if(!stopper.isRunning()){
					stopper.start();
				}
			} catch (Exception e) {e.printStackTrace();}
		});
	}
	
	public static void main(String[] args){
		new JLayerTest().init();
	}
}

//渐变颜色的JLayer
@SuppressWarnings("serial")
class FirstLayerUI extends LayerUI<JComponent>{
	public void paint(Graphics g,JComponent c){
		super.paint(g, c);
		Graphics2D g2 = (Graphics2D)g.create();
		//设置透明效果
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
		//设置渐变画笔颜色
		g2.setPaint(new GradientPaint(0,0,Color.RED,0,c.getHeight(),Color.ORANGE));
		//绘制一个与被装饰组件具有相同大小的组件
		g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		g2.dispose();
	}
}

//模糊效果的JLayer
@SuppressWarnings("serial")
class BlurLayerUI extends LayerUI<JComponent>{
	private BufferedImage screenBlurImage;
	private BufferedImageOp operation;
	public BlurLayerUI(){
		float ninth = 1.0f/9.0f;
		float[] blurKernel = {
			ninth,ninth,ninth,
			ninth,ninth,ninth,
			ninth,ninth,ninth
		};
		//ConvolveOp代表一个模糊处理,它将原图片的每一个像素与周围的
		//像素进行混合,从而计算出当前像素的颜色值
		operation = new ConvolveOp(
			new Kernel(3,3,blurKernel),
			ConvolveOp.EDGE_NO_OP,null
		);
	}
	@Override
	public void paint(Graphics g,JComponent c){
		int w = c.getWidth();
		int h = c.getHeight();
		//如果被装饰的窗口面积为0,直接返回
		if(w==0||h==0) return;
		//如果screenBlurImage没有初始化,或他的尺寸不对
		if(screenBlurImage == null 
			|| screenBlurImage.getWidth() != w
			|| screenBlurImage.getHeight()!= h)
		{
			//重新创建新的BufferedImage
			screenBlurImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		}
		Graphics2D ig2 = screenBlurImage.createGraphics();
		//把被装饰的组件的界面绘制当当期screenBlurImage上
		ig2.setClip(g.getClip());
		super.paint(ig2, c);
		ig2.dispose();
		Graphics2D g2= (Graphics2D)g;
		//对JLayer装饰的组件进行模糊处理
		g2.drawImage(screenBlurImage, operation, 0, 0);
	}
}

//"探照灯"效果的JLayer
@SuppressWarnings("serial")
class SpotlightLayerUI extends LayerUI<JComponent>{
	private boolean active;
	private int cx,cy;
	@Override
	public void installUI(JComponent c){
		super.installUI(c);
		JLayer<?> layer = (JLayer<?>)c;
		//设置JLayer可以响应鼠标事件和鼠标动作事件
		layer.setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK
			|AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}
	public void uninstallUI(JComponent c){
		JLayer<?> layer = (JLayer<?>)c;
		//设置JLayer不响应任何事件
		layer.setLayerEventMask(0);
		super.uninstallUI(c);
	}
	public void paint(Graphics g,JComponent c){
		Graphics2D g2=(Graphics2D)g.create();
		super.paint(g2, c);
		//如果处于激活状态
		if(active){
		 	//定义一个cx,cy位置的点
			Point2D center = new Point2D.Float(cx,cy);
			float radius = 35;
			float[] dist={0.0f,1.0f};
			Color[] colors = {Color.CYAN,new Color(25,35,55)};
			//以center为中心,colors为颜色数组创建环形渐变
			RadialGradientPaint p = new RadialGradientPaint(center,radius,dist,colors);
			g2.setPaint(p);
			//设置渐变效果
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			//绘制矩形
			g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		}
		g2.dispose();
	}
	//处理鼠标事件的方法
	@SuppressWarnings("rawtypes")
	public void processMouseEvent(MouseEvent e,JLayer layer){
		if(e.getID() == MouseEvent.MOUSE_ENTERED) active = true;
		if(e.getID() == MouseEvent.MOUSE_EXITED ) active = false;
		layer.repaint();
	}
	//处理鼠标动作事件
	@SuppressWarnings("rawtypes")
	public void processMouseMotionEvent(MouseEvent e,JLayer layer){
		Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), layer);
		//获取鼠标动作事件发生的坐标
		cx = p.x;
		cy = p.y;
		layer.repaint();
	}
}

@SuppressWarnings("serial")
//提醒用户"程序正在处理中"的JLayer
class WaitingLayerUI extends LayerUI<JComponent>{
	private boolean isRunning;
	private Timer timer;
	//记录转过的角度
	private int angle;
	
	public void paint(Graphics g,JComponent c){
		super.paint(g, c);
		int w = c.getWidth();
		int h = c.getHeight();
		//已经停止运行,直接返回
		if(!isRunning) return;
		Graphics2D g2 = (Graphics2D)g.create();
		Composite urComposite = g2.getComposite();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		//填充矩形
		g2.fillRect(0, 0, w, h);
		g2.setComposite(urComposite);
		//---------下面代码开始绘制旋转中的"齿轮"-------------//
		//计算得到宽高中较小值得1/5
		int s = Math.min(w, h)/5;
		int cx = w/2;
		int cy = h/2;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//设置笔触
		g2.setStroke(new BasicStroke(s/2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setPaint(Color.BLUE);
		//画笔绕被装饰的组件中心转过angle度
		g2.rotate(Math.PI * angle/180, cx, cy);
		//绘制12条线,形成齿轮
		for(int i=0;i<12;++i){
			float scale = (11.0f - (float)i) / 11.0f;
			g2.drawLine(cx+s, cy, cx+s*2, cy);
			g2.rotate(-Math.PI/6, cx, cy);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, scale));
		}
		g2.dispose();
	}
	//控制等待"齿轮开始转动"的方法
	public void start(){
		//如果已经在运行中,直接返回
		if(isRunning) return;
		isRunning = true;
		//每隔0.1秒重绘一次
		timer = new Timer(100,e ->{
			if(isRunning){
				//触发applyPropertyChange()方法,让JLayer重绘
				//在这行代码中,后两个参数没有意义
				firePropertyChange("crazyitFlag",0,1);
				//角度加6
				angle += 6;
				if(angle >= 360) angle = 0;
			}
		});
		timer.start();
	}
	//控制停止等待齿轮转动的方法
	public void stop(){
		isRunning = false;
		//最后通知JLayer重绘一次,清除曾经绘制的图形
		firePropertyChange("crazyitFlag",0,1);
		timer.stop();
	}
	@SuppressWarnings("rawtypes")
	public void applyPropertyChange(PropertyChangeEvent event,JLayer layer){
		//控制JLayer重绘
		if(event.getPropertyName().equals("crazyitFlag"))
			layer.repaint();
	}
}