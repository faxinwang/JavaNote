package myjava.io;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class MyFileInputStream extends FileInputStream{
	public MyFileInputStream(String file) throws FileNotFoundException {
		super(file);
	}
	
	//重写父类的close方法,并输出一条语句
	@Override
	public void close()throws IOException{
		super.close();
		System.out.println("Closing FileInputStream...");
	}
}

class PersonB implements Serializable{
	private static final long serialVersionUID = 20160805_3L;
	String name;
	int age;
	//自定义序列化时不序列化flag成员变量,反序列化时该成员变量会用默认值初始化
	boolean flag=true;
	
	public PersonB(){
		System.out.println("PersonB类无参数的构造函数");
	}
	public PersonB(String name,int age){
		System.out.println("PersonB类有参数的构造函数");
		this.name = name;
		this.age = age;
	}	
	public String toString(){
		return "PersonB[ name="+name+", age="+age+", falg="+flag+" ]";
	}
	//提供如下的特殊方法来实现自定义序列化
	private void writeObject(ObjectOutputStream out)throws IOException{
		//将name实例变量的值反转后写入二进制流
		//该处理相当于给数据加密，这样即使有Cracker截取到Person对象流，他看到的也是加密后的内容
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age*5 + 2);
	}
	private void readObject(ObjectInputStream in)throws IOException,ClassNotFoundException{
		//根基对象写入的方法来编写相应的读取方法
		this.name = ((StringBuffer)in.readObject()).reverse().toString();
		this.age = (in.readInt()-2)/5;//相当于解密
	}
}

//通过实现Externalizable接口来实现自定义序列化
//必须实现该接口中的两个方法
class PersonC implements Externalizable{
	private static final long serialVersionUID = 20160805_4L;
	String name;
	int age;
	//自定义序列化时不序列化flag成员变量
	//不同的是这种序列化方式下flag就算没在writeExternal()方法中写入文件时也会被序列化
	boolean flag=true;
	
	public PersonC(){
		System.out.println("PerconC类无参数的构造函数");
	}
	public PersonC(String name,int age){
		System.out.println("PersonC类有参数的构造函数");
		this.name = name;
		this.age = age;
	}	
	public String toString(){
		return "PersonC[ name="+name+", age="+age+", falg="+flag+" ]";
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		//将name实例变量的值反转后写入二进制流
		//该处理相当于给数据加密，这样即使有Cracker截取到Person对象流，他看到的也是加密后的内容
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age*5 + 2);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		//根据对象写入的方法来编写相应的读取方法
		this.name = ((StringBuffer)in.readObject()).reverse().toString();
		this.age = (in.readInt()-2)/5;//相当于解密	
	}
	
}

public class CustomizedSerializable{
	
	static void test1()throws Exception{
		PersonB p = new PersonB("孙悟空",500);
		try(
			ObjectOutputStream oos = new ObjectOutputStream(
					new FileOutputStream("./lib/myjava/io/Objects.obj"));
			ObjectInputStream ois = new ObjectInputStream(
					new MyFileInputStream("./lib/myjava/io/Objects.obj"));
		){
			oos.writeObject(p);
			PersonB pp =(PersonB)ois.readObject();
			System.out.println("p: "+ p);
			System.out.println("pp: "+ pp);
		}
	}
	
	static void test2()throws Exception{
		PersonC p = new PersonC("孙悟空",500);
		try(
			ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("./lib/myjava/io/Objects.obj"));
			ObjectInputStream ois = new ObjectInputStream(
				new MyFileInputStream("./lib/myjava/io/Objects.obj"));	
		){
			oos.writeObject(p);
			//此处会调用PersonC类无参数的构造函数
			PersonC pp = (PersonC)ois.readObject();
			System.out.println("p: "+ p);
			System.out.println("pp: "+ pp);			
		}
	}
	
	public static void main(String[] args)throws Exception{
		test1();
		test2();
	}
}
