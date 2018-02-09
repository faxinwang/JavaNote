package myjava.lang.reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPoolFactory {
	//定义一个对象池Map,key是对象名,value是对应的类名
	private Map<String,Object> objPool = new HashMap<>();
	//定义一个创建对象的方法
	//该方法根据传入的字符串生成对应的java对象
	private Object createObject(String className)
			throws InstantiationException,IllegalAccessException,ClassNotFoundException{
		//根据字符串来获取对应的Class对象
		Class<?> clazz = Class.forName(className);
		//使用clazz对应类的默认构造器来创建实例,该类必须有默认构造器,否侧会抛出异常
		return clazz.newInstance(); 
	}
	//该方法根据指定文件来初始化对象池
	//他会根据配置文件来创建对象
	public void initPool(String fileName)
		throws InstantiationException,IllegalAccessException,ClassNotFoundException{
		try(
			FileInputStream fis = new FileInputStream(fileName);
		){
			Properties pps = new Properties();
			pps.load(fis);
			for(String name:pps.stringPropertyNames()){
				//每取出一对key-value对,就根据value创建一个对象
				//调用createObject()创建对象,并将对象添加到对象池中
				objPool.put(name, createObject(pps.getProperty(name)) );
			}
			
		}catch(IOException ioe){
			System.out.println("读取" + fileName+"异常 " + ioe.getMessage());
		}
	}
	
	public Object getObject(String name){
		//从objPool中取出指定name对应的对象
		return objPool.get(name);
	}
	
	public static void main(String[] args)throws Exception{
		ObjectPoolFactory pf= new ObjectPoolFactory();
		pf.initPool("./lib/myjava/lang/reflect/ObjPool.properties");
		System.out.println(pf.getObject("Date"));
		System.out.println(pf.getObject("Hello"));
		System.out.println(pf.getObject("MethodParameterTest"));
	}
}
