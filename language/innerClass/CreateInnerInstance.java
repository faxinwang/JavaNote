package innerClass;

public class CreateInnerInstance {
	
	public static void main(String[] args){
		Cow cow = new Cow(123);//创建外部类对象
		Cow.CowLeg leg = cow.new CowLeg(12.6, "黑白相间");//通过外部类对象创建非静态内部类
		leg.info();
		//也可以这样创建内部类对象
		Cow.CowLeg leg2 =new Cow(13.5).new CowLeg(11.4,"黑色");
		leg2.info();
		
		//创建静态内部类的对象
		StaticInnerClass.StaticInClass inner;
		inner = new StaticInnerClass.StaticInClass();
		inner.accessOuterProp();
	}
}
