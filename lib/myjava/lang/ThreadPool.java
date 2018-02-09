package myjava.lang;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* Java5增加了一个Executor工厂类来生产线程池,该工厂类包含如下几个静态方法:
 * newCachedThreadPool():
 * newFixedThreadPool(int nThreads):
 * newSingleThreadExecutor():
 * 上面三个方法返回一个ExecutorService对象,该对象代表一个线程池,它可以执行Runnable或Callable对象所代表的线程体.
 * ExecutorService代表尽快执行线程的线程池(只要线程池中有空闲线程,就立刻执行线程任务).ExecutorService里提供了如下三个方法:
 * Future<?> submit(Runnable task):Future将在run()方法执行结束后返回null,
 * 	但可以调用future对象的isDone(),isCancelled()方法来获取Runnable对象的执行状态.
 * <T>Future<T> submit(Runnable task,T result):Future对象将在run()方法执行结束后返回result.
 * <T>Future<T> submit(Callable<T> task):返回的future代表线程的返回值.
 * 
 * 
 * newScheduledThreadpool(int corePoolSize):
 * newSingleThreadScheduledExecutor():
 * 上面两个方法返回一个ScheduledExecutorService,它是ExecutorService的子类,它提供了如下四个方法:
 * ScheduledFuture<V> scheduled(Callable<V> callable,long delay,TimeUnit unit):
 * 	指定callable任务将在delay延迟后执行
 * ScheduledFuture<V> scheduled(Runnable command,long delay,TimeUnit unit):
 * 	指定callable任务将在delay延迟后执行
 * ScheduledFuture<V> scheduleAtFixedRate(Runnable cmd,long initDelay,long period,TimeUnit unit):
 * 	指定任务将在inidDelay+period,inidDelay+period*2,inidDelay+period*3...处重复执行
 * ScheduledFuture<V> scheduleWithFixedDelay(Runnable cmd,long initDelay,long delay,TimeUnit unit):
 * 	创建并在initDelay后开始执行cmd任务,以后每隔delay重复执行该任务.一旦发生异常,就会取消后序执行,否则,只能通过程序显示取消或终止
 * 
 * newWorkStealingPoll():使用计算机的CPU个数来设置并行级别.
 * newWorkStealingPool(int parallelism):创建持有足够的线程的线程池来支持给定的并行级别.该方法还会使用多个队列来减少竞争
 */

public class ThreadPool {
	static void test1(){
		//创建一个具有固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(3);
		Runnable task = () ->{
			for(int i=0;i<10;++i)
				System.out.println(Thread.currentThread().getName()+": " + i);
		};
		//向线程池中提交5个线程
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
		
		//关闭线程池
		//使用shotdown关闭线程池后,已提交的线程会执行完,但不在接受任务
		//使用shotdownNow()方法关闭线程时,该方法试图停止所有正在执行的活动任务,暂停处理正在等待的任务,并返回等待执行的任务列表
		pool.shutdown();
	//	pool.shutdownNow();
	}
	
	static void test2(){
		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleWithFixedDelay(()->{
			System.out.println(LocalDateTime.now());
			//延迟5秒开始,每隔5秒执行一次线程体.
		}, 5, 5, TimeUnit.SECONDS);
	}
	public static void main(String[] args){
	//	test1();
		test2();
	}
}
