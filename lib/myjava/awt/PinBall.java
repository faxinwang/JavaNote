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
	//桌面的尺寸
	final int TABLE_WIDTH = 300;
	final int TABLE_HEIGHT = 400;
	//球拍的高度
	final int RACKET_Y = 350;
	//球拍的宽度和高度
	final int RACKET_WIDTH = 80;
	final int RACKET_HEIGHT = 10;
	//求的大小
	final int BALL_SIZE = 16;
	
	Frame f = new Frame("弹球游戏");
	Random rand = new Random();
	//小球纵向的运动速度
	int speedY = 10;
	//返回一个-0.5~0.5的比率，用于控制小球的运行方向
	double xyRate = rand.nextDouble() - 0.5;
	//小球横向的运行速度
	int speedX = (int)(speedY * xyRate * 2);
	//球拍运动速度
	int RACKET_SPEED = 25;
	//小球的坐标
	int ballX = rand.nextInt(200) + 20;
	int ballY = rand.nextInt(10) + 20;
	//球拍的水平位置
	int racket_X = rand.nextInt(200);
	MyCanvas tableArea = new MyCanvas();
	Timer timer ;
	//游戏结束标志
	boolean isLose = false;
	//接球次数
	int count = 0;
	final int PASS = 15;//每接PASS个球就给球加速
	final int grow = 2;//每次增加grow点速度
	
	void init(){
		//设置桌面区域的最佳大小
		tableArea.setPreferredSize(new Dimension(TABLE_WIDTH,TABLE_HEIGHT));
		f.add(tableArea);
		//定义键盘监听器
		KeyAdapter keyProcessor = new KeyAdapter(){
			public void keyPressed(KeyEvent ke){
				if(ke.getKeyCode() == KeyEvent.VK_LEFT)//球拍左移
					if(racket_X > 0) racket_X -= RACKET_SPEED;
				if(ke.getKeyCode() == KeyEvent.VK_RIGHT)//球拍右移
					if(racket_X <TABLE_WIDTH -RACKET_WIDTH) racket_X += RACKET_SPEED;
			}
		};
		//为窗口和tableArea分别添加监听器
		f.addKeyListener(keyProcessor);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		tableArea.addKeyListener(keyProcessor);
		//定义每0.1秒执行一次的事件监听器
		ActionListener taskPerformer = evt ->{
			//如果小球碰到左右边框,则横向速度反向
			if(ballX <= 0 || ballX >= TABLE_WIDTH - BALL_SIZE){
				speedX = -speedX;
			}
			//如果小球超出了球拍的高度，且横向不在球拍的范围之内，游戏结束
			if(ballY > RACKET_Y - BALL_SIZE && 
					(ballX<racket_X || ballX >racket_X + RACKET_WIDTH) ){
				isLose = true;
				timer.stop();
				tableArea.repaint();
			}
			//如果小球到达球拍位置或碰到上边框，则小球反弹,纵向速度反向
			else if(ballY <= 0 || 
				(ballY >= RACKET_Y - BALL_SIZE 
				&& ballX>=racket_X 
				&& ballX<=racket_X + RACKET_WIDTH)){
				speedY = -speedY;
				if(++count % PASS == 0){//升级，给球加速
					speedX += (int)Math.copySign(grow, speedX);
					speedY += (int)Math.copySign(grow, speedY);
				}
			}
			ballY += speedY;
			ballX += speedX;
			tableArea.repaint();
		};
		//0.05秒执行一次
		timer = new Timer(50,taskPerformer);
		timer.start();
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
	}
	
	
	@SuppressWarnings("serial")
	class MyCanvas extends Canvas{
		Color ballColor = new Color(240,240,80);//球的颜色
		Color racketColor = new Color(80,80,200);//球拍的颜色
		public void paint(Graphics g){
			if(isLose){//游戏结束
				g.setColor(new Color(255,0,0));
				g.setFont(new Font("Times",Font.BOLD,25));
				g.drawString("Game Over!", tableArea.getWidth()/3, tableArea.getHeight()/3);
			}else{//游戏继续
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
