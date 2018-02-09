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

//����һ��ImageHolder�࣬���ڷ�װͼƬ��id������
class ImageHolder{
	//��װͼƬ��id
	private int id;
	//��װͼƬ������
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

//����FileFilter�����࣬����ʵ���ļ����˵Ĺ���
class ExtensionFileFilter extends FileFilter{
	private String description;
	private ArrayList<String> extensions = new ArrayList<>();
	//�Զ��巽������������ļ���չ��
	public void addExtension(String ext){
		if(!ext.startsWith(".")){
			ext = "." + ext;
			extensions.add(ext.toLowerCase());
		}
	}
	//�������ø��ı��������������ı�
	public void setDescription(String desc){
		description = desc;
	}
	@Override
	public boolean accept(File f) {
		//������ļ�ʱ·��������ܸ��ļ�
		if(f.isDirectory()) return true;
		String name = f.getName().toLowerCase();
		//�������пν��ܵ��ļ����������չ����ͬ�����ļ��Ϳɽ���
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
	JFrame jf = new JFrame("ͼƬ�������");
	private static Connection conn;
	private static PreparedStatement insert;
	private static PreparedStatement query;
	private static PreparedStatement queryAll;
	//����һ��DefaultListModel����
	private DefaultListModel<ImageHolder> imgModel 
		= new DefaultListModel<>();
	private JList<ImageHolder> imgList = new JList<>(imgModel);
	private JTextField filePath = new JTextField(26);
	private JButton browserBtn = new JButton("...");
	private JButton uploadBtn = new JButton("�ϴ�");
	private JLabel imgLabel = new JLabel();
	//һ��ǰ·�������ļ�ѡ����
	JFileChooser fileChooser = new JFileChooser(".");
	//�����ļ�������
	ExtensionFileFilter  filter = new ExtensionFileFilter();
	//��̬��ʼ����
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
			//����ִ�в�������PreparedStatement����,
			//�ö���ִ�в������Է����Զ����ɵ�����
			String sql = "insert into img_tb values(null,?,?)";
			insert = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			//��������PreparedStatement����,���ڲ�ѯָ��ͼƬ����ѯ����ͼƬ
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
		//--------��ʼ���ļ�ѡ����------------
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("gif");
		filter.addExtension("png");
		filter.setDescription("ͼƬ�ļ�(*.jpg,*.jpeg,*.gif,*.png)");
		fileChooser.addChoosableFileFilter(filter);
		//��ֹ"�ļ�����"�����б�����ʾ"�����ļ�"ѡ��
		fileChooser.setAcceptAllFileFilterUsed(false);
		//-------��ʼ���������----------
		fillListModel();
		filePath.setEditable(false);
		//ֻ�ܵ�ѡ
		imgList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JPanel jp = new JPanel();
		jp.add(filePath);
		jp.add(browserBtn);
		jp.add(uploadBtn);
		browserBtn.addActionListener(event ->{
			//��ʾ�ļ��Ի���
			int result = fileChooser.showDialog(jf, "���ͼƬ�ļ��ϴ�");
			//����û�ѡ����approve��ť�����򿪣������Ч��ť
			if(result == JFileChooser.APPROVE_OPTION)
				filePath.setText(fileChooser.getSelectedFile().getPath());
		});
		uploadBtn.addActionListener(event ->{
			if(filePath.getText().trim().length()>0 ){
				//��ָ���ļ��ϴ������ݿ�
				upload(filePath.getText());
				//����ļ�������
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
				//������˫��
				if(event.getClickCount() >= 2){
					//ȡ��ѡ�е�list��
					ImageHolder curImg = imgList.getSelectedValue();
					try{
						//��ʾѡ�����Ӧ��Image
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
	
	//---------����img_tb���ListModal----------
	public void fillListModel()throws SQLException{
		try(
			//ִ�в�ѯ
			ResultSet rst = queryAll.executeQuery();
		){
			//���������Ԫ��
			imgModel.clear();
			//�Ѳ�ѯ��ȫ����¼��ӵ�ListModel��
			while(rst.next()){
				imgModel.addElement(new ImageHolder(rst.getInt(1),rst.getString(2)));
			}
		}
	}
	
	//--------��ָ��ͼƬ�������ݿ�--------------
	public void upload(String fileName){
		//��ȡ�ļ���
		String imgName = fileName.substring(fileName.lastIndexOf('\\')+1,fileName.lastIndexOf('.'));
		File f = new File(fileName);
		try(
			InputStream fin = new FileInputStream(f);
		){
			//����ͼƬ������
			insert.setString(1, imgName);
			insert.setBinaryStream(2, fin,f.length());
			int affect = insert.executeUpdate();
			if(affect==1){
				//���¸���ListModel��������JList��ʾ���µ�ͼƬ�б�
				fillListModel();
				//��ʾ���ϴ���ͼƬ
				ImageIcon icon = new ImageIcon(fileName);
				imgLabel.setIcon(icon);
			}
		}catch(Exception e){
			System.out.println("file:" + fileName + "not found!");
			e.printStackTrace();
		}
	}
	
	//---------����ͼƬid����ʾͼƬ
	public void showImage(int id)throws SQLException{
		//���ò���
		query.setInt(1,id);
		try(
			ResultSet rst = query.executeQuery();
		){
			if(rst.next()){
				//ȡ��Blob�е�����,�������ֻ������һ������
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
