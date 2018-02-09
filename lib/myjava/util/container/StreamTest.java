package myjava.util.container;

import java.util.LinkedList;
import java.util.stream.IntStream;
import static java.lang.System.*;

/* Java 8 ������Stream,IntStream,LongStream,DoubleStream����ʽAPI,
 * ���ĸ��ӿ���,Stream��һ��ͨ�����ӿ�.����ʹ��Stream�Ĳ�������:
 * 1.ʹ��Stream��builder()�෽��������Stream��Ӧ��Builder��
 * 2.�ظ�����Builder��add()�����ȸ�·������Ӷ��Ԫ��
 * 3.����Builder��build()������ȡ��Ӧ��Stream��
 * 4.����Stream�ľۼ�����.
 */

/* Stream�ĳ����м䷽��:
 * filter(Predicate pre):�������в�����pre��Ԫ��
 * mapToXxx(ToXxxFunction mapper):ʹ��ToXxxFunction�����е�Ԫ��ִ��һ��һ��ת��,������ת��ֵ��ɵ�����
 * peek(Consumer action): �÷������ص�����ԭ����������ͬ��Ԫ�أ��÷�����Ҫ���ڵ���.
 * distinct():�÷��������ų����������ظ���Ԫ��(ʹ��equals�Ƚ����),����һ����״̬�ķ���
 * sorted():�÷������ڱ�֤���е�Ԫ���ں����ķ����д�������״̬������һ����״̬�ķ���
 * limit(long maxSize):�÷������ڱ�֤�����ĺ������������������ʵ�Ԫ�ظ���������һ����״̬�ģ���·������
 * 
 * Stream�ĳ���ĩ�˷���:
 * forEach(Consumer action):����������Ԫ��ִ��action
 * toArray():����������Ԫ��ת��Ϊһ������
 * reduce():�÷������������صİ汾��������ͨ��ĳ�ֲ������ϲ����е�Ԫ��.
 * min(): ������������Ԫ�ص���Сֵ
 * max(): ������������Ԫ�ص����ֵ
 * count(): ������������Ԫ�صĸ���
 * anyMatch(Predicate p):�ж������Ƿ����ٰ���һ��Ԫ�ط���Predicate����
 * allMatch(Predicate P):�ж������Ƿ�ÿ��Ԫ�ض�����Predicate����
 * noneMatch(Predicate P): �ж������Ƿ�����Ԫ�ض�������Predicate����
 * findFirst():�������еĵ�һ��Ԫ��
 * findAny(): �������е�����һ��Ԫ��
 */
public class StreamTest {
	static void testStream1(){
		IntStream is = IntStream.builder()
			.add(20).add(13).add(-2).add(18).build();
	//	out.println("is����Ԫ�ص����ֵ:" + tmp.max().getAsInt());
	//	out.println("is����Ԫ�ص���Сֵ:" + is.min().getAsInt());
	//	out.println("is����Ԫ���ܺ�:" + is.sum());
	//	out.println("is����Ԫ�ظ���:" + is.count());
	//	out.println("is����Ԫ�ص�ƽ��ֵ:" + is.average());
	//	out.println("is�Ƿ�����κ�Ԫ�ص�ƽ���ʹ���30:" + is.anyMatch(elem -> elem*elem > 30));
		IntStream newIs = is.map(elem -> elem * 2 + 1); //����ǰStreamӳ�����һ��Stream
		newIs.forEach(out::println);
	}
	
	static void testStream2(){
		LinkedList<String> books = new LinkedList<String>();
		books.add("������Java EE��ҵӦ��ʵս");
		books.add("���Java����");
		books.add("���Ajax����");
		books.add("���Android����");
		books.add("���ios����");
		books.forEach(out::println);
		out.println("��������\"���\"�ִ�������:" + books.stream()
				.filter(elem -> elem.contains("���"))
				.count()+ "��");
		out.println("��������\"Java\"�ִ�������:" + books.stream()
				.filter(elem -> elem.contains("Java"))
				.count()+ "��");
		out.println("�������ȴ���10������:" + books.stream()
				.filter(elem -> elem.length() > 10)
				.count() + "��");
		out.println("�������ĳ���Ϊ:");
		books.stream()
			.mapToInt(elem -> elem.length())
			.forEach(elem->out.print(elem + " "));
	}
	
	public static void main(String[] args){
		testStream1();
		testStream2();
	}
}
