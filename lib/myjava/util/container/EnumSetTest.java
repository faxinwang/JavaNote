package myjava.util.container;

import static java.lang.System.out;

import java.util.EnumSet;

/* EnumSet ��ר��Ϊö������Ƶļ���,��Ԫ��˳���Ǹ���Ԫ����ö�����е�˳������һ��������.
 * EnumSet ���治������null,��û�п��õĹ��캯����������һ���෽�����ڹ���ʵ������:
 * EnumSet allOf(Class elemType):create a EnumSet contains all Objects of the given Enum Class
 * EnumSet complementOf(EnumSet s): create a EnumSet contains all Objects not int s
 * EnumSet copyOf(Collection c): create a EnumSet from a collection
 * EnumSet copyOf(EnumSet s): create a copy of the given EnumSet s 
 * EnumSet noneOf(Class elemType): create a empty set whict contains the given elemType
 * EnumSet of(E first,E... rest ):create a EnumSet with the given value and elemType
 * EnumSet range(E from,E to):create a EnumSet with the range [from,to)
 */

public class EnumSetTest {
	enum Season{
		SPRING,SUMMER,FALL,WINTER;
	}
	static void test1(){
		EnumSet<Season> s1 = EnumSet.allOf(Season.class);
		out.println("s1 : " + s1);
		EnumSet<Season> s2 = EnumSet.of(Season.SPRING, Season.SUMMER);
		out.println("s2 : " + s2);
		EnumSet<Season> s3 = EnumSet.complementOf(s2);
		out.println("s3 : " + s3);
		EnumSet<Season> s4 = EnumSet.range(Season.SUMMER, Season.WINTER);
		out.println("s4 : " + s4);
		
		out.println("s1.contains(Season.SPRING):" + s1.contains(Season.SPRING));
		out.println("s1.containsAll(s2):" + s1.containsAll(s2));
		out.println("s3.contains(Season.SPRING):" +s3.contains(Season.SPRING));
		out.println("EnumSet.copyOf(s4):" + EnumSet.copyOf(s4));
	}
	
	public static void main(String []args){
		test1();
	}
}
