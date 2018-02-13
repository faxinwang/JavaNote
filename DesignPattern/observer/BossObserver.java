package observer;

import java.util.ArrayList;
import java.util.List;

/* ISubject(主题接口，定义了)
 * 		|------>Secretary
 * 
 * IObserver(观察者接口)
 * 		|------>EmployeeA
 * 		|------>EmployeeB
 * 
 */

//主题接口,具有如下的三个接口方法
interface ISubject{
	void attach(IObserver observer);
	void detach(IObserver observer);
	void Notify();
}

//观察者接口，实现此接口的类都是观察者
interface IObserver{
	public abstract void update();
}

//具体主题，可以有一个或多个观察他的对象
class Secretary implements ISubject{
	private List<IObserver> observers = new ArrayList<>();
	@SuppressWarnings("unused")
	private String state;
	
	public void attach(IObserver observer) {
		observers.add(observer);		
	}
	public void detach(IObserver observer) {
		observers.remove(observer);
	}
	
	public void setState(String state) {
		this.state = state;
		Notify();
	}
	
	public void Notify() {
		for(IObserver o:observers) {
			o.update();
		}
	}
}

//具体观察者A
class EmployeeA implements IObserver{
	String name;
	public EmployeeA(String name) {
		this.name = name;
	}

	public void update() {
		System.out.println("老板回来了，"+name + "快关闭股票软件,继续工作");
	}
}

//具体观察者B
class EmployeeB implements IObserver{
	String name;
	public EmployeeB(String name) {
		this.name = name;
	}

	public void update() {
		System.out.println("老板回来了，"+name + "快关闭NBA直播,继续工作");
	}
}

public class BossObserver {
	public static void main(String[] args) {
		Secretary s = new Secretary();
		IObserver zs = new EmployeeA("张三");
		IObserver ls = new EmployeeA("李四");
		IObserver ww = new EmployeeB("王五");
		
		s.attach(zs);
		s.attach(ls);
		s.attach(ww);
		
		s.setState("老板回来了");
//		老板回来了，张三快关闭股票软件,继续工作
//		老板回来了，李四快关闭股票软件,继续工作
//		老板回来了，王五快关闭NBA直播,继续工作
	}
}
