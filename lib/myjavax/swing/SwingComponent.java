package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.event.InputEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

public class SwingComponent {
	String src = "./src/icon/";
	JFrame jf = new JFrame("测试");
	//定义一个按钮,并为其指定图标
	Icon okIcon = new ImageIcon(src+"ok.png");
	JButton okBt = new JButton("确认",okIcon);
	//定义一个单选按钮,初始处于选中状态
	JRadioButton maleBt = new JRadioButton("男",true);
	JRadioButton femaleBt = new JRadioButton("女",false);
	ButtonGroup bg = new ButtonGroup();
	//定义一个复选框,初始处于没有选中状态
	JCheckBox married = new JCheckBox("是否已婚?",false);
	String[] colors = new String[]{"红色","绿色","蓝色"};
	//定义一个下拉选择框
	JComboBox<String> colorChooser = new JComboBox<>(colors);
	//定义一个选择列表框
	JList<String> colorList = new JList<>(colors);
	//定义一个8行,20列的多行文本框
	JTextArea jta = new JTextArea(8,20);
	//定义一个40列的单行文本域
	JTextField jtf = new JTextField(40);
	JMenuBar jmb = new JMenuBar();
	JMenu file_mu = new JMenu("文件");
	JMenu edit_mu  =new JMenu("编辑");
	//创建"新建"菜单项,并为之指定图标
	Icon newIcon = new ImageIcon(src+"new.png");
	JMenuItem new_item = new JMenuItem("新建",newIcon);
	//创建保存菜单项,并为之指定图标
	Icon saveIcon = new ImageIcon(src+"save.png");
	JMenuItem save_item = new JMenuItem("保存",saveIcon);
	//创建退出菜单项,并为之指定图标
	Icon exitIcon = new ImageIcon(src+"exit.png");
	JMenuItem exit_item = new JMenuItem("退出",exitIcon);
	
	JCheckBoxMenuItem autoWrap = new JCheckBoxMenuItem("自动换行");
	//创建复制菜单项,并位置指定图标
	JMenuItem copy_item = new JMenuItem("复制",new ImageIcon(src+"copy.png"));
	JMenuItem paste_item =new JMenuItem("粘贴",new ImageIcon(src+"paste.png"));
	
	JMenu format_mu = new JMenu("格式");
	JMenuItem comment_item = new JMenuItem("注释");
	JMenuItem uncomment_item = new JMenuItem("取消注释");
	
	//定义一个右键菜单用于设置程序风格
	JPopupMenu pop = null;
	
	/*********************执行界面初始化*********************************/
	public void init(){
		//创建一个装载了文本框,按钮的JPanel
		JPanel bottom = new JPanel();
		bottom.add(jtf);
		bottom.add(okBt);
		jf.add(bottom,BorderLayout.SOUTH);
		
		//创建一个装载了下拉列表框,三个JCheckBox的JPanel
		JPanel checkPanel = new JPanel();
		checkPanel.add(colorChooser);
		bg.add(maleBt);
		bg.add(femaleBt);
		checkPanel.add(maleBt);
		checkPanel.add(femaleBt);
		checkPanel.add(married);
		
		//创建一个垂直排列的Box组件,盛装多行文本域JPanel
		Box topLeft = Box.createVerticalBox();
		topLeft.add(new JScrollPane(jta));
		topLeft.add(checkPanel);
		//创建一个说排列组件的Box,盛装topLeft,colorList
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		//将top容器添加到窗口中央
		jf.add(top);
		//------------------下面开始组合菜单------------------------//
		//为new_item菜单项设置快捷键,设置快捷键时要使用大写字母
		new_item.setAccelerator(KeyStroke.getKeyStroke('N',InputEvent.CTRL_MASK));
		//为file_mu添加菜单项
		file_mu.add(new_item);
		file_mu.add(save_item);
		file_mu.add(exit_item);
		//为edit_mu添加菜单项
		edit_mu.add(autoWrap);
		edit_mu.addSeparator();//添加菜单分割线
		edit_mu.add(copy_item);
		edit_mu.add(paste_item);
		//为comment_item组件添加提示信息
		comment_item.setToolTipText("将程序代码注释起来");
		//为format_mu添加菜单项
		format_mu.add(comment_item);
		format_mu.add(uncomment_item);
		//使用添加new JMenuItem("-")的方式不能添加菜单分隔符
		edit_mu.add(new JMenuItem("-"));
		//将format_mu添加到edit_mu菜单中,从而形成二级菜单
		edit_mu.add(format_mu);
		//将file_mu,edit_mu添加到mbar菜单条上
		jmb.add(file_mu);
		jmb.add(edit_mu);
		//为jf窗口设置菜单条
		jf.setJMenuBar(jmb);
		
		MyUIManager manager = new MyUIManager(jf,jmb);
		//添加一个二级菜单用于修改程序界面风格
//		pop.add(manager.getJPopupMenu());
		pop = manager.getJPopupMenu();
		//调用该方法即可设置右键菜单,无需使用事件机制
		jta.setComponentPopupMenu(pop);
		//设置关闭窗口时退出程序
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setResizable(false);
		jf.setVisible(true);
		
		add_actions();
	}

	public void add_actions(){
		//为new_item添加事件监听器
		new_item.addActionListener(ac->{
			jta.append("用户单击了\"新建\"菜单项\n");
		});
	}

	static void getInstalledLookAndFeel(){
		System.out.println("当前系统可用的所有LAF:");
		for(UIManager.LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
			System.out.println(info.getName()+"----->" +info);
		}
	}
	
	public static void main(String[] args){
		//设置Swing窗口使用Java风格
	//	JFrame.setDefaultLookAndFeelDecorated(true);
		getInstalledLookAndFeel();
		new SwingComponent().init();
	}
}
