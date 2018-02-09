package Singleton;

public class Singleton {
	
	private String name;
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	//使用一个私有类变量来缓存曾经创建的实例
	private static Singleton instance;
	//将构造器声明为private,隐藏该构造器
	private Singleton(){}
	
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
	
	public static void main(String[] args){
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		
		System.out.println(s2.getName());
		
		s1.setName("single");
		
		System.out.println(s2.getName());
		System.out.println("s1 == s2: " + (s1==s2));
		
		s1.setName("singleton!");
		s1 = null;
		s2 = null;
		
		s2 = Singleton.getInstance();
		System.out.println(s2.getName());
	}
	
}
