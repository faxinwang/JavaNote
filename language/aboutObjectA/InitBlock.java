package aboutObjectA;

public class InitBlock {
	static int count;
	int eye,ear,nose;
	String name;
	int height;
	
	//执行顺序:3
	public InitBlock(String name){
		this.name = name;
		System.out.println("InitBlock的无参构造器!");
	}
	public InitBlock(String name,int height){
		this.name = name;
		this.height = height;
	}
	
	//静态初始化快，在类被jvm加载到系统时执行(只执行一次),如有多个则按在代码中的顺序执行
	//执行顺序:1
	static{
		count=0;
		System.out.println("InitBlock的一个静态初始化块!");
	}
	//下面定义了一个普通初始化块,在每次实例化一个对象时在构造函数之前被执行,如有多个,则按在代码中的顺序执行
	//使用初始化块的原则，各构造函数中相同的代码提取出来放到初始化块中，以提高代码复用率
	//执行顺序:2
	{
		eye=ear=2;
		nose=1;
		System.out.println("InitBlock类的普通初始化块!");
	}
	public String toString(){
		return "[eye="+eye+", ear="+ ear + ", nose="+nose+", name=\""+name+"\", height="+height+"]";
	}
	
	public static void main(String[] args){
		System.out.println(new InitBlock("Tom"));
		System.out.println(new InitBlock("Bob",168));
	}
}
