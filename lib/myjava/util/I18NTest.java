package myjava.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18NTest {
	
	public static void main(String[] args){
		//��ȡϵͳĬ�ϵĹ���/���Ի���
		Locale myLocale = Locale.getDefault(Locale.Category.FORMAT);
		System.out.println(myLocale);
		
		//����ָ���������Ի���������Դ�ļ�
		ResourceBundle bundle = ResourceBundle.getBundle("./lib/myjava/util/Msg"
				,myLocale);
		
		System.out.println(bundle.getString("hello"));
	}
}
