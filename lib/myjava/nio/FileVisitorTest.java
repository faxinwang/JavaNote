package myjava.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/*Files���ṩ���������������������ļ�����Ŀ¼
*walkFileTree(Path start,FileVisitor<? super Path>visitor ):����start·���µ������ļ�����Ŀ¼
*walkFileTree(Path start,Set<FileVisitOption> options,
*		int maxDepth,FileVisitor<? super Path> visitor):����һ���������ƣ��÷���������maxDepth��ȵ�Ŀ¼
* ��������������Ҫһ��FileVisitor������FileVisitor����һ���ļ�������,walkFileTree()�������Զ�����start·���������ļ�
* ����Ŀ¼�������ļ�����Ŀ¼����"����"FileVisitor����Ӧ�ķ�����
* FileVisitResult postVisitDirectory(T dir,IOException exc):������Ŀ¼֮�󴥷��÷���
* FileVisitResult preVisittDirectory(T dir,BasicFileAttributes attrs):������Ŀ¼֮ǰ�����÷���
* FileVisitResult visitFile(T file,BasicFileAttributes attrs):����file�ļ�ʱ�����÷���
* FileVisitResult visitFileFailed(T file,IOException exc):����file�ļ�ʧ��ʱ�����÷���
* 
* ����4������������һ��FileVisitResult��������һ��ö���࣬�����˷���֮��ĺ�����Ϊ��
* CONTINUE:�����������ʡ��ĺ�����Ϊ
* SKIP_SIBLINGS:�����������ʡ��ĺ�����Ϊ,�������ʸ��ļ���Ŀ¼���ֵ��ļ���Ŀ¼
* SKIP_SUBTREE:�����������ʡ��ĺ�����Ϊ,�������ʸ��ļ���Ŀ¼����Ŀ¼��
* TERMINATE:����"��ֹ����"�ĺ�����Ϊ
*/


public class FileVisitorTest {
	
	public static void main(String[] args)throws Exception {
		//��������Ŀ¼�µ��ļ�����Ŀ¼,֪���ҵ����ļ�Ϊֹ.
		//����ı��Ϊ������walkFileTree���õ�˳��
		
		
		Files.walkFileTree(Paths.get("."), new FileVisitor<Path>(){
			int depth=0,width = 4;
			//1
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			//	System.out.println("�Ѿ�����" + dir.getFileName() +"Ŀ¼...");
				++depth;
				
				for(int i=1;i<depth;++i){
					System.out.print('|');
					for(int j=0;j<width ;++j)
						System.out.print(' ');
				}
				if(depth>1)
					System.out.println("|");
				
				for(int i=1;i<depth;++i){
					System.out.print('|');
					for(int j=0;j<width ;++j)
						System.out.print(' ');
				}
				//��ӡĿ¼��
				System.out.println("|-----[" + dir.getFileName() + "]");
				
				return FileVisitResult.CONTINUE;
			}
			//2
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			//	System.out.println("���ڷ���" + file.getFileName() + "�ļ�...");
				for(int i=0;i<depth;++i){
					System.out.print('|');
					for(int j=0;j<width;++j)
						System.out.print(' ');
				}
				
				System.out.println("|--->" + file.getFileName());
				if(file.endsWith("FileVisitorTest.java")){
					System.out.println("�ҵ�Ŀ���ļ�-----FileVisitorTest.java-----");
				//	return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}
			//3
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				System.out.println("����" + file.getFileName() + "ʧ��!!!");
				return FileVisitResult.CONTINUE;
			}
			//4
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			//	System.out.println("�ѱ�����" + dir.getFileName() + "Ŀ¼...");
				for(int i=0;i<depth;++i){
					System.out.print('|');
					for(int j=0;j<width;++j)
						System.out.print(' ');
				}
				System.out.println("");
				
				--depth;
				
				return FileVisitResult.CONTINUE;
			}
			
		});
	}
}
