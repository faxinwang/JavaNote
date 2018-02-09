package myjava.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/* GridLayout�������ָ���ݺ��߷ָ������ÿ������ռ�������С��ͬ����FlowLayout��ͬ����
 * GridLayout��ÿ��������������С�����������������Զ�ռ��������������
 * GridLayout();
 * GridLayout(int rows,int cols);
 * GridLayout(int rows,int cols,int hgap,int vgap);
 */
public class GridLayoutTest {

	
	
	public static void main(String[] args){
		Frame f =new Frame("AWT:������");
		//����ı���
		Panel p1 = new Panel();
		TextField text = new TextField(30);
		p1.add(text);
		f.add(p1, BorderLayout.NORTH);
		//��Ӱ�ť
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(3,5,4,4));
		String[] name = {
				"0","1","2","3","4",
				"5","6","7","8","9",
				"+","-","*","/","="
		};
		//��Panel�����15����ť
		for(int i=0;i<name.length;++i){
			p2.add(new Button(name[i]));
		}
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		f.add(p2);
		f.pack();
		f.setVisible(true);
	}
}
