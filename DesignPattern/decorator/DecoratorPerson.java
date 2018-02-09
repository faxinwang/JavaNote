package decorator;

/* 该案例来自《大话设计模式》并略做了修改
 * 需求:实现给Person类的对象以任意顺序添加任意装饰的功能。
 * 如果要增加其他的装饰，只需增加相应的装饰类就可以了。
 */
/* PersonComponent
 * 		|----->Person
 *		|----->PersonDecorator
 *					|----->DecorateHat
 *					|----->DecorateTshirt
 *					|----->DecorateTrouser
 *					|----->DecorateShose
 */

//抽象组件，定义了Show()接口函数，该函数是要被装饰的函数
abstract class PersonComponent{
	protected String name;
	public abstract void Show();
}

//具体组件(被装饰的对象)
class Person extends PersonComponent{
	public Person(String name) {
		this.name = name;
	}
	public void Show() {
		System.out.println("装扮的" + name);
	}
}

//抽象装饰器, 持有一个PersonComponent对象的引用，对其进行装饰
abstract class PersonDecorator extends PersonComponent{
	protected PersonComponent component;
	public PersonDecorator(PersonComponent component) {
		this.component = component;
	}
}

//添加帽子的具体装饰类
class DecorateHat extends PersonDecorator{
	public DecorateHat(PersonComponent component) {
		super(component);
	}
	
	private void decorateHat() {
		System.out.print("帅气的帽子 ");
	}
	
	public void Show() {
		decorateHat();
		component.Show();
	}
}

//添加T恤的具体装饰类
class DecorateTshirt extends PersonDecorator{
	public DecorateTshirt(PersonComponent component) {
		super(component);
	}
	
	private void decorateTShirt() {
		System.out.print("时尚的T恤  ");
	}
	public void Show() {
		decorateTShirt();
		component.Show();
	}
}

//添加裤子的具体装饰类
class DecorateTrouser extends PersonDecorator{
	public DecorateTrouser(PersonComponent component) {
		super(component);
	}
	
	private void decorateTrouser() {
		System.out.print("修长的牛仔裤  ");
	}
	public void Show() {
		decorateTrouser();
		component.Show();
	}
}

//添加鞋子的具体装饰类
class DecorateShose extends PersonDecorator{
	public DecorateShose(PersonComponent component) {
		super(component);
	}
	private void decorateShose() {
		System.out.print("修型板鞋  ");
	}
	public void Show() {
		decorateShose();
		component.Show();
	}
}

public class DecoratorPerson {
	public static void main(String[] args) {
		PersonComponent xc = new Person("小菜");
		xc.Show(); //装扮的小菜
		
		xc = new DecorateHat(			//增加帽子装扮
			 new DecorateTshirt(		//增加T恤装扮
			 new DecorateTrouser(		//增加裤子装扮
			 new DecorateShose(xc))));  //增加鞋子装扮
		xc.Show(); //帅气的帽子 时尚的T恤  修长的牛仔裤  修型板鞋  装扮的小菜
	}
}
