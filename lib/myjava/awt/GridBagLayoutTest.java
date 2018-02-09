package myjava.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/* ʹ��GridBagLayout�Ĳ���:
 * 1.����GridBagLayout���ֹ���������ָ������ʹ�øò��ֹ�����
 * 	 GridBagLayout gbl = new GridBagLayout();
 *   container.setLayout(gbl);
 * 2.����GridBagConstraints���󣬲����øö�����������
 *   GridBagConstraints gbc = new GridBagConstraints();
 *   gbc.gridx = 1;//�����ܿ�������������еĺ�������(������0��ʼ)
 *   gbc.gridwidth = 2;//�����ܿ��Ƶ���������Խ���ٸ�����
 *   gbc.fill = GridBagConstraints.NONE:���������
 *   gbc.fill = GridBagConstraints.HORIZONTAL:���ˮƽ������ռ�ݿհ׿ռ�
 *   gbc.fill = GridBagConstraints.VERTICAL:�����ֱ������ռ�ݿհ׿ռ�
 *   gbc.fill = GridBagConstraints.BOTH:���ˮƽ����ֱͬʱ������ռ�ݿհ׿ռ�
 *   gbc.ipadx = 5 ; //���������������߼ʵ�ˮƽ����
 *   gbc.insets;//����������ⲿ��䣬������߼�����ʾ����߽�֮��ľ���
 *   gbc.anchor;//�������������ʾ�����еĶ�λ��ʽ,��ֵΪGridBagConstraints���е�ö��ֵ
 *   gbc.weightx; //���������ˮƽ������������������ռ�ݵĿռ����,�����Ե�Ĭ��ֵΪ0,��Ĭ�ϲ�ռ�ݶ���ռ�
 *   ...
 * 3.����GridBagLayout����ķ���������GridBagConstraints������ܿ��Ƶ����֮��Ĺ���
 *   gbl.setConstraints(widget gbc);����wedget�����gbc�������
 * 4.�������ӵ�����
 *   container.add(widget);
 */


public class GridBagLayoutTest {
	Frame f = new Frame("AWT:GridBagLayout");
	GridBagLayout gbl = new GridBagLayout();
	GridBagConstraints gbc = new GridBagConstraints();
	Button[] btns = new Button[10];
	void addButton(Button btn){
		gbl.setConstraints(btn, gbc);
		f.add(btn);
	}
	void init(){
		f.setLayout(gbl);
		for(int i=0;i<btns.length;++i){
			btns[i] = new Button("��ť"+ i);
		}
		//��������ں���,����������
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx=1;
		gbc.ipadx = 30;//�����������ľ���
		gbc.ipady = 5;
	//	gbc.gridx = 2;//�������еĺ�������
		addButton(btns[0]);
	//	gbc.gridx = 0;
		addButton(btns[1]);
		addButton(btns[2]);
		addButton(btns[3]);
		//��constraint���Ƶ���������Ϊ���������һ�����
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 2;
		
		addButton(btns[4]);
		//��constraints���Ƶ���������Խ��������
		gbc.gridwidth = 2;
		addButton(btns[5]);
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		addButton(btns[6]);
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		//��GridBagConstraints���Ƶ�������������Ȩ����1
		gbc.weighty = 1;
		addButton(btns[7]);
		//��������İ�ť�����ϲ�������
		gbc.weighty = 0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		//�����Խ1������
		gbc.gridheight = 1;
		addButton(btns[8]);
		addButton(btns[9]);
		f.pack();
		f.setVisible(true);
	}
	
	
	public static void main(String[] args){
		new GridBagLayoutTest().init();
	}
}
