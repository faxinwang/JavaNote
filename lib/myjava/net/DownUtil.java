package myjava.net;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownUtil {
	//定义下载资源的路径
	private String src;
	//指定所下载的文件的保存位置
	private String savePath;
	//定义需要使用多少个线程
	private int threadNum;
	//定义下载的线程对象
	private DownThread[] threads;
	//下载的文件的总大小
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
		//得到文件大小
		fileSize = conn.getContentLength();
		conn.disconnect();
		int partSize = fileSize/threadNum + 1;
		RandomAccessFile file = new RandomAccessFile(savePath,"rw");
		//设置本地文件的大小
		System.out.println("fileSize:" + fileSize);
		file.setLength(fileSize);
		file.close();
		for(int i=0;i<threadNum;++i){
			//计算每个线程下载的开始位置
			int startPos = i * partSize;
			//每个线程使用一个RandomAccessFile下载
			RandomAccessFile raf = new RandomAccessFile(savePath,"rw");
			//定位该线程下载数据的写入位置
			raf.seek(startPos);
			//创建下载线程
			threads[i] = new DownThread(startPos,partSize,raf);
			threads[i].start();
		}
	}
	
	//获取下载的完成百分比
	public double getCompleteRate(){
		//统计多个线程已经下载的总和
		int sum = 0;
		for(int i=0;i<threadNum;++i){
			sum += threads.length;
		}
		//返回已经完成的百分比
		return sum*1.0/fileSize;
	}
	
	private class DownThread extends Thread{
		//当前线程的下载位置
		private int startPos;
		//定义当前线程负责下载的文件大小
		private int partSize;
		//当前线程需要下载的文件块
		private RandomAccessFile raf;
		//该线程已下载的字节数
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
				//跳过startPos个字节,表明该线程只下载自己负责的那部分文件
				inStream.skip(startPos);
				byte[] buffer = new byte[1024];
				int hasRead = 0;
				//读取网络数,并写入本地文件
				while(length<partSize && (hasRead=inStream.read(buffer))!=-1 ){
					raf.write(buffer,0,hasRead);
					//累计该线程下载的总大小
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
