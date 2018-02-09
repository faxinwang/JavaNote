package aboutEnum;

import java.util.Arrays;

/*1.枚举类可以实现一个或多个接口，使用enum定义的枚举类都默认继承了java.lang.Enum类，
 *  而不是Object类，因此枚举类不能显示继承其他父类。
 *2.使用enum定义非抽象的枚举值时会自动使用final修饰，因此枚举类不能派生子类。
 *3.枚举类的构造器只能用private修饰，如果省略了会自动成为private
 *4.枚举类的所有实例必须在枚举类的第一行显示列出，否则这个枚举类永远都不能再产生实例。
 *	列出这些枚举值时，系统会自动添加public static final修饰.
 * */

enum SeasonEnum{
	SPRING,SUMMER,FALL,WINTER;//public static final修饰
}

public class EnumTest {
	
	public static void judge(SeasonEnum s){
		//switch语句里的表达式可以是枚举值
		switch(s){
		case SPRING:
			System.out.println("春暖花开,正好踏青!");
			break;
		case SUMMER:
			System.out.println("夏日炎炎,适合游泳!");
			break;
		case FALL:
			System.out.println("秋高气爽,进补及时");
			break;
		case WINTER:
			System.out.println("冬雪皑皑,围炉赏雪!");
			break;
		}
	}
	
	
	public static void main(String[] args){
		//枚举类里有一个默认方法，返回该枚举类里的所有实例
		for(SeasonEnum s : SeasonEnum.values()){
			System.out.println(s);
		}
		judge(SeasonEnum.SPRING);
		System.out.println(SeasonEnum.FALL);			//1
		System.out.println(SeasonEnum.FALL.name());		//2  这三条语句都将打印出枚举值的名字"FALL"
		System.out.println(SeasonEnum.FALL.toString());	//3
		System.out.println(Arrays.toString(SeasonEnum.values()));
	}
}
