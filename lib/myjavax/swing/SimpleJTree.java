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
	JFrame jf = new JFrame("����");
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
		//���δ������е����нڵ�
		root=new DefaultMutableTreeNode("�й�");
		guangdong = new DefaultMutableTreeNode("�㶫");
		guangxi = new DefaultMutableTreeNode("����");
		foshan = new DefaultMutableTreeNode("��ɽ");
		shantou= new DefaultMutableTreeNode("��ͷ");
		guiling= new DefaultMutableTreeNode("����");
		nanning= new DefaultMutableTreeNode("����");
		//ͨ��add�����������ڵ�֮��ĸ��ӹ�ϵ
		guangdong.add(foshan);
		guangdong.add(shantou);
		guangxi.add(guiling);
		guangxi.add(nanning);
		root.add(guangdong);
		root.add(guangxi);
		//�Ը��ڵ㴴����
		tree = new JTree(root);
		
		//����ֻ��ѡ��һ��TreePath
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		//��Ӽ������ڵ�ѡ���¼��ļ�����,
		//��JTree�б�ѡ��Ľڵ㷢���ı�ʱ,�������÷���
		tree.addTreeSelectionListener(evt ->{
			if(evt.getOldLeadSelectionPath() != null)
				jta.append("ԭѡ�еĽڵ�·��:" + evt.getOldLeadSelectionPath().toString() +"\n");
			if(evt.getNewLeadSelectionPath()!=null)
				jta.append("��ѡ�еĽڵ�·��:"+evt.getNewLeadSelectionPath().toString()+"\n" );
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
		//ǿ��JTree����ʾ�ڵ�֮�������
		tree.putClientProperty("JTree.lineStyle", "None");
		//����ʹ�����������ǿ�ƽڵ�֮��ֻ��ˮƽ�ָ���
//		tree.putClientProperty("JTree.lineStyle", "Horizontal");
		//�����Ƿ���ʾ���ڵ��"չ��","�۵�"ͼ��,Ĭ����false
		tree.setShowsRootHandles(true);
		//����һ�д���������ظ��ڵ�
		tree.setRootVisible(false);
		
		DefaultTreeCellRenderer cellRender = new DefaultTreeCellRenderer();
		String src = "./src/icon/";
		//����ѡ���ͷ�ѡ���ڵ�ı���ɫ
		cellRender.setBackgroundNonSelectionColor(new Color(220,160,160));
		cellRender.setBackgroundSelectionColor(new Color(160,220,160));
		//����ѡ�нڵ�ı߿���ɫ
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
