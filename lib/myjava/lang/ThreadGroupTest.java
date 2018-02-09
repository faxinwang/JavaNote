package myjava.lang;

/* һ��ĳ���̼߳���ָ���̺߳�,��һֱ���ڸ��߳���,ֱ�����߳�����.
 * Thread���ṩ�����¼������������������߳������ĸ��߳���
 * Thread(ThreadGroup group,Runnable target):ָ���߳���������߳���
 * Thread(ThreadGroup group,Runnable target,String name):ָ���߳�������,�߳�����߳���
 * Thread(TrheadGroup group,String name):ָ���߳������߳�����߳���
 * getThreadGroup():�����߳���������
 * 
 * ThreadGroup(String name):ָ���߳�����
 * ThreadGroup(ThreadGroup parent,String name):ָ�����߳�����߳�����
 * ThreadGroup�ṩ�����¼������÷��������������߳�����������߳�:
 * int activeCount():���ش��߳������̵߳���Ŀ
 * interrupt():�жϴ��߳�����������߳�
 * isDaemon():�жϸ��߳����Ƿ��Ǻ�̨�߳���
 * setDaemon(boolean daemon):�Ѹ��߳������óɺ�̨�߳���
 * setMaxPriority(int pri):�����߳����������ȼ�
 */

class MyThreadB extends Thread{
	public MyThreadB(String name){
		super(name);
	}
	public MyThreadB(ThreadGroup group,String name){
		super(group,name);
	}
	public void run(){
		for(int i=0;i<20;++i){
			System.out.println("["+getName()+"] ��i����:" + i);
		}
	}
}

public class ThreadGroupTest {

	public static void main(String[] args){
		//��ȡ���߳����ڵ��߳���,���������߳�Ĭ�ϵ��߳���
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println("���߳��������:" + mainGroup.getName());
		System.out.println("���߳����Ƿ��Ǻ�̨�߳���:" + mainGroup.isDaemon());
		mainGroup.setMaxPriority(3);
		new MyThreadB("main-1").start();
		ThreadGroup tg = new ThreadGroup("A��");
		//�����߳�����Ϊ��̨�߳���
		tg.setDaemon(true);
		tg.setMaxPriority(10);
		System.out.println("���߳����Ƿ��Ǻ�̨�߳���:"+tg.isDaemon());
		MyThreadB tt = new MyThreadB(tg,"A��");
		tt.start();
		new MyThreadB(tg,"A��").start();;
		new MyThreadB(tg,"A��").start();
	}
}
