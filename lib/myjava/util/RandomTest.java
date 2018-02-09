package myjava.util;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
/*
 * Java 7 ������һ��ThreadLocalRandom���̰߳�ȫ�࣬���÷���
 * Random����.
 */

@SuppressWarnings("unused")
public class RandomTest {
	public static void main(String[] args){
//		Random rand = new Random();
		//��current()��̬�����õ�����
		ThreadLocalRandom rand =ThreadLocalRandom.current();
		
		System.out.println("rand.nextBoolean():"+ rand.nextBoolean());
		byte[] buffer = new byte[16];
		rand.nextBytes(buffer);
		System.out.println(Arrays.toString(buffer));
		//����[0.0,1.0)֮���α�����
		System.out.println("rand.nextDouble():"+rand.nextDouble());
		//����[0.0,1.0)֮���α�����
		System.out.println("rand.nextFloat():"+rand.nextFloat());
		//����ƽ��ֵ��0.0����׼����1.0��α��˹��
		System.out.println("rand.nextGaussian():"+rand.nextGaussian());
		//����һ������int��Χ�ڵ�αint��
		System.out.println("rand.nextInt():"+rand.nextInt());
		//����һ������[0,26)��Χ�ڵ�αint��
		System.out.println("rand.nextInt():"+rand.nextInt(26));
		//����һ��long��α�����
		System.out.println("rand.nextInt():"+rand.nextLong());
	}
}
