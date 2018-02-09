package aboutObjectA;

public class Instanceof {
	
	public static void main(String[] args){
		//声明hello时使用的是Object类，则hello的编译类型的Object
		//Object是所有类的父类，但hello的实际类型是String
		Object hello = "Hello";
		
		//String 与 Object存在继承关系,可以进行instanceof运算
		System.out.println("字符串是否是Object实例:" + (hello instanceof Object));//返回true
		System.out.println("字符串是否是String实例:" + (hello instanceof String));//返回true
		
		//Math与Object类存在继承关系，可以进行instanceof 运算
		System.out.println("字符串是否是Math实例:" + (hello instanceof Math));//返回false
		
		//String实现了Comparable接口,所以返回true
		System.out.println("字符串是否是Comparable实例:" + (hello instanceof Comparable));//返回true
		
		@SuppressWarnings("unused")
		String a = "Hello";
		//String类与Math类没有继承关系，所以下面的代码编译无法通过
	//	System.out.println("字符串是否是Math类的实例:" + (a instanceof Math));
	}
	
}
