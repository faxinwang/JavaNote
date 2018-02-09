package aboutObjectA;
import static java.lang.System.*;

class Wrap{
	//a,b没有加参数修饰符，是default访问权限，即只能在包类被访问
	int a,b;
	Wrap(int a,int b){
		this.a = a;
		this.b = b;
	}
	@Override
	public String toString(){
		return "[" + a+ ","+b+"]";
	}
	
}

public class ArgsTransfer {
	//该函数交换整数a,b的值,但不起作用,因为他们不是引用类型
	public static void swap(int a,int b){
		out.println("before swap:a="+a+", b="+b);
		a^=b;
		b^=a;
		a^=b;
		out.println("after swap:a="+a+", b="+b);
	}
	//能成功交换w.a 和w.b的值，因为w是引用类型
	public static void swap(Wrap w){
		w.a ^= w.b;
		w.b ^= w.a;
		w.a ^= w.b;
	}
	
	static void testVerargs(String str){
		out.println("非可变参数的testVerargs() :"+str);
	}
	//参数个数可变的函数,只能是最后一个参数为可变个数参数,其实质相当于一个数组
	static void testVerargs(String...books){
		int count=0;
		out.println("参数个数可变的testVerargs() :");
		for(String book:books){
			++count;
			out.println(book);
		}
		out.println("参数个数为:"+count);
	}
	
	public static void main(String[] args){
		int x = 1,y=2;
		swap(x,y);
		out.println("x=" + x +",y=" + y);
		
		Wrap w = new Wrap(3,4);
		out.println(w);
		swap(w);
		out.println(w);
		
		testVerargs();//调用参数个数可变的版本
		testVerargs("just one arg");//调用非参数个数可变的版本
		testVerargs("arg one","arg two","arg three");//调用参数个数可变的版本
		testVerargs(new String[]{"one","two","three","four"});//调用参数个数可变的版本
	}
}
