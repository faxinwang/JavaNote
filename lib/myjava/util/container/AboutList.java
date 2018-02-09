package myjava.util.container;

import static java.lang.System.*;
import java.util.ArrayList;

/* List集合表示一个顺序容器,List集合增加了一些以索引来操作集合元素的方法
 * void 	add(int idx,Object elem):insert elem to idx
 * boolean	allAll(int idx,Collection c):insert all elem of c to idx
 * Object	get(int idx):return the elem at idx
 * int 		indexOf(Object o):return the index of O
 * int		lastIndexOf(Object o): return the last index of O
 * Object	remove(int idx): remove and return the Object at idx
 * Object	set(int idx,Object elem): replace and return the elem at idx 
 * List		subList(int beg,int end):return a subList consisting of range [beg,end)
 * 
 * void replaceAll(unaryOperator operator): reset all element with the result produced by the operator
 * void sort(Comparator c): sort the list according to the given sorting criterion
 * void ensureCapacity(int minCapaicty):reserve some memorys to avoid reallocation
 * void trimToSize():change the internal array's lenth to the size of elements to save some memory
 */

/* List还提供了一个listIterator()方法用于返回一个ListIterator对象.
 * ListIterator对象有下面一些额外的方法:
 * boolean 	hasPrevious():return true if it has a previous elem
 * Object	previous(): return the last elem
 * void 	add(Object o): insert a elem in at the pos where iterator is referencing
 */

public class AboutList {
	static void test1(){
		ArrayList<String> books = new ArrayList<String>();
		books.add("轻量级Java EE企业应用实战");
		books.add("疯狂Java讲义");
		books.add("疯狂Android讲义");
		out.println(books);
		//将新字符串对象插入到第二个位置
		books.add(1,"疯狂Ajax讲义");
		out.println(books);
		//删除第三个元素
		books.remove(2);
		out.println(books);
		books.addAll(books);//将所有元素添加两次
		out.println(books);
		out.println("疯狂Android讲义的下标:"+books.indexOf("疯狂Android讲义"));
		out.println("疯狂Android讲义的最后一次出现的下标:"+books.lastIndexOf("疯狂Android讲义"));
		//ArrayList判断元素相等只是通过equals比较
		if(books.remove("轻量级Java EE企业应用实战")){
			out.println("\"轻量级Java EE企业应用实战\" removed once!");
		}
		//查找指定元素的位置,下标从0开始
		
		//将第二个元素替换成新的字符串对象
		books.set(1, "疯狂ios讲义");
		out.println(books);
		out.println("books的一个子集:" + books.subList(1, books.size()-1));//0 <= beg <= end < size()
	}
	static void test2(){
		ArrayList<String> books = new ArrayList<String>();
		books.add("0717-13500002222");
		books.add("0715-13911112222");
		books.add("0255-13622225555");
		books.add("0713-18877445566");
		//按字符串长度升序排序
		books.sort((e1,e2) -> e1.compareTo(e2));;
		out.println(books);
		books.replaceAll(elem -> elem.substring(5,elem.length()));
		out.println(books);
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
