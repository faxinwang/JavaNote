package myjava.lang;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/* 线程在如下几种情况下会释放对同步监视器的锁定:
 * 1.当前线程的同步代码块,同步方法执行结束.
 * 2.当前线程在同步代码块,同步方法中遇到break,return终止了该代码块或方法.
 * 3.当前线程在同步代码块,同步方法中出现了未处理的Error或Exception,导致该代码块或方法异常结束时.
 * 4.当前线程执行同步代码块或同步方法是,程序执行了同步监视器对象的wait()方法,则当前线程暂停,并释放同步锁.
 * 
 * 在下面几种情况下,当前线程不会释放同步锁.
 * 1.线程执行同步代码块,同步方法时,调用了Thread.sleep(),Thread.yield()方法来暂停当前线程时.
 * 2.线程执行同步代码块,同步方法时,其他线程调用了该线程的suspend()方法将该线程挂起,该线程不会释放同步锁.
 */


class Account{
	//封装账户号,账户余额两个成员变量
	private String Id;
	private double balance;
	//定义锁对象
	private final ReentrantLock lock = new ReentrantLock();
	
	public Account(String id, double bal){
		Id = id;
		balance=bal;
	}
	//提供一个线程安全的方法来完成取钱操作
	//如果该方法不是线程安全的,将会出现银行账户余额出现负数的情况
	public synchronized int draw1(double drawAmount){
		if(balance>=drawAmount){
			System.out.println(Thread.currentThread().getName()
					+"从"+Id+"取钱成功,吐出钞票:" + drawAmount);
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
			balance -= drawAmount;
			System.out.println(Id+"账户余额为:" + balance);
			return (int)drawAmount;
		}else{
			System.out.println(Thread.currentThread().getName()
					+"从"+Id+"取款失败!余额不足!");
		}
		return 0;
	}
	
	//使用同步锁来实现线程安全
	public int draw2(double drawAmount){
		//加锁
		lock.lock();
		boolean flag=false;
		try{
			if(balance>=drawAmount){
				flag = true;
				System.out.println(Thread.currentThread().getName()
						+"从"+Id+"取钱成功,吐出钞票:" + drawAmount);
				try{
					Thread.sleep(1);
				}catch(InterruptedException e){
					System.out.println(e.getMessage());
				}
				balance -= drawAmount;
				System.out.println(Id+"账户余额为:" + balance);
			}else{
				System.out.println(Thread.currentThread().getName()
						+"从"+Id+"取款失败!余额不足!");
			}
		//使用finally块来确保锁被释放掉
		}finally{
			//修改完成,释放锁
			lock.unlock();
		}
		return flag?(int)drawAmount:0;
	}
	
	public double getBalance(){
		return balance;
	}
	public String getId(){
		return Id;
	}
}


public class ThreadLock implements Runnable{
	//定义一个账户成员变量,该变量可被子线程共享
	private Account a = new Account("11111",5000);
	private Account b = new Account("22222",5000);
	private Random rand = new Random();
	
	@Override
	public void run(){
		int r,moneya=0,moneyb=0;
		for(int i=0;i<10;++i){
			//10次分别从两个账户取出随机数量的钱
			r = rand.nextInt(10);
			if(r==0) r=5;
			//a账户始终用draw1()方法取钱
			moneya += a.draw1(r*100);
			
			r = rand.nextInt(10);
			if(r==0) r=5;
			//b账户始终用draw2()方法取钱
			moneyb += b.draw2(r*100);
		}
		try{
			Thread.sleep(10);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------"+Thread.currentThread().getName()+
				"从"+a.getId()+"账户取了" + moneya +"元," + 
				"从"+b.getId()+"账户取了" + moneyb +"元--------");
		
	}
	
	//主线程调用该方法取钱
	public  void drawMoney(){
		int r,moneya=0,moneyb=0;
		for(int i=0;i<10;++i){
			//10次分别从两个账户取出随机数量的钱
			r = rand.nextInt(10);
			if(r==0) r=5;
			//a账户始终用draw1()方法取钱
			moneya += a.draw1(r*100);
			
			r = rand.nextInt(10);
			if(r==0) r=5;
			//b账户始终用draw2()方法取钱
			moneyb += b.draw2(r*100);
		}
		try{
			Thread.sleep(10);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------"+Thread.currentThread().getName()+
				"从"+a.getId()+"账户取了" + moneya +"元," +
				"从"+b.getId()+"账户取了" + moneyb +"元--------");
	}
	public static void main(String[] args){
		Thread.currentThread().setName("甲");
		ThreadLock tl = new ThreadLock();
		Thread thread = new Thread(tl,"乙");
		thread.start();//乙线程可以开始取钱
		tl.drawMoney();//甲线程开始取钱
		
		System.out.println(tl.a.getId()+"账户剩余:" + tl.a.getBalance());
		System.out.println(tl.b.getId()+"账户剩余:" + tl.b.getBalance());
	}
}
