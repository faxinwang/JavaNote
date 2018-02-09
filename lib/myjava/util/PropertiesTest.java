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
		//加载属性文件
		props.load(new FileInputStream(file));
		props.forEach((key,value) -> System.out.println(key + "--->" + value));
		props.setProperty("username","wangfaxin");//设置username的属性值
		props.setProperty("password", "abc123");
		//将属性类的key-value保存到文件
		props.store(new FileOutputStream(file), "updated:" + LocalDateTime.now());
		props.forEach((key,value) -> System.out.println(key + "--->" + value));
		System.out.println("username:"+props.getProperty("username"));
		System.out.println("user:"+props.getProperty("user","faxin"));
	}
}
