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
//������ظ�ע��
@Repeatable(Annos.class)
@interface Anno{}

@Retention(RetentionPolicy.RUNTIME)
@interface Good{}

//ʹ���ĸ�ע�����θ���
@Good
@Deprecated
@Anno
@Anno
@SuppressWarnings("unused")	//��ע���Retention��ֵΪSOURCE,��������ʱ�ò�����ע��
public abstract class ClassTest {
	{
		System.out.println("ClassTest�ĳ�ʼ����");
	}
	
	//Ϊ���ඨ��һ��˽�еĹ�����
	private ClassTest(){}
	//����һ���в����Ĺ�����
	public ClassTest(String name){
		System.out.println("ִ���в����Ĺ�����");
	}
	//����һ���޲�����info����
	public void info(){}
	//����һ���в�����info()����
	public void info(String str,Integer Int){
		System.out.println("�в�����info����:");
		System.out.println("arg1:" +str);
		System.out.println("arg2:" +Int);
	}
	public abstract void fun();
	//����һ�������õ��ڲ���
	class InnerClass{
		{
			System.out.println("InnerClass�ĳ�ʼ����");
		}
		public InnerClass(){
			System.out.println("ClassTest���ڲ���:InnerClass");
		}
	}
	
	
	public static void main(String[] args){
		//��ȥClassTest��Ӧ��Class
		Class<ClassTest> clazz = ClassTest.class;
		//��ȡ��Class��������Ӧ��ȫ��������
		Constructor<?>[] cstor = clazz.getDeclaredConstructors();
		System.out.println("ClassTest��ȫ������������:");
		for(Constructor<?> c:cstor){
			System.out.println(c);
		}
		//��ȡ��Class����Ķ�Ӧ���ȫ��public ������
		Constructor<?>[] publicCstor = clazz.getConstructors();
		System.out.println("ClassTest��ȫ��public������:");
		for(Constructor<?> c:publicCstor){
			System.out.println(c);
		}
		//��ȡ��Class�����ȫ��Public����
		Method[] mtds = clazz.getDeclaredMethods();
		System.out.println("ClassTest��ȫ��public����:");
		for(Method m :mtds ){
			System.out.println(m);
		}
		//��ȡ��Class�����Ӧ���info(String)����
		try {
			System.out.println("ClassTest�������һ���ַ�����һ������������info����:" +
					clazz.getMethod("info", String.class,Integer.class));
		} catch (NoSuchMethodException e) {
			System.out.println("ClassTest����û��info(String)����");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//��ȡ��Class�����Ӧ���info(Integer)����
		try {
			System.out.println("ClassTest�������һ���ַ���������info����:" +
					clazz.getMethod("info", Integer.class));
		} catch (NoSuchMethodException e) {
			System.out.println("ClassTest����û��info(String)����");
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		
		//��ȡ��Class��������Ӧ���ȫ��ע��
		Annotation[] anns = clazz.getAnnotations();
		System.out.println("ClassTest��ȫ��Annotation����:");
		for(Annotation an:anns){
			System.out.println(an);
		}
		System.out.println("��Class�����ϵ�@SuppressWarningsע��Ϊ:" + 
			Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class)) );
		System.out.println("��Class�����ϵ�@Annoע��Ϊ:" +
			Arrays.toString(clazz.getAnnotationsByType(Anno.class)) );
		//��ȡ��Class��������Ӧ���ȫ���ڲ���
		Class<?>[] inners = clazz.getDeclaredClasses();
		System.out.println("ClassTest������ڲ�����:");
		for(Class<?> in :inners){
			System.out.println(in);
		}
		//ʹ��Class.forName()��������ClassTest����ڲ���InnerClass
		try {
		//	Class<?> inClazz = Class.forName("ClassTest$InnerClass");
			//ʹ��Class.forName()������Ҫʹ�����ȫ���޶���,��Ҫ��������
			Class<?> inClazz = Class.forName("myjava.lang.reflect.ClassTest$InnerClass");
			//ͨ��getDeclaringClass()���ʸ������ڵ��ⲿ��
			System.out.println("inClazz��Ӧ����ⲿ��Ϊ:" + inClazz.getDeclaringClass());
		} catch (ClassNotFoundException e) {
			System.out.println("ϵͳû���ҵ�ClassTest$InnerClass");
		}
		
		System.out.println("ClassTest�İ�Ϊ:" + clazz.getPackage());
		System.out.println("ClassTest�ĸ���Ϊ:" + clazz.getSuperclass());
	}
}
