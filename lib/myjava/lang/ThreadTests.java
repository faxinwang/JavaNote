package myjava.lang;

import static java.lang.System.*;

public class ThreadTests {
	//������̨�߳�,�ֳ�Ϊ"�ػ��߳�"
	//��ǰ̨�߳�ȫ��������,��̨�߳̽��Զ�����.
	static void startDaemonThread(){
		Thread t = new Thread(new Runnable(){
			@Override
			public void run(){
				//��ѭ��
				for(int i=100;i>0;++i)
					out.println( Thread.currentThread().getName()+" : " + i );
				//����һ����
				try{
					Thread.sleep(1);
				}catch(Exception e){
					out.print(e.getMessage());
				}
			}
		},"Daemon");
		//�Ѹ��߳�����Ϊ�ػ��߳�
		//������һ��Ҫ��start()����֮ǰ,���򽫻��׳��쳣
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
			//���߽����ø÷���,���Ը��߳̽�join�����߳���ȥ
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args){
		//�����ػ��߳�
		startDaemonThread();
		
		//���߳�����������һ���߳�
		for(int i=100;i>0;--i){
			if(i==10){
				joinThread();
			}
			out.println(Thread.currentThread().getName()+" : " + i);
		}
	}
}
