package myjava.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/* 分类		字节输入流				字节输出流					字符输入流				字符输出流
 * 抽象基类	InputStream			OutputStream			Reader				Writer
 * 访问文件	FileInputStream		FileOutputStream		FileReader			FileWriter
 * 访问数组    ByteArrayInputStream	ByteArrayOutputStream	CharArrayReader		CharArrayWriter
 * 访问管道	PipedInputStream	PipedOutputStream		PipedReader			PipedWriter
 * 访问字符串												StringReader		StringWriter
 * 对象流		ObjectInputStream	ObjectOutputStream
 * 缓冲流	  BufferedInputStream	BufferedOutputStream	BufferedReader		BufferedWriter
 * 
 * 抽象基类	FilterInputStream	FilterInputStream		FilterReader		FilterWriter	
 * 打印流							PrintStream									PrintWriter
 * 推回输入流  PushbackInputStream							PushbackReader
 * 特殊流		DataInputStream		DataOutputStream
 */

/* 基本概念:
 * 字节流和字符流:字节流和字符流基本一样，区别在与字节流和字符流所操作的数据单元不同---字节流操作的数据单元是8位的字节，而字符流操作的
 * 		数据单元是16位的字符,字节流主要以InputStream和OutputStream作为基类，而字符流则主要用Reader和Writer作为基类.
 * 节点流和处理流:可以从一个特定的IO设备(如磁盘，网络)读写数据的流称为节点流，节点流也被称为低级流。所有可以包装节点流的都称为处理流.
 */

/* InputStream抽象基类中有如下三个方法:
 * int read():读取单个字符,返回所读取的字节数据(自己数据可以直接转换为int类型)
 * int read(byte[] b):从流中最多读取b.length个字节的数据，并存在字节数组b中
 * int read(byte[] b,int off,int len):最多读取len个字节的数据存在从off开始的位置
 * Reader抽象基类中也有三个对应的方法，只不顾参数为char[] buf.
 * 
 * OutputStream和Writer抽象基类也非常相似，他们都提供了下面三个方法:
 * void write(int c):
 * void write(byte[]/char[] buf):
 * void write(byte[]/char[] buf,int off,int len):
 * 因为字符流直接以字符为操作单位，所以Writer可以用字符串来代替字符数组
 * void write(String str):将str字符串输出到指定输出流
 * void write(String str,int off,int len):将str里从off位置开始的len个字符输出到指定输出流
 */

public class JavaIO {
	static void test1()throws Exception{
		//创建字节输入流
		FileInputStream fis = new FileInputStream("./lib/myjava/io/JavaIO.java");
		byte[] buf = new byte[1024];
		//用于保存实际读取的字节数
		int hasRead=0;
		//当读取到数据末尾时，返回-1
		while((hasRead=fis.read(buf)) > 0){
			//将字节数据里的数据转换为字符流输出
			System.out.println(new String(buf,0,hasRead));
		}
		//关闭输入流，放在finally块里更安全
		fis.close();
	}
	//上面方法使用字节流来读取字符文件，如果read()方法读取时只读到半个中文字符，则将会出现乱码
	static void test2()throws Exception{
		try(
			FileReader fr = new FileReader("./lib/myjava/io/JavaIO.java");
		){
			char[] buf = new char[512];
			int hasRead=0;
			while((hasRead=fr.read(buf)) > 0){
				//将字符数组转换为字符串输出
				System.out.println(new String(buf,0,hasRead));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	static void test3()throws Exception{
		try(
			FileOutputStream fos = new FileOutputStream("./lib/myjava/io/test.txt");
			//使用PrintStream处理流封装fos
			PrintStream ps = new PrintStream(fos);	
		){
			//使用PrintStream执行输出
			ps.println("测试字符串!");
			//直接使用PrintStream输出对象
			ps.println(new JavaIO());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/*FileReader FileWriter 默认是用gbk编码来读写文件
	 * 如果需要使用其他编码读写文件, 可以是用转换流:InputStreamReader,OutputStreamWriter
	*/
	static void test4() {
		try (
				FileOutputStream fout = new FileOutputStream("./lib/myjava/io/test_utf8.txt");
				//使用转换流,转换为utf-8格式输出
				OutputStreamWriter fin = new OutputStreamWriter(fout, "utf-8");
				
		){
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void main(String[] args)throws Exception{
	//	test1();
	//	test2();
		test3();
	}
}
