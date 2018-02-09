package aboutObjectA;
//静态导入，可以导入一个类中的单个或所有静态方法或变量
import static java.lang.System.*;

public class This {
	public int a;
	String str;
	protected double d;
	private float p;
	
	public This(int a,String str){
		out.println("Two arguements constructor called!");
		this.a = a;
		this.str = str;
		p = 0;
	}
	public This(int a,String str,double d){
		//使用this调用另一个构造函数的代码
		//该语句必须放在方法的第一行，且只能在构造函数中才能这样使用
		this(a,str);
		this.d = d;
		out.println("Three arguements constructor called!");
	}
	
	//不加任何修饰符，则是default访问权限，即只能在包内访问
	This grow(){
		++a;
		return this;//返回对象自身，这样就可以连续同一个方法
	}
	
	@Override
	public String toString(){
		return "[" + a+","+ str +","+ d +","+ p + "]" ;
	}
	
	public static void main(String[] args){
		This ths = new This(1,"test_this",3.3);
		out.println(ths);
		ths.grow().grow().grow().grow();
		out.println(ths);
		
	}
	
}
