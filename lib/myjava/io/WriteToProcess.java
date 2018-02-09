package myjava.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class WriteToProcess {
	
	public static void main(String[] args)throws Exception{
		//����Java ReadStandard����������и�����Ľ���
		Process pro = Runtime.getRuntime().exec("java myjava.io.ReadStandard");
		try(
			//��p���̵����������PrintStream����
			//���������Ա����������������p��������������
			PrintStream ps = new PrintStream(pro.getOutputStream());
			BufferedReader br = new BufferedReader(
					new FileReader("./lib/myjava/io/WriteToProcess.java"));
		){
			//��ReadStandard����д�����ݣ���Щ���ݽ���ReadStandard��ȡ
			//��һ�У�дһ��
			String line = null;
			while((line=br.readLine())!=null){
				ps.println(line);
			//	System.out.println(line);
			}
		}
	}
}


//�ó���Ľ��̴ӱ�׼�����ȡ����,ͬʱ�������׼����ʹ����ļ�
class ReadStandard{
	public static void main(String[] args){
		try(
			//ʹ��System.in����Scanner�������ڻ�ȡ��׼����
			Scanner sc = new Scanner(System.in);
			PrintStream ps = new PrintStream(new FileOutputStream("./lib/myjava/io/out.txt"));
		){
		//	sc.useDelimiter("\n");
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				ps.println(line);
				System.out.println(line);
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}