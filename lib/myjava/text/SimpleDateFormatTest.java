package myjava.text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatTest {
	
	public static void main(String[] args)throws Exception{
		Date dt = new Date();
		//��ָ����ʽ����һ��SimpleDateFormat����
		SimpleDateFormat sdf1 = new SimpleDateFormat("Gyyyy���еĵ�d��");
		//�����ڸ�ʽ�����ַ���,���:��Ԫǰxxxx���еĵ�x��
		String dateStr1 = sdf1.format(dt);
		System.out.println(dateStr1);
		
		String str = "16###����##21";
		SimpleDateFormat sdf2 = new SimpleDateFormat("yy###MMM##d");
		//�������ַ�������������,���:Fri Mar 21 00:00:00 CST 2016
		System.out.println(sdf2.parse(str));
	}
}
