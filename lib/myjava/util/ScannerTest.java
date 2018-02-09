package myjava.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerTest {
	public static void test1(){
		//System.in�����׼���룬���Ǽ�������
		Scanner sc = new Scanner(System.in);
		//��������һ�н�ֻ�ѻس���Ϊ�ָ���
		sc.useDelimiter("000");
		while(sc.hasNext()){
			//���������
			System.out.println("���������������: " + sc.next());
		}
		sc.close();
	}
	public static void test2(){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLong()){
			System.out.println("��һ��long:" + sc.nextLong());
		}
		sc.close();
	}
	public static void test3() throws FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream("./lib/myjava/util/ScannerTest.java"));
		System.out.println("ScannerTest.java�ļ���������:");
		while(sc.hasNextLine()){	
			System.out.println(sc.nextLine());
		}
		sc.close();
	}
	
	public static void main(String[] args)throws FileNotFoundException{
	//	test1();
	//	test2();
		test3();
		
	}
}
