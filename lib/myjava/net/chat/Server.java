package myjava.net.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static final int SERVER_PORT = 30000;
	//ʹ��MyMap����������ÿ���ͻ����ֺͶ�Ӧ�������֮��Ķ�Ӧ��ϵ
	public static MyMap<String,PrintStream> clients=new MyMap<>();
	public void init(){
		try(
			//����������ServerSocket
			ServerSocket ss = new ServerSocket(SERVER_PORT);
		){
			//������ѭ�������ϵؽ������Կͻ��˵�����
			while(true){
				//accept()�������������߳�
				Socket socket = ss.accept();
				//ÿ����һ���ͻ���,�Ϳ���һ���߳���������֮ͨ��
				new ServerThread(socket).start();
			}
			
		}catch(IOException e){
			System.out.println("����������ʧ��,��ȷ�϶˿�:" + SERVER_PORT +"δ��ռ��!");
		}
	}
	
	//main����,����������
	public static void main(String[] args){
		Server server = new Server();
		server.init();
		System.out.println("��������������!");
	}
}

class ServerThread extends Thread{
	private Socket socket;
	//��socket��Ӧ��������
	BufferedReader br = null;
	//��socket��Ӧ�������
	PrintStream ps = null;
	//����һ������Socket�Ĺ�����������ServerThread�߳�
	public ServerThread(Socket socket){
		this.socket = socket;
	}
	public void run(){
		try{
			//��ȡ��Socket��Ӧ��������
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//��ȡ��Socket��Ӧ�������
			ps = new PrintStream(socket.getOutputStream());
			String line = null;
			while( (line=br.readLine()) != null){
				//�������������MyProtocal.USER_ROUND��ʼ,���������,�����ȷ�����������û���¼�û���
				if(line.startsWith(MyProtocal.USER_ROUND) &&
					line.endsWith(MyProtocal.USER_ROUND)){
					//��ȡ��ʵ��Ϣ
					String userName=getRealMsg(line);
					//����û����ظ�
					if(Server.clients.map.containsKey(userName)){
						System.out.println("�û���[" + userName + "]�ظ�!");
						//��ͻ��˷����û����ظ�����Ϣ
						ps.println(MyProtocal.NAME_REP);
					}else{
						System.out.println("�û�["+userName+"]��¼�ɹ�!");
						//��ͻ��˷��͵�¼�ɹ�����Ϣ
						ps.println(MyProtocal.LOGIN_SUCCESS);
						//�����û����뵽�û��б���ȥ
						Server.clients.put(userName, ps);
						System.out.println(userName+"�ѵ�¼");
					}
				}
				//�����ȡ��������MyProtocal.PRIVATE_ROUND��ʼ�ͽ���
				//������жϸ���Ϣ��˽����Ϣ,˽����Ϣֻ���ض������������
				else if(line.startsWith(MyProtocal.PRIVATE_ROUND) &&
						line.endsWith(MyProtocal.PRIVATE_ROUND)){
					//��ȡ��ʵ��Ϣ
					String userAndMsg = getRealMsg(line);
					//��SPLIT_SIGN�ָ��ַ���,ǰ����˽���û���,�����������Ϣ
					//msgs[0]������user,msgs[1]��������������
					String[] msgs = userAndMsg.split(MyProtocal.SPLIT_SIGN);
					//��ȡ˽�����û���Ӧ�������������˽����Ϣ
					Server.clients.map.get(msgs[0])
					.println(Server.clients.getKeyByValue(ps)+":"+msgs[1]);
				}
				//���ĵ���ϢҪ��ÿ��socket����һ��
				else{
					//��ȡ��ʵ��Ϣ
					String msg =getRealMsg(line);
						//����clients�е�ÿ�������
						for(PrintStream clientPs:Server.clients.valueSet()){
							clientPs.println(Server.clients.getKeyByValue(ps)+":" +msg);
						}
					}
				}
			}
			//�����쳣��,������socket��Ӧ�Ŀͻ����Ѿ�����������
			//���Գ������Ӧ���������Map��ɾ��
			catch(IOException e){
				Server.clients.removeByValue(ps);
				System.out.println(Server.clients.map.size());
				//�ر�����,IO��Դ
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
	
	//������������ȥ��ǰ���Э���ַ�,�ָ�����ʵ����
	private String getRealMsg(String line){
		return line.substring(MyProtocal.PROTOCAL_LEN, line.length()-MyProtocal.PROTOCAL_LEN);
	}
}
