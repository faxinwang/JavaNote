package myjava.util.container;

import static java.lang.System.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;

/* Iterator�ӿ��ﶨ��������4������:
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
		hset.forEach(elem-> out.println("ʹ��forEach��������Ԫ��:" + elem));
		out.println("��[30,70)�������Ԫ����");
		hset.stream().filter(elem -> 30<=elem && elem<70).forEach(out::println);
	}
	static void test2(){
		LinkedHashSet<String> lhset = new LinkedHashSet<String>();
		lhset.add(new String("������Java EE��ҵӦ��ʵս"));
		lhset.add(new String("���java����"));
		lhset.add(new String("���Ajax����"));
		lhset.add(new String("���android����"));
		lhset.add(new String("һ���ܳ��ܳ��ܳ��ܳ��ܳ��ܳ����ַ���"));
		out.println(lhset);
		//ʹ��lembda���ʽ(Ŀ��������Predicate)������
		lhset.removeIf(elem -> ((String)elem).length() < 15);
		out.println(lhset);
	}
	static void test3(){
		//LinkedHashSet������ά����Ԫ�صĲ���˳��,��˱���ʱ�ǰ�����˳����������ٶȱ�hashSet��,
		//��Ϊ������ֱ���ҵ���һ��Ԫ�ص�λ�ã�������Ҫ���α���ÿһ������Ԫ��
		//LinkedHashSet��Ȼ��set,����������������ظ�Ԫ��
		LinkedHashSet<String> lhset = new LinkedHashSet<String>();
		lhset.add("���Java����");
		lhset.add("���Android����");
		out.println(lhset);
		lhset.remove("���Java����");
		lhset.add("���ios����");
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
