package myjavax.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

/* JTree----TreeModel
 * 				|--->>>DefaultTreeModel------TreeNode
 * 												|-----MutableTreeNode
 * 															|-------->>>DefaultMutableTreeNode
 */

public class SimpleJTree {
	JFrame jf = new JFrame("简单树");
	JTree tree=null;
	JTextArea jta= new JTextArea(5,20);
	
	DefaultMutableTreeNode root ;
	DefaultMutableTreeNode guangdong;
	DefaultMutableTreeNode guangxi;
	DefaultMutableTreeNode foshan;
	DefaultMutableTreeNode shantou;
	DefaultMutableTreeNode guiling;
	DefaultMutableTreeNode nanning;
	public void init(){
		//依次创建树中的所有节点
		root=new DefaultMutableTreeNode("中国");
		guangdong = new DefaultMutableTreeNode("广东");
		guangxi = new DefaultMutableTreeNode("广西");
		foshan = new DefaultMutableTreeNode("佛山");
		shantou= new DefaultMutableTreeNode("汕头");
		guiling= new DefaultMutableTreeNode("桂林");
		nanning= new DefaultMutableTreeNode("南宁");
		//通过add方法建立树节点之间的父子关系
		guangdong.add(foshan);
		guangdong.add(shantou);
		guangxi.add(guiling);
		guangxi.add(nanning);
		root.add(guangdong);
		root.add(guangxi);
		//以根节点创建树
		tree = new JTree(root);
		
		//设置只能选择一个TreePath
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		//添加监听树节点选择事件的监听器,
		//当JTree中被选择的节点发生改变时,将触发该方法
		tree.addTreeSelectionListener(evt ->{
			if(evt.getOldLeadSelectionPath() != null)
				jta.append("原选中的节点路径:" + evt.getOldLeadSelectionPath().toString() +"\n");
			if(evt.getNewLeadSelectionPath()!=null)
				jta.append("新选中的节点路径:"+evt.getNewLeadSelectionPath().toString()+"\n" );
		});
		
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(new JScrollPane(tree));
		box.add(Box.createHorizontalStrut(10));
		box.add(new JScrollPane(jta));
		box.setComponentPopupMenu(new MyUIManager(jf,null).getJPopupMenu());

		jf.add(box);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		setProperties();
		printProperties();
	}
	private void setProperties(){
		//强制JTree不显示节点之间的连线
		tree.putClientProperty("JTree.lineStyle", "None");
		//或者使用下面代码来强制节点之间只有水平分割线
//		tree.putClientProperty("JTree.lineStyle", "Horizontal");
		//设置是否显示根节点的"展开","折叠"图标,默认是false
		tree.setShowsRootHandles(true);
		//下面一行代码可以隐藏根节点
		tree.setRootVisible(false);
		
		DefaultTreeCellRenderer cellRender = new DefaultTreeCellRenderer();
		String src = "./src/icon/";
		//设置选定和非选定节点的背景色
		cellRender.setBackgroundNonSelectionColor(new Color(220,160,160));
		cellRender.setBackgroundSelectionColor(new Color(160,220,160));
		//设置选中节点的边框颜色
		cellRender.setBorderSelectionColor(Color.BLUE);
		cellRender.setClosedIcon(new ImageIcon(src+"file.png"));
		cellRender.setFont(new Font("SansSerif",Font.BOLD,16));
		cellRender.setLeafIcon(new ImageIcon(src+"img.png"));
		cellRender.setOpenIcon(new ImageIcon(src+"open.png"));
		cellRender.setTextNonSelectionColor(Color.blue);
		cellRender.setTextSelectionColor(Color.cyan);
		tree.setCellRenderer(cellRender);
	}
	private void printProperties(){
		System.out.println("getNextSibling():"+guangdong.getNextSibling());;
		System.out.println("getChildCount():"+guangxi.getChildCount());
		System.out.println("getRoot():"+guangxi.getRoot());
		System.out.println(foshan.getSharedAncestor(guiling));
	}
	
	public static void main(String[] args){
		new SimpleJTree().init();
	}
}
