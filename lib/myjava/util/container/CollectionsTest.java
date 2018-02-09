package myjava.util.container;

import static java.lang.System.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/* Collections提供了如下类方法用于对List集合进行操作
 * void reverse(List);
 * void shuffle(List);
 * void sort(List);
 * void sort(List,Comparator);
 * void swap(List lst,int i,int j);
 * void rotate(List,int distance);
 * 
 * 查找，替换操作
 * int binarySearch(List lst,Object key);
 * Object max(Collection c);
 * Object max(Collection c,Comparator cmp);
 * Object min(Collection c);
 * Object min(Collection c,Comparator cmp);
 * void fill(List lst,Object obj);
 * int frequency(Collection c,Object o);
 * int indexOfSubList(List src,List tag)如果不存在返回-1
 * int lastIndexOfSubList(List src,List tag)如果不存在返回-1
 * boolean replaceAll(List lst,Object oldValue,Object newValue);
 */

public class CollectionsTest {
	static void test1(){
		ArrayList<Integer> intArr = new ArrayList<Integer>();
		Random rand = new Random();
		for(int i=0;i<10;++i){
			intArr.add(rand.nextInt(100));
		}
		out.println("before sort:"+intArr);
		Collections.sort(intArr);
		out.println("after sort:"+intArr);
		out.println("index of 50:" + Collections.binarySearch(intArr, 50));
		Collections.rotate(intArr, 5);
		out.println("after rotate(5):" + intArr);
		Collections.shuffle(intArr);
		out.println("after shuffle():" + intArr);
		out.println("max elem:" + Collections.max(intArr));
		out.println("min elem:" + Collections.min(intArr));
		out.println("frequency(intArr.get(0)):" + Collections.frequency(intArr, intArr.get(0)));
	}
	public static void main(String[] args){
		test1();
	}
}
