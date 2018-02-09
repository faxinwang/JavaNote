package myjava.util.container;
import static java.lang.System.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

/* Map
 * 	|-------->>>EnumMap(ʢװö�����Map)
 *  |-------->>>WeakHashMap(������Map)
 *  |-------->>>IdentityHashMap(��==�е�,��������equals��hashCode)
 *  |-------->>>HashTable-------->>>Properties(�����ļ�)
 *  |-------->>>HashMap---------->>>LinkedHashMap(��¼����˳���hashMap)
 *  |-------->SortedMap
 *  |			|--------->>>TreeMap(�����Map)
 */


/* Map������Ľӿ�:
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

/* Map �а�����һ���ڲ���Entry,�����װ��key-value�ԣ�������������������:
 * Object 	getKey(): return the key of the entry
 * Object 	getValue(): return the value of the entry
 * Object	setValue(Object v): set the value of the entry
 */

/* ������Java 8 ΪMap�����ķ���:
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
		map.put("���java����",109);
		map.put("���ios����", 99);
		map.put("���Ajax����", 79);
		out.println(map);
		map.replace("���xml����", 66);
		out.println(map);
		map.merge("���ios����", 10, (oldValue,newValue) -> oldValue + newValue);
		map.merge("���android����", 10, (oldValue,newValue) -> oldValue + newValue);
		out.println(map);
		//��key "java"��Ӧ��valueΪnull��key������ʱ��ʹ�ü���Ľ����Ϊ��value
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
