package myjava.time;
import java.time.*;
import static java.lang.System.*;

public class NewDatePackageTest {
	public static void testClock(){
		out.println("testClock: ");
		//获取当前Clock
		Clock clock = Clock.systemUTC();
		//通过clock获取当前时刻
		out.println("当前时刻为:" + clock.instant());
		//获取clock对应的毫秒数,与System.currentTimeMillis()输出相同
		out.println(clock.millis());
		out.println(System.currentTimeMillis());
	}
	public static void testDuration(){
		out.println("testDuration: ");
		Duration d = Duration.ofSeconds(6666);
		out.println("6666秒相当于 " + d.toMinutes() + " 分");
		out.println("6666秒相当于 " + d.toHours() + " 小时");
		out.println("6666秒相当于 " + d.toDays() + " 天");
		Clock clock = Clock.systemUTC();
		//在clock基础上增加6666秒(100分钟),返回新的Instance
		Clock clock2 = Clock.offset(clock, d);
		out.println("当前时刻加6666秒为:" + clock2.instant());
	}
	public static void testInstant(){
		out.println("testInstant: ");
		Instant instant = Instant.now();
		out.println(instant);
		//添加6666s,返回新的instant
		Instant instant2 = instant.plusSeconds(6666);
		out.println(instant2);
		//根据字符串解析Instant对象
		Instant instant3 = Instant.parse("2016-07-23T19:20:30.123z");
		out.println(instant3);
		//在instant3的基础上增加5小时4分钟
		Instant instant4 = instant3.plus(Duration.ofHours(5).plusMinutes(4));
		out.println(instant4);
		//获取instant4五天以前的时刻
		Instant instant5 = instant4.minus(Duration.ofDays(5));
		out.println(instant5);
	}
	public static void testLocalDate(){
		out.println("testLocalDate: ");
		LocalDate localDate = LocalDate.now();
		out.println(localDate);
		//获取2015年的第146天
		localDate = LocalDate.ofYearDay(2015, 146);
		out.println(localDate);//2015-05-26
		//设置为2015-5-21
		localDate = LocalDate.of(2015, Month.MAY, 21);
		out.println(localDate);
	}
	public static void testLocalTime(){
		out.println("testLocalTime: ");
		LocalTime localTime = LocalTime.now();
		out.println(localTime);
		//设为22:33
		localTime = LocalTime.of(22, 33);
		out.println(localTime);
		//返回一天中的第5503秒
		out.println(LocalTime.ofSecondOfDay(5503)); //01:31:43
	}
	public static void testLocalDateTime(){
		out.println("testLocakDateTime: ");
		//获取当前日期时间
		LocalDateTime localDateTime = LocalDateTime.now();
		out.println("当前时间:" + localDateTime);
		//当前时间加上25小时3分钟
		LocalDateTime future = localDateTime.plusHours(25).plusMinutes(3);
		out.println("当前时间加上25小时3分钟后:" + future);
	}
	public static void testYear_YearMonth_MonthDay(){
		out.println("testYear_YearMonth_MonthDay:");
		Year year = Year.now();
		out.println("当前年份:"+year);
		out.println("五年后:" + year.plusYears(5));
		//获取YearMonth
		YearMonth  ym = year.atMonth(LocalDate.now().getMonth());
		out.println("当前年月:" + ym);
		//当前年月+5年-3个月
		ym = ym.plusYears(5).minusMonths(3);
		out.println("当前年月加5年减3个月:" + ym);
		MonthDay md = MonthDay.now();
		out.println("当前月日:" + md);
		//设置为5月23日
		md = md.with(Month.MAY).withDayOfMonth(23);
		out.println("5月23日为:" + md); 
	}
	
	public static void main(String[] args){
		testClock();
		testDuration();
		testInstant();
		testLocalDate();
		testLocalTime();
		testLocalDateTime();
		testYear_YearMonth_MonthDay();
	}
}
