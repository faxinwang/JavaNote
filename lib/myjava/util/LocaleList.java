package myjava.util;

import java.util.Locale;

public class LocaleList {

	public static void main(String[] args){
		//返回java所支持的全部国家和语言的数组
		Locale[] localeList = Locale.getAvailableLocales();
		//遍历数组的每一个元素，一次获取所支持的每一个国家和语言
		for(int i=0;i<localeList.length;++i){
			System.out.print(localeList[i].getDisplayCountry() + " = " +
					localeList[i].getCountry()+"\t");
			System.out.println(localeList[i].getDisplayLanguage() + " = " +
					localeList[i].getLanguage());
		}
	}
}
