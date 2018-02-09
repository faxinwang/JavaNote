package array;

import java.util.Arrays;
/*
 * Java 8 增强了Arrays类的功能，为Arrays增加了一些工具方法,
 * 这些方法充分利用了cpu并行计算的能力来提高设值，排序的性能。
 * 这些方法均以parallel开头.
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
			//left代表数组中前一个元素的下标，计算第一个元素是left为1
			//right代表数组中当前元素的下标
			public int applyAsInt(int left,int right){
				return left*right;
			}
		});
		System.out.println(Arrays.toString(arr));
	}
	
	public static void parallelSetAll(){
		int[] arr = new int[5];
		Arrays.parallelSetAll(arr, new IntUnaryOperator(){
			//operand代表正在计算的元素下标
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
