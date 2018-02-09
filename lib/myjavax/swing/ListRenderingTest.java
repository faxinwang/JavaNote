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
	private JFrame jf = new JFrame("好友列表");
	private String[] friends = new String[]{
		"wfx","qzj","sz","ljq","ljf","lyl","sj","zyj"
	};
	private JList<String> friendList =new JList<>(friends);
	
	@SuppressWarnings("unchecked")
	public void init(){
		//设置该JList使用ImageCellRenderer作为列表项绘制器
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
	//定义绘制单元格是的背景色和前景色
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
		
		
		//返回该JPanel对象做为列表项绘制器
		return this;
	}
	//重写paintComponent(Graphics g)方法,改变JPanel外观
	@Override
	public void paintComponent(Graphics g){
			//创建一个img缓存
			buffImg = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,BufferedImage.TYPE_INT_RGB);
			Graphics imgg = buffImg.getGraphics();
			//将原始图像压缩画到img缓存上
			imgg.drawImage(srcImg, 0, 0,IMG_WIDTH,IMG_HEIGHT, null);
			//用img缓存来创建ImageIcon
			icon = new ImageIcon(buffImg);
			
			g.setColor(bgColor);
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(fgColor);
			//绘制好友图标,绘制到Y轴中间,距左边3个像素
			g.drawImage(icon.getImage(),3,getHeight()/2 -IMG_HEIGHT/2 ,null);
			g.setFont(new Font("SansSerif",Font.BOLD,18));
			//绘制好友用户名
			g.drawString(name,IMG_WIDTH+10,30);
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(200,70);
	}
}