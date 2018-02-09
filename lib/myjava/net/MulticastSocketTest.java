package myjava.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Scanner;

public class MulticastSocketTest implements Runnable{
	private static final int BROADCAST_PORT = 30000;
	private static final String BROADCAST_IP="230.0.0.1";
	private static final int DATA_LEN = 4096;
	private MulticastSocket socket =null;
	private InetAddress broadcastAddress = null;
//	private Scanner scan=null;
	byte[] inBuff = new byte[DATA_LEN];
	private DatagramPacket inPacket =new DatagramPacket(inBuff,inBuff.length);
	private DatagramPacket outPacket = null;
	
	public void init()throws Exception{
		try(
			Scanner scan = new Scanner(System.in);
		){
			//�������ڷ���,�������ݵ�MulitcastSocket����
			//���ڸ�MulitcastSocket������Ҫ��������,������ָ���˿�
			socket = new MulticastSocket(BROADCAST_PORT+1);
			broadcastAddress=InetAddress.getByName(BROADCAST_IP);
			//����socket����ָ���Ķ��㲥��ַ
			socket.joinGroup(broadcastAddress);
			//���ñ�MulitcastSocket���͵����ݱ��ᱻ�ͻص�����
			socket.setLoopbackMode(false);
			//��ʼ�������õ�DatagramPacket
			outPacket = new DatagramPacket(new byte[0],0,broadcastAddress,BROADCAST_PORT);
			//�����Ա�ʵ����run()������Ϊ�߳�ִ������߳�
			new Thread(this).start();
			
			//���ϵض�ȡ��������
			while(scan.hasNextLine()){
				//������������ַ���ת��Ϊ�ֽ�����
				byte[] buff = scan.nextLine().getBytes();
				//���÷����õ�DatagramPacket����ֽ�����
				outPacket.setData(buff);
				//�������ݱ�
				socket.send(outPacket);
			}
		}finally{
			if(socket!=null)
				socket.close();
		}
	}

	@Override
	public void run() {
		try{
			while(true){
				socket.receive(inPacket);
				System.out.println("������Ϣ:" +new String(inBuff,0,inPacket.getLength()));
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("�����̳߳���IO�쳣,���˳�!");
			try{
				if(socket!=null){
					//�ø�socket�뿪�ö��IP�㲥��ַ
					socket.leaveGroup(broadcastAddress);
					socket.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		new MulticastSocketTest().init();
	}
}
