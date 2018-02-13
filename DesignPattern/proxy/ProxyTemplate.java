package proxy;

/* ����ģʽ����Ӧ�õĳ���:
 * 1.Զ�̴���Ҳ����Ϊһ�������ڲ�ͬ�ĵ�ַ�ռ��ṩ�ֲ�����������������һ����������ڲ�ͬ��ַ�ռ����ʵ��
 * 2.��������Ǹ�����Ҫ���������ܴ�Ķ���ͨ���������ʵ������Ҫ�ܳ�ʱ�����ʵ����
 * 3.��ȫ��������������ʵ�������ʱ��Ȩ�ޡ�һ�����ڶ���Ӧ���в�ͬ�ķ���Ȩ�޵�ʱ��
 * 4.����ָ������ָ��������ʵ����ʱ������������һЩ���顣
 * ����ģʽ��ʵ�����ڷ��ʶ���ʱ����һ���̶ȵļ���ԣ���Ϊ���ּ���ԣ����Ը��Ӷ�����;��
 */

/*  ISubject
 * 		|----->RealSubject
 * 		|----->Proxy
 */

//Subject�࣬������RealSubject��Proxy�Ĺ�ͬ�ӿ�
//���������κ�ʹ��RealSubject�ĵط�������ʹ��Proxy
interface ISubject{
	void Func1();
	void Func2();
	void Func3();
}

//RealSubject��Proxyʵ����ͬ�Ľӿ�
class RealSubject implements ISubject{
	@Override
	public void Func1() {
		System.out.println("��ʵ����ķ���ʵ��Func1");
	}

	@Override
	public void Func2() {
		System.out.println("��ʵ����ķ���ʵ��Func2");
	}

	@Override
	public void Func3() {
		System.out.println("��ʵ����ķ���ʵ��Func3");
	}
}

//Proxy�Ľӿ�ʵ���У�ʵ�ʵ��õ���RealSubject�Ķ�Ӧ������
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
		proxy.Func1(); //��ʵ����ķ���ʵ��Func1
		proxy.Func2(); //��ʵ����ķ���ʵ��Func2
		proxy.Func3(); //��ʵ����ķ���ʵ��Func3
	}
}
