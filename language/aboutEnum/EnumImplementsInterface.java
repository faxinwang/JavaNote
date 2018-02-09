package aboutEnum;

import java.util.Arrays;

interface GenderDesc{
	void info();
}
enum Gender1 implements GenderDesc{
	//�˴���ö��ֵ������ö�Ӧ�Ĺ�����������
	MALE("��"),FEMALE("Ů");
	private final String name;
	private Gender1(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	//���е�ö��ֵ����ӵ����ͬ�ĳ��󷽷�
	@Override
	public void info() {
		System.out.println("����һ�����ڶ����Ա��ö����!");
	}
}

enum Gender2 implements GenderDesc{
	//�����Ų���ʵ������һ�����岿��,�������ڲ����������,�������ö��ֵ���������ڲ������ʵ��
	//ϵͳ�Զ�����Щʵ��ʹ�� public static final����
	MALE("��"){	
		public void info(){
			System.out.println("���ö��ֵ��������!");
		}
	},
	//ÿ��ö��ֵʵ���ò�ͬ�Ĵ���ʵ���˽ӿڵĳ��󷽷��������ָ���
	FEMALE("Ů"){
		public void info(){
			System.out.println("���ö��ֵ����Ů��!");
		}
	};
	
	private final String name;
	private Gender2(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}

public class EnumImplementsInterface {
	
	public static void main(String[] args){
		System.out.println(Arrays.toString(Gender1.values()));
		for(Gender1 g : Gender1.values()){
			g.info();
		}
		for(Gender2 g : Gender2.values()){
			g.info();
		}
		
		//ö��ֵ����public static final�ģ�
		//���Կ���ֱ��ͨ��className.valName.func()��������ʵ������
		Gender1.FEMALE.info();
		Gender2.FEMALE.info();
	}
}
