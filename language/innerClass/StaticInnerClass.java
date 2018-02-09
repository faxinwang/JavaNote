package innerClass;

public class StaticInnerClass {
	private int Outer_prop = 5;
	private static int Outer_static_prop = 6;
	
	static class StaticInClass{
		//��̬�ڲ�������԰�����̬��Ա����
		private int Inner_prop = 7;
		private static int Inner_static_prop = 8; 
		public void accessOuterProp(){
			//��̬�ڲ��಻�ܷ����ⲿ���ʵ������
			//��Ϊ��̬�ڲ��������Ҫ�������ⲿ�������
			//��̬�ڲ���������ʱ���ⲿ�����һ������
		//	System.out.println(Outer_prop);
			//�����ڲ���Ҫ�����ⲿ���ʵ����ԱҲֻ��ͨ���ⲿ��������
			System.out.println("accessOuterProp() "
					+ "Outer_prop:" + new StaticInnerClass().Outer_prop);
			//��̬�ڲ�������ⲿ��ľ�̬��Ա������
			System.out.println("accessOuterProp()"
					+ " Outer_static_prop: " + Outer_static_prop);
		}
		//��̬�ڲ���ľ�̬������ֱ�ӷ����ⲿ��ľ�̬��Ա
		public static void staticAccessOuterProp(){
			System.out.println("staticAccessOuterProp() "
					+ "Outer_static_prop :" + Outer_static_prop);
		}
	}
	
	public void accessInnerProp(){
		//�ⲿ����ʾ�̬�ڲ���ľ�̬��Ա,Ҫͨ����������
	//	System.out.println(Inner_static_prop);
		System.out.println("accessInnerProp() "
				+ "Inner_static_prop:"+StaticInClass.Inner_static_prop);
		//�ⲿ����ʾ�̬�ڲ����ʵ��������Ҫͨ����̬�ڲ����ʵ������
		System.out.println("accessInnerProp()"
				+ " Inner_prop:"+new StaticInClass().Inner_prop);
	}
	
	public static void main(String[] args){
		StaticInClass.staticAccessOuterProp(); 		//�ⲿ��ֱ�ӷ����ڲ���ľ�̬����
		
		StaticInClass inner = new StaticInClass();	//�����ڲ������
		
		inner.accessOuterProp();//�����ڲ���ʵ������,�÷����������ⲿ��ľ�̬��Ա������ʵ������
		
		new StaticInnerClass().accessInnerProp();//�ⲿ���ʵ���������÷����з������ڲ���ľ�̬������ʵ������
	}
	
}
