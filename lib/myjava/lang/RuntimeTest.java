package myjava.lang;

public class RuntimeTest {
	static void test1(){
		//��ȡjava�������������ʱ����
		Runtime rt = Runtime.getRuntime();
		System.out.println("����������:" + rt.availableProcessors());
		System.out.println("�����ڴ���:" + rt.freeMemory());
		System.out.println("���ڴ���:" + rt.totalMemory());
		System.out.println("��������ڴ���:" + rt.maxMemory());
	}
	static void test2() throws Exception{
		Runtime rt = Runtime.getRuntime();
		//���м��±�����
		rt.exec("notepad.exe");
	}
	
	public static void main(String[] args)throws Exception{
		test1();
		test2();
	}
}
