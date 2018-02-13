package facade;

/* ���ģʽ(Facade)��Ϊ��ϵͳ�е�һ��ӿ��ṩһ��һ�µĽ��棬��ģʽ������
 * һ���߲�ӿڣ�����ӿ�ʹ����һ��ϵͳ��������ʹ�á�
 * 
 * -��ʱʹ�����ģʽ:
 * ���ȣ�����ƽ׶γ��ڣ�Ӧ������ʶ�ؽ���ͬ����������룬���羭�������ܹ�������Ҫ���������ݷ���
 * ���ҵ���߼��㣬ҵ���߼���ͱ�ʾ��Ĳ����֮�佨�����ģʽFacade����������Ϊ���ӵ���ϵͳ�ṩ
 * һ���򵥵Ľӿڣ�ʹ����ϴ�󽵵͡�
 * 
 * ��Σ���ϵͳ������Ϊ���ϵ��ع��ݻ������Խ��Խ���ӣ��������ģʽʹ��ʱҲ��������ܶ��С���࣬��
 * ���Ǻ��£���Ҳ���ⲿ�������ǵ��û����������ʹ���ϵ����ѣ��������Facade�����ṩһ���򵥵Ľӿڣ�
 * ��������֮���������
 * 
 * ��������ά��һ�������Ĵ���ϵͳ�ǣ��������ϵͳ�Ѿ��ǳ�����ά������չ�ˣ�����Ϊ�������ܶ�ǳ���Ҫ
 * �Ĺ��ܣ��µ����󿪷�����Ҫ������������ʱʹ�����ģʽFacadeҲ�Ƿǳ����ʵģ�����Ϊ��ϵͳ����һ��
 * Facade����࣬���ṩ��ƴֲڻ�߶ȸ��ӵ������Ĵ���ıȽ������򵥵Ľӿڣ�����ϵͳ��Facade����
 * ������Facade���������뽻�����и��ӵĹ�����
 */


class SubSystemOne{
	public void MethonOne() {
		System.out.println("��ϵͳ����һ");
	}
}

class SubSystemTwo{
	public void MethonTwo() {
		System.out.println("��ϵͳ������");
	}
}

class SubSystemThree{
	public void MethonThree() {
		System.out.println("��ϵͳ������");
	}
}

class SubSystemFour{
	public void MethonFour() {
		System.out.println("��ϵͳ������");
	}
}


//����࣬����Ҫ�˽�������ϵͳ�ķ��������ԣ�������ϣ��Ա�������
public class FacadeTemplate {
	SubSystemOne one;
	SubSystemTwo two;
	SubSystemThree three;
	SubSystemFour four;
	
	public FacadeTemplate() {
		one = new SubSystemOne();
		two = new SubSystemTwo();
		three = new SubSystemThree();
		four = new SubSystemFour();
	}
	
	public void MethodA() {
		System.out.println("������A()----");
		one.MethonOne();
		two.MethonTwo();
	}
	
	public void MethodB() {
		System.out.println("������B()----");
		three.MethonThree();
		four.MethonFour();
	}
	
	//�ͻ��˵��ã�����Facade�����ã��ͻ��˿��Ը�����֪��������ϵͳ�Ĵ���
	public static void main(String[] args) {
		FacadeTemplate facade = new FacadeTemplate();
		facade.MethodA();
//		������A()----
//		��ϵͳ����һ
//		��ϵͳ������
		System.out.println();
		facade.MethodB();
//		������B()----
//		��ϵͳ������
//		��ϵͳ������
	}
}
