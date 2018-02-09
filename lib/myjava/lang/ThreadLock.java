package myjava.lang;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/* �߳������¼�������»��ͷŶ�ͬ��������������:
 * 1.��ǰ�̵߳�ͬ�������,ͬ������ִ�н���.
 * 2.��ǰ�߳���ͬ�������,ͬ������������break,return��ֹ�˸ô����򷽷�.
 * 3.��ǰ�߳���ͬ�������,ͬ�������г�����δ�����Error��Exception,���¸ô����򷽷��쳣����ʱ.
 * 4.��ǰ�߳�ִ��ͬ��������ͬ��������,����ִ����ͬ�������������wait()����,��ǰ�߳���ͣ,���ͷ�ͬ����.
 * 
 * �����漸�������,��ǰ�̲߳����ͷ�ͬ����.
 * 1.�߳�ִ��ͬ�������,ͬ������ʱ,������Thread.sleep(),Thread.yield()��������ͣ��ǰ�߳�ʱ.
 * 2.�߳�ִ��ͬ�������,ͬ������ʱ,�����̵߳����˸��̵߳�suspend()���������̹߳���,���̲߳����ͷ�ͬ����.
 */


class Account{
	//��װ�˻���,�˻����������Ա����
	private String Id;
	private double balance;
	//����������
	private final ReentrantLock lock = new ReentrantLock();
	
	public Account(String id, double bal){
		Id = id;
		balance=bal;
	}
	//�ṩһ���̰߳�ȫ�ķ��������ȡǮ����
	//����÷��������̰߳�ȫ��,������������˻������ָ��������
	public synchronized int draw1(double drawAmount){
		if(balance>=drawAmount){
			System.out.println(Thread.currentThread().getName()
					+"��"+Id+"ȡǮ�ɹ�,�³���Ʊ:" + drawAmount);
			try{
				Thread.sleep(1);
			}catch(InterruptedException e){
				System.out.println(e.getMessage());
			}
			balance -= drawAmount;
			System.out.println(Id+"�˻����Ϊ:" + balance);
			return (int)drawAmount;
		}else{
			System.out.println(Thread.currentThread().getName()
					+"��"+Id+"ȡ��ʧ��!����!");
		}
		return 0;
	}
	
	//ʹ��ͬ������ʵ���̰߳�ȫ
	public int draw2(double drawAmount){
		//����
		lock.lock();
		boolean flag=false;
		try{
			if(balance>=drawAmount){
				flag = true;
				System.out.println(Thread.currentThread().getName()
						+"��"+Id+"ȡǮ�ɹ�,�³���Ʊ:" + drawAmount);
				try{
					Thread.sleep(1);
				}catch(InterruptedException e){
					System.out.println(e.getMessage());
				}
				balance -= drawAmount;
				System.out.println(Id+"�˻����Ϊ:" + balance);
			}else{
				System.out.println(Thread.currentThread().getName()
						+"��"+Id+"ȡ��ʧ��!����!");
			}
		//ʹ��finally����ȷ�������ͷŵ�
		}finally{
			//�޸����,�ͷ���
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
	//����һ���˻���Ա����,�ñ����ɱ����̹߳���
	private Account a = new Account("11111",5000);
	private Account b = new Account("22222",5000);
	private Random rand = new Random();
	
	@Override
	public void run(){
		int r,moneya=0,moneyb=0;
		for(int i=0;i<10;++i){
			//10�ηֱ�������˻�ȡ�����������Ǯ
			r = rand.nextInt(10);
			if(r==0) r=5;
			//a�˻�ʼ����draw1()����ȡǮ
			moneya += a.draw1(r*100);
			
			r = rand.nextInt(10);
			if(r==0) r=5;
			//b�˻�ʼ����draw2()����ȡǮ
			moneyb += b.draw2(r*100);
		}
		try{
			Thread.sleep(10);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------"+Thread.currentThread().getName()+
				"��"+a.getId()+"�˻�ȡ��" + moneya +"Ԫ," + 
				"��"+b.getId()+"�˻�ȡ��" + moneyb +"Ԫ--------");
		
	}
	
	//���̵߳��ø÷���ȡǮ
	public  void drawMoney(){
		int r,moneya=0,moneyb=0;
		for(int i=0;i<10;++i){
			//10�ηֱ�������˻�ȡ�����������Ǯ
			r = rand.nextInt(10);
			if(r==0) r=5;
			//a�˻�ʼ����draw1()����ȡǮ
			moneya += a.draw1(r*100);
			
			r = rand.nextInt(10);
			if(r==0) r=5;
			//b�˻�ʼ����draw2()����ȡǮ
			moneyb += b.draw2(r*100);
		}
		try{
			Thread.sleep(10);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		System.out.println("------------"+Thread.currentThread().getName()+
				"��"+a.getId()+"�˻�ȡ��" + moneya +"Ԫ," +
				"��"+b.getId()+"�˻�ȡ��" + moneyb +"Ԫ--------");
	}
	public static void main(String[] args){
		Thread.currentThread().setName("��");
		ThreadLock tl = new ThreadLock();
		Thread thread = new Thread(tl,"��");
		thread.start();//���߳̿��Կ�ʼȡǮ
		tl.drawMoney();//���߳̿�ʼȡǮ
		
		System.out.println(tl.a.getId()+"�˻�ʣ��:" + tl.a.getBalance());
		System.out.println(tl.b.getId()+"�˻�ʣ��:" + tl.b.getBalance());
	}
}
