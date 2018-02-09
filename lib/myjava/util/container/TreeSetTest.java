package myjava.util.container;
import static java.lang.System.*;

import java.math.BigInteger;
import java.util.TreeSet;

/* TreeSet��SortedSet�ӿڵ�ʵ����,�ɽӿ����ֿ��Կ���,TreeSet���Ա�֤Ԫ���������.
 * ��ʵ��TreeSet�Լ������TreeMap������"�����"��ʵ�ֵ�,������Ƕ��ܹ���֤Ԫ�ص�����.
 * TreeSet�ṩ�����¼�������ķ���:
 * Comparator comparator():���TreeSet�����˶���������÷������ض���������ʹ�õ�Comparator�����򷵻�null
 * Object first():���ؼ����е�һ��Ԫ��
 * Object last():���ؼ��������һ��Ԫ��
 * Object lower(Object o): ���ؼ����б�oС�����Ԫ��(o���Բ��Ǽ����е�Ԫ��)
 * Object higher(Obejct o): ���ؼ����б�o�����СԪ��(o���Բ��Ǽ����е�Ԫ��)
 * SortedSet subSet(Object start,Object end): �����Ӽ�[start,end)
 * SortedSet headSet(Object end):���ؼ�����С��end������Ԫ��
 * SortedSet tailSet(Object start):���ؼ����д��ڵ���start������Ԫ��
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
		out.println(nums);//���Կ���Ԫ���Ѿ���������״̬
		out.println("��һ��Ԫ��:" + nums.first());
		out.println("���һ��Ԫ��:" + nums.last());
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
