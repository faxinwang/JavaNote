package myjavax.swing;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class TableCellRendererTest {
	JFrame jf =new JFrame("使用单元格绘制器");
	JTable table;
	String src="./src/QQIMG/";
	Object[][] tableData = {
		new Object[]{"李清照",29,"女",new ImageIcon(src+"1.jpg"),true},
		new Object[]{"苏格拉底",56,"男",new ImageIcon(src+"8.jpg"),false},
		new Object[]{"李白",35,"男",new ImageIcon(src+"7.jpg"),true},
		new Object[]{"弄玉",18,"女",new ImageIcon(src+"5.jpg"),true},
		new Object[]{"虎头",2,"男",new ImageIcon(src+"4.jpg"),true}
	};
	String[] title={"姓名","年龄","性别","主头像","是否中国人"};
	public void init(){
		ExtendedTableModel model = new ExtendedTableModel(title,tableData);
		table = new JTable(model);
		table.setRowHeight(60);
		//获取第三列
		TableColumn thirdCol = table.getColumnModel().getColumn(2);
		//对第三列采用自定义的单元格绘制器
		thirdCol.setCellRenderer(new GenderTableCellRenderer());
		jf.add(new JScrollPane(table));
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
	}
	public static void main(String[] args){
		new TableCellRendererTest().init();
	}
}


@SuppressWarnings("serial")
class ExtendedTableModel extends DefaultTableModel{
	
	//重新提供一个构造器,该构造器的实现委托给DefaultTableModel父类
	public ExtendedTableModel(String[] colNames,Object[][]cells){
		super(cells,colNames);
	}
	//重写getColumnClass方法,根据每列数据的第一个值来返回其真实的数据类型
	public Class<?> getColumnClass(int  c){
		return getValueAt(0,c).getClass();
	}
}

@SuppressWarnings("serial")
class GenderTableCellRenderer extends JPanel implements TableCellRenderer{
	private String cellValue;
	private Image boy,girl;
	//定义图标的宽度和高度
	final int ICON_WIDTH=60;
	final int ICON_HEIGHT=60;
	public GenderTableCellRenderer(){
		try{
			boy = ImageIO.read(new File("./src/icon/boy.png"));
			girl= ImageIO.read(new File("./src/icon/girl.png"));
		}catch(IOException e){e.printStackTrace();}
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus,int row, int column) 
	{
		cellValue = (String)value;
		//设置选中状态下的边框
		if(hasFocus){
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		}else{
			setBorder(null);
		}
		return this;
	}
	public void paint(Graphics g){
			if(cellValue.equals("男") && boy!=null){
				drawImage(g,boy);
			}else if(girl!=null){
				drawImage(g,girl);
			}
	}
	//绘制图标的方法
	private void drawImage(Graphics g,Image image){
		g.drawImage(image, (getWidth()-ICON_WIDTH)/2, (getHeight()-ICON_HEIGHT)/2, null);
	}
}