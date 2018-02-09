package basic_grammer;


class Cat{
	int age;
	String name;
	static String staticField="staticfield";
	//ִ��˳��:3
	public Cat(int age,String name){
		this.age= age;
		this.name = name;
	}
	//ִ��˳��:1
	double weight = 2.2;
	//ִ��˳��:2
	{
		weight = 2.0;
		System.out.println("ʵ������weight="+weight);
	}
	
	static{
		staticField = "staticString";
		System.out.println("�����staticField="+staticField);
	}
	
	public String toString(){
		return "Cat[name="+name+", age="+age+", weight="+weight+" ]";
	}
}

public class ClassInit {
	public static void main(String[] args){
		
		Cat cat = new Cat(2,"kitty");
		System.out.println(cat);
		Cat cat2= new Cat(4,"Garfield");
		System.out.println(cat2);
	}
}
