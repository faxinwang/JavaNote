package myjava.net.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	private static final int SERVER_PORT = 30000;
	private Socket socket =null;
	private PrintStream ps=null;
	private BufferedReader fromKey=null,fromServer=null;
	
	public void init(){
		try{
			//初始化代表键盘的输入流
			fromKey =new BufferedReader(new InputStreamReader(System.in));
			//连接到服务器
			socket = new Socket("127.0.0.1",SERVER_PORT);
			//获取该Socket对应的输入流和输出流
			ps = new PrintStream(socket.getOutputStream());
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip="";
			//采用循环不断地弹出对话框要求输入用户名
			while(true){
				String userName = JOptionPane.showInputDialog(tip+"输入用户名");
				//在用户输入的用户名前后增加协议字符串后再发送
				ps.println(MyProtocal.USER_ROUND + userName + MyProtocal.USER_ROUND);
				//读取服务器端的向应
				String result = fromServer.readLine();
				//如果用户名重复,则要求重新输入
				if(result.equals(MyProtocal.NAME_REP)){
					tip = "用户名重复!请重新";
					continue;
				}
				//如果服务器端返回登录成功,则结束循环
				if(result.equals(MyProtocal.LOGIN_SUCCESS)){
					System.out.println("登录成功!");
					break;
				}
			}
		}
		//捕获到异常,关闭网络资源,并退出该程序
		catch(UnknownHostException ue){
			System.out.println("找不到远程服务器,请确认服务器已经启动!");
			closeRes();
			System.exit(1);
		}catch(IOException ie){
			System.out.println("网络异常,请重新登录!");
			closeRes();
			System.exit(1);
		}
		//以该socket对应的输入流启动ClientThread来读取来服务器的信息
		new ClientThread(fromServer).start();
	}
	//定义一个从键盘输入,并向网络发送的方法
	private void readAndSend(){
		try{
			//不断地读取键盘输入
			String line =null;
			while( (line=fromKey.readLine()) != null){
				//如果方法的消息中以@开头,且有冒号':',则认为是发送的私聊信息
				if(line.startsWith("@") && line.indexOf(":")>0 ){
					line = line.substring(1);
					ps.println(MyProtocal.PRIVATE_ROUND +	//添加私聊信息的标记
							line.split(":")[0] + 			//被@的用户名
							MyProtocal.SPLIT_SIGN +			//添加分割符号
							line.split(":")[1] +			//消息内容
							MyProtocal.PRIVATE_ROUND);		//添加私聊信息的标记
				}else{
					//发送群聊信息
					ps.println(MyProtocal.MSG_ROUND + line + MyProtocal.MSG_ROUND);
				}
			}
		}catch(IOException ioe){
			System.out.println("网络通信异常!请重新登录!");
			closeRes();
			System.exit(1);
		}
	}
	//关闭网络资源,IO流资源
	private void closeRes(){
		try{
			if(fromKey!=null) fromKey.close();
			if(fromServer!=null) fromServer.close();
			if(ps!=null) ps.close();
			if(socket!=null) socket.close();
		}catch(IOException ioe){
			System.out.println(ioe);
		}
	}
	
	public static void main(String[] args){
		Client client = new Client();
		//开启不断从服务器读取数据的线程
		client.init();
		//在主线程中处理本地输入输出
		client.readAndSend();
	}
}

class ClientThread extends Thread{
	//该客户端线程负责处理的输入流
	private BufferedReader fromServer=null;
	//使用一个网络输入流来创建客户端线程
	public ClientThread(BufferedReader br){
		fromServer = br;
	}
	public void run(){
		try{
			String line = null;
			//不断地从输入流中读取数据,并将这些数据打印输出
			while( (line=fromServer.readLine()) != null){
				System.out.println(line);
			}
		}catch(IOException ioe){
			System.out.println("网络通信异常!\n" + ioe);
		}finally{
			try{
				if(fromServer!=null) fromServer.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
}