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


//ǰ��
class Forwards extends Player{
	public Forwards(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println("ǰ�� " + name + " ����");
	}
	@Override
	public void defense() {
		System.out.println("ǰ�� " + name + " ����");
	}
}

//�з�
class Center extends Player{
	public Center(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println("�з� " + name + " ����");
	}
	@Override
	public void defense() {
		System.out.println("�з� " + name + " ����");
	}
}

//����
class Guards extends Player{
	public Guards(String name) {
		super(name);
	}
	@Override
	public void attack() {
		System.out.println("���� " + name + " ����");
	}
	@Override
	public void defense() {
		System.out.println("���� " + name + " ����");
	}
}

//�⼮�з�
class ForeignCenter{
	private String name;
	public void ����() {
		System.out.println("�⼮�з� " + name + " ����");
	}
	public void ����() {
		System.out.println("�⼮�з�  " + name  + " ����");
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {return name;}
}


//������
class Translator extends Player{
	private ForeignCenter fc =new ForeignCenter();
	public Translator(String name) {
		super(name);
		fc.setName(name);
	}
	public void attack() {
		fc.����();
	}
	public void defense() {
		fc.����();
	}
}



public class BallAdapter {
	public static void main(String[] args) {
		Player b = new Forwards("�͵ٶ�");
		b.attack();
		
		Player m = new Guards("��˸��׵�");
		m.attack();
		
		//�����߸���Ҧ��������Ҫ���Ҫ��������Ҫ����
		Player ym = new Translator("Ҧ��");
		ym.attack();
		ym.defense();
		
	}
}
