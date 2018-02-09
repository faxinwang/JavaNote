package myjava.awt;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ZoomImage {
	final int WIDTH = 80;
	final int HEIGHT = 90;
	BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	Graphics g = image.getGraphics();
	public void zoom() throws Exception{
		//��ȡԭʼλͼ
		Image srcImage = ImageIO.read(new File("./src/img/wfx.jpg"));
		//��ԭʼλͼ��С����Ƶ�image������
		g.drawImage(srcImage,0,0,WIDTH,HEIGHT,null);
		//��image��������������ļ�
		ImageIO.write(image, "jpeg", new File("./src/img/wfx80X90.jpg"));
		System.out.println("ѹ�����!");
	}
	
	public static void main(String[] args)throws Exception{
		new ZoomImage().zoom();
	}
}
