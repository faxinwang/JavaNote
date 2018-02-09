package array;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

public class Array2D{
	
	public static void main(String[] args){
		//定义一个二维数组
		int [][] a; 
		//把二维数组当做一维数组初始化为长度为4的数组
		a = new int[4][];
		for(int[] tmp:a) {
			System.out.println(tmp);
			//将输出4个null
		}
		//将每一个以为数组的应用初始化为一个长度为i+1的数组
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