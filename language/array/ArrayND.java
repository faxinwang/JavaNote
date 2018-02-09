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
		//����һ����ά����,����Ԫ��ΪObject����
		Object[][] obj = new Object[3][];
		
		//ÿһ������Ԫ�ض���һ��String����
		obj[0] = new String[3];
		obj[1] = new String[3];
		obj[2] = new String[3];

		//������Ԫ�ظ�ֵ
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
		//����һ����ά����
		Object[][][] obj = new Object[2][][];
		//ÿһ��Ϊһ����ά����
		obj[0] = new Object[2][];
		obj[1] = new Object[2][];
		
		//��һ���һάΪString����
		obj[0][0] = new String[3];
		//��һ��ڶ�άΪPoint����
		obj[0][1] = new Point[3];
		//����һ���һάԪ�ظ�ֵ
		obj[0][0][0] = "love";
		obj[0][0][1] = "you";
		obj[0][0][2] = "die";
		//�����һ���һά������
		System.out.println(Arrays.toString(obj[0][0]));
		
		//����һ��ڶ�άԪ�ظ�ֵ
		obj[0][1][0] = new Point(0,1,0);
		obj[0][1][1] = new Point(0,1,1);
		obj[0][1][2] = new Point(0,1,2);
		//�����һ��ڶ�ά������
		System.out.println(Arrays.toString(obj[0][1]));
		
		
		//�ڶ����һάΪ����Ϊ2��String����
		obj[1][0] = new String[2];
		//�ڶ���ڶ�άΪ����Ϊ2��Point����
		obj[1][1] = new Point[2];
		
		//���ڶ����һάԪ�ظ�ֵ
		obj[1][0][0] = "li";
		obj[1][0][1] = "die";
		//����ڶ����һά����Ԫ��
		System.out.println(Arrays.toString(obj[1][0]));
		//���ڶ���ڶ�άԪ�ظ�ֵ
		obj[1][1][0] = new Point(1,1,0);
		obj[1][1][1] = new Point(1,1,1);
		//����ڶ���ڶ�ά����Ԫ��
		System.out.println(Arrays.toString(obj[1][1]));
	}
	public static void main(String[] args){
		Array2DTest();
		System.out.println("");
		Array3DTest();
	}
}
