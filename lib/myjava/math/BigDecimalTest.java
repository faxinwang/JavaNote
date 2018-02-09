package myjava.math;

import java.math.BigDecimal;

class Arith{
	private static final int DEF_DIV_SCALE = 10;
	public static double add(double v1, double v2){
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 = BigDecimal.valueOf(v2);
		return b1.add(b2).doubleValue();
	}
	public static double sub(double v1, double v2){
		BigDecimal b1 =BigDecimal.valueOf(v1);
		BigDecimal b2 =BigDecimal.valueOf(v2);
		return b1.subtract(b2).doubleValue();
	}
	public static double mul(double v1,double v2){
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 =BigDecimal.valueOf(v2);
		return b1.multiply(b2).doubleValue();
	}
	public static double div(double v1,double v2){
		BigDecimal b1 = BigDecimal.valueOf(v1);
		BigDecimal b2 =BigDecimal.valueOf(v2);
		return b1.divide(b2,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP).doubleValue();
	}	
}

public class BigDecimalTest {
	//在进行浮点运算时，经常会发生精度丢失
	public static void test1(){
		System.out.println("不适用BigDecimal时，发生了精度丢失:");
		System.out.println("0.05 + 0.01 = " + (0.05 + 0.01));
		System.out.println("1.0 - 0.42 = " + (1.0 - 0.42));
		System.out.println("4.015 * 100 = " + (4.015 * 100));
		System.out.printf("123.3 / 100 = " + (123.3 / 100));
	}
	
	public static void test2(){
		System.out.println("下面是使用BigDecimal类进行计算的结果:");
		System.out.println("0.05 + 0.01 = " + Arith.add(0.05 , 0.01));
		System.out.println("1.0 - 0.42 = " + Arith.sub(1.0 , 0.42));
		System.out.println("4.015 * 100 = " + Arith.mul(4.015 , 100));
		System.out.println("123.3 / 100 = "+ Arith.div(123.3 , 100));
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
