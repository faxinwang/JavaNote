package myjavax.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

public class ListRenderingTest {
	private JFrame jf = new JFrame("�����б�");
	private String[] friends = new String[]{
		"wfx","qzj","sz","ljq","ljf","lyl","sj","zyj"
	};
	private JList<String> friendList =new JList<>(friends);
	
	@SuppressWarnings("unchecked")
	public void init(){
		//���ø�JListʹ��ImageCellRenderer��Ϊ�б��������
		friendList.setCellRenderer(new ImageCellRenderer());
		jf.add(new JScrollPane(friendList));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setResizable(false);
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args){
		new ListRenderingTest().init();
	}
}


@SuppressWarnings({ "rawtypes", "serial" })
class ImageCellRenderer extends JPanel implements ListCellRenderer{
	private Image srcImg;
	private BufferedImage buffImg;
	private ImageIcon icon;
	private int IMG_WIDTH=60;
	private int IMG_HEIGHT=60;
	private String name;
	//������Ƶ�Ԫ���ǵı���ɫ��ǰ��ɫ
	private Color bgColor;
	private Color fgColor;
	private String src="./src/img/";
	
	@Override
	public Component getListCellRendererComponent(JList list,
			Object value, int index, boolean isSelected,boolean cellHasFocus) {
		try {
			srcImg = ImageIO.read(new File(src+value+".jpg"));
		} catch (IOException e){e.printStackTrace();}
		
		name = value.toString();
		
//		bgColor=isSelected?Color.orange:list.getBackground();
//		fgColor=isSelected?Color.red:list.getForeground();
		
		bgColor=cellHasFocus?Color.cyan : Color.BLUE;
		fgColor=cellHasFocus?Color.red : list.getForeground();
		
		
		//���ظ�JPanel������Ϊ�б��������
		return this;
	}
	//��дpaintComponent(Graphics g)����,�ı�JPanel���
	@Override
	public void paintComponent(Graphics g){
			//����һ��img����
			buffImg = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_RGB);
			Graphics imgg = buffImg.getGraphics();
			//��ԭʼͼ��ѹ������img������
			imgg.drawImage(srcImg, 0, 0,IMG_WIDTH,IMG_HEIGHT, null);
			//��img����������ImageIcon
			icon = new ImageIcon(buffImg);
			
			g.setColor(bgColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(fgColor);
			//���ƺ���ͼ��,���Ƶ�Y���м�,�����3������
			g.drawImage(icon.getImage(),3,getHeight()/2 -IMG_HEIGHT/2 ,null);
			g.setFont(new Font("SansSerif",Font.BOLD,18));
			//���ƺ����û���
			g.drawString(name,IMG_WIDTH+10,30);
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(200,70);
	}
}