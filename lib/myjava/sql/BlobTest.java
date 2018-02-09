package myjava.sql;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileFilter;

//创建一个ImageHolder类，用于封装图片的id和名字
class ImageHolder{
	//封装图片的id
	private int id;
	//封装图片的名字
	private String name;
	public ImageHolder(int id,String name){
		this.id= id;
		this.name = name;
	}
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public String toString(){
		return name;
	}
}

//创建FileFilter的子类，用于实现文件过滤的功能
class ExtensionFileFilter extends FileFilter{
	private String description;
	private ArrayList<String> extensions = new ArrayList<>();
	//自定义方法，用于添加文件扩展名
	public void addExtension(String ext){
		if(!ext.startsWith(".")){
			ext = "." + ext;
			extensions.add(ext.toLowerCase());
		}
	}
	//用于设置该文本过滤器的描述文本
	public void setDescription(String desc){
		description = desc;
	}
	@Override
	public boolean accept(File f) {
		//如果该文件时路径，则接受该文件
		if(f.isDirectory()) return true;
		String name = f.getName().toLowerCase();
		//遍历所有课接受的文件名，如果扩展名相同，该文件就可接受
		for(String ext:extensions){
			if(name.endsWith(ext)){
				return true;
			}
		}
		return false;
	}
	@Override
	public String getDescription() {
		return description;
	}
}

public class BlobTest {
	JFrame jf = new JFrame("图片管理程序");
	private static Connection conn;
	private static PreparedStatement insert;
	private static PreparedStatement query;
	private static PreparedStatement queryAll;
	//定义一个DefaultListModel对象
	private DefaultListModel<ImageHolder> imgModel 
		= new DefaultListModel<>();
	private JList<ImageHolder> imgList = new JList<>(imgModel);
	private JTextField filePath = new JTextField(26);
	private JButton browserBtn = new JButton("...");
	private JButton uploadBtn = new JButton("上传");
	private JLabel imgLabel = new JLabel();
	//一当前路径创建文件选择器
	JFileChooser fileChooser = new JFileChooser(".");
	//创建文件过滤器
	ExtensionFileFilter  filter = new ExtensionFileFilter();
	//静态初始化块
	static{
		try{
			Properties pps = new Properties();
			pps.load(new FileInputStream("./lib/myjava/sql/mysql_conn_param"));
			String driver = pps.getProperty("driver");
			String url = pps.getProperty("url");
			String user = pps.getProperty("user");
			String pwd = pps.getProperty("pwd");
			Class.forName(driver);
			conn = DriverManager.getConnection(url,user,pwd);
			//创建执行插入语句的PreparedStatement对象,
			//该对象执行插入后可以返回自动生成的主键
			String sql = "insert into img_tb values(null,?,?)";
			insert = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//创建两个PreparedStatement对象,用于查询指定图片，查询所有图片
			sql = "select img_data from img_tb where img_id=?";
			query = conn.prepareStatement(sql);
			sql = "select img_id,img_name from img_tb";
			queryAll = conn.prepareStatement(sql);
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void init()throws SQLException{
		//--------初始化文件选择器------------
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("gif");
		filter.addExtension("png");
		filter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");
		fileChooser.addChoosableFileFilter(filter);
		//禁止"文件类型"下拉列表中显示"所有文件"选项
		fileChooser.setAcceptAllFileFilterUsed(false);
		//-------初始化程序界面----------
		fillListModel();
		filePath.setEditable(false);
		//只能单选
		imgList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel jp = new JPanel();
		jp.add(filePath);
		jp.add(browserBtn);
		jp.add(uploadBtn);
		browserBtn.addActionListener(event ->{
			//显示文件对话框
			int result = fileChooser.showDialog(jf, "浏览图片文件上传");
			//如果用户选择了approve按钮，即打开，保存等效按钮
			if(result == JFileChooser.APPROVE_OPTION)
				filePath.setText(fileChooser.getSelectedFile().getPath());
		});
		uploadBtn.addActionListener(event ->{
			if(filePath.getText().trim().length()>0 ){
				//将指定文件上传到数据库
				upload(filePath.getText());
				//清空文件框内容
				filePath.setText("");
			}
		});
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.add(new JScrollPane(imgLabel),BorderLayout.CENTER);
		left.add(jp, BorderLayout.SOUTH);
		jf.add(left);//center
		imgList.setFixedCellWidth(160);
		jf.add(new JScrollPane(imgList),BorderLayout.EAST);
		imgList.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent event){
				//如果鼠标双加
				if(event.getClickCount() >= 2){
					//取出选中的list项
					ImageHolder curImg = imgList.getSelectedValue();
					try{
						//显示选中项对应的Image
						showImage(curImg.getId());
					}catch(SQLException e){
						System.out.println(e.getMessage());
						e.printStackTrace();
					}
				}
			}
		});
		jf.setSize(620, 400);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	
	//---------查找img_tb填充ListModal----------
	public void fillListModel()throws SQLException{
		try(
			//执行查询
			ResultSet rst = queryAll.executeQuery();
		){
			//先清除所有元素
			imgModel.clear();
			//把查询的全部记录添加到ListModel中
			while(rst.next()){
				imgModel.addElement(new ImageHolder(rst.getInt(1),rst.getString(2)));
			}
		}
	}
	
	//--------将指定图片放入数据库--------------
	public void upload(String fileName){
		//截取文件名
		String imgName = fileName.substring(fileName.lastIndexOf('\\')+1,fileName.lastIndexOf('.'));
		File f = new File(fileName);
		try(
			InputStream fin = new FileInputStream(f);
		){
			//设置图片名参数
			insert.setString(1, imgName);
			insert.setBinaryStream(2, fin,f.length());
			int affect = insert.executeUpdate();
			if(affect==1){
				//重新更新ListModel，将会让JList显示最新的图片列表
				fillListModel();
				//显示刚上传的图片
				ImageIcon icon = new ImageIcon(fileName);
				imgLabel.setIcon(icon);
			}
		}catch(Exception e){
			System.out.println("file:" + fileName + "not found!");
			e.printStackTrace();
		}
	}
	
	//---------根据图片id来显示图片
	public void showImage(int id)throws SQLException{
		//设置参数
		query.setInt(1,id);
		try(
			ResultSet rst = query.executeQuery();
		){
			if(rst.next()){
				//取出Blob列的数据,结果集中只包括这一列数据
				Blob imgBlob = rst.getBlob(1);
				ImageIcon icon = new ImageIcon( imgBlob.getBytes(1L, (int)imgBlob.length()));
				imgLabel.setIcon(icon);
			}
		}
	}
	
	public static void main(String[] args)throws Exception{
		new BlobTest().init();
	}
}
