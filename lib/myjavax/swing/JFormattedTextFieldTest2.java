package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;

/* 下面程序示范了如何为格式化文本框添加输入过滤器,输入校验其,程序还自定义了一个IP地址格式器,
 * 该IP地址格式器扩展了DefaultFormatter格式器
 */
public class JFormattedTextFieldTest2 {
	private JFrame mainWin = new JFrame("测试格式化文本框");
	private JButton okBn = new JButton("确定");
	private JPanel mainPanel =new JPanel();
	@SuppressWarnings("serial")
	public void init(){
		JPanel bnPanel = new JPanel();
		bnPanel.add(okBn);
		mainWin.add(bnPanel,BorderLayout.SOUTH);
		mainPanel.setLayout(new GridLayout(0,3));;
		mainWin.add(mainPanel);
		JFormattedTextField intField0 =new JFormattedTextField(
			new InternationalFormatter(NumberFormat.getIntegerInstance()){
				@SuppressWarnings("unused")
				protected DocumentFilter getDoucmentFilter(){
					return new NumberFilter();
				}
			});
		intField0.setValue(100);
		addRow("只接受数字的文本框",intField0);
		JFormattedTextField intField1 = new JFormattedTextField(NumberFormat.getInstance());
		intField1.setValue(100);
		//添加输入校验器
		intField1.setInputVerifier(new FormattedTextFieldVerifier());
		addRow("带输入校验器的输入文本框",intField1);
		//创建自定义格式器对象
		IPAddressFormatter ipFormatter = new IPAddressFormatter();
		ipFormatter.setOverwriteMode(false);
		//以自定义格式器对象创建格式化文本框
		JFormattedTextField ipField = new JFormattedTextField(ipFormatter);
		ipField.setValue(new short[]{192,168,4,1});
		addRow("IP地址格式",ipField);
		
		mainWin.pack();
		mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWin.setVisible(true);
	}
	
	private void addRow(String labelText,final JFormattedTextField field){
		mainPanel.add(new JLabel(labelText));
		mainPanel.add(field);
		final JLabel valueLabel = new JLabel();
		mainPanel.add(valueLabel);
		okBn.addActionListener( evt ->{
			Object value = field.getValue();
			//如果该值是数组,则使用Arrays的toString()方法
			if(value.getClass().isArray()){
				StringBuilder builder = new StringBuilder();
				builder.append('{');
				for(int i=0;i<Array.getLength(value);++i){
					if(i>0) builder.append(',');
					short a = (short)Array.get(value, i);
//					if(a<0) a+=256;
					builder.append(String.valueOf(a));
				}
				builder.append('}');
				valueLabel.setText(builder.toString());
			}else{
				//输出格式化文本框的值
				valueLabel.setText(value.toString());
			}
		});
	}
	public static void main(String[] args){
		new JFormattedTextFieldTest2().init();
	}
	
}

//输入校验器
class FormattedTextFieldVerifier extends InputVerifier{
	//当输入组件失去焦点时,触发该方法
	@Override
	public boolean verify(JComponent input) {
		JFormattedTextField field = (JFormattedTextField)input;
		return field.isEditValid();
	}
}

//数字过滤器
class NumberFilter extends DocumentFilter{
	public void insertString(FilterBypass fb,int offset,
			String str,AttributeSet attrs)throws BadLocationException
	{
		StringBuilder builder = new StringBuilder(str);
		//过滤用户输入的所有字符
		filterInt(builder);
		super.insertString(fb, offset, str, attrs);
	}
	
	public void replace(FilterBypass fb,int offset,int length,String string,
			AttributeSet attrs)throws BadLocationException
	{
		if(string != null){
			StringBuilder builder = new StringBuilder(string);
			//过滤用户替换的所有字符
			filterInt(builder);
			string = builder.toString();
		}
		super.replace(fb, offset, length, string, attrs);
	}
	
	//过滤整数数字,把所有非0-9的字符全部删除
	private void filterInt(StringBuilder builder){
		for(int i=0;i<builder.length();++i){
			int cp = builder.codePointAt(i);
			if(cp>'9' || cp<'0'){
				builder.deleteCharAt(i);
			}
		}
	}
}

@SuppressWarnings("serial")
class IPAddressFormatter extends DefaultFormatter{
	public String valueToString(Object value)throws ParseException{
		if(!(value instanceof short[])){
			throw new ParseException("该IP地址的值只能是字节数组",0);
		}
		short[] a = (short[])value;
		if(a.length != 4){
			throw new ParseException("IP地址必须是4个整数",0);
		}
		StringBuilder builder =new StringBuilder();
		for(int i=0;i<4;++i){
			short b = a[i];
//			if(b<0) b+=256;
			builder.append(String.valueOf(b));
			if(i<3) builder.append('.');
		}
		return builder.toString();
	}
	public Object stringToValue(String text)throws ParseException{
		//将格式化文本内的字符串以点号(.)分成4字节
		String[] nums = text.split("\\.");
		if(nums.length != 4){
			throw new ParseException("IP地址必须是4个整数",0);
		}
		short[] a = new short[4];
		for(int i=0;i<4;++i){
			short b = 0;
			try{
				b=Short.valueOf(nums[i]);
			}catch(NumberFormatException e){
				throw new ParseException("IP地址必须是整数",0);
			}
			if(b<0 || b>=256){
				throw new ParseException("IP地址值只能在0~255之间",0);
			}
			a[i] = b;
		}
		return a;
	}
}