package array;

public class ArrayLength {
	public static void main(String[] args){
		//ʹ�þ�̬��ʼ��
		int[] a = {5,7,10};
		//ʹ�ö�̬��ʼ��
		int[] b = new int[4];
		
		System.out.println("b����ĳ���: " + b.length);
		
		for(int i = 0 , len = a.length ; i < len ; ++i){
			System.out.print(a[i]);
		}
		System.out.println("");
		for(int i = 0 , len = b.length ; i < len ; ++i){
			System.out.print(b[i]);
		}
		System.out.println("");
		//������a��ֵ������b,֮��a��b��ָ��ͬһ���ڴ�,b֮ǰָ����ڴ潫����������ȴ���������������
		b = a;
		System.out.println("b����ĳ���Ϊ: " + b.length);
	}
	
}
