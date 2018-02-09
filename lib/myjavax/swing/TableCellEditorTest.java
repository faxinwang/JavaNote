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
	JFrame jf =new JFrame("ʹ�õ�Ԫ�������");
	JTable table;
	String src="./src/QQIMG/";
	String boy="./src/icon/boy.png";
	String girl="./src/icon/girl.png";
	Object[][] tableData = {
		new Object[]{"������",29,new ImageIcon(girl),new ImageIcon(src+"1.jpg"),true},
		new Object[]{"�ո�����",56,new ImageIcon(boy),new ImageIcon(src+"8.jpg"),false},
		new Object[]{"���",35,new ImageIcon(boy),new ImageIcon(src+"7.jpg"),true},
		new Object[]{"Ū��",18,new ImageIcon(girl),new ImageIcon(src+"5.jpg"),true},
		new Object[]{"��ͷ",2,new ImageIcon(boy),new ImageIcon(src+"4.jpg"),true}
	};
	String[] title={"����","����","�Ա�","��ͷ��","�Ƿ��й���"};
	
	public void init(){
		ExtendedTableModel model = new ExtendedTableModel(title,tableData);
		table = new JTable(model);
		table.setRowHeight(65);
		//Ϊ�ñ������������ΪImageIcon����ָ����Ԫ��༭��
		table.setDefaultEditor(ImageIcon.class, new ImageCellEditor());
		//��ȡ��4��
		TableColumn col4 = table.getColumnModel().getColumn(3);
		//����JComboBox����,����Ӷ��ͼ���б���
		JComboBox<ImageIcon> editCombo = new JComboBox<>();
		for(int i=1;i<10;++i){
			editCombo.addItem(new ImageIcon("./src/QQIMG/"+ i +".jpg"));
		}
		//���õ�5��ʹ�û���JComboBox��DefaultCellEditor
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
//��չDefaultCellEditor��ʵ��TableCellEditor��
class ImageCellEditor extends DefaultCellEditor{
	private JFileChooser fDialog = new JFileChooser();
	private JTextField jtf = new JTextField(15);
	private JButton bn = new JButton("...");
	
	public ImageCellEditor() {
		//��ΪDefaultCellEditorû���޲����Ĺ�����
		//����������ʾ���ø����в����Ĺ�����
		super(new JTextField());
		init();
	}
	private void init(){
		jtf.setEditable(false);
		//Ϊ��ť����¼�������,���û������ð�ťʱ,
		//������һ���ļ�ѡ�������û�ѡ��ͼ���ļ�
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
				return "��Ч��ͼƬ�ļ�";
			}
		});
	}

	//��дTableCellEditor�ӿڵ�getTableCellEditorComponent����
	//�÷������ص�Ԫ��༭��,�ñ༭����һ��JPanel
	//����������һ���ı����һ����ť
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
		//����������ļ�ѡ������"ȡ��"��ť
		if(result==JFileChooser.CANCEL_OPTION){
			//ȡ���༭
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
	//��ȡ�ļ���չ���ķ���
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
