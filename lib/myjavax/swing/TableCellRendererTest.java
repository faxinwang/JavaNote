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
	JFrame jf =new JFrame("ʹ�õ�Ԫ�������");
	JTable table;
	String src="./src/QQIMG/";
	Object[][] tableData = {
		new Object[]{"������",29,"Ů",new ImageIcon(src+"1.jpg"),true},
		new Object[]{"�ո�����",56,"��",new ImageIcon(src+"8.jpg"),false},
		new Object[]{"���",35,"��",new ImageIcon(src+"7.jpg"),true},
		new Object[]{"Ū��",18,"Ů",new ImageIcon(src+"5.jpg"),true},
		new Object[]{"��ͷ",2,"��",new ImageIcon(src+"4.jpg"),true}
	};
	String[] title={"����","����","�Ա�","��ͷ��","�Ƿ��й���"};
	public void init(){
		ExtendedTableModel model = new ExtendedTableModel(title,tableData);
		table = new JTable(model);
		table.setRowHeight(60);
		//��ȡ������
		TableColumn thirdCol = table.getColumnModel().getColumn(2);
		//�Ե����в����Զ���ĵ�Ԫ�������
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
	
	//�����ṩһ��������,�ù�������ʵ��ί�и�DefaultTableModel����
	public ExtendedTableModel(String[] colNames,Object[][]cells){
		super(cells,colNames);
	}
	//��дgetColumnClass����,����ÿ�����ݵĵ�һ��ֵ����������ʵ����������
	public Class<?> getColumnClass(int  c){
		return getValueAt(0,c).getClass();
	}
}

@SuppressWarnings("serial")
class GenderTableCellRenderer extends JPanel implements TableCellRenderer{
	private String cellValue;
	private Image boy,girl;
	//����ͼ��Ŀ�Ⱥ͸߶�
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
		//����ѡ��״̬�µı߿�
		if(hasFocus){
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		}else{
			setBorder(null);
		}
		return this;
	}
	public void paint(Graphics g){
			if(cellValue.equals("��") && boy!=null){
				drawImage(g,boy);
			}else if(girl!=null){
				drawImage(g,girl);
			}
	}
	//����ͼ��ķ���
	private void drawImage(Graphics g,Image image){
		g.drawImage(image, (getWidth()-ICON_WIDTH)/2, (getHeight()-ICON_HEIGHT)/2, null);
	}
}