package innerClass;

public class Cow {
	private double weight;
	public Cow(){}
	public Cow(double weight){
		this.weight = weight;
	}
	//定义一个非静态内部类
	class CowLeg{
		//非静态内部类的两个实例变量
		private double length;
		private String color;
		public CowLeg(){}
		public CowLeg(double len,String color){
			this.length = len; 
			this.color = color;
		}
		public double getLength() {
			return length;
		}
		public void setLength(double length) {
			this.length = length;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		//非静态内部类的实例方法
		public void info(){
			System.out.println("当前牛腿颜色是:" + color +",高 :" + length);
			//直接访问外部类的private成员变量
			System.out.println("本牛腿所在奶牛重量:" + weight);
			/*上面一行代码中，非静态内部类访问了外部类的私有变量，这是因为在非静态内部类里，它保存了一个它
			 * 所寄生的 "外部类对象"的引用(当调用非静态内部类的实例方法时，必须有一个非静态内部类对象，非静态
			 * 内部类对象必须寄存在外部类实例里)
			 * */
		}
	}
	
	public void test(){
		CowLeg cl = new CowLeg(1.12,"黑白相间");
		cl.info();
	}
	
	public static void main(String[] args){
		Cow cow = new Cow(378.9);
		cow.test();
	}
	
}
