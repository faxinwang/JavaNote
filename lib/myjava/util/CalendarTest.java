package myjava.util;

import java.util.Calendar;
import java.util.Date;

public class CalendarTest {
	public static void test1(){
		//����һ��Ĭ�ϵ�Calendarʵ��
		Calendar  cal = Calendar.getInstance();
		System.out.println(cal);
		//��Calendar��ȡ��Date����
		Date dt = cal.getTime();
		System.out.println(dt);
		//ͨ��Date�����ȡ��Ӧ��Calendar����
		//��ΪCalendar/GregorianCalendarû�й��캯�����Խ���Date����
		//���Ա����Ȼ��һ��Calendarʵ����Ȼ�������setTime()����
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(dt);
		System.out.println(cal2);
	}
	public static void test2(){
		Calendar cal = Calendar.getInstance();
		System.out.println("��ǰ��:"+cal.get(Calendar.YEAR));
		System.out.println("�����:"+cal.getActualMaximum(Calendar.YEAR));
		System.out.println("��ǰ��:"+cal.get(Calendar.MONTH));
		System.out.println("�����:"+cal.getActualMaximum(Calendar.MONTH));
		System.out.println("��ǰ��:"+cal.get(Calendar.WEEK_OF_YEAR));
		System.out.println("�����:"+cal.getActualMaximum(Calendar.WEEK_OF_YEAR));
		System.out.println("��ǰ��:"+cal.get(Calendar.DAY_OF_YEAR));
		System.out.println("�����:"+cal.getActualMaximum(Calendar.DAY_OF_YEAR));
		
		System.out.println("��ǰ��:"+cal.get(Calendar.MONTH));
		System.out.println("���������:"+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		System.out.println("��ǰ��:"+cal.get(Calendar.WEEK_OF_MONTH));
		System.out.println("���������:"+cal.getActualMaximum(Calendar.DAY_OF_WEEK));

		
	}
	public static void test3(){
		Calendar cal = Calendar.getInstance();
		//�����ǰʱ��
		System.out.println(cal.getTime());
		cal.set(2003, 4, 31, 12, 32, 23); //2003-5-31
		System.out.println(cal.getTime());
		//ǰ��һ��
		cal.add(Calendar.YEAR, -1);		//2002-5-31
		System.out.println(cal.getTime());
		//ǰ��3����
		cal.add(Calendar.MONTH, -3);	//2002-2-28 ���Զ���������
		System.out.println(cal.getTime());
		
		//add()��roll()������:
		//������ĳ�ֶεĲ�������ֶγ������ʱ���Զ���λ����roll()����
		cal.add(Calendar.DAY_OF_MONTH, 5);
		System.out.println(cal.getTime());//2002-3-5
		cal.set(2003,7,31,0,0,0);
		cal.roll(Calendar.MONTH, 6);	//2003-2-28���Զ���������
		System.out.println(cal.getTime());		
	}
	public static void test4(){
		//����Calendar�ݴ���
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 28);//Year�ֶ�+1
		System.out.println(cal.getTime());
		//�ر��ݴ���
		cal.setLenient(false);
		//����ʱ�쳣
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
