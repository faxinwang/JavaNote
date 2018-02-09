package myjava.text;

import java.time.LocalDateTime;
import java.time.format.*;

public class DateTimeFormatterTest {
	
	public static void test1(){
		
		DateTimeFormatter[] formatters = new DateTimeFormatter[]{
			//直接使用常量创建DateTimeFormatter对象
			DateTimeFormatter.ISO_LOCAL_DATE,
			DateTimeFormatter.ISO_LOCAL_TIME,
			DateTimeFormatter.ISO_LOCAL_DATE_TIME,
			//使用本地化的不同风格来创建DateTimeFormatter格式器
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM),
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG),
			//根据模式字符串来创建DateTimeFormatter对象
			DateTimeFormatter.ofPattern("Gyyyy//MMM//dd HH:mm:ss")
		};
		LocalDateTime datetime = LocalDateTime.now();
		//一次使用不同的格式器对datetime进行格式化
		for(int i=0;i<formatters.length;++i){
			//下面两行代码效果一样
		//	System.out.println(datetime.format(formatters[i]));
			System.out.println(formatters[i].format(datetime));
		}
	}
	public static void test2(){
		//定义一个任意格式的日期,时间字符串
		String str1 = "2014==04==12 01时06分08秒";
		DateTimeFormatter fmter1 = DateTimeFormatter.ofPattern("yyyy==MM==dd HH时mm分ss秒");
		//将字符串解析成LocalDateTiem对象
		LocalDateTime dt1 = LocalDateTime.parse(str1,fmter1);
		System.out.println(dt1);//输出 2014-04-12T01:06:08
		//再次解析另一个奇葩的字符串
		String str2 = "2014$$$四月$$$13 20小时";
		//模式中的MMM至少要为三个,两个时会发生解析异常
		DateTimeFormatter fmter2 = DateTimeFormatter.ofPattern("yyyy$$$MMM$$$dd HH小时");
		LocalDateTime dt2 = LocalDateTime.parse(str2,fmter2);
		System.out.println(dt2);//输出 2014-04-13T20:00
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
