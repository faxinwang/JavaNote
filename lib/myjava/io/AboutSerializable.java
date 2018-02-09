package myjava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* 1.���л�����ʱ���������Ĺ�����.
 * 2.��һ�������л����ж���� ��ʱ���丸��Ҫô���޲����Ĺ�������ҪôҲ�ǿ����л��ģ��������л�ʱ���׳�InvalidClassException.
 * 3.��������ǲ������л��ģ���ø����ж���ĳ�Ա����ֵ�������л�������������.
 * 4.������а����������л����������ã�����಻�����л�
 * 5.
 * 
 * Java���л����Ʋ�����һ��������㷨�����㷨��������:
 * 1.���б��浽�����еĶ�����һ�����л����;
 * 2.��������ͼ���л�һ������ʱ�������ȼ��ö����Ƿ��Ѿ����л���������ö����δ(�ڱ����������)�����л�����ϵͳ�ŻὫ�ö���ת��
 *   ���ֽ����в����;
 * 3.���ĳ�������Ѿ����л���������ֻ��ֱ�����һ�����л���ţ��������ٴ��������л��ö���
 */


class Person implements Serializable{
	private static final long serialVersionUID = 20160805_1L;
	String name;
	int age;
	//��transient�ؼ������εĳ�Ա�������ᱻ���л�,�ùؼ���ֻ������ʵ������
	//�����л�ʱ,��transient���εĳ�Ա���������ʼ��Ϊnull,0����false;
	transient boolean flag;
	public Person(String name,int age){
		System.out.println("Person���в����Ĺ�����");
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
		Person per = new Person("�����",500);
		//����Teacher�����stud��Ա����������ͬһ��Person����per,��per��ֻ�ᱻ���л�һ��
		Teacher t1 = new Teacher("��ɮ",per);
		Teacher t2 = new Teacher("��������",per);
		//�������������,��һ������������Ҫ��װһ���ڵ���
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("./lib/myjava/io/Objects.obj"));
		//��per,t1,t2��������ת���ɶ�������д���ļ�������per��д������
		oos.writeObject(per);
		oos.writeObject(t1);
		oos.writeObject(t2);
		//����ͬһ������ֻ�е�һ��д���ǻᱻ���л����Ժ�ֻ��д��ö����serialVersionUID
		//��˶������л�֮�������ĸı䲻�ᱻ�����ļ�
		per.name = "��˽�";
		oos.writeObject(per);
		oos.close();
		
		//����������������Ҳ��һ������������Ҫ��װһ���ڵ���
		ObjectInputStream ois= new ObjectInputStream(
				new FileInputStream("./lib/myjava/io/Objects.obj"));
		//���ζ�ȡObjectInputStream���е�3������,����ȡper���Σ��Ƚ����������Ƿ���ͬ
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
