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
	//���ڼ������Channel״̬��Selector
	private Selector selector=null;
	static final int PORT =30000;
	//����ʵ�ֱ���,������ַ�������
	private Charset charset = Charset.forName("UTF-8");
	private final String IP ="172.25.1.125";
	public void init()throws IOException{
		selector=Selector.open();
		//ͨ��Open()������һ��δ�󶨵�ServerSocketChannel����
		ServerSocketChannel server = ServerSocketChannel.open();
		InetSocketAddress isa = new InetSocketAddress(IP,PORT);
		//��ServerSocketChannel�󶨵�ָ���˿�
		server.bind(isa);
		//����ServerSocket�Է�������ʽ����
		server.configureBlocking(false);
		//��serverע�ᵽָ����Selector����
		server.register(selector, SelectionKey.OP_ACCEPT);
		
		System.out.println("�������Ѿ�����!");
		System.out.println("���ڼ���"+IP +":"+ PORT);
		
		//��Selector��ע�������Channel��û����Ҫ�����IO����ʱ,select()������������,���ø÷������߳̽�������
		while(selector.select()>0){
			//���δ���selector�ϵ�ÿ����ѡ���SelectionKey
			for(SelectionKey skey :selector.selectedKeys()){
				//��selector�ϵ���ѡ���key����ɾ�����ڴ����sKey
				selector.selectedKeys().remove(skey);
				//���skey��Ӧ��Channel�����ͻ��˵���������
				if(skey.isAcceptable()){
					//����accept������������,�����������˵�Socketchannel
					SocketChannel sc = server.accept();
					//���ò��÷�����ģʽ
					sc.configureBlocking(false);
					//����SocketChannelҲע�ᵽSelector��
					sc.register(selector, SelectionKey.OP_READ);
					//��skey��Ӧ��Channel���ó�׼��������������
					skey.interestOps(SelectionKey.OP_ACCEPT);
					System.out.println("accept...");
				}
				//���skey��Ӧ��Channel��������Ҫ��ȡ
				if(skey.isReadable()){
					//��ȡ��SelectionKey��Ӧ��Channel,��Channel���пɶ�������
					SocketChannel sc = (SocketChannel)skey.channel();
					//����׼��ִ�ж�ȡ���ݵ�ByteBuffer
					ByteBuffer buff = ByteBuffer.allocate(1024);
					String content = "";
					//��ʼ��ȡ����
					try{
						while(sc.read(buff) > 0){
							buff.flip();
							content += charset.decode(buff);
						}
						//��ӡ�Ӹ�skey��Ӧ��Channel���ȡ��������
						System.out.println("��ȡ������:" +content);
						//��skey��Ӧ��Channel���ó�׼����һ�ζ�ȡ
						skey.interestOps(SelectionKey.OP_READ);
					}
					//�������skey��Ӧ��Channel�������쳣,��������Channel��Ӧ��Client����������
					//����Ҫ��Selector��ȡ����skey��ע��
					catch(IOException ioe){
						//��Selector��ɾ��ָ����SelectionKey
						skey.cancel();
						if(skey.channel()!=null){
							skey.channel().close();
						}
					}
					System.out.println("read...");
					//���content�ĳ��ȴ���0,��������Ϣ��Ϊ��
					if(content.length()>0){
						//������selector��ע�������SelectionKey(Selector��һ��skey��Ӧ��ServerSocketChannel)
						for(SelectionKey key:selector.keys()){
							//��ȡ��key��Ӧ��Channel
							Channel targetChannel = key.channel();
							
							//�����Channel��SocketChannel����,�Ҳ�����Ϣ������Channel
							//�Ͱ���ϢȺ������Channel
							if(targetChannel instanceof SocketChannel 
									&& key.hashCode()!=skey.hashCode()){
								//������������д���Channel��
								SocketChannel dest = (SocketChannel)targetChannel;
								dest.write(charset.encode(content+"123"));
							}
						}
					}
				}
			}
		}
		//�ر�serverChannel
		server.close();
		System.out.println("�������ѹر�!");
	}

	public static void main(String[] args)throws IOException{
		NServer server = new NServer();
		server.init();
	}
}
