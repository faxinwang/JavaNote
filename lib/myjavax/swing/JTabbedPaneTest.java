package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

public class JTabbedPaneTest {
	String src = "./src/img/";
	JFrame jf =new JFrame("测试Tab页面");
	//创建一个Tab页面的标签放在左边,采用换行布局策略
	JTabbedPane tabPane = new JTabbedPane(JTabbedPane.LEFT,JTabbedPane.WRAP_TAB_LAYOUT);
	ImageIcon icon = new ImageIcon("./src/icon/open.png");
	String[] layouts = {"换行布局","滚动条布局"};
	String[] positions={"左边","顶部","右边","底部"};
	Map<String,String> books = new LinkedHashMap<>();
	
	public void init(){
		books.put("疯狂Java讲义","wfx.jpg");
		books.put("轻量级Java EE企业应用实战", "ljq.jpg");
		books.put("疯狂Ajax讲义", "sz.jpg");
		books.put("疯狂Android讲义","ljf.jpg");
		books.put("经典Java EE企业应用实战","qzj.jpg");
		String tip = "查看封面照片";
		//向JTabbedPane中添加5个标签页,指定了标题,图标和提示,
		//但该标签页的组件为null
		for(String bookName : books.keySet()){
			tabPane.addTab(bookName, icon,null,tip);
		}
		jf.add(tabPane,BorderLayout.CENTER);
		//为JTabbedPane添加事件监听器
		tabPane.addChangeListener(event ->{
			//如果被选择的组件依然是空
			if(tabPane.getSelectedComponent()==null){
				//获取所选择的标签页
				int page = tabPane.getSelectedIndex();
				loadTab(page);
			}
		});
		//系统默认选择第一页,加载第一页的内容
		loadTab(0);
		tabPane.setPreferredSize(new Dimension(500,300));
		//增加控制标签页布局,标签位置的单选按钮
		JPanel buttonPanel = new JPanel();
		ChangeAction action= new ChangeAction();
		buttonPanel.add(new ButtonPanel(action,"选择布局方式",layouts));
		buttonPanel.add(new ButtonPanel(action,"选择标签位置",positions));
		jf.add(buttonPanel, BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	//为指定标签页加载内容
	private void loadTab(int n){
		String title = tabPane.getTitleAt(n);
		//根据标签页的标题获取对应图书封面
		ImageIcon bookImage = new ImageIcon(src+books.get(title));
		System.out.println(src+books.get(title));
		tabPane.setComponentAt(n, new JLabel(bookImage));
		//改变标签页的图标
		tabPane.setIconAt(n, new ImageIcon("./src/icon/edit.png"));
	}
	
	//定义改变标签页布局策略,放置位置的监听器
	class ChangeAction implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			JRadioButton source = (JRadioButton)evt.getSource();
			String selection = source.getActionCommand();
			//设置标签页的布局策略
			if(selection.equals(layouts[0])){
				tabPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			}else if(selection.equals(layouts[1])){
				tabPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			}
			//设置标签页放置位置
			else if(selection.equals(positions[0])){
				tabPane.setTabPlacement(JTabbedPane.LEFT);
			}else if(selection.equals(positions[1])){
				tabPane.setTabPlacement(JTabbedPane.TOP);
			}else if(selection.equals(positions[2])){
				tabPane.setTabPlacement(JTabbedPane.RIGHT);
			}else if(selection.equals(positions[3])){
				tabPane.setTabPlacement(JTabbedPane.BOTTOM);
			}
		}
	}
	
	@SuppressWarnings("serial")
	//定义一个JPanel类,该类的对象包含多个纵向排列的JRadioButton控件
	//且JPanel扩展类可以指定一个字符串作为TitledBorder
	class ButtonPanel extends JPanel{
		private ButtonGroup group;
		public ButtonPanel(JTabbedPaneTest.ChangeAction action,String title,String[] labels){
			setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),title));
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
			group = new ButtonGroup();
			for(int i=0;labels!=null && i<labels.length;++i){
				JRadioButton b =new JRadioButton(labels[i]);
				b.setActionCommand(labels[i]);
				add(b);
				//添加事件监听器
				b.addActionListener(action);
				group.add(b);
				b.setSelected(i==0);
			}
		}
	}
	public static void main(String[] args){
		new JTabbedPaneTest().init();
	}
}
