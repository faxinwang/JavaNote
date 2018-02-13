package builder;

/* ������ģʽ
 * Person(��Ʒ��)
 * PsBuilder(Person���������࣬����������Person��Ҫʵ�ֵķ���)
 * 		|------>PersonThinBuilder(������Ľ��ݵ���)
 *		|------>PersonFatBuilder(������Ľ��ֵ���)
 * 
 * PersonDirector(ָ������,��װ��Person���������̣�ʹ�ø�����PersonBuilder
 *  ��װ�̶���˳������Person,��ͬ�������߿�����������ͬ��Person)
 */


class Person{
	private String head;
	private String body;
	private String ArmLeft;
	private String ArmRight;
	private String LegLeft;
	private String LegRight;
	
	public void setHead(String head) {
		this.head = head;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setArmLeft(String armLeft) {
		ArmLeft = armLeft;
	}

	public void setArmRight(String armRight) {
		ArmRight = armRight;
	}

	public void setLegLeft(String legLeft) {
		LegLeft = legLeft;
	}

	public void setLegRight(String legRight) {
		LegRight = legRight;
	}

	public void show() {
		System.out.println(head);
		System.out.println(body);
		System.out.println(ArmLeft);
		System.out.println(ArmRight);
		System.out.println(LegLeft);
		System.out.println(LegRight);
	}
}

//����Person������
abstract class PsBuilder{
	public abstract void BuildHead();
	public abstract void BuildBody();
	public abstract void BuildArmLeft();
	public abstract void BuildArmRight();
	public abstract void BuildLegLeft();
	public abstract void BuildLegRight();
	public abstract Person getResult();
}

class PersonThinBuilder extends PsBuilder{
	private Person person = new Person();
	@Override
	public void BuildHead() {
		person.setHead("СС���Դ�");
	}

	@Override
	public void BuildBody() {
		person.setBody("���ݵ����");
	}

	@Override
	public void BuildArmLeft() {
		person.setArmLeft("ϸϸ�����ֱ�");
	}

	@Override
	public void BuildArmRight() {
		person.setArmRight("ϸϸ�����ֱ�");
	}

	@Override
	public void BuildLegLeft() {
		person.setLegLeft("�޳�������");
	}

	@Override
	public void BuildLegRight() {
		person.setLegRight("�޳�������");
	}
	
	public Person getResult() {
		return person;
	}
}

class PersonFatBuilder extends PsBuilder{
	private Person person = new Person();
	@Override
	public void BuildHead() {
		person.setHead("�����Դ�");
	}

	@Override
	public void BuildBody() {
		person.setBody("�ʷʵ����");
	}

	@Override
	public void BuildArmLeft() {
		person.setArmLeft("��������ֱ�");
	}

	@Override
	public void BuildArmRight() {
		person.setArmRight("��������ֱ�");
	}

	@Override
	public void BuildLegLeft() {
		person.setLegLeft("�ִֵ�����");
	}

	@Override
	public void BuildLegRight() {
		person.setLegRight("�ִֵ�����");
	}
	
	public Person getResult() {
		return person;
	}
}

//����ָ���߰��ռȶ��Ĳ����ò�ͬ����������������ͬ�Ĳ�Ʒ����
class PersonDirector{
	private PsBuilder builder;
	public PersonDirector(PsBuilder builder) {
		this.builder = builder;
	}
	public void setBuilder(PsBuilder builder) {this.builder =builder;}
	public PsBuilder getBuilder() {return builder;}
	
	public Person Construct(){
		builder.BuildHead();
		builder.BuildBody();
		builder.BuildArmLeft();
		builder.BuildArmRight();
		builder.BuildLegLeft();
		builder.BuildLegRight();
		return builder.getResult();
	}
}

public class PersonBuilder {
	
	public static void main(String[] args) {
		PersonDirector director = new PersonDirector(new PersonThinBuilder());
		System.out.println("����");
		Person thin= director.Construct();
		thin.show();
		
//		 ����
//		СС���Դ�
//		���ݵ����
//		ϸϸ�����ֱ�
//		ϸϸ�����ֱ�
//		�޳�������
//		�޳�������
		System.out.println("\n����");
		director.setBuilder(new PersonFatBuilder());
		Person fat = director.Construct();
		fat.show();
//		����
//		�����Դ�
//		�ʷʵ����
//		��������ֱ�
//		��������ֱ�
//		�ִֵ�����
//		�ִֵ�����
	}
}

