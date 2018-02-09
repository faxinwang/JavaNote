package myjava.lang;

import static java.lang.System.out;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* �����̵߳����ַ���:
 * 1.�̳�Thread�ಢ��д��run()����.�÷����޷���ֵ,�����׳��쳣.
 * 2.����һ��ʵ��Runnable�ӿ�(����ʽ�ӿ�)���ಢʵ�ָýӿڵ�run()����,�������ʵ����ΪThread���캯���Ĳ�������Thread����.
 * 	 ���run()����Ҳ�����з���ֵ,�����׳��쳣
 * 3.����һ��ʵ�� Callable�ӿ�(����ʽ�ӿ�)���ಢ�ǰ��ýӿڵ�call()����,�������ʵ����ΪFutureTask��Ĺ��캯������һ��FutureTask
 *   ����,��FutureTask������ΪThread���캯���Ĳ�������Thread����.call���������з���ֵ,�����׳��쳣.����ֵ����ͨ��FutureTask
 *   �����get()���������.
 * FutureTask��Future�ӿڵ�ʵ����,Future�ӿ��ﶨ��������һЩ����:
 * boolean cancel(boolean mayInterrupIfRunning):��ͼȡ����Future�������Callable����
 * V get():����Callable������call()�����ķ���ֵ,���ø÷��������³�������,����ȵ����߳̽�����Ż�õ�����ֵ
 * V get(long timeout,TimeUnit unit):����Callable�κ���call()�����ķ���ֵ,�ȴ�ָ����ʱ��,����ȴ�ָ��ʱ��֮��û�еõ�
 * 		����ֵ,�����׳�TimeoutException�쳣
 * boolean isCanceled():���Callable�κ����������ǰ��ȡ��,�򷵻�true
 * boolean isDone():���Callable�����Ѿ����,�򷵻�true
 * 
 * �̵߳���������:�½�(New),����(Runnable),����(Running),����(Blocked),����(Dead).
 */
/*�����̵߳����з�ʽ�Ա�:
 * ����Runnable,Callable�ӿڵķ�ʽ�������̵߳���ȱ��:
 * 1.�߳���ֻ��ʵ����Runnable��Callable�ӿ�,�����Լ̳�������
 * 2.�����ַ�ʽ��,����߳̿��Թ���ͬһ��target����(���캯���Ĳ���),���Էǳ��ʺ϶���߳��ദ��ͬһ����Դ�����.�Ӷ����Խ�CPU,��������ݷֿ�,
 *   �γ�������ģ��,�Ϻõ��������������ı��˼��.
 * 3.�����Ǳ���ٸ���,�����Ҫ���ʵ�ǰ�߳�,�����ʹ��Thread.currentThread()����.
 * ���ü̳�Thread��ķ�ʽ�������̵߳���ȱ��:
 * ������,��Ϊ�߳����Ѿ��̳���Thread��,���Բ����ټ̳���������.
 * ������,��д��,�����Ҫ���ʵ�ǰ�߳�,��ֱ��ʹ��this���ɻ�ȡ��ǰ�߳�.
 * ��������ķ���,���һ���Ƽ�����Runnable,Callable�ӿڵķ�ʽ���������߳�.
 */

class MyThread extends Thread{
	//����i���ᱻ��ͬ���̶߳�����
	int i;
	@Override
	public void run(){
		for(i=100;i>0;--i){
			out.println(getName()+" : "+i);
		}
	}
}

class MyRunnable implements Runnable{
	//����i���Ա���ͬ���̶߳�����
	int i=100;
	@Override
	public void run() {
		while(i>0){
			out.println(Thread.currentThread().getName() +" : "+ i--);
		}
	}
}

class MyCallable implements Callable<Integer>{
	//����i���Ա���ͬ���̶߳�����
	//call���������з���ֵ,Ҳ�����׳��쳣
	int i=100;
	@Override
	public Integer call() throws Exception {
		while(i>0){
			out.println(Thread.currentThread().getName() +" : "+ i--);
		}
		return i;
	}
	
}


public class StartThread {
	static void test1(){
		//���������߳�
		new MyThread().start();
		new MyThread().start();
	}

	static void test2(){
		//���������߳�
		MyRunnable runnable = new MyRunnable();
		new Thread(runnable,"Runnable 1").start();
		new Thread(runnable,"Runnable 2").start();
	}
	static void test3()throws Exception{
		//���������߳�
		MyCallable callable = new MyCallable();
		FutureTask<Integer> task = new FutureTask<Integer>(callable);
		new Thread(task,"Callable 1").start();
		//����һ���̲߳���õ�ִ��
		new Thread(task,"Callable 2").start();
		//����task.get()�����ᵼ��ס�̱߳�����.
	//	out.println("task ����ֵ:" + task.get());
	}
	public static void main(String[] args)throws Exception{
	//	test1();
	//	test2();
		test3();
		for(int i=100;i>0;--i){
			out.println(Thread.currentThread().getName()+ " : " + i);
		}
	}
}
