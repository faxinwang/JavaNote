package composite;

import java.util.*;

abstract class CompanyComponent{
	protected String name;
	protected String duty;//��ͬ�Ĳ������в�ͬ��ְ��
	public CompanyComponent() {};
	public CompanyComponent(String name) {
		this.name = name;
	}
	public abstract void add(CompanyComponent c);
	public abstract void remove(CompanyComponent c);
	public abstract void display(int depth);
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getDuty() {return duty;}
}

//��֦���
class BranchComponents extends CompanyComponent{
	private List<CompanyComponent> list = new ArrayList<>();
	public BranchComponents(String name) {
		super(name);
	}
	public BranchComponents() {}
	
	public void add(CompanyComponent cc) {
		list.add(cc);
	}
	public void remove(CompanyComponent cc) {
		list.remove(cc);
	}
	public void display(int depth) {
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<depth; ++i) sb.append('-');
		sb.append(name + " [ " + duty + " ]");
		System.out.println(sb);
		for(CompanyComponent cc : list) {
			cc.display(depth + 3);
		}
	}
}

//Ҷ���
class LeafComponents extends CompanyComponent{
	public LeafComponents(String name) {
		super(name);
	}
	public LeafComponents() {}
	
	public void add(CompanyComponent cc) {
		System.out.println("�Ѿ�����Сְ�ܵ�λ,����������������!");
	}
	public void remove(CompanyComponent cc) {
		System.out.println("��ǰ����Сְ�ܵ�λ��û�У�û���κ���������");
	}
	public void display(int depth) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<depth; ++i) sb.append('-');
		sb.append(name + "  [" + duty + "]");
		System.out.println(sb);
	}
}

//������Դ��
class HRDepartment extends LeafComponents{
	public HRDepartment(String name) {
		super(name);
		setDuty("Ա����Ƹ����ѵ����");
	}
	public HRDepartment() {
		super("������Դ��");
		setDuty("Ա����Ƹ����ѵ����");
	}
}

//����
class FinanceDepartment extends LeafComponents{
	public FinanceDepartment(String name) {
		super(name);
		setDuty("���Ų�����֧����");
	}
	public FinanceDepartment() {
		super("����");
		setDuty("���Ų�����֧����");
	}
}

//IT��
class ITDepartment extends LeafComponents{
	public ITDepartment(String name) {
		super(name);
		setDuty("�����ŵ��Ե�Ӳ����������ά��");
	}
	public ITDepartment() {
		super("IT��");
		setDuty("�����ŵ��Ե�Ӳ����������ά��");
	}
}


public class CompositeCompany {
	public static void main(String[] args) {
		BranchComponents company = new BranchComponents("�����ܹ�˾");
		company.add(new HRDepartment());
		company.add(new FinanceDepartment());
		company.add(new ITDepartment());
		
		BranchComponents subCompany = new BranchComponents("�Ϻ������ֹ�˾");
		subCompany.add(new HRDepartment());
		subCompany.add(new FinanceDepartment());
		company.add(subCompany);
		
		BranchComponents subDepart = new BranchComponents("�Ͼ����´�");
		subDepart.add(new HRDepartment());
		subDepart.add(new FinanceDepartment());
		subDepart.add(new ITDepartment());
		subCompany.add(subDepart);
		
		BranchComponents subDepart2 = new BranchComponents("���ݰ��´�");
		subDepart2.add(new FinanceDepartment());
		subDepart2.add(new ITDepartment());
		subCompany.add(subDepart2);
		
		company.display(1);
	}
}
