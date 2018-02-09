package myjava.lang;

public class RuntimeTest {
	static void test1(){
		//获取java程序关联的运行时对象
		Runtime rt = Runtime.getRuntime();
		System.out.println("处理器数量:" + rt.availableProcessors());
		System.out.println("空闲内存数:" + rt.freeMemory());
		System.out.println("总内存数:" + rt.totalMemory());
		System.out.println("可用最大内存数:" + rt.maxMemory());
	}
	static void test2() throws Exception{
		Runtime rt = Runtime.getRuntime();
		//运行记事本程序
		rt.exec("notepad.exe");
	}
	
	public static void main(String[] args)throws Exception{
		test1();
		test2();
	}
}
