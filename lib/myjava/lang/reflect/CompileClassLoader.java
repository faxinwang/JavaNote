package myjava.lang.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/* 可以用该类直接运行其他类,不过得先编译得到该类的class文件
 * 用法: java CompileClassLoader ClassName arg1 arg2...
 * 
 */

public class CompileClassLoader extends ClassLoader{
	//读取一个文件的内容
	private byte[] getBytes(String filename)throws IOException{
		File file = new File(filename);
		long len = file.length();
		byte[] raw = new byte[(int)len];
		try(
				FileInputStream fin = new FileInputStream(file);
		){
			//一次读取class文件的全部二进制数据
			int r =fin.read(raw);
			if(r!=len){
				throw new IOException("无法读取全部文件:" +r+ "!=" +len);
			}
			return raw;
		}
	}
	
	//定义编译指定java文件的方法
	private boolean compile(String javaFile)throws IOException{
		System.out.println("CompileClassLoader:正在编译" +javaFile+"...");
		//调用系统的javac命令
		Process p = Runtime.getRuntime().exec("javac "+javaFile);
		try{
			//让其他线程都等待这个线程完成
			p.waitFor();
		}catch(InterruptedException ie){
			System.out.println("编译失败:" + ie.getMessage());
		}
		//返回javac是否编程正常
		return p.exitValue()==0;
	}
	
	//重写ClassLoader的findClass方法
	protected Class<?> findClass(String name)throws ClassNotFoundException{
		Class<?> clazz = null;
		//将包路径中的'.'替换成'/'
		String filePath = name.replace('.', '/');
		String javaFile = filePath + ".java";
		String classFile = filePath + ".class";
		File jfile = new File(javaFile);
		File cfile = new File(classFile);
		//当指定java源文件存在且Class文件不存在
		//或Java源文件的修改时间比class文件的修改时间更晚时,重新编译
		if( (jfile.exists() && !cfile.exists()) || 
			(jfile.lastModified() > cfile.lastModified()) ){
			try{
				//如果编译失败,或者该class文件不存在
				if(!compile(javaFile) || !cfile.exists()){
					throw new ClassNotFoundException("ClassNotFoundException:" + javaFile);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		//如果Class文件存在,系统负责将该文件转换成Class对象
		if(cfile.exists()){
			try{
				//将class文件的二进制数据读入数组
				byte[] raw = getBytes(classFile);
				//调用ClassLoader的defineClass方法将二进制数据转换为class对象
				clazz = defineClass(name,raw,0,raw.length);
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		//如果clazz为null,则表明加载失败,则抛出异常
		if(clazz == null){
			throw new ClassNotFoundException(name);
		}
		return clazz;
	}
	
	public static void main(String[] args)throws Exception{
		//如果运行该程序时没有参数,即没有目标类
		if(args.length<1){
			System.out.println("缺少目标类,请按如下格式运行java源文件:");
			System.out.println("java CompileClassLoader ClassName");
			System.exit(1);
		}
		//第一个参数是需要运行的类
		String progClass = args[0];
		//剩下的参数将作为运行目标类时的参数,将这些参数复制到一个新数组中
		String[] progArgs = new String[args.length-1];
		System.arraycopy(args, 1, progArgs, 0, progArgs.length);
		CompileClassLoader ccl = new CompileClassLoader();
		//加载需要运行的类
		Class<?> clazz = ccl.loadClass(progClass);
		//获取需要运行的类的主方法
		Method main = clazz.getMethod("main", (new String[0]).getClass() );
		Object argsArray[] ={progArgs};
		main.invoke(null, argsArray);
	}
}
