package aboutObjectA;
import static java.lang.System.*;
class Root{
	static{
		out.println("Root的静态初始化块!");
	}
	{
		out.println("Root的普通初始化块!");
	}
	public Root(){
		out.println("Root的无参构造函数!");
	}
}
class Mid extends Root{
	static{
		out.println("Mid的静态初始化块!");
	}
	{
		out.println("Mid的普通初始化块!");
	}
	public Mid(){
		out.println("Mid的无参构造函数!");
	}
	public Mid(String msg){
		this();//调用同一类中重载的构造函数
		out.println("Mid的带参数的构造函数,参数值为:"+msg);
	}
}
class Leaf extends Mid{
	static{
		out.println("Leaf的静态初始化块!");
	}
	{
		out.println("Leaf的普通初始化块!");
	}
	public Leaf(){
		//同过super调用父类中有一个字符串参数的构造函数
		super("疯狂java讲义!");
		out.println("执行Leaf的构造器!");
	}
}

public class TestInitBlock {
	
	public static void main(String[] args){
//		new Leaf();
//		out.println();
		
		//每个类实例化时都是先执行父类的静态初始化块(只在jvm加载该类时执行一次)
		//再执行自己的普通初始化块
		//最后执行自己的构造函数
		//(对于继承树上的每个类都是这样，所以需要用递归的思想来思考)
		
		//如果父类被加载过，则跳过父类的静态初始化块
		new Root();
		out.println();
		
		new Mid("mid class!");
		out.println();
		
		
		new Leaf();
		
		
	}
}
