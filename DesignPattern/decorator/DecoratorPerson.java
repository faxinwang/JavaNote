package decorator;

/* �ð������ԡ������ģʽ�����������޸�
 * ����:ʵ�ָ�Person��Ķ���������˳���������װ�εĹ��ܡ�
 * ���Ҫ����������װ�Σ�ֻ��������Ӧ��װ����Ϳ����ˡ�
 */
/* PersonComponent
 * 		|----->Person
 *		|----->PersonDecorator
 *					|----->DecorateHat
 *					|----->DecorateTshirt
 *					|----->DecorateTrouser
 *					|----->DecorateShose
 */

//���������������Show()�ӿں������ú�����Ҫ��װ�εĺ���
abstract class PersonComponent{
	protected String name;
	public abstract void Show();
}

//�������(��װ�εĶ���)
class Person extends PersonComponent{
	public Person(String name) {
		this.name = name;
	}
	public void Show() {
		System.out.println("װ���" + name);
	}
}

//����װ����, ����һ��PersonComponent��������ã��������װ��
abstract class PersonDecorator extends PersonComponent{
	protected PersonComponent component;
	public PersonDecorator(PersonComponent component) {
		this.component = component;
	}
}

//���ñ�ӵľ���װ����
class DecorateHat extends PersonDecorator{
	public DecorateHat(PersonComponent component) {
		super(component);
	}
	
	private void decorateHat() {
		System.out.print("˧����ñ�� ");
	}
	
	public void Show() {
		decorateHat();
		component.Show();
	}
}

//���T���ľ���װ����
class DecorateTshirt extends PersonDecorator{
	public DecorateTshirt(PersonComponent component) {
		super(component);
	}
	
	private void decorateTShirt() {
		System.out.print("ʱ�е�T��  ");
	}
	public void Show() {
		decorateTShirt();
		component.Show();
	}
}

//��ӿ��ӵľ���װ����
class DecorateTrouser extends PersonDecorator{
	public DecorateTrouser(PersonComponent component) {
		super(component);
	}
	
	private void decorateTrouser() {
		System.out.print("�޳���ţ�п�  ");
	}
	public void Show() {
		decorateTrouser();
		component.Show();
	}
}

//���Ь�ӵľ���װ����
class DecorateShose extends PersonDecorator{
	public DecorateShose(PersonComponent component) {
		super(component);
	}
	private void decorateShose() {
		System.out.print("���Ͱ�Ь  ");
	}
	public void Show() {
		decorateShose();
		component.Show();
	}
}

public class DecoratorPerson {
	public static void main(String[] args) {
		PersonComponent xc = new Person("С��");
		xc.Show(); //װ���С��
		
		xc = new DecorateHat(			//����ñ��װ��
			 new DecorateTshirt(		//����T��װ��
			 new DecorateTrouser(		//���ӿ���װ��
			 new DecorateShose(xc))));  //����Ь��װ��
		xc.Show(); //˧����ñ�� ʱ�е�T��  �޳���ţ�п�  ���Ͱ�Ь  װ���С��
	}
}
