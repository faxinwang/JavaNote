package myjava.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/*Files类提供了如下两个方法来遍历文件和子目录
*walkFileTree(Path start,FileVisitor<? super Path>visitor ):遍历start路径下的所有文件和子目录
*walkFileTree(Path start,Set<FileVisitOption> options,
*		int maxDepth,FileVisitor<? super Path> visitor):与上一个方法类似，该方法最多遍历maxDepth深度的目录
* 这两个方法都需要一个FileVisitor参数，FileVisitor代表一个文件访问器,walkFileTree()方法会自动遍历start路径下所有文件
* 和子目录，遍历文件和子目录都会"触发"FileVisitor中相应的方法。
* FileVisitResult postVisitDirectory(T dir,IOException exc):访问子目录之后触发该方法
* FileVisitResult preVisittDirectory(T dir,BasicFileAttributes attrs):访问子目录之前触发该方法
* FileVisitResult visitFile(T file,BasicFileAttributes attrs):访问file文件时触发该方法
* FileVisitResult visitFileFailed(T file,IOException exc):访问file文件失败时触发该方法
* 
* 上面4个方法都返回一个FileVisitResult对象，它是一个枚举类，代表了访问之后的后续行为。
* CONTINUE:代表“继续访问”的后续行为
* SKIP_SIBLINGS:代表“继续访问”的后续行为,但不访问该文件或目录的兄弟文件或目录
* SKIP_SUBTREE:代表“继续访问”的后续行为,但不访问该文件或目录的子目录树
* TERMINATE:代表"终止访问"的后续行为
*/


public class FileVisitorTest {
	
	public static void main(String[] args)throws Exception {
		//遍历当期目录下的文件和子目录,知道找到该文件为止.
		//下面的编号为函数被walkFileTree调用的顺序
		
		
		Files.walkFileTree(Paths.get("."), new FileVisitor<Path>(){
			int depth=0,width = 4;
			//1
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			//	System.out.println("已经进入" + dir.getFileName() +"目录...");
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
				//打印目录名
				System.out.println("|-----[" + dir.getFileName() + "]");
				
				return FileVisitResult.CONTINUE;
			}
			//2
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			//	System.out.println("正在访问" + file.getFileName() + "文件...");
				for(int i=0;i<depth;++i){
					System.out.print('|');
					for(int j=0;j<width;++j)
						System.out.print(' ');
				}
				
				System.out.println("|--->" + file.getFileName());
				if(file.endsWith("FileVisitorTest.java")){
					System.out.println("找到目标文件-----FileVisitorTest.java-----");
				//	return FileVisitResult.TERMINATE;
				}
				return FileVisitResult.CONTINUE;
			}
			//3
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				System.out.println("访问" + file.getFileName() + "失败!!!");
				return FileVisitResult.CONTINUE;
			}
			//4
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			//	System.out.println("已遍历完" + dir.getFileName() + "目录...");
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
