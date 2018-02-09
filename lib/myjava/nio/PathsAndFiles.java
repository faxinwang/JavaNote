package myjava.nio;

import java.io.FileOutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.*;

public class PathsAndFiles {
	static void testPaths(){
		//以当前路径来创建Path对象
		Path path = Paths.get(".");
		System.out.println("path.getNameCount(): " + path.getNameCount());
		System.out.println("patt.getRoot(): " + path.getRoot());
		//获取path的绝对路径
		Path absolutePath = path.toAbsolutePath();
		System.out.println("absolutePath: " + absolutePath);
		System.out.println("absolutePath.getRoot(): " + absolutePath.getRoot());
		System.out.println("absulotePath.getNameCount(): "+absolutePath.getNameCount());
		System.out.println("absolutePath.getName(3): " + absolutePath.getName(3));
		//以多个String来构建path对象
		Path path2 = Paths.get("g:","files" , "images" , "2016");
		System.out.println(path2);
	}
	static void testFiles()throws Exception{
		Path file = Paths.get("./lib/myjava/nio/PathsAndFiles.java");
		//复制本文件到"./src/tmp.java"
		Files.copy(file,new FileOutputStream("./src/tmp.java"));
		System.out.println("file.getFileName():" + file.getFileName());
		//判断本文件是否问隐藏文件
		System.out.println("本文件是否为掩藏文件: " +Files.isHidden(file));
		//一次性读取本文件的所有行
		List<String> lines = Files.readAllLines(file,Charset.forName("GBK"));
		lines.forEach(line -> System.out.println(line));
		//判读指定文件的大小
		System.out.println("本文件大小为:" + Files.size(file));
		List<String> poem = new ArrayList<>();
		poem.add("我的氢气产率永远是全球第一");
		poem.add("大概是因为遇见你");
		poem.add("我的锌里再无杂质");
		//直接将多个字符串内容写入文件
		Files.write(Paths.get("./src/tmp.txt"), poem);
		
		//Java8 新曾的Stream API列出当前目录下所有文件和子目录
		System.out.println("当前文件目录下的所有文件和子目录:");
		Files.list(Paths.get(".")).forEach(path -> System.out.println(path));
		//使用Java8新增的API读取文件内容
		Files.lines(file, Charset.forName("GBK"))
			.forEach(line -> System.out.println(line));
		FileStore cStore = Files.getFileStore(Paths.get("C:"));
		out.println("C:TotalSpace:" + (cStore.getTotalSpace()/1024/1024/1024) + "GB");
		out.println("C:UsableSpace:" + (cStore.getUsableSpace()/1024/1024/1024)+"GB");
		out.println("C:UnallocatedSpace:"+(cStore.getUnallocatedSpace()/1024/1024/1024)+"GB");
		
	}
	public static void main(String[] args)throws Exception{
	//	testPaths();
		testFiles();
	}
}
