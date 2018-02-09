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
			//创建用于发送,接收数据的MulitcastSocket对象
			//由于该MulitcastSocket对象需要接收数据,所以有指定端口
			socket = new MulticastSocket(BROADCAST_PORT+1);
			broadcastAddress=InetAddress.getByName(BROADCAST_IP);
			//将该socket加入指定的多点广播地址
			socket.joinGroup(broadcastAddress);
			//设置本MulitcastSocket发送的数据报会被送回到自身
			socket.setLoopbackMode(false);
			//初始化发送用的DatagramPacket
			outPacket = new DatagramPacket(new byte[0],0,broadcastAddress,BROADCAST_PORT);
			//启动以本实例的run()方法作为线程执行体的线程
			new Thread(this).start();
			
			//不断地读取键盘输入
			while(scan.hasNextLine()){
				//将键盘输入的字符串转换为字节数组
				byte[] buff = scan.nextLine().getBytes();
				//设置发送用的DatagramPacket里的字节数据
				outPacket.setData(buff);
				//发送数据报
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
				System.out.println("聊天信息:" +new String(inBuff,0,inPacket.getLength()));
			}
		}catch(IOException ioe){
			ioe.printStackTrace();
			System.out.println("接受线程出现IO异常,已退出!");
			try{
				if(socket!=null){
					//让该socket离开该多点IP广播地址
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
