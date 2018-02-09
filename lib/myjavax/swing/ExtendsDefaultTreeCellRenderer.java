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
	JFrame jf = new JFrame("�������Ľڵ�");
	JTree tree;
	DefaultMutableTreeNode root = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.ROOT,"���ݿ⵼��"));
	DefaultMutableTreeNode salaryDB =
			new DefaultMutableTreeNode(new NodeData(DBElementType.DATABASE,"��˾�������ݿ�"));
	DefaultMutableTreeNode customDB =
			new DefaultMutableTreeNode(new NodeData(DBElementType.DATABASE,"��˾�ͻ����ݿ�"));
	DefaultMutableTreeNode employeeTB =
			new DefaultMutableTreeNode(new NodeData(DBElementType.TABLE,"Ա����"));
	DefaultMutableTreeNode attendTB = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.TABLE,"���ڱ�"));
	DefaultMutableTreeNode contentTB = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.TABLE,"��ϵ��ʽ"));
	DefaultMutableTreeNode id = 
			new DefaultMutableTreeNode(new NodeData(DBElementType.INDEX,"Ա��ID"));
	DefaultMutableTreeNode name=
			new DefaultMutableTreeNode(new NodeData(DBElementType.COLUMN,"����"));
	DefaultMutableTreeNode gender =
			new DefaultMutableTreeNode(new NodeData(DBElementType.COLUMN,"�Ա�"));
	public void init(){
		//ͨ��add()�����������ڵ�֮��ĸ��ӹ�ϵ
		root.add(salaryDB);
		root.add(customDB);
		salaryDB.add(employeeTB);
		salaryDB.add(attendTB);
		customDB.add(contentTB);
		employeeTB.add(id);
		employeeTB.add(name);
		employeeTB.add(gender);
		tree=new JTree(root);
		//�Ը��ڵ㴴����
		tree.setCellRenderer(new MyRenderer());
		//������ʾ���ڵ���۵���ǩ
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

//����һ��NodeData��,���ڷ�װ�ڵ�����
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

//����һ���ӿ�,�ýӿ���������ݿ�������͵ĳ���
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
	//��ʼ��5��ͼ��
	ImageIcon root = new ImageIcon(src+"root.png");
	ImageIcon database = new ImageIcon(src+"database.png");
	ImageIcon table = new ImageIcon(src+"table.png");
	ImageIcon column = new ImageIcon(src+"male.png");
	ImageIcon index = new ImageIcon(src+"user_info.png");
	
	public Component getTreeCellRendererComponent(JTree tree,
			Object value,boolean sel,boolean expanded,
			boolean leaf,int row,boolean hasFocus)
	{
		//ִ�и���Ĭ�ϵĽڵ���Ʋ���
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		NodeData data=(NodeData)node.getUserObject();
		//�������ݽڵ����nodeType���ݾ����ڵ�ͼ��
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
		//�ı�ͼ��
		this.setIcon(icon);
		return this;
	}
}