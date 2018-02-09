package myjava.net;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class SimpleAIOClient {
	static final int PORT = 30000;
	
	public static void main(String[] args)throws Exception{
		//���ڶ�ȡ���ݵ�ByteBuffer
		ByteBuffer buff = ByteBuffer.allocate(1024);
		Charset utf8 = Charset.forName("UTF-8");
		try(
			//1.����AsynchronousSocketChannel����
			AsynchronousSocketChannel clientChannel = 
				AsynchronousSocketChannel.open();
		){
			//2.����Զ�̷�����
			clientChannel.connect(new InetSocketAddress("127.0.0.1",PORT)).get(); //4
			buff.clear();
			//3.��channel�ж�ȡ����
			clientChannel.read(buff).get();	//5
			buff.flip();
			//��buff�е�����ת��Ϊ�ַ���
			String content = utf8.decode(buff).toString();
			System.out.println("��������Ϣ:" + content);
		}
	}
	
	/* ����û���õ�4,5�����ط���get()�����ķ���ֵ,���������ط��������get()����! ��Ϊ����������Զ�̷�����,
	 * ��ȡ����������ʱ,��û�д���CompletionHandler,��˳����޷�ͨ���ü�������IO�������ʱ�����ض��Ķ���,
	 * ����������Future����ֵ��get()����,���ȵ�get()������ɲ���ȷ���첽IO�����Ѿ�ִ�����.
	 */
}
