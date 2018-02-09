package myjava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* 1.序列化对象时无需调用类的构造器.
 * 2.当一个可序列化类有多个父 类时，其父类要么有无参数的构造器，要么也是可序列化的，否则反序列化时会抛出InvalidClassException.
 * 3.如果父类是不可序列化的，则该父类中定义的成员变量值不会序列化到二进制流中.
 * 4.如果类中包含不可序列化类对象的引用，则该类不可序列化
 * 5.
 * 
 * Java序列化机制采用了一种特殊的算法，其算法内容如下:
 * 1.所有保存到磁盘中的对象都有一个序列化编号;
 * 2.当程序试图序列化一个对象时，程序先检查该对象是否已经序列化过，如果该对象从未(在本次虚拟机中)被序列化过，系统才会将该对象转换
 *   成字节序列并输出;
 * 3.如果某个对象已经序列化过，程序将只是直接输出一个序列化编号，而不是再次重新序列化该对象
 */


class Person implements Serializable{
	private static final long serialVersionUID = 20160805_1L;
	String name;
	int age;
	//被transient关键字修饰的成员变量不会被序列化,该关键字只能修饰实例变量
	//反序列化时,被transient修饰的成员变量将会初始化为null,0或者false;
	transient boolean flag;
	public Person(String name,int age){
		System.out.println("Person类有参数的构造器");
		this.name = name;
		this.age = age;
		flag = true;
	}
	
	public String toString(){
		return "Person[ name="+name+", age="+age+",flag="+flag+" ]";
	}
}

class Teacher implements Serializable{
	private static final long serialVersionUID = 20160805_2L;
	String name;
	Person stud;
	public Teacher(String name,Person stud){
		this.name = name;
		this.stud = stud;
	}

	public String toString(){
		return "Teacher[ name="+name + ", stud=" + stud+" ]";
	}
}

public class AboutSerializable {
	static void test1()throws Exception{
		Person per = new Person("孙悟空",500);
		//两个Teacher对象的stud成员变量引用了同一个Person对象per,但per将只会被序列化一次
		Teacher t1 = new Teacher("唐僧",per);
		Teacher t2 = new Teacher("菩提老祖",per);
		//创建对象输出流,是一个处理流，需要包装一个节点流
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("./lib/myjava/io/Objects.obj"));
		//将per,t1,t2三个对象转换成二进制流写入文件，其中per被写入两次
		oos.writeObject(per);
		oos.writeObject(t1);
		oos.writeObject(t2);
		//对于同一个对象，只有第一次写入是会被序列化，以后都只会写入该对象的serialVersionUID
		//因此对象被序列化之后所做的改变不会被存入文件
		per.name = "猪八戒";
		oos.writeObject(per);
		oos.close();
		
		//创建对象输入流，也是一个处理流，需要包装一个节点流
		ObjectInputStream ois= new ObjectInputStream(
				new FileInputStream("./lib/myjava/io/Objects.obj"));
		//依次读取ObjectInputStream流中的3个对象,并读取per两次，比较两个对象是否相同
		Person per1 = (Person)ois.readObject();
		Teacher t11 = (Teacher)ois.readObject();
		Teacher t22 = (Teacher)ois.readObject();
		Person per2 = (Person)ois.readObject();
		ois.close();
		
		System.out.println("per1==per2: " + (per1==per2));
		System.out.println("per1==per: " + (per1==per));
		System.out.println("per1==t1.stud: " + (per1==t1.stud));
		System.out.println("per1==t11.stud: " + (per1==t11.stud));
		System.out.println("per1==t22.stud: " + (per1==t22.stud));
		System.out.println("t11.stud==t22.stud: " + (t11.stud==t22.stud));
		System.out.println("t1.stud==t2.stud: " + (t1.stud==t2.stud));
		
		System.out.println("t1: " + t1);
		System.out.println("t2: " + t2);
		System.out.println("per: " + per);
		System.out.println("per1: " + per1);
		System.out.println("per2: " + per2);
		System.out.println("t11: " + t11);
		System.out.println("t22: " + t22);
	}
	
	public static void main(String[] args)throws Exception{
		test1();
	}
}
