package templateMethod;


/* 总结:
 * 1.模板方法模式是通过把不变行为搬移到父类，去除子类中的重复代码来体现它的优势。
 * 2.当我们要完成在某一细节层次一致的一个过程或一系列步骤，但其中个别步骤在更详
 *   细的的层次上的实现可能不同时，通常考虑用模板方法模式来处理。
 */


/*
 * 定义一个抽象类，其实也就是抽象模板，定义并实现了一个模板方法，这个模板方法一般是一个具体的方法，
 * 它给出了一个顶级逻辑的骨架，而逻辑的组成步骤在相应的抽象操作中，推迟到子类实现，顶级逻辑也有可能
 * 调用一些具体方法。
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
 * ConcreteClass,实现父类所定义的一个或多个抽象方法。每一个AbstractClass都可以有任意多个
 * ConcreteClass与之对应，而每一个ConcreteClass都可以给出这些抽象方法(也就是顶级逻辑的组
 * 成步骤)的不同实现，从而使得顶级逻辑的实现各不相同。
 */
class ConcreteClassA extends AbstractClass{
	@Override
	public void primitiveOperation1() {
		System.out.println("ConcreteClassA的operation1实现");
	}
	
	@Override
	public void primitiveOperation2() {
		System.out.println("ConcreteClassA的operation2实现");
	}
}

class ConcreteClassB extends AbstractClass{
	@Override
	public void primitiveOperation1() {
		System.out.println("ConcreteClassB的operation1实现");
	}
	
	@Override
	public void primitiveOperation2() {
		System.out.println("ConcreteClassB的operation2实现");
	}
}

public class TemplateMethodTemplate {
	public static void main(String[]  args) {
		AbstractClass ac;
		ac = new ConcreteClassA();
		ac.templateMethod();
//		ConcreteClassA的operation1实现
//		ConcreteClassA的operation2实现
		ac = new ConcreteClassB();
		ac.templateMethod();
//		ConcreteClassB的operation1实现
//		ConcreteClassB的operation2实现
	}
}

