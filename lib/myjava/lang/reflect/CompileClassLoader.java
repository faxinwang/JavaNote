package myjava.lang.reflect;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/* �����ø���ֱ������������,�������ȱ���õ������class�ļ�
 * �÷�: java CompileClassLoader ClassName arg1 arg2...
 * 
 */

public class CompileClassLoader extends ClassLoader{
	//��ȡһ���ļ�������
	private byte[] getBytes(String filename)throws IOException{
		File file = new File(filename);
		long len = file.length();
		byte[] raw = new byte[(int)len];
		try(
				FileInputStream fin = new FileInputStream(file);
		){
			//һ�ζ�ȡclass�ļ���ȫ������������
			int r =fin.read(raw);
			if(r!=len){
				throw new IOException("�޷���ȡȫ���ļ�:" +r+ "!=" +len);
			}
			return raw;
		}
	}
	
	//�������ָ��java�ļ��ķ���
	private boolean compile(String javaFile)throws IOException{
		System.out.println("CompileClassLoader:���ڱ���" +javaFile+"...");
		//����ϵͳ��javac����
		Process p = Runtime.getRuntime().exec("javac "+javaFile);
		try{
			//�������̶߳��ȴ�����߳����
			p.waitFor();
		}catch(InterruptedException ie){
			System.out.println("����ʧ��:" + ie.getMessage());
		}
		//����javac�Ƿ�������
		return p.exitValue()==0;
	}
	
	//��дClassLoader��findClass����
	protected Class<?> findClass(String name)throws ClassNotFoundException{
		Class<?> clazz = null;
		//����·���е�'.'�滻��'/'
		String filePath = name.replace('.', '/');
		String javaFile = filePath + ".java";
		String classFile = filePath + ".class";
		File jfile = new File(javaFile);
		File cfile = new File(classFile);
		//��ָ��javaԴ�ļ�������Class�ļ�������
		//��JavaԴ�ļ����޸�ʱ���class�ļ����޸�ʱ�����ʱ,���±���
		if( (jfile.exists() && !cfile.exists()) || 
			(jfile.lastModified() > cfile.lastModified()) ){
			try{
				//�������ʧ��,���߸�class�ļ�������
				if(!compile(javaFile) || !cfile.exists()){
					throw new ClassNotFoundException("ClassNotFoundException:" + javaFile);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		//���Class�ļ�����,ϵͳ���𽫸��ļ�ת����Class����
		if(cfile.exists()){
			try{
				//��class�ļ��Ķ��������ݶ�������
				byte[] raw = getBytes(classFile);
				//����ClassLoader��defineClass����������������ת��Ϊclass����
				clazz = defineClass(name,raw,0,raw.length);
			}catch(IOException ioe){
				ioe.printStackTrace();
			}
		}
		//���clazzΪnull,���������ʧ��,���׳��쳣
		if(clazz == null){
			throw new ClassNotFoundException(name);
		}
		return clazz;
	}
	
	public static void main(String[] args)throws Exception{
		//������иó���ʱû�в���,��û��Ŀ����
		if(args.length<1){
			System.out.println("ȱ��Ŀ����,�밴���¸�ʽ����javaԴ�ļ�:");
			System.out.println("java CompileClassLoader ClassName");
			System.exit(1);
		}
		//��һ����������Ҫ���е���
		String progClass = args[0];
		//ʣ�µĲ�������Ϊ����Ŀ����ʱ�Ĳ���,����Щ�������Ƶ�һ����������
		String[] progArgs = new String[args.length-1];
		System.arraycopy(args, 1, progArgs, 0, progArgs.length);
		CompileClassLoader ccl = new CompileClassLoader();
		//������Ҫ���е���
		Class<?> clazz = ccl.loadClass(progClass);
		//��ȡ��Ҫ���е����������
		Method main = clazz.getMethod("main", (new String[0]).getClass() );
		Object argsArray[] ={progArgs};
		main.invoke(null, argsArray);
	}
}
