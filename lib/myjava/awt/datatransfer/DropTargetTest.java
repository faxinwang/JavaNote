package myjava.awt.datatransfer;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

/* AWT�ṩ��DropTarget������ʾ�Ϸ�Ŀ�꣬�乹��������
 * DropTarget(Component c,int options,DropTargetListener drl):
 * ��C���������һ���Ϸ�Ŀ�꣬optionsָ���������ϷŲ���,DropTargetListener�����
 * �ϷŲ���������Ӧ����Ӧ��options�ɽ������¼���ֵ��
 * DnDConstants.ACTION_COPY:��ʾ���Ʋ���
 * DnDConstants.ACTION_COPY_OR_MOVE:��ʾ���ƻ��ƶ�����
 * DnDConstants.ACTION_LINK:��ʾ������ݷ�ʽ
 * DnDConstants.ACTION_MOVE:��ʾ�ƶ�����
 * DnDConstants.ACTION_NONE:��ʾ���κβ���
 * 
 * ���м�������������5���¼�������:
 * dragEnter(DropTargetDragEvent dtde):���������Ϸ�Ŀ���ǽ�����DropTargetListener�ĸ÷���
 * dragExit(DropTargetEvent dte):������Ƴ��Ϸ�Ŀ���ǽ�����DropTargetListener�ĸ÷���
 * dragOver(DropTargetDragEvent dtde):��������Ϸ�Ŀ�����ƶ�ʱ������DropTargetListener�ĸ÷���
 * drop(DropTargetDropEvent dtde):���û����Ϸ�Ŀ�����ɿ����������ϷŽ���ʱ������DropTargetListener�ĸ÷���
 * dropActionChanged(DropTargetDragEvent dtde):���û����Ϸ�Ŀ���ϸı����ϷŲ������簴�»��ɿ���Ctrl�ȸ�����ʱ�����÷���
 */

public class DropTargetTest {
	final int DESKTOP_WIDTH = 480;
	final int DESKTOP_HEIGHT = 360;
	final int FRAME_DISTANCE = 30;
	JFrame jf =new JFrame("�����Ϸ�Ŀ��---��ͼƬ�ļ�����ô���");
	//����һ����������
	private JDesktopPane desktop = new JDesktopPane();
	//������һ���ڲ����ڵ�����
	private int nextFrameX;
	private int nextFrameY;
	//�����ڲ�����Ϊ���������1/2��С
	private int width = DESKTOP_WIDTH /2;
	private int height = DESKTOP_HEIGHT/2;
	
	void init(){
		desktop.setPreferredSize(new Dimension(DESKTOP_WIDTH,DESKTOP_HEIGHT));
		//����ǰ���ڴ������Ϸ�Ŀ�괰��
		new DropTarget(jf,DnDConstants.ACTION_COPY,new ImageDropTargetListener());
		jf.add(desktop);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	//�������ʵ��DropTargetListener��ȫ������������ͨ���̳�DropTargetAdapter����д���𷽷�
	class ImageDropTargetListener implements DropTargetListener{

		@Override
		public void dragEnter(DropTargetDragEvent dtde) {
			System.out.println("dragEnter");
		}

		@Override
		public void dragOver(DropTargetDragEvent dtde) {
			System.out.println("dragOver");
		}

		@Override
		public void dropActionChanged(DropTargetDragEvent dtde) {
			System.out.println("dropActionChanged");
		}

		@Override
		public void dragExit(DropTargetEvent dte) {
			System.out.println("dragExit");
		}

		@Override
		public void drop(DropTargetDropEvent event) {
			System.out.println("drop");
			//���ܸ��Ʋ���
			event.acceptDrop(DnDConstants.ACTION_COPY);
			//��ȡ�Ϸŵ�����
			Transferable transferable = event.getTransferable();
			DataFlavor[] flavors = transferable.getTransferDataFlavors();
			//�����Ϸ���������������ݸ�ʽ
			for(int i=0;i<flavors.length;++i){
				DataFlavor d = flavors[i];
				try{
					//����Ϸ����ݵĸ�ʽ���ļ��б�
					if(d.equals(DataFlavor.javaFileListFlavor)){
						//ȡ���ϷŲ�������ļ��б�
						List<?> fileList = (List<?>)transferable.getTransferData(d);
						for(Object f : fileList){
							showImage((File)f,event);
						}
					}
				}catch(Exception e){
					System.out.println("��ȡ�ļ�����:" + e.getMessage());
					e.printStackTrace();
					//ǿ���ϷŲ���������ֹͣ�����Ϸ�Ŀ��
					event.dropComplete(true);
				}
			}
		}
		//��ʾÿ���ļ��Ĺ��߷���
		private void showImage(File f,DropTargetDropEvent event)throws IOException{
			Image image = ImageIO.read(f);
			if(image == null){
				//ǿ���ϷŲ���������ֹͣ�����Ϸ�Ŀ��
				event.dropComplete(true);
				JOptionPane.showInternalMessageDialog(desktop, "ϵͳ��֧���������͵��ļ�");
				return ;
			}
			ImageIcon icon = new ImageIcon(image);
			//�����ڲ�������ʾ��ͼƬ
			JInternalFrame iframe = new JInternalFrame(f.getName(),true,true,true,true);
			JLabel imageLabel = new JLabel(icon);
			iframe.add(new JScrollPane(imageLabel));
			desktop.add(iframe);
			//���ô��ڵ�ԭʼλ��(�ڲ�����Ĭ�ϴ�С��0X0,����0,0λ��)
			iframe.reshape(nextFrameX, nextFrameY, width, height);
			//ʹ�ô��ڿɼ���������ѡ����
			iframe.show();
			//������һ���ڴ��ڵĵ�λ��
			nextFrameX += FRAME_DISTANCE;
			nextFrameY += FRAME_DISTANCE;
			if(nextFrameX + width > desktop.getWidth()) nextFrameX = 0;
			if(nextFrameY + height >desktop.getHeight()) nextFrameY = 0;
		}
		
	}
	
	public static void main(String[] args){
		new DropTargetTest().init();
	}
}
