package myjava.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static final int PORT = 30000;
	//����ÿ�����ݱ��Ĵ�С���Ϊ4KB
	private static final int DATA_LEN=4096;
	//��������������ݵ��ֽ�����
	byte[] inBuff = new byte[DATA_LEN];
	//��ָ���ֽ����ݴ���׼���������ݵ�DatagramPacket����
	private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
	//����һ�����ڷ��͵�DatagramPacket����
	private DatagramPacket outPacket;
	//�����ַ�������,�������˷��͸��ַ������Ԫ��
	String[] books={
		"���java����","������Java EE��ҵӦ��ʵս","���android����","���Ajax����"
	};
	public void init()throws Exception{
		try(
			//����DatagrampSocket����
			DatagramSocket socket = new DatagramSocket(PORT);
		){
			System.out.println("DUP������������!");
			//����ѭ����������
			for(int i=0;i<1000;++i){
				//��ȡsocket�е�����,���������ݷ���inPacket�з�װ��������
				socket.receive(inPacket);
				//�ж�inPacket.getData()��inBuff�Ƿ���ͬһ������
				System.out.println("inBuff==inPacket.getData():"+(inBuff==inPacket.getData()));
				//�����ܵ�������ת��Ϊ�ַ��������
				System.out.println(new String(inBuff,0,inPacket.getLength()));
				//���ַ�������ȡ��һ��Ԫ����Ϊ��������
				byte[] sendData = books[i%4].getBytes();
				//��ָ�����ֽ�������Ϊ��������,�Ըս��ܵ���DatagramPacket
				//��ԴSocketAddress��ΪĿ��SocketAddress����DatagramPacket
				outPacket = new DatagramPacket(sendData,sendData.length,
						inPacket.getSocketAddress());
				//��������
				socket.send(outPacket);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		new UDPServer().init();
	}
}
