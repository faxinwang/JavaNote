package aboutObjectA;

class Animal{
	private String desc;
	public Animal(){
		this.desc  = getDesc();	//2 �˴����ܵ��������е���д��getDesc()�ĺ���
	}
	public String getDesc(){
		return "Animal";
	}
	public String toString(){
		return desc;
	}
}

class Wolf extends Animal{
	private String name;
	private double weight;
	public Wolf(String name ,double weight){	//3
		this.name = name;
		this.weight = weight;
	}
	
	@Override
	public String getDesc(){
		return "Wolf[name="+name+",weight="+weight+"]";
	}
}

public class InvokeMethodFromSubclass {
	public static void main(String[] args){
		//�������:Wolf[name=null,weight=0.0]
		System.out.println(new Wolf("��̫��",22.2)); 	//1
		/* �����������������Ӧ����Wolf[name=��̫��,weight=22.2],
		 * ��ʵ����ȴ���ǡ��������ִ��˳��Ϊ����1,2,3.Wolf()���캯�����ȵ���Animal��Ĭ�Ϲ��캯��,
		 * ��Ĭ�Ϲ��캯���е�����getDesc()����,ֵ��ע�����,2���Ĵ���ʵ���ϻ���������getDesc()����,
		 * ԭ����thisʵ�������õ���Wolf�Ķ���.��ִ�д˺�����,Wolf�Ĺ��캯����δ������,����ֵ��ΪĬ��ֵ.
		 */
	}
}
