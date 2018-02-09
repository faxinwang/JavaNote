package myjava.net;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class SimpleAIOClient {
	static final int PORT = 30000;
	
	public static void main(String[] args)throws Exception{
		//用于读取数据的ByteBuffer
		ByteBuffer buff = ByteBuffer.allocate(1024);
		Charset utf8 = Charset.forName("UTF-8");
		try(
			//1.创建AsynchronousSocketChannel对象
			AsynchronousSocketChannel clientChannel = 
				AsynchronousSocketChannel.open();
		){
			//2.连接远程服务器
			clientChannel.connect(new InetSocketAddress("127.0.0.1",PORT)).get(); //4
			buff.clear();
			//3.从channel中读取数据
			clientChannel.read(buff).get();	//5
			buff.flip();
			//将buff中的数据转换为字符串
			String content = utf8.decode(buff).toString();
			System.out.println("服务器信息:" + content);
		}
	}
	
	/* 程序没有用到4,5两个地方的get()方法的返回值,但这两个地方必须调用get()方法! 因为程序在连接远程服务器,
	 * 读取服务器数据时,都没有传入CompletionHandler,因此程序无法通过该监听器在IO操作完成时触发特定的动作,
	 * 程序必须调用Future返回值的get()方法,并等到get()方法完成才能确定异步IO操作已经执行完成.
	 */
}
