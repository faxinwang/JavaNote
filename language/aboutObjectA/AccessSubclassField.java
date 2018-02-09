package aboutObjectA;

class Base2{
	private int i=2;
	public Base2(){
		System.out.println(i);	//输出2，输出的是base
		this.display();			//输出0，调用了子类的display方法
		//输出aboutObjectA.Derived，表明此时this代表的引用是Derived对象
		System.out.println(this.getClass());
		/* 此例子表明当变量的编译时类型和运行时类型不同是，通过该变量访问引用的对象的实例变量时，
		 * 访问的是编译时类型引用所以引用的实例变量，但通过该变量调用它引用的对象的实例方法时，
		 * 引用的将是它实际引用的对象的方法。因此当程序访问this.i是将会得到Base.i的值也就是2,
		 * 当执行this.display()时，调用的将是Derived.display(),因而访问的i是Derived中
		 * 定义的i,而此时i还没只是默认值0.
		 */
	}
	public void display(){
		System.out.println(i);
	}
}

class Derived extends Base2{
	private int i=22;
	public Derived(){
		i = 222;
	}
	public void display(){
		System.out.println(i);
	}
}


public class AccessSubclassField {
	public static void main(String[] args){
		
		//将会看到输出结果为0
		new Derived();
	}
}
