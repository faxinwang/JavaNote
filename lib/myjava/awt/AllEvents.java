package myjava.awt;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/* Event			Listener			Processor
 * ActionEvent		ActionListener		actionPerformed:��ť���ı��򣬲˵������ʱ����
 * AdjustmentEvent	AdjustmentListener	adjustmentValueChanged:����λ�÷����ı��Ǵ���
 * ContainerEvent	ContainerListener	componentAdded:��������������ʱ����
 * 										componentRemoved:��������ɾ�����ʱ����
 * FocusEvent		FocusListener		focusGained:�����ý���
 * 										focusLost:���ʧȥ����
 * ComponentEvent	ComponentListener	componentHidden:������ڲ�ʱ
 * 										componentMoved:���λ�÷����ı�
 * 										componentResized:�����С�����ı�
 * 										componentShown:�������ʾ
 * KeyEvent			KeyListener			keyPressed:����ĳ������ʱ
 * 										keyReleased:�ɿ�ĳ������ʱ
 * 										keyTyped:����ĳ������ʱ
 * MouseEvent		MouseListener		mouseClicked:��ĳ������ϵ������ʱ
 * 										mouseEntered:������ĳ�����ʱ
 * 										mouseExited:����뿪ĳ�����ʱ
 * 										mousePressed:��ĳ������ϰ������ʱ
 * 										mouseReleased:��ĳ��������ɿ����ʱ
 * 					MouseMotionListener	mouseDragged:�϶�ĳ�����ʱ
 * 										mouseMoved:�����ĳ��������ƶ�ʱ
 * TextEvent		TextListener		textValueChanged:�ı��������ı������ı�ʱ
 * ItemEvent		ItemListener		itemStateChanged:�����ѡ�л�ȡ��ѡ��ʱ
 * WindowEvent		WindowListener		windowActivated:���ڱ�����ʱ
 * 										windowDeactivated:����ʧȥ����ʱ
 * 										windowClosing:�������Ͻǵ�"X"������ʱ
 * 										windowClosed:���ڵ���dispose()�����ر�ʱ
 * 										windowIconified:������С��ʱ
 * 										windowDeiconified:���ڻָ�ʱ
 * 										windowopend:�����״α���ʱ
 * 
 */

/* AWT����ļ̳й�ϵ
 * Object
 *  |------->>>Component(��ͨ���)
 *  |				|------->>>Button
 *  |				|------->>>TextField
 *  |				|------->>>List
 *  |				|------->>>Others
 *  |				|------->>>Container(������)
 *  |							|------->>>Window
 *  |							|			|------->>>Frame
 *  |							|			|------->>>Dialog
 *  |							|			
 *  |							|------->>>Panel
 *  |							|			|------->>>Applet
 *  |							|
 *  |							|------->>>ScrollPanel
 *  |					
 *  |
 *  |------->>>MenuComponent(�˵�������)
 *  |				|------->>>MenuBar
 *  |				|------->>>MenuItem
 *  								|------->>>Menu
 */
public class AllEvents {
	int count=0;
	Frame f = new Frame("AWT:windowEvents");
	TextArea txts = new TextArea(10,40);
	void init(){
		f.addWindowListener(new MyWindowListener());
		f.addFocusListener(new MyFocusListener());
		f.addComponentListener(new MyComponentListener());
		f.addKeyListener(new MyKeyListener());
		f.addMouseListener(new MyMouseListener());
		f.add(txts);
		f.setFocusable(false);
		f.pack();
		f.setVisible(true);
	}
	//implements a WindowListener
	class MyWindowListener implements WindowListener{

		@Override
		public void windowOpened(WindowEvent e) {
			txts.append(++count + ": window opened for the first time!\n");
		}

		@Override
		public void windowClosing(WindowEvent e) {
			txts.append(++count + ": user closing window!\n");
			((Window)e.getSource()).dispose();
		}

		@Override
		public void windowClosed(WindowEvent e) {
			txts.append(++count + ": window closed successfully!\n");
			System.exit(0);
		}

		@Override
		public void windowIconified(WindowEvent e) {
			txts.append(++count + ": window iconified!\n");
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			txts.append(++count + ": window deiconified!\n");
		}

		@Override
		public void windowActivated(WindowEvent e) {
			txts.append(++count + ": window activated!\n");
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			txts.append(++count + ": window deactivated!\n");
		}
		
	}
	//implements a FocusListener
	class MyFocusListener implements FocusListener{

		@Override
		public void focusGained(FocusEvent e) {
			txts.append(++count + ": focusGeined!\n");
		}

		@Override
		public void focusLost(FocusEvent e) {
			txts.append(++count + ": focusLost!\n");
		}
		
	}
	//implements a ComponentListener
	class MyComponentListener implements ComponentListener{

		@Override
		public void componentResized(ComponentEvent e) {
			txts.append(++count + ": componentResized!\n");
		}

		@Override
		public void componentMoved(ComponentEvent e) {
			txts.append(++count + ": componentMoved!\n");
		}

		@Override
		public void componentShown(ComponentEvent e) {
			txts.append(++count + ": componentShown!\n");
		}

		@Override
		public void componentHidden(ComponentEvent e) {
			txts.append(++count + ": componentHidden!\n");
		}
		
	}
	//implements a KeyListener
	class MyKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			txts.append(++count + ": keyTyped!\n");
		}

		@Override
		public void keyPressed(KeyEvent e) {
			txts.append(++count + ": keyPressed!\n");
		}

		@Override
		public void keyReleased(KeyEvent e) {
			txts.append(++count + ": keyReleased!\n");
		}
		
	}
	//implements a MouseListener
	class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			txts.append(++count + ": mouseClicked!\n");
		}

		@Override
		public void mousePressed(MouseEvent e) {
			txts.append(++count + ": mousePressed!\n");
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			txts.append(++count + ": mouseReleased!\n");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			txts.append(++count + ": mouseEntered!\n");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			txts.append(++count + ": mouseExited!\n");
		}
		
	}
	
	
	public static void main(String[] args){
		new AllEvents().init();
	}
}
