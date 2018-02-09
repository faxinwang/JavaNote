package myjava.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Date;

/* RandomAccessFile����������������������������ļ���¼ָ��
 * long getFilePointer():�����ļ���¼ָ���ĵ�ǰλ��
 * void seek(long pos):���ļ���¼ָ����λ��posλ��
 * ���캯����RandomAccessFile(String/File name,String mode),����mode��ȡ����ֵ
 * "r":��ֻ�������򿪡�
 * "rw":�Զ�д��ʽ�򿪡�
 * "rws":�Զ�д��ʽ�򿪣� ͬʱ���ļ����ݻ�Ԫ���ݵ��޸Ļ�ͬ��д��ײ�洢�豸.
 * "rwd":�Զ�д��ʽ�򿪣�ͬʱ���ļ����ݵ��޸Ļ�ͬʱд��ײ�洢�豸.
 */

public class RandomAccessFileTest {
	static void insert(File fileName,long pos,String content)throws Exception{
		File tmp = new File("./src/tmp.txt");
		try(
			RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
			//ʹ����ʱ�ļ�������������������
			FileWriter fw = new FileWriter(tmp);
			BufferedWriter bw = new BufferedWriter(fw);
			FileReader fr = new FileReader(tmp);
			BufferedReader br = new BufferedReader(fr);
		){
			raf.seek(pos);
			//������������ݶ�����ʱ�ļ���
			String line=null;
			while((line=raf.readLine())!=null){
				System.out.println("w to tmp:"+line);
				bw.write(line);
				bw.write("\\n\\r");
			}
			//���ļ�ָ�����¶�λ�������
			raf.seek(pos);
			//д��Ҫ���������
			raf.write(("//" + content).getBytes());
		//	raf.writeChars("//"+content);
			//����ʱ�ļ��������׷�ӻ���
			line=null;
			while((line=br.readLine())!=null){
				System.out.println("r from tmp:"+line);
				raf.write(line.getBytes());
				raf.write("\n".getBytes());
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		File file = new File("./lib/myjava/io/RandomAccessFileTest.java");
		System.out.println(file.length());
		insert(file,1930,new Date().toString());
	}
}
//Fri Aug 05 14:22:47 CST 201616
//Fri Aug 05 13:53:52 CST 2016