package myjava.util.container;

import java.util.ArrayDeque;
import static java.lang.System.*;

/* ʵ����Deque������ArrayDeque �� LinkedList.
 * Deque����������һЩ����,��Щ�����������������������:
 * void 	addFirst(Object e):add e to frist
 * void 	addLast(Obejct e): add e to last
 * Iterator	descendingIterator(): return a reverse iterator
 * Object	getFirst():return the first element
 * Object	getLast(): return teh last elem
 * boolean 	offerFirst(Object e): similar to addFirst()
 * boolean	offerLast(Object e): similar to addLast()
 * Object	peekFirst():return the first elem or null
 * Object	peekLast(): return the last elem or null
 * Object	pollFirst(): return and remove the first elem or null
 * Object	pollLast():return and remove the last elem or null
 * Object	removeFirst():return and remove the first elem
 * Object	removeFirstOccurrence(Object e): remove the first occurrence of e 
 * Object	removeLast(): return and remove the last elem
 * Object	removeLastOccurrence(Object e): return the occurrence of e
 * Object	pop(): return and remove the first elem
 * Object	push(Object e): push the elem at first
 */

public class AboutDeque {
	static void test1(){
		ArrayDeque<String> stack = new ArrayDeque<String>();
		stack.push("���java����");
		stack.push("���Android����");
		stack.push("���ios����");
		out.println("stack: "+stack);
		out.println("ջ��Ԫ��:" + stack.peek());
		out.println("����ջ��Ԫ��:" + stack.pop());
		out.println("stack: "+stack);
	}
	static void test2(){
		ArrayDeque<String> queue = new ArrayDeque<String>();
		queue.offer("���java����");
		queue.offer("���Android����");
		queue.offer("���ios����");
		out.println("queue: "+queue);
		out.println("��ͷԪ��:" + queue.peek());
		out.println("������ͷԪ��:" + queue.poll());
		out.println("queue: "+queue);
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
