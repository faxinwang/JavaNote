package observer;

import java.util.*;

/* 观察者模式定义了一种一对多的依赖关系， 让多了观察者对象同时监听某一个主题对象。
 * 当这个主题对象在状态发生变化时，会通知所有观察者对象，使他们能够自动更新自己。
 * 当一个对象的改变需要同时改变其他的对象，而且它不知道有多少对象有待改变时，
 * 应该考虑使用观察者模式。总的来说，观察者模式所做的工作其实就是在解除耦合，让
 * 耦合的双方依赖于抽象，而不是依赖于具体，从而使得各自的编号不会影响到另一边的
 * 变化。
 */

//主题类，抽象通知者
abstract class Subject{
	private List<Observer> observers = new ArrayList<>();
	public void attach(Observer observer) {
		observers.add(observer);
	}
	public void detach(Observer observer) {
		observers.remove(observer);
	}
	
	public void Notify() {
		for(Observer o : observers) {
			o.update();
		}
	}
}

//观察者接口，定义了统一的update接口。
interface Observer{
	public abstract void update();
}

//具体主题类。
class ConcreteSubject extends Subject{
	private String state;//被观察者的状态
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
}

//具体观察者类。
class ConcreteObserver implements Observer{
	private String name;
	private String subjectState;
	private ConcreteSubject subject;
	public ConcreteObserver(ConcreteSubject subject,String name) {
		this.subject = subject;
		this.name = name;
	}
	public ConcreteSubject getConcreteSubject() {
		return subject;
	}
	public void setConcreteSubject(ConcreteSubject subject) {
		this.subject = subject;
	}
	public void update() {
		subjectState = subject.getState();
		System.out.println("观察者" + name + "的新状体是"+subjectState);
	}
}

public class ObserverTemplate {
	public static void main(String[] args) {
		ConcreteSubject subject = new ConcreteSubject();
		subject.attach(new ConcreteObserver(subject, "x"));
		subject.attach(new ConcreteObserver(subject, "y"));
		subject.attach(new ConcreteObserver(subject, "z"));
		
		subject.setState("abc");
		subject.Notify();
		//观察者x的新状体是abc
		//观察者y的新状体是abc	
		//观察者z的新状体是abc
	}
}

