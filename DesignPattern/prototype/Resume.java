package prototype;


/* ������Clone�ӿ�
 * Clone: ��ʵ�ָýӿڵĶ������ǳ���������������ö���Ľṹ(����ֵ�����ֶ�
 * ��ֵ�;������������ֶε�����)
 */
interface ICloneable{
	Object Clone();
}


/*������Copy�ӿ�
 * Copy: ��ʵ���˸ýӿڵĶ��������������������öԵĽṹ����Ҫ�����ö�
 * �������(����ֵ�����ֶε�ֵ�;������������ֶε�������ָ��Ķ���)
 */
interface ICopyable{
	Object Copy();
}

//ʵ��ICopyable�ӿ�
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
		System.out.println("��������:" + work.getWorkDate() + " " + work.getCompany());
	}
	
	//ʵ��Icloneable�ӿڵ�Clone����
	public Object Clone() {
		Resume obj = new Resume(name);
		obj.sex = sex;
		obj.age = age;
		obj.work = work;// ǳ���� ��ֻ�������ú�ֵ
		return obj;
	}
	//ʵ��ICopyable�ӿڵ�Copy����
	public Object Copy() {
		Resume obj = new Resume(name);
		obj.sex = sex;
		obj.age = age;
		obj.work = (WorkExperience)work.Copy(); //��ƣ��������ö�ָ��Ķ���
		return obj;
	}
	
	public static void main(String[] args) {
		Resume a = new Resume("����");
		a.setPersonalInfo("��", 33);
		a.setWorkExperience("1998-2000", "xx��˾");
		
		Resume b = (Resume)a.Clone();  //ǳ������������a�Ĺ�������
		b.setPersonalInfo("��", 20);
		b.setWorkExperience("1998-2006", "yy��ҵ");
		
		Resume c = (Resume)a.Copy();  //����� ���Ḳ��a�Ĺ�������
		c.setPersonalInfo("��", 24);
		c.setWorkExperience("2000-2005", "zz��ҵ");
		
		a.display();
		b.display();
		c.display();
	}
}

