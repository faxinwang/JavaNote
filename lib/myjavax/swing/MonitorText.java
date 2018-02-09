package myjavax.swing;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.undo.UndoableEdit;

public class MonitorText {
	JFrame jf = new JFrame("����Document�仯");
	JTextArea target = new JTextArea(4,35);
	JTextArea msg = new JTextArea(10,35);
	Document doc = target.getDocument();
	//���泷��������list����
	LinkedList<UndoableEdit> undoList = new LinkedList<>();
	//������������ٴ�
	final int MAX_UNDO=20;
	
	public void init(){
		msg.setEditable(false);
		//���DocumentListener
		doc.addDocumentListener(new DocumentListener(){
			//����document�в����ı�ʱ�����÷���
			@Override
			public void insertUpdate(DocumentEvent e) {
				int offset = e.getOffset();
				int len =e.getLength();
				//ȡ�ò����¼���λ��
				msg.append("�����ı�����:\t" + len+"\n");
				msg.append("�����ı�����ʼλ��:\t"+offset+"\n");
				try{
					msg.append("�����ı�����:\t" +doc.getText(offset, len)+"\n\n");
				}catch(BadLocationException ble){
					System.out.println(ble.getMessage());
					ble.printStackTrace();
				}
			}
			//����document��ɾ���ı�ʱ�����÷���
			@Override
			public void removeUpdate(DocumentEvent e) {
				int offset = e.getOffset();
				int len = e.getLength();
				msg.append("ɾ���ı��ĳ���:\t"+len+"\n");
				msg.append("ɾ���ı�����ʼλ��:\t" + offset+"\n\n");
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("changedUpdate() called");
			}
		});
		//��ӿɳ��������ļ�����
		doc.addUndoableEditListener( evt ->{
			//ÿ�η����ɳ�������ʱ���ᴥ���ô����
			UndoableEdit edit = evt.getEdit();
			if(edit.canUndo() &&  undoList.size()<MAX_UNDO){
				//����������װ��list��
				undoList.add(edit);
			}
			//�Ѿ��ﵽ�����������
			else if(edit.canUndo() && undoList.size()>=MAX_UNDO){
				//������һ���γ�������
				undoList.pop();
				undoList.add(edit);
			}
		});
		//Ϊ"Ctrl+Z"������Ӽ�����
		target.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				//������µ���Ctrl+Z
				if(e.getKeyChar()==26){
					if(undoList.size()>0){
						//�Ƴ����һ�οɳ�������,��ȡ���ò���
						undoList.removeLast().undo();
					}
				}
			}
		});
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(new JScrollPane(target));
		JPanel p = new JPanel();
		JLabel label = new JLabel("�ı�����޸���Ϣ");
		p.add(label);
		box.add(p);
		box.add(new JScrollPane(msg));
		jf.add(box);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}

	public static void main(String[]args){
		new MonitorText().init();
	}
}
