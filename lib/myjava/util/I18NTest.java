package myjava.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTest {
	
	public static void main(String[] args){
		//获取系统默认的国家/语言环境
		Locale myLocale = Locale.getDefault(Locale.Category.FORMAT);
		System.out.println(myLocale);
		
		//根据指定国家语言环境加载资源文件
		ResourceBundle bundle = ResourceBundle.getBundle("./lib/myjava/util/Msg"
				,myLocale);
		
		System.out.println(bundle.getString("hello"));
	}
}
