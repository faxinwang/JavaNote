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
	//��ͼ���Ĵ�С
	private final int AREA_WIDTH = 500;
	private final int AREA_HEIGHT = 400;
	//��һ������λ��
	private int preX = -1;
	private int preY = -1;
	//����һ���Ҽ��˵��������û�����ɫ
	JPopupMenu pop = new JPopupMenu();
	JMenuItem cc_item = new JMenuItem("ѡ����ɫ");
	JMenuItem clear_item=new JMenuItem("����");
	BufferedImage image  = new BufferedImage(AREA_WIDTH,AREA_HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	//��ȡimage�����Graphics
	Graphics g =image.getGraphics();
	private JFrame jf = new JFrame("���ֻ����");
	private DrawCanvas canvas = new DrawCanvas();
	//���ڱ��滭����ɫ
	private Color foreColor = new Color(255,0,0);
	
	public void init(){
		//���˵�����ϳ��Ҽ��˵�
		pop.add(cc_item);
		pop.add(clear_item);
		canvas.setComponentPopupMenu(pop);
		//��image�������ɫ���ɰ�ɫ
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		canvas.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));
		g.setColor(foreColor);
		jf.add(canvas);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		
		add_actions();
	}
	//������������¼�������
	private void add_actions(){
		//ΪColorChooser�˵�������¼�������
		cc_item.addActionListener(listener -> {
			//�������ֱ�ӵ���һ��ģʽ�Ի���,�������û�ѡ�����ɫ
		//	foreColor = JColorChooser.showDialog(jf, "ѡ�񻭱���ɫ", foreColor);
			//��������򵯳�һ����ģʽ����ɫѡ��Ի���
			//�����Էֱ�Ϊ"ȷ��","ȡ��"��ťָ���¼�������
			final JColorChooser colorPane = new JColorChooser(foreColor);
			JDialog jd = JColorChooser.createDialog(jf, "ѡ�񻭱���ɫ", 
					false, colorPane, lis-> foreColor=colorPane.getColor(), null);
			g.setColor(foreColor);
			jd.setVisible(true);
			System.out.println("��ǰ������ɫ:" + g.getColor());
		});
		clear_item.addActionListener( listener ->{
			g.setColor(new Color(255,255,255));
			g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
			canvas.repaint();
			g.setColor(foreColor);
		});
		//��������ƶ�����
		canvas.addMouseMotionListener(new MouseMotionAdapter(){
			//ʵ�ְ�����겢�϶����¼�������
			@Override
			public void mouseDragged(MouseEvent e){
				if(preX < 0 && preY <0){
					preX = e.getX();
					preY = e.getY();
				}
				//���õ�ǰ��ɫ
			//	g.setColor(foreColor);
				g.drawLine(preX, preY, e.getX(), e.getY());
				preX = e.getX();
				preY = e.getY();
				canvas.repaint();
			}
		});
		
		//��������¼�
		canvas.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				g.setColor(foreColor);
				g.drawLine(0, 0, e.getX(),e.getY());
				canvas.repaint();
			}
			//ʵ������ɿ����¼�������
			@Override
			public void mouseReleased(MouseEvent e){				
				//�ɿ�����ʱ,���ϴ�����϶��¼���X,Y������Ϊ-1
				preX = preY = -1;
				System.out.println("MouseReleased!");
			}
		});
	}
	
	@SuppressWarnings("serial")
	//�û�ͼ����̳�JPanel��
	class DrawCanvas extends JPanel{
		//��дJpanel��paint()����,ʵ���ػ�
		@Override
		public void paint(Graphics g){
			g.drawImage(image, 0, 0, null);
		}
	}
	
	public static void main(String[] args){
		new JColorChooserTest().init();
	}
}
