package basic_grammer;

public class Foreach {
	
	public static void main(String[] args){
		
		String[] books = new String[]{ 
			"������Java EE ��ҵӦ��ʵս",
			"���java����",
			"���android����"	
		};
		
		//ʹ��foreachѭ������������Ԫ�أ�����book�����Զ�����ÿ������Ԫ��
		for(String book:books){
			book = "���Ajax����";
			System.out.println(book);
		}
		
		for(String book:books){
			System.out.println(book);
		}
		
	}
	
	
}
