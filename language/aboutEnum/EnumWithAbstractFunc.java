package aboutEnum;

/*	枚举类里定义抽象方法是不能使用abstract关键字将枚举类声明为抽象类，
 *  因为系统会自动为它加上abstract关键字。枚举类需要显示创建枚举值，
 *  而不是作为父类，所以定义每个枚举值时必须为抽象方法提供实现，否则将编译出错。
 * 
 * */
enum Operation{
	PLUS{
		public double eval(double x,double y){
			return x + y;
		}
	},
	MINUS{
		public double eval(double x,double y){
			return x - y;
		}
	},
	TIMES{
		public double eval(double x,double y){
			return x * y;
		}
	},
	DIVIDE{
		public double eval(double x,double y){
			return x / y;
		}
	};
	//为枚举类定义一个抽象方法
	//这个抽象方法由不同的枚举值提供不同的实现
	public abstract double eval(double x,double y);
}

public class EnumWithAbstractFunc {
	
	public static void main(String[] args){
		System.out.println(Operation.PLUS.eval(3, 4));
		System.out.println(Operation.MINUS.eval(5, 4));
		System.out.println(Operation.TIMES.eval(3, 4));
		System.out.println(Operation.DIVIDE.eval(10, 4));
	}
}
