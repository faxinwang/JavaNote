package proxy;

/* 代理模式可以应用的场合:
 * 1.远程代理，也就是为一个对象在不同的地址空间提供局部代表，这样可以隐藏一个对象存在于不同地址空间的事实。
 * 2.虚拟代理，是根据需要创建开销很大的对象。通过它来存放实例化需要很长时间的真实对象。
 * 3.安全代理，用来控制真实对象访问时的权限。一般用于对象应该有不同的访问权限的时候。
 * 4.智能指引，是指当调用真实对象时，代理处理另外一些事情。
 * 代理模式其实就是在访问对象时引入一定程度的间接性，因为这种间接性，可以附加多种用途。
 */

/*  ISubject
 * 		|----->RealSubject
 * 		|----->Proxy
 */

//Subject类，定义了RealSubject和Proxy的共同接口
//这样就在任何使用RealSubject的地方都可以使用Proxy
interface ISubject{
	void Func1();
	void Func2();
	void Func3();
}

//RealSubject和Proxy实现相同的接口
class RealSubject implements ISubject{
	@Override
	public void Func1() {
		System.out.println("真实对象的方法实现Func1");
	}

	@Override
	public void Func2() {
		System.out.println("真实对象的方法实现Func2");
	}

	@Override
	public void Func3() {
		System.out.println("真实对象的方法实现Func3");
	}
}

//Proxy的接口实现中，实际调用的是RealSubject的对应方法。
class Proxy implements ISubject{
	RealSubject realSubject;
	public Proxy(RealSubject realSubject) {
		this.realSubject = realSubject;
	}
	public void Func1() {
		if(realSubject != null) realSubject.Func1();
	}
	public void Func2() {
		if(realSubject != null) realSubject.Func2();
	}
	public void Func3() {
		if(realSubject != null ) realSubject.Func3();
	}
}

public class ProxyTemplate {
	public static void main(String[] args) {
		Proxy proxy = new Proxy(new RealSubject());
		proxy.Func1(); //真实对象的方法实现Func1
		proxy.Func2(); //真实对象的方法实现Func2
		proxy.Func3(); //真实对象的方法实现Func3
	}
}
