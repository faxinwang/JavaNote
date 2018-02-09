package simpleFactory;


/* �ð������ԡ������ģʽ��
 * ���Ǽ򵥹���ģʽ��һ��ʵ������������̳���Operation�����ಢʵ������getResult()������
 * �ŵ㣺
 * 1.ʹ�ø����������������໥���룬�������Ҳ��ͻ��˵�ʹ�ô�������룬ʵ��������ϡ�
 * 2.��չ����Ҳ�ܷ��㣬���Ҫ������������������翪���ȣ� ֻ��Ҫ�̳���Operation��ʵ������󷽷���
 * 	  Ȼ����OperationFactory������createOperate()����������switch��֧�Ϳ�����
 * */

/* Operation
 * 		|---->OperatinAdd
 * 		|---->OperatinSub
 * 		|---->OperatinMul
 * 		|---->OperatinDiv
 */

//����������
abstract class Operation{
	protected double _numA;
	protected double _numB;
	
	public void setNumA(double a) { _numA = a;}
	
	public double getNumA() {return _numA;}
	
	public void setNumB(double b) {	_numB = b;}
	
	public double getNumB() {return _numB;}
	
	//���󷽷����ڻ�ȡ��ͬ����Ľ��
	public abstract double getResult() throws Exception;
}

//�ӷ�������
class OperationAdd extends Operation{
	public double getResult() {
		return _numA + _numB;
	}
}

//����������
class OperationSub extends Operation{
	public double getResult() {
		return _numA - _numB;
	}
}

//�˷�������
class OperationMul extends Operation{
	public double getResult() {
		return _numA * _numB;
	}
}

//����������
class OperationDiv extends Operation{
	public double getResult() throws Exception {
		if(Math.abs(_numB) < 1e-10) {
			throw new Exception("div by zero!");
		}
		return _numA / _numB;
	}
}

//�����㹤����
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
