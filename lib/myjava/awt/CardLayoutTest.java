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
		"第一张","第二张","第三张","第四张","第五张"
	};
	Panel p1 =new Panel();
	
	void init(){
		final CardLayout card = new CardLayout(5,5);//hgap,vgap
		p1.setLayout(card);
		for(int i=0;i<names.length;++i){
			p1.add(names[i], new Button(names[i]));
		}
		//用lambda表达式写的局部内部类
		ActionListener listener = e->{
			switch(e.getActionCommand()){
			case "上一张":
				card.previous(p1); 	break;
			case "下一张":
				card.next(p1); 		break;
			case "第一张":
				card.first(p1);		break;
			case "最后一张":
				card.last(p1);		break;
			case "第三张":
				card.show(p1, "第三张");	break;
			}
		};
		Button prev = new Button("上一张");
		prev.addActionListener(listener);
		Button next = new Button("下一张");
		next.addActionListener(listener);
		Button first = new Button("第一张");
		first.addActionListener(listener);
		Button last = new Button("最后一张");
		last.addActionListener(listener);
		Button thrid = new Button("第三张");
		thrid.addActionListener(listener);
		Panel p =new Panel();//默认使用FlowLayout布局管理器,默认从中间向两边添加
		p.add(prev);
		p.add(next);
		p.add(first);
		p.add(last);
		p.add(thrid);
		f.add(p1);//默认放中间
		f.add(p,BorderLayout.SOUTH);//frame放在下面
		f.pack();
		f.setVisible(true);
	}
	
	public static void main(String[] args){
		new CardLayoutTest().init();
	}
}
