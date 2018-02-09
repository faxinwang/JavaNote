package myjava.lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/* 自定义的异常类，一般直接继承自Exception或RuntimeException.
 * 所有RuntimeException类都是运行时异常，其他都是checked异常。
 * 对于checked异常，必须显示捕获或抛出;对于RuntimeException,程序无需显示声明抛出，
 * 如果需要也可以用try..catch来捕获.
 * 
 * 自定义异常需要提供一个无参构造函数和一个带String参数的构造函数或一个带Throwable的构造函数
 */

//自定义的RuntimeException异常
@SuppressWarnings("serial")
class MyRuntimeException extends RuntimeException{
	
	public MyRuntimeException(){}
	public MyRuntimeException(String msg){
		super(msg);
	}
	public MyRuntimeException(Throwable cause){
		super(cause);
	}
}

//自定义的Checked异常
@SuppressWarnings("serial")
class MyException extends Exception{
	public MyException(){}
	public MyException(String msg){
		super(msg);
	}
	public MyException(Throwable cause){
		super(cause);
	}
}


public class ExceptionTest {
	static void throwChecked(int a)throws MyException{
		if(a>0){
			//自行抛出Exception异常，调用该函数的语句必须处于try块，
			//或处于声明抛出异常的函数中
			throw new MyException("throwChecked:参数的值大于0，不符合要求");
		}
	}
	static void throwRuntime(int a)throws MyRuntimeException{
		if(a>0){
			//自行抛出RuntimeException,即可以显示铺货
			//也可以完全不理会，把该异常交给方法调用者处理
			throw new MyRuntimeException("throwRuntime:参数的值大于0，不符合要求");
		}
	}
	
	//该函数虽然可能抛出Runtime异常，但不需要声明会抛出异常
	static void test1(){
		try{
			//调用声明抛出Checked异常的方法，要么显示捕获该异常
			//要么在main方法中再次声明抛出异常
			throwChecked(3);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//调用声明抛出Runtime异常的方法既可以显示捕获，也可以不理会该异常
		try{
			throwRuntime(2);
		}catch(MyRuntimeException re){
			System.out.println(re.getMessage());
		}
		
		System.out.println("这是捕获两个异常之后执行的语句");
	
//		throwChecked(3); //Checked异常必须显示捕获或继续抛出
	}
	static void test2()throws IOException{
		//java 7 以后，可以声明后面跟圆括号的try语句，这样的try语句会自动关闭物理资源
		//且可以没有catch 和finally语句
		//可用于这种try语句的资源类必须实现AutoCloseable或Closeable接口
		try(
			//声明，初始化一个可关闭的资源,try语句会自动关闭这个资源
			BufferedReader br = new BufferedReader(new FileReader("./lib/myjava/lang/ExceptionTest.java"));
		){
			for(int i=0;i<30;++i)
				System.out.println(br.readLine());
		}
	}
	
	public static void main(String[] args){
		test1();
		try{
			test2();
		}catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
		
		System.out.println("\n\nMain函数执行完毕!");
		System.out.println("下面一条语句将抛出Runtime异常");
		throwRuntime(3);	//此处将抛出RuntimeException
	}
}
