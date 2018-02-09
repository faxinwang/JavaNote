package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionListener;

public class CardLayoutTest {
	Frame  f =new Frame("AWT:CardLayout");
	String[] names= {
		"��һ��","�ڶ���","������","������","������"
	};
	Panel p1 =new Panel();
	
	void init(){
		final CardLayout card = new CardLayout(5,5);//hgap,vgap
		p1.setLayout(card);
		for(int i=0;i<names.length;++i){
			p1.add(names[i], new Button(names[i]));
		}
		//��lambda���ʽд�ľֲ��ڲ���
		ActionListener listener = e->{
			switch(e.getActionCommand()){
			case "��һ��":
				card.previous(p1); 	break;
			case "��һ��":
				card.next(p1); 		break;
			case "��һ��":
				card.first(p1);		break;
			case "���һ��":
				card.last(p1);		break;
			case "������":
				card.show(p1, "������");	break;
			}
		};
		Button prev = new Button("��һ��");
		prev.addActionListener(listener);
		Button next = new Button("��һ��");
		next.addActionListener(listener);
		Button first = new Button("��һ��");
		first.addActionListener(listener);
		Button last = new Button("���һ��");
		last.addActionListener(listener);
		Button thrid = new Button("������");
		thrid.addActionListener(listener);
		Panel p =new Panel();//Ĭ��ʹ��FlowLayout���ֹ�����,Ĭ�ϴ��м����������
		p.add(prev);
		p.add(next);
		p.add(first);
		p.add(last);
		p.add(thrid);
		f.add(p1);//Ĭ�Ϸ��м�
		f.add(p,BorderLayout.SOUTH);//frame��������
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new CardLayoutTest().init();
	}
}
