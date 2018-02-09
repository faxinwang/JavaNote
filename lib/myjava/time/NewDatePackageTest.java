package myjava.time;
import java.time.*;
import static java.lang.System.*;

public class NewDatePackageTest {
	public static void testClock(){
		out.println("testClock: ");
		//��ȡ��ǰClock
		Clock clock = Clock.systemUTC();
		//ͨ��clock��ȡ��ǰʱ��
		out.println("��ǰʱ��Ϊ:" + clock.instant());
		//��ȡclock��Ӧ�ĺ�����,��System.currentTimeMillis()�����ͬ
		out.println(clock.millis());
		out.println(System.currentTimeMillis());
	}
	public static void testDuration(){
		out.println("testDuration: ");
		Duration d = Duration.ofSeconds(6666);
		out.println("6666���൱�� " + d.toMinutes() + " ��");
		out.println("6666���൱�� " + d.toHours() + " Сʱ");
		out.println("6666���൱�� " + d.toDays() + " ��");
		Clock clock = Clock.systemUTC();
		//��clock����������6666��(100����),�����µ�Instance
		Clock clock2 = Clock.offset(clock, d);
		out.println("��ǰʱ�̼�6666��Ϊ:" + clock2.instant());
	}
	public static void testInstant(){
		out.println("testInstant: ");
		Instant instant = Instant.now();
		out.println(instant);
		//���6666s,�����µ�instant
		Instant instant2 = instant.plusSeconds(6666);
		out.println(instant2);
		//�����ַ�������Instant����
		Instant instant3 = Instant.parse("2016-07-23T19:20:30.123z");
		out.println(instant3);
		//��instant3�Ļ���������5Сʱ4����
		Instant instant4 = instant3.plus(Duration.ofHours(5).plusMinutes(4));
		out.println(instant4);
		//��ȡinstant4������ǰ��ʱ��
		Instant instant5 = instant4.minus(Duration.ofDays(5));
		out.println(instant5);
	}
	public static void testLocalDate(){
		out.println("testLocalDate: ");
		LocalDate localDate = LocalDate.now();
		out.println(localDate);
		//��ȡ2015��ĵ�146��
		localDate = LocalDate.ofYearDay(2015, 146);
		out.println(localDate);//2015-05-26
		//����Ϊ2015-5-21
		localDate = LocalDate.of(2015, Month.MAY, 21);
		out.println(localDate);
	}
	public static void testLocalTime(){
		out.println("testLocalTime: ");
		LocalTime localTime = LocalTime.now();
		out.println(localTime);
		//��Ϊ22:33
		localTime = LocalTime.of(22, 33);
		out.println(localTime);
		//����һ���еĵ�5503��
		out.println(LocalTime.ofSecondOfDay(5503)); //01:31:43
	}
	public static void testLocalDateTime(){
		out.println("testLocakDateTime: ");
		//��ȡ��ǰ����ʱ��
		LocalDateTime localDateTime = LocalDateTime.now();
		out.println("��ǰʱ��:" + localDateTime);
		//��ǰʱ�����25Сʱ3����
		LocalDateTime future = localDateTime.plusHours(25).plusMinutes(3);
		out.println("��ǰʱ�����25Сʱ3���Ӻ�:" + future);
	}
	public static void testYear_YearMonth_MonthDay(){
		out.println("testYear_YearMonth_MonthDay:");
		Year year = Year.now();
		out.println("��ǰ���:"+year);
		out.println("�����:" + year.plusYears(5));
		//��ȡYearMonth
		YearMonth  ym = year.atMonth(LocalDate.now().getMonth());
		out.println("��ǰ����:" + ym);
		//��ǰ����+5��-3����
		ym = ym.plusYears(5).minusMonths(3);
		out.println("��ǰ���¼�5���3����:" + ym);
		MonthDay md = MonthDay.now();
		out.println("��ǰ����:" + md);
		//����Ϊ5��23��
		md = md.with(Month.MAY).withDayOfMonth(23);
		out.println("5��23��Ϊ:" + md); 
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
