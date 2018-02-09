package myjava.lang;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;


/* java7提供了ForkJoinPool来支持将一个任务拆分成多个"小任务"并行计算,再把多个"小任务"的结果合并成总的计算结果.
 * ForkJoinPool是ExecutorService的实现类,因此是一种特殊的线程池.ForkJoinPool提供了如下两个常用的构造器:
 * ForkJoinPool(int parallelism):创建一个包含parallelism个并行线程的ForkJoinPool.
 * ForkJoinPool():以Runtime.availableProcessors()方法的返回值作为parallelism参数来创建线程池.
 * 创建了ForkJoinPool实例之后,就可以调用它的submit(ForkJoinTask task)或者invoke(ForkJoinTask task)
 * 方法来执行指定任务了.其中ForkJoinTask代表一个可以并行,合并的任务.ForkJoinTask是一个抽象类,它还有两个抽象子类:
 * RecursiveAction和RecursiveTask.其中RecursiveTask代表有返回值的任务,Resursive代表没有返回值的任务.
 */

/* Future<T>
 * 	|----->ForkJoinTask<T>
 * 				|------->ResurviceAction
 * 				|------->ResurviceTask
 * 
 * Executor
 * 	|------>ExecutorService
 * 				|------>AbstractExecutorService
 * 								|------>ForkJoinPool
 * 
 */

//继承RecursiveAction来实现"可分解"的任务
@SuppressWarnings("serial")
class PrintTask extends RecursiveAction{
	private static final int SPAN=50;
	private int start,end;
	
	public PrintTask(int start,int end){
		this.start = start;
		this.end = end;
	}
	@Override
	protected void compute() {
		//when the distance between start and end is less then SPAN, print it
		if(end - start < SPAN){
			System.out.println();
			for(int i=start;i<end;++i){
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
		}else{
			//when the distance beween start and end is larger than SPAN,divide it
			//divide the larger problem into smaller problem
			int mid = (start+end)/2;
			PrintTask left = new PrintTask(start,mid);
			PrintTask right = new PrintTask(mid,end);
			//execute the two smaller task parallelly
			left.fork();
			right.fork();
		}
	}
	
}


//继承RecursiveTask来实现"可分解"的任务
@SuppressWarnings("serial")
class CalTask extends RecursiveTask<Integer>{
	//每个"小任务"最多只累加20个数
	private static final int MAXTASK = 1000;
	private int arr[];
	private int start,end;
	public CalTask(int[] arr,int start,int end){
		this.arr = arr;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Integer compute() {
		int sum = 0;
		//当start与end之间的差小于MAXTASK时,开始进行实际累加
		if(end - start < MAXTASK){
			for(int i=start;i<end;++i){
				sum += arr[i];
			}
			return sum;
		}else{
			//当start与end之间的差大于MAXTASK时,将大任务分解成两个小任务
			int mid = (start + end)/2;
			CalTask left = new CalTask(arr,start,mid);
			CalTask right = new CalTask(arr,mid,end);
			left.fork();
			right.fork();
			//把两个小任务累加的结果合并起来
			return left.join()+right.join();
		}
	}
}


public class ForkJoinPoolTest {
	static void testRecursiveAction(){
		ForkJoinPool pool = new ForkJoinPool();
		//submit the dividable task
		pool.submit(new PrintTask(0,300));
		try {
			pool.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
	
	static void testRecursiveTask(){
		int[] arr = new int[10000];
		Random rand = new Random();
		int total = 0;
		for(int i=0,len=arr.length;i<len;++i){
			int tmp = rand.nextInt(50);
			total += (arr[i]=tmp);
		}
		System.out.println("total=" + total);
		//创建一个通用池
		ForkJoinPool pool = ForkJoinPool.commonPool();
		//提交可分解的CalTask任务
		Future<Integer> future = (Future<Integer>) pool.submit(new CalTask(arr,0,arr.length));
		try {
			System.out.println("数组元素总和为:"+future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}

	public static void main(String[] args){
	//	testRecursiveAction();
		testRecursiveTask();
	}
}
