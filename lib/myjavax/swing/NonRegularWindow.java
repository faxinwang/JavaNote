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
	//����4������
	JFrame transWin = new JFrame("͸������");
	JFrame gradientWin = new JFrame("����͸������");
	JFrame bgWin = new JFrame ("����ͼƬ����");
	JFrame shapeWin = new JFrame("��Բ����");
	
	public NonRegularWindow(){
		super("�����򴰿ڲ���");
		setLayout(new FlowLayout());
		JButton transBn = new JButton("͸������");
		JButton gradientBn = new JButton("����͸������");
		JButton bgBn = new JButton("����ͼƬ����");
		JButton shapeBn = new JButton("��Բ����");
		//Ϊ4����ť����¼�������
		transBn.addActionListener(this);
		gradientBn.addActionListener(this);
		bgBn.addActionListener(this);
		shapeBn.addActionListener(this);
		add(transBn);
		add(gradientBn);
		add(bgBn);
		add(shapeBn);
		/*******����͸������*********/
		transWin.setLayout(new GridBagLayout());
		transWin.setSize(300, 200);
		transWin.add(new JButton("͸��������ļ򵥰�ť"));
		//����͸����Ϊ0.65f,͸����Ϊ1fʱ��ȫ��͸��
		transWin.setOpacity(0.65f);
		/********���ý���͸���Ĵ���********/
		gradientWin.setBackground(new Color(0,0,0,0));
		gradientWin.setSize(new Dimension(300,200));
		//ʹ��һ��JPanel������Ϊ����͸���ı���
		JPanel panel = new JPanel(){
			@Override
			protected void paintComponent(Graphics g){
				if(g instanceof Graphics2D){
					final int R = 120;
					final int G = 190;
					final int B = 240;
					//����һ�����仭��
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
		//ʹ��JPanel������ΪJFrame��contentPane
		gradientWin.setContentPane(panel);
		panel.setLayout(new GridBagLayout());
		gradientWin.add(new JButton("����͸��������ļ򵥰�ť"));
		
		/*******�����б���ͼƬ�Ĵ���*********/
		bgWin.setBackground(new Color(0,0,0,0));
		bgWin.setSize(new Dimension(300,200));
		//ʹ��һ��JPanel��Ϊ����ͼƬ
		JPanel bgPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				try{
					Image bg = ImageIO.read(new File("./src/img/wfx.jpg"));
					//����һ��ͼƬ��Ϊ����
					g.drawImage(bg, 0, 0, getWidth(),getHeight(),null);
				}catch(IOException e){ e.printStackTrace();}
			}
		};
		//ʹ��bgPanel��ΪJFrame��contentPane
		bgWin.setContentPane(bgPanel);
		bgPanel.setLayout(new GridBagLayout());
		bgWin.add(new JButton("�б���ͼƬ������ļ򵥰�ť"));
		
		/*******������Բ�δ���*********/
		shapeWin.setLayout( new GridBagLayout());
		shapeWin.addComponentListener( new ComponentAdapter(){
			//�����ڴ�С���ı�ʱ,��Բ�Ĵ�СҲ����Ӧ�ĸı�
			@Override
			public void componentResized(ComponentEvent e){
				//���ô�����״
				shapeWin.setShape(new Ellipse2D.Double(0, 0,
						shapeWin.getWidth(), shapeWin.getHeight()));
			}
		});
		shapeWin.setSize(300, 200);
		shapeWin.add(new JButton("��Բ�δ�����ļ򵥰�ť"));
		
		/********���������򴰿�********/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		case "͸������":
			transWin.setVisible(true);
			break;
		case "����͸������":
			gradientWin.setVisible(true);
			break;
		case "����ͼƬ����":
			bgWin.setVisible(true);
			break;
		case "��Բ����":
			shapeWin.setVisible(true);
			break;
		}
	}
	
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		new NonRegularWindow();
	}
}
