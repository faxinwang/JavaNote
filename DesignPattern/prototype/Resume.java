package prototype;


/* 定义了Clone接口
 * Clone: 对实现该接口的对象进行浅拷贝，即仅拷贝该对象的结构(具有值语义字段
 * 的值和具有引用语义字段的引用)
 */
interface ICloneable{
	Object Clone();
}


/*定义了Copy接口
 * Copy: 对实现了该接口的对象进行深拷贝，不仅拷贝该对的结构，还要拷贝该对
 * 象的数据(具有值语义字段的值和具有引用语义字段的引用所指向的对象)
 */
interface ICopyable{
	Object Copy();
}

//实现ICopyable接口
class WorkExperience implements ICopyable{
	private String workDate;
	private String company;
	
	public void setWorkDate(String workdate) {
		this.workDate = workdate;
	}
	public String getWorkDate() {
		return workDate;
	}
	
	public void setCompany(String company) {
		this.company = company;
	}
	public String getCompany() {
		return company;
	}
	
	public Object Copy() {
		WorkExperience obj = new WorkExperience();
		obj.setWorkDate(workDate);
		obj.setCompany(company);
		return obj;
	}
}


public class Resume implements ICloneable,ICopyable{
	private String name;
	private String sex;
	private int age;
	private WorkExperience work;
	
	public Resume(String name) {
		this.name = name;
		work = new WorkExperience();
	}
	public void setPersonalInfo(String sex,int age) {
		this.sex = sex;
		this.age = age;
	}
	public void setWorkExperience(String workDate, String company) {
		work.setWorkDate(workDate);
		work.setCompany(company);
	}
	public void display() {
		System.out.println(name + " " + sex + " " + age);
		System.out.println("工作经验:" + work.getWorkDate() + " " + work.getCompany());
	}
	
	//实现Icloneable接口的Clone方法
	public Object Clone() {
		Resume obj = new Resume(name);
		obj.sex = sex;
		obj.age = age;
		obj.work = work;// 浅复制 ，只复制引用和值
		return obj;
	}
	//实现ICopyable接口的Copy方法
	public Object Copy() {
		Resume obj = new Resume(name);
		obj.sex = sex;
		obj.age = age;
		obj.work = (WorkExperience)work.Copy(); //深复制，复制引用对指向的对象
		return obj;
	}
	
	public static void main(String[] args) {
		Resume a = new Resume("大鸟");
		a.setPersonalInfo("男", 33);
		a.setWorkExperience("1998-2000", "xx公司");
		
		Resume b = (Resume)a.Clone();  //浅拷贝，将覆盖a的工作经历
		b.setPersonalInfo("男", 20);
		b.setWorkExperience("1998-2006", "yy企业");
		
		Resume c = (Resume)a.Copy();  //深拷贝， 不会覆盖a的工作经历
		c.setPersonalInfo("男", 24);
		c.setWorkExperience("2000-2005", "zz企业");
		
		a.display();
		b.display();
		c.display();
	}
}

