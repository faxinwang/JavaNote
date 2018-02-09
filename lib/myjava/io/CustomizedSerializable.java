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
	
	//��д�����close����,�����һ�����
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
	//�Զ������л�ʱ�����л�flag��Ա����,�����л�ʱ�ó�Ա��������Ĭ��ֵ��ʼ��
	boolean flag=true;
	
	public PersonB(){
		System.out.println("PersonB���޲����Ĺ��캯��");
	}
	public PersonB(String name,int age){
		System.out.println("PersonB���в����Ĺ��캯��");
		this.name = name;
		this.age = age;
	}	
	public String toString(){
		return "PersonB[ name="+name+", age="+age+", falg="+flag+" ]";
	}
	//�ṩ���µ����ⷽ����ʵ���Զ������л�
	private void writeObject(ObjectOutputStream out)throws IOException{
		//��nameʵ��������ֵ��ת��д���������
		//�ô����൱�ڸ����ݼ��ܣ�������ʹ��Cracker��ȡ��Person����������������Ҳ�Ǽ��ܺ������
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age*5 + 2);
	}
	private void readObject(ObjectInputStream in)throws IOException,ClassNotFoundException{
		//��������д��ķ�������д��Ӧ�Ķ�ȡ����
		this.name = ((StringBuffer)in.readObject()).reverse().toString();
		this.age = (in.readInt()-2)/5;//�൱�ڽ���
	}
}

//ͨ��ʵ��Externalizable�ӿ���ʵ���Զ������л�
//����ʵ�ָýӿ��е���������
class PersonC implements Externalizable{
	private static final long serialVersionUID = 20160805_4L;
	String name;
	int age;
	//�Զ������л�ʱ�����л�flag��Ա����
	//��ͬ�����������л���ʽ��flag����û��writeExternal()������д���ļ�ʱҲ�ᱻ���л�
	boolean flag=true;
	
	public PersonC(){
		System.out.println("PerconC���޲����Ĺ��캯��");
	}
	public PersonC(String name,int age){
		System.out.println("PersonC���в����Ĺ��캯��");
		this.name = name;
		this.age = age;
	}	
	public String toString(){
		return "PersonC[ name="+name+", age="+age+", falg="+flag+" ]";
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		//��nameʵ��������ֵ��ת��д���������
		//�ô����൱�ڸ����ݼ��ܣ�������ʹ��Cracker��ȡ��Person����������������Ҳ�Ǽ��ܺ������
		out.writeObject(new StringBuffer(name).reverse());
		out.writeInt(age*5 + 2);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		//���ݶ���д��ķ�������д��Ӧ�Ķ�ȡ����
		this.name = ((StringBuffer)in.readObject()).reverse().toString();
		this.age = (in.readInt()-2)/5;//�൱�ڽ���	
	}
	
}

public class CustomizedSerializable{
	
	static void test1()throws Exception{
		PersonB p = new PersonB("�����",500);
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
		PersonC p = new PersonC("�����",500);
		try(
			ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("./lib/myjava/io/Objects.obj"));
			ObjectInputStream ois = new ObjectInputStream(
				new MyFileInputStream("./lib/myjava/io/Objects.obj"));	
		){
			oos.writeObject(p);
			//�˴������PersonC���޲����Ĺ��캯��
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
