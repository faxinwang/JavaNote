package innerClass;

public class CreateInnerInstance {
	
	public static void main(String[] args){
		Cow cow = new Cow(123);//�����ⲿ�����
		Cow.CowLeg leg = cow.new CowLeg(12.6, "�ڰ����");//ͨ���ⲿ����󴴽��Ǿ�̬�ڲ���
		leg.info();
		//Ҳ�������������ڲ������
		Cow.CowLeg leg2 =new Cow(13.5).new CowLeg(11.4,"��ɫ");
		leg2.info();
		
		//������̬�ڲ���Ķ���
		StaticInnerClass.StaticInClass inner;
		inner = new StaticInnerClass.StaticInClass();
		inner.accessOuterProp();
	}
}
