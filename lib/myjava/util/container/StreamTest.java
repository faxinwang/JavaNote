package myjava.util.container;

import java.util.LinkedList;
import java.util.stream.IntStream;
import static java.lang.System.*;

/* Java 8 新增了Stream,IntStream,LongStream,DoubleStream等流式API,
 * 这四个接口中,Stream是一个通用流接口.独立使用Stream的步骤如下:
 * 1.使用Stream的builder()类方法创建该Stream对应的Builder；
 * 2.重复调用Builder的add()方法先该路流中添加多个元素
 * 3.调用Builder的build()方法获取对应的Stream。
 * 4.调用Stream的聚集方法.
 */

/* Stream的常用中间方法:
 * filter(Predicate pre):过滤流中不符合pre的元素
 * mapToXxx(ToXxxFunction mapper):使用ToXxxFunction对流中的元素执行一对一的转换,返回有转换值组成的新流
 * peek(Consumer action): 该方法返回的流与原有流包含相同的元素，该方法主要用于调试.
 * distinct():该方法用于排除流中所有重复的元素(使用equals比较相等),这是一个有状态的方法
 * sorted():该方法用于保证流中的元素在后续的访问中处于有序状态，这是一个有状态的方法
 * limit(long maxSize):该方法用于保证对流的后续访问中最大允许访问的元素个数，这是一个有状态的，短路方法。
 * 
 * Stream的常用末端方法:
 * forEach(Consumer action):对流中所有元素执行action
 * toArray():将流中所有元素转换为一个数组
 * reduce():该方法有三个重载的版本，都用于通过某种操作来合并流中的元素.
 * min(): 返回流中所有元素的最小值
 * max(): 返回流中所有元素的最大值
 * count(): 返回流中所有元素的个数
 * anyMatch(Predicate p):判断流中是否至少包含一个元素符合Predicate条件
 * allMatch(Predicate P):判断流中是否每个元素都符合Predicate条件
 * noneMatch(Predicate P): 判断流中是否所有元素都不符合Predicate条件
 * findFirst():返回流中的第一个元素
 * findAny(): 返回流中的任意一个元素
 */
public class StreamTest {
	static void testStream1(){
		IntStream is = IntStream.builder()
			.add(20).add(13).add(-2).add(18).build();
	//	out.println("is所有元素的最大值:" + tmp.max().getAsInt());
	//	out.println("is所有元素的最小值:" + is.min().getAsInt());
	//	out.println("is所有元素总和:" + is.sum());
	//	out.println("is所有元素个数:" + is.count());
	//	out.println("is所有元素的平均值:" + is.average());
	//	out.println("is是否包含任何元素的平方和大于30:" + is.anyMatch(elem -> elem*elem > 30));
		IntStream newIs = is.map(elem -> elem * 2 + 1); //将当前Stream映射成另一个Stream
		newIs.forEach(out::println);
	}
	
	static void testStream2(){
		LinkedList<String> books = new LinkedList<String>();
		books.add("轻量级Java EE企业应用实战");
		books.add("疯狂Java讲义");
		books.add("疯狂Ajax讲义");
		books.add("疯狂Android讲义");
		books.add("疯狂ios讲义");
		books.forEach(out::println);
		out.println("书名包含\"疯狂\"字串的书有:" + books.stream()
				.filter(elem -> elem.contains("疯狂"))
				.count()+ "本");
		out.println("书名包含\"Java\"字串的书有:" + books.stream()
				.filter(elem -> elem.contains("Java"))
				.count()+ "本");
		out.println("书名长度大于10的书有:" + books.stream()
				.filter(elem -> elem.length() > 10)
				.count() + "本");
		out.println("各书名的长度为:");
		books.stream()
			.mapToInt(elem -> elem.length())
			.forEach(elem->out.print(elem + " "));
	}
	
	public static void main(String[] args){
		testStream1();
		testStream2();
	}
}
