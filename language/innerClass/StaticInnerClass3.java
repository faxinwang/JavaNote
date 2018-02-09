package innerClass;

class ExA {
	private static ExA a = new ExA();
	protected static int idx = 0;
	static {
		System.out.println("父类--静态代码块"+idx++);
	}
	
	{
		System.out.println("父类--非静态代码块"+idx++);
	}
	
	public ExA() {
		System.out.println("父类--构造函数"+idx++);
	}
}

class ExB extends ExA {
	private static ExB b = new ExB();
	
	static {
		System.out.println("子类--静态代码块"+idx++);
	}
	
	{
		System.out.println("子类--非静态代码块"+idx++);
	}

	public ExB() {
		System.out.println("子类--构造函数"+idx++);
	}
}


public class StaticInnerClass3 {
	public static void main(String[] args) {
		new ExB();
	}
}
