package myjava.lang;

class A{
	public synchronized void foo(B b){
		System.out.println("当前线程:[" + Thread.currentThread().getName()+"] 进入了A实例的foo()方法 ");
		try{
			Thread.sleep(200);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("当前线程:[" + Thread.currentThread().getName()+"] 企图调用B实例的last()方法");
		b.last();
	}
	public synchronized void last(){
		System.out.println("进入了A类的last()方法");
	}
}

class B{
	public synchronized void bar(A a){
		System.out.println("当前线程:[" + Thread.currentThread().getName()+"] 进入了B实例的bar()方法");
		try{
			Thread.sleep(200);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("当前线程:["+ Thread.currentThread().getName()+"] 企图调用A实例的last()方法");
		a.last();
	}
	public synchronized void last(){
		System.out.println("进入了B类的last()方法");
	}
}

public class DeadLock implements Runnable{
	A a = new A();
	B b = new B();
	public void init(){
		Thread.currentThread().setName("主线程");
		//调用a对象的foo()方法
		a.foo(b);
		System.out.println("进入了主线程之后...");
	}
	
	public void run(){
		Thread.currentThread().setName("副线程");
		//调用b的bar()方法
		b.bar(a);
		System.out.println("进入了副线程之后...");
	}
	
	public static void main(String[] args){
		DeadLock dl = new DeadLock();
		//启动副线程
		new Thread(dl).start();
		
		dl.init();
	}
}
