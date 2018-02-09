package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.MaskFormatter;

/* MaskFormatter格式器的功能有些类似与正则表达式,他要求用户在格式化文本框中输入的内容必须匹配一定的掩码格式
 * #: 代表任何有效数字
 * ': 转译字符,用于转译具有特殊格式的字符,如如想匹配#,则应该写成'#
 * U: 任何字符,将所有小写字母映射为大写字母
 * L: 任何字符,将所有大写字母映射为小写字母
 * A: 任何字符或数字
 * ?: 任何字符
 * *: 可以匹配任何内容
 * H: 任何十六进制字符(0~9,a~f或A~F)
 */

public class JFormattedTextFieldTest {
	private JFrame jf =new JFrame("测试格式化文本框");
	private JButton okBn = new JButton("确定");
	//用于添加格式化文本框的JPanel
	private JPanel mainPanel = new JPanel();
	JFormattedTextField[] fields = new JFormattedTextField[6];
	String[] behaviorLabels ={
		"COMMIT","COMMIT_OR_REVERT","PERSIST","REVERT"
	};
	int[] behaviors ={
		JFormattedTextField.COMMIT,
		JFormattedTextField.COMMIT_OR_REVERT,
		JFormattedTextField.PERSIST,
		JFormattedTextField.REVERT
	};
	ButtonGroup bg =new ButtonGroup();
	
	public void init(){

		//rows:the rows, with the value zero meaning any number of rows.
		mainPanel.setLayout(new GridLayout(0,3));
		jf.add(mainPanel,BorderLayout.CENTER);
		//使用NumberFormat的integerInstance创建一个JFromattedTextField对象
		fields[0] = new JFormattedTextField(NumberFormat.getIntegerInstance());
		//设置初始值
		fields[0].setValue(100);
		addRow("整数格式文本框",fields[0]);
		//使用NumberFormatted的currencyInstance创建一个JFormattedTextField对象
		fields[1] = new JFormattedTextField(NumberFormat.getCurrencyInstance());
		fields[1].setValue(100.0);
		addRow("货币格式文本框",fields[1]);
		//使用默认的日期格式创建一个JFormattedTextField对象
		fields[2]=new JFormattedTextField(DateFormat.getDateInstance());
		fields[2].setValue(new Date());
		addRow("默认的日期格式器",fields[2]);
		//使用SHORT类型的日期格式创建一个JFormattedTextField对象
		//且要求采用严格日期格式
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		//要采用严格的日期格式语法
		format.setLenient(false);
		fields[3] = new JFormattedTextField(format);
		fields[3].setValue(new Date());
		addRow("SHORT类型的日期格式器(语法严格)",fields[3]);
		try{
			//创建默认的DefaultFormatter对象
			DefaultFormatter formatter = new DefaultFormatter();
			//关闭Override状态
			formatter.setOverwriteMode(false);
			fields[4]=new JFormattedTextField(formatter);
			//使用DefaultFormatter来格式化URL
			fields[4].setValue(new URL("http://www.crazyit.org"));
			addRow("URL:",fields[4]);
		}catch(MalformedURLException e){e.printStackTrace();}
		
		try{
			MaskFormatter formatter = new MaskFormatter("020-########");
			//设置占位符
			formatter.setPlaceholderCharacter('□');
			fields[5] = new JFormattedTextField(formatter);
			fields[5].setValue("020-28309738");
			addRow("电话号码:",fields[5]);
		}catch(ParseException e){e.printStackTrace();}
		
		JPanel focusLostPanel = new JPanel();
		for(int i=0;i<behaviorLabels.length;++i){
			final int idx = i;
			final JRadioButton radio = new JRadioButton(behaviorLabels[i]);
			//默认选中第二个按钮
			if(i==1){
				radio.setSelected(true);
			}
			focusLostPanel.add(radio);
			bg.add(radio);
			//为所有的单选按钮添加事件监听器
			radio.addActionListener(evt ->{
				//如果当前该单选按钮处于选中状态
				if(radio.isSelected()){
					//设置所有格式化文本框失去焦点的行为
					for(int j=0;j<fields.length;++j){
						fields[j].setFocusLostBehavior(behaviors[idx]);
					}
				}
			});
		}
		focusLostPanel.setBorder(new TitledBorder(new EtchedBorder(),"请选中失去焦点后的行为"));
		JPanel bnPanel = new JPanel();
		bnPanel.add(okBn);
		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(focusLostPanel);
		box.add(bnPanel);
		jf.add(box, BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	
	private void addRow(String labelText,final JFormattedTextField field){
		mainPanel.add(new JLabel(labelText));
		mainPanel.add(field);
		final JLabel valueLabel = new JLabel();
		mainPanel.add(valueLabel);
		//为"确定"按钮添加事件监听器
		//当用户单击按钮时,文本框后显示文本框的值
		okBn.addActionListener(evt ->{
			Object value = field.getValue();
			//显示格式化文本框的值
			valueLabel.setText(value.toString());
		});
	}
	
	public static void main(String[] args){
		new JFormattedTextFieldTest().init();
	}
	
}
