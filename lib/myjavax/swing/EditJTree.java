package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class EditJTree {
	JFrame jf = new JFrame("可编辑节点的树");
	JTree tree;
	DefaultTreeModel model;
	DefaultMutableTreeNode root =new DefaultMutableTreeNode("中国");
	DefaultMutableTreeNode gd =new DefaultMutableTreeNode("广东");
	DefaultMutableTreeNode gx =new DefaultMutableTreeNode("广西");
	DefaultMutableTreeNode fs =new DefaultMutableTreeNode("佛山");
	DefaultMutableTreeNode st =new DefaultMutableTreeNode("汕头");
	DefaultMutableTreeNode gl =new DefaultMutableTreeNode("桂林");
	DefaultMutableTreeNode nl =new DefaultMutableTreeNode("南宁");
	//定义需要被拖动的TreePath
	TreePath movePath;
	JButton  addSiblBn = new JButton("添加兄弟节点");
	JButton  addChildBn = new JButton("添加子节点");
	JButton  delBn = new JButton("删除节点");
	JButton  editBn = new JButton("编辑当前结点");
	
	public void init(){
		gd.add(fs);
		gd.add(st);
		gx.add(gl);
		gx.add(nl);
		root.add(gd);
		root.add(gx);
		tree = new JTree(root);
		//获取tree对应的model
		model = (DefaultTreeModel)tree.getModel();
		//设置tree可编辑
		tree.setEditable(true);
		MouseListener ml =new MouseAdapter(){
			//按下鼠标时获得被拖动的节点
			public void mousePressed(MouseEvent evt){
				//如果需要唯一确定某个节点,则必须通过TreePath来获取
				TreePath path = tree.getPathForLocation(evt.getX(), evt.getY());
				if(path!=null) movePath = path;
			}
			//松开鼠标时获得需要拖到那个父节点
			public void mouseReleased(MouseEvent evt){
				TreePath path =tree.getPathForLocation(evt.getX(), evt.getY());
				if(path!=null && movePath!=null){
					//阻止向子节点拖动
					if(movePath.isDescendant(path) && movePath!=path){
						JOptionPane.showMessageDialog(jf, "目标节点是被移动节点的子节点,无法移动!",
								"非法操作",JOptionPane.ERROR_MESSAGE);
						return ;
					}
					//不是向子节点移动,鼠标按下,松开的也不是同一个节点
					else if(movePath!=path){
						//add方法先将该节点从原父节点下删除,再添加到新父节点下 
						((DefaultMutableTreeNode)path.getLastPathComponent())
						.add((DefaultMutableTreeNode)movePath.getLastPathComponent());
						movePath=null;
						tree.updateUI();
					}
				}
			}
		};
		//为JTree添加鼠标监听器
		tree.addMouseListener(ml);
		JPanel panel = new JPanel();
		panel.add(addSiblBn);
		panel.add(addChildBn);
		panel.add(delBn);
		panel.add(editBn);
		panel.setComponentPopupMenu(new MyUIManager(jf,null).getJPopupMenu());
		
		jf.add(new JScrollPane(tree));
		jf.add(panel,BorderLayout.SOUTH);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		add_actions();
	}
	
	private void add_actions(){
		//实现添加兄弟节点的监听器
		addSiblBn.addActionListener(evt ->{
			//获取选择的节点
			DefaultMutableTreeNode selectedNode =
					(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			//如果没选择任何节点,则直接返回
			if(selectedNode==null) return ;
			//获取选中节点的父节点
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
			//如果父节点为空,则直接返回
			if(parent==null) return;
			//创建一个新节点
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("新节点");
			//获取选中节点的选中索引
			int selectedIndex = parent.getIndex(selectedNode);
			//在选中位置插入新节点
			model.insertNodeInto(newNode, parent, selectedIndex+1);
			//--------------下面代码实现显示新节点(自动展开父节点)----------
			//获取从根节点到新节点的所有节点
			TreeNode[] nodes = model.getPathToRoot(newNode);
			TreePath path = new TreePath(nodes);
			//显示指定的TreePath
			tree.scrollPathToVisible(path);
		});
		
		//实现添加子节点的监听器
		addChildBn.addActionListener(evt->{
			//获取选中的节点
			DefaultMutableTreeNode selectedNode =
					(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			//如果没选择任何节点,则直接返回
			if(selectedNode==null) return ;
			//创建一个新节点
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("新节点");
			//通过Model来添加新节点,无需条用JTree的updateUI()方法
			model.insertNodeInto(newNode,selectedNode,selectedNode.getChildCount());
			//通过节点添加新节点,则需要调用tree的updateUI方法
			selectedNode.add(newNode);
			//-------下面代码用于显示新添加的节点
			//获取从根节点到新节点的所有节点
//			TreeNode[] nodes = model.getPathToRoot(newNode);
//			TreePath path = new TreePath(nodes);
			//显示指定的TreePath
//			tree.scrollPathToVisible(path);
//			tree.updateUI();
		});
		//实现删除节点的监听器
		delBn.addActionListener(evt->{
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
					tree.getLastSelectedPathComponent();
			if(selectedNode != null && selectedNode.getParent()!=null){
				model.removeNodeFromParent(selectedNode);
			}
		});
		//实现编辑节点的监听器
		editBn.addActionListener(evt->{
			TreePath selectedPath = tree.getSelectionPath();
			if(selectedPath != null){
				//编辑选中的节点
				tree.startEditingAtPath(selectedPath);
			}
		});
	}
	
	public static void main(String[] args){
		new EditJTree().init();
	}
}
