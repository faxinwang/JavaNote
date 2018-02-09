package myjava.lang;

public class MathTest {
	/*------三角运算-----------*/
	static void test1(){
		//将弧度转换为角度
		System.out.println("Math.toDegree(1.57):" + Math.toDegrees(1.57));
		//将角度转换为弧度
		System.out.println("Math.toRadians(90):" + Math.toRadians(90));
		//计算正弦
		System.out.println("Math.sin(pi/3):" + Math.sin(Math.PI/3));
		//计算反正弦,返回的角度范围在0 - pi之间
		System.out.println("Math.asin(0.8):" + Math.asin(0.8));
	}
	/*--------取整运算---------*/
	static void test2(){
		//向下取整
		System.out.println("Math.floor(-1.2):" + Math.floor(-1.2));
		System.out.println("Math.floor(1.2):" + Math.floor(1.2));
		//向上取整
		System.out.println("Math.ceil(-1.2):" + Math.ceil(-1.2));
		System.out.println("Math.ceil(1.2):" + Math.ceil(1.2));
		//四舍五入
		System.out.println("Math.round(-1.2):" + Math.round(-1.2));
		System.out.println("Math.round(1.2):" + Math.round(1.2));
		System.out.println("Math.round(-1.5):" + Math.round(-1.5));
		System.out.println("Math.round(1.5):" + Math.round(1.5));
	}
	/*--------乘方，开方，指数运算---------*/
	static void test3(){
		//计算平方根
		System.out.println("Math.sqrt(2.3):" + Math.sqrt(2.3));
		//计算立方根
		System.out.println("Math.cbrt(9):" + Math.cbrt(9));
		//返回欧拉数e的n次方
		System.out.println("Math.exp(2):" + Math.exp(2));
		//返回sqrt(x2 + y2),没有中间溢出或下溢
		System.out.println("Math.hypot(4,4):" + Math.hypot(4,4));
		//计算乘方
		System.out.println("Math.pow(3,2):" + Math.pow(3,2));
		//计算自然对数
		System.out.println("Math.log(12):" + Math.log(12));
		//计算底数为10的对数
		System.out.println("Math.log10(9):" + Math.log10(9));
	}
	/*--------符号相关运算---------*/
	static void test4(){
		//计算绝对值
		System.out.println("Math.abs(-1.5):" + Math.abs(-1.5));
		//返回第二个参数符号的第一个浮点参数
		System.out.println("Math.coypSign(1.2,-3):" + Math.copySign(1.2,-3));
		//符号函数 参数=0 ,返回0 ; 参数<0, 返回-1.0; 参数>0,返回 1.0;
		System.out.println("Math.signum(-3):" + Math.signum(-3));
		System.out.println("Math.signum(0):" + Math.signum(0));
		System.out.println("Math.signum(3):" + Math.signum(3));
	}
	/*--------大小相关运算---------*/
	static void test5(){
		System.out.println("Math.max(3,6):" + Math.max(3,6));
		System.out.println("Math.min(2,5):" + Math.min(2,5));
		//返回两个参数之间与第一个参数相邻的浮点数
		System.out.println("Math.nextAfter(1.2,1.0):" + Math.nextAfter(1.2,1.0));
		//返回比目标数略大的浮点数
		System.out.println("Math.nextUp(1.2):" + Math.nextUp(1.2));
		//返回[0.0,1.0)之间的一个伪随机数
		System.out.println("Math.random():" + Math.random());
		System.out.println("Math.random():" + Math.random());
		System.out.println("Math.random():" + Math.random());
	}
	
	public static void main(String[] args){
		test1();
		System.out.println();
		test2();
		System.out.println();
		test3();
		System.out.println();
		test4();
		System.out.println();
		test5();
	}
}
