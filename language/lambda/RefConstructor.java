package lambda;

import javax.swing.JFrame;

@FunctionalInterface
interface RefConst{
	JFrame win(String title);
}

public class RefConstructor {
	
	public static void main(String[] args){
		//ʹ��lambda���ʽ����FefConstructer����
		RefConst rc = title -> new JFrame(title);
		JFrame jf = rc.win("my window!");
		jf.setVisible(true);
		
		//���������ô���lambda���ʽ
		//����ʽ�ӿ��е�ȫ�����������ù�������Ϊ����
		@SuppressWarnings("unused")
		RefConst rc2 = JFrame::new;
		JFrame jf2 = rc.win("�ҵĴ���!");
		jf2.setVisible(true);
	}
}
