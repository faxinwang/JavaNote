package myjava.lang.reflect;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPoolFactory {
	//����һ�������Map,key�Ƕ�����,value�Ƕ�Ӧ������
	private Map<String,Object> objPool = new HashMap<>();
	//����һ����������ķ���
	//�÷������ݴ�����ַ������ɶ�Ӧ��java����
	private Object createObject(String className)
			throws InstantiationException,IllegalAccessException,ClassNotFoundException{
		//�����ַ�������ȡ��Ӧ��Class����
		Class<?> clazz = Class.forName(className);
		//ʹ��clazz��Ӧ���Ĭ�Ϲ�����������ʵ��,���������Ĭ�Ϲ�����,�����׳��쳣
		return clazz.newInstance(); 
	}
	//�÷�������ָ���ļ�����ʼ�������
	//������������ļ�����������
	public void initPool(String fileName)
		throws InstantiationException,IllegalAccessException,ClassNotFoundException{
		try(
			FileInputStream fis = new FileInputStream(fileName);
		){
			Properties pps = new Properties();
			pps.load(fis);
			for(String name:pps.stringPropertyNames()){
				//ÿȡ��һ��key-value��,�͸���value����һ������
				//����createObject()��������,����������ӵ��������
				objPool.put(name, createObject(pps.getProperty(name)) );
			}
			
		}catch(IOException ioe){
			System.out.println("��ȡ" + fileName+"�쳣 " + ioe.getMessage());
		}
	}
	
	public Object getObject(String name){
		//��objPool��ȡ��ָ��name��Ӧ�Ķ���
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
