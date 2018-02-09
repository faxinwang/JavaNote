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
 * ActionEvent		ActionListener		actionPerformed:按钮，文本框，菜单项被单击时触发
 * AdjustmentEvent	AdjustmentListener	adjustmentValueChanged:滑块位置发生改变是触发
 * ContainerEvent	ContainerListener	componentAdded:向容器中添加组件时触发
 * 										componentRemoved:向容器中删除组件时触发
 * FocusEvent		FocusListener		focusGained:组件获得焦点
 * 										focusLost:组件失去焦点
 * ComponentEvent	ComponentListener	componentHidden:组件被掩藏时
 * 										componentMoved:组件位置发生改变
 * 										componentResized:组件大小发生改变
 * 										componentShown:组件被显示
 * KeyEvent			KeyListener			keyPressed:按下某个键盘时
 * 										keyReleased:松开某个键盘时
 * 										keyTyped:单击某个键盘时
 * MouseEvent		MouseListener		mouseClicked:在某个组件上单击鼠标时
 * 										mouseEntered:鼠标进入某个组件时
 * 										mouseExited:鼠标离开某个组件时
 * 										mousePressed:在某个组件上按下鼠标时
 * 										mouseReleased:在某个组件上松开鼠标时
 * 					MouseMotionListener	mouseDragged:拖动某个组件时
 * 										mouseMoved:鼠标在某个组件上移动时
 * TextEvent		TextListener		textValueChanged:文本组件里的文本发生改变时
 * ItemEvent		ItemListener		itemStateChanged:组件被选中或取消选中时
 * WindowEvent		WindowListener		windowActivated:窗口被激活时
 * 										windowDeactivated:窗口失去激活时
 * 										windowClosing:窗口右上角的"X"被单击时
 * 										windowClosed:窗口调用dispose()即将关闭时
 * 										windowIconified:窗口最小化时
 * 										windowDeiconified:窗口恢复时
 * 										windowopend:窗口首次被打开时
 * 
 */

/* AWT组件的继承关系
 * Object
 *  |------->>>Component(普通组件)
 *  |				|------->>>Button
 *  |				|------->>>TextField
 *  |				|------->>>List
 *  |				|------->>>Others
 *  |				|------->>>Container(容器类)
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
 *  |------->>>MenuComponent(菜单相关组件)
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
