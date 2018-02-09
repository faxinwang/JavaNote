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
		super(x);	//���ø���Ĺ��캯��
		a = x + 1;
		System.out.println("Super(int x) called!");
	}
	/* this() �� super() ����������ڹ��캯���ĵ�һ��
	 * ����this()��super()������ͬʱ������ͬһ�����캯���� 
	 * */
	public Super(int x,String str){
		this(x);	//�������صĹ��캯��
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
