package myjava.util;

import java.util.Locale;

public class LocaleList {

	public static void main(String[] args){
		//����java��֧�ֵ�ȫ�����Һ����Ե�����
		Locale[] localeList = Locale.getAvailableLocales();
		//���������ÿһ��Ԫ�أ�һ�λ�ȡ��֧�ֵ�ÿһ�����Һ�����
		for(int i=0;i<localeList.length;++i){
			System.out.print(localeList[i].getDisplayCountry() + " = " +
					localeList[i].getCountry()+"\t");
			System.out.println(localeList[i].getDisplayLanguage() + " = " +
					localeList[i].getLanguage());
		}
	}
}
