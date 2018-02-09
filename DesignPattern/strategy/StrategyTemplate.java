package strategy;

/* ���±ʼ����ԡ������ģʽ��
 * ����ģʽ��һ�ֶ���һϵ���㷨�ķ������Ӹ�����������������Щ�㷨��ɵĶ�����ͬ�Ĺ����� ����ʵ�ֲ�ͬ��
 * ���ǿ�������ͬ�ķ�ʽ�������е��㷨�������˸����㷨����ʹ���㷨��֮�����ϡ�
 * 
 * ����ģʽ����������װ�㷨�ģ�������ʵ���У�������������װ�����κ����͵Ĺ�����Ҫ�ڷ���������������Ҫ��
 * ��ͬʱ��Ӧ�ò�ͬ��ҵ����򣬾Ϳ��Կ���ʹ�ò���ģʽ�������ֱ仯�Ŀ����ԡ�
 * 
 * ����ģʽ��Strategy����ΪContext������һϵ�еĿɹ����õ��㷨����Ϊ���̳���������ȡ����Щ
 * �㷨�еĹ�������
 * 
 * ����ģʽ���ŵ��Ǽ��˵�Ԫ���ԣ���Ϊÿ���㷨�����Լ����࣬����ͨ�����Լ��Ľӿڵ������ԡ�
 */

//Strategy�࣬��������֧�ֵ��㷨�Ĺ����ӿ�
abstract class Strategy{
	public abstract void AlgorithmInterface();
}

//ConcreteStrategy, ��װ�˾�����㷨����Ϊ���̳���Strategy
//�����㷨A
class ConcreteStrategyA extends Strategy{
	@Override
	public void AlgorithmInterface() {
		System.out.println("�㷨A��ʵ��");
	}
}

//�����㷨B
class ConcreteStrategyB extends Strategy{
	@Override
	public void AlgorithmInterface() {
		System.out.println("�㷨B��ʵ��");
	}
}

//�����㷨C
class ConcreteStrategyC extends Strategy{
	@Override
	public void AlgorithmInterface() {
		System.out.println("�㷨C��ʵ��");
	}
}

//Context, ��һ��ConcreteStrategy�����ã�ά��һ����Strategy���������
class Context{
	Strategy strategy;
	//��ʼ��ʱ���������Ķ��Զ���
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}
	//�����Ľӿڣ����ݾ���Ĳ��Զ��󣬵������㷨�ķ���
	public void contextInterface() {
		strategy.AlgorithmInterface();
	}
}

//�ͻ��˴���
public class StrategyTemplate {
	public static void main(String[] args) {
		Context context;
		context = new Context(new ConcreteStrategyA());
		context.contextInterface();
		
		context = new Context(new ConcreteStrategyB());
		context.contextInterface();
		
		context = new Context(new ConcreteStrategyC());
		context.contextInterface();
	}
}


