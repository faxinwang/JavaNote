package aboutObjectB;

/* final���εķǾ�̬��Ա�������������������ط�֮һ��ʼ��
 * 	1.����ʱ.(���������(�ڱ���ʱ��֪��������ֵ)ϵͳʵ����ִ����"���滻")
 *  2.��ͨ��ʼ����
 *  3.���캯��
 * final���εľ�̬��Ա�������������������ط�֮һ��ʼ��
 *	1.����ʱ.
 *  2.��̬��ʼ����.
 * public final���εĳ�Ա�������ܱ���д�����ܱ�����,����
 * 		�в��ܶ����븸��������ͬǩ���Ͳ����б�ĳ�Ա����
 * private final���εĳ�Ա�����������в��ɼ�������������
 *		�ܶ����븸������ͬ����final���εĳ�Ա����,��Щ�����������Լ���
 * */



public class Final {
	static void testFinal1(){
		final int a = 2 + 4;
		final int b = 3 + 5;
		System.out.println(a * b);// 6 * 8 = 48;
		@SuppressWarnings("unused")
		final String str = "���" + "Java";
		final String book = "���Java����: " + 99.0;
		final String book2 = "���Java����: " + String.valueOf(99.0);
		System.out.println(book == "���Java����: 99.0");	//book�Ǻ��滻����,ʵ��������������Ƚ�,true
		System.out.println(book2 == "���Java����: 99.0");	//books�����к��滻,���ñ��������,����false
		System.out.println(book2.equals("���Java����: 99.0"));//true
	}
	static void testFinal2(){
		String s1 = "���java";
		String s2 = "���" + "java";
		System.out.println("s1 == s2 : " + s1 == s2); //false
		
		String str1 = "���";
		String str2 = "java";
		String s3 = str1 + str2;
		System.out.println("s1 == s3 : " + s1 == s3); //false
		
		final String str3 = "���";
		final String str4 = "java";
		String s4 = str3 + str4;
		System.out.println("s1 == s4 : " + s1 == s4 );//false
	}
	
	public static void main(String[] args){
		testFinal1();
		testFinal2();
	}
}
