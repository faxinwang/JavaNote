package myjava.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import javax.swing.JFrame;

class Person{
	private String name;
	private int age;
	public String toString(){
		return "Person[name:"+name+", age;"+age+"]";
	}
}

public class FieldTest {
	static void createJFrame(String title)throws Exception{
		@SuppressWarnings("unchecked")
		Class<JFrame> jfclazz = (Class<JFrame>)Class.forName("javax.swing.JFrame");
		Constructor<JFrame> cstr = jfclazz.getConstructor(String.class);
		JFrame jf = cstr.newInstance(title);
		jf.setSize(300, 100);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	static void testField()throws Exception{
		Person p = new Person();
		Class<Person> pclass = Person.class;
		//��ȡPerson����Ϊname�ĳ�Ա����
		//ʹ��getDeclaredField()�����������Ի�ȡ���ַ��ʿ��Ʒ��ĳ�Ա����
		Field nameField = pclass.getDeclaredField("name");
		//����ͨ��������ʸó�Ա����ʱȡ������Ȩ�޼��
		nameField.setAccessible(true);
		//����set����Ϊp������name��Ա������ֵ
		nameField.set(p, "wangfaxin");
		//��ȡPerson���age�ĳ�Ա����
		Field ageField = pclass.getDeclaredField("age");
		ageField.setAccessible(true);
		ageField.setInt(p, 20);
		System.out.println(p);
	}
	public static void main(String[] args)throws Exception{
		createJFrame("���Դ���");
		testField();
	}
}
