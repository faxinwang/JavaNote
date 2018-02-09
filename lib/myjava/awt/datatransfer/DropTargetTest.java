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

/* AWT提供了DropTarget类来表示拖放目标，其构造器如下
 * DropTarget(Component c,int options,DropTargetListener drl):
 * 将C组件创建成一个拖放目标，options指定所做的拖放操作,DropTargetListener负责对
 * 拖放操作做出相应的向应。options可接受如下几个值：
 * DnDConstants.ACTION_COPY:表示复制操作
 * DnDConstants.ACTION_COPY_OR_MOVE:表示复制或移动操作
 * DnDConstants.ACTION_LINK:表示建立快捷方式
 * DnDConstants.ACTION_MOVE:表示移动操作
 * DnDConstants.ACTION_NONE:表示无任何操作
 * 
 * 其中监听器包含如下5个事件处理器:
 * dragEnter(DropTargetDragEvent dtde):当光标进入拖放目标是将触发DropTargetListener的该方法
 * dragExit(DropTargetEvent dte):当光标移出拖放目标是将触发DropTargetListener的该方法
 * dragOver(DropTargetDragEvent dtde):当光标在拖放目标上移动时将触发DropTargetListener的该方法
 * drop(DropTargetDropEvent dtde):当用户在拖放目标上松开鼠标键，即拖放结束时将触发DropTargetListener的该方法
 * dropActionChanged(DropTargetDragEvent dtde):当用户在拖放目标上改变了拖放操作，如按下或松开了Ctrl等辅助键时触发该方法
 */

public class DropTargetTest {
	final int DESKTOP_WIDTH = 480;
	final int DESKTOP_HEIGHT = 360;
	final int FRAME_DISTANCE = 30;
	JFrame jf =new JFrame("测试拖放目标---把图片文件拖入该窗口");
	//定义一个虚拟桌面
	private JDesktopPane desktop = new JDesktopPane();
	//保存下一个内部窗口的坐标
	private int nextFrameX;
	private int nextFrameY;
	//定义内部窗口为虚拟桌面的1/2大小
	private int width = DESKTOP_WIDTH /2;
	private int height = DESKTOP_HEIGHT/2;
	
	void init(){
		desktop.setPreferredSize(new Dimension(DESKTOP_WIDTH,DESKTOP_HEIGHT));
		//将当前窗口创建成拖放目标窗口
		new DropTarget(jf,DnDConstants.ACTION_COPY,new ImageDropTargetListener());
		jf.add(desktop);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	//如果不想实现DropTargetListener的全部方法，可以通过继承DropTargetAdapter来重写个别方法
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
			//接受复制操作
			event.acceptDrop(DnDConstants.ACTION_COPY);
			//获取拖放的内容
			Transferable transferable = event.getTransferable();
			DataFlavor[] flavors = transferable.getTransferDataFlavors();
			//遍历拖放内容里的所有数据格式
			for(int i=0;i<flavors.length;++i){
				DataFlavor d = flavors[i];
				try{
					//如果拖放内容的格式是文件列表
					if(d.equals(DataFlavor.javaFileListFlavor)){
						//取出拖放操作里的文件列表
						List<?> fileList = (List<?>)transferable.getTransferData(d);
						for(Object f : fileList){
							showImage((File)f,event);
						}
					}
				}catch(Exception e){
					System.out.println("获取文件出错:" + e.getMessage());
					e.printStackTrace();
					//强制拖放操作结束，停止阻塞拖放目标
					event.dropComplete(true);
				}
			}
		}
		//显示每个文件的工具方法
		private void showImage(File f,DropTargetDropEvent event)throws IOException{
			Image image = ImageIO.read(f);
			if(image == null){
				//强制拖放操作结束，停止阻塞拖放目标
				event.dropComplete(true);
				JOptionPane.showInternalMessageDialog(desktop, "系统不支持这种类型的文件");
				return ;
			}
			ImageIcon icon = new ImageIcon(image);
			//创建内部窗口显示该图片
			JInternalFrame iframe = new JInternalFrame(f.getName(),true,true,true,true);
			JLabel imageLabel = new JLabel(icon);
			iframe.add(new JScrollPane(imageLabel));
			desktop.add(iframe);
			//设置窗口的原始位置(内部窗口默认大小是0X0,放在0,0位置)
			iframe.reshape(nextFrameX, nextFrameY, width, height);
			//使该窗口可见，并尝试选中它
			iframe.show();
			//计算下一个内窗口的的位置
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
