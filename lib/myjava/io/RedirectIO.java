package myjava.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/* System�����ṩ������������̬���������ض����׼�������
 * staitc void setErr(PrintStream err):�ض����׼"����"�����
 * static void setIn(InputStream in):�ض����׼������
 * static void setOut(PrintStream out)�ض����׼�����
 */
public class RedirectIO {

	public static void main(String[] args){
		Scanner sc= null;
		try(
			PrintStream ps = new PrintStream(new FileOutputStream("./lib/myjava/io/out.txt"));
			FileInputStream fis = new FileInputStream("./lib/myjava/io/RedirectIO.java");
		){
			//����׼�����ض���fis
			System.setIn(fis);
			//����׼����ض���ps��
			System.setOut(ps);
			//ʹ��System.in����Scanner����,���ڻ�ȡ��׼����
			sc = new Scanner(System.in);
			//ʹ�û�����Ϊ�ָ���
			sc.useDelimiter("\n");
			//�ж��Ƿ�����һ��������
			while(sc.hasNext()){
				//����������Ļ�ϲ�������ʾ��ֻ�������out.txt�ļ�
				System.out.println(sc.next());
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			sc.close();
		}
	}
}
