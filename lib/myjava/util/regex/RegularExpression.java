package myjava.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* ������ʽ�е������ַ�:
 * $		ƥ��һ�еĽ�β(Ҫƥ��$����,��ʹ�� \$,��ͬ)
 * ^		ƥ��һ�еĿ�ͷ
 * ()		����ӱ��ʽ�Ŀ�ͷ�ͽ�β
 * []		����ȷ�������ŵĿ�ʼ�ͽ���λ��
 * {}		���ڱ��ǰ���ӱ��ʽ�ĳ���Ƶ��
 * *		ָ��ǰ���ӱ��ʽ���Գ���0�λ���
 * +		ָ��ǰ���ӱ��ʽ���Գ���1�λ���
 * ��		ָ��ǰ���ӱ��ʽ���Գ���0�λ�1��
 * .		ƥ������з�\n֮����κε����ַ�
 * \		����ת����һ���ַ���ָ���ˣ�ʮ�������ַ�(����ƥ��\,ʹ��\\)
 * |		ָ���ڸ����ӱ��ʽ����ѡһ��
 */

/*Examples:
 *  "\u0041\\\\" 	ƥ��: A\
 * "\u0061\t"		ƥ�� : a<�Ʊ��>
 * "\\?\\["			ƥ�� : ?[
 * ע��Java�з�б�ܱ�����Ҫת�룬��������  \\ ʵ�����൱��һ�� \
 */

/* Ԥ�����ַ�: 
 * .		����ƥ���κ��ַ�
 * \d		ƥ��0-9����������
 * \D		ƥ�������
 * \s		ƥ�����пհ��ַ�(�ո�,�س�,����,��ҳ,�Ʊ��)
 * \S		ƥ�����зǿհ��ַ�
 * \w		ƥ�����еĵ����ַ�(����0-9,26��Ӣ����ĸ,�»���_ )
 * \W		ƥ�����зǵ����ַ�
 * 
 * \b		���ʵı߽�
 * \B		�ǵ��ʱ߽�
 * \A		����Ŀ�ͷ
 * \G		ǰһ��ƥ��Ľ�β
 * \Z		����Ľ�β,���������Ľ�����
 * \z		����Ľ�β
 */

/* �����ű��ʽ���÷�:
 * ��ʾö��:		����[abd]��ʾa,b,d�����κ�һ���ַ�
 * ��ʾ��Χ:-		����[a-f]��ʾa-f��Χ�ڵ������ַ���[\\u0041-\\u0056]��ʾʮ�������ַ�\u0041��\u0056��Χ���ַ�
 * 				��Χ���Ժ�ö�����ʹ��,��[a-cx-z]��ʾa-c,x-z��Χ�ڵ������ַ�
 * ��ʾ���:^		����[^abd]��ʾ��a,b,d�������ַ�,[^a-f]��ʾ����a-f��Χ�ڵ������ַ�
 * ��ʾ���롱:&&	[a-z&&[def]] ��a-z��[def]�Ľ���,��ʾd,e��f
 * 				[a-z&&[^bc]] a-z��Χ�ڳ���b,c֮��������ַ�,��[ad-z]
 * 				[a-z&&[^m-p]] ��[a-lq-z]
 * ��ʾ������		��������ǰ���ö������ [a-d[m-p]] ��[a-dm-p]
 * 
 */

public class RegularExpression {
	public static void testFindGroup(){
		String str = "������һ�������Java���塷,������ϵ�� 13500006666" +
					"�����ѣ��绰����Ϊ13611125656" + 
					"���۶��ֵ���,��ϵ��ʽ15899903312";
		//����һ��Pattern���󣬲���������һ��Matcher����
		//��������ʽֻץȡ13x,15x�εĵ绰����
		Matcher m = Pattern.compile("((13)|(15))\\d{9}").matcher(str);
		//�����з���������ʽ���Ӵ�(�绰����)ȫ�����
		
		while(m.find()){	//find()����Ŀ���ַ������Ƿ������Patternƥ����Ӵ�
			System.out.println(m.group());//group()��һ����Patternƥ����Ӵ�
		}
	}
	public static void testStartEnd(){
		String regStr = "Java is very easy!";
		System.out.println("Ŀ���ַ�����:" + regStr);
		//����һ��Pattern���󣬲���������һ��Matcher����
		Matcher m = Pattern.compile("\\w+").matcher(regStr);
		while(m.find()){
			System.out.println(m.group() + "�Ӵ�����ʼλ��:" + m.start() 
										 + "����λ��:" + m.end());
		}
	}
	public static void testMatches(){
		String[] mails = {
			"kongyeeku@163.com",
			"kongyeeku@gmail.com",
			"ligang@crazyit.org",
			"wawa@abc.xx",
			"1094828998@qq.com",
			"www.wjx.gg.66@qq.com"
		};
		//					ǰ����3��20�������ַ���.��	@����һ�����������ַ������һ��.��
		String mailRegEx = "(((\\w)|(\\.)){3,20}@\\w+\\.(com|cn|org|net|gov))";
		Pattern mailPattern = Pattern.compile(mailRegEx);
		Matcher  matcher = null;
		for(String  mail:mails){
			if(matcher==null){
				matcher = mailPattern.matcher(mail);
			}else{
				matcher.reset(mail);
			}
			System.out.println(mail+(matcher.matches()?"��":"����")+
					"һ����Ч���ʼ���ַ!");
		}
		
	}
	
	public static void main(String[]args){
		testFindGroup();
		testStartEnd();
		testMatches();
	}
}
