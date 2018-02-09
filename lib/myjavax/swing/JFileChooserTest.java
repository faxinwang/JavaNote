package myjavax.swing;

import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

/* ������JFileChooser��,���Ե���һϵ�з���������г�ʼ������
 * setCurrentDirectory(File dir):���õ�ǰ·��.
 * setSelectedFile()/setSelectedFiles():Ĭ��ѡ��ǰ·���µ�ָ���ļ�,
 * 		��:chooser.setSelectedFile(new File("123.jpg"));
 * setMultiSelectionEnabled(boolean b):Ĭ�������,�ļ�ѡ����ֻ��ѡ��һ���ļ�,�÷���������Ϊ����ѡ�����ļ�
 * setFileSelectionMode(int mode):��ѡֵ��JFileChooser.FILES_ONLY(Ĭ��ֵ),
 * 			JFileChooser.DIRECTORIES_ONLY,
 * 			JFileChooser.FILES_AND_DIRECTORIES
 * 
 */

public class JFileChooserTest {
	String src ="./src/icon/";
	//����ͼƬԤ������Ĵ�С
	final int PREVIEW_SIZE = 100;
	JFrame jf = new JFrame("ͼƬ�鿴��");
	JMenuBar jmb = new JMenuBar();
	JMenuItem open_item = new JMenuItem("open",new ImageIcon(src+"open.png"));
	//��Label������ʾͼƬ
	JLabel label = new JLabel();
	//�Ե�ǰ·�������ļ�ѡ����
	JFileChooser chooser = new JFileChooser();
	JLabel accessory = new JLabel();
	//�����ļ�������
	ExtensionFileFilter filter = new ExtensionFileFilter();
	
	public void init(){
		//----------���濪ʼ��ʼ��JFileChooser�������--------------//
		filter.addExtension(".jpg");
		filter.addExtension(".jpeg");
		filter.addExtension("gif");
		filter.addExtension(".png");
		filter.setDescription("ͼƬ�ļ�(*.jpg,*jpeg,*.gif,*png)");
		//��ֹ"�ļ�����"�����б�����ʾ"�����ļ�"ѡ��
		chooser.setAcceptAllFileFilterUsed(false);
		//Ϊ�ļ�ѡ����ָ���Զ����FileView����
		chooser.setFileView(new FileIconView(filter));
		//�����ļ�������
		chooser.setFileFilter(filter);
		//����ѡ��ģʽΪ�ȿ���ѡ���ļ��ֿ���ѡ���ļ���
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//���ÿ���ѡ�����ļ����ļ���
		chooser.setMultiSelectionEnabled(true);
		//Ϊ�ļ�ѡ����ָ��һ��Ԥ��ͼƬ�ĸ���(JComponent)
		chooser.setAccessory(accessory);
		//����Ԥ��ͼƬ�Ĵ�С�ͱ߿�
		accessory.setPreferredSize(new Dimension(PREVIEW_SIZE,PREVIEW_SIZE));
		accessory.setBorder(BorderFactory.createEtchedBorder());

		//---------���濪ʼΪ���ڰ�װ�˵�--------------------/
		JMenu file_mu = new JMenu("�ļ�");
		file_mu.add(open_item);
		JMenuItem exit_item = new JMenuItem("Exit",new ImageIcon(src+"exit.png"));
		exit_item.addActionListener(event -> System.exit(0));
		file_mu.add(exit_item);
		jmb.add(file_mu);
		
		//�Ҽ��˵�����ӵ�JPanel��
		JPanel pl = new JPanel();
		pl.setComponentPopupMenu(new MyUIManager(jf,jmb).getJPopupMenu());
		jf.add(pl);
		
		jf.setJMenuBar(jmb);
		jf.setPreferredSize(new Dimension(300,200));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		
		
		add_actions();
	}
	
