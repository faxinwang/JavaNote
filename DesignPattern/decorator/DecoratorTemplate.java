package decorator;

/* Component是定义了一个对象接口，可以给这些对象动态得添加职责， ConcreteComponent是定义了一个具体的
 * 对象，也可以给这个对象添加一些职责。Decorator是抽象类，继承了Component,从外类来扩展Component类的
 * 功能，但是对于Component来说，是无需知道Decorator的存在的。至于ConcreteDecorator就是具体的装饰
 * 对象，起到给Component添加职责的功能。
 * 
 * 装饰模式的优点：
 * 	1.把类中的装饰功能从类中搬移出去，这样可以简化原有的类。
 *  2.有效地把类的核心职责和装饰功能区分开了，而且可以去除相关类中重复的装饰逻辑。
 */

//Component类
abstract class Component{
	public abstract void Operation();
}

//ConcreteCompoent类
class ConcreteComponent extends Component{
	@Override
	public void Operation() {
		System.out.println("具体对象的操作");
	}
}

//Decorator类
abstract class Decorator extends Component{
	protected Component component;
	//设置component
	public Decorator(Component component) {
		this.component = component;
	}
	
	//重写Operation,实际执行的是component的Operation方法
	@Override
	public void Operation() {
		if(component != null) {
			component.Operation();
		}
	}
}

//ConcreteDecoratorA
class ConcreteDecoratorA extends Decorator{
	
	public ConcreteDecoratorA(Component component) {
		super(component);
	}
	//本类的独有功能，区别于其他的具体装饰类
	public void Operation() {
		//首先运行原component的operation，再执行本类的功能，如addedBehavior操作，相当于对
		//原component进行了装饰
		component.Operation();
		addedBehavior();
		System.out.println("具体装饰对象A的操作!");
	}
	private void addedBehavior() {
		System.out.println("具体装饰对象A独有的操作!");
	}
}

//ConcreteDecoratorB
class ConcreteDecoratorB  extends Decorator{
	public ConcreteDecoratorB(Component component) {
		super(component);
	}
	
	public void Operation() {
		component.Operation();
		addedBehavior();
		System.out.println("具体装饰对象B的操作!");
	}
	private void addedBehavior() {
		System.out.println("具体装饰对象B独有的操作!");
	}
}


public class DecoratorTemplate {
	
	public static void main(String[] args) {
		Component component = new ConcreteDecoratorB(
				new ConcreteDecoratorA(new ConcreteComponent()) );
		component.Operation();
	}
}
