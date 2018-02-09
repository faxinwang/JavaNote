package myjava.lang;

public class MathTest {
	/*------��������-----------*/
	static void test1(){
		//������ת��Ϊ�Ƕ�
		System.out.println("Math.toDegree(1.57):" + Math.toDegrees(1.57));
		//���Ƕ�ת��Ϊ����
		System.out.println("Math.toRadians(90):" + Math.toRadians(90));
		//��������
		System.out.println("Math.sin(pi/3):" + Math.sin(Math.PI/3));
		//���㷴����,���صĽǶȷ�Χ��0 - pi֮��
		System.out.println("Math.asin(0.8):" + Math.asin(0.8));
	}
	/*--------ȡ������---------*/
	static void test2(){
		//����ȡ��
		System.out.println("Math.floor(-1.2):" + Math.floor(-1.2));
		System.out.println("Math.floor(1.2):" + Math.floor(1.2));
		//����ȡ��
		System.out.println("Math.ceil(-1.2):" + Math.ceil(-1.2));
		System.out.println("Math.ceil(1.2):" + Math.ceil(1.2));
		//��������
		System.out.println("Math.round(-1.2):" + Math.round(-1.2));
		System.out.println("Math.round(1.2):" + Math.round(1.2));
		System.out.println("Math.round(-1.5):" + Math.round(-1.5));
		System.out.println("Math.round(1.5):" + Math.round(1.5));
	}
	/*--------�˷���������ָ������---------*/
	static void test3(){
		//����ƽ����
		System.out.println("Math.sqrt(2.3):" + Math.sqrt(2.3));
		//����������
		System.out.println("Math.cbrt(9):" + Math.cbrt(9));
		//����ŷ����e��n�η�
		System.out.println("Math.exp(2):" + Math.exp(2));
		//����sqrt(x2 + y2),û���м����������
		System.out.println("Math.hypot(4,4):" + Math.hypot(4,4));
		//����˷�
		System.out.println("Math.pow(3,2):" + Math.pow(3,2));
		//������Ȼ����
		System.out.println("Math.log(12):" + Math.log(12));
		//�������Ϊ10�Ķ���
		System.out.println("Math.log10(9):" + Math.log10(9));
	}
	/*--------�����������---------*/
	static void test4(){
		//�������ֵ
		System.out.println("Math.abs(-1.5):" + Math.abs(-1.5));
		//���صڶ����������ŵĵ�һ���������
		System.out.println("Math.coypSign(1.2,-3):" + Math.copySign(1.2,-3));
		//���ź��� ����=0 ,����0 ; ����<0, ����-1.0; ����>0,���� 1.0;
		System.out.println("Math.signum(-3):" + Math.signum(-3));
		System.out.println("Math.signum(0):" + Math.signum(0));
		System.out.println("Math.signum(3):" + Math.signum(3));
	}
	/*--------��С�������---------*/
	static void test5(){
		System.out.println("Math.max(3,6):" + Math.max(3,6));
		System.out.println("Math.min(2,5):" + Math.min(2,5));
		//������������֮�����һ���������ڵĸ�����
		System.out.println("Math.nextAfter(1.2,1.0):" + Math.nextAfter(1.2,1.0));
		//���ر�Ŀ�����Դ�ĸ�����
		System.out.println("Math.nextUp(1.2):" + Math.nextUp(1.2));
		//����[0.0,1.0)֮���һ��α�����
		System.out.println("Math.random():" + Math.random());
		System.out.println("Math.random():" + Math.random());
		System.out.println("Math.random():" + Math.random());
	}
	
	public static void main(String[] args){
		test1();
		System.out.println();
		test2();
		System.out.println();
		test3();
		System.out.println();
		test4();
		System.out.println();
		test5();
	}
}
