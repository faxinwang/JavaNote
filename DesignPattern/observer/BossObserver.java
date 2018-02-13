package observer;

import java.util.ArrayList;
import java.util.List;

/* ISubject(����ӿڣ�������)
 * 		|------>Secretary
 * 
 * IObserver(�۲��߽ӿ�)
 * 		|------>EmployeeA
 * 		|------>EmployeeB
 * 
 */

//����ӿ�,�������µ������ӿڷ���
interface ISubject{
	void attach(IObserver observer);
	void detach(IObserver observer);
	void Notify();
}

//�۲��߽ӿڣ�ʵ�ִ˽ӿڵ��඼�ǹ۲���
interface IObserver{
	public abstract void update();
}

//�������⣬������һ�������۲����Ķ���
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

//����۲���A
class EmployeeA implements IObserver{
	String name;
	public EmployeeA(String name) {
		this.name = name;
	}

	public void update() {
		System.out.println("�ϰ�����ˣ�"+name + "��رչ�Ʊ���,��������");
	}
}

//����۲���B
class EmployeeB implements IObserver{
	String name;
	public EmployeeB(String name) {
		this.name = name;
	}

	public void update() {
		System.out.println("�ϰ�����ˣ�"+name + "��ر�NBAֱ��,��������");
	}
}

public class BossObserver {
	public static void main(String[] args) {
		Secretary s = new Secretary();
		IObserver zs = new EmployeeA("����");
		IObserver ls = new EmployeeA("����");
		IObserver ww = new EmployeeB("����");
		
		s.attach(zs);
		s.attach(ls);
		s.attach(ww);
		
		s.setState("�ϰ������");
//		�ϰ�����ˣ�������رչ�Ʊ���,��������
//		�ϰ�����ˣ����Ŀ�رչ�Ʊ���,��������
//		�ϰ�����ˣ������ر�NBAֱ��,��������
	}
}
