package innerClass;

class ExA {
	private static ExA a = new ExA();
	protected static int idx = 0;
	static {
		System.out.println("����--��̬�����"+idx++);
	}
	
	{
		System.out.println("����--�Ǿ�̬�����"+idx++);
	}
	
	public ExA() {
		System.out.println("����--���캯��"+idx++);
	}
}

class ExB extends ExA {
	private static ExB b = new ExB();
	
	static {
		System.out.println("����--��̬�����"+idx++);
	}
	
	{
		System.out.println("����--�Ǿ�̬�����"+idx++);
	}

	public ExB() {
		System.out.println("����--���캯��"+idx++);
	}
}


public class StaticInnerClass3 {
	public static void main(String[] args) {
		new ExB();
	}
}
