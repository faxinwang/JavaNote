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
	//��ͼ���Ĵ�С
	final int AREA_WIDTH = 500;
	final int AREA_HEIGHT = 400;
	//��һ������λ��
	int preX = -1;
	int preY = -1;
	//����һ���Ҽ��˵��������û�����ɫ
	PopupMenu pop = new PopupMenu();
	MenuItem redItem = new MenuItem("��ɫ");
	MenuItem greenItem = new MenuItem("��ɫ");
	MenuItem blueItem = new MenuItem("��ɫ");
	MenuItem clear = new MenuItem("����");
	//����һ��BufferedImage����
	BufferedImage image = new BufferedImage(AREA_WIDTH,AREA_HEIGHT,BufferedImage.TYPE_INT_RGB);
	//��ȡimage�����Graphics
	Graphics g = image.getGraphics();
	Frame f = new Frame("���ֻ����");
	DrawCanvas drawArea = new DrawCanvas();
	//���ڱ��滭����ɫ
	Color Red = new Color(255,0,0);
	Color Green = new Color(0,255,0);
	Color Blue = new Color(0,0,255);
	Color White = new Color(255,255,255);
	Color foreColor = Red;
	
	void init(){
		//�����Ҽ��˵����¼�������
		ActionListener menuListener = e->{
			if(e.getActionCommand().equals("��ɫ")){
				foreColor = Green;
			}
			else if(e.getActionCommand().equals("��ɫ")){
				foreColor = Red;
			}else if(e.getActionCommand().equals("��ɫ")){
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
		//���˵�����ӵ��Ҽ��˵���
		pop.add(blueItem);
		pop.add(greenItem);
		pop.add(redItem);
		pop.add(clear);
		//���Ҽ��˵���ӵ�drawArea�˵���
		drawArea.add(pop);
		//��image����ı���ɫ���ɰ�ɫ
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		drawArea.setPreferredSize(new Dimension(AREA_WIDTH,AREA_HEIGHT));
		drawArea.addMouseMotionListener(new MouseMotionAdapter(){
			//ʵ����갴�²��϶����¼�����
			public void mouseDragged(MouseEvent e){
//				System.out.println(g.getColor());
				if(preX>0 && preY>0){
					//���õ�ǰ��ɫ
					g.setColor(foreColor);
					//������һ������϶��¼��㵽��������϶��¼�����߶�
					g.drawLine(preX, preY, e.getX(),e.getY());
				}
				preX = e.getX();
				preY = e.getY();
				drawArea.repaint();
			}
		});
		drawArea.addMouseListener(new MouseAdapter(){
			//ʵ������ɿ�ʱ���¼�����
			public void mouseReleased(MouseEvent e){
				//�����Ҽ��˵�
				if(e.isPopupTrigger()){
					pop.show(drawArea, e.getX(), e.getY());
				}
				//�ɿ����ʱ����һ������϶��¼���X��Y������Ϊ-1
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
			//��image���Ƶ��������
			g.drawImage(image, 0, 0, null);
		}
	}
	
	public static void main(String[] args){
		new HandDraw().init();
	}
}
