package simpleFactory;


/* 该案例来自《大话设计模式》
 * 这是简单工厂模式的一个实例，各运算类继承自Operation抽象类并实现了其getResult()方法，
 * 优点：
 * 1.使得各运算与其他运算相互分离，运算代码也与客户端的使用代码相分离，实现了松耦合。
 * 2.扩展起来也很方便，如果要增加其他的运算类比如开方等， 只需要继承自Operation并实现其抽象方法，
 * 	  然后在OperationFactory工厂的createOperate()方法中增加switch分支就可以了
 * */

/* Operation
 * 		|---->OperatinAdd
 * 		|---->OperatinSub
 * 		|---->OperatinMul
 * 		|---->OperatinDiv
 */

//抽象运算类
abstract class Operation{
	protected double _numA;
	protected double _numB;
	
	public void setNumA(double a) { _numA = a;}
	
	public double getNumA() {return _numA;}
	
	public void setNumB(double b) {	_numB = b;}
	
	public double getNumB() {return _numB;}
	
	//抽象方法用于获取不同运算的结果
	public abstract double getResult() throws Exception;
}

//加法运算类
class OperationAdd extends Operation{
	public double getResult() {
		return _numA + _numB;
	}
}

//减法运算类
class OperationSub extends Operation{
	public double getResult() {
		return _numA - _numB;
	}
}

//乘法运算类
class OperationMul extends Operation{
	public double getResult() {
		return _numA * _numB;
	}
}

//除法运算类
class OperationDiv extends Operation{
	public double getResult() throws Exception {
		if(Math.abs(_numB) < 1e-10) {
			throw new Exception("div by zero!");
		}
		return _numA / _numB;
	}
}

//简单运算工厂类
public class OperationFactory {
	
	public static Operation createOperate(String operator) {
		switch(operator) {
		case "+": return new OperationAdd();
		case "-": return new OperationSub();
		case "*": return new OperationMul();
		case "/": return new OperationDiv(); 
		default: return null;
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		Operation op = OperationFactory.createOperate("*");
		op.setNumA(2);
		op.setNumB(10);
		if(op != null) {
			double result = op.getResult();
			System.out.println(result);			
		}
	}
}
