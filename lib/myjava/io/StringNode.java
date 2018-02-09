package myjava.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

public class StringNode {
	static void test1(){
		String src="从明天起，做一个幸福的人\n" + 
				"喂马，劈柴，周游世界\n" +
				"从明天起，关心粮食和蔬菜\n" + 
				"我有一所房子，面朝大海，春暖花开\n" + 
				"从明天起，和每一个亲人通信\n" + 
				"告诉他们我的幸福\n";
		//	char[] buf = new char[32];
		//	int hasRead = 0;
			try(
				StringReader sr = new StringReader(src);
				BufferedReader br = new BufferedReader(sr);
			){
				String line=null;
				//采用循环读取的方式读取m字符串
				while((line=br.readLine())!=null){
					System.out.println(line);
				}
				
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
			try(
				//创建一个StringWriter时，实际上以一个StringBuffer作为输出节点
				//下面指定的20就是StringBuffer的初始长度
				StringWriter sw = new StringWriter(20);
			){
				sw.write("有一个美丽的世界,\n");
				sw.write("她在远方等我,\n");
				sw.write("哪里有天真的孩子,\n");
				sw.write("还有姑凉的酒窝.\n");
				System.out.println("\n\n----下面是sw字符串节点里面的内容----\n" + sw);
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
	}
	
	static void test2(){
		try(
			//将System.in对象转换成Reader对象
			InputStreamReader reader=new InputStreamReader(System.in);
			//将普通的Reader包装成BufferedReader
			BufferedReader br = new BufferedReader(reader);
		){
			String line = null;
			while((line=br.readLine())!=null){
				if(line.equals("exit")){
					System.exit(1);
				}
				System.out.println(line);
			}
			
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
