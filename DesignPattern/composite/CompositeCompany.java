package composite;

import java.util.*;

abstract class CompanyComponent{
	protected String name;
	protected String duty;//不同的部门履行不同的职责
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

//树枝结点
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

//叶结点
class LeafComponents extends CompanyComponent{
	public LeafComponents(String name) {
		super(name);
	}
	public LeafComponents() {}
	
	public void add(CompanyComponent cc) {
		System.out.println("已经是最小职能单位,不能设置下属机构!");
	}
	public void remove(CompanyComponent cc) {
		System.out.println("当前是最小职能单位下没有，没有任何下属机构");
	}
	public void display(int depth) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<depth; ++i) sb.append('-');
		sb.append(name + "  [" + duty + "]");
		System.out.println(sb);
	}
}

//人力资源部
class HRDepartment extends LeafComponents{
	public HRDepartment(String name) {
		super(name);
		setDuty("员工招聘，培训管理");
	}
	public HRDepartment() {
		super("人力资源部");
		setDuty("员工招聘，培训管理");
	}
}

//财务部
class FinanceDepartment extends LeafComponents{
	public FinanceDepartment(String name) {
		super(name);
		setDuty("部门财务收支管理");
	}
	public FinanceDepartment() {
		super("财务部");
		setDuty("部门财务收支管理");
	}
}

//IT部
class ITDepartment extends LeafComponents{
	public ITDepartment(String name) {
		super(name);
		setDuty("负责部门电脑等硬件的修缮与维护");
	}
	public ITDepartment() {
		super("IT部");
		setDuty("负责部门电脑等硬件的修缮与维护");
	}
}


public class CompositeCompany {
	public static void main(String[] args) {
		BranchComponents company = new BranchComponents("北京总公司");
		company.add(new HRDepartment());
		company.add(new FinanceDepartment());
		company.add(new ITDepartment());
		
		BranchComponents subCompany = new BranchComponents("上海华东分公司");
		subCompany.add(new HRDepartment());
		subCompany.add(new FinanceDepartment());
		company.add(subCompany);
		
		BranchComponents subDepart = new BranchComponents("南京办事处");
		subDepart.add(new HRDepartment());
		subDepart.add(new FinanceDepartment());
		subDepart.add(new ITDepartment());
		subCompany.add(subDepart);
		
		BranchComponents subDepart2 = new BranchComponents("杭州办事处");
		subDepart2.add(new FinanceDepartment());
		subDepart2.add(new ITDepartment());
		subCompany.add(subDepart2);
		
		company.display(1);
	}
}
