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
		//�Ե�ǰ·��������Path����
		Path path = Paths.get(".");
		System.out.println("path.getNameCount(): " + path.getNameCount());
		System.out.println("patt.getRoot(): " + path.getRoot());
		//��ȡpath�ľ���·��
		Path absolutePath = path.toAbsolutePath();
		System.out.println("absolutePath: " + absolutePath);
		System.out.println("absolutePath.getRoot(): " + absolutePath.getRoot());
		System.out.println("absulotePath.getNameCount(): "+absolutePath.getNameCount());
		System.out.println("absolutePath.getName(3): " + absolutePath.getName(3));
		//�Զ��String������path����
		Path path2 = Paths.get("g:","files" , "images" , "2016");
		System.out.println(path2);
	}
	static void testFiles()throws Exception{
		Path file = Paths.get("./lib/myjava/nio/PathsAndFiles.java");
		//���Ʊ��ļ���"./src/tmp.java"
		Files.copy(file,new FileOutputStream("./src/tmp.java"));
		System.out.println("file.getFileName():" + file.getFileName());
		//�жϱ��ļ��Ƿ��������ļ�
		System.out.println("���ļ��Ƿ�Ϊ�ڲ��ļ�: " +Files.isHidden(file));
		//һ���Զ�ȡ���ļ���������
		List<String> lines = Files.readAllLines(file,Charset.forName("GBK"));
		lines.forEach(line -> System.out.println(line));
		//�ж�ָ���ļ��Ĵ�С
		System.out.println("���ļ���СΪ:" + Files.size(file));
		List<String> poem = new ArrayList<>();
		poem.add("�ҵ�����������Զ��ȫ���һ");
		poem.add("�������Ϊ������");
		poem.add("�ҵ�п����������");
		//ֱ�ӽ�����ַ�������д���ļ�
		Files.write(Paths.get("./src/tmp.txt"), poem);
		
		//Java8 ������Stream API�г���ǰĿ¼�������ļ�����Ŀ¼
		System.out.println("��ǰ�ļ�Ŀ¼�µ������ļ�����Ŀ¼:");
		Files.list(Paths.get(".")).forEach(path -> System.out.println(path));
		//ʹ��Java8������API��ȡ�ļ�����
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
