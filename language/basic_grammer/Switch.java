package basic_grammer;


public class Switch {

	public static void switchTest(String season){
		switch(season){
		case "����":
			System.out.println("��ů����");
			break;
		case "����":
			System.out.println("��������");
			break;
		case "����":
			System.out.println("�����ˬ");
			break;
		case "����":
			System.out.println("��ѩ����");
			break;
		default :
			System.out.println("���ڴ���!");
		}
	}
	
	public static void main(String args[]){
		switchTest("����");
		switchTest("����");
	}

}
