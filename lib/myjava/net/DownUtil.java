package myjava.net;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownUtil {
	//����������Դ��·��
	private String src;
	//ָ�������ص��ļ��ı���λ��
	private String savePath;
	//������Ҫʹ�ö��ٸ��߳�
	private int threadNum;
	//�������ص��̶߳���
	private DownThread[] threads;
	//���ص��ļ����ܴ�С
	private int fileSize;
	
	public DownUtil(String src,String savePath,int threadNum){
		this.src = src;
		this.savePath = savePath;
		this.threadNum = threadNum;
	}
	
	public void download()throws Exception{
		URL url = new URL(src);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setConnectTimeout(5*1000);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept-Language", "zh-CN");
		conn.setRequestProperty("Charset", "UTF-8");
		conn.setRequestProperty("Connection","Keep-Alive");
		conn.setRequestProperty("Accept",
				"image/gif, image/jpeg, image/pjpeg," +
				"application/x-shockwave-flash,application/xaml+xml,"+
				"application/vnd.ms-xpsdocument,application/x-ms-xbap," +
				"application/x-ms-application,application/vnd.ms-excel,"+
				"application/vnd.ms-powerpoint,application/msword,*/*");
		//�õ��ļ���С
		fileSize = conn.getContentLength();
		conn.disconnect();
		int partSize = fileSize/threadNum + 1;
		RandomAccessFile file = new RandomAccessFile(savePath,"rw");
		//���ñ����ļ��Ĵ�С
		System.out.println("fileSize:" + fileSize);
		file.setLength(fileSize);
		file.close();
		for(int i=0;i<threadNum;++i){
			//����ÿ���߳����صĿ�ʼλ��
			int startPos = i * partSize;
			//ÿ���߳�ʹ��һ��RandomAccessFile����
			RandomAccessFile raf = new RandomAccessFile(savePath,"rw");
			//��λ���߳��������ݵ�д��λ��
			raf.seek(startPos);
			//���������߳�
			threads[i] = new DownThread(startPos,partSize,raf);
			threads[i].start();
		}
	}
	
	//��ȡ���ص���ɰٷֱ�
	public double getCompleteRate(){
		//ͳ�ƶ���߳��Ѿ����ص��ܺ�
		int sum = 0;
		for(int i=0;i<threadNum;++i){
			sum += threads.length;
		}
		//�����Ѿ���ɵİٷֱ�
		return sum*1.0/fileSize;
	}
	
	private class DownThread extends Thread{
		//��ǰ�̵߳�����λ��
		private int startPos;
		//���嵱ǰ�̸߳������ص��ļ���С
		private int partSize;
		//��ǰ�߳���Ҫ���ص��ļ���
		private RandomAccessFile raf;
		//���߳������ص��ֽ���
		public int length;
		public DownThread(int start,int partSize,RandomAccessFile raf){
			this.startPos = start;
			this.partSize = partSize;
			this.raf = raf;
		}
		public void run(){
			try{
				URL url = new URL(src);
				HttpURLConnection conn =(HttpURLConnection)url.openConnection();
				conn.setConnectTimeout(5 * 1000);
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept",
						"image/gif, image/jpeg, image/pjpeg," +
						"application/x-shockwave-flash,application/xaml+xml,"+
						"application/vnd.ms-xpsdocument,application/x-ms-xbap," +
						"application/x-ms-application,application/vnd.ms-excel,"+
						"application/vnd.ms-powerpoint,application/msword,*/*");
				conn.setRequestProperty("Accept-Language","zh-CN");
				conn.setRequestProperty("Charset","UTF-8");
				InputStream inStream = conn.getInputStream();
				//����startPos���ֽ�,�������߳�ֻ�����Լ�������ǲ����ļ�
				inStream.skip(startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				//��ȡ������,��д�뱾���ļ�
				while(length<partSize && (hasRead=inStream.read(buffer))!=-1 ){
					raf.write(buffer,0,hasRead);
					//�ۼƸ��߳����ص��ܴ�С
					length += hasRead;
				}
				raf.close();
				inStream.close();
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
}
