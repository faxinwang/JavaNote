package myjava.lang;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


@SuppressWarnings("unused")
//定义MyAccount接口
interface MyAccount{
	public void draw(double money);
	public void deposit(double money);
}
//用synchronized实现接口里的两个同步方法
//用wait(),notifyAll()实现线程间通信
class AccountB implements MyAccount{
	private String Id;
	private double balance;
	public AccountB(String id,double balance){
		this.Id = id;
		this.balance = balance;
	}
	public double getBalance(){
		return balance;
	}
	@Override
	public synchronized void draw(double money){
		try{
			//如果取款数大于余额,则取钱方法将阻塞
			if(money > balance){
				wait();
			}else{
				//执行取钱操作
				System.out.println(Thread.currentThread().getName()+"取钱:" + money);
				balance -= money;
				System.out.println(Id + "余额:" + balance);
				//唤醒其他线程
				notifyAll();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	@Override
	public synchronized void deposit(double money){
		try{
			//如果存款数大于5000,存款方法将会阻塞
			if(balance > 2000){
				wait();
			}else{
				System.out.println(Thread.currentThread().getName()+ "存款:" + money);
				balance += money;
				System.out.println(Id + "余额:" + balance);
				notifyAll();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}

//用Lock实现接口里的两个同步方法
//用Condition实现线程间通行
class AccountC implements MyAccount{
	private String Id;
	private double balance;
	private final Lock lock = new ReentrantLock();
	//获取指定lock对象对应的Condition对象
	private final Condition cond = lock.newCondition();
	public AccountC(String id,double balance){
		this.Id = id;
		this.balance = balance;
	}
	@Override
	public void draw(double money) {
		lock.lock();
		try{
			if(money > balance){
			//	wait until cond.sinal()/cond.sinalAll() called
				cond.await();
			//	wait to the given time
			//	cond.awaitUntil(LocalTime.now().plusSeconds(3));
			//	waiting for the given time
			//	cond.await(1, TimeUnit.SECONDS);
			}else{
				System.out.println(Thread.currentThread().getName()+" draw:" + money);
				balance -= money;
				System.out.println(Id + " balance:" + balance) ;
				//to weakup other threads
				cond.signalAll();
			}
		}catch(Exception e ){
			System.out.println(e.getMessage());
		}finally{
			lock.unlock();
		}
	}
	
	@Override
	public void deposit(double money) {
		lock.lock();
		try{
			if(balance > 2000){
				cond.await();
			}else{
				System.out.println(Thread.currentThread().getName()+" deposit:" + money);
				balance += money;
				System.out.println(Id + " balance:" + balance);
				cond.notifyAll();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			lock.unlock();
		}
	}
}

class DrawThread extends Thread{
	private MyAccount account;
	private double drawMoney;
	public DrawThread(String name,MyAccount account,double money){
		super(name);
		this.account = account;
		this.drawMoney = money;
	}
	//set the draws three times to deposits
	public void run(){
		for(int i=0;i<10;++i){
			account.draw(drawMoney);
		}
	}
}

class DepositThread extends Thread{
	private MyAccount account;
	private double saveMoney;
	public DepositThread(String name,MyAccount account,double money){
		super(name);
		this.account = account;
		this.saveMoney = money;
	}
	public void run(){
		for(int i=0;i<5;++i){
			account.deposit(saveMoney);
		}
	}
}
public class ThreadsContact {
	static void testAccountB(){
		AccountB  acct = new AccountB("AccountB",500);
		//draw 1000 yuan every time
		//this thread will draw 10000 yuan in total
		new DrawThread("Draw",acct,1000).start();;
		//save 500 yuan every time
		//the four threads will save 10000 yuan in total
		new DepositThread("Save1",acct,500).start();
		new DepositThread("Save2",acct,500).start();
		new DepositThread("Save3",acct,500).start();
		new DepositThread("Save4",acct,500).start();
	}
	static void testAccountC(){
		AccountC  acct = new AccountC("AccountC",500);
		//draw 1000 yuan every time
		//this thread will draw 10000 yuan in total
		new DrawThread("Draw",acct,1000).start();;
		//save 500 yuan every time
		//the four threads will save 10000 yuan in total
		new DepositThread("Save1",acct,500).start();
		new DepositThread("Save2",acct,500).start();
		new DepositThread("Save3",acct,500).start();
		new DepositThread("Save4",acct,500).start();
	}

	public static void main(String[] args){
	//	testAccountB();
		testAccountC();
	}
}
