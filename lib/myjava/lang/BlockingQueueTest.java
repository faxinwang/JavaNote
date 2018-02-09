package myjava.lang;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* BlockingQueue�ӿ�Ҳ��Queue�Ľӿ�,����������Ϊ����ʹ��,������Ϊ�߳�ͬ������ʹ��,���ṩ����������֧�������ķ���
 * put(E e):���԰�Ԫ��e����BlockingQueue��,����ö�������,���������߳�
 * take(E e):���Դ�BlockingQueueͷ��ȡ��Ԫ��,����ö����ѿ�,���������߳�
 * �ڶ���β������Ԫ�ذ�����������:add(E e),offer(E e),put(E e),����������ʱ,�����������ֱ���׳��쳣,����false,��������
 * �ڶ���ͷ��ȡ����ɾ��ͷ��Ԫ�ذ�����������:remove(),poll(),take(),���ö���Ϊ��ʱ,�����������ֱ���׳��쳣,����false,��������
 * ��ȡ����ɾ��ͷ��Ԫ������������:element(),peek(),������Ϊ��ʱ,�����������ֱ��׳��쳣,����false;
 */
/* Queue<E>
 * 	|--------->BlockingQueue<E>
 *  |				|-------->>>ArrayBlcokingQueue<E>
 *  |				|-------->>>LinkedBlockingQueue<E>
 *  |				|-------->>>PriorityBlockingQueue<E>
 *  |				|-------->>>SynchronousQueue<E>
 *  |				|-------->>>DelayQueue<E>
 *  |				|
 *  |				|----------->TransferQeueue<E>
 *  |				|				|-------->>>LinkedTransferQueue<E>
 *  |				|
 *  |--->Deque<E>	|
 *  		|-------|--->BlockingDeque<E>
 *  						|-------->>>LinkedBlockingDeque
 */

class Producer extends Thread{
	private BlockingQueue<String> bq;
	public Producer(BlockingQueue<String> bq){
		this.bq=bq;
	}
	public void run(){
		String[] arr={
			"Java","Struts","Spring"
		};
		for(int i=0;i<50;++i){
			System.out.println(getName() + "������׼����������Ԫ��");
			try{
			//	Thread.sleep(200);
				//����������з���Ԫ��,�����������,���̱߳�����
				bq.put(arr[i%3]);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			System.out.println(getName()+"������������:"+i + bq);
		}
	}
}

class Consumer extends Thread{
	private BlockingQueue<String> bq;
	public Consumer(BlockingQueue<String> bq){
		this.bq = bq; 
	}
	public void run(){
		while(true){
			System.out.println(getName()+"������׼���Ѽ���Ԫ��!");
			try{
		//		Thread.sleep(200);
				//����ȡ��Ԫ��,������Ϊ��,���̱߳�����
				bq.take();
			}catch(Exception e){System.out.println(e.getMessage());}
			
			System.out.println(getName()+"�������������:" +bq);
		}
	}
}


public class BlockingQueueTest {

	public static void main(String[] args){
		//����һ������Ϊ3��BlockingQueue
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(3);
		//���������������߳�
		new Producer(bq).start();
		new Producer(bq).start();
		new Producer(bq).start();
		//����һ���������߳�
		new Consumer(bq).start();
	}
}
