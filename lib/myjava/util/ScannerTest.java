package myjava.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerTest {
	public static void test1(){
		//System.in代表标准输入，就是键盘输入
		Scanner sc = new Scanner(System.in);
		//增加下面一行将只把回车作为分隔符
		sc.useDelimiter("000");
		while(sc.hasNext()){
			//输出输入项
			System.out.println("键盘输入的内容是: " + sc.next());
		}
		sc.close();
	}
	public static void test2(){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLong()){
			System.out.println("下一个long:" + sc.nextLong());
		}
		sc.close();
	}
	public static void test3() throws FileNotFoundException{
		Scanner sc = new Scanner(new FileInputStream("./lib/myjava/util/ScannerTest.java"));
		System.out.println("ScannerTest.java文件内容如下:");
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
