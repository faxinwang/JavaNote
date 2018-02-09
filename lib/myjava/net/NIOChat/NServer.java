package myjava.net.NIOChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NServer {
	//用于检测所有Channel状态的Selector
	private Selector selector=null;
	static final int PORT =30000;
	//定义实现编码,解码的字符集对象
	private Charset charset = Charset.forName("UTF-8");
	private final String IP ="172.25.1.125";
	public void init()throws IOException{
		selector=Selector.open();
		//通过Open()方法打开一个未绑定的ServerSocketChannel对象
		ServerSocketChannel server = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress(IP,PORT);
		//将ServerSocketChannel绑定到指定端口
		server.bind(isa);
		//设置ServerSocket以非阻塞方式工作
		server.configureBlocking(false);
		//将server注册到指定的Selector对象
		server.register(selector, SelectionKey.OP_ACCEPT);
		
		System.out.println("服务器已经启动!");
		System.out.println("正在监听"+IP +":"+ PORT);
		
		//当Selector上注册的所有Channel都没有需要处理的IO操作时,select()方法将被阻塞,调用该方法的线程将被阻塞
		while(selector.select()>0){
			//依次处理selector上的每个已选择的SelectionKey
			for(SelectionKey skey :selector.selectedKeys()){
				//从selector上的已选择的key集中删除正在处理的sKey
				selector.selectedKeys().remove(skey);
				//如果skey对应的Channel包含客户端的连接请求
				if(skey.isAcceptable()){
					//调用accept方法接受连接,产生服务器端的Socketchannel
					SocketChannel sc = server.accept();
					//设置采用非阻塞模式
					sc.configureBlocking(false);
					//将该SocketChannel也注册到Selector上
					sc.register(selector, SelectionKey.OP_READ);
					//将skey对应的Channel设置成准备接受其他请求
					skey.interestOps(SelectionKey.OP_ACCEPT);
					System.out.println("accept...");
				}
				//如果skey对应的Channel有数据需要读取
				if(skey.isReadable()){
					//获取该SelectionKey对应的Channel,该Channel中有可读的数据
					SocketChannel sc = (SocketChannel)skey.channel();
					//定义准备执行读取数据的ByteBuffer
					ByteBuffer buff = ByteBuffer.allocate(1024);
					String content = "";
					//开始读取数据
					try{
						while(sc.read(buff) > 0){
							buff.flip();
							content += charset.decode(buff);
						}
						//打印从该skey对应的Channel里读取到的数据
						System.out.println("读取的数据:" +content);
						//将skey对应的Channel设置成准备下一次读取
						skey.interestOps(SelectionKey.OP_READ);
					}
					//如果捕获到skey对应的Channel发生的异常,即表明该Channel对应的Client出现了问题
					//所以要从Selector中取消该skey的注册
					catch(IOException ioe){
						//从Selector中删除指定的SelectionKey
						skey.cancel();
						if(skey.channel()!=null){
							skey.channel().close();
						}
					}
					System.out.println("read...");
					//如果content的长度大于0,即聊天信息不为空
					if(content.length()>0){
						//遍历该selector里注册的所有SelectionKey(Selector有一个skey对应了ServerSocketChannel)
						for(SelectionKey key:selector.keys()){
							//获取该key对应的Channel
							Channel targetChannel = key.channel();
							
							//如果该Channel是SocketChannel对象,且不是消息到来的Channel
							//就把信息群发给该Channel
							if(targetChannel instanceof SocketChannel 
									&& key.hashCode()!=skey.hashCode()){
								//将读到的内容写入该Channel中
								SocketChannel dest = (SocketChannel)targetChannel;
								dest.write(charset.encode(content+"123"));
							}
						}
					}
				}
			}
		}
		//关闭serverChannel
		server.close();
		System.out.println("服务器已关闭!");
	}

	public static void main(String[] args)throws IOException{
		NServer server = new NServer();
		server.init();
	}
}
