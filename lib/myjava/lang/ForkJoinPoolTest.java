package myjava.lang;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;


/* java7�ṩ��ForkJoinPool��֧�ֽ�һ�������ֳɶ��"С����"���м���,�ٰѶ��"С����"�Ľ���ϲ����ܵļ�����.
 * ForkJoinPool��ExecutorService��ʵ����,�����һ��������̳߳�.ForkJoinPool�ṩ�������������õĹ�����:
 * ForkJoinPool(int parallelism):����һ������parallelism�������̵߳�ForkJoinPool.
 * ForkJoinPool():��Runtime.availableProcessors()�����ķ���ֵ��Ϊparallelism�����������̳߳�.
 * ������ForkJoinPoolʵ��֮��,�Ϳ��Ե�������submit(ForkJoinTask task)����invoke(ForkJoinTask task)
 * ������ִ��ָ��������.����ForkJoinTask����һ�����Բ���,�ϲ�������.ForkJoinTask��һ��������,������������������:
 * RecursiveAction��RecursiveTask.����RecursiveTask�����з���ֵ������,Resursive����û�з���ֵ������.
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

//�̳�RecursiveAction��ʵ��"�ɷֽ�"������
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


//�̳�RecursiveTask��ʵ��"�ɷֽ�"������
@SuppressWarnings("serial")
class CalTask extends RecursiveTask<Integer>{
	//ÿ��"С����"���ֻ�ۼ�20����
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
		//��start��end֮��Ĳ�С��MAXTASKʱ,��ʼ����ʵ���ۼ�
		if(end - start < MAXTASK){
			for(int i=start;i<end;++i){
				sum += arr[i];
			}
			return sum;
		}else{
			//��start��end֮��Ĳ����MAXTASKʱ,��������ֽ������С����
			int mid = (start + end)/2;
			CalTask left = new CalTask(arr,start,mid);
			CalTask right = new CalTask(arr,mid,end);
			left.fork();
			right.fork();
			//������С�����ۼӵĽ���ϲ�����
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
		//����һ��ͨ�ó�
		ForkJoinPool pool = ForkJoinPool.commonPool();
		//�ύ�ɷֽ��CalTask����
		Future<Integer> future = (Future<Integer>) pool.submit(new CalTask(arr,0,arr.length));
		try {
			System.out.println("����Ԫ���ܺ�Ϊ:"+future.get());
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
