package myjava.awt;

import java.awt.CheckboxMenuItem;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuTest {
	Frame f = new Frame("AWT:MenuTest");
	Panel pl = new Panel();
	MenuBar mb = new MenuBar();
	Menu filem = new Menu("文件");
	Menu editm = new Menu("编辑");
	MenuItem newItem = new MenuItem("新建");
	MenuItem saveItem = new MenuItem("保存");
	//创建exitItem菜单项,指定使用“Ctrl + x”快捷键
	MenuItem exitItem = new MenuItem("退出",new MenuShortcut(KeyEvent.VK_X));
	CheckboxMenuItem autoWrap = new CheckboxMenuItem("自动换行");
	MenuItem copyItem = new MenuItem("复制");
	MenuItem pastItem = new MenuItem("粘贴");
	Menu format = new Menu("格式");
	//创建commentItem菜单项，指定使用"ctrl+shift+/"快捷键
	//第二个参数true表示需要shift键辅助
	MenuItem commentItem = new MenuItem("注释",new MenuShortcut(KeyEvent.VK_SLASH,true));
	MenuItem cancelItem = new MenuItem("取消注释");
	TextArea txts = new TextArea(6,40);
	
	public void init(){
		ActionListener menuListener = e ->{
			String cmd = e.getActionCommand();
			txts.append("单击 “" + cmd + "” 菜单\n" );
			if(cmd.equals("退出"))
				System.exit(0);
		};
		commentItem.addActionListener(menuListener);
		exitItem.addActionListener(menuListener);
		//为file菜单添加菜单项
		filem.add(newItem);
		filem.add(saveItem);
		filem.add(exitItem);
		//为edit菜单添加菜单项
		editm.add(autoWrap);
		//使用addSeparator方法来添加菜单分割线
		editm.addSeparator();
		editm.add(copyItem);
		editm.add(pastItem);
		//为format菜单添加菜单项
		format.add(commentItem);
		format.add(cancelItem);
		//使用添加new MenuItem("-")的方式添加菜单分隔线
		editm.add(new MenuItem("-"));
		//将format菜单添加到editm菜单中，从而形成二级菜单
		editm.add(format);
		//将filem,editm菜单添加到mb菜单条上
		mb.add(filem);
		mb.add(editm);
		//为f窗口设置菜单条
		f.setMenuBar(mb);
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//将txts放入Panel中,并为panel增加右键菜单
		addPopMenu();
		
		f.pack();
		f.setVisible(true);
	}
	void addPopMenu(){
		GridBagLayout bgl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		pl.setLayout(bgl);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		bgl.setConstraints(txts,gbc);
		pl.add(txts);
		f.add(pl);
		//创建右键菜单
		PopupMenu pop = new PopupMenu();
		pop.add(autoWrap);
		pop.addSeparator();
		pop.add(copyItem);
		pop.add(pastItem);
		pop.add(new MenuItem("-"));
		pop.add(format);//二级菜单
		pl.add(pop);
		//右击文本域时将弹出右键菜单
		txts.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				//如果释放的是鼠标右键
				if(e.isPopupTrigger()){
					pop.show(pl, e.getX(), e.getY());
				}
			}
		});
	}
	public static void main(String[] args){
		new MenuTest().init();
	}
}
