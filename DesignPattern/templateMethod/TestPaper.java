package templateMethod;

/* Paper
 *   |--->TestPaperA
 * 	 |--->TestPaperB
 */

//����ģ���࣬������һ������ģ�巽��,ģ�巽�������ɳ��󷽷�(Ҳ���Լ��Ͼ��巽��)��ɶ����߼�
abstract class Paper {
	//���巽��1
	public void TestQuestion1() {
		String question = "����õ����������˹��������������콣��������������������:\n"
				+ "a.��ĥ����\t b.�����\t c.���ٺϽ��\t d.̼��ά��";
		System.out.println(question);
		System.out.println("��:" + Answer1() + "\n");
	}
	//���巽��2
	public void TestQuestion2() {
		String question = "�������Ӣ��½��˫�������黨�����: \n"
				+ "a.ʹ����ֲ�ﲻ�ں���\t b.ʹһ����ϧ�������\t c.�ƻ����Ǹ�����Ȧ����̬ƽ��\t d.��ɸõ���ɳĮ��";
		System.out.println(question);
		System.out.println("��:" + Answer2()+ "\n");
	}
	//���巽��3
	public void TestQuestion3() {
		String question = "�������ʹ��ɽʦͽ���ҹ�����Ż�²�ֹ��������Ǵ�򣬻�����ǿ�ʲôҩ:\n"
				+ "a.��˾ƥ��\t b.ţ�ƽⶾƬ\t c.������\t d.�����Ǻʹ�������ţ��\t e.����ȫ����";
		System.out.println(question);
		System.out.println("��:" + Answer3()+ "\n");
	}
	
	abstract String Answer1();//���󷽷�1
	abstract String Answer2();//���󷽷�2
	abstract String Answer3();//���󷽷�3
}

//������A
class TestPaperA extends Paper{
	//TestPaperA���������󷽷���ʵ��
	public String Answer1() {return "b";}
	public String Answer2() {return "a";}
	public String Answer3() {return "c";}
}
//������B
class TestPaperB extends Paper{
	//TestPaperB���������󷽷���ʵ��
	public String Answer1() {return "c";}
	public String Answer2() {return "b";}
	public String Answer3() {return "a";}
}

public class TestPaper{
	public static void main(String[] args) {
		Paper studentA = new TestPaperA();
		Paper studentB = new TestPaperB();
		//���ڸ�������
		System.out.println("StudentA�Ĵ��");
		studentA.TestQuestion1();
		studentA.TestQuestion2();
		studentA.TestQuestion3();
		System.out.println("StudentB�Ĵ��");
		studentB.TestQuestion1();
		studentB.TestQuestion2();
		studentB.TestQuestion3();
	}
}