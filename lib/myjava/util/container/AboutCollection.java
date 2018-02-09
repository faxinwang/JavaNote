package myjava.util.container;

import static java.lang.System.*;
import java.util.ArrayList;
import java.util.List;

/*Collection
 * 	|------>Set(无序集合，元素不可重复)
 * 	|		 |------>SortedSet----->>>TreeSet
 * 	|	 	 |------>>>EnumSet
 * 	|	 	 |------>>>HashSet----->>>LinkedHashSet
 *  |		
 *  |------>List(无序集合，元素可以重复)
 *  |		 |----->>>Vector(过时)------>>>Stack(过时)
 *  |		 |----->>>ArrayList
 *  |		 |-----------------/---->>>LinkedList(同时实现了List接口和Deque接口)
 *  |					      /
 *  |------>Queue(队列)		 /
 *   		 |----->Deque   /
 *  		 |		  |----/--->>>ArrayDeque
 * 			 |----------------->>>PriorityQueue
 * 			
 */

/*collection接口里定义的方法:
 * boolean add(Object o): adds an elem to collection,returns true if collection is modified. 
 * boolean addAll(Collection c): adds all elem of c,returns true if collection is modified.
 * void    clear(): makes the collection empty(size to be 0).
 * boolean contains(Object o):returns true if contains o.
 * boolean containsAll(Collection c): returns true if contains all elem of c.
 * boolean isEmpty(): returns true if collection is empty.
 * Iterator iterator(): returns a iterator.
 * boolean remove(Object o): removes the first o and returns true.
 * boolean removeAll(Collection c): removes elems which are also in c,if removed any,return true.
 * boolean retainAll(Collection c):	removes elems which are not in c,if removed any,return true.
 * int 	   size(): returns the number of elems in the collection
 * Object[] toArray(): convert the collection into a array.
 */

public class AboutCollection {
	static void testArrayList(){
		List<String> list = new ArrayList<String>();
		list.add(new String("str1"));
		list.add(new String("str2"));
		list.add(1,"str3"); //add "str2" in index 1(index start from 0)
		list.add("str2");
		list.add("str3");
		out.println(" list:"+list);//list allows duplicate
		list.remove("str3");
		out.println(" list:"+list);
		out.println("list.contains(\"str1\") :" + list.contains("str1"));//true
		
		List<String> list2 = new ArrayList<String>();
		list2.add("str2");
		list2.add("str1");
		out.println(" list2: "+ list2);
		out.println("list.containsAll(list2):"+list.containsAll(list2));//true
		list2.add("str4");
		out.println(" list2: "+ list2);
		out.println("list.containsAll(list2):"+list.containsAll(list2));//true
		list.removeAll(list2);
		out.println("after list.removeAll(list2)\n list:" + list);
		out.println("list.isEmpty():" + list.isEmpty());
		list.clear();
		out.println("after list.clear();\n list:" + list);
		out.println("list.isEmpty():" + list.isEmpty());
		
	}
	public static void main(String[] args){
		testArrayList();
	}
}
