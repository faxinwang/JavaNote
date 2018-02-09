package myjava.lang;

import static java.lang.System.out;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* 创建线程的三种方法:
 * 1.继承Thread类并重写其run()方法.该方法无返回值,不能抛出异常.
 * 2.定义一个实现Runnable接口(函数式接口)的类并实现该接口的run()方法,将该类的实例作为Thread构造函数的参数创建Thread对象.
 * 	 这个run()方法也不能有返回值,不能抛出异常
 * 3.定义一个实现 Callable接口(函数式接口)的类并是按该接口的call()方法,将该类的实例作为FutureTask类的构造函数创建一个FutureTask
 *   对象,将FutureTask对象作为Thread构造函数的参数创建Thread对象.call方法可以有返回值,可以抛出异常.返回值可以通过FutureTask
 *   对象的get()方法来获得.
 * FutureTask是Future接口的实现类,Future接口里定义了如下一些方法:
 * boolean cancel(boolean mayInterrupIfRunning):试图取消该Future里关联的Callable任务
 * V get():返回Callable任务里call()方法的返回值,调用该方法将导致程序阻塞,必须等到子线程结束后才会得到返回值
 * V get(long timeout,TimeUnit unit):返回Callable任何里call()方法的返回值,等待指定的时间,如果等待指定时间之后还没有得到
 * 		返回值,将会抛出TimeoutException异常
 * boolean isCanceled():如果Callable任何正常完成以前被取消,则返回true
 * boolean isDone():如果Callable任务已经完成,则返回true
 * 
 * 线程的生命周期:新建(New),就绪(Runnable),运行(Running),阻塞(Blocked),死亡(Dead).
 */
/*创建线程的三中方式对比:
 * 采用Runnable,Callable接口的方式创建多线程的优缺点:
 * 1.线程类只是实现了Runnable或Callable接口,开可以继承其他类
 * 2.在这种方式下,多个线程可以共享同一个target对象(构造函数的参数),所以非常适合多个线程类处理同一份资源的情况.从而可以将CPU,代码和数据分开,
 *   形成清晰的模型,较好的体现了面向对象的编程思想.
 * 3.劣势是编程少复杂,如果需要访问当前线程,则必须使用Thread.currentThread()方法.
 * 采用继承Thread类的方式创建多线程的优缺点:
 * 劣势是,因为线程类已经继承了Thread类,所以不能再继承其他父类.
 * 优势是,编写简单,如果需要访问当前线程,可直接使用this即可获取当前线程.
 * 鉴于上面的分析,因此一般推荐采用Runnable,Callable接口的方式来创建多线程.
 */

class MyThread extends Thread{
	//变量i不会被不同的线程对象共享
	int i;
	@Override
	public void run(){
		for(i=100;i>0;--i){
			out.println(getName()+" : "+i);
		}
	}
}

class MyRunnable implements Runnable{
	//变量i可以被不同的线程对象共享
	int i=100;
	@Override
	public void run() {
		while(i>0){
			out.println(Thread.currentThread().getName() +" : "+ i--);
		}
	}
}

class MyCallable implements Callable<Integer>{
	//变量i可以被不同的线程对象共享
	//call方法可以有返回值,也可以抛出异常
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
		//启动两条线程
		new MyThread().start();
		new MyThread().start();
	}

	static void test2(){
		//启动两条线程
		MyRunnable runnable = new MyRunnable();
		new Thread(runnable,"Runnable 1").start();
		new Thread(runnable,"Runnable 2").start();
	}
	static void test3()throws Exception{
		//启动两条线程
		MyCallable callable = new MyCallable();
		FutureTask<Integer> task = new FutureTask<Integer>(callable);
		new Thread(task,"Callable 1").start();
		//下面一个线程不会得到执行
		new Thread(task,"Callable 2").start();
		//下面task.get()方法会导致住线程被阻塞.
	//	out.println("task 返回值:" + task.get());
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
