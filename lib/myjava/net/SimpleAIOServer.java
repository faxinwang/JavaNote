package myjava.net;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.Future;

/* 使用AsynchronousServerSocketChannel只需要三步:
 * 调用open()静态方法创建AsynchronousServerSocketChannel
 * 调用AsynchronousServerSocketChannel的bind()方法让它在指定IP地址,端口监听
 * 调用AsynchronousServerSocketChannel的accept()方法接受连接请求
 */

//使用最简单,最少的步骤来实现一个基于AsynchronousServerSocketChannel的服务器端
public class SimpleAIOServer {
	static final int PORT = 30000;
	
	public static void main(String[] args)throws Exception {
		int count=0;
		try(
			//1.创建AsynchronousServerSocketChannel对象
			AsynchronousServerSocketChannel serverChannel = 
					AsynchronousServerSocketChannel.open();
		){
			//2.在指定地址,端口监听
			serverChannel.bind(new InetSocketAddress("127.0.0.1",PORT));
			while(true){
				//3.采用循环接受来自客户端的连接
				Future<AsynchronousSocketChannel> future = serverChannel.accept();
				//获取连接完成后返回的AsynchronousSocketChannel,该方法将阻塞线程
				AsynchronousSocketChannel socketChannel = future.get();
				//向客户端发送数据
				socketChannel.write(ByteBuffer.wrap("welcome to the world of AIO!"
						.getBytes("UTF-8"))).get();
				
				//三次连接之后就关闭服务器
				if(++count > 3){
					break;
				}
			}
		}
		
	}
}
