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
			//��ʼ��������̵�������
			fromKey =new BufferedReader(new InputStreamReader(System.in));
			//���ӵ�������
			socket = new Socket("127.0.0.1",SERVER_PORT);
			//��ȡ��Socket��Ӧ���������������
			ps = new PrintStream(socket.getOutputStream());
			fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String tip="";
			//����ѭ�����ϵص����Ի���Ҫ�������û���
			while(true){
				String userName = JOptionPane.showInputDialog(tip+"�����û���");
				//���û�������û���ǰ������Э���ַ������ٷ���
				ps.println(MyProtocal.USER_ROUND + userName + MyProtocal.USER_ROUND);
				//��ȡ�������˵���Ӧ
				String result = fromServer.readLine();
				//����û����ظ�,��Ҫ����������
				if(result.equals(MyProtocal.NAME_REP)){
					tip = "�û����ظ�!������";
					continue;
				}
				//����������˷��ص�¼�ɹ�,�����ѭ��
				if(result.equals(MyProtocal.LOGIN_SUCCESS)){
					System.out.println("��¼�ɹ�!");
					break;
				}
			}
		}
		//�����쳣,�ر�������Դ,���˳��ó���
		catch(UnknownHostException ue){
			System.out.println("�Ҳ���Զ�̷�����,��ȷ�Ϸ������Ѿ�����!");
			closeRes();
			System.exit(1);
		}catch(IOException ie){
			System.out.println("�����쳣,�����µ�¼!");
			closeRes();
			System.exit(1);
		}
		//�Ը�socket��Ӧ������������ClientThread����ȡ������������Ϣ
		new ClientThread(fromServer).start();
	}
	//����һ���Ӽ�������,�������緢�͵ķ���
	private void readAndSend(){
		try{
			//���ϵض�ȡ��������
			String line =null;
			while( (line=fromKey.readLine()) != null){
				//�����������Ϣ����@��ͷ,����ð��':',����Ϊ�Ƿ��͵�˽����Ϣ
				if(line.startsWith("@") && line.indexOf(":")>0 ){
					line = line.substring(1);
					ps.println(MyProtocal.PRIVATE_ROUND +	//���˽����Ϣ�ı��
							line.split(":")[0] + 			//��@���û���
							MyProtocal.SPLIT_SIGN +			//��ӷָ����
							line.split(":")[1] +			//��Ϣ����
							MyProtocal.PRIVATE_ROUND);		//���˽����Ϣ�ı��
				}else{
					//����Ⱥ����Ϣ
					ps.println(MyProtocal.MSG_ROUND + line + MyProtocal.MSG_ROUND);
				}
			}
		}catch(IOException ioe){
			System.out.println("����ͨ���쳣!�����µ�¼!");
			closeRes();
			System.exit(1);
		}
	}
	//�ر�������Դ,IO����Դ
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
		//�������ϴӷ�������ȡ���ݵ��߳�
		client.init();
		//�����߳��д������������
		client.readAndSend();
	}
}

class ClientThread extends Thread{
	//�ÿͻ����̸߳������������
	private BufferedReader fromServer=null;
	//ʹ��һ�������������������ͻ����߳�
	public ClientThread(BufferedReader br){
		fromServer = br;
	}
	public void run(){
		try{
			String line = null;
			//���ϵش��������ж�ȡ����,������Щ���ݴ�ӡ���
			while( (line=fromServer.readLine()) != null){
				System.out.println(line);
			}
		}catch(IOException ioe){
			System.out.println("����ͨ���쳣!\n" + ioe);
		}finally{
			try{
				if(fromServer!=null) fromServer.close();
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
	}
}