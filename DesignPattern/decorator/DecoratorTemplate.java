package decorator;

/* Component�Ƕ�����һ������ӿڣ����Ը���Щ����̬�����ְ�� ConcreteComponent�Ƕ�����һ�������
 * ����Ҳ���Ը�����������һЩְ��Decorator�ǳ����࣬�̳���Component,����������չComponent���
 * ���ܣ����Ƕ���Component��˵��������֪��Decorator�Ĵ��ڵġ�����ConcreteDecorator���Ǿ����װ��
 * �����𵽸�Component���ְ��Ĺ��ܡ�
 * 
 * װ��ģʽ���ŵ㣺
 * 	1.�����е�װ�ι��ܴ����а��Ƴ�ȥ���������Լ�ԭ�е��ࡣ
 *  2.��Ч�ذ���ĺ���ְ���װ�ι������ֿ��ˣ����ҿ���ȥ����������ظ���װ���߼���
 */

//Component��
abstract class Component{
	public abstract void Operation();
}

//ConcreteCompoent��
class ConcreteComponent extends Component{
	@Override
	public void Operation() {
		System.out.println("�������Ĳ���");
	}
}

//Decorator��
abstract class Decorator extends Component{
	protected Component component;
	//����component
	public Decorator(Component component) {
		this.component = component;
	}
	
	//��дOperation,ʵ��ִ�е���component��Operation����
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
	//����Ķ��й��ܣ������������ľ���װ����
	public void Operation() {
		//��������ԭcomponent��operation����ִ�б���Ĺ��ܣ���addedBehavior�������൱�ڶ�
		//ԭcomponent������װ��
		component.Operation();
		addedBehavior();
		System.out.println("����װ�ζ���A�Ĳ���!");
	}
	private void addedBehavior() {
		System.out.println("����װ�ζ���A���еĲ���!");
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
		System.out.println("����װ�ζ���B�Ĳ���!");
	}
	private void addedBehavior() {
		System.out.println("����װ�ζ���B���еĲ���!");
	}
}


public class DecoratorTemplate {
	
	public static void main(String[] args) {
		Component component = new ConcreteDecoratorB(
				new ConcreteDecoratorA(new ConcreteComponent()) );
		component.Operation();
	}
}
