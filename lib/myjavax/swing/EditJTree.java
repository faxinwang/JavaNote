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
	JFrame jf = new JFrame("�ɱ༭�ڵ����");
	JTree tree;
	DefaultTreeModel model;
	DefaultMutableTreeNode root =new DefaultMutableTreeNode("�й�");
	DefaultMutableTreeNode gd =new DefaultMutableTreeNode("�㶫");
	DefaultMutableTreeNode gx =new DefaultMutableTreeNode("����");
	DefaultMutableTreeNode fs =new DefaultMutableTreeNode("��ɽ");
	DefaultMutableTreeNode st =new DefaultMutableTreeNode("��ͷ");
	DefaultMutableTreeNode gl =new DefaultMutableTreeNode("����");
	DefaultMutableTreeNode nl =new DefaultMutableTreeNode("����");
	//������Ҫ���϶���TreePath
	TreePath movePath;
	JButton  addSiblBn = new JButton("����ֵܽڵ�");
	JButton  addChildBn = new JButton("����ӽڵ�");
	JButton  delBn = new JButton("ɾ���ڵ�");
	JButton  editBn = new JButton("�༭��ǰ���");
	
	public void init(){
		gd.add(fs);
		gd.add(st);
		gx.add(gl);
		gx.add(nl);
		root.add(gd);
		root.add(gx);
		tree = new JTree(root);
		//��ȡtree��Ӧ��model
		model = (DefaultTreeModel)tree.getModel();
		//����tree�ɱ༭
		tree.setEditable(true);
		MouseListener ml =new MouseAdapter(){
			//�������ʱ��ñ��϶��Ľڵ�
			public void mousePressed(MouseEvent evt){
				//�����ҪΨһȷ��ĳ���ڵ�,�����ͨ��TreePath����ȡ
				TreePath path = tree.getPathForLocation(evt.getX(), evt.getY());
				if(path!=null) movePath = path;
			}
			//�ɿ����ʱ�����Ҫ�ϵ��Ǹ����ڵ�
			public void mouseReleased(MouseEvent evt){
				TreePath path =tree.getPathForLocation(evt.getX(), evt.getY());
				if(path!=null && movePath!=null){
					//��ֹ���ӽڵ��϶�
					if(movePath.isDescendant(path) && movePath!=path){
						JOptionPane.showMessageDialog(jf, "Ŀ��ڵ��Ǳ��ƶ��ڵ���ӽڵ�,�޷��ƶ�!",
								"�Ƿ�����",JOptionPane.ERROR_MESSAGE);
						return ;
					}
					//�������ӽڵ��ƶ�,��갴��,�ɿ���Ҳ����ͬһ���ڵ�
					else if(movePath!=path){
						//add�����Ƚ��ýڵ��ԭ���ڵ���ɾ��,����ӵ��¸��ڵ��� 
						((DefaultMutableTreeNode)path.getLastPathComponent())
						.add((DefaultMutableTreeNode)movePath.getLastPathComponent());
						movePath=null;
						tree.updateUI();
					}
				}
			}
		};
		//ΪJTree�����������
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
		//ʵ������ֵܽڵ�ļ�����
		addSiblBn.addActionListener(evt ->{
			//��ȡѡ��Ľڵ�
			DefaultMutableTreeNode selectedNode =
					(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			//���ûѡ���κνڵ�,��ֱ�ӷ���
			if(selectedNode==null) return ;
			//��ȡѡ�нڵ�ĸ��ڵ�
			DefaultMutableTreeNode parent = (DefaultMutableTreeNode)selectedNode.getParent();
			//������ڵ�Ϊ��,��ֱ�ӷ���
			if(parent==null) return;
			//����һ���½ڵ�
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("�½ڵ�");
			//��ȡѡ�нڵ��ѡ������
			int selectedIndex = parent.getIndex(selectedNode);
			//��ѡ��λ�ò����½ڵ�
			model.insertNodeInto(newNode, parent, selectedIndex+1);
			//--------------�������ʵ����ʾ�½ڵ�(�Զ�չ�����ڵ�)----------
			//��ȡ�Ӹ��ڵ㵽�½ڵ�����нڵ�
			TreeNode[] nodes = model.getPathToRoot(newNode);
			TreePath path = new TreePath(nodes);
			//��ʾָ����TreePath
			tree.scrollPathToVisible(path);
		});
		
		//ʵ������ӽڵ�ļ�����
		addChildBn.addActionListener(evt->{
			//��ȡѡ�еĽڵ�
			DefaultMutableTreeNode selectedNode =
					(DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			//���ûѡ���κνڵ�,��ֱ�ӷ���
			if(selectedNode==null) return ;
			//����һ���½ڵ�
			DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("�½ڵ�");
			//ͨ��Model������½ڵ�,��������JTree��updateUI()����
			model.insertNodeInto(newNode,selectedNode,selectedNode.getChildCount());
			//ͨ���ڵ�����½ڵ�,����Ҫ����tree��updateUI����
			selectedNode.add(newNode);
			//-------�������������ʾ����ӵĽڵ�
			//��ȡ�Ӹ��ڵ㵽�½ڵ�����нڵ�
//			TreeNode[] nodes = model.getPathToRoot(newNode);
//			TreePath path = new TreePath(nodes);
			//��ʾָ����TreePath
//			tree.scrollPathToVisible(path);
//			tree.updateUI();
		});
		//ʵ��ɾ���ڵ�ļ�����
		delBn.addActionListener(evt->{
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)
					tree.getLastSelectedPathComponent();
			if(selectedNode != null && selectedNode.getParent()!=null){
				model.removeNodeFromParent(selectedNode);
			}
		});
		//ʵ�ֱ༭�ڵ�ļ�����
		editBn.addActionListener(evt->{
			TreePath selectedPath = tree.getSelectionPath();
			if(selectedPath != null){
				//�༭ѡ�еĽڵ�
				tree.startEditingAtPath(selectedPath);
			}
		});
	}
	
	public static void main(String[] args){
		new EditJTree().init();
	}
}
