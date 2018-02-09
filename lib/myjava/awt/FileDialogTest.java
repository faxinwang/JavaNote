package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDialogTest {
	//���¼���������������������
	static WindowListener listener = new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			((Window)e.getSource()).dispose();
		}
	};
	
	static void testDialog(){
		Frame f = new Frame("AWT:DialogTest");
		//Dialog��������ѡ����:owner,title,modal
		//�ֱ�����ָ�������ڣ����ڱ���,�Ƿ�ģʽ(true/false)
		//����ģʽ�Ի��򣬵��öԻ���δ���ر�֮ǰ�����ĸ������޷���ý���
		Dialog dg1 = new Dialog(f,"ģʽ�Ի���",true);
		Dialog dg2 = new Dialog(f,"��ģʽ�Ի���",false);
		Button b1 = new Button("��ģʽ�Ի���");
		Button b2 = new Button("�򿪷�ģʽ�Ի���");
		
		
		dg1.addWindowListener(listener);
		dg2.addWindowListener(listener);
		f.addWindowListener(listener);
		
		dg1.setBounds(20,30,300,400);
		dg2.setBounds(20,30,300,400);
		b1.addActionListener(e -> dg1.setVisible(true));
		b2.addActionListener(e -> dg2.setVisible(true));
		f.add(b1);
		f.add(b2,BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
	}
	
	static void testFileDialog(){
		Frame f =new Frame("AWT:FileDialogTest");
		FileDialog fload = new FileDialog(f,"ѡ����Ҫ�򿪵��ļ�",FileDialog.LOAD);
		FileDialog fsave = new FileDialog(f,"ѡ�񱣴��ļ���·��",FileDialog.SAVE);
		Button openf = new Button("���ļ�");
		Button savef= new Button("�����ļ�");
		//Ϊopenf��ť����¼�������
		openf.addActionListener(even -> {
			fload.setVisible(true);
			//��ӡ���û�ѡ����ļ�·��
			System.out.println("please select the file to open");
			System.out.println(fload.getDirectory() + fload.getFile());
		});
		//Ϊ�����ļ���ť����¼�������
		savef.addActionListener(even -> {
			fload.setVisible(true);
			//��ӡ���û�ѡ����ļ�·��
			System.out.println("please select a path to save file");
			System.out.println(fload.getDirectory());
			FileOutputStream file=null;
			try{
				 file = new FileOutputStream(fsave.getDirectory() + "testingFile.txt");
			}catch(IOException e){
				System.out.println("Unknown IOException");
			}finally{
				if(file!=null){
					try{
						file.close();
					}catch(Exception e){
						System.out.println("unable to close file!");
					}
				}
			}
		});
		f.add(openf);
		f.add(savef,BorderLayout.SOUTH);
		f.addWindowListener(listener);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		testDialog();
		testFileDialog();
	}
}
