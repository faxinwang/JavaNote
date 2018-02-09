package lambda;

interface Eatable{
	void taste();
}
interface Flyable{
	void fly(String weather);
}
interface Addable{
	int add(int a,int b);
}

public class LambdaTest {
	//���ø÷�����ҪEatable����
	public void eat(Eatable e){
		System.out.println(e);
		e.taste();
	}
	//���ø÷�����ҪFlyable����
	public void drive(String weather,Flyable f){
		System.out.println("�����ڼ�ʻ: "+ f);
		f.fly(weather);
	}
	//���ø÷�����ҪAddable����
	public void test(int a,int b,Addable add){
		System.out.printf("%d + %d = %d\n",a,b,add.add(a, b));
	}
	
	public static void main(String[] args){
		LambdaTest lmd = new LambdaTest();
		//lambda���ʽ�Ĵ����ֻ��һ����䣬����ʡ�Ի�����
		lmd.eat(()-> System.out.println("ƻ����ζ������"));
		
		//labbda���ʽ���β��б�ֻ��һ������������ʡ��Բ����
		lmd.drive( "����ת��",(weather) -> {
			System.out.println("����������:" + weather);
			System.out.println("ֱ��������ƽ��!");
		});
		
		//lambda���ʽ��ֻ��һ����䣬������ֵ���Զ���Ϊ����ֵ
		lmd.test(2,5,(a,b)-> a + b);
	}
}
