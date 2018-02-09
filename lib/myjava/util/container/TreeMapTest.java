package myjava.util.container;

import static java.lang.System.*;

import java.util.TreeMap;

/* TreeSet是sortedMap接口的实现类,它提供了下面一下方法: 
 * Map.Entry 	firstEntry();
 * Object 		firstKey();
 * Object		lastKey();
 * Map.Entry	higherEntry(Object key);
 * Map.Entry	lowerEntry(Object key);
 * Object		higherKey(Object key);
 * Object		lowerKey(Object key);
 * NavigableMap	subMap(Object begKey,boolean begInclusive,Object endkey ,boolean endInclusive);
 * SortedMap	subMap(Object begkey,Object endkey);
 * SortedMap	headMap(Object endkey);
 * SortedMap	headMap(Object endkey,boolean endInclusive);
 * SortedMap	tailMap(Object begKey);
 * SortedMap	tailMap(Object begKey,boolean begInclusive);
 * 
 * 表面上看起来这些方法很复杂，其实他们很简单，因为TreeMap中的元素是有序的，
 * 所有增加了访问第一个，前一个，后一个，最后一个元素的方法.并提供了几个截取
 * 子TreeMap的方法
 */

public class TreeMapTest {
	static class R implements Comparable<R>{
		int count;
		public R(int count){
			this.count = count;
		}
		public String toString(){
			return "R[count:" + count + "]";
		}
		public boolean equasl(Object obj){
			if(this == obj) return true;
			if(obj!=null && obj.getClass() == R.class){
				R r = (R)obj;
				return this.count == r.count;
			}
			return false;
		}
		public int compareTo(R r){
			return count > r.count? 1:
					count < r.count? -1: 0;
		}
	}
	
	public static void main(String[] args){
		TreeMap<R,String> tmap = new TreeMap<R,String>();
		tmap.put(new R(3),"str3");
		tmap.put(new R(5), "str5");
		tmap.put(new R(7), "str7");
		tmap.put(new R(9), "str9");
		tmap.put(new R(-5), "str5");
		tmap.put(new R(-9), "str9");
		tmap.forEach((key, value) -> out.println(key + ","+value));
		out.println("firstEntry:"+tmap.firstEntry());
		out.println("lastEntry:"+tmap.lastEntry());
		out.println("firstKey:"+tmap.firstKey());
		out.println("lastKey:"+tmap.lastKey());
		out.println("higherKey(new R(3)):"+ tmap.higherKey(new R(3)));
		out.println("higherEntry(new R(5)):"+ tmap.higherEntry(new R(5)));
		out.println("subMap(new R(-1),new R(5)):" + tmap.subMap(new R(-1), new R(5)));
		out.println("headMap(new R(5)):" + tmap.headMap( new R(5)));
		out.println("tailMap(new R(5)):" + tmap.tailMap( new R(5)));
	}
}
