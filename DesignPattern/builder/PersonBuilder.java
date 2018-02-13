package builder;

/* 建造者模式
 * Person(产品类)
 * PsBuilder(Person抽象生产类，定义了生产Person需要实现的方法)
 * 		|------>PersonThinBuilder(生产身材较瘦的人)
 *		|------>PersonFatBuilder(生产身材较胖的人)
 * 
 * PersonDirector(指挥者类,封装了Person的生产过程，使用给定的PersonBuilder
 *  安装固定的顺序生产Person,不同的生产者可以生产出不同的Person)
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

//抽象Person创建者
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
		person.setHead("小小的脑袋");
	}

	@Override
	public void BuildBody() {
		person.setBody("瘦瘦的身材");
	}

	@Override
	public void BuildArmLeft() {
		person.setArmLeft("细细的左手臂");
	}

	@Override
	public void BuildArmRight() {
		person.setArmRight("细细的右手臂");
	}

	@Override
	public void BuildLegLeft() {
		person.setLegLeft("修长的左腿");
	}

	@Override
	public void BuildLegRight() {
		person.setLegRight("修长的右腿");
	}
	
	public Person getResult() {
		return person;
	}
}

class PersonFatBuilder extends PsBuilder{
	private Person person = new Person();
	@Override
	public void BuildHead() {
		person.setHead("大大的脑袋");
	}

	@Override
	public void BuildBody() {
		person.setBody("肥肥的身材");
	}

	@Override
	public void BuildArmLeft() {
		person.setArmLeft("肉肉的左手臂");
	}

	@Override
	public void BuildArmRight() {
		person.setArmRight("肉肉的右手臂");
	}

	@Override
	public void BuildLegLeft() {
		person.setLegLeft("粗粗的左腿");
	}

	@Override
	public void BuildLegRight() {
		person.setLegRight("粗粗的右腿");
	}
	
	public Person getResult() {
		return person;
	}
}

//创建指挥者按照既定的步骤用不同的生产者生产出不同的产品对象
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
		System.out.println("瘦子");
		Person thin= director.Construct();
		thin.show();
		
//		 瘦子
//		小小的脑袋
//		瘦瘦的身材
//		细细的左手臂
//		细细的右手臂
//		修长的左腿
//		修长的右腿
		System.out.println("\n胖子");
		director.setBuilder(new PersonFatBuilder());
		Person fat = director.Construct();
		fat.show();
//		胖子
//		大大的脑袋
//		肥肥的身材
//		肉肉的左手臂
//		肉肉的右手臂
//		粗粗的左腿
//		粗粗的右腿
	}
}

