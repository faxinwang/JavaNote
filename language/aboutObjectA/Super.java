package aboutObjectA;

class Base{
	int a;
	public Base(int x){
		System.out.println("Base(int x) called!");
		a = x;
	}
}

public class Super extends Base{
	int a;
	String str;
	public Super(int x){
		super(x);	//调用父类的构造函数
		a = x + 1;
		System.out.println("Super(int x) called!");
	}
	/* this() 和 super() 都必须出现在构造函数的第一行
	 * 所以this()和super()不可能同时出现在同一个构造函数中 
	 * */
	public Super(int x,String str){
		this(x);	//调用重载的构造函数
		this.str = str;
		System.out.println("Super(int x,String str) called!");
	}
	
	public String toString(){
		return "[ base.a="+super.a + ", this.a="+ a + ", this.str=\"" + str+"\"]";
	}
	
	public static void main(String [] rags){
		System.out.println(new Super(1,"str"));
	}
}
