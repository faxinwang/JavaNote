package lambda;

interface Eatable{
	void taste();
}
interface Flyable{
	void fly(String weather);
}
interface Addable{
	int add(int a,int b);
}

public class LambdaTest {
	//调用该方法需要Eatable对象
	public void eat(Eatable e){
		System.out.println(e);
		e.taste();
	}
	//调用该方法需要Flyable对象
	public void drive(String weather,Flyable f){
		System.out.println("我正在驾驶: "+ f);
		f.fly(weather);
	}
	//调用该方法需要Addable对象
	public void test(int a,int b,Addable add){
		System.out.printf("%d + %d = %d\n",a,b,add.add(a, b));
	}
	
	public static void main(String[] args){
		LambdaTest lmd = new LambdaTest();
		//lambda表达式的代码块只有一条语句，可以省略花括号
		lmd.eat(()-> System.out.println("苹果的味道不错"));
		
		//labbda表达式的形参列表只有一个参数，可以省略圆括号
		lmd.drive( "多云转晴",(weather) -> {
			System.out.println("今天天气是:" + weather);
			System.out.println("直升机飞行平稳!");
		});
		
		//lambda表达式中只有一条语句，该语句的值将自动成为返回值
		lmd.test(2,5,(a,b)-> a + b);
	}
}
