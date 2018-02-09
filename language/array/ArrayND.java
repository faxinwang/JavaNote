package array;
import java.util.Arrays;

class Point{
	public int x,y,z;
	public Point(int x,int y,int z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public String toString(){
		String str= "("+ x +","+ y+","+z+")" ;
		return str;
	}
}

public class ArrayND {
	public static void Array2DTest(){
		//创建一个二维数组,数组元素为Object数组
		Object[][] obj = new Object[3][];
		
		//每一个数组元素都是一个String数组
		obj[0] = new String[3];
		obj[1] = new String[3];
		obj[2] = new String[3];

		//给数组元素赋值
		obj[0][0] = "1";
		obj[0][1] = "2";
		obj[0][2] = "3";
		System.out.println(Arrays.toString(obj[0]));
		
		obj[1][0] = "love";
		obj[1][1] = "you";
		obj[1][2] = "die";
		System.out.println(Arrays.toString(obj[1]));
		
		obj[2][0] = "1.1";
		obj[2][1] = "2.2";
		obj[2][2] = "3.3";
		System.out.println(Arrays.toString(obj[2]));
	}
	
	public static void Array3DTest(){
		//创建一个三维数组
		Object[][][] obj = new Object[2][][];
		//每一层为一个二维数组
		obj[0] = new Object[2][];
		obj[1] = new Object[2][];
		
		//第一层第一维为String数组
		obj[0][0] = new String[3];
		//第一层第二维为Point数组
		obj[0][1] = new Point[3];
		//给第一层第一维元素赋值
		obj[0][0][0] = "love";
		obj[0][0][1] = "you";
		obj[0][0][2] = "die";
		//输出第一层第一维的数组
		System.out.println(Arrays.toString(obj[0][0]));
		
		//给第一层第二维元素赋值
		obj[0][1][0] = new Point(0,1,0);
		obj[0][1][1] = new Point(0,1,1);
		obj[0][1][2] = new Point(0,1,2);
		//输出第一层第二维的数组
		System.out.println(Arrays.toString(obj[0][1]));
		
		
		//第二层第一维为长度为2的String数组
		obj[1][0] = new String[2];
		//第二层第二维为长度为2的Point数组
		obj[1][1] = new Point[2];
		
		//给第二层第一维元素赋值
		obj[1][0][0] = "li";
		obj[1][0][1] = "die";
		//输出第二层第一维数组元素
		System.out.println(Arrays.toString(obj[1][0]));
		//给第二层第二维元素赋值
		obj[1][1][0] = new Point(1,1,0);
		obj[1][1][1] = new Point(1,1,1);
		//输出第二层第二维数组元素
		System.out.println(Arrays.toString(obj[1][1]));
	}
	public static void main(String[] args){
		Array2DTest();
		System.out.println("");
		Array3DTest();
	}
}
