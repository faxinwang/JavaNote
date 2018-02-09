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
	JFrame jf = new JFrame("����JLayeredPane");
	JLayeredPane jlp = new JLayeredPane();
	public void init(){
		//��JLayeredPane������������
		jlp.add(new ContentPanel(10,20,"������",src+"wfx.jpg"));
		jlp.add(new ContentPanel(100,60,"���н�",src+"qzj.jpg"));
		jlp.add(new ContentPanel(190,100,"�ǿ",src+"ljq.jpg"));
		jlp.setPreferredSize(new Dimension(400,300));
		jlp.setVisible(true);
		jf.add(jlp);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	@SuppressWarnings("serial")
	//��չ��JPanel��,����ֱ�Ӵ���һ������ָ��λ��,
	//����ָ������,ָ��ͼ���JPanel����
	class ContentPanel extends JPanel{
		public ContentPanel(int x,int y,String title,String icon){
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(),title));
			JLabel label = new JLabel(new ImageIcon(icon));
			add(label);
			//��JLabelPane��������ʱ,������ʾ���ø�����Ĵ�С��λ��,��������������ʾЧ��
			setBounds(x,y,160,220);
		}
	}
}


public class JInternalFrameTest {
	final int DESKTOP_WIDTH = 480;
	final int DESKTOP_HEIGHT= 360;
	final int FRAME_DISTANCE= 30;
	final String src = "./src/icon/";
	JFrame jf =new JFrame("MDI����");
	//����һ����������
	private MyJDesktopPane desktop = new MyJDesktopPane();
	//������һ���ڲ����ڵ�����
	private int nextFrameX;
	private int nextFrameY;
	//�����ڲ�����Ϊ���������1/2��С
	private int width = DESKTOP_WIDTH/2;
	private int height = DESKTOP_HEIGHT/2;
	//Ϊ�����ڶ��������˵�
	JMenu file_mu = new JMenu("�ļ�");
	JMenu window_mu = new JMenu("����");
	//����NewAction���ڴ����˵��͹�����
	@SuppressWarnings("serial")
	Action newAction = new AbstractAction("�½�",new ImageIcon(src+"new.png")){
		@Override
		public void actionPerformed(ActionEvent e) {
			//�����ڲ�����
			final JInternalFrame iframe = new JInternalFrame("���ĵ�",
					true, 	//�ɸı��С
					true,	//�ɹر�
					true,	//�����
					true);	//����С��
			iframe.add(new JScrollPane(new JTextArea(8,40)));
			desktop.add(iframe);
			iframe.reshape(nextFrameX, nextFrameY, width, height);
			iframe.show();
			//������һ���ڲ����ڵ�λ��
			nextFrameX += FRAME_DISTANCE;
			nextFrameY += FRAME_DISTANCE;
			if( nextFrameX + width > desktop.getWidth() ) nextFrameX = 0;
			if( nextFrameY + height > desktop.getHeight() ) nextFrameY = 0;
		}
	};
	@SuppressWarnings("serial")
	//����exitAction���ڴ����˵��͹��߰�ť
	Action exitAction = new AbstractAction("�˳�",new ImageIcon(src+"exit.png")){
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
	public void init(){
		//Ϊ���ڰ�װ�˵����͹�����
		JMenuBar jmb = new JMenuBar();
		JToolBar jtb = new JToolBar();
		jf.setJMenuBar(jmb);
		jmb.add(file_mu);
		jmb.add(window_mu);
		file_mu.add(newAction);
		file_mu.add(exitAction);
		jtb.add(newAction);
		jtb.add(exitAction);
		
		//ѡ����һ����ͼ�괰�ڵĲ˵���
		JMenuItem next_item = new JMenuItem("��һ��");
		next_item.addActionListener(event -> desktop.selectNextWindow());
		window_mu.add(next_item);
		//������ʾ���ڵĲ˵���
		JMenuItem cascade_item = new JMenuItem("����");
		//�ڲ����ڵĴ�С���ⲿ���ڵ�0.75��
		cascade_item.addActionListener(event -> desktop.cascadeWindows(FRAME_DISTANCE, 0.75));
		window_mu.add(cascade_item);
		//ƽ����ʾ�����ڲ����ڵĲ˵���
		JMenuItem tile_item = new JMenuItem("ƽ��");
		tile_item.addActionListener(event -> desktop.tileWindows());
		window_mu.add(tile_item);
		
		final JCheckBoxMenuItem dragOutline_item = new JCheckBoxMenuItem("����ʾ�϶����ڵ�����");
		dragOutline_item.addActionListener(event -> {
			//���ݸò˵����Ƿ�ѡ�����������������϶�ģʽ
			desktop.setDragMode(dragOutline_item.isSelected()?
					JDesktopPane.OUTLINE_DRAG_MODE:	//�϶�ʱ����ʾ����
					JDesktopPane.LIVE_DRAG_MODE);	//�϶�ʱ�����ػ�
		});
		window_mu.add(dragOutline_item);
		desktop.setPreferredSize(new Dimension(480,360));
		//������������ӵ�����������
		jf.add(desktop);
		jf.add(jtb,BorderLayout.NORTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class MyJDesktopPane extends JDesktopPane{
		//�����еĴ����Լ�����ʽ��ʾ
		//����offset���������ڵ�λ�ƾ���
		//scale���ڲ�������JDesktopPane�Ĵ�С����
		public void cascadeWindows(int offset,double scale){
			//������ʾ������ʾ����ʱ�ڲ����ڵĴ�С
			int width = (int)(getWidth()*scale);
			int height = (int)(getHeight()*scale);
			//���ڱ��漶������ʱÿ�����ڵ�λ��
			int x=0,y=0;
			for(JInternalFrame frame:getAllFrames()){
				//ȡ���ڲ����ڵ����,��С��
				try {
					frame.setIcon(false);
					frame.setMaximum(false);
					//�Ѵ������·�����ָ��λ��
					frame.reshape(x, y, width, height);
					x += offset;
					y += offset;
					//���������������ı߽�
					if( x + width > getWidth()) x =0;
					if( y + height > getHeight() )y=0;
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		}
		//�����д�����ƽ�̷�ʽ��ʾ
		public void tileWindows(){
			//ͳ�����д���
			int frameCount=0;
			for(@SuppressWarnings("unused") JInternalFrame frame:getAllFrames()){
				++frameCount;
			}
			//������Ҫ������,�����вſ���ƽ�����д���
			int rows = (int)Math.sqrt(frameCount);
			int cols = frameCount/rows;
			//��Ҫ�������ӵ��������еĴ���
			int extra = frameCount % rows;
			//����ƽ��ʱ�ڲ����ڵĴ�С
			int width = getWidth()/cols;
			int height = getHeight()/rows;
			//���ڱ���ƽ�̴���ʱÿ�������ں���,�����ϵ�����
			int x=0,y=0;
			for(JInternalFrame frame: getAllFrames()){
				try{
					//ȡ���ڲ����ڵ����,��С��
					frame.setMaximum(false);
					frame.setIcon(false);
					//�����ڷ���ָ��λ��
					frame.reshape(x*width, y*height, width, height);
					++y;
					//ÿ����һ�д���
					if(y==rows){
						//��ʼ�ŷ���һ�д���
						y=0;
						++x;
						//����������Ĵ�����ʣ�µ��������
						//����������ж���Ҫ������һ������
						if(extra == cols -x ){
							++rows;
							height=getHeight()/rows;
						}
					}
				}catch(PropertyVetoException e){e.printStackTrace();}
			}
		}
		
		//ѡ����һ����ͼ�괰��
		public void selectNextWindow(){
			JInternalFrame[] frames = getAllFrames();
			for(int i=0;i<frames.length;++i){
				if(frames[i].isSelected()){
					//�ҳ���һ������С���Ĵ���,����ѡ����
					//���ѡ��ʧ��,���������ѡ����һ������
					int next=(i+1)%frames.length;
					while(next != i){
						//����ô��ڲ��ǳ�����С��״̬
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
