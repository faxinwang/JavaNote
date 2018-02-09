package builder;
import java.util.ArrayList;
import java.util.List;

/* 建造者模式(Builder)主要用于创建一些复杂的对象，这些对象内部构建间的建造顺序通常
 * 是稳定的，但对象内部的构建通常面临着复杂的变化。建造者的好处就是使得构建代码与表示代
 * 码分离，由于建造者隐藏了该产品是如何组装的，所以若需要改变一个产品的内部表示，只需要
 * 再定义一个具体的建造者就可以了。所以建造者模式是在当创建复杂对象的算法应该独立与该
 * 对象的组成部分以及他们的装配方式时适用的模式。
 */

/* Product(产品类)
 * 
 * Builder(抽象创建者类,为创建一个Product对象的各部件指定抽象的接口)
 *	  |------>ConcreteBuilderA(实现了Builder的抽象方法) 		
 * 	  |------>ConcreteBuilderB(实现了Builder的抽象方法)
 * 
 * Director(指挥者类，封装产品的创建过程)
 */

/* 生产者模式与模板方法模式有些类似,不同之处是:
 * 1.模板方法模式是将模板方法放在了抽象类中,模板方法的方法体由抽象类的抽象函数构成顶层逻辑，
 * 	 不同的子类实现可以以不同的方式(相同的步骤)完成顶层逻辑的实现。而生产者模式的顶层逻辑是放在了
 * 	独立的Director类中。
 * 2.模板方法模式中没有Product类，也没有Director类，
 * 3.模板方法更适用于完成在细节上有不同实现的事物，但时事物的整体流程是比较固定的，而生产者
 *   模式更适合于生产结构复杂的对象，并且这些对象的各个部件可以有不同的易变化的实现。这些实现
 *   是交给具体的ConcreteBuilder类去完成的，不同的Builder类通常有不同的部件实现。
 */

//产品
class Product{
	List<String> parts = new ArrayList<>();
	//添加产品部件
	public void add(String part) {parts.add(part);}
	//列举所有的产品部件
	public void show() {
		System.out.println("产品部件:");
		for (String part : parts) {
			System.out.println(part);
		}
	}
}

//抽象产品创建者，定义创建产品需要实现的功能或组件
abstract class Builder{
	public abstract void BuildPartA();
	public abstract void BuildPartB();
	public abstract Product getResult();
}

//----多个具体生产者类，可以有不同的实现
class ConcreteBuilder1 extends Builder{
	private  Product product = new Product();
	@Override
	public void BuildPartA() {
		product.add("部件A");
	}
	@Override
	public void BuildPartB() {
		product.add("部件B");
	}
	@Override
	public Product getResult() {
		return product;
	}
}

class ConcreteBuilder2 extends Builder{
	private Product product = new Product();
	@Override
	public void BuildPartA() {
		product.add("部件X");
	}
	@Override
	public void BuildPartB() {
		product.add("部件Y");
	}
	@Override
	public Product getResult() {
		return product;
	}
}

//指挥者类，封装产品的创建过程
class Director{
	public Product Construct(Builder builder) {
		builder.BuildPartA();
		builder.BuildPartB();
		return builder.getResult();
	}
}

public class BuilderTemplate {
	public static void main(String[] args) {
		Director director = new Director();
		Builder b1 = new ConcreteBuilder1();
		Builder b2 = new ConcreteBuilder2();
		
		Product p1 = director.Construct(b1);
		p1.show();
		System.out.println();
		Product p2 = director.Construct(b2);
		p2.show();
	}
}
