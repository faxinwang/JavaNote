package innerClass;

public class DiscernVar {
	private String prop = "�ⲿ���ʵ������";
	
	private class InnerClass{
		private String prop = "�ڲ����ʵ������";
		
		//��������﷨���� �Ǿ�̬�ڲ����в����о�̬��Ա
	//	private static int a=0;
	//	private static void test(){}
	//	static{}  ��̬��ʼ����
		
		public void info(){
			String prop = "�ֲ�����";
			//�����ⲿ���ͬ��ʵ������:OuterClass.this.verName
			System.out.println("�ⲿ���ʵ��������ֵ:" + DiscernVar.this.prop);
			//�����ڲ����ͬ��ʵ������:InnerClass.this.verName ���� this.verName 
			System.out.println("�ڲ����ʵ��������ֵ:" + this.prop);
			System.out.println("�ֲ�������ֵ:" + prop);
		}
		
		
	}
	
	public void test(){
		InnerClass in = new InnerClass();
		in.info();
		/*�Ǿ�̬�ڲ���ĳ�Ա���ⲿ�����ǲ���֪�ģ�����ⲿ��Ҫ���÷Ǿ�̬�ڲ���ĳ�Ա
		 *�������ʽ�����Ǿ�̬�ڲ�����������÷�����ʵ����Ա��ԭ�����ⲿ�����ʱ���ڲ���
		 *����һ�����ڡ� 
		 * */
	}
	
	public static void main(String[] args){
		new DiscernVar().test();
	}
	

}
