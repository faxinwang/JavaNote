package visitor;

/* �ܽ�:
 * ������ģʽ���������ݽṹ����ȶ���ϵͳ���������ݽṹ�������ڽṹ֮�ϵĲ���֮������
 * ���ѿ��� ʹ�ò������Ͽ���������ɵ��ݻ���������ģʽ��Ҫ�Ѷ����ݵĴ�������ݽṹ���������
 * �ܶ�ϵͳ���԰������ݽṹ���㷨�ֿ������������ϵͳ�бȽ��ȶ������ݽṹ���������ڱ仯���㷨��
 * ʹ�÷�����ģʽ���ǱȽϺ��ʵģ���Ϊ������ģʽʹ���㷨���������ӱ�����ף������µĲ�����������
 * �µķ������ࡣ
 * �������ŵ��������µĲ��������ף�ȱ���������µ����ݽṹ���ѣ���Ϊ����Ҫ�ı����еĲ����࣬Ϊ
 * ���������ݽṹ������Ӧ�Ĳ���������
 */

//����Ԫ�أ�������һ��accept(Visitor visitor)������һ��Visitor��Ϊ����
abstract class Element{
	public abstract void accept(Visitor visitor);
}

//����Ԫ��A
class ConcreteElementA extends Element{
	public void accept(Visitor visitor) {
		visitor.VisitConcreteElementA(this);
	}
	public void OperationA() {} //��������ط���
}

//����Ԫ��B
class ConcreteElementB extends Element{
	public void accept(Visitor visitor) {
		visitor.VisitConcreteElementB(this);
	}
	public void OperationB() {} //��������ط���
}


//��������ߣ������˷��ʸ�������Ԫ�ص���ط���,��Щ������Ҫ���ʵľ���Ԫ����Ϊ����
abstract class Visitor{
	public abstract void VisitConcreteElementA(ConcreteElementA elementA);
	public abstract void VisitConcreteElementB(ConcreteElementB elementB);
}

//��������ߣ���Ҫʵ�ֳ���������ж���ĳ��󷽷�
class ConcreteVisitor1 extends Visitor{
	public void VisitConcreteElementA(ConcreteElementA elementA) {
		System.out.println("���������1�����˾���Ԫ��A");
	}
	public void VisitConcreteElementB(ConcreteElementB elementB) {
		System.out.println("���������1�����˾���Ԫ��B");
	}
}

class ConcreteVisitor2 extends Visitor{
	public void VisitConcreteElementA(ConcreteElementA elementA) {
		System.out.println("���������2�����˾���Ԫ��A");
	}
	public void VisitConcreteElementB(ConcreteElementB elementB) {
		System.out.println("���������2�����˾���Ԫ��B");
	}
}

class ConcreteVisitor3 extends Visitor{
	public void VisitConcreteElementA(ConcreteElementA elementA) {
		System.out.println("���������3�����˾���Ԫ��A");
	}
	public void VisitConcreteElementB(ConcreteElementB elementB) {
		System.out.println("���������3�����˾���Ԫ��B");
	}
}


public class VisitorTemplate {
	public static void main(String[] args) {
		ConcreteElementA elemA = new ConcreteElementA();
		ConcreteElementB elemB = new ConcreteElementB();
		
		Visitor v1 = new ConcreteVisitor1();
		Visitor v2 = new ConcreteVisitor2();
		Visitor v3 = new ConcreteVisitor3();
		//������ظ��������ͨ����Visitor����ConcreteElement�ŵ���������ѭ���������
		elemA.accept(v1);
		elemA.accept(v2);
		elemA.accept(v3);
//		���������1�����˾���Ԫ��A
//		���������2�����˾���Ԫ��A
//		���������3�����˾���Ԫ��A
		elemB.accept(v1);
		elemB.accept(v2);
		elemB.accept(v3);
//		���������1�����˾���Ԫ��B
//		���������2�����˾���Ԫ��B
//		���������3�����˾���Ԫ��B
	}
}
