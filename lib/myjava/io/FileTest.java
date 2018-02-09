package myjava.io;

import static java.lang.System.out;
import java.io.File;
import java.io.FilenameFilter;

//import org.junit.Test;

/* 1.File������ļ�����ط���
 * String getName():���ش�file��������ʾ���ļ�������·����(�����·�����򷵻����һ����·����)
 * String getPath():���ش�file��������Ӧ��·��.
 * File getAbsoluteFile():���ش�file����ľ���·������Ӧ��file����
 * String getAbsolutePath():���ش�file����ľ���·��
 * String getParent():������һ��Ŀ¼��·��
 * boolean renameTo(File newName):��������file�����Ӧ���ļ���Ŀ¼������������ɹ����򷵻�true
 * 
 * 2.�ļ������صķ���
 * boolean exists():�ж�file�����Ӧ���ļ��Ƿ����
 * boolean canWrite():�ж�file�����Ӧ���ļ���Ŀ¼�Ƿ��д
 * boolean canRead():�ж�file�����Ӧ���ļ���Ŀ¼�Ƿ�ɶ�
 * boolean isFile():�ж�file�����Ƿ��ʾһ���ļ�
 * boolean isDirectory():�ж�file�����Ƿ��ʾһ��Ŀ¼
 * boolean isAbsolute():�ж�file��������Ӧ���ļ���Ŀ¼�Ƿ��Ǿ���Ŀ¼
 * 
 * 3.��ȡ�����ļ���Ϣ
 * long lastModified():��ȡ�ϴ��޸�ʱ��
 * long lenth():�����ļ����ݵĳ���
 * 
 * 4.�ļ�������ط���
 * boolean createNewFile():���˶�������Ӧ���ļ�������ʱ���÷������½�һ����file��������Ӧ���ļ�
 * boolean delete():ɾ��file��������Ӧ���ļ���·��
 * static File createTempFile(String prefix,String suffix):��Ĭ�ϵ���ʱ�ļ����д���һ����ʱ�ļ���ʹ��prefix+ϵͳ
 * 			���ɵ���������ͺ�׺suffix��Ϊ�ļ���������prefix������������Ϊ3
 * static File createTempFile(String prefix,String suffix,File dir):��dirָ����Ŀ¼�д���һ����ʱ�ļ�
 * void deleteOnExit():ע��һ��ɾ�����ӣ���jvm�˳�ʱ��ɾ��file��������Ӧ���ļ���Ŀ¼
 * 
 * 5.Ŀ¼������صķ���
 * boolean mkdir():��ͼ����һ��file��������Ӧ��Ŀ¼����������ɹ����򷵻�true�����ø÷���ʱ��file���������һ��·��������һ���ļ�
 * String[] list():�г�file��������·���µ������ļ�����·����������String����
 * File[] listFiles():�г�file��������·���µ������ļ�����·����������File����
 * static File[] listRoots():�г�ϵͳ���еĸ�·��
 */


public class FileTest{
	void filenameFilterTest(){
		File file = new File("./src");
		String[] nameList = file.list(new FilenameFilter(){
			@Override
			public boolean accept(File dir, String name) {
				//����ļ�����.java��β�����ļ���Ӧһ��·�������г����ļ���·��
				out.println("dir:"+dir);
				out.println("name:"+name);
				String path = dir.toString();
				//��·���еķ�б��'\'����б�߲��ܱ�ʾ·��'/'
				//��б��Ҫ��ʾ·����Ҫת�룺'\\'
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
		//�Ե�ǰ·��������һ��File����
		File file =new File("./src");
		//��ȡ�ļ����� �����"."
		out.println("file.getName():"+file.getName());
		out.println("file.getPath():"+file.getPath());
		//��ȡ���·���ĸ�·�����ܻ��������������null
		out.println("file.getParent():"+file.getParent());
		//��ȡ����·��
		out.println("file.getAbsolutePath():"+file.getAbsolutePath());
		out.println("file.getAbsoluteFile():"+file.getAbsoluteFile());
		//��ȡ��һ��·��
		out.println("file.getAbsoluteFile().getParent():"+file.getAbsoluteFile().getParent());
		//�ڵ�ǰ·���´���һ����ʱ�ļ�
		File tmpFile = File.createTempFile("aaa", ".txt",file);
		//ָ����jvm������˳�ʱɾ�����ļ�
		tmpFile.deleteOnExit();
		//��ϵͳ��ǰʱ��Ϊ���ļ������������ļ�
		File newFile  = new File(System.currentTimeMillis()+"");
		out.println("newFile�ļ��Ƿ����" + newFile.exists());
		//��ָ��newFile��������ʱ�ļ�
		out.println("newFile.createNewFile(): "+newFile.createNewFile());
		//��newFile����������һ��Ŀ¼����ΪnewFile�Ѿ����ڣ��������淽������false,���޷�������Ŀ¼
		out.println("newFile.mkdir(): "+newFile.mkdir());
		
		//ʹ��list()�����г���ǰ·���µ������ļ���·��
		String[] fileList = file.list();
		out.println("==============��ǰ·���������ļ���·������===========");
		for(String name:fileList){
			out.println(name);
		}
		//ʹ��listRoots()�����г����еĴ��̸�Ŀ¼
		File[] roots = File.listRoots();
		out.println("==============ϵͳ���и�·������===========");
		for(File root:roots){
			out.println(root);
		}	
	}
	public static void main(String[] args)throws Exception{
//		fileTest();
//		filenameFilterTest();
	}
}
