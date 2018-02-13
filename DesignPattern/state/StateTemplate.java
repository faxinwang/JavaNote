package state;

/* 状态模式(State):
 * 当一个对象的内在状态改变时允许改变其行为，这个对象看起来像是改变了其类。
 * 状态模糊主要解决的是当控制一个对象状态转移的条件表达式过于复杂的情况，
 * 把状态的判断逻辑转移到表示不同状态的一系列类当中，可以把复杂的判断逻辑简化。
 * 状态模式的好处是将与特定状态相关的行为局部化，并且将不同状态 的行为分割开来。
 * 这样所有与状态相关的代码都存在于某一个ConcreteState类中，所以通过定义
 * 新的子类可以很容易地增加新的状态和转移逻辑。
 * 当一个对象的行为取决于它的状态，并且它必须在运行时刻根据状态改变他的行为时，
 * 就可以考虑使用状态模式了。
 */

//抽象状态类，定义Handle抽象方法
abstract class State{
	public abstract void Handle(Context context);
}

class Context{
	private State state;
	public Context(State state) {
		this.state = state;  //定义Context的初始状态
	}
	public State getState() {return state;}
	public void setState(State state) {
		this.state = state;
		System.out.println("当前状态:" + state.getClass());
	}
	
	public void Request() {
		state.Handle(this);  //对请求做出处理，并设置下一状态
	}
}

//具体状态类
class ConcreteStateA extends State{
	public void Handle(Context context) {
		context.setState(new ConcreteStateB()); //状态转移逻辑在这里实现
	}
}

class ConcreteStateB extends State{
	public void Handle(Context context) {
		context.setState(new ConcreteStateC()); //状态转移逻辑在这里实现
	}
}

class ConcreteStateC extends State{
	public void Handle(Context context) {
		context.setState(new ConcreteStateA()); //状态转移逻辑在这里实现
	}
}


public class StateTemplate {
	public static void main(String[] args) {
		Context context = new Context(new ConcreteStateA());
		context.Request(); //当前状态:class state.ConcreteStateB
		context.Request(); //当前状态:class state.ConcreteStateC
		context.Request(); //当前状态:class state.ConcreteStateA
		context.Request(); //当前状态:class state.ConcreteStateB
		context.Request(); //当前状态:class state.ConcreteStateC
		context.Request(); //当前状态:class state.ConcreteStateA
	}
}
