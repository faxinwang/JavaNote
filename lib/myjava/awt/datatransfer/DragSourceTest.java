package myjava.awt.datatransfer;

import java.awt.Cursor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class DragSourceTest {
	JFrame jf = new JFrame("Swing���Ϸ�֧��");
	JLabel srcLabel = new JLabel("Swing���Ϸ�֧��.\n" +
			"�����ı��������������������!");
	void init(){
		DragSource dragSource = DragSource.getDefaultDragSource();
		//��srcLabelת�����Ϸ�Դ�����ܽ��ܸ��ƣ��ƶ����ֲ���
		dragSource.createDefaultDragGestureRecognizer(srcLabel, 
				DnDConstants.ACTION_COPY_OR_MOVE, event->{
			//��JLabel����ı���Ϣ��װ��Transferable����
			String txt = srcLabel.getText();
			Transferable transferable = new StringSelection(txt);
			//�����ϷŲ������ϷŹ�����ʹ���ֱ����
			event.startDrag(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), transferable);
		});
		jf.add(new JScrollPane(srcLabel));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args){
		new DragSourceTest().init();
	}
}

/*Swing���Ϸ�֧��.
�����ı��������������������!
 * 
 * Swing���Ϸ�֧��.Swing���Ϸ�֧��.
�����ı��������������������!
�����ı��������������������!
 * 
 */
