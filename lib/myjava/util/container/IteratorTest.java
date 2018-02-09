package myjava.util.container;

import static java.lang.System.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

/* Iterator接口里定义了如下4个方法:
 * boolean  hasNext(): returns true if there remains element not reached
 * Object   next(): returns the next element
 * void 	remove(): removes the last element
 * void		forEachRemaining(Consumer action) this function is add by java 8,
 * 			it can use lambda expression to iterate the collection
 */

public class IteratorTest {
	
	static void test1(){
		HashSet<Integer> hset = new HashSet<Integer>();
		Random rand = new Random();
		for(int i=0;i<10;++i){
			hset.add(rand.nextInt(100));
		}
	//	hset.forEach(out::println);
		hset.forEach(elem-> out.println("使用forEach迭代集合元素:" + elem));
		out.println("在[30,70)区间里的元素有");
		hset.stream().filter(elem -> 30<=elem && elem<70).forEach(out::println);
	}
	static void test2(){
		LinkedHashSet<String> lhset = new LinkedHashSet<String>();
		lhset.add(new String("轻量级Java EE企业应用实战"));
		lhset.add(new String("疯狂java讲义"));
		lhset.add(new String("疯狂Ajax讲义"));
		lhset.add(new String("疯狂android讲义"));
		lhset.add(new String("一个很长很长很长很长很长很长的字符串"));
		out.println(lhset);
		//使用lembda表达式(目标类型是Predicate)过滤器
		lhset.removeIf(elem -> ((String)elem).length() < 15);
		out.println(lhset);
	}
	static void test3(){
		//LinkedHashSet用链表维护了元素的插入顺序,因此遍历时是按出入顺序遍历的且速度比hashSet快,
		//因为它可以直接找到下一个元素的位置，而不需要依次遍历每一个数组元素
		//LinkedHashSet依然是set,所以它不允许包含重复元素
		LinkedHashSet<String> lhset = new LinkedHashSet<String>();
		lhset.add("疯狂Java讲义");
		lhset.add("疯狂Android讲义");
		out.println(lhset);
		lhset.remove("疯狂Java讲义");
		lhset.add("疯狂ios讲义");
		out.println(lhset);
	}
	public static void main(String[] args){
		test1();
		out.println();
		test2();
		out.println();
		test3();
	}
}
