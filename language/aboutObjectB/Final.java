package aboutObjectB;

/* final修饰的非静态成员变量必须在下面三个地方之一初始化
 * 	1.定义时.(这种情况下(在编译时就知道变量的值)系统实质上执行了"宏替换")
 *  2.普通初始化块
 *  3.构造函数
 * final修饰的静态成员变量必须在下面两个地方之一初始化
 *	1.定义时.
 *  2.静态初始化块.
 * public final修饰的成员函数不能被重写，但能被重载,子类
 * 		中不能定义与父类中有相同签名和参数列表的成员函数
 * private final修饰的成员函数在子类中不可见，所以子类中
 *		能定义与父类中相同的用final修饰的成员函数,这些函数是子类自己的
 * */



public class Final {
	static void testFinal1(){
		final int a = 2 + 4;
		final int b = 3 + 5;
		System.out.println(a * b);// 6 * 8 = 48;
		@SuppressWarnings("unused")
		final String str = "疯狂" + "Java";
		final String book = "疯狂Java讲义: " + 99.0;
		final String book2 = "疯狂Java讲义: " + String.valueOf(99.0);
		System.out.println(book == "疯狂Java讲义: 99.0");	//book是宏替换得来,实质是两个常量相比较,true
		System.out.println(book2 == "疯狂Java讲义: 99.0");	//books不进行宏替换,是用变量保存的,返回false
		System.out.println(book2.equals("疯狂Java讲义: 99.0"));//true
	}
	static void testFinal2(){
		String s1 = "疯狂java";
		String s2 = "疯狂" + "java";
		System.out.println("s1 == s2 : " + s1 == s2); //false
		
		String str1 = "疯狂";
		String str2 = "java";
		String s3 = str1 + str2;
		System.out.println("s1 == s3 : " + s1 == s3); //false
		
		final String str3 = "疯狂";
		final String str4 = "java";
		String s4 = str3 + str4;
		System.out.println("s1 == s4 : " + s1 == s4 );//false
	}
	
	public static void main(String[] args){
		testFinal1();
		testFinal2();
	}
}
