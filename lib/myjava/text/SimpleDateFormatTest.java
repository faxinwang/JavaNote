package myjava.text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {
	
	public static void main(String[] args)throws Exception{
		Date dt = new Date();
		//用指定格式创建一个SimpleDateFormat对象
		SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy年中的第d天");
		//将日期格式化成字符串,输出:公元前xxxx年中的第x天
		String dateStr1 = sdf1.format(dt);
		System.out.println(dateStr1);
		
		String str = "16###三月##21";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yy###MMM##d");
		//将日期字符串解析成日期,输出:Fri Mar 21 00:00:00 CST 2016
		System.out.println(sdf2.parse(str));
	}
}
