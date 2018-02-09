package adapter;

/* Player
 *   |---->Forwards
 * 	 |---->Center
 * 	 |---->Guards
 *   |---->Translator(adapter)
 *   
 * ForeignCenter(adaptee)
 */

abstract class Player{
	protected String  name;
	public Player(String name) {
		this.name = name;
	}
	public abstract void attack();
	public abstract void defense();
}


//前锋
class Forwards extends Player{
	public Forwards(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println("前锋 " + name + " 进攻");
	}
	@Override
	public void defense() {
		System.out.println("前锋 " + name + " 防守");
	}
}

//中锋
class Center extends Player{
	public Center(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println("中锋 " + name + " 进攻");
	}
	@Override
	public void defense() {
		System.out.println("中锋 " + name + " 防守");
	}
}

//后卫
class Guards extends Player{
	public Guards(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println("后卫 " + name + " 进攻");
	}
	@Override
	public void defense() {
		System.out.println("后卫 " + name + " 防守");
	}
}

//外籍中锋
class ForeignCenter{
	private String name;
	public void 进攻() {
		System.out.println("外籍中锋 " + name + " 进攻");
	}
	public void 防守() {
		System.out.println("外籍中锋  " + name  + " 防守");
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {return name;}
}


//翻译者
class Translator extends Player{
	private ForeignCenter fc =new ForeignCenter();
	public Translator(String name) {
		super(name);
		fc.setName(name);
	}
	public void attack() {
		fc.进攻();
	}
	public void defense() {
		fc.防守();
	}
}



public class BallAdapter {
	public static void main(String[] args) {
		Player b = new Forwards("巴蒂尔");
		b.attack();
		
		Player m = new Guards("麦克格雷迪");
		m.attack();
		
		//翻译者告诉姚明，教练要你既要进攻，又要防守
		Player ym = new Translator("姚明");
		ym.attack();
		ym.defense();
		
	}
}
