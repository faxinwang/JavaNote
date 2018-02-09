package myjava.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTransform {
	//�÷�����ӡ��java֧�ֵ������ַ���
	static void test1(){
		SortedMap<String,Charset> map = Charset.availableCharsets();
		for(String alias:map.keySet()){
			//����ַ��������Ͷ�Ӧ��Charset����
			System.out.println(alias + "---->" + map.get(alias));
		}
	}
	
	static void test2(){
		//�����������Ķ�Ӧ��Charset
		Charset cn = Charset.forName("GBK");
		//����һ��CharBuffer����
		CharBuffer cbuf = CharBuffer.allocate(15);
		cbuf.put('��');
		cbuf.put('��');
		cbuf.put('��');
		cbuf.put('��');
		cbuf.put('��');
		cbuf.flip();
		//��CharBuffer���ַ�����ת�����ֽ�����
		ByteBuffer bbuf = cn.encode(cbuf);
		//ѭ������bbuf�е�ÿ���ֽ�
		//ע�⣬ֻ�е���bbuf.get(i)ʱ���ſ��Է���position���������
		//����getXxx(i)��Ҫ�����ݽ�����Ӧ�Ľ��ͣ����Բ���Խ�����
		System.out.println("\nbuf.get(i):");
		for(int i=0;i<bbuf.capacity();++i){
			System.out.print(bbuf.get(i)+" ");
		}
		System.out.println("\nbuf.getChar(i):");
		for(int i=0;i<bbuf.position();++i){
			System.out.print(bbuf.getChar(i)+" ");
		}
		System.out.println("\nbuf.getDouble(i):");
		for(int i=0;i<bbuf.position();++i){
			System.out.println(bbuf.getDouble(i)+" ");
		}
		System.out.println("\nbuf.getFloat(i):");
		for(int i=0;i<bbuf.position();++i){
			System.out.println(bbuf.getFloat(i) + " ");
		}
		System.out.println("\nbuf.getLong(i):");
		for(int i=0;i<bbuf.position();++i){
			System.out.println(bbuf.getLong(i));
		}
		System.out.println("\nbuf.getInt(i):");
		for(int i=0;i<bbuf.position();++i){
			System.out.println(bbuf.getInt(i));
		}
		System.out.println("\nbuf.getShort(i):");
		for(int i=0;i<bbuf.position();++i){
			System.out.println(bbuf.getShort(i)+" ");
		}
		//��ByteBuffer�����ݽ��ܳ��ַ�����
		System.out.println("\n" + cn.decode(bbuf));
	}
	
	public static void main(String[] args){
	//	test1();
		test2();
	}
}
