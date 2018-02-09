package adapter;

/* 适配器模式(Adapter):
 * 将一个类的接口转换为客户希望的另外一个接口。Adapter模式使得原本由于接口不兼容
 * 而不能一起工作的那些类可以一起工作。 在软件开发中，有时系统的数据和行为都正确， 但
 * 接口不符时，应该考虑使用适配器，目的是使控制范围之外的一个原有对象与某个接口匹配，
 * 适配器模式主要应用与希望复用一些现存的类，但是接口 又与复用环境不一致的情况。
 * 
 * 需要注意的事,如果能事先预防接口不同的问题，不匹配问题就不会发生；在有小的接口不
 * 统一时，及时重构，问题不至于扩大；只有碰到无法改变原有设计和代码的情况，才考虑适配。
 * 事后控制不如事中控制，事中控制不如事前控制。
 */

//这是客户端所期待的接口，目标可以是具体的后抽象的类，也可以是接口。
class Target{
	//如果是C++代码，需要将该方法定义为virtual的
	public void Request() {
		System.out.println("普通请求");
	}
}

//需要适配的类
class Adaptee{
	public void SpecificRequest() {
		System.out.println("特殊请求");
	}
}


//通过在内部包装一个Adaptee对象，把源接口转换为目标接口。
class Adapter extends Target{
	private Adaptee adaptee = new Adaptee();
	@Override
	public void Request() {
		adaptee.SpecificRequest();
	}
}

public class AdapterTemplate {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.Request();
	}
}
