package abstractFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* 在抽象工厂模式中，如果要增加一个项目表Project,就至少要增加三个类IProject,
 * SqlServerProject,AccessProject,还需要更改IFactory,SqlServerFactory,
 * AccessFactory类才可以完全实现,增加类是避免不了的，但修改现有的类就违反了开闭原则,这就是
 * 抽象工厂模式的一个缺点。另外，如果系统中有100个地方使用了IUser或者IDepartment,就必然
 * 会有100句 IFactory factory = new AccessFactory()这样的代码,如果要更改数据库
 * 就有100个地方需要改动，这就非常糟糕了。
 * 
 * 下面用反射技术来客服这一问题，利用反射技术，取代IFactory,SqlServerFactory,AccessFactory
 * 同时也消除了switch-case语句和if判断语句。并且由于是使用变量利用反射动态创建类,所以可以使用配置文件来
 * 动态修改 使用何种数据库了。
 */

class DataAccess{
	private static String db;
	
	//读取配置文件
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
			System.out.println("实例化类:" + className +"时发生异常");
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
			System.out.println("实例化类:" + className +"时发生异常");
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
在 access 中给User表增加一条记录
在 access 中根据id得到user表一条记录
在access的department表中插入一条记录
在 access的department表中根据id得到一条记录
 * */