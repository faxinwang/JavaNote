package array;

import java.util.Arrays;
/*
 * Java 8 ��ǿ��Arrays��Ĺ��ܣ�ΪArrays������һЩ���߷���,
 * ��Щ�������������cpu���м���������������ֵ����������ܡ�
 * ��Щ��������parallel��ͷ.
 * 
 * */

import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;

public class ParallalFuncOfArrays {
	
	public static void parallelSort(){
		int[] arr = {3,-4,25,16,30,18,12,18,17,16};
		//void parallelSort(type[] a,int start,int end)
		Arrays.parallelSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void parallelPrefix(){
		int[] arr = {1,2,3,4,5,6};
		//void parallelPrefix(xxx[] arr, int start, int end, XxxBinaryOperator op)
		Arrays.parallelPrefix(arr, new IntBinaryOperator(){
			//left����������ǰһ��Ԫ�ص��±꣬�����һ��Ԫ����leftΪ1
			//right���������е�ǰԪ�ص��±�
			public int applyAsInt(int left,int right){
				return left*right;
			}
		});
		System.out.println(Arrays.toString(arr));
	}
	
	public static void parallelSetAll(){
		int[] arr = new int[5];
		Arrays.parallelSetAll(arr, new IntUnaryOperator(){
			//operand�������ڼ����Ԫ���±�
			public int applyAsInt(int operand){
				return operand * 5;
			}
		});
		System.out.println(Arrays.toString(arr));
	}
	
	
	public static void main(String[] args){
		parallelSort();
		parallelPrefix();
		parallelSetAll();
	}
}
