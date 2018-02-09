package myjava.net.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT = 30000;
	//使用MyMap对象来保存每个客户名字和对应的输出流之间的对应关系
	public static MyMap<String,PrintStream> clients=new MyMap<>();
	public void init(){
		try(
			//建立监听的ServerSocket
			ServerSocket ss = new ServerSocket(SERVER_PORT);
		){
			//采用死循环来不断地接受来自客户端的请求
			while(true){
				//accept()方法将阻塞该线程
				Socket socket = ss.accept();
				//每连接一个客户端,就开启一条线程来处理与之通信
				new ServerThread(socket).start();
			}
			
		}catch(IOException e){
			System.out.println("服务器启动失败,请确认端口:" + SERVER_PORT +"未被占用!");
		}
	}
	
	//main函数,开启服务器
	public static void main(String[] args){
		Server server = new Server();
		server.init();
		System.out.println("服务器正常启动!");
	}
}

class ServerThread extends Thread{
	private Socket socket;
	//该socket对应的输入流
	BufferedReader br = null;
	//该socket对应的输出流
	PrintStream ps = null;
	//定义一个接受Socket的构造器来创建ServerThread线程
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	public void run(){
		try{
			//获取该Socket对应的输入流
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//获取该Socket对应的输出流
			ps = new PrintStream(socket.getOutputStream());
			String line = null;
			while( (line=br.readLine()) != null){
				//如果读到的行以MyProtocal.USER_ROUND开始,并以其结束,则可以确定读到的是用户登录用户名
				if(line.startsWith(MyProtocal.USER_ROUND) &&
					line.endsWith(MyProtocal.USER_ROUND)){
					//获取真实消息
					String userName=getRealMsg(line);
					//如果用户名重复
					if(Server.clients.map.containsKey(userName)){
						System.out.println("用户名[" + userName + "]重复!");
						//向客户端发送用户名重复的消息
						ps.println(MyProtocal.NAME_REP);
					}else{
						System.out.println("用户["+userName+"]登录成功!");
						//向客户端发送登录成功的消息
						ps.println(MyProtocal.LOGIN_SUCCESS);
						//将该用户加入到用户列表中去
						Server.clients.put(userName, ps);
						System.out.println(userName+"已登录");
					}
				}
				//如果读取到的行以MyProtocal.PRIVATE_ROUND开始和结束
				//则可以判断该消息是私聊信息,私聊消息只向特定的输出流发送
				else if(line.startsWith(MyProtocal.PRIVATE_ROUND) &&
						line.endsWith(MyProtocal.PRIVATE_ROUND)){
					//截取真实消息
					String userAndMsg = getRealMsg(line);
					//以SPLIT_SIGN分割字符串,前半是私聊用户名,后半是聊天信息
					//msgs[0]里面是user,msgs[1]里面是聊天内容
					String[] msgs = userAndMsg.split(MyProtocal.SPLIT_SIGN);
					//获取私聊用用户对应的输出流并发送私聊信息
					Server.clients.map.get(msgs[0])
					.println(Server.clients.getKeyByValue(ps)+":"+msgs[1]);
				}
				//公聊的消息要向每个socket发送一遍
				else{
					//截取真实消息
					String msg =getRealMsg(line);
						//遍历clients中的每个输出流
						for(PrintStream clientPs:Server.clients.valueSet()){
							clientPs.println(Server.clients.getKeyByValue(ps)+":" +msg);
						}
					}
				}
			}
			//捕获到异常后,表明该socket对应的客户端已经出现了问题
			//所以程序将其对应的输出流从Map中删除
			catch(IOException e){
				Server.clients.removeByValue(ps);
				System.out.println(Server.clients.map.size());
				//关闭网络,IO资源
				try{
					if(br!=null){
						br.close();
					}
					if(ps!=null){
						ps.close();
					}
					if(socket!=null){
						socket.close();
					}
				}catch(IOException ee){
					System.out.println(ee);
				}
			}
	}
	
	//将读到的内容去掉前面的协议字符,恢复成真实数据
	private String getRealMsg(String line){
		return line.substring(MyProtocal.PROTOCAL_LEN, line.length()-MyProtocal.PROTOCAL_LEN);
	}
}
