package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;

import javax.swing.Box;

/* BoxLayout����CardBagLayout���ŵ㣬��û������ô����,
 * BoxLayout(container target,int axis):ָ����������target�Ĳ��ֹ�����
 * axisָ������������е����з���:BoxLayout.X_AXIS(����),BoxLayout.Y_AXIS(����);
 * 
 * BoxLayoutͨ����Box����һ��ʹ��,Box����Ĭ��ʹ��BoxLayout���ֹ�����,Box�ṩ��������������
 * ������Box����:
 * createHorizontalBox():����һ��ˮƽ���������Box����
 * createVerticalBox():����һ����ֱ���������Box����
 * Box�ṩ������5������������Glue,Strut,RigidArea.
 * createHorizontalGlue():����һ��ˮƽ��glue(��������������ͬʱ����ļ��)
 * createVerticalGlue():����һ����ֱglue(��������������ͬʱ����ļ��)
 * createHorizontalStrut(int width):����һ��ָ����ȵ�ˮƽstrut(����ˮƽ����������ļ��)
 * createVerticalStrut(int height):����һ��ָ���߶ȵĴ�ֱstrut(����ˮƽ����������ļ��)
 * createRigidArea(Dimension d):����ָ����ȣ��߶ȵ�RigidArea(��������ļ��)
 */
public class BoxLayoutTest {

	Frame f = new Frame("AWT:BoxLayout");
	//����ˮƽ�ڷ������box��������
	Box horizontal = Box.createHorizontalBox();
	//���崹ֱ�ڷ������box��������
	Box vertical = Box.createVerticalBox();
	void init(){
		horizontal.add(new Button("ˮƽ��ť1"));
		horizontal.add(Box.createHorizontalGlue());
		horizontal.add(new Button("ˮƽ��ť2"));
		//���ˮƽ���򲻿�����ļ�࣬����Ϊ10
		horizontal.add(Box.createHorizontalStrut(10));
		horizontal.add(new Button("ˮƽ��ť3"));
		vertical.add(new Button("��ֱ��ť1"));
		vertical.add(Box.createVerticalGlue());
		vertical.add(new Button("��ֱ��ť2"));
		vertical.add(Box.createVerticalStrut(15));
		vertical.add(new Button("��ֱ��ť3"));
		f.add(horizontal,BorderLayout.NORTH);
		f.add(vertical);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new BoxLayoutTest().init();
	}
}
