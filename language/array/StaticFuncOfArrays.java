package array;
import java.util.Arrays;

public class StaticFuncOfArrays {
	//使用二分法查询a
	public static void binarySearch_Test(){
		int[] a = {9,5,8,3,5,4,7,8,6,1,2,1,1};
		
		//type[] copyOf(type[] src,int lenth)
		//type[] copyOfRange(type[] src,int start,int end)
		int[] tmp = Arrays.copyOf(a,a.length);
		
		//void sort(type[])
		//void sort(type[],int start,int end)
		Arrays.sort(tmp);
		System.out.println(Arrays.toString(tmp));
		
		//int binarySearch(type[] a,type key)
		//int binarySearch(type[] a,int start,int end,type key)
		//返回key在数组中的下标，如果没找到，则返回负数，数组需要按升序排序
		int idxOf5 = Arrays.binarySearch(tmp,5);
		
		if(idxOf5 < 0){
			System.out.println("5 is not in a");
		}else{
			System.out.println("5 has index "+idxOf5+" in a");
		}
		int idxOf0 = Arrays.binarySearch(tmp,0);
		
		if(idxOf0 < 0){
			System.out.println("0 is not in a");
		}else{
			System.out.println("0 has index "+idxOf0+" in a");
		}
	}
	
	public static void fill_Test(){
		int[] a = {1,1,1,5,6,7};
		int[] b = {2,2,2,5,6,7};
		System.out.println("a: "+Arrays.toString(a));
		System.out.println("b: "+Arrays.toString(b));
		//equals(type[] a,type[] b) 如果数组a,b的长度相等并且所有元素都相等，就返回true
		System.out.println("a==b: "+Arrays.equals(a,b));
		
		//void fill(type[],type val)
		//void fill(type[],int start,int end,type val)
		Arrays.fill(a, 0,3,4);
		Arrays.fill(b, 0,3,4);
		System.out.println("after fill(type[],int start,int end,type val)");
		System.out.println("a: "+Arrays.toString(a));
		System.out.println("b: "+Arrays.toString(b));
		System.out.println("a==b: "+Arrays.equals(a,b));
	}
	
	
	public static void main(String[] args){
		System.out.println("binarySearch_Test():");
		binarySearch_Test();
		System.out.println("\nfill_Test():");
		fill_Test();
		
	}
	
}
