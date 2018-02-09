package myjava.net.AIOChat;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AServer {
	static final int PORT = 30000;
	static final String IP = "172.25.1.125";
	static List<AsynchronousSocketChannel> channelList
		= new ArrayList<>();
	
	public void startListen()throws Exception{
		//创建一个线程池
		ExecutorService pool = Executors.newFixedThreadPool(20);
		//以指定线程池来创建一个AsynchronousChannelGroup
		AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(pool);
		//以指定ChannelGroup来创建一个AsynchronousServerSocketChannel
		AsynchronousServerSocketChannel serverChannel =
				AsynchronousServerSocketChannel.open(channelGroup);
		//指定监听本机的PORT端口
		serverChannel.bind(new InetSocketAddress(IP,PORT));
		//使用CompletionHandler接收来自客户端的请求
		serverChannel.accept(null, new AcceptHandler(serverChannel));
		System.out.println("服务器已经启动!");
		System.out.println("正在监听" +IP +":" +PORT);
	
	}
	
	class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,Object>{
		private AsynchronousServerSocketChannel serverChannel;
		public AcceptHandler(AsynchronousServerSocketChannel ssc){
			serverChannel = ssc;
		}
		//定义一个ByteBuffer准备读取数据
		ByteBuffer buff = ByteBuffer.allocate(1024);
		
		@Override //当实际IO操作完成时触发该方法
		public void completed(AsynchronousSocketChannel sc, Object attachment) {
			//记录新连接进来的Channel
			AServer.channelList.add(sc);
			//准备接收客户端的下一次连接,还是使用该AcceptHandler处理下一次的连接请求
			serverChannel.accept(null, this);
			
			sc.read(buff, null, new CompletionHandler<Integer,Object>(){

				@Override
				public void completed(Integer result, Object attachment) {
					buff.flip();
					//将buff中的内容转换为字符串
					String content = StandardCharsets.UTF_8.decode(buff).toString();
					//遍历每个Channel,将收到的信息写入各Channel中
					for(AsynchronousSocketChannel c:AServer.channelList){
						try{
							//不发送给自己
							if(c!=sc)
								c.write(ByteBuffer.wrap(content.getBytes("UTF-8"))).get();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					buff.clear();
					//读取下一次数据
					sc.read(buff,null,this);
				}

				@Override //读取失败时会调用此方法
				public void failed(Throwable exc, Object attachment) {
					System.out.println("读取数据失败!" + exc);
					//从该Channel中读取数据失败,就删除该Channel
					AServer.channelList.remove(sc);
				}
				
			});
		}

		@Override //读取失败时会调用此方法
		public void failed(Throwable exc, Object attachment) {
			System.out.println("连接失败!" + exc);
		}
	}
	
	public static void main(String[] args)throws Exception{
		AServer server = new AServer();
		server.startListen();
		//等待客户端的连接请求
		Thread.sleep(5*1000);
	}
}
