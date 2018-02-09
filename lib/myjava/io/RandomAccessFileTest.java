package myjava.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Date;

/* RandomAccessFile类包含了如下两个方法用来操作文件记录指针
 * long getFilePointer():返回文件记录指正的当前位置
 * void seek(long pos):将文件记录指正定位到pos位置
 * 构造函数：RandomAccessFile(String/File name,String mode),其中mode可取如下值
 * "r":以只读方法打开。
 * "rw":以读写方式打开。
 * "rws":以读写方式打开， 同时对文件内容或元数据的修改会同步写入底层存储设备.
 * "rwd":以读写方式打开，同时对文件内容的修改会同时写入底层存储设备.
 */

public class RandomAccessFileTest {
	static void insert(File fileName,long pos,String content)throws Exception{
		File tmp = new File("./src/tmp.txt");
		try(
			RandomAccessFile raf = new RandomAccessFile(fileName,"rw");
			//使用临时文件来保存插入点后面的数据
			FileWriter fw = new FileWriter(tmp);
			BufferedWriter bw = new BufferedWriter(fw);
			FileReader fr = new FileReader(tmp);
			BufferedReader br = new BufferedReader(fr);
		){
			raf.seek(pos);
			//将插入点后的数据读入临时文件中
			String line=null;
			while((line=raf.readLine())!=null){
				System.out.println("w to tmp:"+line);
				bw.write(line);
				bw.write("\\n\\r");
			}
			//把文件指针重新定位到插入点
			raf.seek(pos);
			//写入要插入的内容
			raf.write(("//" + content).getBytes());
		//	raf.writeChars("//"+content);
			//将临时文件里的数据追加回来
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