package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

class JLayeredPaneTest{
	String src="./src/img/";
	JFrame jf = new JFrame("测试JLayeredPane");
	JLayeredPane jlp = new JLayeredPane();
	public void init(){
		//向JLayeredPane中添加三个组件
		jlp.add(new ContentPanel(10,20,"王发新",src+"wfx.jpg"));
		jlp.add(new ContentPanel(100,60,"秦中杰",src+"qzj.jpg"));
		jlp.add(new ContentPanel(190,100,"李建强",src+"ljq.jpg"));
		jlp.setPreferredSize(new Dimension(400,300));
		jlp.setVisible(true);
		jf.add(jlp);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	@SuppressWarnings("serial")
	//扩展了JPanel类,可以直接创建一个放在指定位置,
	//且有指定标题,指定图标的JPanel对象
	class ContentPanel extends JPanel{
		public ContentPanel(int x,int y,String title,String icon){
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(),title));
			JLabel label = new JLabel(new ImageIcon(icon));
			add(label);
			//向JLabelPane中添加组件时,必须显示设置该组件的大小和位置,否则该组件不能显示效果
			setBounds(x,y,160,220);
		}
	}
}


public class JInternalFrameTest {
	final int DESKTOP_WIDTH = 480;
	final int DESKTOP_HEIGHT= 360;
	final int FRAME_DISTANCE= 30;
	final String src = "./src/icon/";
	JFrame jf =new JFrame("MDI界面");
	//定义一个虚拟桌面
	private MyJDesktopPane desktop = new MyJDesktopPane();
	//保存下一个内部窗口的坐标
	private int nextFrameX;
	private int nextFrameY;
	//定义内部窗口为虚拟桌面的1/2大小
	private int width = DESKTOP_WIDTH/2;
	private int height = DESKTOP_HEIGHT/2;
	//为主窗口定义两个菜单
	JMenu file_mu = new JMenu("文件");
	JMenu window_mu = new JMenu("窗口");
	//定义NewAction用于创建菜单和工具条
	@SuppressWarnings("serial")
	Action newAction = new AbstractAction("新建",new ImageIcon(src+"new.png")){
		@Override
		public void actionPerformed(ActionEvent e) {
			//创建内部窗口
			final JInternalFrame iframe = new JInternalFrame("新文档",
					true, 	//可改变大小
					true,	//可关闭
					true,	//可最大化
					true);	//可最小化
			iframe.add(new JScrollPane(new JTextArea(8,40)));
			desktop.add(iframe);
			iframe.reshape(nextFrameX, nextFrameY, width, height);
			iframe.show();
			//计算下一个内部窗口的位置
			nextFrameX += FRAME_DISTANCE;
			nextFrameY += FRAME_DISTANCE;
			if( nextFrameX + width > desktop.getWidth() ) nextFrameX = 0;
			if( nextFrameY + height > desktop.getHeight() ) nextFrameY = 0;
		}
	};
	@SuppressWarnings("serial")
	//定义exitAction用于创建菜单和工具按钮
	Action exitAction = new AbstractAction("退出",new ImageIcon(src+"exit.png")){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
	public void init(){
		//为窗口安装菜单条和工具条
		JMenuBar jmb = new JMenuBar();
		JToolBar jtb = new JToolBar();
		jf.setJMenuBar(jmb);
		jmb.add(file_mu);
		jmb.add(window_mu);
		file_mu.add(newAction);
		file_mu.add(exitAction);
		jtb.add(newAction);
		jtb.add(exitAction);
		
		//选择下一个非图标窗口的菜单项
		JMenuItem next_item = new JMenuItem("下一个");
		next_item.addActionListener(event -> desktop.selectNextWindow());
		window_mu.add(next_item);
		//级联显示窗口的菜单项
		JMenuItem cascade_item = new JMenuItem("级联");
		//内部窗口的大小是外部窗口的0.75倍
		cascade_item.addActionListener(event -> desktop.cascadeWindows(FRAME_DISTANCE, 0.75));
		window_mu.add(cascade_item);
		//平铺显示所有内部窗口的菜单项
		JMenuItem tile_item = new JMenuItem("平铺");
		tile_item.addActionListener(event -> desktop.tileWindows());
		window_mu.add(tile_item);
		
		final JCheckBoxMenuItem dragOutline_item = new JCheckBoxMenuItem("仅显示拖动窗口的轮廓");
		dragOutline_item.addActionListener(event -> {
			//根据该菜单项是否选择来决定采用哪种拖动模式
			desktop.setDragMode(dragOutline_item.isSelected()?
					JDesktopPane.OUTLINE_DRAG_MODE:	//拖动时仅显示轮廓
					JDesktopPane.LIVE_DRAG_MODE);	//拖动时连续重绘
		});
		window_mu.add(dragOutline_item);
		desktop.setPreferredSize(new Dimension(480,360));
		//将虚拟桌面添加到顶级容器中
		jf.add(desktop);
		jf.add(jtb,BorderLayout.NORTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class MyJDesktopPane extends JDesktopPane{
		//将所有的窗口以级联方式显示
		//其中offset是两个窗口的位移距离
		//scale是内部窗口与JDesktopPane的大小比例
		public void cascadeWindows(int offset,double scale){
			//定义显示级联显示窗口时内部窗口的大小
			int width = (int)(getWidth()*scale);
			int height = (int)(getHeight()*scale);
			//用于保存级联窗口时每个窗口的位置
			int x=0,y=0;
			for(JInternalFrame frame:getAllFrames()){
				//取消内部窗口的最大化,最小化
				try {
					frame.setIcon(false);
					frame.setMaximum(false);
					//把窗口重新放置在指定位置
					frame.reshape(x, y, width, height);
					x += offset;
					y += offset;
					//如果到了虚拟桌面的边界
					if( x + width > getWidth()) x =0;
					if( y + height > getHeight() )y=0;
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		}
		//将所有窗口以平铺方式显示
		public void tileWindows(){
			//统计所有窗口
			int frameCount=0;
			for(@SuppressWarnings("unused") JInternalFrame frame:getAllFrames()){
				++frameCount;
			}
			//计算需要多少行,多少列才可以平铺所有窗口
			int rows = (int)Math.sqrt(frameCount);
			int cols = frameCount/rows;
			//需要额外增加到其他列中的窗口
			int extra = frameCount % rows;
			//计算平铺时内部窗口的大小
			int width = getWidth()/cols;
			int height = getHeight()/rows;
			//用于保存平铺窗口时每个窗口在横向,纵向上的索引
			int x=0,y=0;
			for(JInternalFrame frame: getAllFrames()){
				try{
					//取消内部窗口的最大化,最小化
					frame.setMaximum(false);
					frame.setIcon(false);
					//将窗口放在指定位置
					frame.reshape(x*width, y*height, width, height);
					++y;
					//每排完一列窗口
					if(y==rows){
						//开始排放下一列窗口
						y=0;
						++x;
						//如果额外多出的窗口与剩下的列数相等
						//则后面所有列都需要多排列一个窗口
						if(extra == cols -x ){
							++rows;
							height=getHeight()/rows;
						}
					}
				}catch(PropertyVetoException e){e.printStackTrace();}
			}
		}
		
		//选择下一个非图标窗口
		public void selectNextWindow(){
			JInternalFrame[] frames = getAllFrames();
			for(int i=0;i<frames.length;++i){
				if(frames[i].isSelected()){
					//找出下一个非最小化的窗口,尝试选中它
					//如果选中失败,则继续尝试选中下一个窗口
					int next=(i+1)%frames.length;
					while(next != i){
						//如果该窗口不是出于最小化状态
						if(!frames[next].isIcon()){
							try{
								frames[next].setSelected(true);
								frames[next].toFront();
								frames[i].toBack();
								return;
							}catch(PropertyVetoException e){e.printStackTrace();}
						}
						next = (next+1)%frames.length;
					}
				}
			}
		}
	}
	
	public static void main(String[] args){
//		new JLayeredPaneTest().init();
		new JInternalFrameTest().init();
	}
}
