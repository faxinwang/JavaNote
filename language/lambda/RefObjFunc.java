package lambda;
import static java.lang.System.*;

@FunctionalInterface
interface RefObjectFunc{
	String subStr(String str,int beg,int end);
}

public class RefObjFunc {
	
	public static void main(String[] args){
		//使用lambda表达式创建RefObjectFunc对象
		RefObjectFunc rof1 = (str,beg,end)->str.substring(beg,end);
		String sub1 = rof1.subStr("android", 3, 6);
		out.println(sub1);
		
		
		//方法引用代替lambda表达式: 引用某对象的实例方法
		//函数式接口中的第一个参数作为调用者,后面的所有参数穿个方法作为参数
		RefObjectFunc rof2 = String::substring;
		String sub2 = rof2.subStr("android", 3, 7);
		out.println(sub2);
	}
}
