package myjava.util.container;

import static java.lang.System.*;
import java.util.ArrayList;

/* List���ϱ�ʾһ��˳������,List����������һЩ����������������Ԫ�صķ���
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

/* List���ṩ��һ��listIterator()�������ڷ���һ��ListIterator����.
 * ListIterator����������һЩ����ķ���:
 * boolean 	hasPrevious():return true if it has a previous elem
 * Object	previous(): return the last elem
 * void 	add(Object o): insert a elem in at the pos where iterator is referencing
 */

public class AboutList {
	static void test1(){
		ArrayList<String> books = new ArrayList<String>();
		books.add("������Java EE��ҵӦ��ʵս");
		books.add("���Java����");
		books.add("���Android����");
		out.println(books);
		//�����ַ���������뵽�ڶ���λ��
		books.add(1,"���Ajax����");
		out.println(books);
		//ɾ��������Ԫ��
		books.remove(2);
		out.println(books);
		books.addAll(books);//������Ԫ���������
		out.println(books);
		out.println("���Android������±�:"+books.indexOf("���Android����"));
		out.println("���Android��������һ�γ��ֵ��±�:"+books.lastIndexOf("���Android����"));
		//ArrayList�ж�Ԫ�����ֻ��ͨ��equals�Ƚ�
		if(books.remove("������Java EE��ҵӦ��ʵս")){
			out.println("\"������Java EE��ҵӦ��ʵս\" removed once!");
		}
		//����ָ��Ԫ�ص�λ��,�±��0��ʼ
		
		//���ڶ���Ԫ���滻���µ��ַ�������
		books.set(1, "���ios����");
		out.println(books);
		out.println("books��һ���Ӽ�:" + books.subList(1, books.size()-1));//0 <= beg <= end < size()
	}
	static void test2(){
		ArrayList<String> books = new ArrayList<String>();
		books.add("0717-13500002222");
		books.add("0715-13911112222");
		books.add("0255-13622225555");
		books.add("0713-18877445566");
		//���ַ���������������
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
