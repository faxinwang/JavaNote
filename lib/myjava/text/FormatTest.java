package myjava.text;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import static java.text.DateFormat.*;

public class FormatTest {
	
	public static void testMessageFormat(){
		String msg="������{0},�����������{1}";
		System.out.println(MessageFormat.format(msg,LocalDate.now(),"�������"));
	}
	public static void testNumberFormat(){
		double db = 1234000.456;
		//�����ĸ�Locale���󣬷ֱ�����й����ձ����¹�������
		Locale[] locals = {Locale.CHINA,Locale.JAPAN,Locale.GERMAN,Locale.US};
		NumberFormat[] nf = new NumberFormat[12];
		//Ϊ�����ĸ�locale����12��NumberFormat����
		//ÿ��locale����ֱ���ͨ����ֵ��ʽ�����ٷ�����ʽ�������Ҹ�ʽ��
		for(int i=0;i<locals.length;++i){
			nf[i*3]   = NumberFormat.getInstance(locals[i]);
			nf[i*3+1] = NumberFormat.getPercentInstance(locals[i]);
			nf[i*3+2] = NumberFormat.getCurrencyInstance(locals[i]);
		}
		for(int i=0;i<locals.length;++i){
			String tip = i==0? "-------�й��ĸ�ʽ-------":
						 i==1? "-------�ձ��ĸ�ʽ-------":
						 i==2? "-------�¹��ĸ�ʽ-------":
							   "-------�����ĸ�ʽ-------";
			System.out.println(tip);
			System.out.println("ͨ����ֵ��ʽ:"+nf[i*3].format(db));
			System.out.println("�ٷֱȸ�ʽ:"+nf[i*3+1].format(db));
			System.out.println("���Ҹ�ʽ:"+nf[i*3+2].format(db));
		}
	}
	public static void testDateFormat(){
		//��Ҫ����ʽ����ʱ��
		Date dt = new Date();
		//��������Locale�ֱ�����й�������
		Locale[] locales = {Locale.CHINA,Locale.US};
		DateFormat[] df = new DateFormat[16];
		//Ϊ��������Locale���� ����16��DateFormat����
		for(int i=0 ;i < locales.length;++i){
			df[i*8] = DateFormat.getDateInstance(SHORT,locales[i]);
			df[i*8 + 1] = DateFormat.getDateInstance(MEDIUM,locales[i]);
			df[i*8 + 2] = DateFormat.getDateInstance(LONG,locales[i]);
			df[i*8 + 3] = DateFormat.getDateInstance(FULL,locales[i]);
			df[i*8 + 4] = DateFormat.getTimeInstance(SHORT,locales[i]);
			df[i*8 + 5] = DateFormat.getTimeInstance(MEDIUM,locales[i]);
			df[i*8 + 6] = DateFormat.getTimeInstance(LONG,locales[i]);
			df[i*8 + 7] = DateFormat.getTimeInstance(FULL,locales[i]);
		}
		for(int i=0;i<locales.length;++i){
			String tip= i==0? "-----�й����ڸ�ʽ-----":"-----�������ڸ�ʽ-----";
			System.out.println(tip);
			System.out.println("SHORT���ڸ�ʽ:"+	df[i*8].format(dt));
			System.out.println("MEDIUM���ڸ�ʽ:"+	df[i*8+1].format(dt));
			System.out.println("LONG���ڸ�ʽ:"+	df[i*8+2].format(dt));
			System.out.println("FULL���ڸ�ʽ:"+	df[i*8+3].format(dt));
			System.out.println("SHORTʱ���ʽ:"+	df[i*8+4].format(dt));
			System.out.println("MEDIUMʱ���ʽ:"+	df[i*8+5].format(dt));
			System.out.println("LONGʱ���ʽ:"+	df[i*8+6].format(dt));
			System.out.println("FULLʱ���ʽ:"+	df[i*8+7].format(dt));
		}
	}

	public static void main(String[] args){
		testMessageFormat();
		testNumberFormat();
		testDateFormat();
	}
}
