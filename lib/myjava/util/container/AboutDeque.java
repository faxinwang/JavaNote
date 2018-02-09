package myjava.util.container;

import java.util.ArrayDeque;
import static java.lang.System.*;

/* 实现了Deque的类有ArrayDeque 和 LinkedList.
 * Deque定义了下面一些几口,这些方法允许从两端来操作队列:
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
		stack.push("疯狂java讲义");
		stack.push("疯狂Android讲义");
		stack.push("疯狂ios讲义");
		out.println("stack: "+stack);
		out.println("栈顶元素:" + stack.peek());
		out.println("弹出栈顶元素:" + stack.pop());
		out.println("stack: "+stack);
	}
	static void test2(){
		ArrayDeque<String> queue = new ArrayDeque<String>();
		queue.offer("疯狂java讲义");
		queue.offer("疯狂Android讲义");
		queue.offer("疯狂ios讲义");
		out.println("queue: "+queue);
		out.println("队头元素:" + queue.peek());
		out.println("弹出队头元素:" + queue.poll());
		out.println("queue: "+queue);
	}
	
	public static void main(String[] args){
		test1();
		test2();
	}
}
