package myjava.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args)throws Exception{
		Properties props = new Properties();
		File file = new File("./bin/myjava/util/mess_zh_CN.properties");
		//���������ļ�
		props.load(new FileInputStream(file));
		props.forEach((key,value) -> System.out.println(key + "--->" + value));
		props.setProperty("username","wangfaxin");//����username������ֵ
		props.setProperty("password", "abc123");
		//���������key-value���浽�ļ�
		props.store(new FileOutputStream(file), "updated:" + LocalDateTime.now());
		props.forEach((key,value) -> System.out.println(key + "--->" + value));
		System.out.println("username:"+props.getProperty("username"));
		System.out.println("user:"+props.getProperty("user","faxin"));
	}
}
