package state;

/* ״̬ģʽ(State):
 * ��һ�����������״̬�ı�ʱ����ı�����Ϊ����������������Ǹı������ࡣ
 * ״̬ģ����Ҫ������ǵ�����һ������״̬ת�Ƶ��������ʽ���ڸ��ӵ������
 * ��״̬���ж��߼�ת�Ƶ���ʾ��ͬ״̬��һϵ���൱�У����԰Ѹ��ӵ��ж��߼��򻯡�
 * ״̬ģʽ�ĺô��ǽ����ض�״̬��ص���Ϊ�ֲ��������ҽ���ͬ״̬ ����Ϊ�ָ����
 * ����������״̬��صĴ��붼������ĳһ��ConcreteState���У�����ͨ������
 * �µ�������Ժ����׵������µ�״̬��ת���߼���
 * ��һ���������Ϊȡ��������״̬������������������ʱ�̸���״̬�ı�������Ϊʱ��
 * �Ϳ��Կ���ʹ��״̬ģʽ�ˡ�
 */

//����״̬�࣬����Handle���󷽷�
abstract class State{
	public abstract void Handle(Context context);
}

class Context{
	private State state;
	public Context(State state) {
		this.state = state;  //����Context�ĳ�ʼ״̬
	}
	public State getState() {return state;}
	public void setState(State state) {
		this.state = state;
		System.out.println("��ǰ״̬:" + state.getClass());
	}
	
	public void Request() {
		state.Handle(this);  //����������������������һ״̬
	}
}

//����״̬��
class ConcreteStateA extends State{
	public void Handle(Context context) {
		context.setState(new ConcreteStateB()); //״̬ת���߼�������ʵ��
	}
}

class ConcreteStateB extends State{
	public void Handle(Context context) {
		context.setState(new ConcreteStateC()); //״̬ת���߼�������ʵ��
	}
}

class ConcreteStateC extends State{
	public void Handle(Context context) {
		context.setState(new ConcreteStateA()); //״̬ת���߼�������ʵ��
	}
}


public class StateTemplate {
	public static void main(String[] args) {
		Context context = new Context(new ConcreteStateA());
		context.Request(); //��ǰ״̬:class state.ConcreteStateB
		context.Request(); //��ǰ״̬:class state.ConcreteStateC
		context.Request(); //��ǰ״̬:class state.ConcreteStateA
		context.Request(); //��ǰ״̬:class state.ConcreteStateB
		context.Request(); //��ǰ״̬:class state.ConcreteStateC
		context.Request(); //��ǰ״̬:class state.ConcreteStateA
	}
}
