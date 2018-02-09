package abstractFactory;

/* 抽象工厂模式: 提供一个创建一系列相关或相互依赖对象的接口，
 * 而无需指定他们具体的类。
 */

/* User类(保存User表中一行记录的相关信息)
 * Department类(保存department表中一行记录的相关信息)
 *
 * IUser(定义需要对User表进行的操作,与具体的数据库无关)
 *   |---->SqlServerUser(实现在SqlServer中对User表进行操作的相关方法)
 *   |---->AccessUser(实现在Access中对User表进行操作的相关方法)
 *  扩展--->MysqlUser(实现在Mysql中对User表进行操作的相关方法)
 *   
 * IDepartment(定义需要对Department表进行的操作,与具体的数据库无关)
 * 	 |---->SqlServerDepartment(实现在SqlServer中对Deparment表进行操作的相关方法)
 *   |---->AccessDepartment(实现在Access中对Department表进行操作的相关方法)
 *  扩展--->MysqlDepartment(实现在Mysql中对Department表进行操作的相关方法)
 *  
 * IFactory(定义创建 “对数据库中的表进行操作的对象” 的函数,与具体的数据库无关)
 * 	 |---->SqlServerFactory(创建的都是针对SqlServer中的表进行操作的类)
 * 	 |---->AccessFactory(创建的都是针对Access数据库中的表进行操作的类)
 * 	 扩展--->MysqlFactory(创建的都是针对MySQL数据库中的表进行操作的类)
 */

@SuppressWarnings("unused")
class User{
	private int id;
	private String name;
}

@SuppressWarnings("unused")
class Department{
	private String name;
}


//定义对数据库中User表需要进行的操作，与具体数据库无关
interface IUser{
	void insert(User user);
	User getUser(int id);
}

//实现在SqlServer中对User表进行操作的相关方法
class SqlServerUser implements IUser{
	public void insert(User user) {
		System.out.println("在sql server中给User表增加一条记录");
	}
	public User getUser(int id) {
		System.out.println("在sql server中根据id得到user表一条记录");
		return null;
	}
}

//实现在Access中对User表进行操作的相关方法
class AccessUser implements IUser{
	public void insert(User user) {
		System.out.println("在 access 中给User表增加一条记录");
	}
	public User getUser(int id) {
		System.out.println("在 access 中根据id得到user表一条记录");
		return null;
	}
}


//定义对数据库中Department表需要进行的操作，与具体数据库无关
interface IDepartment{
	void insert(Department department);
	Department getDepartment(int id);
}

//实现在SqlServer中对Department表进行操作的相关方法
class SqlServerDepartment implements IDepartment{
	public void insert(Department department) {
		System.out.println("在sql server的department表中插入一条记录");
	}
	public Department getDepartment(int id) {
		System.out.println("在 sql server的department表中根据id得到一条记录");
		return null;
	}
}

//实现在access中对Department表进行操作的相关方法
class AccessDepartment implements IDepartment{
	public void insert(Department department) {
		System.out.println("在access的department表中插入一条记录");
	}
	public Department getDepartment(int id) {
		System.out.println("在 access的department表中根据id得到一条记录");
		return null;
	}
}

//定义创建 “对数据库中的表进行操作的类” 的函数，与具体的数据库无关
interface IFactory{
	IUser createIUser();
	IDepartment createIDepartment();
}

//定义创建 “对SqlServer数据库中的表进行操作的对象” 的函数
class SqlServerFactory implements IFactory{
	@Override
	public IUser createIUser() {
		return new SqlServerUser();
	}

	@Override
	public IDepartment createIDepartment() {
		return new SqlServerDepartment();
	}
}

//定义创建 “对Access数据库中的表进行操作的对象” 的函数
class AccessFactory implements IFactory{
	@Override
	public IUser createIUser() {
		return new AccessUser();
	}
	@Override
	public IDepartment createIDepartment() {
		return new AccessDepartment();
	}
}


public class AbstractFactory {

	public static void main(String[] args) {
		User user = new User();
		Department department = new Department();
		
		/* 只需要在下面选择一个数据库工厂，就可以切换所有的针对具体数据库的操作
		 * 扩展: 
		 * 1 如果数据库中需要增加新表Salary,需要:
		 *  增加一个Salary类，
		 *  增加一个ISalary接口 定义需要对该表进行的操作，
		 *  编写不同数据库对该接口的实现类如AccessSalary,SqlServerSalary，
		 * 	在AccessFactory数据库工厂类中增加创建AccessSalary的方法
		 *  在SqlServerFactory数据库工厂类中增加创建SqlServerSalary的方法
		 * 
		 * 2 如果需要改使用其他的数据库如MySQL，需要:
		 *  增加MysqlUser类实现IUser接口实现对mysql数据库中User表的操作
		 *  增加MysqlDepartment类实现IDepartment接口实现对mysql数据库中Department表的操作
		 *  增加相应的工厂类MysqlFactory,并编写createIUser()函数和createIDepartment()函数  
		 */
		
		IFactory factory = new AccessFactory();    /* 使用/操作Access数据库 */
//		IFactory factory = new SqlServerFactory(); /* 使用/操作SqlServer数据库 */
		
		IUser iuser = factory.createIUser();
		iuser.insert(user);
		iuser.getUser(1);
		
		IDepartment iDepartment = factory.createIDepartment();
		iDepartment.insert(department);
		iDepartment.getDepartment(2);
	}
}
