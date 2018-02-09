package innerClass;

public class StaticInnerClass {
	private int Outer_prop = 5;
	private static int Outer_static_prop = 6;
	
	static class StaticInClass{
		//静态内部类里可以包含静态成员变量
		private int Inner_prop = 7;
		private static int Inner_static_prop = 8; 
		public void accessOuterProp(){
			//静态内部类不能访问外部类的实例变量
			//因为静态内部类对象不需要寄生在外部类对象中
			//静态内部类对象存在时，外部类对象不一定存在
		//	System.out.println(Outer_prop);
			//所以内部类要访问外部类的实例成员也只能通过外部类对象访问
			System.out.println("accessOuterProp() "
					+ "Outer_prop:" + new StaticInnerClass().Outer_prop);
			//静态内部类访问外部类的静态成员，正常
			System.out.println("accessOuterProp()"
					+ " Outer_static_prop: " + Outer_static_prop);
		}
		//静态内部类的静态方法可直接访问外部类的静态成员
		public static void staticAccessOuterProp(){
			System.out.println("staticAccessOuterProp() "
					+ "Outer_static_prop :" + Outer_static_prop);
		}
	}
	
	public void accessInnerProp(){
		//外部类访问静态内部类的静态成员,要通过类名访问
	//	System.out.println(Inner_static_prop);
		System.out.println("accessInnerProp() "
				+ "Inner_static_prop:"+StaticInClass.Inner_static_prop);
		//外部类访问静态内部类的实例变量，要通过静态内部类的实例访问
		System.out.println("accessInnerProp()"
				+ " Inner_prop:"+new StaticInClass().Inner_prop);
	}
	
	public static void main(String[] args){
		StaticInClass.staticAccessOuterProp(); 		//外部类直接访问内部类的静态方法
		
		StaticInClass inner = new StaticInClass();	//创建内部类对象
		
		inner.accessOuterProp();//调用内部类实例方法,该方法访问了外部类的静态成员变量和实例变量
		
		new StaticInnerClass().accessInnerProp();//外部类的实例方法，该方法中访问了内部类的静态变量和实例变量
	}
	
}
