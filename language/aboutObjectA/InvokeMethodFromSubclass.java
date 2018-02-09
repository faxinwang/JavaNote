package aboutObjectA;

class Animal{
	private String desc;
	public Animal(){
		this.desc  = getDesc();	//2 此处可能调用子类中的重写了getDesc()的函数
	}
	public String getDesc(){
		return "Animal";
	}
	public String toString(){
		return desc;
	}
}

class Wolf extends Animal{
	private String name;
	private double weight;
	public Wolf(String name ,double weight){	//3
		this.name = name;
		this.weight = weight;
	}
	
	@Override
	public String getDesc(){
		return "Wolf[name="+name+",weight="+weight+"]";
	}
}

public class InvokeMethodFromSubclass {
	public static void main(String[] args){
		//将会输出:Wolf[name=null,weight=0.0]
		System.out.println(new Wolf("灰太狼",22.2)); 	//1
		/* 初看本程序会觉得输出应该是Wolf[name=灰太狼,weight=22.2],
		 * 但实际上却不是。本程序的执行顺序为代码1,2,3.Wolf()构造函数会先调用Animal的默认构造函数,
		 * 该默认构造函数中调用了getDesc()函数,值得注意的是,2处的代码实际上会调用子类的getDesc()函数,
		 * 原因是this实际上引用的是Wolf的对象.而执行此函数是,Wolf的构造函数还未被调用,故其值均为默认值.
		 */
	}
}
