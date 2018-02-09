package myjava.text;

import java.time.LocalDateTime;
import java.time.format.*;

public class DateTimeFormatterTest {
	
	public static void test1(){
		
		DateTimeFormatter[] formatters = new DateTimeFormatter[]{
			//ֱ��ʹ�ó�������DateTimeFormatter����
			DateTimeFormatter.ISO_LOCAL_DATE,
			DateTimeFormatter.ISO_LOCAL_TIME,
			DateTimeFormatter.ISO_LOCAL_DATE_TIME,
			//ʹ�ñ��ػ��Ĳ�ͬ���������DateTimeFormatter��ʽ��
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM),
			DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG),
			//����ģʽ�ַ���������DateTimeFormatter����
			DateTimeFormatter.ofPattern("Gyyyy//MMM//dd HH:mm:ss")
		};
		LocalDateTime datetime = LocalDateTime.now();
		//һ��ʹ�ò�ͬ�ĸ�ʽ����datetime���и�ʽ��
		for(int i=0;i<formatters.length;++i){
			//�������д���Ч��һ��
		//	System.out.println(datetime.format(formatters[i]));
			System.out.println(formatters[i].format(datetime));
		}
	}
	public static void test2(){
		//����һ�������ʽ������,ʱ���ַ���
		String str1 = "2014==04==12 01ʱ06��08��";
		DateTimeFormatter fmter1 = DateTimeFormatter.ofPattern("yyyy==MM==dd HHʱmm��ss��");
		//���ַ���������LocalDateTiem����
		LocalDateTime dt1 = LocalDateTime.parse(str1,fmter1);
		System.out.println(dt1);//��� 2014-04-12T01:06:08
		//�ٴν�����һ��������ַ���
		String str2 = "2014$$$����$$$13 20Сʱ";
		//ģʽ�е�MMM����ҪΪ����,����ʱ�ᷢ�������쳣
		DateTimeFormatter fmter2 = DateTimeFormatter.ofPattern("yyyy$$$MMM$$$dd HHСʱ");
		LocalDateTime dt2 = LocalDateTime.parse(str2,fmter2);
		System.out.println(dt2);//��� 2014-04-13T20:00
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
