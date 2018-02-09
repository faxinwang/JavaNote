package myjava.lang;

/* 一旦某个线程加入指定线程后,将一直属于该线程组,直到该线程死亡.
 * Thread类提供了如下几个构造器来设置新线程属于哪个线程组
 * Thread(ThreadGroup group,Runnable target):指定线程所属组和线程体
 * Thread(ThreadGroup group,Runnable target,String name):指定线程所属组,线程体和线程名
 * Thread(TrheadGroup group,String name):指定线程所属线程组合线程名
 * getThreadGroup():返回线程所属的组
 * 
 * ThreadGroup(String name):指定线程组名
 * ThreadGroup(ThreadGroup parent,String name):指定父线程组和线程组名
 * ThreadGroup提供了如下几个常用方法来操作整个线程组里的所有线程:
 * int activeCount():返回次线程组里活动线程的数目
 * interrupt():中断此线程组里的所有线程
 * isDaemon():判断该线程组是否是后台线程组
 * setDaemon(boolean daemon):把该线程组设置成后台线程组
 * setMaxPriority(int pri):设置线程组的最高优先级
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
			System.out.println("["+getName()+"] 的i变量:" + i);
		}
	}
}

public class ThreadGroupTest {

	public static void main(String[] args){
		//获取主线程所在的线程组,这是所有线程默认的线程组
		ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
		System.out.println("主线程组的名字:" + mainGroup.getName());
		System.out.println("主线程组是否是后台线程组:" + mainGroup.isDaemon());
		mainGroup.setMaxPriority(3);
		new MyThreadB("main-1").start();
		ThreadGroup tg = new ThreadGroup("A组");
		//将新线程组设为后台线程组
		tg.setDaemon(true);
		tg.setMaxPriority(10);
		System.out.println("新线程组是否是后台线程组:"+tg.isDaemon());
		MyThreadB tt = new MyThreadB(tg,"A甲");
		tt.start();
		new MyThreadB(tg,"A乙").start();;
		new MyThreadB(tg,"A丙").start();
	}
}
