package myjava.awt.datatransfer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;


class ImageSelection implements Transferable{
	Image image;
	//构造器，负责持有一个Image对象
	public ImageSelection(Image img){
		image = img;
	}
	//返回该Transferable对象所支持的所有DataFlavor
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] {DataFlavor.imageFlavor};
	}
	//返回该Transferable对象是否支持指定的DataFlavor
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor){
		return flavor.equals(DataFlavor.imageFlavor);
	}
	//取出该Transferable对象里实际的数据
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if(flavor.equals(DataFlavor.imageFlavor))
			return image;
		else
			throw new UnsupportedFlavorException(flavor);
	}
	
}

public class CopyImage {
	int IMAGE_WIDTH = 400;
	int IMAGE_HEIGHT = 500;
	int cur_image = 0,image_count = 0;
	Clipboard clipboard =Toolkit.getDefaultToolkit().getSystemClipboard();
	//使用ArrayList来保存所有粘贴进来的image--就是当成图层处理
	java.util.List<Image> imageList = new java.util.ArrayList<>();
	BufferedImage image = new BufferedImage(IMAGE_WIDTH,IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	Frame f = new Frame("图片显示器");
	Button last = new Button("上一张");
	Button next = new Button("下一张");
	Button copy = new Button("复制");
	Button paste = new Button("粘贴");
	MyCanvas drawArea = new MyCanvas();
	Panel p = new Panel();
	
	void init(){
		addActionListeners();
		p.add(last);
		p.add(next);
		p.add(paste);
		p.add(copy);
		drawArea.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
		drawArea.drawFirst();
		f.add(drawArea);//center
		f.add(p,BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
		
	}
	void addActionListeners(){
		copy.addActionListener(event ->{
			if(imageList.size()>0){
				//将当前image对象封装成ImageSelection对象
				ImageSelection contents = new ImageSelection(imageList.get(cur_image));
				//将ImageSelection放入剪贴板中
				clipboard.setContents(contents, null);
			}
		});
		//将剪贴板中的图片添加到imageList中并显示出来
		paste.addActionListener(event ->{
			if(clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)){
				try{
					//取出剪贴板中的数据内容，并将其添加到list集合中
					imageList.add((Image)clipboard.getData(DataFlavor.imageFlavor));
					++image_count;
					cur_image = image_count-1;//下标从0开始
					drawArea.repaint();
				}catch(Exception e){
					System.out.println("从剪贴板中取出数据失败!");
					e.printStackTrace();
				}
			}
		});
		//显示上一张图片
		last.addActionListener(event ->{
			if(imageList.size()>0){
				if(--cur_image == -1) cur_image = imageList.size()-1;
				drawArea.repaint();
			}
		});
		//显示下一张图片
		next.addActionListener(event ->{
			if(imageList.size()>0){
				if(++cur_image == imageList.size()) cur_image = 0;
				drawArea.repaint();
			}
		});
		//关闭窗口事件
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
	}

	@SuppressWarnings("serial")
	class MyCanvas extends Canvas{
		public void drawFirst(){
			g.setColor(new Color(200,220,250));
			g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
			g.drawString("快去复制一张图片或截图\n然后点击粘贴!", IMAGE_WIDTH/3, IMAGE_HEIGHT/2);
			drawArea.repaint();
		}
		public void paint(Graphics g){
			if(imageList.size()>0)
				g.drawImage(imageList.get(cur_image), 0, 0, null);
		}
	}
	
	public static void main(String[] args){
		new CopyImage().init();
	}
}
