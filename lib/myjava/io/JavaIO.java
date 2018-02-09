package myjava.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

/* ����		�ֽ�������				�ֽ������					�ַ�������				�ַ������
 * �������	InputStream			OutputStream			Reader				Writer
 * �����ļ�	FileInputStream		FileOutputStream		FileReader			FileWriter
 * ��������    ByteArrayInputStream	ByteArrayOutputStream	CharArrayReader		CharArrayWriter
 * ���ʹܵ�	PipedInputStream	PipedOutputStream		PipedReader			PipedWriter
 * �����ַ���												StringReader		StringWriter
 * ������		ObjectInputStream	ObjectOutputStream
 * ������	  BufferedInputStream	BufferedOutputStream	BufferedReader		BufferedWriter
 * 
 * �������	FilterInputStream	FilterInputStream		FilterReader		FilterWriter	
 * ��ӡ��							PrintStream									PrintWriter
 * �ƻ�������  PushbackInputStream							PushbackReader
 * ������		DataInputStream		DataOutputStream
 */

/* ��������:
 * �ֽ������ַ���:�ֽ������ַ�������һ�������������ֽ������ַ��������������ݵ�Ԫ��ͬ---�ֽ������������ݵ�Ԫ��8λ���ֽڣ����ַ���������
 * 		���ݵ�Ԫ��16λ���ַ�,�ֽ�����Ҫ��InputStream��OutputStream��Ϊ���࣬���ַ�������Ҫ��Reader��Writer��Ϊ����.
 * �ڵ����ʹ�����:���Դ�һ���ض���IO�豸(����̣�����)��д���ݵ�����Ϊ�ڵ������ڵ���Ҳ����Ϊ�ͼ��������п��԰�װ�ڵ����Ķ���Ϊ������.
 */

/* InputStream�����������������������:
 * int read():��ȡ�����ַ�,��������ȡ���ֽ�����(�Լ����ݿ���ֱ��ת��Ϊint����)
 * int read(byte[] b):����������ȡb.length���ֽڵ����ݣ��������ֽ�����b��
 * int read(byte[] b,int off,int len):����ȡlen���ֽڵ����ݴ��ڴ�off��ʼ��λ��
 * Reader���������Ҳ��������Ӧ�ķ�����ֻ���˲���Ϊchar[] buf.
 * 
 * OutputStream��Writer�������Ҳ�ǳ����ƣ����Ƕ��ṩ��������������:
 * void write(int c):
 * void write(byte[]/char[] buf):
 * void write(byte[]/char[] buf,int off,int len):
 * ��Ϊ�ַ���ֱ�����ַ�Ϊ������λ������Writer�������ַ����������ַ�����
 * void write(String str):��str�ַ��������ָ�������
 * void write(String str,int off,int len):��str���offλ�ÿ�ʼ��len���ַ������ָ�������
 */

public class JavaIO {
	static void test1()throws Exception{
		//�����ֽ�������
		FileInputStream fis = new FileInputStream("./lib/myjava/io/JavaIO.java");
		byte[] buf = new byte[1024];
		//���ڱ���ʵ�ʶ�ȡ���ֽ���
		int hasRead=0;
		//����ȡ������ĩβʱ������-1
		while((hasRead=fis.read(buf)) > 0){
			//���ֽ������������ת��Ϊ�ַ������
			System.out.println(new String(buf,0,hasRead));
		}
		//�ر�������������finally�������ȫ
		fis.close();
	}
	//���淽��ʹ���ֽ�������ȡ�ַ��ļ������read()������ȡʱֻ������������ַ����򽫻��������
	static void test2()throws Exception{
		try(
			FileReader fr = new FileReader("./lib/myjava/io/JavaIO.java");
		){
			char[] buf = new char[512];
			int hasRead=0;
			while((hasRead=fr.read(buf)) > 0){
				//���ַ�����ת��Ϊ�ַ������
				System.out.println(new String(buf,0,hasRead));
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	static void test3()throws Exception{
		try(
			FileOutputStream fos = new FileOutputStream("./lib/myjava/io/test.txt");
			//ʹ��PrintStream��������װfos
			PrintStream ps = new PrintStream(fos);	
		){
			//ʹ��PrintStreamִ�����
			ps.println("�����ַ���!");
			//ֱ��ʹ��PrintStream�������
			ps.println(new JavaIO());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/*FileReader FileWriter Ĭ������gbk��������д�ļ�
	 * �����Ҫʹ�����������д�ļ�, ��������ת����:InputStreamReader,OutputStreamWriter
	*/
	static void test4() {
		try (
				FileOutputStream fout = new FileOutputStream("./lib/myjava/io/test_utf8.txt");
				//ʹ��ת����,ת��Ϊutf-8��ʽ���
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