	private void add_actions(){
		//���ڼ�ⱻѡ���ļ��ĸı��¼�
		chooser.addPropertyChangeListener(event ->{
			//JFileChooser�ı�ѡ�ļ��Ѿ������˸ı�
			if(event.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
				//��ȡ�û�ѡ����ļ�
				File f = (File)event.getNewValue();
				if(f==null){
					accessory.setIcon(null);
					return ;
				}
				//����ѡ�ļ�����ImageIcon��
				ImageIcon icon = new ImageIcon(f.getPath());
				System.out.println(f.getPath());
				//���ͼ��̫��,��ѹ����
				if(icon.getIconWidth() > PREVIEW_SIZE){
					icon = new ImageIcon(icon.getImage()
							.getScaledInstance(PREVIEW_SIZE, -1, Image.SCALE_DEFAULT));
				}
				//�ı�accessory Label��ͼ��
				accessory.setIcon(icon);
			}
		});
		
		//����open_item�˵�������ʾ"���ļ�"�Ի���
		open_item.addActionListener(event ->{
			//�����ļ��Ի���ĵ�ǰ·��
			chooser.setCurrentDirectory(new File("."));
			//����Ĭ��ѡ��ǰ·���µ��ļ����ļ���
			chooser.setSelectedFile(new File("src"));
			//��ʾ�ļ��Ի���
			int result = chooser.showDialog(jf, "��ͼƬ�ļ�");
			//����û�ѡ����APPROVE��ť,�������,����ȵİ�ť
			if(result==JFileChooser.APPROVE_OPTION){
				String name = chooser.getSelectedFile().getPath();
				System.out.println(name);
				label.setIcon(new ImageIcon(name));
			}
		});
	}
	
	//����һ��FileView��,����Ϊָ�����͵��ļ����ļ�������ͼ��
	class FileIconView extends FileView{
		private FileFilter filter;
		public FileIconView(FileFilter filter){
			this.filter = filter;
		}
		//��д�÷���,Ϊ�ļ���,�ļ�����ͼ��
		public Icon getIcon(File f){
			//�����filter���ܵ��ļ�,����img.pngͼ����ʾ
			if(!f.isDirectory() && filter.accept(f)){
				return new ImageIcon(src+"img.png");
			}
			else if(f.isDirectory()){
				//��ȡ���и�·��
				File[] roots = File.listRoots();
				for(File rt:roots){
					//���f�Ǹ�·��
					if(rt.equals(f)){
						//�����Ӳ��,����dsk.pngͼ����ʾ
						return new ImageIcon(src+"dsk.png");
					}
				}
				//�������ͨ�ļ���,����folder.pngͼ����ʾ
				return new ImageIcon(src+"folder.png");
			}
			//ʹ��Ĭ��ͼ��
			return null;
		}
	}
	
	//����FileFilter������,����ʵ���ļ����˹���
	class ExtensionFileFilter extends FileFilter{
		private String description;
		private ArrayList<String> extensions = new ArrayList<>();
		//�Զ��巽��,��������ļ���չ��
		public void addExtension(String extension){
			if(!extension.startsWith(".")){
				extension= "." + extension;
			}
			extensions.add(extension);
		}
		//�жϸ��ļ��������Ƿ����ָ�����ļ�
		@Override
		public boolean accept(File f) {
			//�����ļ���
			if(f.isDirectory()) return true;
			//�����ļ�����Сд�Ƚ��ļ�
			String name = f.getName().toLowerCase();
			//�������пɽ��ܵ���չ�ļ���,����������ļ�������չ�ļ���,˵�����ļ��ɽ���
			for(String ext : extensions){
				if(name.endsWith(ext)){
					return true;
				}
			}
			return false;
		}
		
		public void setDescription(String desc){
			description = desc;
		}
		
		//�������ø��ļ��������������ı�
		@Override
		public String getDescription() {
			return description;
		}
	}

	public static void main(String[] args){
		new JFileChooserTest().init();
	}
}
