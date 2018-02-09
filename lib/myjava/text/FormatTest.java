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
		String msg="今天是{0},今天中午想吃{1}";
		System.out.println(MessageFormat.format(msg,LocalDate.now(),"红烧鱼块"));
	}
	public static void testNumberFormat(){
		double db = 1234000.456;
		//创建四个Locale对象，分别代表中国，日本，德国，美国
		Locale[] locals = {Locale.CHINA,Locale.JAPAN,Locale.GERMAN,Locale.US};
		NumberFormat[] nf = new NumberFormat[12];
		//为上面四个locale创建12个NumberFormat对象，
		//每个locale对象分别有通用数值格式器，百分数格式器，货币格式器
		for(int i=0;i<locals.length;++i){
			nf[i*3]   = NumberFormat.getInstance(locals[i]);
			nf[i*3+1] = NumberFormat.getPercentInstance(locals[i]);
			nf[i*3+2] = NumberFormat.getCurrencyInstance(locals[i]);
		}
		for(int i=0;i<locals.length;++i){
			String tip = i==0? "-------中国的格式-------":
						 i==1? "-------日本的格式-------":
						 i==2? "-------德国的格式-------":
							   "-------美国的格式-------";
			System.out.println(tip);
			System.out.println("通用数值格式:"+nf[i*3].format(db));
			System.out.println("百分比格式:"+nf[i*3+1].format(db));
			System.out.println("货币格式:"+nf[i*3+2].format(db));
		}
	}
	public static void testDateFormat(){
		//需要被格式化的时间
		Date dt = new Date();
		//创建两个Locale分别代表中国，美国
		Locale[] locales = {Locale.CHINA,Locale.US};
		DateFormat[] df = new DateFormat[16];
		//为上面两个Locale对象 创建16个DateFormat对象
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
			String tip= i==0? "-----中国日期格式-----":"-----美国日期格式-----";
			System.out.println(tip);
			System.out.println("SHORT日期格式:"+	df[i*8].format(dt));
			System.out.println("MEDIUM日期格式:"+	df[i*8+1].format(dt));
			System.out.println("LONG日期格式:"+	df[i*8+2].format(dt));
			System.out.println("FULL日期格式:"+	df[i*8+3].format(dt));
			System.out.println("SHORT时间格式:"+	df[i*8+4].format(dt));
			System.out.println("MEDIUM时间格式:"+	df[i*8+5].format(dt));
			System.out.println("LONG时间格式:"+	df[i*8+6].format(dt));
			System.out.println("FULL时间格式:"+	df[i*8+7].format(dt));
		}
	}

	public static void main(String[] args){
		testMessageFormat();
		testNumberFormat();
		testDateFormat();
	}
}
