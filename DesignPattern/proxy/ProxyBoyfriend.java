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

//��ʵ��׷����
class Pursuit implements IGiveGift{
	SchoolGirl mm;
	public Pursuit(SchoolGirl mm) {
		this.mm = mm;
	}
	@Override
	public void giveDolls() {
		System.out.println(mm.getName() + " ����������");
	}
	@Override
	public void giveFlowers() {
		System.out.println(mm.getName() + " �����ʻ�");
	}
	@Override
	public void giveChocolate() {
		System.out.println(mm.getName() + " �����ɿ���");
	}
}

//Ů��
class SchoolGirl{
	private String name;
	public SchoolGirl(String name) {
		this.name = name;
	}
	public String getName() {return name;}
}

//����������,����һ����ʵ׷���ߵ�����,���ṩ������ʵ׷����ͬ���Ľӿ�
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
		ProxyPursuit proxy = new ProxyPursuit(new SchoolGirl("����"));
		proxy.giveDolls(); //���� ����������
		proxy.giveFlowers(); //���� �����ʻ�
		proxy.giveChocolate(); //���� �����ɿ���
	}
}
