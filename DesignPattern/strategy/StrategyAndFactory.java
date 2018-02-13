package strategy;

//现金收费抽象类
abstract class CashSuper{
	public abstract double acceptCash(double money);
}

//正常收费子类
class CashNormal extends CashSuper{
	@Override
	public double acceptCash(double money) {
		return money; //正常收费，原价返回
	}
}

//打折收费子类
class CashRebate extends CashSuper{
	private double moneyRebate = 1;
	
	//初始化时传入折扣率，如八折，就传入0.8
	public CashRebate(double moneyRebate) {
		this.moneyRebate = moneyRebate;
	}
	
	@Override
	public double acceptCash(double money) {
		return money * moneyRebate; //返回打折后的价钱
	}
}

//返利收费子类
class CashReture extends CashSuper{
	private double moneyCondition;
	private double moneyReture;
	
	/*
	 * 返利收费，初始化时必须传入返利条件和返利值，比如满300返100，则moneyCondition为300，
	 * moneyReturn为100
	 */
	public CashReture(double moneyCondition, double moneyReture) {
		this.moneyCondition = moneyCondition;
		this.moneyReture = moneyReture;
	}
	
	@Override
	public double acceptCash(double money) {
		if(money > moneyCondition) 
			return money - Math.floor(money / moneyCondition) * moneyReture;
		return money;
	}
}

//现金收费工厂
class CashContext{
	enum AcceptType{Normal, Rebate, Return}
	public static final AcceptType Normal = AcceptType.Normal;
	public static final AcceptType Rebate = AcceptType.Rebate;
	public static final AcceptType Return = AcceptType.Return;
	
	CashSuper cs=null;
	public CashContext(AcceptType type) {
		switch(type) {
		case Normal: cs = new CashNormal();
			break;
		case Return: cs = new CashReture(300, 100);
			break;
		case Rebate: cs = new CashRebate(0.8);
			break;
		}
	}
	
	public double getResult(double money) {
		if(cs != null) return cs.acceptCash(money);
		return money;
	}
}

public class StrategyAndFactory {
	
	public static void main(String[] args) {
		double total = 0, totalPrices;
		
		CashContext csuper = new CashContext(CashContext.Return);
		totalPrices = csuper.getResult(500);
		System.out.println("Item1: " + totalPrices +"  满300减100");
		//Item1: 400.0  满300减100
		total += totalPrices;
		
		csuper = new CashContext(CashContext.Rebate);
		totalPrices = csuper.getResult(1000);
		System.out.println("Item2: " + totalPrices + "  打八折");
		//Item2: 800.0  打八折
		total += totalPrices;
		
		System.out.println("总价: " + total); 
		//总价: 1200.0
		
	}

}
