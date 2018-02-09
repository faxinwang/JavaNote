package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/* Swing提供了JToolBar来创建工具条,
 * JToolBar(Sting name,int orientation):指定工具条的名称和方向
 * 创建工具条后,JToolBar提供了如下常用方法:
 * JButton add(Action a):通过Action对象为JToolBar添加对应的工具按钮
 * void setFloatable(boolean b):设置工具条是否可浮动
 * void addSeparator(Dimension size):添加分隔符
 * void setMargin(Insets m):设置工具按钮与工具条之间的边距
 * void setOrientation(int o):设置工具条方向
 * void setRollover(boolean rollover):设置工具条的rollover状态
 */

public class JToolBarTest {
	JFrame jf = new JFrame("测试工具条");
	JTextArea jta = new JTextArea(6,35);
	JToolBar jtb = new JToolBar();
	JMenuBar jmb = new JMenuBar();
	JMenu edit_mu = new JMenu("编辑");
	//获取系统剪贴板
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	//创建"粘贴"Action,该action用于创建菜单项,工具按钮和普通按钮
	@SuppressWarnings("serial")
	Action pasteAction = new AbstractAction("粘贴",new ImageIcon("./src/icon/paste.png")){
		@Override
		public void actionPerformed(ActionEvent evt) {
			//如果剪贴板中包含stringFlavor内容
			if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
				try{
					//取出剪贴板中的内容
					String content = (String)clipboard.getData(DataFlavor.stringFlavor);
					//将选中内容替换成剪贴板中的内容
					jta.replaceRange(content,jta.getSelectionStart(),jta.getSelectionEnd());
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	};
	
	//创建"复制"action
	@SuppressWarnings("serial")
	Action copyAction  = new AbstractAction("复制",new ImageIcon("./src/icon/copy.png")){
		@Override
		public void actionPerformed(ActionEvent e) {
			StringSelection contents = new StringSelection(jta.getSelectedText());
			//将StringSelection对象放入剪贴板中
			clipboard.setContents(contents, null);
			//如果剪贴板中包含stringFlavor内容,就将pasteAction激活
			if(clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)){
				pasteAction.setEnabled(true);
			}
		}
	};
	
	public void init(){
		//添加右键菜单该变程序外观
		jta.setComponentPopupMenu(new MyUIManager(jf,jmb).getJPopupMenu());
		//pasteAction默认处于不激活状态
		pasteAction.setEnabled(false);
		jf.add(new JScrollPane(jta));
		//以action创建按钮,并将按钮添加到panel中
		JButton copyBn = new JButton(copyAction);
		JButton pasteBn = new JButton(pasteAction);
		JPanel jp = new JPanel();
		jp.add(copyBn);
		jp.add(pasteBn);
		jf.add(jp,BorderLayout.SOUTH);
		//向工具条中添加Action对象,该对象会自动转换成工具按钮
		jtb.add(copyAction);
		jtb.addSeparator();
		jtb.add(pasteAction);
		//想菜单中添加Action对象,该对象会自动转换成菜单项
		edit_mu.add(copyAction);
		edit_mu.add(pasteAction);
		jmb.add(edit_mu);
		jf.setJMenuBar(jmb);
		//设置工具条和工具按钮之间的页边距
		jtb.setMargin(new Insets(2,3,2,2));
		//向窗口中添加工具条,工具条默认处于可浮动状态
		jf.add(jtb,BorderLayout.NORTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	public static void main(String[] args){
		new JToolBarTest().init();
	}
}
