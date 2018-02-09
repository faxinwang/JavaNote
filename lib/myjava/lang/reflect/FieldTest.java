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
		//获取Person的名为name的成员变量
		//使用getDeclaredField()方法表明可以获取各种访问控制符的成员变量
		Field nameField = pclass.getDeclaredField("name");
		//设置通过反射访问该成员变量时取消访问权限检查
		nameField.setAccessible(true);
		//调用set方法为p变量的name成员变量赋值
		nameField.set(p, "wangfaxin");
		//获取Person类的age的成员变量
		Field ageField = pclass.getDeclaredField("age");
		ageField.setAccessible(true);
		ageField.setInt(p, 20);
		System.out.println(p);
	}
	public static void main(String[] args)throws Exception{
		createJFrame("测试窗口");
		testField();
	}
}
