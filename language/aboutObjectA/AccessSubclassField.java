package aboutObjectA;

class Base2{
	private int i=2;
	public Base2(){
		System.out.println(i);	//���2���������base
		this.display();			//���0�������������display����
		//���aboutObjectA.Derived��������ʱthis�����������Derived����
		System.out.println(this.getClass());
		/* �����ӱ����������ı���ʱ���ͺ�����ʱ���Ͳ�ͬ�ǣ�ͨ���ñ����������õĶ����ʵ������ʱ��
		 * ���ʵ��Ǳ���ʱ���������������õ�ʵ����������ͨ���ñ������������õĶ����ʵ������ʱ��
		 * ���õĽ�����ʵ�����õĶ���ķ�������˵��������this.i�ǽ���õ�Base.i��ֵҲ����2,
		 * ��ִ��this.display()ʱ�����õĽ���Derived.display(),������ʵ�i��Derived��
		 * �����i,����ʱi��ûֻ��Ĭ��ֵ0.
		 */
	}
	public void display(){
		System.out.println(i);
	}
}

class Derived extends Base2{
	private int i=22;
	public Derived(){
		i = 222;
	}
	public void display(){
		System.out.println(i);
	}
}


public class AccessSubclassField {
	public static void main(String[] args){
		
		//���ῴ��������Ϊ0
		new Derived();
	}
}
