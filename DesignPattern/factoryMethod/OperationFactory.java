package factoryMethod;

/* 工厂方法模式是简单工厂模式的进一步抽象和推广，由于使用了多态性，工厂方法模式保持了简单工
 * 厂模式封装对象创建过程的优点，降低了客户端程序与产品对象的耦合，而且克服了它违背开闭原则
 * 的缺点。但缺点是由于每增加一个产品， 就需要增加一个产品工厂的类， 增加了额外的开销。
 */

/* Operation(定义了getResult抽象方法)
 * 		|----->OperationAdd(实现了getResult抽象方法)
 * 		|----->OperationSub(实现了getResult抽象方法)
 * 		|----->OperationMul(实现了getResult抽象方法)
 * 		|----->OperationDiv(实现了getResult抽象方法)
 * 
 * IFactory(定义了createOperation抽象方法)
 * 		|----->FactoryAdd(实现了createOperation抽象方法)
 * 		|----->FactorySub(实现了createOperation抽象方法)
 * 		|----->FactoryMul(实现了createOperation抽象方法)
 * 		|----->FactoryDiv(实现了createOperation抽象方法)
 */

abstract class Operation{
	protected double NumA;
	protected double NumB;
	public abstract double getResult() throws Exception;
}

//加法类
class OperationAdd extends Operation{
	public double getResult() {
		return NumA + NumB;
	}
}
//减法类
class OperationSub extends Operation{
	public double getResult() {
		return NumA - NumB;
	}
}
//乘法
class OperationMul extends Operation{
	public double getResult() {
		return NumA * NumB; 
	}
}
//除法
class OperationDiv extends Operation{
	public double getResult() throws Exception{
		if(Math.abs(NumB) < 1e-10) throw new Exception("divided by zero");
		return NumA / NumB;
	}
}

//各具体工厂需要实现的接口
interface IFactory{
	Operation createOperation();
}

//加法工厂
class FactoryAdd implements IFactory{
	public Operation createOperation() {
		return new OperationAdd();
	}
}

//减法工厂
class FactorySub implements IFactory{
	public Operation createOperation() {
		return new OperationSub();
	}
}

//乘法工厂
class FactoryMul implements IFactory{
	public Operation createOperation() {
		return new OperationMul();
	}
}

//除法工厂
class FactoryDiv implements IFactory{
	public Operation createOperation() {
		return new OperationDiv();
	}
}


public class OperationFactory{
	//客户端代码
	public static void main(String[] args) throws Exception {
		IFactory mulFactory = new FactoryMul();
		Operation op = mulFactory.createOperation();
		op.NumA = 10;
		op.NumB = 30;
		System.out.println("10 * 30 = " + op.getResult());
		//10 * 30 = 300.0
	}
}
