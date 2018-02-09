package myjava.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UDPClient {
	//���巢�����ݱ���Ŀ�ĵ�
	public static final int DEST_PORT = 30000;
	public static final String DEST_IP = "172.25.1.125";
	//����ÿ�����ݱ��Ĵ�С���Ϊ4KB
	private static final int DATA_LEN=4096;
	//��������������ݵ��ֽ�����
	byte[] inBuff = new byte[DATA_LEN] ;
	//��ָ���ֽ����ݴ���׼���������ݵ�DatagramPacket����
	private DatagramPacket inPacket = new DatagramPacket(inBuff,inBuff.length);
	//����һ�����ڷ��͵�DatagramPacket����
	private DatagramPacket outPacket;
	private boolean exit_status=true;
	public void init(){
		try(
			//����һ���ͻ���DatagramSocket,ʹ������˿�
			DatagramSocket socket = new DatagramSocket();
			//��������������
			Scanner scanner = new Scanner(System.in);
		){
			//��ʼ�������õ�DatagramPacket,������һ������Ϊ0���ֽ�����
			outPacket = new DatagramPacket(new byte[0],0,
					InetAddress.getByName(DEST_IP),DEST_PORT);
			
			while(scanner.hasNextLine()){
				byte[] buff =scanner.nextLine().getBytes();
				//���÷����õ�DatagramPacket�е��ֽ�����
				outPacket.setData(buff);
				//�������ݱ�
				socket.send(outPacket);
				//��ȡsocket�е�����
				socket.receive(inPacket);
				System.out.println(new String(inBuff,0,inPacket.getLength()));
			}
		}catch(SocketException se){
			System.out.println("����DatagramSocketʧ��:" + se.getMessage());
			exit_status=false;
		}catch(SecurityException se){
			System.out.println("����ǽ��ֹ����:" + se.getMessage());
			exit_status=false;
		}catch(IOException ioe){
			System.out.println("IO�쳣:" + ioe.getMessage());
			exit_status=false;
		}catch(Exception e){
			System.out.println("����δ֪�쳣:" + e.getMessage());
			exit_status=false;
		}finally{
			if(exit_status){
				System.out.println("�����˳�!");
			}else{
				System.out.println("�쳣�˳�!");
			}
		}
	}
	
	public static void main(String[] args){
		new UDPClient().init();
	}
}
