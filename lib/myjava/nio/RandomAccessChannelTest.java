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


/* ����InputStream��OutputStream������getChannel()������RandomAccessFileҲ������
 * getChannel���������ص�channel�Ķ�дģʽͬRandomAccessFile��ģʽ
 */


public class RandomAccessChannelTest {
	//�÷������ļ�"./src/tmp.txt"�е����ݸ��ƺ�׷�ӵ����ļ����档
	static void test1(){
		File f = new File("./src/tmp.txt");
		try(
			RandomAccessFile raf = new RandomAccessFile(f,"rw");
			//��ȡRandomAccessFile��Ӧ��Channel
			FileChannel randChannel = raf.getChannel();
		){
			//��randChannel�е���������ӳ���ByteBuffer
			ByteBuffer bbuf = randChannel.map(MapMode.READ_ONLY, 0, f.length());
			//��channel�ļ�¼ָ���ƶ������
			randChannel.position(f.length());
			//��Buffer�е�����ȫ�����
			randChannel.write(bbuf);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//�÷��������ļ������ݶ�ȡ������̨���
	static void test2(){
		try(
			FileInputStream fis = new FileInputStream("./lib/myjava/nio/RandomAccessChannelTest.java");
			FileChannel channel = fis.getChannel();
		){
			//����һ��Buffer���������ظ���ȡ
			ByteBuffer bbuf = ByteBuffer.allocate(256);
			//��channel�е����ݷ���ByteBuffer��
			while(channel.read(bbuf) != -1){
				//����bbuf�Ŀհ���
				bbuf.flip();
				//����Charset����
				Charset charset=Charset.forName("GBK");
				//����Charset������
				CharsetDecoder decoder = charset.newDecoder();
				//��ByteBuffer����ת��
				CharBuffer cbuf = decoder.decode(bbuf);
				System.out.println(cbuf);
				//��bbuf��ʼ��,Ϊ��һ�ζ�ȡ������׼��(��position��Ϊ0,limit��Ϊcapacity)
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
