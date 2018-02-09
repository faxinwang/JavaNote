package myjava.lang;

import static java.lang.System.*;

public class ThreadTests {
	//启动后台线程,又称为"守护线程"
	//当前台线程全部结束后,后台线程将自动死亡.
	static void startDaemonThread(){
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				//死循环
				for(int i=100;i>0;++i)
					out.println( Thread.currentThread().getName()+" : " + i );
				//休眠一毫秒
				try{
					Thread.sleep(1);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}
		},"Daemon");
		//把该线程设置为守护线程
		//该设置一定要在start()方法之前,否则将会抛出异常
		t.setDaemon(true);
	//	t.setPriority(6);
		t.start();
	}
	
	static void joinThread(){
		Thread t = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i=100;i>0;--i){
					out.println(Thread.currentThread().getName()+" : " + i);
				}
			}
		},"JoinThread");
		
		t.start();
		try {
			//主线将调用该方法,所以该线程将join到主线程中去
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		//启动守护线程
		startDaemonThread();
		
		//主线程中启动另外一个线程
		for(int i=100;i>0;--i){
			if(i==10){
				joinThread();
			}
			out.println(Thread.currentThread().getName()+" : " + i);
		}
	}
}
