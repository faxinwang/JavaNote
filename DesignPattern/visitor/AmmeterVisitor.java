package visitor;

/* IVisitor(������visit�ӿڷ���)
 *    |----->HomeAmmeterVisitor(ʵ����visit�ӿڷ���)
 *    |----->IndustryAmmeterVisitor(ʵ����visit�ӿڷ���)
 * 
 * 
 * AmmeterElement(������accept���󷽷�)
 * 		|----->Ammeter(ʵ����accept���󷽷�)
 */

//�������߽ӿ�,������visit�ӿڷ���
interface IVisitor{
	//����visit�ӿ�
	double visit(AmmeterElement element);
}

//������Ԫ��
abstract class AmmeterElement{
	private double electricAmount;
	
	public void setElectricAmount(double amount) {
		electricAmount = amount;
	}
	
	public double getElectricAmount() {
		return electricAmount;
	}
	
	//����accept���󷽷�
	public abstract void accept(IVisitor visitor); 
}

//���������,���õ�ѵļƱ�Ա
class HomeAmmeterVisitor implements IVisitor{
	//ʵ�ֽӿڵ�visit����
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

//��������ߣ���ҵ�õ�ѵļƱ�Ա
class IndustryAmmeterVisitor implements IVisitor{
	//ʵ�ֽӿڵ�visit����
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

//����Ԫ��, ���
class Ammeter extends AmmeterElement{
	//��д�����accept���󷽷�
	public void accept(IVisitor visitor) {
		double cost = visitor.visit(this);
		System.out.println("��ǰ�����û���Ҫ���ɵ��:" + cost + "Ԫ"); 
	}
}


public class AmmeterVisitor {
	public static void main(String[] args) {
		Ammeter ammeter = new Ammeter();
		ammeter.setElectricAmount(4567);
		
		IVisitor homeVisitor = new HomeAmmeterVisitor();
		ammeter.accept(homeVisitor); //��ǰ�����û���Ҫ���ɵ��:2740.2Ԫ

		IVisitor industryVisitor = new IndustryAmmeterVisitor();
		ammeter.accept(industryVisitor); //��ǰ�����û���Ҫ���ɵ��:6941.84Ԫ
	}
}
