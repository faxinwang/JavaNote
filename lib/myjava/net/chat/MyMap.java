package myjava.net.chat;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyMap<K, V> {
	//����һ���̰߳�ȫ��HashMap
	public Map<K,V> map = Collections.synchronizedMap(new HashMap<K,V>());
	//����value��ɾ��ָ����
	public synchronized void removeByValue(Object value){
		for(Object key:map.keySet()){
			if(map.get(key) == value){
				map.remove(key);
				break;
			}
		}
	}
	
	//��ȡ����value��ɵ�set����
	public synchronized Set<V> valueSet(){
		Set<V> result = new HashSet<V>();
		//��map������value���ӵ�result������
		map.forEach((key,value) -> result.add(value));
		return result;
	}
	
	//����value����key
	public synchronized K getKeyByValue(V val){
		//��������key��� �ļ���
		for(K key:map.keySet()){
			//���key���ж�Ӧ��value�뱻������value��ͬ,�򷵻ض�Ӧ��key
			if(map.get(key) == val || map.get(key).equals(val)){
				return key;
			}
		}
		return null;
	}
	
	//ʵ��put����,�÷���������value�ظ�
	public synchronized V put(K key,V value){
		//��������value��ɵļ���
		for(V val:valueSet()){
			//���ĳ��value����ͼ�����value��ͬ,���׳�һ��RuntimeException�쳣
			if(val.equals(value) && val.hashCode()==value.hashCode()){
				throw new RuntimeException("MyMap�в��������ظ�value");
			}
		}
		return map.put(key, value);
	}
}