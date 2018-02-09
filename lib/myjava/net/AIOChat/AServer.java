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
		//����һ���̳߳�
		ExecutorService pool = Executors.newFixedThreadPool(20);
		//��ָ���̳߳�������һ��AsynchronousChannelGroup
		AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(pool);
		//��ָ��ChannelGroup������һ��AsynchronousServerSocketChannel
		AsynchronousServerSocketChannel serverChannel =
				AsynchronousServerSocketChannel.open(channelGroup);
		//ָ������������PORT�˿�
		serverChannel.bind(new InetSocketAddress(IP,PORT));
		//ʹ��CompletionHandler�������Կͻ��˵�����
		serverChannel.accept(null, new AcceptHandler(serverChannel));
		System.out.println("�������Ѿ�����!");
		System.out.println("���ڼ���" +IP +":" +PORT);
	
	}
	
	class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel,Object>{
		private AsynchronousServerSocketChannel serverChannel;
		public AcceptHandler(AsynchronousServerSocketChannel ssc){
			serverChannel = ssc;
		}
		//����һ��ByteBuffer׼����ȡ����
		ByteBuffer buff = ByteBuffer.allocate(1024);
		
		@Override //��ʵ��IO�������ʱ�����÷���
		public void completed(AsynchronousSocketChannel sc, Object attachment) {
			//��¼�����ӽ�����Channel
			AServer.channelList.add(sc);
			//׼�����տͻ��˵���һ������,����ʹ�ø�AcceptHandler������һ�ε���������
			serverChannel.accept(null, this);
			
			sc.read(buff, null, new CompletionHandler<Integer,Object>(){

				@Override
				public void completed(Integer result, Object attachment) {
					buff.flip();
					//��buff�е�����ת��Ϊ�ַ���
					String content = StandardCharsets.UTF_8.decode(buff).toString();
					//����ÿ��Channel,���յ�����Ϣд���Channel��
					for(AsynchronousSocketChannel c:AServer.channelList){
						try{
							//�����͸��Լ�
							if(c!=sc)
								c.write(ByteBuffer.wrap(content.getBytes("UTF-8"))).get();
						}catch(Exception e){
							e.printStackTrace();
						}
					}
					buff.clear();
					//��ȡ��һ������
					sc.read(buff,null,this);
				}

				@Override //��ȡʧ��ʱ����ô˷���
				public void failed(Throwable exc, Object attachment) {
					System.out.println("��ȡ����ʧ��!" + exc);
					//�Ӹ�Channel�ж�ȡ����ʧ��,��ɾ����Channel
					AServer.channelList.remove(sc);
				}
				
			});
		}

		@Override //��ȡʧ��ʱ����ô˷���
		public void failed(Throwable exc, Object attachment) {
			System.out.println("����ʧ��!" + exc);
		}
	}
	
	public static void main(String[] args)throws Exception{
		AServer server = new AServer();
		server.startListen();
		//�ȴ��ͻ��˵���������
		Thread.sleep(5*1000);
	}
}
