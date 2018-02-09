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
		//��������JRadioButton,����������ӳ�һ����
		jp.add(jrb=new JRadioButton("���Ϲ���"),true);
		group.add(jrb);
		jp.add(jrb= new JRadioButton("��깺��"));
		group.add(jrb);
		jp.add(jrb=new JRadioButton("ͼ��ݽ���"));
		group.add(jrb);
		//�������JCheckBox���
		jp.add(new JCheckBox("���java����"));
		jp.add(new JCheckBox("���android����"));
		jp.add(new JCheckBox("������Java EE��ҵӦ��ʵս"));
		orderBn = new JButton("ͶƱ");
		jp.add(orderBn);
		//����Ҽ��˵������޸ĳ��������
		jp.setComponentPopupMenu(new MyUIManager(jf,null).getJPopupMenu());;
		//����LayerUI����
//		layerUI = new FirstLayerUI();
//		layerUI = new BlurLayerUI();
		layerUI = new SpotlightLayerUI();
//		layerUI = new WaitingLayerUI();
		//ʹ��layerUI��װ��ָ����JPanel���
		layer= new JLayer<JComponent>(jp,layerUI);
		//��װ�κ��JPanel�����ӵ�����
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
					System.out.println("����stopper...");
					//����4���ִ��ָ������,����LayerUI��stop����
					stopper= new Timer(4*1000,ae -> {
						try {
							stop.invoke(layerUI, new Object[]{});
						} catch (Exception e) {	e.printStackTrace();}
					});
					//���ö�ʱ��ֻ����һ��
					stopper.setRepeats(false);
				}
				
				//���stopper��ʱ����ֹͣ,��������
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

//������ɫ��JLayer
@SuppressWarnings("serial")
class FirstLayerUI extends LayerUI<JComponent>{
	public void paint(Graphics g,JComponent c){
		super.paint(g, c);
		Graphics2D g2 = (Graphics2D)g.create();
		//����͸��Ч��
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.35f));
		//���ý��仭����ɫ
		g2.setPaint(new GradientPaint(0,0,Color.RED,0,c.getHeight(),Color.ORANGE));
		//����һ���뱻װ�����������ͬ��С�����
		g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		g2.dispose();
	}
}

//ģ��Ч����JLayer
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
		//ConvolveOp����һ��ģ������,����ԭͼƬ��ÿһ����������Χ��
		//���ؽ��л��,�Ӷ��������ǰ���ص���ɫֵ
		operation = new ConvolveOp(
			new Kernel(3,3,blurKernel),
			ConvolveOp.EDGE_NO_OP,null
		);
	}
	@Override
	public void paint(Graphics g,JComponent c){
		int w = c.getWidth();
		int h = c.getHeight();
		//�����װ�εĴ������Ϊ0,ֱ�ӷ���
		if(w==0||h==0) return;
		//���screenBlurImageû�г�ʼ��,�����ĳߴ粻��
		if(screenBlurImage == null 
			|| screenBlurImage.getWidth() != w
			|| screenBlurImage.getHeight()!= h)
		{
			//���´����µ�BufferedImage
			screenBlurImage = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
		}
		Graphics2D ig2 = screenBlurImage.createGraphics();
		//�ѱ�װ�ε�����Ľ�����Ƶ�����screenBlurImage��
		ig2.setClip(g.getClip());
		super.paint(ig2, c);
		ig2.dispose();
		Graphics2D g2= (Graphics2D)g;
		//��JLayerװ�ε��������ģ������
		g2.drawImage(screenBlurImage, operation, 0, 0);
	}
}

