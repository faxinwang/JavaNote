package myjava.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
	public static final int PORT = 30000;
	//定义每个数据报的大小最大为4KB
	private static final int DATA_LEN=4096;
	//定义接受网络数据的字节数组
	byte[] inBuff = new byte[DATA_LEN];
	//以指定字节数据创建准备接受数据的DatagramPacket对象
	private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
	//定义一个用于发送的DatagramPacket对象
	private DatagramPacket outPacket;
	//定义字符串数组,服务器端发送该字符数组的元素
	String[] books={
		"疯狂java讲义","轻量级Java EE企业应用实战","疯狂android讲义","疯狂Ajax讲义"
	};
	public void init()throws Exception{
		try(
			//创建DatagrampSocket对象
			DatagramSocket socket = new DatagramSocket(PORT);
		){
			System.out.println("DUP服务器已启动!");
			//采用循环接收数据
			for(int i=0;i<1000;++i){
				//读取socket中的数据,读到的数据放入inPacket中封装的数组里
				socket.receive(inPacket);
				//判断inPacket.getData()和inBuff是否是同一个数组
				System.out.println("inBuff==inPacket.getData():"+(inBuff==inPacket.getData()));
				//将接受到的内容转换为字符串后输出
				System.out.println(new String(inBuff,0,inPacket.getLength()));
				//从字符数组中取出一个元素作为发送数据
				byte[] sendData = books[i%4].getBytes();
				//以指定的字节数组作为发送数据,以刚接受到的DatagramPacket
				//的源SocketAddress作为目标SocketAddress创建DatagramPacket
				outPacket = new DatagramPacket(sendData,sendData.length,
						inPacket.getSocketAddress());
				//发送数据
				socket.send(outPacket);
			}
		}
	}
	public static void main(String[] args)throws Exception{
		new UDPServer().init();
	}
}
