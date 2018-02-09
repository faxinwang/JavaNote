package myjava.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;


/* 不仅InputStream，OutputStream包含了getChannel()方法，RandomAccessFile也包含了
 * getChannel方法，返回的channel的读写模式同RandomAccessFile的模式
 */


public class RandomAccessChannelTest {
	//该方法将文件"./src/tmp.txt"中的内容复制后追加到该文件后面。
	static void test1(){
		File f = new File("./src/tmp.txt");
		try(
			RandomAccessFile raf = new RandomAccessFile(f,"rw");
			//获取RandomAccessFile对应的Channel
			FileChannel randChannel = raf.getChannel();
		){
			//将randChannel中的所有数据映射成ByteBuffer
			ByteBuffer bbuf = randChannel.map(MapMode.READ_ONLY, 0, f.length());
			//把channel的记录指针移动到最后
			randChannel.position(f.length());
			//将Buffer中的数据全部输出
			randChannel.write(bbuf);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//该方法将本文件的内容读取到控制台输出
	static void test2(){
		try(
			FileInputStream fis = new FileInputStream("./lib/myjava/nio/RandomAccessChannelTest.java");
			FileChannel channel = fis.getChannel();
		){
			//定义一个Buffer对象，用于重复读取
			ByteBuffer bbuf = ByteBuffer.allocate(256);
			//将channel中的数据放入ByteBuffer中
			while(channel.read(bbuf) != -1){
				//锁定bbuf的空白区
				bbuf.flip();
				//创建Charset对象
				Charset charset=Charset.forName("GBK");
				//创建Charset解码器
				CharsetDecoder decoder = charset.newDecoder();
				//将ByteBuffer内容转码
				CharBuffer cbuf = decoder.decode(bbuf);
				System.out.println(cbuf);
				//将bbuf初始化,为下一次读取数据做准备(将position设为0,limit设为capacity)
				bbuf.clear();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args){
	//	test1();
		test2();
	}

}
