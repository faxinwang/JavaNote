package strategy;

/* 以下笔记来自《大话设计模式》
 * 策略模式是一种定义一系列算法的方法，从概念上来看，所有这些算法完成的都是相同的工作， 这是实现不同，
 * 他们可以以相同的方式调用所有的算法，减少了各种算法类与使用算法类之间的耦合。
 * 
 * 策略模式就是用来封装算法的，但是在实践中，可以用它来封装几乎任何类型的规则，主要在分析过程中听到需要在
 * 不同时间应用不同的业务规则，就可以考虑使用策略模式处理这种变化的可能性。
 * 
 * 策略模式的Strategy类层次为Context定义了一系列的可供重用的算法或行为。继承有助于析取出这些
 * 算法中的公共功能
 * 
 * 策略模式的优点是简化了单元测试，因为每个算法都有自己的类，可以通过对自己的接口单独测试。
 */

//Strategy类，定义所有支持的算法的公共接口
abstract class Strategy{
	public abstract void AlgorithmInterface();
}

//ConcreteStrategy, 封装了具体的算法或行为，继承于Strategy
//具体算法A
class ConcreteStrategyA extends Strategy{
	@Override
	public void AlgorithmInterface() {
		System.out.println("算法A的实现");
	}
}

//具体算法B
class ConcreteStrategyB extends Strategy{
	@Override
	public void AlgorithmInterface() {
		System.out.println("算法B的实现");
	}
}

//具体算法C
class ConcreteStrategyC extends Strategy{
	@Override
	public void AlgorithmInterface() {
		System.out.println("算法C的实现");
	}
}

//Context, 用一个ConcreteStrategy来配置，维护一个对Strategy对象的引用
class Context{
	Strategy strategy;
	//初始化时，传入具体的对略对象
	public Context(Strategy strategy) {
		this.strategy = strategy;
	}
	//上下文接口，根据具体的策略对象，调用其算法的方法
	public void contextInterface() {
		strategy.AlgorithmInterface();
	}
}

//客户端代码
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


