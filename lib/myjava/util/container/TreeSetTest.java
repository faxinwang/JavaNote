package myjava.util.container;
import static java.lang.System.*;

import java.math.BigInteger;
import java.util.TreeSet;

/* TreeSet是SortedSet接口的实现类,由接口名字可以看出,TreeSet可以保证元素是有序的.
 * 事实上TreeSet以及后面的TreeMap都是用"红黑树"来实现的,因此它们都能够保证元素的有序.
 * TreeSet提供了如下几个额外的方法:
 * Comparator comparator():如果TreeSet采用了定制排序，则该方法返回定制排序所使用的Comparator，否则返回null
 * Object first():返回集合中第一个元素
 * Object last():返回集合中最后一个元素
 * Object lower(Object o): 返回集合中比o小的最大元素(o可以不是集合中的元素)
 * Object higher(Obejct o): 返回集合中比o大的最小元素(o可以不是集合中的元素)
 * SortedSet subSet(Object start,Object end): 返回子集[start,end)
 * SortedSet headSet(Object end):返回集合中小于end的所有元素
 * SortedSet tailSet(Object start):返回集合中大于等于start的所有元素
 */


public class TreeSetTest {
	static void test1(){
		TreeSet<Integer> nums = new TreeSet<Integer>();
		nums.add(5);
		nums.add(2);
		nums.add(10);
		nums.add(-9);
		nums.add(15);
		nums.add(-1);
		out.println(nums);//可以看到元素已经处于有序状态
		out.println("第一个元素:" + nums.first());
		out.println("最后一个元素:" + nums.last());
		out.println("nums.headSet(11):" + nums.headSet(11));
		out.println("nums.tailSet(5):" + nums.tailSet(5));
		out.println("nums.subSet(2,11):" + nums.subSet(2, 11));
	} 
	
	static void test2(){
		TreeSet<BigInteger> bigInts = new TreeSet<BigInteger>();
		bigInts.add(new BigInteger("1111111111111111"));
		bigInts.add(new BigInteger("9999999999999999"));
		bigInts.add(new BigInteger("6666666666666666"));
		bigInts.add(new BigInteger("4444444444444444"));
		bigInts.add(new BigInteger("7777777777777777"));
		bigInts.add(new BigInteger("1111111111111111"));//add the same value twice,be ignored
		out.println(bigInts);
		bigInts.add( bigInts.first().add( bigInts.last()) );
		bigInts.add( bigInts.first().subtract( bigInts.last()) );
		bigInts.add( bigInts.first().multiply(bigInts.last()) );
		bigInts.add( bigInts.first().divide(bigInts.last()) );
		bigInts.add( bigInts.first().mod(bigInts.last()) );
		bigInts.add( bigInts.first().pow(3) );
		out.println(bigInts);
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
