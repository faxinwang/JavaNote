package myjava.net;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class InetAddressTest {
	static void testInetAddress(String websit)throws Exception{
		//根据主机名来获取对应的InetAddress实例
		InetAddress ip = InetAddress.getByName(websit);
		//判断是否可达
		System.out.println(websit+"是否可达:"+ ip.isReachable(2000));
		//获取该InetAddress实例的ip字符串
		System.out.println("ip:" + ip.getHostAddress());
		//实例对应的全域限定名
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
		//将application/x-www-form-urlencoded字符串转换成普通字符串
	//	String keyWard = URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","UTF-8");
		System.out.println(URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","UTF-16"));
		System.out.println(URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","UTF-8"));
		System.out.println(URLDecoder.decode("android%E5%BC%80%E5%8F%91%E5%85%A5%E9%97%A8","GBK"));
		//将普通字符串转换为application/x-www-form-urlencoded字符串
	//	String urlStr = URLEncoder.encode("疯狂android讲义", "GBK");
		System.out.println(URLEncoder.encode("疯狂android讲义", "GBK"));
		System.out.println(URLEncoder.encode("疯狂android讲义", "UTF-8"));
		System.out.println(URLEncoder.encode("疯狂android讲义", "UTF-16"));
	}
	
	public static void main(String[] args)throws Exception{
//		testInetAddress("www.crazyit.org");
//		testInetAddress("baidu.com");
//		testInetAddress("google.com");
		
//		testLocalAddress();
		testURLDecoder();
	}
}
