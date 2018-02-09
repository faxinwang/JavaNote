package myjava.lang;

import java.io.FileOutputStream;
import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.util.Map;
import java.util.Properties;

public class SystemTest {
	
	public static void testEnv(){
		//获取系统所有的环境变量
			Map<String,String> env= System.getenv();
			for(String name : env.keySet()){
				System.out.println(name + "----->" + env.get(name));
			}
			System.out.println();
			
			//获取指定环境变量的值
			System.out.println("JAVA_HOME : "+ System.getenv("JAVA_HOME"));
			System.out.println();
				
			//获取所有的系统属性
			Properties props = System.getProperties();
			//将系统所有属性保存到props.txt文件中
			try{
				props.store( new FileOutputStream("./src/testFiles/props.txt"), "System Properties");
			}catch(Exception e){
				System.out.println("file not exist!");
			}
				
			//获取特定的系统属性
			System.out.println(System.getProperty("os.name"));
	}
	
	public static void testIdentityHashCode(){
		//s1 s2是两个不同的对象
		String s1 = new String("Hello");
		String s2 = new String("Hello");
		//String重写了hashCode()方法，改为根据字符序列计算hashCode值
		//因为s1和s2字符序列相同，所以他们的hashcode()返回值也相同
		System.out.println(s1.hashCode()+"------"+ s2.hashCode());
		//s1,s2是两个不同的对象，所以他们的identityHashCode值不同
		//identityHashCode实际上就是对象在内存中的地址
		System.out.println(System.identityHashCode(s1)+"-----"+System.identityHashCode(s2));
		
		String s3 = "HelloJava";
		String s4 = "HelloJava";
		//s1,s2是两个相同的对象，所以他们的identityHashCode值相同
		System.out.println(System.identityHashCode(s3)+"-----"+System.identityHashCode(s4));
	}
	
	public static void main(String[] args){
		testEnv();
		testIdentityHashCode();
	}
}
