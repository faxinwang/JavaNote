package aboutObjectB;

import static java.lang.System.*;

class Person{
	@SuppressWarnings("unused")
	private String name;
	private String id;
	public Person(){}
	public Person(String name,String id){
		this.name = name;
		this.id = id;
	}
	
	public boolean equals(Object obj){
		if(this == obj) return true; //两个对象是同一个对象
		if(obj != null && obj.getClass() == Person.class){//此行代码使用了反射
			Person p =(Person)obj;
			if(id != null && id.equals(p.id)) return true; //身份证号相同
		}
		return false;
	}
}

public class Equals {
	static void test1(){
		int it = 65;
		float fl = 65.0f;
		out.println("65和65.0是否相等? " + (it==fl));//true
		out.println("65和‘A’是否相等? " + (it=='A'));//'A'的ascll码为65,故true
		
		String str1 = new String("hello");
		String str2 = new String("hello");
		out.println("str1 == str2? "+ (str1==str2));//两个引用变量引用的不是同一个实例，返回false
		out.println("\"hello\"==\"hello\"? " + ("hello"=="hello"));//在常量池中是同一个实例，返回true
		out.println("str1.eqrals(str2)? " + str1.equals(str2));//逐个字符比较，返回true
	}
	
	static void test2(){
		//s1直接引用常量池中的"疯狂java"
		String s1 = "疯狂java";
		String s2 = "疯狂";
		String s3 = "java";
		//s4后面的字符串值在编译时就可以确定下来，s4直接引用常量池中的"疯狂java"
		String s4 = "疯狂" + "java";
		//
		String s5 = "疯" + "狂 " + "java";
		//s6中的字符串不能再编译时就确定下来，故不引用常量池中的字符串
		String s6 = s2 + s3;
		//使用new调用构造器会创建一个新的String对象
		//s7引用堆内存中新创建的String对象
		String s7 = new String("疯狂java");
		
		out.println(s1 == s4);//true
		out.println(s1 == s5);//false
		out.println(s1 == s6);//false
		out.println(s1 == s7);//false
	}
	
	public static void main(String[] args){
		test1();
		test2();
		
		Person p1 = new Person("孙悟空","123123123");
		Person p2 = new Person("孙行者","123123123");
		Person p3 = new Person("孙悟饭","123123456");
		Person p4 = null;
		
		out.println("p1.equals(p1): " + p1.equals(p2));
		out.println("p1.equals(p3): " + p1.equals(p3));
		out.println("p1.equals(p4): " + p1.equals(p4));
	}
}
