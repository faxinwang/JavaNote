package myjava.nio;

import java.nio.CharBuffer;

public class BufferTest {
	
	public static void main(String[] args){
		//创建Buffer
		CharBuffer buf = CharBuffer.allocate(8);
		System.out.println("capacity: " + buf.capacity());
		System.out.println("limit: " + buf.limit());
		System.out.println("posittion: " +buf.position());
		//放入元素
		buf.put('a');
		buf.put('b');
		buf.put('c');
		System.out.println("after put three elements:");
		System.out.println("buf: "+buf);
		System.out.println("position: " +buf.position());
		buf.flip();
		System.out.println("after flip:");
		System.out.println("position: " + buf.position());
		System.out.println("limit: " + buf.limit());
		System.out.println("buf.get(): " + buf.get());
		System.out.println("position: " + buf.position());
		System.out.println("buf.remaining(): "+buf.remaining());
		try{
			System.out.println("buf.get(3): " + buf.get(3));
		}catch(Exception e){
			System.out.println("3 is out of bounds!");
		}
		buf.clear();
		System.out.println("position:"+buf.position());
		System.out.println("limit:" + buf.limit());
		System.out.println("buf: "+ buf);
	}
}
