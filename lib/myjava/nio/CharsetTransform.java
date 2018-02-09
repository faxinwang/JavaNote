package myjava.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTransform {
	//该方法打印出java支持的所有字符集
	static void test1(){
		SortedMap<String,Charset> map = Charset.availableCharsets();
		for(String alias:map.keySet()){
			//输出字符集别名和对应的Charset对象
			System.out.println(alias + "---->" + map.get(alias));
		}
	}
	
	static void test2(){
		//创建简体中文对应的Charset
		Charset cn = Charset.forName("GBK");
		//创建一个CharBuffer对象
		CharBuffer cbuf = CharBuffer.allocate(15);
		cbuf.put('王');
		cbuf.put('发');
		cbuf.put('新');
		cbuf.put('李');
		cbuf.put('蝶');
		cbuf.flip();
		//将CharBuffer中字符序列转换成字节序列
		ByteBuffer bbuf = cn.encode(cbuf);
		//循环访问bbuf中的每个字节
		//注意，只有调用bbuf.get(i)时，才可以访问position后面的内容
		//其他getXxx(i)需要对数据进行相应的解释，所以不能越界访问
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
		//将ByteBuffer的数据解密成字符序列
		System.out.println("\n" + cn.decode(bbuf));
	}
	
	public static void main(String[] args){
	//	test1();
		test2();
	}
}
