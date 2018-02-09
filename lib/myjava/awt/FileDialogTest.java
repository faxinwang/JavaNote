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
	//用事件适配器创建监听器对象
	static WindowListener listener = new WindowAdapter(){
		public void windowClosing(WindowEvent e){
			((Window)e.getSource()).dispose();
		}
	};
	
	static void testDialog(){
		Frame f = new Frame("AWT:DialogTest");
		//Dialog有三个可选参数:owner,title,modal
		//分别用于指定父窗口，窗口标题,是否模式(true/false)
		//对于模式对话框，当该对话框未被关闭之前，它的父窗口无法获得焦点
		Dialog dg1 = new Dialog(f,"模式对话框",true);
		Dialog dg2 = new Dialog(f,"非模式对话框",false);
		Button b1 = new Button("打开模式对话框");
		Button b2 = new Button("打开非模式对话框");
		
		
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
		FileDialog fload = new FileDialog(f,"选择需要打开的文件",FileDialog.LOAD);
		FileDialog fsave = new FileDialog(f,"选择保存文件的路径",FileDialog.SAVE);
		Button openf = new Button("打开文件");
		Button savef= new Button("保存文件");
		//为openf按钮添加事件监听器
		openf.addActionListener(even -> {
			fload.setVisible(true);
			//打印出用户选择的文件路径
			System.out.println("please select the file to open");
			System.out.println(fload.getDirectory() + fload.getFile());
		});
		//为保存文件按钮添加事件监听器
		savef.addActionListener(even -> {
			fload.setVisible(true);
			//打印出用户选择的文件路径
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
