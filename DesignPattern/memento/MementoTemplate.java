package memento;

/* 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，这样
 * 以后就可以将该对象恢复到原先保存的状态。
 * Originator(发起人):负责创建一个备忘录Memento，用以记录当前时刻它的内部
 * 状态，并可使用备忘录恢复内部状态。Originator根据需要决定Memento存储它的那些
 * 内部状态。
 * Memento(备忘录):负责存储Originator对象的内部状态数据，并可放置Originator
 * 以外的其他对象访问备忘录Memento。备忘录有两个接口，Caretaker只能看到窄接口，它只能
 * 将备忘录传递给其他对象，Originator对想能够看到一个宽接口，允许它访问返回到先前状态所
 * 需要的所有数据。
 * Caretaker(管理者):负责保存好备忘录Memento，不能对备忘录的内容进行操作或检查。
 */

//需要进行保存备忘数据的类
class Originator{
	private String state;
	public void setState(String state) {
		this.state = state;
	}
	public String getState() {return state;}
	//创建一个Memento对象并将要保存的数据传入。
	public Memento createMemento() {
		return new Memento(state); //保存数据
	}
	
	public void setMemento(Memento memento) {
		state = memento.state; //恢复数据
	}
	
	public void show() {
		System.out.println("state: " + state);
	}
}


//保存备忘数据的类
class Memento{
	String state;
	public Memento(String state) {
		this.state = state;
	}
	public String getState() {
		return state;
	}
}

//管理者类
class Caretaker{
	private Memento memento;
	public Memento getMemento() {return memento;}
	public void setMemento(Memento memento) {this.memento = memento;}
}

public class MementoTemplate {
	public static void main(String[] args) {
		Originator originator = new Originator();
		originator.setState("on");
		originator.show();
		
		//备份数据
		Caretaker caretaker = new Caretaker();
		caretaker.setMemento(originator.createMemento());
		
		//改变状态
		originator.setState("off");
		originator.show();
		
		//恢复数据
		originator.setMemento(caretaker.getMemento());
		originator.show();
	}
}
