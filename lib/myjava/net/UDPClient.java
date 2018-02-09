package myjava.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
	//定义发送数据报的目的地
	public static final int DEST_PORT = 30000;
	public static final String DEST_IP = "172.25.1.125";
	//定义每个数据报的大小最大为4KB
	private static final int DATA_LEN=4096;
	//定义接收网络数据的字节数组
	byte[] inBuff = new byte[DATA_LEN] ;
	//以指定字节数据创建准备接受数据的DatagramPacket对象
	private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
	//定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket;
	private boolean exit_status=true;
	public void init(){
		try(
			//创建一个客户端DatagramSocket,使用随机端口
			DatagramSocket socket = new DatagramSocket();
			//创建键盘输入流
			Scanner scanner = new Scanner(System.in);
		){
			//初始化发送用的DatagramPacket,它包含一个长度为0的字节数组
			outPacket = new DatagramPacket(new byte[0],0,
					InetAddress.getByName(DEST_IP),DEST_PORT);
			
			while(scanner.hasNextLine()){
				byte[] buff =scanner.nextLine().getBytes();
				//设置发送用的DatagramPacket中的字节数据
				outPacket.setData(buff);
				//发送数据报
				socket.send(outPacket);
				//读取socket中的数据
				socket.receive(inPacket);
				System.out.println(new String(inBuff,0,inPacket.getLength()));
			}
		}catch(SocketException se){
			System.out.println("创建DatagramSocket失败:" + se.getMessage());
			exit_status=false;
		}catch(SecurityException se){
			System.out.println("防火墙阻止连接:" + se.getMessage());
			exit_status=false;
		}catch(IOException ioe){
			System.out.println("IO异常:" + ioe.getMessage());
			exit_status=false;
		}catch(Exception e){
			System.out.println("发生未知异常:" + e.getMessage());
			exit_status=false;
		}finally{
			if(exit_status){
				System.out.println("正常退出!");
			}else{
				System.out.println("异常退出!");
			}
		}
	}
	
	public static void main(String[] args){
		new UDPClient().init();
	}
}
