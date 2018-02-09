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
			//����FileInputStream�����ø�������FileChannel
			FileChannel inChannel = new FileInputStream(f).getChannel();
			FileChannel outChannel = new FileOutputStream("./src/tmp.txt").getChannel();
		){
			//��FileChannel�������ȫ��ӳ���ByteBuffer
			MappedByteBuffer buffer = inChannel.map(MapMode.READ_ONLY, 0, f.length());
			//ֱ�ӽ�buffer�������ȫ��������ļ�
			outChannel.write(buffer);
			//ʹ��GBK�ַ���������������
			Charset charset = Charset.forName("gbk");
			//����buffer.clear()����,��ԭposition��limit��λ��
			buffer.clear();
			//����������
			CharsetDecoder decoder =charset.newDecoder();
			//ʹ�ý�������ByteBuffer�����CharBuffer
			CharBuffer cbuf = decoder.decode(buffer);
			//CharBuffer��toString�������Ի�ȡ��Ӧ���ַ���
			System.out.println(cbuf);
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
