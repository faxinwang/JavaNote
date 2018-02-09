package abstractFactory;

/* ���󹤳�ģʽ: �ṩһ������һϵ����ػ��໥��������Ľӿڣ�
 * ������ָ�����Ǿ�����ࡣ
 */

/* User��(����User����һ�м�¼�������Ϣ)
 * Department��(����department����һ�м�¼�������Ϣ)
 *
 * IUser(������Ҫ��User����еĲ���,���������ݿ��޹�)
 *   |---->SqlServerUser(ʵ����SqlServer�ж�User����в�������ط���)
 *   |---->AccessUser(ʵ����Access�ж�User����в�������ط���)
 *  ��չ--->MysqlUser(ʵ����Mysql�ж�User����в�������ط���)
 *   
 * IDepartment(������Ҫ��Department����еĲ���,���������ݿ��޹�)
 * 	 |---->SqlServerDepartment(ʵ����SqlServer�ж�Deparment����в�������ط���)
 *   |---->AccessDepartment(ʵ����Access�ж�Department����в�������ط���)
 *  ��չ--->MysqlDepartment(ʵ����Mysql�ж�Department����в�������ط���)
 *  
 * IFactory(���崴�� �������ݿ��еı���в����Ķ��� �ĺ���,���������ݿ��޹�)
 * 	 |---->SqlServerFactory(�����Ķ������SqlServer�еı���в�������)
 * 	 |---->AccessFactory(�����Ķ������Access���ݿ��еı���в�������)
 * 	 ��չ--->MysqlFactory(�����Ķ������MySQL���ݿ��еı���в�������)
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


//��������ݿ���User����Ҫ���еĲ�������������ݿ��޹�
interface IUser{
	void insert(User user);
	User getUser(int id);
}

//ʵ����SqlServer�ж�User����в�������ط���
class SqlServerUser implements IUser{
	public void insert(User user) {
		System.out.println("��sql server�и�User������һ����¼");
	}
	public User getUser(int id) {
		System.out.println("��sql server�и���id�õ�user��һ����¼");
		return null;
	}
}

//ʵ����Access�ж�User����в�������ط���
class AccessUser implements IUser{
	public void insert(User user) {
		System.out.println("�� access �и�User������һ����¼");
	}
	public User getUser(int id) {
		System.out.println("�� access �и���id�õ�user��һ����¼");
		return null;
	}
}


//��������ݿ���Department����Ҫ���еĲ�������������ݿ��޹�
interface IDepartment{
	void insert(Department department);
	Department getDepartment(int id);
}

//ʵ����SqlServer�ж�Department����в�������ط���
class SqlServerDepartment implements IDepartment{
	public void insert(Department department) {
		System.out.println("��sql server��department���в���һ����¼");
	}
	public Department getDepartment(int id) {
		System.out.println("�� sql server��department���и���id�õ�һ����¼");
		return null;
	}
}

//ʵ����access�ж�Department����в�������ط���
class AccessDepartment implements IDepartment{
	public void insert(Department department) {
		System.out.println("��access��department���в���һ����¼");
	}
	public Department getDepartment(int id) {
		System.out.println("�� access��department���и���id�õ�һ����¼");
		return null;
	}
}

//���崴�� �������ݿ��еı���в������ࡱ �ĺ��������������ݿ��޹�
interface IFactory{
	IUser createIUser();
	IDepartment createIDepartment();
}

//���崴�� ����SqlServer���ݿ��еı���в����Ķ��� �ĺ���
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

//���崴�� ����Access���ݿ��еı���в����Ķ��� �ĺ���
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
		
		/* ֻ��Ҫ������ѡ��һ�����ݿ⹤�����Ϳ����л����е���Ծ������ݿ�Ĳ���
		 * ��չ: 
		 * 1 ������ݿ�����Ҫ�����±�Salary,��Ҫ:
		 *  ����һ��Salary�࣬
		 *  ����һ��ISalary�ӿ� ������Ҫ�Ըñ���еĲ�����
		 *  ��д��ͬ���ݿ�Ըýӿڵ�ʵ������AccessSalary,SqlServerSalary��
		 * 	��AccessFactory���ݿ⹤���������Ӵ���AccessSalary�ķ���
		 *  ��SqlServerFactory���ݿ⹤���������Ӵ���SqlServerSalary�ķ���
		 * 
		 * 2 �����Ҫ��ʹ�����������ݿ���MySQL����Ҫ:
		 *  ����MysqlUser��ʵ��IUser�ӿ�ʵ�ֶ�mysql���ݿ���User��Ĳ���
		 *  ����MysqlDepartment��ʵ��IDepartment�ӿ�ʵ�ֶ�mysql���ݿ���Department��Ĳ���
		 *  ������Ӧ�Ĺ�����MysqlFactory,����дcreateIUser()������createIDepartment()����  
		 */
		
		IFactory factory = new AccessFactory();    /* ʹ��/����Access���ݿ� */
//		IFactory factory = new SqlServerFactory(); /* ʹ��/����SqlServer���ݿ� */
		
		IUser iuser = factory.createIUser();
		iuser.insert(user);
		iuser.getUser(1);
		
		IDepartment iDepartment = factory.createIDepartment();
		iDepartment.insert(department);
		iDepartment.getDepartment(2);
	}
}
