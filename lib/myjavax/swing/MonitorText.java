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
	JFrame jf = new JFrame("监听Document变化");
	JTextArea target = new JTextArea(4,35);
	JTextArea msg = new JTextArea(10,35);
	Document doc = target.getDocument();
	//保存撤销操作的list对象
	LinkedList<UndoableEdit> undoList = new LinkedList<>();
	//最多允许撤销多少次
	final int MAX_UNDO=20;
	
	public void init(){
		msg.setEditable(false);
		//添加DocumentListener
		doc.addDocumentListener(new DocumentListener(){
			//当向document中插入文本时触发该方法
			@Override
			public void insertUpdate(DocumentEvent e) {
				int offset = e.getOffset();
				int len =e.getLength();
				//取得插入事件的位置
				msg.append("插入文本长度:\t" + len+"\n");
				msg.append("插入文本的起始位置:\t"+offset+"\n");
				try{
					msg.append("插入文本内容:\t" +doc.getText(offset, len)+"\n\n");
				}catch(BadLocationException ble){
					System.out.println(ble.getMessage());
					ble.printStackTrace();
				}
			}
			//当从document中删除文本时触发该方法
			@Override
			public void removeUpdate(DocumentEvent e) {
				int offset = e.getOffset();
				int len = e.getLength();
				msg.append("删除文本的长度:\t"+len+"\n");
				msg.append("删除文本的起始位置:\t" + offset+"\n\n");
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				System.out.println("changedUpdate() called");
			}
		});
		//添加可撤销操作的监听器
		doc.addUndoableEditListener( evt ->{
			//每次发生可撤销操作时都会触发该代码块
			UndoableEdit edit = evt.getEdit();
			if(edit.canUndo() &&  undoList.size()<MAX_UNDO){
				//将撤销操作装入list内
				undoList.add(edit);
			}
			//已经达到了最大撤销次数
			else if(edit.canUndo() && undoList.size()>=MAX_UNDO){
				//弹出第一个次撤销操作
				undoList.pop();
				undoList.add(edit);
			}
		});
		//为"Ctrl+Z"按键添加监听器
		target.addKeyListener(new KeyAdapter(){
			public void keyTyped(KeyEvent e){
				//如果按下的是Ctrl+Z
				if(e.getKeyChar()==26){
					if(undoList.size()>0){
						//移出最后一次可撤销操作,并取消该操作
						undoList.removeLast().undo();
					}
				}
			}
		});
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(new JScrollPane(target));
		JPanel p = new JPanel();
		JLabel label = new JLabel("文本域的修改信息");
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
