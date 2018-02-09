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
	private JFrame jf = new JFrame("测试ListModel");
	//根据NumberListModel对象来创建一个JList对象
	private JList<BigDecimal> numList = new JList<>(new NumberListModel(1,21,2));
	//根据NumberComboBoxModel对象来创建JComboBox对象
	private JComboBox<BigDecimal> numCombo =
			new JComboBox<>(new NumberComboBoxModel(10,24,1));
	private JTextField showVal = new JTextField(10);
	public void init(){
		//JList的可视高度可同时显示4个列表项
		numList.setVisible(true);
		//默认选择第三项到第5项
		numList.setSelectionInterval(2, 4);
		//设置每个列表项具有指定的高度和宽度
		numList.setFixedCellHeight(30);
		numList.setFixedCellWidth(90);
		//为numList添加监听器
		numList.addListSelectionListener(e->{
			//获取用户选中的所有数子
			List<BigDecimal> nums = numList.getSelectedValuesList();
			showVal.setText("");
			for(BigDecimal num:nums){
				showVal.setText(showVal.getText()+num.toString()+",");
			}
		});
		//设置列表项的可视高度可显示5个列表项
		numCombo.setMaximumRowCount(5);
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(new JScrollPane(numList));
		JPanel p =new JPanel();
		p.add(numCombo);
		box.add(p);
		//为numCombo添加监听器
		numCombo.addActionListener(evt->{
			//获取JComboBox中选中的数字
			Object num = numCombo.getSelectedItem();
			showVal.setText(num.toString());
		});
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("你选择的是:"));
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
	//设置选中"选择项"
	@Override
	public void setSelectedItem(Object anItem){
		if(anItem instanceof BigDecimal){
			BigDecimal target = (BigDecimal)anItem;
			//根据选中项的值来修改选中项的索引
			selectId = target.subtract(start).divide(step).intValue();
		}
	}
	//获取选择项的值
	@Override
	public BigDecimal getSelectedItem(){
		return BigDecimal.valueOf(selectId).multiply(step).add(start);
	}
}