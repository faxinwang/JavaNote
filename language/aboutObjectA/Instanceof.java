package aboutObjectA;

public class Instanceof {
	
	public static void main(String[] args){
		//����helloʱʹ�õ���Object�࣬��hello�ı������͵�Object
		//Object��������ĸ��࣬��hello��ʵ��������String
		Object hello = "Hello";
		
		//String �� Object���ڼ̳й�ϵ,���Խ���instanceof����
		System.out.println("�ַ����Ƿ���Objectʵ��:" + (hello instanceof Object));//����true
		System.out.println("�ַ����Ƿ���Stringʵ��:" + (hello instanceof String));//����true
		
		//Math��Object����ڼ̳й�ϵ�����Խ���instanceof ����
		System.out.println("�ַ����Ƿ���Mathʵ��:" + (hello instanceof Math));//����false
		
		//Stringʵ����Comparable�ӿ�,���Է���true
		System.out.println("�ַ����Ƿ���Comparableʵ��:" + (hello instanceof Comparable));//����true
		
		@SuppressWarnings("unused")
		String a = "Hello";
		//String����Math��û�м̳й�ϵ����������Ĵ�������޷�ͨ��
	//	System.out.println("�ַ����Ƿ���Math���ʵ��:" + (a instanceof Math));
	}
	
}
