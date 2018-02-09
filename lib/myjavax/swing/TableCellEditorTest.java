package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;

public class TableCellEditorTest {
	JFrame jf =new JFrame("使用单元格绘制器");
	JTable table;
	String src="./src/QQIMG/";
	String boy="./src/icon/boy.png";
	String girl="./src/icon/girl.png";
	Object[][] tableData = {
		new Object[]{"李清照",29,new ImageIcon(girl),new ImageIcon(src+"1.jpg"),true},
		new Object[]{"苏格拉底",56,new ImageIcon(boy),new ImageIcon(src+"8.jpg"),false},
		new Object[]{"李白",35,new ImageIcon(boy),new ImageIcon(src+"7.jpg"),true},
		new Object[]{"弄玉",18,new ImageIcon(girl),new ImageIcon(src+"5.jpg"),true},
		new Object[]{"虎头",2,new ImageIcon(boy),new ImageIcon(src+"4.jpg"),true}
	};
	String[] title={"姓名","年龄","性别","主头像","是否中国人"};
	
	public void init(){
		ExtendedTableModel model = new ExtendedTableModel(title,tableData);
		table = new JTable(model);
		table.setRowHeight(65);
		//为该表格中数据类型为ImageIcon的列指定单元格编辑器
		table.setDefaultEditor(ImageIcon.class, new ImageCellEditor());
		//获取第4列
		TableColumn col4 = table.getColumnModel().getColumn(3);
		//创建JComboBox对象,并添加多个图标列表项
		JComboBox<ImageIcon> editCombo = new JComboBox<>();
		for(int i=1;i<10;++i){
			editCombo.addItem(new ImageIcon("./src/QQIMG/"+ i +".jpg"));
		}
		//设置第5列使用基于JComboBox的DefaultCellEditor
		col4.setCellEditor(new DefaultCellEditor(editCombo));
		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args){
		new TableCellEditorTest().init();
	}
}


@SuppressWarnings("serial")
//扩展DefaultCellEditor来实现TableCellEditor类
class ImageCellEditor extends DefaultCellEditor{
	private JFileChooser fDialog = new JFileChooser();
	private JTextField jtf = new JTextField(15);
	private JButton bn = new JButton("...");
	
	public ImageCellEditor() {
		//因为DefaultCellEditor没有无参数的构造器
		//所以这里显示调用父类有参数的构造器
		super(new JTextField());
		init();
	}
	private void init(){
		jtf.setEditable(false);
		//为按钮添加事件监听器,当用户单击该按钮时,
		//将弹出一个文件选择器让用户选择图标文件
		bn.addActionListener(evt -> browse());
		fDialog.setAcceptAllFileFilterUsed(false);
		fDialog.addChoosableFileFilter(new FileFilter(){

			@Override
			public boolean accept(File f) {
				if(f.isDirectory()) return true;
				String ext = Utils.getExtension(f);
				if(ext != null){
					if( ext.equals(Utils.png) 	||
						ext.equals(Utils.jpeg)	||
						ext.equals(Utils.jpg)	||
						ext.equals(Utils.gif)){
						return true;
					}
				}
				return false;
			}

			@Override
			public String getDescription() {
				return "有效的图片文件";
			}
		});
	}

	//重写TableCellEditor接口的getTableCellEditorComponent方法
	//该方法返回单元格编辑器,该编辑器是一个JPanel
	//该容器包含一个文本框和一个按钮
	public Component getTableCellEditorComponent(JTable table,Object value,
			boolean isSelected,int row,int column)
	{
		bn.setPreferredSize(new Dimension(20,20));
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		jtf.setText(value.toString());
		p.add(jtf);
		p.add(bn,BorderLayout.EAST);
		return p;
	}
	
	public Object getCellEditorValue(){
		return new ImageIcon(jtf.getText());
	}
	
	private void browse(){
		fDialog.setCurrentDirectory(new File("./src/QQIMG/"));
		int result = fDialog.showOpenDialog(null);
		//如果单击了文件选择器的"取消"按钮
		if(result==JFileChooser.CANCEL_OPTION){
			//取消编辑
			super.cancelCellEditing();
			return;
		}else{
			jtf.setText("./src/QQIMG/"+fDialog.getSelectedFile().getName());
		}
	}
}

class Utils{
	public final static String jpeg = "jpeg";
	public final static String jpg = "jpg";
	public final static String png = "png";
	public final static String gif = "gif";
	//获取文件扩展名的方法
	public static String getExtension(File f){
		String ext = null;
		String s = f.getName();
		int i=s.lastIndexOf('.');
		if(i>0 && i<s.length()-1){
			ext = s.substring(i+1).toLowerCase();
		}
		return ext;
	}
}
