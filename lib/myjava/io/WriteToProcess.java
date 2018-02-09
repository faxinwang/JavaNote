package myjava.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class WriteToProcess {
	
	public static void main(String[] args)throws Exception{
		//运行Java ReadStandard命令，返回运行该命令的进程
		Process pro = Runtime.getRuntime().exec("java myjava.io.ReadStandard");
		try(
			//以p进程的输出流创建PrintStream对象
			//这个输出流对本程序是输出流，对p进程则是输入流
			PrintStream ps = new PrintStream(pro.getOutputStream());
			BufferedReader br = new BufferedReader(
					new FileReader("./lib/myjava/io/WriteToProcess.java"));
		){
			//向ReadStandard程序写入内容，这些内容将被ReadStandard读取
			//读一行，写一行
			String line = null;
			while((line=br.readLine())!=null){
				ps.println(line);
			//	System.out.println(line);
			}
		}
	}
}


//该程序的进程从标准输入读取数据,同时输出到标准输出和磁盘文件
class ReadStandard{
	public static void main(String[] args){
		try(
			//使用System.in创建Scanner对象，用于获取标准输入
			Scanner sc = new Scanner(System.in);
			PrintStream ps = new PrintStream(new FileOutputStream("./lib/myjava/io/out.txt"));
		){
		//	sc.useDelimiter("\n");
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				ps.println(line);
				System.out.println(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}