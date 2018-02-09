package myjava.lang;

import java.io.FileOutputStream;
import java.lang.Exception;
import java.lang.String;
import java.lang.System;
import java.util.Map;
import java.util.Properties;

public class SystemTest {
	
	public static void testEnv(){
		//��ȡϵͳ���еĻ�������
			Map<String,String> env= System.getenv();
			for(String name : env.keySet()){
				System.out.println(name + "----->" + env.get(name));
			}
			System.out.println();
			
			//��ȡָ������������ֵ
			System.out.println("JAVA_HOME : "+ System.getenv("JAVA_HOME"));
			System.out.println();
				
			//��ȡ���е�ϵͳ����
			Properties props = System.getProperties();
			//��ϵͳ�������Ա��浽props.txt�ļ���
			try{
				props.store( new FileOutputStream("./src/testFiles/props.txt"), "System Properties");
			}catch(Exception e){
				System.out.println("file not exist!");
			}
				
			//��ȡ�ض���ϵͳ����
			System.out.println(System.getProperty("os.name"));
	}
	
	public static void testIdentityHashCode(){
		//s1 s2��������ͬ�Ķ���
		String s1 = new String("Hello");
		String s2 = new String("Hello");
		//String��д��hashCode()��������Ϊ�����ַ����м���hashCodeֵ
		//��Ϊs1��s2�ַ�������ͬ���������ǵ�hashcode()����ֵҲ��ͬ
		System.out.println(s1.hashCode()+"------"+ s2.hashCode());
		//s1,s2��������ͬ�Ķ����������ǵ�identityHashCodeֵ��ͬ
		//identityHashCodeʵ���Ͼ��Ƕ������ڴ��еĵ�ַ
		System.out.println(System.identityHashCode(s1)+"-----"+System.identityHashCode(s2));
		
		String s3 = "HelloJava";
		String s4 = "HelloJava";
		//s1,s2��������ͬ�Ķ����������ǵ�identityHashCodeֵ��ͬ
		System.out.println(System.identityHashCode(s3)+"-----"+System.identityHashCode(s4));
	}
	
	public static void main(String[] args){
		testEnv();
		testIdentityHashCode();
	}
}
