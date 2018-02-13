package visitor;

/* 总结:
 * 访问者模式适用于数据结构相对稳定的系统，它把数据结构和作用于结构之上的操作之间的耦合
 * 解脱开， 使得操作集合可以相对自由地演化。访问者模式是要把对数据的处理从数据结构分离出来，
 * 很多系统可以按照数据结构和算法分开，如果这样的系统有比较稳定的数据结构，又有易于变化的算法，
 * 使用访问者模式就是比较合适的，因为访问者模式使得算法操作的增加变得容易，增加新的操作就是增加
 * 新的访问者类。
 * 所以其优点是增加新的操作很容易，缺点是增加新的数据结构很难，因为这需要改变所有的操作类，为
 * 新增的数据结构增加相应的操作方法。
 */

//抽象元素，定义了一个accept(Visitor visitor)方法以一个Visitor作为参数
abstract class Element{
	public abstract void accept(Visitor visitor);
}

//具体元素A
class ConcreteElementA extends Element{
	public void accept(Visitor visitor) {
		visitor.VisitConcreteElementA(this);
	}
	public void OperationA() {} //其他的相关方法
}

//具体元素B
class ConcreteElementB extends Element{
	public void accept(Visitor visitor) {
		visitor.VisitConcreteElementB(this);
	}
	public void OperationB() {} //其他的相关方法
}


//抽象访问者，定义了访问各个具体元素的相关方法,这些方法以要访问的具体元素做为参数
abstract class Visitor{
	public abstract void VisitConcreteElementA(ConcreteElementA elementA);
	public abstract void VisitConcreteElementB(ConcreteElementB elementB);
}

//具体访问者，需要实现抽象访问者中定义的抽象方法
class ConcreteVisitor1 extends Visitor{
	public void VisitConcreteElementA(ConcreteElementA elementA) {
		System.out.println("具体访问者1访问了具体元素A");
	}
	public void VisitConcreteElementB(ConcreteElementB elementB) {
		System.out.println("具体访问者1访问了具体元素B");
	}
}

class ConcreteVisitor2 extends Visitor{
	public void VisitConcreteElementA(ConcreteElementA elementA) {
		System.out.println("具体访问者2访问了具体元素A");
	}
	public void VisitConcreteElementB(ConcreteElementB elementB) {
		System.out.println("具体访问者2访问了具体元素B");
	}
}

class ConcreteVisitor3 extends Visitor{
	public void VisitConcreteElementA(ConcreteElementA elementA) {
		System.out.println("具体访问者3访问了具体元素A");
	}
	public void VisitConcreteElementB(ConcreteElementB elementB) {
		System.out.println("具体访问者3访问了具体元素B");
	}
}


public class VisitorTemplate {
	public static void main(String[] args) {
		ConcreteElementA elemA = new ConcreteElementA();
		ConcreteElementB elemB = new ConcreteElementB();
		
		Visitor v1 = new ConcreteVisitor1();
		Visitor v2 = new ConcreteVisitor2();
		Visitor v3 = new ConcreteVisitor3();
		//下面的重复代码可以通过将Visitor或者ConcreteElement放到容器中用循环语句消除
		elemA.accept(v1);
		elemA.accept(v2);
		elemA.accept(v3);
//		具体访问者1访问了具体元素A
//		具体访问者2访问了具体元素A
//		具体访问者3访问了具体元素A
		elemB.accept(v1);
		elemB.accept(v2);
		elemB.accept(v3);
//		具体访问者1访问了具体元素B
//		具体访问者2访问了具体元素B
//		具体访问者3访问了具体元素B
	}
}
