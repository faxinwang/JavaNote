package myjava.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {

	public static void main(String[] args){
		File f = new File("./lib/myjava/nio/FileChannelTest.java");
		try(
			//创建FileInputStream流，用该流创建FileChannel
			FileChannel inChannel = new FileInputStream(f).getChannel();
			FileChannel outChannel = new FileOutputStream("./src/tmp.txt").getChannel();
		){
			//将FileChannel里的数据全部映射成ByteBuffer
			MappedByteBuffer buffer = inChannel.map(MapMode.READ_ONLY, 0, f.length());
			//直接将buffer里的数据全部输出到文件
			outChannel.write(buffer);
			//使用GBK字符集来创建解码器
			Charset charset = Charset.forName("gbk");
			//调用buffer.clear()方法,复原position，limit的位置
			buffer.clear();
			//创建解码器
			CharsetDecoder decoder =charset.newDecoder();
			//使用解码器将ByteBuffer解码成CharBuffer
			CharBuffer cbuf = decoder.decode(buffer);
			//CharBuffer的toString方法可以获取对应的字符串
			System.out.println(cbuf);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
