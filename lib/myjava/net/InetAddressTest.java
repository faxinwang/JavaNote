package myjava.net;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class InetAddressTest {
	static void testInetAddress(String websit)throws Exception{
		//��������������ȡ��Ӧ��InetAddressʵ��
		InetAddress ip = InetAddress.getByName(websit);
		//�ж��Ƿ�ɴ�
		System.out.println(websit+"�Ƿ�ɴ�:"+ ip.isReachable(2000));
		//��ȡ��InetAddressʵ����ip�ַ���
		System.out.println("ip:" + ip.getHostAddress());
		//ʵ����Ӧ��ȫ���޶���
		System.out.println("ip.CanonicalHostName():" + ip.getCanonicalHostName());
		System.out.println();
	}
	static void testLocalAddress()throws Exception{
		InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
		System.out.println("local.isReachable():" + local.isReachable(2000));
		System.out.println("local.CanonicalHostName():" + local.getCanonicalHostName());
		System.out.println(InetAddress.getByName("localhost").getCanonicalHostName());
		System.out.println(InetAddress.getByName("localhost").getHostName());
		System.out.println(InetAddress.getByName("localhost").getHostAddress());
	}
	
	static void testURLDecoder()throws Exception{
		//��application/x-www-form-urlencoded�ַ���ת������ͨ�ַ���
	//	String keyWard = URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","UTF-8");
		System.out.println(URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","UTF-16"));
		System.out.println(URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","UTF-8"));
		System.out.println(URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","GBK"));
		//����ͨ�ַ���ת��Ϊapplication/x-www-form-urlencoded�ַ���
	//	String urlStr = URLEncoder.encode("���android����", "GBK");
		System.out.println(URLEncoder.encode("���android����", "GBK"));
		System.out.println(URLEncoder.encode("���android����", "UTF-8"));
		System.out.println(URLEncoder.encode("���android����", "UTF-16"));
	}
	
	public static void main(String[] args)throws Exception{
//		testInetAddress("www.crazyit.org");
//		testInetAddress("baidu.com");
//		testInetAddress("google.com");
		
//		testLocalAddress();
		testURLDecoder();
	}
}
