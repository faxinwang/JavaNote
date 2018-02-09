package myjava.lang;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* Java5������һ��Executor�������������̳߳�,�ù�����������¼�����̬����:
 * newCachedThreadPool():
 * newFixedThreadPool(int nThreads):
 * newSingleThreadExecutor():
 * ����������������һ��ExecutorService����,�ö������һ���̳߳�,������ִ��Runnable��Callable������������߳���.
 * ExecutorService������ִ���̵߳��̳߳�(ֻҪ�̳߳����п����߳�,������ִ���߳�����).ExecutorService���ṩ��������������:
 * Future<?> submit(Runnable task):Future����run()����ִ�н����󷵻�null,
 * 	�����Ե���future�����isDone(),isCancelled()��������ȡRunnable�����ִ��״̬.
 * <T>Future<T> submit(Runnable task,T result):Future������run()����ִ�н����󷵻�result.
 * <T>Future<T> submit(Callable<T> task):���ص�future�����̵߳ķ���ֵ.
 * 
 * 
 * newScheduledThreadpool(int corePoolSize):
 * newSingleThreadScheduledExecutor():
 * ����������������һ��ScheduledExecutorService,����ExecutorService������,���ṩ�������ĸ�����:
 * ScheduledFuture<V> scheduled(Callable<V> callable,long delay,TimeUnit unit):
 * 	ָ��callable������delay�ӳٺ�ִ��
 * ScheduledFuture<V> scheduled(Runnable command,long delay,TimeUnit unit):
 * 	ָ��callable������delay�ӳٺ�ִ��
 * ScheduledFuture<V> scheduleAtFixedRate(Runnable cmd,long initDelay,long period,TimeUnit unit):
 * 	ָ��������inidDelay+period,inidDelay+period*2,inidDelay+period*3...���ظ�ִ��
 * ScheduledFuture<V> scheduleWithFixedDelay(Runnable cmd,long initDelay,long delay,TimeUnit unit):
 * 	��������initDelay��ʼִ��cmd����,�Ժ�ÿ��delay�ظ�ִ�и�����.һ�������쳣,�ͻ�ȡ������ִ��,����,ֻ��ͨ��������ʾȡ������ֹ
 * 
 * newWorkStealingPoll():ʹ�ü������CPU���������ò��м���.
 * newWorkStealingPool(int parallelism):���������㹻���̵߳��̳߳���֧�ָ����Ĳ��м���.�÷�������ʹ�ö�����������پ���
 */

public class ThreadPool {
	static void test1(){
		//����һ�����й̶��߳������̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Runnable task = () ->{
			for(int i=0;i<10;++i)
				System.out.println(Thread.currentThread().getName()+": " + i);
		};
		//���̳߳����ύ5���߳�
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		pool.submit(task);
		Future<Integer> f = pool.submit(task,5);
		try {
			System.out.println("f.get():" + f.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		//�ر��̳߳�
		//ʹ��shotdown�ر��̳߳غ�,���ύ���̻߳�ִ����,�����ڽ�������
		//ʹ��shotdownNow()�����ر��߳�ʱ,�÷�����ͼֹͣ��������ִ�еĻ����,��ͣ�������ڵȴ�������,�����صȴ�ִ�е������б�
		pool.shutdown();
	//	pool.shutdownNow();
	}
	
	static void test2(){
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleWithFixedDelay(()->{
			System.out.println(LocalDateTime.now());
			//�ӳ�5�뿪ʼ,ÿ��5��ִ��һ���߳���.
		}, 5, 5, TimeUnit.SECONDS);
	}
	public static void main(String[] args){
	//	test1();
		test2();
	}
}
