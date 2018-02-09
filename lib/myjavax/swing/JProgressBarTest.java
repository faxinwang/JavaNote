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
	JFrame jf = new JFrame("���Խ�����");
	//����һ����ֱ������
	JProgressBar bar = new JProgressBar(JProgressBar.VERTICAL);
	JCheckBox indeterminate= new JCheckBox("��ȷ������");
	JCheckBox noBorder= new JCheckBox("�����Ʊ߿�");
	public void init(){
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(indeterminate);
		box.add(noBorder);
		jf.setLayout(new FlowLayout());
		jf.add(box);
		//�ѽ�������ӵ�JFrame��
		jf.add(bar);
		bar.setMinimum(0);
		bar.setMaximum(100);
		//�����ڽ������л�����ɰٷֱ�
		bar.setStringPainted(true);
		//���ݸ�ѡ�������Ƿ���ƽ������ı߿�
		noBorder.addActionListener(event ->{
			bar.setBorderPainted(!noBorder.isSelected());
		});
		//���øý������Ľ����Ƿ�ȷ��
		indeterminate.addActionListener(event ->{
			bar.setIndeterminate(indeterminate.isSelected());
			bar.setStringPainted(!indeterminate.isSelected());
		});
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
		//����ѭ����ʽ�����ϸı����������ɽ���
		for(int i=0;i<=100;++i){
			//�ñ����������ɽ���
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
	//����ĵ�ǰ�����
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
	JFrame jf = new JFrame("���Խ�����");
	//����һ����ֱ������
	JProgressBar bar = new JProgressBar(JProgressBar.VERTICAL);
	JCheckBox indeterminate = new JCheckBox("��ȷ������");
	JCheckBox noBorder = new JCheckBox("�����Ʊ߿�");
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
		//������һ���̵߳ķ�ʽ��ִ��һ����ʱ������
		new Thread(target).start();
		//���ý��Ƚ����������ֵ
		bar.setMinimum(0);
		bar.setMaximum(target.getAmount());
		//�������������ȵı仯
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
		//������һ���̵߳ķ�ʽ��ִ��һ����ʱ������
		final Thread targetThread = new Thread(target);
		targetThread.start();
		final ProgressMonitor dialog = new ProgressMonitor(null,
				"�ȴ��������","�����:",0,target.getAmount());
		timer = new Timer(300,e -> {
			//�Ե�ǰ��������������ý��ȶԻ������ɱ���
			dialog.setProgress(target.getCurrent());
			//����û������˽��ȶԻ����е�"ȡ��"��ť
			if(dialog.isCanceled()){
				timer.stop();
				targetThread.interrupt();
				System.exit(0);
			}
		});
		timer.start();
	}
}
