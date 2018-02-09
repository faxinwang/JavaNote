package myjava.util.container;

import static java.lang.System.*;

import java.util.TreeMap;

/* TreeSet��sortedMap�ӿڵ�ʵ����,���ṩ������һ�·���: 
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
 * �����Ͽ�������Щ�����ܸ��ӣ���ʵ���Ǻܼ򵥣���ΪTreeMap�е�Ԫ��������ģ�
 * ���������˷��ʵ�һ����ǰһ������һ�������һ��Ԫ�صķ���.���ṩ�˼�����ȡ
 * ��TreeMap�ķ���
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
