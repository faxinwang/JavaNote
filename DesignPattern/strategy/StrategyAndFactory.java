package strategy;

//�ֽ��շѳ�����
abstract class CashSuper{
	public abstract double acceptCash(double money);
}

//�����շ�����
class CashNormal extends CashSuper{
	@Override
	public double acceptCash(double money) {
		return money; //�����շѣ�ԭ�۷���
	}
}

//�����շ�����
class CashRebate extends CashSuper{
	private double moneyRebate = 1;
	
	//��ʼ��ʱ�����ۿ��ʣ�����ۣ��ʹ���0.8
	public CashRebate(double moneyRebate) {
		this.moneyRebate = moneyRebate;
	}
	
	@Override
	public double acceptCash(double money) {
		return money * moneyRebate; //���ش��ۺ�ļ�Ǯ
	}
}

//�����շ�����
class CashReture extends CashSuper{
	private double moneyCondition;
	private double moneyReture;
	
	/*
	 * �����շѣ���ʼ��ʱ���봫�뷵�������ͷ���ֵ��������300��100����moneyConditionΪ300��
	 * moneyReturnΪ100
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

//�ֽ��շѹ���
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
		System.out.println("Item1: " + totalPrices +"  ��300��100");
		//Item1: 400.0  ��300��100
		total += totalPrices;
		
		csuper = new CashContext(CashContext.Rebate);
		totalPrices = csuper.getResult(1000);
		System.out.println("Item2: " + totalPrices + "  �����");
		//Item2: 800.0  �����
		total += totalPrices;
		
		System.out.println("�ܼ�: " + total); 
		//�ܼ�: 1200.0
		
	}

}
