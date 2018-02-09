package decorator;

/* 需求: 给麻雀安装智能电子翅膀
 * 麻雀每次能连续飞行100m,智能电子翅磅能让麻雀不用自己的翅磅就能飞行50m
 */

/* BirdComponent
 * 		|---->Sparrow
 * 		|---->BirdDecorator
 * 					|------>DecorateEleWing
 */

//抽象组件,定义fly()接口
abstract class BirdComponent{
	public abstract int fly();
}

//麻雀具体组件,能飞行100m
class Sparrow extends BirdComponent{
	private final int Distance = 100;
	public int fly() { return Distance; }
}

//抽象鸟类装饰器
abstract class BirdDecorator extends BirdComponent{
	BirdComponent component;
	public BirdDecorator(BirdComponent component) {
		this.component = component;
	}
}

//具体鸟类装饰器，继承自BirdDecorator
class DecorateEleWing extends BirdDecorator{
	private final int Distance = 50;
	public DecorateEleWing(BirdComponent component) {
		super(component);
	}
	private int eleFly(){
		return Distance;
	}
	public int fly() {
		return component.fly() + eleFly(); 
	}
}


public class DecoratorBird {

	public static void main(String[] args) {
		BirdComponent bird = new Sparrow();
		System.out.println("没有安装电子翅磅的小鸟飞行距离:"+bird.fly());
		bird = new DecorateEleWing(bird);
		System.out.println("安装了1个电子翅磅的小鸟飞行距离:"+bird.fly());
		bird = new DecorateEleWing(bird);
		System.out.println("安装了2个电子翅磅的小鸟飞行距离:"+bird.fly());
		bird = new DecorateEleWing(bird);
		System.out.println("安装了3个电子翅磅的小鸟飞行距离:"+bird.fly());
		/*	
			没有安装电子翅磅的小鸟飞行距离:100
			安装了1个电子翅磅的小鸟飞行距离:150
			安装了2个电子翅磅的小鸟飞行距离:200
			安装了3个电子翅磅的小鸟飞行距离:250
		 */
	}

}
