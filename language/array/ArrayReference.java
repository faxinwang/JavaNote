package array;

class Person{
	public int age;
	public double height;
	public void info(){
		System.out.println("�ҵ�������:" + age + ", �ҵ������:" + height);
	}
	@Override
	public String toString(){
		String str = "�ҵ�������:" + age + ", �ҵ������:" + height;
		return str;
	}
}

public class ArrayReference {
	
	public static void main(String[] args){
		//��ջ�ڴ��д�����һ��Person����
		Person[] stu;
		//�ڶ��ڴ��п�����һ������Ϊ5��Person����
		//ÿ������Ԫ�ض���һ��Person�����ҳ�ֵΪnull
		stu = new Person[5];
		
		Person zhang = new Person();
		zhang.age = 15;
		zhang.height = 158;
		
		Person lee = new Person();
		lee.age = 18;
		lee.height = 161;
		
		stu[0] = zhang;
		stu[1] = lee;
		
		System.out.println("stu����ĳ���Ϊ: " + stu.length);
		
		for(int i = 0 ,len = stu.length; i < len ; ++i){
			if(stu[i] != null)
				stu[i].info();
			else
				break;
		}
		
		//stu[0]��zhangָ����ڴ��е�ͬһ��Personʵ��
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
