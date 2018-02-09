package myjava.lang;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/* BlockingQueue接口也是Queue的接口,但它不是作为容器使用,而是作为线程同步工具使用,它提供了如下两个支持阻塞的方法
 * put(E e):尝试把元素e放入BlockingQueue中,如果该队列已满,则阻塞该线程
 * take(E e):尝试从BlockingQueue头部取出元素,如果该队列已空,则阻塞该线程
 * 在队列尾部插入元素包括三个方法:add(E e),offer(E e),put(E e),当队列已满时,这三个方法分别会抛出异常,返回false,阻塞队列
 * 在队列头部取出并删除头部元素包括三个方法:remove(),poll(),take(),当该队列为空时,这三个方法分别会抛出异常,返回false,阻塞队列
 * 获取但不删除头部元素有三个方法:element(),peek(),当队列为空时,这两个方法分别抛出异常,返回false;
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
			System.out.println(getName() + "生产者准备生产集合元素");
			try{
			//	Thread.sleep(200);
				//尝试向队列中放入元素,如果队列以满,则线程被阻塞
				bq.put(arr[i%3]);
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			System.out.println(getName()+"生产者生产完:"+i + bq);
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
			System.out.println(getName()+"消费者准消费集合元素!");
			try{
		//		Thread.sleep(200);
				//尝试取出元素,如果嘟列为空,则线程被阻塞
				bq.take();
			}catch(Exception e){System.out.println(e.getMessage());}
			
			System.out.println(getName()+"消费者消费完成:" +bq);
		}
	}
}


public class BlockingQueueTest {

	public static void main(String[] args){
		//创建一个容量为3的BlockingQueue
		BlockingQueue<String> bq = new ArrayBlockingQueue<>(3);
		//启动三个生产者线程
		new Producer(bq).start();
		new Producer(bq).start();
		new Producer(bq).start();
		//启动一个消费者线程
		new Consumer(bq).start();
	}
}
