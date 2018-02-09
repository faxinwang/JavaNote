package myjavax.swing;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class ExtendsDefaultTreeCellRenderer {
	JFrame jf = new JFrame("定制树的节点");
	JTree tree;
	DefaultMutableTreeNode root = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.ROOT,"数据库导航"));
	DefaultMutableTreeNode salaryDB =
			new DefaultMutableTreeNode(new NodeData(DBElementType.DATABASE,"公司工资数据库"));
	DefaultMutableTreeNode customDB =
			new DefaultMutableTreeNode(new NodeData(DBElementType.DATABASE,"公司客户数据库"));
	DefaultMutableTreeNode employeeTB =
			new DefaultMutableTreeNode(new NodeData(DBElementType.TABLE,"员工表"));
	DefaultMutableTreeNode attendTB = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.TABLE,"考勤表"));
	DefaultMutableTreeNode contentTB = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.TABLE,"联系方式"));
	DefaultMutableTreeNode id = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.INDEX,"员工ID"));
	DefaultMutableTreeNode name=
			new DefaultMutableTreeNode(new NodeData(DBElementType.COLUMN,"姓名"));
	DefaultMutableTreeNode gender =
			new DefaultMutableTreeNode(new NodeData(DBElementType.COLUMN,"性别"));
	public void init(){
		//通过add()方法建立树节点之间的父子关系
		root.add(salaryDB);
		root.add(customDB);
		salaryDB.add(employeeTB);
		salaryDB.add(attendTB);
		customDB.add(contentTB);
		employeeTB.add(id);
		employeeTB.add(name);
		employeeTB.add(gender);
		tree=new JTree(root);
		//以根节点创建树
		tree.setCellRenderer(new MyRenderer());
		//设置显示根节点的折叠标签
		tree.setShowsRootHandles(true);
		tree.setRootVisible(true);
		JPanel p = new JPanel();
		p.add(tree);
		p.setPreferredSize(new Dimension(500,300));
		p.setComponentPopupMenu(new MyUIManager(jf,null).getJPopupMenu());
		jf.add(p);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	public static void main(String[] args){
		new ExtendsDefaultTreeCellRenderer().init();
	}
}

//定义一个NodeData类,用于封装节点数据
class NodeData{
	public int nodeType;
	public String name;
	public NodeData(int type,String nm){
		nodeType=type;
		name=nm;
	}
	public String toString(){
		return name;
	}
}

//定义一个接口,该接口里包含数据库对象类型的常量
interface DBElementType{
	int ROOT = 0;
	int DATABASE = 1;
	int TABLE=2;
	int COLUMN = 3;
	int INDEX=4;
}

@SuppressWarnings("serial")
class MyRenderer extends DefaultTreeCellRenderer{
	String src="./src/icon/";
	//初始化5个图标
	ImageIcon root = new ImageIcon(src+"root.png");
	ImageIcon database = new ImageIcon(src+"database.png");
	ImageIcon table = new ImageIcon(src+"table.png");
	ImageIcon column = new ImageIcon(src+"male.png");
	ImageIcon index = new ImageIcon(src+"user_info.png");
	
	public Component getTreeCellRendererComponent(JTree tree,
			Object value,boolean sel,boolean expanded,
			boolean leaf,int row,boolean hasFocus)
	{
		//执行父类默认的节点绘制操作
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		NodeData data=(NodeData)node.getUserObject();
		//根据数据节点里的nodeType数据决定节点图标
		ImageIcon icon = null;
		switch(data.nodeType){
		case DBElementType.ROOT :
			icon = root;
			break;
		case DBElementType.DATABASE:
			icon = database;
			break;
		case DBElementType.TABLE:
			icon = table;
			break;
		case DBElementType.COLUMN:
			icon = column;
			break;
		case DBElementType.INDEX:
			icon = index;
			break;
		}
		//改变图标
		this.setIcon(icon);
		return this;
	}
}