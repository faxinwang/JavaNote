package abstractFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* �ڳ��󹤳�ģʽ�У����Ҫ����һ����Ŀ��Project,������Ҫ����������IProject,
 * SqlServerProject,AccessProject,����Ҫ����IFactory,SqlServerFactory,
 * AccessFactory��ſ�����ȫʵ��,�������Ǳ��ⲻ�˵ģ����޸����е����Υ���˿���ԭ��,�����
 * ���󹤳�ģʽ��һ��ȱ�㡣���⣬���ϵͳ����100���ط�ʹ����IUser����IDepartment,�ͱ�Ȼ
 * ����100�� IFactory factory = new AccessFactory()�����Ĵ���,���Ҫ�������ݿ�
 * ����100���ط���Ҫ�Ķ�����ͷǳ�����ˡ�
 * 
 * �����÷��似�����ͷ���һ���⣬���÷��似����ȡ��IFactory,SqlServerFactory,AccessFactory
 * ͬʱҲ������switch-case����if�ж���䡣����������ʹ�ñ������÷��䶯̬������,���Կ���ʹ�������ļ���
 * ��̬�޸� ʹ�ú������ݿ��ˡ�
 */

class DataAccess{
	private static String db;
	
	//��ȡ�����ļ�
	static {
		Properties p = new Properties();
		try {
			FileInputStream fin = new FileInputStream(
					new File("./DesignPattern/abstractFactory/dbConfig.properties"));
			p.load(fin);
			db = p.getProperty("db");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static IUser createUser(){
		String className = "abstractFactory."+db+"User";
		try {
			Class<?> clazz = Class.forName(className);
			return (IUser)clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("ʵ������:" + className +"ʱ�����쳣");
			e.printStackTrace();
		}
		return null;
	}
	
	public static IDepartment createDepartment() {
		
		String className = "abstractFactory." + db+ "Department";
		try {
			Class<?> clazz = Class.forName(className);
			return (IDepartment)clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			System.out.println("ʵ������:" + className +"ʱ�����쳣");
			e.printStackTrace();
		}
		return null;
	}
}

public class ReflactionAbstractFactory {
	public static void main(String[] args) {
		User user = new User();
		Department department = new Department();
		
		IUser iUser = DataAccess.createUser();
		iUser.insert(user);
		iUser.getUser(1);
		
		IDepartment iDepartment = DataAccess.createDepartment();
		iDepartment.insert(department);
		iDepartment.getDepartment(2);
	}
}
/*
output:
�� access �и�User������һ����¼
�� access �и���id�õ�user��һ����¼
��access��department���в���һ����¼
�� access��department���и���id�õ�һ����¼
 * */