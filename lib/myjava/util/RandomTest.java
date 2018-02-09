package myjava.util;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/*
 * Java 7 新曾了一个ThreadLocalRandom的线程安全类，其用法与
 * Random类似.
 */

@SuppressWarnings("unused")
public class RandomTest {
	public static void main(String[] args){
//		Random rand = new Random();
		//用current()静态方法得到对象
		ThreadLocalRandom rand =ThreadLocalRandom.current();
		
		System.out.println("rand.nextBoolean():"+ rand.nextBoolean());
		byte[] buffer = new byte[16];
		rand.nextBytes(buffer);
		System.out.println(Arrays.toString(buffer));
		//生成[0.0,1.0)之间的伪随机数
		System.out.println("rand.nextDouble():"+rand.nextDouble());
		//生成[0.0,1.0)之间的伪随机数
		System.out.println("rand.nextFloat():"+rand.nextFloat());
		//生成平局值是0.0，标准差是1.0的伪高斯数
		System.out.println("rand.nextGaussian():"+rand.nextGaussian());
		//生成一个处于int范围内的伪int数
		System.out.println("rand.nextInt():"+rand.nextInt());
		//生成一个处于[0,26)范围内的伪int数
		System.out.println("rand.nextInt():"+rand.nextInt(26));
		//生成一个long型伪随机数
		System.out.println("rand.nextInt():"+rand.nextLong());
	}
}
