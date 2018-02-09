package myjava.util.container;
import static java.lang.System.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* Map
 * 	|-------->>>EnumMap(盛装枚举类的Map)
 *  |-------->>>WeakHashMap(弱引用Map)
 *  |-------->>>IdentityHashMap(用==判等,而不是用equals和hashCode)
 *  |-------->>>HashTable-------->>>Properties(属性文件)
 *  |-------->>>HashMap---------->>>LinkedHashMap(记录插入顺序的hashMap)
 *  |-------->SortedMap
 *  |			|--------->>>TreeMap(有序的Map)
 */


/* Map所定义的接口:
 * void 	clear():makes the map empty
 * boolean	containsKey(Object Key): return true if key is in the map
 * boolean	containsValue(Object value): return true if map contains value
 * boolean	isEmpty(): return true if the map is empty
 * Set	 	entrySet(): return a Entry set consisting of all key-value pair
 * Object	get(Object key): return the value mapped by the key
 * Set		keySet(): return a set of all keys of the map
 * Object	put(Object key,Object value): add or override a key-value pair to the map
 * void 	putAll(Map m): add all elem of m
 * Object	remove(Object key): remove the elem with this.key == key and returns the old value or null
 * int		size(): returns the number of elements of the map
 * Collection values(): return a collection consisting of all values of the map
 */

/* Map 中包含了一个内部类Entry,该类封装了key-value对，它包含如下三个方法:
 * Object 	getKey(): return the key of the entry
 * Object 	getValue(): return the value of the entry
 * Object	setValue(Object v): set the value of the entry
 */

/* 下面是Java 8 为Map新增的方法:
 * Object 	compute(Object key,BiFunc remappingFunc);
 * Object	computeIfAbsent(Object key, Func mappingFunc);
 * Object	computeIfPresent(Object key, BiFunc remappingFunc);
 * void		forEach(BiComsumer action);
 * Object	getOrDefault(Object key,Object defaultValue);
 * Object	merge(Object key, Object value, BiFunc remappingFunc);
 * Object	putIfAbsent(Object key, Object value);
 * Object	replace(Object key, Object value);
 * boolean	replace(Object key, Object oldValue,Object newValue);
 * Object	replaceAll(BiFunction);
 */

public class AboutMap {
	static void test1(){
		HashMap<String,Integer> map = new LinkedHashMap<String, Integer>();
	//	HashMap<String,Integer> map = new HashMap<String, Integer>();
		map.put("疯狂java讲义",109);
		map.put("疯狂ios讲义", 99);
		map.put("疯狂Ajax讲义", 79);
		out.println(map);
		map.replace("疯狂xml讲义", 66);
		out.println(map);
		map.merge("疯狂ios讲义", 10, (oldValue,newValue) -> oldValue + newValue);
		map.merge("疯狂android讲义", 10, (oldValue,newValue) -> oldValue + newValue);
		out.println(map);
		//当key "java"对应的value为null或key不存在时，使用计算的结果作为新value
		map.computeIfAbsent("java",key -> key.length());
		out.println(map);
		//map: java=4 -> java=16
		map.computeIfPresent("java", (key,value) -> value * value);
		out.println(map);
	}
	
	public static void main(String[] args){
		test1();
	}
}
