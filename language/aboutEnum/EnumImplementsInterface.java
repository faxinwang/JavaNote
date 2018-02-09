package aboutEnum;

import java.util.Arrays;

interface GenderDesc{
	void info();
}
enum Gender1 implements GenderDesc{
	//此处的枚举值必须调用对应的构造器来创建
	MALE("男"),FEMALE("女");
	private final String name;
	private Gender1(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	//所有的枚举值都将拥有相同的抽象方法
	@Override
	public void info() {
		System.out.println("这是一个用于定义性别的枚举类!");
	}
}

enum Gender2 implements GenderDesc{
	//花括号部分实际上是一个类体部分,是匿名内部子类的类体,而定义的枚举值就是匿名内部子类的实例
	//系统自动给这些实例使用 public static final修饰
	MALE("男"){	
		public void info(){
			System.out.println("这个枚举值代表男性!");
		}
	},
	//每个枚举值实例用不同的代码实现了接口的抽象方法，将表现各异
	FEMALE("女"){
		public void info(){
			System.out.println("这个枚举值代表女性!");
		}
	};
	
	private final String name;
	private Gender2(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}

public class EnumImplementsInterface {
	
	public static void main(String[] args){
		System.out.println(Arrays.toString(Gender1.values()));
		for(Gender1 g : Gender1.values()){
			g.info();
		}
		for(Gender2 g : Gender2.values()){
			g.info();
		}
		
		//枚举值都是public static final的，
		//所以可以直接通过className.valName.func()来调用其实例函数
		Gender1.FEMALE.info();
		Gender2.FEMALE.info();
	}
}
