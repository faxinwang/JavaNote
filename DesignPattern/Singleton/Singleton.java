package Singleton;

public class Singleton {
	
	private String name;
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	//ʹ��һ��˽�����������������������ʵ��
	private static Singleton instance;
	//������������Ϊprivate,���ظù�����
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
