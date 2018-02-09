package array;

class Person{
	public int age;
	public double height;
	public void info(){
		System.out.println("我的年龄是:" + age + ", 我的身高是:" + height);
	}
	@Override
	public String toString(){
		String str = "我的年龄是:" + age + ", 我的身高是:" + height;
		return str;
	}
}

public class ArrayReference {
	
	public static void main(String[] args){
		//在栈内存中创建了一个Person引用
		Person[] stu;
		//在堆内存中开辟了一个长度为5的Person数组
		//每个数组元素都是一个Person引用且初值为null
		stu = new Person[5];
		
		Person zhang = new Person();
		zhang.age = 15;
		zhang.height = 158;
		
		Person lee = new Person();
		lee.age = 18;
		lee.height = 161;
		
		stu[0] = zhang;
		stu[1] = lee;
		
		System.out.println("stu数组的长度为: " + stu.length);
		
		for(int i = 0 ,len = stu.length; i < len ; ++i){
			if(stu[i] != null)
				stu[i].info();
			else
				break;
		}
		
		//stu[0]和zhang指向堆内存中的同一个Person实例
		zhang.age = 100;
		lee.height = 100;
		System.out.println("after modified zhang and lee: ");
		
		for(int i = 0 ,len = stu.length; i < len ; ++i){
			if(stu[i] != null)
				System.out.println(stu[i]);
			else
				break;
		}
	}
}
