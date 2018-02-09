package myjava.io;

import static java.lang.System.out;
import java.io.File;
import java.io.FilenameFilter;

//import org.junit.Test;

/* 1.File类访问文件名相关方法
 * String getName():返回此file对象所表示的文件名或者路径名(如果是路径，则返回最后一级子路径名)
 * String getPath():返回此file对象所对应的路径.
 * File getAbsoluteFile():返回此file对象的绝对路径所对应的file对象
 * String getAbsolutePath():返回此file对象的绝对路径
 * String getParent():返回上一级目录的路径
 * boolean renameTo(File newName):重命名此file对象对应的文件或目录，如果重命名成功，则返回true
 * 
 * 2.文件检测相关的方法
 * boolean exists():判断file对象对应的文件是否存在
 * boolean canWrite():判断file对象对应的文件或目录是否可写
 * boolean canRead():判断file对象对应的文件或目录是否可读
 * boolean isFile():判断file对象是否表示一个文件
 * boolean isDirectory():判断file对象是否表示一个目录
 * boolean isAbsolute():判读file对象所对应的文件或目录是否是绝对目录
 * 
 * 3.获取常规文件信息
 * long lastModified():获取上次修改时间
 * long lenth():返回文件内容的长度
 * 
 * 4.文件操作相关方法
 * boolean createNewFile():当此对象所对应的文件不存在时，该方法将新建一个该file对象所对应的文件
 * boolean delete():删除file对象所对应的文件或路径
 * static File createTempFile(String prefix,String suffix):在默认的临时文件夹中创建一个临时文件，使用prefix+系统
 * 			生成的随机数，和后缀suffix作为文件名。其中prefix参数长度至少为3
 * static File createTempFile(String prefix,String suffix,File dir):在dir指定的目录中创建一个临时文件
 * void deleteOnExit():注册一个删除钩子，当jvm退出时，删除file对象所对应的文件和目录
 * 
 * 5.目录操作相关的方法
 * boolean mkdir():试图创建一个file对象所对应的目录，如果创建成功，则返回true，调用该方法时，file对象必须是一个路径而不是一个文件
 * String[] list():列出file对象所在路径下的所有文件名和路径名，返回String数组
 * File[] listFiles():列出file对象所在路径下的所有文件名和路径名，返回File数组
 * static File[] listRoots():列出系统所有的根路径
 */


public class FileTest{
	void filenameFilterTest(){
		File file = new File("./src");
		String[] nameList = file.list(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				//如果文件名以.java结尾或者文件对应一个路径，则列出该文件或路径
				out.println("dir:"+dir);
				out.println("name:"+name);
				String path = dir.toString();
				//将路径中的反斜杠'\'换成斜线才能表示路径'/'
				//反斜杠要表示路径需要转译：'\\'
				path = path.replace('\\', '/');
				return  name.endsWith(".java")||new File(path+"/"+name).isDirectory();
			}
		});
		out.println();
		for(String name:nameList){
			out.println(name);
		}
	}
	

	void fileTest()throws Exception{
		//以当前路径来创建一个File对像
		File file =new File("./src");
		//获取文件名， 将输出"."
		out.println("file.getName():"+file.getName());
		out.println("file.getPath():"+file.getPath());
		//获取相对路径的父路径可能会出错，下面代码输出null
		out.println("file.getParent():"+file.getParent());
		//获取绝对路径
		out.println("file.getAbsolutePath():"+file.getAbsolutePath());
		out.println("file.getAbsoluteFile():"+file.getAbsoluteFile());
		//获取上一级路径
		out.println("file.getAbsoluteFile().getParent():"+file.getAbsoluteFile().getParent());
		//在当前路径下创建一个临时文件
		File tmpFile = File.createTempFile("aaa", ".txt",file);
		//指定当jvm虚拟机退出时删除该文件
		tmpFile.deleteOnExit();
		//以系统当前时间为新文件名来创建新文件
		File newFile  = new File(System.currentTimeMillis()+"");
		out.println("newFile文件是否存在" + newFile.exists());
		//以指定newFile来创建临时文件
		out.println("newFile.createNewFile(): "+newFile.createNewFile());
		//以newFile对象来创建一个目录，因为newFile已经存在，所以下面方法返回false,即无法创建该目录
		out.println("newFile.mkdir(): "+newFile.mkdir());
		
		//使用list()方法列出当前路径下的所有文件和路径
		String[] fileList = file.list();
		out.println("==============当前路径下所有文件和路径如下===========");
		for(String name:fileList){
			out.println(name);
		}
		//使用listRoots()方法列出所有的磁盘根目录
		File[] roots = File.listRoots();
		out.println("==============系统所有根路径如下===========");
		for(File root:roots){
			out.println(root);
		}	
	}
	public static void main(String[] args)throws Exception{
//		fileTest();
//		filenameFilterTest();
	}
}
