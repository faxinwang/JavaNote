package array;

public class ArrayLength {
	public static void main(String[] args){
		//使用静态初始化
		int[] a = {5,7,10};
		//使用动态初始化
		int[] b = new int[4];
		
		System.out.println("b数组的长度: " + b.length);
		
		for(int i = 0 , len = a.length ; i < len ; ++i){
			System.out.print(a[i]);
		}
		System.out.println("");
		for(int i = 0 , len = b.length ; i < len ; ++i){
			System.out.print(b[i]);
		}
		System.out.println("");
		//将引用a赋值给引用b,之后a和b将指向同一块内存,b之前指向的内存将变成垃圾，等待垃圾回收器回收
		b = a;
		System.out.println("b数组的长度为: " + b.length);
	}
	
}
