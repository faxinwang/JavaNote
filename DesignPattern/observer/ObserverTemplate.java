package observer;

import java.util.*;

/* �۲���ģʽ������һ��һ�Զ��������ϵ�� �ö��˹۲��߶���ͬʱ����ĳһ���������
 * ��������������״̬�����仯ʱ����֪ͨ���й۲��߶���ʹ�����ܹ��Զ������Լ���
 * ��һ������ĸı���Ҫͬʱ�ı������Ķ��󣬶�������֪���ж��ٶ����д��ı�ʱ��
 * Ӧ�ÿ���ʹ�ù۲���ģʽ���ܵ���˵���۲���ģʽ�����Ĺ�����ʵ�����ڽ����ϣ���
 * ��ϵ�˫�������ڳ��󣬶����������ھ��壬�Ӷ�ʹ�ø��Եı�Ų���Ӱ�쵽��һ�ߵ�
 * �仯��
 */

//�����࣬����֪ͨ��
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

//�۲��߽ӿڣ�������ͳһ��update�ӿڡ�
interface Observer{
	public abstract void update();
}

//���������ࡣ
class ConcreteSubject extends Subject{
	private String state;//���۲��ߵ�״̬
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
}

//����۲����ࡣ
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
		System.out.println("�۲���" + name + "����״����"+subjectState);
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
		//�۲���x����״����abc
		//�۲���y����״����abc	
		//�۲���z����״����abc
	}
}

