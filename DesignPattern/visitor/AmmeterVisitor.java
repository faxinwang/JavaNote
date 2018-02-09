package visitor;

/* IVisitor(定义了visit接口方法)
 *    |----->HomeAmmeterVisitor(实现了visit接口方法)
 *    |----->IndustryAmmeterVisitor(实现了visit接口方法)
 * 
 * 
 * AmmeterElement(定义了accept抽象方法)
 * 		|----->Ammeter(实现了accept抽象方法)
 */

//电表访问者接口,定义了visit接口方法
interface IVisitor{
	//定义visit接口
	double visit(AmmeterElement element);
}

//抽象电表元素
abstract class AmmeterElement{
	private double electricAmount;
	
	public void setElectricAmount(double amount) {
		electricAmount = amount;
	}
	
	public double getElectricAmount() {
		return electricAmount;
	}
	
	//定义accept抽象方法
	public abstract void accept(IVisitor visitor); 
}

//具体访问者,家用电费的计表员
class HomeAmmeterVisitor implements IVisitor{
	//实现接口的visit方法
	public double visit(AmmeterElement ammeter) {
		double charge = 0;
		double unitOne = 0.6,unitTwo = 1.05;
		int basic = 6000;
		double n = ammeter.getElectricAmount();
		if(n <= basic) {
			charge = n * unitOne;
		}else {
			charge = basic * unitOne + (n- basic)*unitTwo;
		}
		return charge;
	}
}

//具体访问者，工业用电费的计表员
class IndustryAmmeterVisitor implements IVisitor{
	//实现接口的visit方法
	public double visit(AmmeterElement ammeter) {
		double charge =0 ;
		double unitOne = 1.52,unitTwo = 2.78;
		int basic = 15000;
		double n = ammeter.getElectricAmount();
		if(n < basic ) {
			charge = n * unitOne;
		}else {
			charge = basic * unitOne + (n - basic)*unitTwo;
		}
		return charge;
	}
	
}

//具体元素, 电表
class Ammeter extends AmmeterElement{
	//重写父类的accept抽象方法
	public void accept(IVisitor visitor) {
		double cost = visitor.visit(this);
		System.out.println("当前电表的用户需要交纳电费:" + cost + "元"); 
	}
}


public class AmmeterVisitor {
	public static void main(String[] args) {
		Ammeter ammeter = new Ammeter();
		ammeter.setElectricAmount(4567);
		
		IVisitor homeVisitor = new HomeAmmeterVisitor();
		ammeter.accept(homeVisitor); //当前电表的用户需要交纳电费:2740.2元

		IVisitor industryVisitor = new IndustryAmmeterVisitor();
		ammeter.accept(industryVisitor); //当前电表的用户需要交纳电费:6941.84元
	}
}
