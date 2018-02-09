package myjava.awt;

import static java.awt.BorderLayout.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextField;

/* Frame,Dialog,ScrollPaneĬ�϶�ʹ����BorderLayout���ֹ�����
 * BorderLayout()
 * BordreLayout(int hgap,int vgap)
 * ��BorderLayout��������ʱ,Ӧʹ���侲̬����ָ����������λ��:
 * EAST(�ң���),NORTH(�ϣ���),WEST(����),SOUTH(�£���),CNETER(��)
 */
public class BorderLayoutTest {
	static void test1(){
		Frame f = new Frame("AWT-Freame:BorderLayout");
		f.setLayout(new BorderLayout(30,5));//���ò��ֹ�����
		f.add(new Button("��"),SOUTH);
		f.add(new Button("��"),NORTH);
		f.add(new Button("��"),EAST);
		f.add(new Button("��"),WEST);
		f.add(new Button("��"));//Ĭ����ӵ��м�����
		//�������ڵ���Ѵ�С
		f.pack();
		f.setVisible(true);
	}
	static void test2(){
		Frame f = new Frame("AWT-Frame:BorderLayout");
		f.setLayout(new BorderLayout(30,5));
		f.add(new Button("��"), SOUTH);
		f.add(new Button("��"),NORTH);
		Panel p = new Panel();
		p.add(new TextField(20));
		p.add(new Button("btn"));
		f.add(p);
		f.add(new Button("��"),EAST);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args){
		test1();
		test2();
	}
}
