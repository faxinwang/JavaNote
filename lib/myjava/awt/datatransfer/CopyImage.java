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
	//���������������һ��Image����
	public ImageSelection(Image img){
		image = img;
	}
	//���ظ�Transferable������֧�ֵ�����DataFlavor
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return new DataFlavor[] {DataFlavor.imageFlavor};
	}
	//���ظ�Transferable�����Ƿ�֧��ָ����DataFlavor
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor){
		return flavor.equals(DataFlavor.imageFlavor);
	}
	//ȡ����Transferable������ʵ�ʵ�����
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
	//ʹ��ArrayList����������ճ��������image--���ǵ���ͼ�㴦��
	java.util.List<Image> imageList = new java.util.ArrayList<>();
	BufferedImage image = new BufferedImage(IMAGE_WIDTH,IMAGE_HEIGHT,BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	Frame f = new Frame("ͼƬ��ʾ��");
	Button last = new Button("��һ��");
	Button next = new Button("��һ��");
	Button copy = new Button("����");
	Button paste = new Button("ճ��");
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
				//����ǰimage�����װ��ImageSelection����
				ImageSelection contents = new ImageSelection(imageList.get(cur_image));
				//��ImageSelection�����������
				clipboard.setContents(contents, null);
			}
		});
		//���������е�ͼƬ��ӵ�imageList�в���ʾ����
		paste.addActionListener(event ->{
			if(clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)){
				try{
					//ȡ���������е��������ݣ���������ӵ�list������
					imageList.add((Image)clipboard.getData(DataFlavor.imageFlavor));
					++image_count;
					cur_image = image_count-1;//�±��0��ʼ
					drawArea.repaint();
				}catch(Exception e){
					System.out.println("�Ӽ�������ȡ������ʧ��!");
					e.printStackTrace();
				}
			}
		});
		//��ʾ��һ��ͼƬ
		last.addActionListener(event ->{
			if(imageList.size()>0){
				if(--cur_image == -1) cur_image = imageList.size()-1;
				drawArea.repaint();
			}
		});
		//��ʾ��һ��ͼƬ
		next.addActionListener(event ->{
			if(imageList.size()>0){
				if(++cur_image == imageList.size()) cur_image = 0;
				drawArea.repaint();
			}
		});
		//�رմ����¼�
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
			g.drawString("��ȥ����һ��ͼƬ���ͼ\nȻ����ճ��!", IMAGE_WIDTH/3, IMAGE_HEIGHT/2);
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
