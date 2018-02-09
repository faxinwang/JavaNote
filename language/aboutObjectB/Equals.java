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
		if(this == obj) return true; //����������ͬһ������
		if(obj != null && obj.getClass() == Person.class){//���д���ʹ���˷���
			Person p =(Person)obj;
			if(id != null && id.equals(p.id)) return true; //���֤����ͬ
		}
		return false;
	}
}

public class Equals {
	static void test1(){
		int it = 65;
		float fl = 65.0f;
		out.println("65��65.0�Ƿ����? " + (it==fl));//true
		out.println("65�͡�A���Ƿ����? " + (it=='A'));//'A'��ascll��Ϊ65,��true
		
		String str1 = new String("hello");
		String str2 = new String("hello");
		out.println("str1 == str2? "+ (str1==str2));//�������ñ������õĲ���ͬһ��ʵ��������false
		out.println("\"hello\"==\"hello\"? " + ("hello"=="hello"));//�ڳ���������ͬһ��ʵ��������true
		out.println("str1.eqrals(str2)? " + str1.equals(str2));//����ַ��Ƚϣ�����true
	}
	
	static void test2(){
		//s1ֱ�����ó������е�"���java"
		String s1 = "���java";
		String s2 = "���";
		String s3 = "java";
		//s4������ַ���ֵ�ڱ���ʱ�Ϳ���ȷ��������s4ֱ�����ó������е�"���java"
		String s4 = "���" + "java";
		//
		String s5 = "��" + "�� " + "java";
		//s6�е��ַ��������ٱ���ʱ��ȷ���������ʲ����ó������е��ַ���
		String s6 = s2 + s3;
		//ʹ��new���ù������ᴴ��һ���µ�String����
		//s7���ö��ڴ����´�����String����
		String s7 = new String("���java");
		
		out.println(s1 == s4);//true
		out.println(s1 == s5);//false
		out.println(s1 == s6);//false
		out.println(s1 == s7);//false
	}
	
	public static void main(String[] args){
		test1();
		test2();
		
		Person p1 = new Person("�����","123123123");
		Person p2 = new Person("������","123123123");
		Person p3 = new Person("����","123123456");
		Person p4 = null;
		
		out.println("p1.equals(p1): " + p1.equals(p2));
		out.println("p1.equals(p3): " + p1.equals(p3));
		out.println("p1.equals(p4): " + p1.equals(p4));
	}
}
