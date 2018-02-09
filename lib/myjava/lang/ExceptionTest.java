package myjava.lang;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/* �Զ�����쳣�࣬һ��ֱ�Ӽ̳���Exception��RuntimeException.
 * ����RuntimeException�඼������ʱ�쳣����������checked�쳣��
 * ����checked�쳣��������ʾ������׳�;����RuntimeException,����������ʾ�����׳���
 * �����ҪҲ������try..catch������.
 * 
 * �Զ����쳣��Ҫ�ṩһ���޲ι��캯����һ����String�����Ĺ��캯����һ����Throwable�Ĺ��캯��
 */

//�Զ����RuntimeException�쳣
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

//�Զ����Checked�쳣
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
			//�����׳�Exception�쳣�����øú����������봦��try�飬
			//���������׳��쳣�ĺ�����
			throw new MyException("throwChecked:������ֵ����0��������Ҫ��");
		}
	}
	static void throwRuntime(int a)throws MyRuntimeException{
		if(a>0){
			//�����׳�RuntimeException,��������ʾ�̻�
			//Ҳ������ȫ����ᣬ�Ѹ��쳣�������������ߴ���
			throw new MyRuntimeException("throwRuntime:������ֵ����0��������Ҫ��");
		}
	}
	
	//�ú�����Ȼ�����׳�Runtime�쳣��������Ҫ�������׳��쳣
	static void test1(){
		try{
			//���������׳�Checked�쳣�ķ�����Ҫô��ʾ������쳣
			//Ҫô��main�������ٴ������׳��쳣
			throwChecked(3);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//���������׳�Runtime�쳣�ķ����ȿ�����ʾ����Ҳ���Բ������쳣
		try{
			throwRuntime(2);
		}catch(MyRuntimeException re){
			System.out.println(re.getMessage());
		}
		
		System.out.println("���ǲ��������쳣֮��ִ�е����");
	
//		throwChecked(3); //Checked�쳣������ʾ���������׳�
	}
	static void test2()throws IOException{
		//java 7 �Ժ󣬿������������Բ���ŵ�try��䣬������try�����Զ��ر�������Դ
		//�ҿ���û��catch ��finally���
		//����������try������Դ�����ʵ��AutoCloseable��Closeable�ӿ�
		try(
			//��������ʼ��һ���ɹرյ���Դ,try�����Զ��ر������Դ
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
		
		System.out.println("\n\nMain����ִ�����!");
		System.out.println("����һ����佫�׳�Runtime�쳣");
		throwRuntime(3);	//�˴����׳�RuntimeException
	}
}
