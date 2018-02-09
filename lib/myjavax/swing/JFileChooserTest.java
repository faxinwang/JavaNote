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

/* 创建了JFileChooser后,可以调用一系列方法对其进行初始化操作
 * setCurrentDirectory(File dir):设置当前路径.
 * setSelectedFile()/setSelectedFiles():默认选择当前路径下的指定文件,
 * 		如:chooser.setSelectedFile(new File("123.jpg"));
 * setMultiSelectionEnabled(boolean b):默认情况下,文件选择器只能选择一个文件,该方法可设置为可以选择多个文件
 * setFileSelectionMode(int mode):可选值有JFileChooser.FILES_ONLY(默认值),
 * 			JFileChooser.DIRECTORIES_ONLY,
 * 			JFileChooser.FILES_AND_DIRECTORIES
 * 
 */

public class JFileChooserTest {
	String src ="./src/icon/";
	//定义图片预览组件的大小
	final int PREVIEW_SIZE = 100;
	JFrame jf = new JFrame("图片查看器");
	JMenuBar jmb = new JMenuBar();
	JMenuItem open_item = new JMenuItem("open",new ImageIcon(src+"open.png"));
	//该Label用于显示图片
	JLabel label = new JLabel();
	//以当前路径创建文件选择器
	JFileChooser chooser = new JFileChooser();
	JLabel accessory = new JLabel();
	//定义文件过滤器
	ExtensionFileFilter filter = new ExtensionFileFilter();
	
	public void init(){
		//----------下面开始初始化JFileChooser相关属性--------------//
		filter.addExtension(".jpg");
		filter.addExtension(".jpeg");
		filter.addExtension("gif");
		filter.addExtension(".png");
		filter.setDescription("图片文件(*.jpg,*jpeg,*.gif,*png)");
		//禁止"文件类型"下拉列表中显示"所有文件"选项
		chooser.setAcceptAllFileFilterUsed(false);
		//为文件选择器指定自定义的FileView对象
		chooser.setFileView(new FileIconView(filter));
		//设置文件过滤器
		chooser.setFileFilter(filter);
		//设置选择模式为既可以选择文件又可以选择文件夹
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		//设置可以选择多个文件或文件夹
		chooser.setMultiSelectionEnabled(true);
		//为文件选择器指定一个预览图片的附件(JComponent)
		chooser.setAccessory(accessory);
		//设置预览图片的大小和边框
		accessory.setPreferredSize(new Dimension(PREVIEW_SIZE,PREVIEW_SIZE));
		accessory.setBorder(BorderFactory.createEtchedBorder());

		//---------下面开始为窗口安装菜单--------------------/
		JMenu file_mu = new JMenu("文件");
		file_mu.add(open_item);
		JMenuItem exit_item = new JMenuItem("Exit",new ImageIcon(src+"exit.png"));
		exit_item.addActionListener(event -> System.exit(0));
		file_mu.add(exit_item);
		jmb.add(file_mu);
		
		//右键菜单可添加到JPanel上
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
		//用于检测被选择文件的改变事件
		chooser.addPropertyChangeListener(event ->{
			//JFileChooser的被选文件已经发生了改变
			if(event.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY){
				//获取用户选择的文件
				File f = (File)event.getNewValue();
				if(f==null){
					accessory.setIcon(null);
					return ;
				}
				//将所选文件读入ImageIcon中
				ImageIcon icon = new ImageIcon(f.getPath());
				System.out.println(f.getPath());
				//如果图像太大,则压缩它
				if(icon.getIconWidth() > PREVIEW_SIZE){
					icon = new ImageIcon(icon.getImage()
							.getScaledInstance(PREVIEW_SIZE, -1, Image.SCALE_DEFAULT));
				}
				//改变accessory Label的图标
				accessory.setIcon(icon);
			}
		});
		
		//单击open_item菜单项是显示"打开文件"对话框
		open_item.addActionListener(event ->{
			//设置文件对话框的当前路径
			chooser.setCurrentDirectory(new File("."));
			//设置默认选择当前路径下的文件或文件夹
			chooser.setSelectedFile(new File("src"));
			//显示文件对话框
			int result = chooser.showDialog(jf, "打开图片文件");
			//如果用户选择了APPROVE按钮,即代表打开,保存等的按钮
			if(result==JFileChooser.APPROVE_OPTION){
				String name = chooser.getSelectedFile().getPath();
				System.out.println(name);
				label.setIcon(new ImageIcon(name));
			}
		});
	}
	
	//定义一个FileView类,用于为指定类型的文件或文件夹设置图标
	class FileIconView extends FileView{
		private FileFilter filter;
		public FileIconView(FileFilter filter){
			this.filter = filter;
		}
		//重写该方法,为文件夹,文件设置图标
		public Icon getIcon(File f){
			//如果是filter接受的文件,则用img.png图标显示
			if(!f.isDirectory() && filter.accept(f)){
				return new ImageIcon(src+"img.png");
			}
			else if(f.isDirectory()){
				//获取所有根路径
				File[] roots = File.listRoots();
				for(File rt:roots){
					//如果f是根路径
					if(rt.equals(f)){
						//如果是硬盘,则用dsk.png图标显示
						return new ImageIcon(src+"dsk.png");
					}
				}
				//如果是普通文件夹,则用folder.png图标显示
				return new ImageIcon(src+"folder.png");
			}
			//使用默认图标
			return null;
		}
	}
	
	//创建FileFilter的子类,用以实现文件过滤功能
	class ExtensionFileFilter extends FileFilter{
		private String description;
		private ArrayList<String> extensions = new ArrayList<>();
		//自定义方法,用于添加文件扩展名
		public void addExtension(String extension){
			if(!extension.startsWith(".")){
				extension= "." + extension;
			}
			extensions.add(extension);
		}
		//判断该文件过滤器是否接受指定的文件
		@Override
		public boolean accept(File f) {
			//接受文件夹
			if(f.isDirectory()) return true;
			//忽略文件名大小写比较文件
			String name = f.getName().toLowerCase();
			//遍历所有可接受的扩展文件名,如果包含该文件名的扩展文件名,说明该文件可接受
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
		
		//用于设置该文件过滤器的描述文本
		@Override
		public String getDescription() {
			return description;
		}
	}

	public static void main(String[] args){
		new JFileChooserTest().init();
	}
}
