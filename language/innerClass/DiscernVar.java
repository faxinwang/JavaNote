package innerClass;

public class DiscernVar {
	private String prop = "外部类的实例变量";
	
	private class InnerClass{
		private String prop = "内部类的实例变量";
		
		//下面点有语法错误， 非静态内部类中不能有静态成员
	//	private static int a=0;
	//	private static void test(){}
	//	static{}  静态初始化块
		
		public void info(){
			String prop = "局部变量";
			//访问外部类的同名实例变量:OuterClass.this.verName
			System.out.println("外部类的实例变量的值:" + DiscernVar.this.prop);
			//访问内部类的同名实例变量:InnerClass.this.verName 或者 this.verName 
			System.out.println("内部类的实例变量的值:" + this.prop);
			System.out.println("局部变量的值:" + prop);
		}
		
		
	}
	
	public void test(){
		InnerClass in = new InnerClass();
		in.info();
		/*非静态内部类的成员在外部类中是不可知的，如果外部类要调用非静态内部类的成员
		 *则必须显式创建非静态内部类对象来调用访问其实例成员，原因是外部类存在时，内部类
		 *并不一定存在。 
		 * */
	}
	
	public static void main(String[] args){
		new DiscernVar().test();
	}
	

}