//"̽�յ�"Ч����JLayer
@SuppressWarnings("serial")
class SpotlightLayerUI extends LayerUI<JComponent>{
	private boolean active;
	private int cx,cy;
	@Override
	public void installUI(JComponent c){
		super.installUI(c);
		JLayer<?> layer = (JLayer<?>)c;
		//����JLayer������Ӧ����¼�����궯���¼�
		layer.setLayerEventMask(AWTEvent.MOUSE_EVENT_MASK
			|AWTEvent.MOUSE_MOTION_EVENT_MASK);
	}
	public void uninstallUI(JComponent c){
		JLayer<?> layer = (JLayer<?>)c;
		//����JLayer����Ӧ�κ��¼�
		layer.setLayerEventMask(0);
		super.uninstallUI(c);
	}
	public void paint(Graphics g,JComponent c){
		Graphics2D g2=(Graphics2D)g.create();
		super.paint(g2, c);
		//������ڼ���״̬
		if(active){
		 	//����һ��cx,cyλ�õĵ�
			Point2D center = new Point2D.Float(cx,cy);
			float radius = 35;
			float[] dist={0.0f,1.0f};
			Color[] colors = {Color.CYAN,new Color(25,35,55)};
			//��centerΪ����,colorsΪ��ɫ���鴴�����ν���
			RadialGradientPaint p = new RadialGradientPaint(center,radius,dist,colors);
			g2.setPaint(p);
			//���ý���Ч��
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
			//���ƾ���
			g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		}
		g2.dispose();
	}
	//��������¼��ķ���
	@SuppressWarnings("rawtypes")
	public void processMouseEvent(MouseEvent e,JLayer layer){
		if(e.getID() == MouseEvent.MOUSE_ENTERED) active = true;
		if(e.getID() == MouseEvent.MOUSE_EXITED ) active = false;
		layer.repaint();
	}
	//������궯���¼�
	@SuppressWarnings("rawtypes")
	public void processMouseMotionEvent(MouseEvent e,JLayer layer){
		Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), layer);
		//��ȡ��궯���¼�����������
		cx = p.x;
		cy = p.y;
		layer.repaint();
	}
}

@SuppressWarnings("serial")
//�����û�"�������ڴ�����"��JLayer
class WaitingLayerUI extends LayerUI<JComponent>{
	private boolean isRunning;
	private Timer timer;
	//��¼ת���ĽǶ�
	private int angle;
	
	public void paint(Graphics g,JComponent c){
		super.paint(g, c);
		int w = c.getWidth();
		int h = c.getHeight();
		//�Ѿ�ֹͣ����,ֱ�ӷ���
		if(!isRunning) return;
		Graphics2D g2 = (Graphics2D)g.create();
		Composite urComposite = g2.getComposite();
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f));
		//������
		g2.fillRect(0, 0, w, h);
		g2.setComposite(urComposite);
		//---------������뿪ʼ������ת�е�"����"-------------//
		//����õ�����н�Сֵ��1/5
		int s = Math.min(w, h)/5;
		int cx = w/2;
		int cy = h/2;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//���ñʴ�
		g2.setStroke(new BasicStroke(s/2,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g2.setPaint(Color.BLUE);
		//�����Ʊ�װ�ε��������ת��angle��
		g2.rotate(Math.PI * angle/180, cx, cy);
		//����12����,�γɳ���
		for(int i=0;i<12;++i){
			float scale = (11.0f - (float)i) / 11.0f;
			g2.drawLine(cx+s, cy, cx+s*2, cy);
			g2.rotate(-Math.PI/6, cx, cy);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, scale));
		}
		g2.dispose();
	}
	//���Ƶȴ�"���ֿ�ʼת��"�ķ���
	public void start(){
		//����Ѿ���������,ֱ�ӷ���
		if(isRunning) return;
		isRunning = true;
		//ÿ��0.1���ػ�һ��
		timer = new Timer(100,e ->{
			if(isRunning){
				//����applyPropertyChange()����,��JLayer�ػ�
				//�����д�����,����������û������
				firePropertyChange("crazyitFlag",0,1);
				//�Ƕȼ�6
				angle += 6;
				if(angle >= 360) angle = 0;
			}
		});
		timer.start();
	}
	//����ֹͣ�ȴ�����ת���ķ���
	public void stop(){
		isRunning = false;
		//���֪ͨJLayer�ػ�һ��,����������Ƶ�ͼ��
		firePropertyChange("crazyitFlag",0,1);
		timer.stop();
	}
	@SuppressWarnings("rawtypes")
	public void applyPropertyChange(PropertyChangeEvent event,JLayer layer){
		//����JLayer�ػ�
		if(event.getPropertyName().equals("crazyitFlag"))
			layer.repaint();
	}
}