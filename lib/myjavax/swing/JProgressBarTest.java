package myjavax.swing;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;
import javax.swing.Timer;

public class JProgressBarTest {
	JFrame jf = new JFrame("测试进度条");
	//创建一条垂直进度条
	JProgressBar bar = new JProgressBar(JProgressBar.VERTICAL);
	JCheckBox indeterminate= new JCheckBox("不确定进度");
	JCheckBox noBorder= new JCheckBox("不绘制边框");
	public void init(){
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(indeterminate);
		box.add(noBorder);
		jf.setLayout(new FlowLayout());
		jf.add(box);
		//把进度条添加到JFrame中
		jf.add(bar);
		bar.setMinimum(0);
		bar.setMaximum(100);
		//设置在进度条中绘制完成百分比
		bar.setStringPainted(true);
		//根据该选择框决定是否绘制进度条的边框
		noBorder.addActionListener(event ->{
			bar.setBorderPainted(!noBorder.isSelected());
		});
		//设置该进度条的进度是否确定
		indeterminate.addActionListener(event ->{
			bar.setIndeterminate(indeterminate.isSelected());
			bar.setStringPainted(!indeterminate.isSelected());
		});
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		//采用循环方式来不断改变进度条的完成进度
		for(int i=0;i<=100;++i){
			//该变进度条的完成进度
			bar.setValue(i);
			try{
				Thread.sleep(100);
			}catch(Exception e){e.printStackTrace();}
		}
	}
	
	public static void main(String[] args){
	//	new JProgressBarTest().init();
//		new JProgressBarTest2().init();
		new ProgressMonitorTest().init();
	}
}

class SimulatedActivity implements Runnable{
	//任务的当前完成量
	private volatile int current;
	private int amount;
	public SimulatedActivity(int amount){
		current = 0;
		this.amount = amount;
	}
	public int getAmount(){
		return amount;
	}
	public int getCurrent(){
		return current;
	}
	public void setCurrent(int c){
		current = c;
	}
	public void run(){
		while(current < amount){
			try{
				Thread.sleep(50);
			}catch(InterruptedException ie){ie.printStackTrace();}
			current += 3;
		}
	}
}

class JProgressBarTest2{
	JFrame jf = new JFrame("测试进度条");
	//创建一个垂直进度条
	JProgressBar bar = new JProgressBar(JProgressBar.VERTICAL);
	JCheckBox indeterminate = new JCheckBox("不确定进度");
	JCheckBox noBorder = new JCheckBox("不绘制边框");
	public void init(){
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(indeterminate);
		box.add(noBorder);
		jf.setLayout(new FlowLayout());
		jf.add(box);
		jf.add(bar);
		bar.setStringPainted(true);
		noBorder.addActionListener(event ->{
			bar.setBorderPainted(!noBorder.isSelected());
		});
		final SimulatedActivity target = new SimulatedActivity(1000);
		//以启动一条线程的方式来执行一个耗时的任务
		new Thread(target).start();
		//设置进度进度条的最大值
		bar.setMinimum(0);
		bar.setMaximum(target.getAmount());
		//监听进度条进度的变化
		bar.getModel().addChangeListener(evt ->{
			if(target.getCurrent() >= target.getAmount()-10){
				target.setCurrent(0);
			}
		});
		Timer timer = new Timer(300,evt-> bar.setValue(target.getCurrent()));
		timer.start();
		indeterminate.addActionListener(event ->{
			bar.setIndeterminate(indeterminate.isSelected());
			bar.setStringPainted(!indeterminate.isSelected());
		});
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
}

class ProgressMonitorTest{
	Timer timer;
	public void init(){
		final SimulatedActivity target = new SimulatedActivity(1000);
		//以启动一条线程的方式来执行一个耗时的任务
		final Thread targetThread = new Thread(target);
		targetThread.start();
		final ProgressMonitor dialog = new ProgressMonitor(null,
				"等待任务完成","已完成:",0,target.getAmount());
		timer = new Timer(300,e -> {
			//以当前的任务完成量设置进度对话框的完成比例
			dialog.setProgress(target.getCurrent());
			//如果用户单击了进度对话框中的"取消"按钮
			if(dialog.isCanceled()){
				timer.stop();
				targetThread.interrupt();
				System.exit(0);
			}
		});
		timer.start();
	}
}
