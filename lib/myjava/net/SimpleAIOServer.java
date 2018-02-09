package myjava.net;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/* ʹ��AsynchronousServerSocketChannelֻ��Ҫ����:
 * ����open()��̬��������AsynchronousServerSocketChannel
 * ����AsynchronousServerSocketChannel��bind()����������ָ��IP��ַ,�˿ڼ���
 * ����AsynchronousServerSocketChannel��accept()����������������
 */

//ʹ�����,���ٵĲ�����ʵ��һ������AsynchronousServerSocketChannel�ķ�������
public class SimpleAIOServer {
	static final int PORT = 30000;
	
	public static void main(String[] args)throws Exception {
		int count=0;
		try(
			//1.����AsynchronousServerSocketChannel����
			AsynchronousServerSocketChannel serverChannel = 
					AsynchronousServerSocketChannel.open();
		){
			//2.��ָ����ַ,�˿ڼ���
			serverChannel.bind(new InetSocketAddress("127.0.0.1",PORT));
			while(true){
				//3.����ѭ���������Կͻ��˵�����
				Future<AsynchronousSocketChannel> future = serverChannel.accept();
				//��ȡ������ɺ󷵻ص�AsynchronousSocketChannel,�÷����������߳�
				AsynchronousSocketChannel socketChannel = future.get();
				//��ͻ��˷�������
				socketChannel.write(ByteBuffer.wrap("welcome to the world of AIO!"
						.getBytes("UTF-8"))).get();
				
				//��������֮��͹رշ�����
				if(++count > 3){
					break;
				}
			}
		}
		
	}
}
