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

/* Graphics��һ������Ļ��ʶ���Graphics����������ϻ��Ʒḻ��ʵļ���ͼ�κ�λͼ:
 * drawLine()����ֱ��
 * drawString()�����ַ���
 * drawRect()���ƾ���
 * drawRoundRect()����Բ�Ǿ���
 * drawOval()������Բ��
 * drawPolygon()���ƶ���α߿�
 * drawArc()����һ��Բ��
 * drawPolyline()��������
 * fillRect()���һ����������
 * fillFoundRect()���һ��Բ�Ǿ�������
 * fillOval()���һ����Բ������
 * fillPolygon()���һ�����������
 * fillArc()���Բ����Բ�������˵㵽������������Χ������
 * drawImage()����λͼ
 * setColor()���û�����ɫ
 * setFount()�������壬����drawString()��Ч
 */
public class SimpleDraw {
	final String RECT_SHAPE = "rect";
	final String OVAL_SHAPE = "oval";
	Frame  f = new Frame("�򵥻�ͼ");
	Button rect = new Button("���ƾ���");
	Button oval = new Button("����Բ��");
	MyCanvas drawArea = new MyCanvas();
	Panel south = new Panel();
	Random rand = new Random();
	//���ڱ�����Ҫ����ʲôͼ�εı���
	String shape = "";
	public void init(){
		rect.addActionListener( e ->{
			//����shapeΪRECT_SHAPE
			shape = RECT_SHAPE;
			for(int i=0;i<rand.nextInt(20);++i)
				drawArea.repaint();
			//repaint()�����undate(Graphice g)
			//undate()�����paint(Graphice g)
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
		//��дCanvas��paint()����,ʵ���ػ�
		public void paint(Graphics g){
			//���������ɫ
			g.setColor(new Color(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255)));
			int x = rand.nextInt(drawArea.getWidth());
			int y = rand.nextInt(drawArea.getWidth());
			int width = 30 + rand.nextInt(70);
			int height = 30 + rand.nextInt(70);
			//Խ�紦��
			if( x > drawArea.getWidth() - width)
				x = drawArea.getWidth() - width;
			if( y > drawArea.getHeight() - height) 
				y = drawArea.getHeight() - height;
			
			if(shape.equals(RECT_SHAPE)){
				//�������һ�����Σ�λ�úʹ�С�������ֵ
				g.fillRect(x, y, width, height);
			}
			if(shape.equals(OVAL_SHAPE)){
				//�������һ����Բ��λ�úʹ�С�������ֵ
				g.fillOval(x, y, width, height);
			}
		}
	}
	
	
	public static void main(String[] args){
		new SimpleDraw().init();
	}
}
