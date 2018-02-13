package facade;

/* 外观模式(Facade)，为子系统中的一组接口提供一个一致的界面，此模式定义了
 * 一个高层接口，这个接口使得这一子系统更加容易使用。
 * 
 * -何时使用外观模式:
 * 首先，在设计阶段初期，应该有意识地将不同的两个层分离，比如经典的三层架构，就需要考虑在数据访问
 * 层或业务逻辑层，业务逻辑层和表示层的层与层之间建立外观模式Facade，这样可以为复杂的子系统提供
 * 一个简单的接口，使得耦合大大降低。
 * 
 * 其次，子系统往往因为不断的重构演化而变得越来越复杂，大多数的模式使用时也都会产出很多很小的类，这
 * 本是好事，但也给外部调用它们的用户程序带来了使用上的困难，增加外观Facade可以提供一个简单的接口，
 * 减少他们之间的依赖。
 * 
 * 第三，在维护一个遗留的大型系统是，可能这个系统已经非常难以维护和扩展了，但因为它包含很多非常重要
 * 的功能，新的需求开发必须要依赖于它。此时使用外观模式Facade也是非常合适的，可以为新系统开发一个
 * Facade外观类，来提供设计粗糙或高度复杂的遗留的代码的比较清晰简单的接口，让新系统与Facade对象
 * 交互，Facade与遗留代码交互所有复杂的工作。
 */


class SubSystemOne{
	public void MethonOne() {
		System.out.println("子系统方法一");
	}
}

class SubSystemTwo{
	public void MethonTwo() {
		System.out.println("子系统方法二");
	}
}

class SubSystemThree{
	public void MethonThree() {
		System.out.println("子系统方法三");
	}
}

class SubSystemFour{
	public void MethonFour() {
		System.out.println("子系统方法四");
	}
}


//外观类，它需要了解所有子系统的方法或属性，进行组合，以备外界调用
public class FacadeTemplate {
	SubSystemOne one;
	SubSystemTwo two;
	SubSystemThree three;
	SubSystemFour four;
	
	public FacadeTemplate() {
		one = new SubSystemOne();
		two = new SubSystemTwo();
		three = new SubSystemThree();
		four = new SubSystemFour();
	}
	
	public void MethodA() {
		System.out.println("方法组A()----");
		one.MethonOne();
		two.MethonTwo();
	}
	
	public void MethodB() {
		System.out.println("方法组B()----");
		three.MethonThree();
		four.MethonFour();
	}
	
	//客户端调用，由于Facade的作用，客户端可以根本不知道三个子系统的存在
	public static void main(String[] args) {
		FacadeTemplate facade = new FacadeTemplate();
		facade.MethodA();
//		方法组A()----
//		子系统方法一
//		子系统方法二
		System.out.println();
		facade.MethodB();
//		方法组B()----
//		子系统方法三
//		子系统方法四
	}
}
