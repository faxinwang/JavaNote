package proxy;

/* IGiveGift
 * 		|---->Pursuit
 * 		|---->PursuitProxy
 * 
 * SchoolGirl
 */

interface IGiveGift{
	void giveDolls();
	void giveFlowers();
	void giveChocolate();
}

//真实的追求者
class Pursuit implements IGiveGift{
	SchoolGirl mm;
	public Pursuit(SchoolGirl mm) {
		this.mm = mm;
	}
	@Override
	public void giveDolls() {
		System.out.println(mm.getName() + " 送你洋娃娃");
	}
	@Override
	public void giveFlowers() {
		System.out.println(mm.getName() + " 送你鲜花");
	}
	@Override
	public void giveChocolate() {
		System.out.println(mm.getName() + " 送你巧克力");
	}
}

//女孩
class SchoolGirl{
	private String name;
	public SchoolGirl(String name) {
		this.name = name;
	}
	public String getName() {return name;}
}

//代理男朋友,持有一个真实追求者的引用,并提供了与真实追求者同样的接口
class ProxyPursuit implements IGiveGift{
	Pursuit gg;
	public ProxyPursuit(SchoolGirl mm){
		gg = new Pursuit(mm);
	}
	public void giveDolls() { gg.giveDolls(); }
	public void giveFlowers() {	gg.giveFlowers(); }
	public void giveChocolate() { gg.giveChocolate();}
}

public class ProxyBoyfriend{
	public static void main(String[] args) {
		ProxyPursuit proxy = new ProxyPursuit(new SchoolGirl("娇娇"));
		proxy.giveDolls(); //娇娇 送你洋娃娃
		proxy.giveFlowers(); //娇娇 送你鲜花
		proxy.giveChocolate(); //娇娇 送你巧克力
	}
}
