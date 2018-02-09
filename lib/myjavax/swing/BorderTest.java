package myjavax.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

/* Ϊ������ñ߿�ɰ�������������:
 * ʹ��BorderFactory����XxxBorder����XxxBorderʵ��
 * ����Swing�����setBorder(Border b)����Ϊ��������ñ߿�
 */

public class BorderTest {
	private JFrame jf = new JFrame("���Ա߿�");
	
	public JPanel getPanelWithBorder(Border b,String BorderName){
		JPanel p = new JPanel();
		p.add(new JLabel(BorderName));
		p.setBorder(b);
		return p;
	}
	public void init(){
		jf.setLayout(new GridLayout(2,4));
		//����BevelBorder
		Border bb = BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.red, 
				Color.green,Color.BLUE,Color.GRAY);
		jf.add(getPanelWithBorder(bb,"BevelBorder"));
		//����LineBorder
		Border lb = BorderFactory.createLineBorder(Color.ORANGE, 10);
		jf.add(getPanelWithBorder(lb,"LineBorder"));
		//����EmptyBorder,EmptyBorder�����������Χ����
		Border eb = BorderFactory.createEmptyBorder(20, 5, 10, 30);
		jf.add(getPanelWithBorder(eb,"EmptyBorder"));
		//����EtchedBorder
		Border etb =BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.RED, Color.GREEN);
		jf.add(getPanelWithBorder(etb,"EtchedBorder"));
		//ֱ�Ӵ���TitledBorder,TitledBorder����Ϊԭ�б߿����ӱ���
		TitledBorder tb=new TitledBorder(lb,"���Ա���",TitledBorder.LEFT,TitledBorder.BOTTOM,
				new Font("StSong",Font.BOLD,18),Color.BLUE);
		jf.add(getPanelWithBorder(tb,"TitledBorder_lb"));
		//ֱ�Ӵ���MatteBorder,MatteBorder��EmptyBorder������
		//������ָ�������������ɫ�򱳾�,�˴�ָ��������ɫ
		MatteBorder mb = new MatteBorder(20,5,10,30,Color.GREEN);
		jf.add(getPanelWithBorder(mb,"MatteBorder"));
		//ֱ�Ӵ���CompoundBorder,CompoundBorder�������߿���ϳ��±߿�
		CompoundBorder cb1 =new CompoundBorder(new LineBorder(Color.RED,8),bb);
		jf.add(getPanelWithBorder(cb1,"CompoundBorder_lb_bb"));
		CompoundBorder cb2 =new CompoundBorder(tb,bb);
		jf.add(getPanelWithBorder(cb2,"CompoundBorder_tb_bb"));
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args){
		new BorderTest().init();
	}
}
