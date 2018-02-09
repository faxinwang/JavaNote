package innerClass;

public class StaticInnerClass2 {
	public static class ClassA {
		public ClassA() {
			System.out.println("ClassA");
		}
		
		static{
			System.out.println("static A class");
		}
		
		{
			System.out.println("A class");
		}
	}
	public static class ClassB  extends ClassA {
		public ClassB() {
			super();//该语句不会再去执行，因为B的父类A是静态类，其构造函数只会执行一次。
			System.out.println("ClassB");
		}
		
		static {
			System.out.println("static B class");
		}
		
		{
			System.out.println("B class");
		}
		
		public void print() {
			System.out.println("B print");
		}
	}
	
	public static void main(String []args) {
		new ClassB();
	}
}
