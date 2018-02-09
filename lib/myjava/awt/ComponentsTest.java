package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.Box;

class ComponentsTest {
	Frame f = new Frame("AWT:ComponentsTest");
	Button ok = new Button("ȷ��");
	CheckboxGroup cbg = new CheckboxGroup();
	//����һ����ѡ��,����cbg���У���ʼ���ڱ�ѡ��״̬
	Checkbox male = new Checkbox("Ů",cbg,true);
	Checkbox female = new Checkbox("��",cbg,false);
	//��ѡ�򣬳�ʼδ��ѡ��
	Checkbox married = new Checkbox("�Ƿ��ѻ�?",false);
	//����ѡ���
	Choice colorChooser = new Choice();
	//�б�ѡ���
	List  colorList = new List(6,true);
	//5�У� 30�е��ı���
	TextArea txts = new TextArea(5,30);
	//50�еĵ����ı���
	TextField txt = new TextField(50);
	
	void init(){
		colorChooser.add("��ɫ");
		colorChooser.add("��ɫ");
		colorChooser.add("��ɫ");
		colorList.add("��ɫ");
		colorList.add("��ɫ");
		colorList.add("��ɫ");
		//����һ��װ�����ı��򣬰�ť��Panel
		Panel bottom = new Panel();
		bottom.add(txt);
		bottom.add(ok);
		f.add(bottom,BorderLayout.SOUTH);//��������
		//����һ��װ���������б������checkbox��panel
		Panel checkPanel = new Panel();
		checkPanel.add(colorChooser);
		checkPanel.add(male);
		checkPanel.add(female);
		checkPanel.add(married);
		//����һ����ֱ���������Box,ʢװ�����ı�
		Box topLeft = Box.createVerticalBox();
		topLeft.add(txts);
		topLeft.add(checkPanel);
		//����һ��ˮƽ���е�Box,ʢװtopLeft,colorList
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		f.add(top);
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new ComponentsTest().init();
	}
}
