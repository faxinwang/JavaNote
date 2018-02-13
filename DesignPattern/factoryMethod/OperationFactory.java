package factoryMethod;

/* ��������ģʽ�Ǽ򵥹���ģʽ�Ľ�һ��������ƹ㣬����ʹ���˶�̬�ԣ���������ģʽ�����˼򵥹�
 * ��ģʽ��װ���󴴽����̵��ŵ㣬�����˿ͻ��˳������Ʒ�������ϣ����ҿ˷�����Υ������ԭ��
 * ��ȱ�㡣��ȱ��������ÿ����һ����Ʒ�� ����Ҫ����һ����Ʒ�������࣬ �����˶���Ŀ�����
 */

/* Operation(������getResult���󷽷�)
 * 		|----->OperationAdd(ʵ����getResult���󷽷�)
 * 		|----->OperationSub(ʵ����getResult���󷽷�)
 * 		|----->OperationMul(ʵ����getResult���󷽷�)
 * 		|----->OperationDiv(ʵ����getResult���󷽷�)
 * 
 * IFactory(������createOperation���󷽷�)
 * 		|----->FactoryAdd(ʵ����createOperation���󷽷�)
 * 		|----->FactorySub(ʵ����createOperation���󷽷�)
 * 		|----->FactoryMul(ʵ����createOperation���󷽷�)
 * 		|----->FactoryDiv(ʵ����createOperation���󷽷�)
 */

abstract class Operation{
	protected double NumA;
	protected double NumB;
	public abstract double getResult() throws Exception;
}

//�ӷ���
class OperationAdd extends Operation{
	public double getResult() {
		return NumA + NumB;
	}
}
//������
class OperationSub extends Operation{
	public double getResult() {
		return NumA - NumB;
	}
}
//�˷�
class OperationMul extends Operation{
	public double getResult() {
		return NumA * NumB; 
	}
}
//����
class OperationDiv extends Operation{
	public double getResult() throws Exception{
		if(Math.abs(NumB) < 1e-10) throw new Exception("divided by zero");
		return NumA / NumB;
	}
}

//�����幤����Ҫʵ�ֵĽӿ�
interface IFactory{
	Operation createOperation();
}

//�ӷ�����
class FactoryAdd implements IFactory{
	public Operation createOperation() {
		return new OperationAdd();
	}
}

//��������
class FactorySub implements IFactory{
	public Operation createOperation() {
		return new OperationSub();
	}
}

//�˷�����
class FactoryMul implements IFactory{
	public Operation createOperation() {
		return new OperationMul();
	}
}

//��������
class FactoryDiv implements IFactory{
	public Operation createOperation() {
		return new OperationDiv();
	}
}


public class OperationFactory{
	//�ͻ��˴���
	public static void main(String[] args) throws Exception {
		IFactory mulFactory = new FactoryMul();
		Operation op = mulFactory.createOperation();
		op.NumA = 10;
		op.NumB = 30;
		System.out.println("10 * 30 = " + op.getResult());
		//10 * 30 = 300.0
	}
}
