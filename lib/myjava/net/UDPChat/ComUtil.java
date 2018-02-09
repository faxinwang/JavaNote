package myjava.net.UDPChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import myjava.net.chat.MyProtocal;

public class ComUtil {
	//���屾����ͨ����ʹ�õ��ַ���
	public static final String CHARSET="UTF-8";
	//ʹ�ó�����Ϊ������Ķ��㲥IP��ַ
	private static final String BROADCAST_IP = "230.1.1.1";
	//ʹ�ó�����Ϊ������Ķ��㲥��Ŀ�ĵض˿�
	//DatagramSocket��ʹ�õĶ˿ں�Ϊ�ö˿ں�+1
	public static final int BROADCAST_PORT = 30000;
	//����ÿ�����ݱ��Ĵ�С���Ϊ4KB
	private static final int DATA_LEN = 4096;
	//���屾�����MulticastSocketʵ��
	private MulticastSocket socket =null;
	//���屾����˽�ĵ�Socketʵ��
	private DatagramSocket singleSocket=null;
	//����㲥��IP��ַ
	private InetAddress broadcastAddress=null;
	//����������������ֽ�����
	byte[] inBuff = new byte[DATA_LEN];
	//��ָ���ֽ����鴴��׼�������ݵ�DatagramPacket����
	private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
	//����һ�����ڷ��͵�DatagramPacket����
	private DatagramPacket outPacket = null;
	//��������������
	private LanTalk lanTalk;
	
	public ComUtil(LanTalk lanTalk)throws Exception{
		this.lanTalk = lanTalk;
		//�������ڽ���,�������ݵ�MulticastSocket����,
		//��Ϊ��MulticastSocket������Ҫ��������,������ָ���˿�
		socket = new MulticastSocket(BROADCAST_PORT);
		//����˽���õ�DatagramSocket����
		singleSocket = new DatagramSocket(BROADCAST_PORT+1);
		broadcastAddress = InetAddress.getByName(BROADCAST_IP);
		//����socket����ָ���Ķ��㲥��ַ
		socket.joinGroup(broadcastAddress);
		//���ñ�MulticastSocket���͵����ݱ��ᱻ���͵�����
		socket.setLoopbackMode(false);
		//��ʼ�������õ�DatagramPacket
		outPacket = new DatagramPacket(new byte[0],0,broadcastAddress,BROADCAST_PORT);
		//����������ȡ�������ݵ��߳�
		new ReadBroad().start();
		Thread.sleep(1);
		new ReadSingle().start();
	}
	//�㲥��Ϣ�Ĺ��߷���
	public void broadcast(String msg){
		try{
			//��msgת��Ϊ�ֽ�����
			byte[] buff = msg.getBytes(CHARSET);
			outPacket.setData(buff);
			socket.send(outPacket);
		}
		catch(IOException ex){
			ex.printStackTrace();
			if(socket!=null){
				socket.close();
			}
			JOptionPane.showMessageDialog(null,
					"������Ϣ�쳣,��ȷ��30000�˿ڿ���,��������������!", 
					"�����쳣!", 
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	//�����򵥶��û�������Ϣ�ķ���
	public void sendSingle(String msg,SocketAddress dest){
		try{
			//��msg�ַ���ת��Ϊ�ֽ�����
			byte[] buff = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(buff,buff.length,dest);
			singleSocket.send(packet);
		}catch(IOException e){
			e.printStackTrace();
			if(singleSocket!=null){
				singleSocket.close();
			}
			JOptionPane.showMessageDialog(null,
					"������Ϣ�쳣,��ȷ��30000�˿ڿ���,��������������!", 
					"�����쳣!", 
					JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
	
	//���ϵش�DatagramSocket�ж�ȡ���ݵ��߳�
	class ReadSingle extends Thread{
		//��������������ݵ��ֽ�����
		byte[] singleBuff = new byte[DATA_LEN];
		private DatagramPacket singlePacket = new DatagramPacket(singleBuff,singleBuff.length);
		public void run(){
			while(true){
				try{
					//��ȡSocket�е�����
					singleSocket.receive(singlePacket);
					//�����������Ϣ
					lanTalk.processMsg(singlePacket,true);
				}catch(IOException ex){
					ex.printStackTrace();
					if(singleSocket != null){
						singleSocket.close();
					}
					JOptionPane.showMessageDialog(null,
							"������Ϣ�쳣,��ȷ��30000�˿ڿ���,��������������!", 
							"�����쳣!", 
							JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}
		}
	}
	
	//������ȡMulticastSocket���߳�
	class ReadBroad extends Thread{
		public void run(){
			while(true){
				try{
					//��ȡsocket�е�����
					socket.receive(inPacket);
					//��ӡ�����Socket�ж�ȡ������
					String msg =new String(inBuff,0,inPacket.getLength(),CHARSET);
					//������������������Ϣ
					if(msg.startsWith(MyProtocal.ONLINE) &&
							msg.endsWith(MyProtocal.ONLINE)){
						String userMsg = msg.substring(2, msg.length()-2);
						String[] userInfo = userMsg.split(MyProtocal.SPLIT_SIGN);
						UserInfo user = new UserInfo(userInfo[1],userInfo[0],
								inPacket.getSocketAddress(),0);
						//�����Ƿ���Ҫ��Ӹ��û��ı���
						boolean addFlag = true;
						ArrayList<Integer> delList = new ArrayList<>();
						//����ϵͳ�����е������û�,��ѭ������ѭ�����
						for(int i=1; i<lanTalk.getUserNum();++i){
							UserInfo curUser = lanTalk.getUser(i);
							//�������û�ʧȥ��ϵ�Ĵ�����1
							curUser.setLost(curUser.getLost()+1);
							//�������Ϣ��ָ���û�����
							if(curUser.equals(user)){
								curUser.setLost(0);
								//���ø��û��������
								addFlag = false;
							}
							if(curUser.getLost()>2){
								delList.add(i);
							}
						}
						
						//ɾ��delList�е�����������Ӧ���û�
						for(int i=0;i<delList.size();++i){
							lanTalk.removeUser(delList.get(i));
						}
						if(addFlag){
							//������û�
							lanTalk.addUser(user);
						}
					}
					//�����������ǹ���
					else{
						//�����������Ϣ
						lanTalk.processMsg(inPacket,false);
					}
				}catch(IOException e){
					e.printStackTrace();
					if(socket != null){
						socket.close();
					}
					JOptionPane.showMessageDialog(null,
							"������Ϣ�쳣,��ȷ��30000�˿ڿ���,��������������!", 
							"�����쳣!", 
							JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}
		}
	}
}
