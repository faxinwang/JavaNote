package myjava.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	public static void test1(){
		//创建一个默认的Calendar实例
		Calendar  cal = Calendar.getInstance();
		System.out.println(cal);
		//从Calendar中取出Date对象
		Date dt = cal.getTime();
		System.out.println(dt);
		//通过Date对象获取相应的Calendar对象
		//因为Calendar/GregorianCalendar没有构造函数可以接收Date对象
		//所以必须先获得一个Calendar实例，然后调用其setTime()方法
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dt);
		System.out.println(cal2);
	}
	public static void test2(){
		Calendar cal = Calendar.getInstance();
		System.out.println("当前年:"+cal.get(Calendar.YEAR));
		System.out.println("最大年:"+cal.getActualMaximum(Calendar.YEAR));
		System.out.println("当前月:"+cal.get(Calendar.MONTH));
		System.out.println("最大月:"+cal.getActualMaximum(Calendar.MONTH));
		System.out.println("当前周:"+cal.get(Calendar.WEEK_OF_YEAR));
		System.out.println("最大周:"+cal.getActualMaximum(Calendar.WEEK_OF_YEAR));
		System.out.println("当前日:"+cal.get(Calendar.DAY_OF_YEAR));
		System.out.println("最大日:"+cal.getActualMaximum(Calendar.DAY_OF_YEAR));
		
		System.out.println("当前月:"+cal.get(Calendar.MONTH));
		System.out.println("当月最大日:"+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		System.out.println("当前周:"+cal.get(Calendar.WEEK_OF_MONTH));
		System.out.println("当周最大日:"+cal.getActualMaximum(Calendar.DAY_OF_WEEK));

		
	}
	public static void test3(){
		Calendar cal = Calendar.getInstance();
		//输出当前时间
		System.out.println(cal.getTime());
		cal.set(2003, 4, 31, 12, 32, 23); //2003-5-31
		System.out.println(cal.getTime());
		//前推一年
		cal.add(Calendar.YEAR, -1);		//2002-5-31
		System.out.println(cal.getTime());
		//前推3个月
		cal.add(Calendar.MONTH, -3);	//2002-2-28 会自动更新天数
		System.out.println(cal.getTime());
		
		//add()与roll()的区别:
		//当加上某字段的参数后该字段超过最大时会自动进位，而roll()不会
		cal.add(Calendar.DAY_OF_MONTH, 5);
		System.out.println(cal.getTime());//2002-3-5
		cal.set(2003,7,31,0,0,0);
		cal.roll(Calendar.MONTH, 6);	//2003-2-28会自动更新天数
		System.out.println(cal.getTime());		
	}
	public static void test4(){
		//设置Calendar容错性
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 28);//Year字段+1
		System.out.println(cal.getTime());
		//关闭容错性
		cal.setLenient(false);
		//运行时异常
		cal.set(Calendar.MONTH, 15);
		System.out.println(cal.getTime());
	}
	
	public static void main(String[] args){
		test1();
		test2();
		test3();
		test4();
	}
}
