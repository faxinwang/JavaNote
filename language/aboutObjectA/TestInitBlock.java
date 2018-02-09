package aboutObjectA;
import static java.lang.System.*;
class Root{
	static{
		out.println("Root�ľ�̬��ʼ����!");
	}
	{
		out.println("Root����ͨ��ʼ����!");
	}
	public Root(){
		out.println("Root���޲ι��캯��!");
	}
}
class Mid extends Root{
	static{
		out.println("Mid�ľ�̬��ʼ����!");
	}
	{
		out.println("Mid����ͨ��ʼ����!");
	}
	public Mid(){
		out.println("Mid���޲ι��캯��!");
	}
	public Mid(String msg){
		this();//����ͬһ�������صĹ��캯��
		out.println("Mid�Ĵ������Ĺ��캯��,����ֵΪ:"+msg);
	}
}
class Leaf extends Mid{
	static{
		out.println("Leaf�ľ�̬��ʼ����!");
	}
	{
		out.println("Leaf����ͨ��ʼ����!");
	}
	public Leaf(){
		//ͬ��super���ø�������һ���ַ��������Ĺ��캯��
		super("���java����!");
		out.println("ִ��Leaf�Ĺ�����!");
	}
}

public class TestInitBlock {
	
	public static void main(String[] args){
//		new Leaf();
//		out.println();
		
		//ÿ����ʵ����ʱ������ִ�и���ľ�̬��ʼ����(ֻ��jvm���ظ���ʱִ��һ��)
		//��ִ���Լ�����ͨ��ʼ����
		//���ִ���Լ��Ĺ��캯��
		//(���ڼ̳����ϵ�ÿ���඼��������������Ҫ�õݹ��˼����˼��)
		
		//������౻���ع�������������ľ�̬��ʼ����
		new Root();
		out.println();
		
		new Mid("mid class!");
		out.println();
		
		
		new Leaf();
		
		
	}
}
