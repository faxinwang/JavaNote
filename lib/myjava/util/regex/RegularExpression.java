package myjava.util.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 正则表达式中的特殊字符:
 * $		匹配一行的结尾(要匹配$本身,需使用 \$,下同)
 * ^		匹配一行的开头
 * ()		标记子表达式的开头和结尾
 * []		用于确定中括号的开始和结束位置
 * {}		用于标记前面子表达式的出现频度
 * *		指定前面子表达式可以出现0次或多次
 * +		指定前面子表达式可以出现1次或多次
 * ？		指定前面子表达式可以出现0次或1次
 * .		匹配除换行符\n之外的任何单个字符
 * \		用于转译下一个字符或指定八，十六进制字符(如需匹配\,使用\\)
 * |		指定在给定子表达式中任选一项
 */

/*Examples:
 *  "\u0041\\\\" 	匹配: A\
 * "\u0061\t"		匹配 : a<制表符>
 * "\\?\\["			匹配 : ?[
 * 注意Java中反斜杠本身需要转译，所以两个  \\ 实际上相当于一个 \
 */

/* 预定义字符: 
 * .		可以匹配任何字符
 * \d		匹配0-9的所有数字
 * \D		匹配非数字
 * \s		匹配所有空白字符(空格,回车,换行,换页,制表符)
 * \S		匹配所有非空白字符
 * \w		匹配所有的单词字符(包括0-9,26个英文字母,下划线_ )
 * \W		匹配所有非单词字符
 * 
 * \b		单词的边界
 * \B		非单词边界
 * \A		输入的开头
 * \G		前一个匹配的结尾
 * \Z		输入的结尾,仅用于最后的结束符
 * \z		输入的结尾
 */

/* 方括号表达式的用法:
 * 表示枚举:		例如[abd]表示a,b,d其中任何一个字符
 * 表示范围:-		例如[a-f]表示a-f范围内的任意字符，[\\u0041-\\u0056]表示十六进制字符\u0041到\u0056范围的字符
 * 				范围可以和枚举组合使用,如[a-cx-z]表示a-c,x-z范围内的任意字符
 * 表示求否:^		例如[^abd]表示非a,b,d的任意字符,[^a-f]表示不在a-f范围内的任意字符
 * 表示“与”:&&	[a-z&&[def]] 求a-z和[def]的交集,表示d,e或f
 * 				[a-z&&[^bc]] a-z范围内除了b,c之外的任意字符,即[ad-z]
 * 				[a-z&&[^m-p]] 即[a-lq-z]
 * 表示并运算		并运算与前面的枚举类似 [a-d[m-p]] 即[a-dm-p]
 * 
 */

public class RegularExpression {
	public static void testFindGroup(){
		String str = "我想求购一本《疯狂Java讲义》,尽快联系我 13500006666" +
					"交朋友，电话号码为13611125656" + 
					"出售二手电脑,联系方式15899903312";
		//创建一个Pattern对象，并用他建立一个Matcher对象
		//该正则表达式只抓取13x,15x段的电话号码
		Matcher m = Pattern.compile("((13)|(15))\\d{9}").matcher(str);
		//将所有符合正则表达式的子串(电话号码)全部输出
		
		while(m.find()){	//find()返回目标字符串中是否包含与Pattern匹配的子串
			System.out.println(m.group());//group()上一次与Pattern匹配的子串
		}
	}
	public static void testStartEnd(){
		String regStr = "Java is very easy!";
		System.out.println("目标字符串是:" + regStr);
		//创建一个Pattern对象，并用它建立一个Matcher对象
		Matcher m = Pattern.compile("\\w+").matcher(regStr);
		while(m.find()){
			System.out.println(m.group() + "子串的起始位置:" + m.start() 
										 + "结束位置:" + m.end());
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
		//					前面是3到20个单词字符或.号	@后面一个或多个单词字符后面跟一个.号
		String mailRegEx = "(((\\w)|(\\.)){3,20}@\\w+\\.(com|cn|org|net|gov))";
		Pattern mailPattern = Pattern.compile(mailRegEx);
		Matcher  matcher = null;
		for(String  mail:mails){
			if(matcher==null){
				matcher = mailPattern.matcher(mail);
			}else{
				matcher.reset(mail);
			}
			System.out.println(mail+(matcher.matches()?"是":"不是")+
					"一个有效的邮件地址!");
		}
		
	}
	
	public static void main(String[]args){
		testFindGroup();
		testStartEnd();
		testMatches();
	}
}
