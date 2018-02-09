package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellRenderer;



public class CustomTreeNode {
	JFrame jf = new JFrame("�������Ľڵ�");
	JTree tree;
	Color c1=new Color(123,200,150,88);
	//���弸����ʼ�ڵ�
	private DefaultMutableTreeNode groups = 
		new DefaultMutableTreeNode(new TreeNode(NodeType.GROUP,"����",null));
	DefaultMutableTreeNode g1 =
		new DefaultMutableTreeNode(new TreeNode(NodeType.GROUP,"����ͬѧ",null));
	DefaultMutableTreeNode g2 =
		new DefaultMutableTreeNode(new TreeNode(NodeType.GROUP,"��ѧͬѧ",null));
	DefaultMutableTreeNode g3 =
		new DefaultMutableTreeNode(new TreeNode(NodeType.GROUP,"����",null));
	DefaultMutableTreeNode wfx=
		new DefaultMutableTreeNode(new TreeNode(NodeType.PERSON,"wfx","ӵ����,��ӵ������������"));
	DefaultMutableTreeNode ld =
		new DefaultMutableTreeNode(
				new TreeNode(NodeType.PERSON,"ld","unified,elegant,makes you a better person"));
	DefaultMutableTreeNode yqj=
		new DefaultMutableTreeNode(new TreeNode(NodeType.PERSON,"yqj","settle down to learn"));
	DefaultMutableTreeNode djw = 
		new DefaultMutableTreeNode(new TreeNode(NodeType.PERSON,"djw","ר���ô���BXZN"));
	DefaultMutableTreeNode qzj = 
		new DefaultMutableTreeNode(new TreeNode(NodeType.PERSON,"qzj","����˯��"));
	DefaultMutableTreeNode thy=
		new DefaultMutableTreeNode(new TreeNode(NodeType.PERSON,"thy","xxxxx"));
	
	public void init(){
		groups.add(g1);
		groups.add(g2);
		groups.add(g3);
		g1.add(wfx);
		g1.add(ld);
		g2.add(yqj);
		g2.add(djw);
		g3.add(qzj);
		g3.add(thy);
		tree = new JTree(groups);
		//���ø�JTreeʹ���Զ���Ľڵ������
		tree.setCellRenderer(new MyCellRenderer());
		//���ò���ʾ���ڵ�
		tree.setRootVisible(false);
		tree.putClientProperty("JTree.lineStyle", "Horizontal");
		jf.add(new JScrollPane(tree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
		JPanel p =new JPanel();
		p.setPreferredSize(new Dimension(200,70));
		p.setComponentPopupMenu(new MyUIManager(jf,null).getJPopupMenu());
		jf.add(p,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(300, 650);
		jf.setAlwaysOnTop(true);
		jf.setResizable(false);
		jf.setVisible(true);
		
		add_actions();
	}
	private void add_actions(){
		jf.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent evt){
				Component c = (Component)evt.getSource();
				if(c!=null){
					Graphics g = c.getGraphics();
					g.setColor(c1);
					g.fillRect(0, 0, c.getWidth(), c.getHeight());
					c.repaint();
				}
				System.out.println("mouseEntered");
			}
			@Override
			public void mouseExited(MouseEvent evt){
				System.out.println("mouseExited");
			}
		});
	}
	public static void main(String[] args){
		new CustomTreeNode().init();
	}
}


@SuppressWarnings("serial")
//ʵ���Լ��Ľڵ������
class MyCellRenderer extends JPanel implements TreeCellRenderer{
	private Image srcImg;
	private BufferedImage buffImg;
	private int IMG_WIDTH=60;
	private int IMG_HEIGHT=60;
	private Color bgColor;
	private Color fgColor;
	private Color descColor = new Color(150,100,155,130);
	private TreeNode node;
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded,boolean leaf, int row, boolean hasFocus)
	{
		DefaultMutableTreeNode def =(DefaultMutableTreeNode)value;
		//��ȡ�Զ����TreeNode
		node = (TreeNode)def.getUserObject();
		bgColor=selected?Color.cyan:tree.getBackground();
		fgColor=selected?Color.red:tree.getForeground();
		//���ظ�JPanel������Ϊ�б��������
		return this;
	}
	//��дpaintComponent(Graphics g)����,�ı�JPanel���
	@Override
	public void paintComponent(Graphics g){
		if(node.getType()==NodeType.GROUP){
			//���Ʊ���
			g.setColor(bgColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			//����ǰ��(���Ʒ�������)
			g.setColor(fgColor);
			g.setFont(new Font("SansSerif",Font.BOLD,20));
			g.drawString(node.getName(),10,getHeight()/2+20);
		}
		//�ýڵ��ʾһ��Person
		else{
			//���Ʊ���
			g.setColor(bgColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			//------����ǰ��----------//
			String src="./src/img/";
			try {
				srcImg = ImageIO.read(new File(src+node.getName()+".jpg"));
			} catch (IOException e) {e.printStackTrace();}
			//����һ��img����
			buffImg = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_RGB);
			Graphics bufg = buffImg.getGraphics();
			//��ԭʼͼ��ѹ������BufImg������
			bufg.drawImage(srcImg, 0, 0,IMG_WIDTH,IMG_HEIGHT, null);
			//���ƺ���ͼ��,���Ƶ�Y���м�,�����3������
			g.drawImage(buffImg,3,getHeight()/2 -IMG_HEIGHT/2 ,null);
			//�����û���
			g.setFont(new Font("SansSerif",Font.BOLD,25));
			g.setColor(fgColor);
			g.drawString(node.getName(), IMG_WIDTH+10, 30);
			//���������ı�
			g.setFont(new Font("SansSerif",Font.ITALIC,14));
			g.setColor(descColor);
			g.drawString(node.getDesc(), IMG_WIDTH+10, 50);
		}
	}
	
	@Override
	public Dimension getPreferredSize(){
		if(node.getType()==NodeType.GROUP){
			return new Dimension(300,50);
		}
		else{
			return new Dimension(300,70);
		}
	}
}

//����һ��NodeData��,���ڷ�װ�ڵ�����
class TreeNode{
	private NodeType nodeType;
	private String name;
	private String desc;
	public TreeNode(NodeType type,String nm,String desc){
		nodeType=type;
		name=nm;
		this.desc = desc;
	}
	public NodeType getType(){
		return nodeType;
	}
	public String getName(){
		return name;
	}
	public String getDesc(){
		return desc;
	}
	public String toString(){
		return "["+name +"]:"+desc;
	}
}

enum NodeType{
	GROUP,PERSON;
}
