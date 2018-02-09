package decorator;

/* ����: ����ȸ��װ���ܵ��ӳ��
 * ��ȸÿ������������100m,���ܵ��ӳ��������ȸ�����Լ��ĳ�����ܷ���50m
 */

/* BirdComponent
 * 		|---->Sparrow
 * 		|---->BirdDecorator
 * 					|------>DecorateEleWing
 */

//�������,����fly()�ӿ�
abstract class BirdComponent{
	public abstract int fly();
}

//��ȸ�������,�ܷ���100m
class Sparrow extends BirdComponent{
	private final int Distance = 100;
	public int fly() { return Distance; }
}

//��������װ����
abstract class BirdDecorator extends BirdComponent{
	BirdComponent component;
	public BirdDecorator(BirdComponent component) {
		this.component = component;
	}
}

//��������װ�������̳���BirdDecorator
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
		System.out.println("û�а�װ���ӳ����С����о���:"+bird.fly());
		bird = new DecorateEleWing(bird);
		System.out.println("��װ��1�����ӳ����С����о���:"+bird.fly());
		bird = new DecorateEleWing(bird);
		System.out.println("��װ��2�����ӳ����С����о���:"+bird.fly());
		bird = new DecorateEleWing(bird);
		System.out.println("��װ��3�����ӳ����С����о���:"+bird.fly());
		/*	
			û�а�װ���ӳ����С����о���:100
			��װ��1�����ӳ����С����о���:150
			��װ��2�����ӳ����С����о���:200
			��װ��3�����ӳ����С����о���:250
		 */
	}

}
