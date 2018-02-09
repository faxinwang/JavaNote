package myjava.awt;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;

//AWT�ṩ��FlowLayout,BorderLayout,GridLayout,GirdBagLayout,CardLayout
//Swing ���ṩ��һ��BoxLayout

/* FlowLayout�ṩ���������캯��: 
 * FlowLayout():ʹ��Ĭ�ϵĶ��䷽ʽ��ˮƽ��࣬��ֱ���
 * FlowLayout(int align):ʹ��ָ���Ķ��䷽ʽ��Ĭ�ϵļ��
 * FlowLayout(int align,int hgap,int vgap):
 * align����ָ����������з���(�������ң��������󣬴��м������ߵ�)��
 * �ò���Ӧ��ʹ��FlowLayout�ľ�̬������FlowLayout.LEFT,--.CENTER,--.RIGHT
 */

public class FlowLayoutTest {
	
	static void test1(){
		//Frame��Ĭ�ϲ��ֹ�������BorderLayout
		Frame f =new Frame("AWT-Frame1:FlowLayout.LEFT");
		f.setLayout(new FlowLayout(FlowLayout.LEFT,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("��ť" + i));
		}
		//���ô���Ϊ��Ѵ�С
		f.pack();
		f.setVisible(true);
	}
	static void test2(){
		//Frame��Ĭ�ϲ��ֹ�������BorderLayout
		Frame f =new Frame("AWT-Frame2:FlowLayout.RIGHT");
		f.setLayout(new FlowLayout(FlowLayout.RIGHT,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("��ť" + i));
		}
		//���ô���Ϊ��Ѵ�С
		f.pack();
		f.setVisible(true);
	}
	static void test3(){
		//Frame��Ĭ�ϲ��ֹ�������BorderLayout
		Frame f =new Frame("AWT-Frame3:FlowLayout.CENTER");
		f.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("��ť" + i));
		}
		//���ô���Ϊ��Ѵ�С
		f.pack();
		f.setVisible(true);
	}
	static void test4(){
		//Frame��Ĭ�ϲ��ֹ�������BorderLayout
		Frame f =new Frame("AWT-Frame4:FlowLayout.LEADING");
		f.setLayout(new FlowLayout(FlowLayout.LEADING,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("��ť" + i));
		}
		//���ô���Ϊ��Ѵ�С
		f.pack();
		f.setVisible(true);
	}
	static void test5(){
		//Frame��Ĭ�ϲ��ֹ�������BorderLayout
		Frame f =new Frame("AWT-Frame5:FlowLayout.TRAILING");
		f.setLayout(new FlowLayout(FlowLayout.TRAILING,10,5));
		for(int i=0;i<10;++i){
			f.add(new Button("��ť" + i));
		}
		//���ô���Ϊ��Ѵ�С
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		test1();
		test2();
		test3();
		test4();
		test5();
	}
}
