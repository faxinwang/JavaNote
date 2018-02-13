package templateMethod;


/* �ܽ�:
 * 1.ģ�巽��ģʽ��ͨ���Ѳ�����Ϊ���Ƶ����࣬ȥ�������е��ظ������������������ơ�
 * 2.������Ҫ�����ĳһϸ�ڲ��һ�µ�һ�����̻�һϵ�в��裬�����и������ڸ���
 *   ϸ�ĵĲ���ϵ�ʵ�ֿ��ܲ�ͬʱ��ͨ��������ģ�巽��ģʽ������
 */


/*
 * ����һ�������࣬��ʵҲ���ǳ���ģ�壬���岢ʵ����һ��ģ�巽�������ģ�巽��һ����һ������ķ�����
 * ��������һ�������߼��ĹǼܣ����߼�����ɲ�������Ӧ�ĳ�������У��Ƴٵ�����ʵ�֣������߼�Ҳ�п���
 * ����һЩ���巽����
 */
abstract class AbstractClass{
	public abstract void primitiveOperation1();
	public abstract void primitiveOperation2();
	public void templateMethod() {
		primitiveOperation1();
		primitiveOperation2();
//		System.out.println("");
	}
}

/* 
 * ConcreteClass,ʵ�ָ����������һ���������󷽷���ÿһ��AbstractClass��������������
 * ConcreteClass��֮��Ӧ����ÿһ��ConcreteClass�����Ը�����Щ���󷽷�(Ҳ���Ƕ����߼�����
 * �ɲ���)�Ĳ�ͬʵ�֣��Ӷ�ʹ�ö����߼���ʵ�ָ�����ͬ��
 */
class ConcreteClassA extends AbstractClass{
	@Override
	public void primitiveOperation1() {
		System.out.println("ConcreteClassA��operation1ʵ��");
	}
	
	@Override
	public void primitiveOperation2() {
		System.out.println("ConcreteClassA��operation2ʵ��");
	}
}

class ConcreteClassB extends AbstractClass{
	@Override
	public void primitiveOperation1() {
		System.out.println("ConcreteClassB��operation1ʵ��");
	}
	
	@Override
	public void primitiveOperation2() {
		System.out.println("ConcreteClassB��operation2ʵ��");
	}
}

public class TemplateMethodTemplate {
	public static void main(String[]  args) {
		AbstractClass ac;
		ac = new ConcreteClassA();
		ac.templateMethod();
//		ConcreteClassA��operation1ʵ��
//		ConcreteClassA��operation2ʵ��
		ac = new ConcreteClassB();
		ac.templateMethod();
//		ConcreteClassB��operation1ʵ��
//		ConcreteClassB��operation2ʵ��
	}
}

