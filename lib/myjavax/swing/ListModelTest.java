package myjavax.swing;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class ListModelTest {
	private JFrame jf = new JFrame("����ListModel");
	//����NumberListModel����������һ��JList����
	private JList<BigDecimal> numList = new JList<>(new NumberListModel(1,21,2));
	//����NumberComboBoxModel����������JComboBox����
	private JComboBox<BigDecimal> numCombo =
			new JComboBox<>(new NumberComboBoxModel(10,24,1));
	private JTextField showVal = new JTextField(10);
	public void init(){
		//JList�Ŀ��Ӹ߶ȿ�ͬʱ��ʾ4���б���
		numList.setVisible(true);
		//Ĭ��ѡ��������5��
		numList.setSelectionInterval(2, 4);
		//����ÿ���б������ָ���ĸ߶ȺͿ��
		numList.setFixedCellHeight(30);
		numList.setFixedCellWidth(90);
		//ΪnumList��Ӽ�����
		numList.addListSelectionListener(e->{
			//��ȡ�û�ѡ�е���������
			List<BigDecimal> nums = numList.getSelectedValuesList();
			showVal.setText("");
			for(BigDecimal num:nums){
				showVal.setText(showVal.getText()+num.toString()+",");
			}
		});
		//�����б���Ŀ��Ӹ߶ȿ���ʾ5���б���
		numCombo.setMaximumRowCount(5);
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(new JScrollPane(numList));
		JPanel p =new JPanel();
		p.add(numCombo);
		box.add(p);
		//ΪnumCombo��Ӽ�����
		numCombo.addActionListener(evt->{
			//��ȡJComboBox��ѡ�е�����
			Object num = numCombo.getSelectedItem();
			showVal.setText(num.toString());
		});
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("��ѡ�����:"));
		bottom.add(showVal);
		jf.add(box);
		jf.add(bottom,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	public static void main(String[] args){
		new ListModelTest().init();
	}
}


@SuppressWarnings("serial")
class NumberListModel extends AbstractListModel<BigDecimal>{
	protected BigDecimal start;
	protected BigDecimal end;
	protected BigDecimal step;
	public NumberListModel(double start,double end,double step){
		this.start = BigDecimal.valueOf(start);
		this.end = BigDecimal.valueOf(end);
		this.step =BigDecimal.valueOf(step);
	}
	@Override
	public int getSize() {
		return (int)Math.floor(end.subtract(start).divide(step).doubleValue())+1;
	}

	@Override
	public BigDecimal getElementAt(int index) {
		return BigDecimal.valueOf(index).multiply(step).add(start);
	}
}

@SuppressWarnings("serial")
class NumberComboBoxModel extends NumberListModel implements ComboBoxModel<BigDecimal>{
	private int selectId =0;
	public NumberComboBoxModel(double start, double end, double step) {
		super(start, end, step);
	}
	//����ѡ��"ѡ����"
	@Override
	public void setSelectedItem(Object anItem){
		if(anItem instanceof BigDecimal){
			BigDecimal target = (BigDecimal)anItem;
			//����ѡ�����ֵ���޸�ѡ���������
			selectId = target.subtract(start).divide(step).intValue();
		}
	}
	//��ȡѡ�����ֵ
	@Override
	public BigDecimal getSelectedItem(){
		return BigDecimal.valueOf(selectId).multiply(step).add(start);
	}
}