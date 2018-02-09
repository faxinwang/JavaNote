package myjava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/* System类里提供了下面三个静态方法用于重定向标准输入输出
 * staitc void setErr(PrintStream err):重定向标准"错误"输出流
 * static void setIn(InputStream in):重定向标准输入流
 * static void setOut(PrintStream out)重定向标准输出流
 */
public class RedirectIO {

	public static void main(String[] args){
		Scanner sc= null;
		try(
			PrintStream ps = new PrintStream(new FileOutputStream("./lib/myjava/io/out.txt"));
			FileInputStream fis = new FileInputStream("./lib/myjava/io/RedirectIO.java");
		){
			//将标准输入重定向到fis
			System.setIn(fis);
			//将标准输出重定向到ps流
			System.setOut(ps);
			//使用System.in创建Scanner对象,用于获取标准输入
			sc = new Scanner(System.in);
			//使用换行作为分隔符
			sc.useDelimiter("\n");
			//判断是否还有下一个输入项
			while(sc.hasNext()){
				//输出输入项，屏幕上不会有显示，只会输出到out.txt文件
				System.out.println(sc.next());
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			sc.close();
		}
	}
}
