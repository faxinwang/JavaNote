package array;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class Array2D{
	
	public static void main(String[] args){
		//����һ����ά����
		int [][] a; 
		//�Ѷ�ά���鵱��һά�����ʼ��Ϊ����Ϊ4������
		a = new int[4][];
		for(int[] tmp:a) {
			System.out.println(tmp);
			//�����4��null
		}
		//��ÿһ����Ϊ�����Ӧ�ó�ʼ��Ϊһ������Ϊi+1������
		for(int i =  0 ; i < a.length ; ++i){
			a[i] = new int[i+1]; 
			
			Arrays.setAll(a[i], new IntUnaryOperator(){
				@Override
				public int applyAsInt(int operand) {
					return operand + 1;
				}
				
			});
		}
		
		for(int i = 0 ,len1 = a.length; i< len1 ;++i){
			for(int j=0 , len2 = a[i].length ; j< len2; ++j){
				System.out.print(a[i][j] + " ");
			}
			System.out.println("");
		}
		
	}
}