package myjava.lang.reflect;

import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;


@Retention(value=RetentionPolicy.RUNTIME)
@interface Annos{
	Anno[] value();
}
//定义可重复注解
@Repeatable(Annos.class)
@interface Anno{}

@Retention(RetentionPolicy.RUNTIME)
@interface Good{}

//使用四个注解修饰该类
@Good
@Deprecated
@Anno
@Anno
@SuppressWarnings("unused")	//该注解的Retention的值为SOURCE,故子运行时得不到该注解
public abstract class ClassTest {
	{
		System.out.println("ClassTest的初始化块");
	}
	
	//为该类定义一个私有的构造器
	private ClassTest(){}
	//定义一个有参数的构造器
	public ClassTest(String name){
		System.out.println("执行有参数的构造器");
	}
	//定义一个无参数的info方法
	public void info(){}
	//定义一个有参数的info()方法
	public void info(String str,Integer Int){
		System.out.println("有参数的info方法:");
		System.out.println("arg1:" +str);
		System.out.println("arg2:" +Int);
	}
	public abstract void fun();
	//定义一个测试用的内部类
	class InnerClass{
		{
			System.out.println("InnerClass的初始化块");
		}
		public InnerClass(){
			System.out.println("ClassTest的内部类:InnerClass");
		}
	}
	
	
	public static void main(String[] args){
		//或去ClassTest对应的Class
		Class<ClassTest> clazz = ClassTest.class;
		//获取该Class对象所对应的全部构造器
		Constructor<?>[] cstor = clazz.getDeclaredConstructors();
		System.out.println("ClassTest的全部构造器如下:");
		for(Constructor<?> c:cstor){
			System.out.println(c);
		}
		//获取该Class对象的对应类的全部public 构造器
		Constructor<?>[] publicCstor = clazz.getConstructors();
		System.out.println("ClassTest的全部public构造器:");
		for(Constructor<?> c:publicCstor){
			System.out.println(c);
		}
		//获取该Class对象的全部Public方法
		Method[] mtds = clazz.getDeclaredMethods();
		System.out.println("ClassTest的全部public方法:");
		for(Method m :mtds ){
			System.out.println(m);
		}
		//获取该Class对象对应类的info(String)方法
		try {
			System.out.println("ClassTest类里带有一个字符串和一个整数参数的info方法:" +
					clazz.getMethod("info", String.class,Integer.class));
		} catch (NoSuchMethodException e) {
			System.out.println("ClassTest类里没有info(String)方法");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//获取该Class对象对应类的info(Integer)方法
		try {
			System.out.println("ClassTest类里带有一个字符串参数的info方法:" +
					clazz.getMethod("info", Integer.class));
		} catch (NoSuchMethodException e) {
			System.out.println("ClassTest类里没有info(String)方法");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		//获取该Class对象所对应类的全部注解
		Annotation[] anns = clazz.getAnnotations();
		System.out.println("ClassTest的全部Annotation如下:");
		for(Annotation an:anns){
			System.out.println(an);
		}
		System.out.println("该Class对象上的@SuppressWarnings注解为:" + 
			Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)) );
		System.out.println("该Class对象上的@Anno注解为:" +
			Arrays.toString(clazz.getAnnotationsByType(Anno.class)) );
		//获取该Class对象所对应类的全部内部类
		Class<?>[] inners = clazz.getDeclaredClasses();
		System.out.println("ClassTest类里的内部类有:");
		for(Class<?> in :inners){
			System.out.println(in);
		}
		//使用Class.forName()方法加载ClassTest里的内部类InnerClass
		try {
		//	Class<?> inClazz = Class.forName("ClassTest$InnerClass");
			//使用Class.forName()加载类要使用类的全域限定名,即要包括包名
			Class<?> inClazz = Class.forName("myjava.lang.reflect.ClassTest$InnerClass");
			//通过getDeclaringClass()访问该类所在的外部类
			System.out.println("inClazz对应类的外部类为:" + inClazz.getDeclaringClass());
		} catch (ClassNotFoundException e) {
			System.out.println("系统没有找到ClassTest$InnerClass");
		}
		
		System.out.println("ClassTest的包为:" + clazz.getPackage());
		System.out.println("ClassTest的父类为:" + clazz.getSuperclass());
	}
}
