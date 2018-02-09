package myjava.awt;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.Timer;

public class PinBall {
	//����ĳߴ�
	final int TABLE_WIDTH = 300;
	final int TABLE_HEIGHT = 400;
	//���ĵĸ߶�
	final int RACKET_Y = 350;
	//���ĵĿ�Ⱥ͸߶�
	final int RACKET_WIDTH = 80;
	final int RACKET_HEIGHT = 10;
	//��Ĵ�С
	final int BALL_SIZE = 16;
	
	Frame f = new Frame("������Ϸ");
	Random rand = new Random();
	//С��������˶��ٶ�
	int speedY = 10;
	//����һ��-0.5~0.5�ı��ʣ����ڿ���С������з���
	double xyRate = rand.nextDouble() - 0.5;
	//С�����������ٶ�
	int speedX = (int)(speedY * xyRate * 2);
	//�����˶��ٶ�
	int RACKET_SPEED = 25;
	//С�������
	int ballX = rand.nextInt(200) + 20;
	int ballY = rand.nextInt(10) + 20;
	//���ĵ�ˮƽλ��
	int racket_X = rand.nextInt(200);
	MyCanvas tableArea = new MyCanvas();
	Timer timer ;
	//��Ϸ������־
	boolean isLose = false;
	//�������
	int count = 0;
	final int PASS = 15;//ÿ��PASS����͸������
	final int grow = 2;//ÿ������grow���ٶ�
	
	void init(){
		//���������������Ѵ�С
		tableArea.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));
		f.add(tableArea);
		//������̼�����
		KeyAdapter keyProcessor = new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_LEFT)//��������
					if(racket_X > 0) racket_X -= RACKET_SPEED;
				if(ke.getKeyCode() == KeyEvent.VK_RIGHT)//��������
					if(racket_X <TABLE_WIDTH -RACKET_WIDTH) racket_X += RACKET_SPEED;
			}
		};
		//Ϊ���ں�tableArea�ֱ���Ӽ�����
		f.addKeyListener(keyProcessor);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		tableArea.addKeyListener(keyProcessor);
		//����ÿ0.1��ִ��һ�ε��¼�������
		ActionListener taskPerformer = evt ->{
			//���С���������ұ߿�,������ٶȷ���
			if(ballX <= 0 || ballX >= TABLE_WIDTH - BALL_SIZE){
				speedX = -speedX;
			}
			//���С�򳬳������ĵĸ߶ȣ��Һ��������ĵķ�Χ֮�ڣ���Ϸ����
			if(ballY > RACKET_Y - BALL_SIZE && 
					(ballX<racket_X || ballX >racket_X + RACKET_WIDTH) ){
				isLose = true;
				timer.stop();
				tableArea.repaint();
			}
			//���С�򵽴�����λ�û������ϱ߿���С�򷴵�,�����ٶȷ���
			else if(ballY <= 0 || 
				(ballY >= RACKET_Y - BALL_SIZE 
				&& ballX>=racket_X 
				&& ballX<=racket_X + RACKET_WIDTH)){
				speedY = -speedY;
				if(++count % PASS == 0){//�������������
					speedX += (int)Math.copySign(grow, speedX);
					speedY += (int)Math.copySign(grow, speedY);
				}
			}
			ballY += speedY;
			ballX += speedX;
			tableArea.repaint();
		};
		//0.05��ִ��һ��
		timer = new Timer(50,taskPerformer);
		timer.start();
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
	}
	
	
	@SuppressWarnings("serial")
	class MyCanvas extends Canvas{
		Color ballColor = new Color(240,240,80);//�����ɫ
		Color racketColor = new Color(80,80,200);//���ĵ���ɫ
		public void paint(Graphics g){
			if(isLose){//��Ϸ����
				g.setColor(new Color(255,0,0));
				g.setFont(new Font("Times",Font.BOLD,25));
				g.drawString("Game Over!", tableArea.getWidth()/3, tableArea.getHeight()/3);
			}else{//��Ϸ����
				g.setColor(ballColor);
				g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
				g.setColor(racketColor);
				g.fillRect(racket_X,RACKET_Y,RACKET_WIDTH,RACKET_HEIGHT);
			}
		}
	}
	
	public static void main(String[] args){
		new PinBall().init();
	}
}
