package myjava.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

public class StringNode {
	static void test1(){
		String src="����������һ���Ҹ�����\n" + 
				"ι��������������\n" +
				"�������𣬹�����ʳ���߲�\n" + 
				"����һ�����ӣ��泯�󺣣���ů����\n" + 
				"�������𣬺�ÿһ������ͨ��\n" + 
				"���������ҵ��Ҹ�\n";
		//	char[] buf = new char[32];
		//	int hasRead = 0;
			try(
				StringReader sr = new StringReader(src);
				BufferedReader br = new BufferedReader(sr);
			){
				String line=null;
				//����ѭ����ȡ�ķ�ʽ��ȡm�ַ���
				while((line=br.readLine())!=null){
					System.out.println(line);
				}
				
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
			try(
				//����һ��StringWriterʱ��ʵ������һ��StringBuffer��Ϊ����ڵ�
				//����ָ����20����StringBuffer�ĳ�ʼ����
				StringWriter sw = new StringWriter(20);
			){
				sw.write("��һ������������,\n");
				sw.write("����Զ������,\n");
				sw.write("����������ĺ���,\n");
				sw.write("���й����ľ���.\n");
				System.out.println("\n\n----������sw�ַ����ڵ����������----\n" + sw);
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
	}
	
	static void test2(){
		try(
			//��System.in����ת����Reader����
			InputStreamReader reader=new InputStreamReader(System.in);
			//����ͨ��Reader��װ��BufferedReader
			BufferedReader br = new BufferedReader(reader);
		){
			String line = null;
			while((line=br.readLine())!=null){
				if(line.equals("exit")){
					System.exit(1);
				}
				System.out.println(line);
			}
			
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
