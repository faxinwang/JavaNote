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

/* �������ʾ�������Ϊ��ʽ���ı���������������,����У����,�����Զ�����һ��IP��ַ��ʽ��,
 * ��IP��ַ��ʽ����չ��DefaultFormatter��ʽ��
 */
public class JFormattedTextFieldTest2 {
	private JFrame mainWin = new JFrame("���Ը�ʽ���ı���");
	private JButton okBn = new JButton("ȷ��");
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
		addRow("ֻ�������ֵ��ı���",intField0);
		JFormattedTextField intField1 = new JFormattedTextField(NumberFormat.getInstance());
		intField1.setValue(100);
		//�������У����
		intField1.setInputVerifier(new FormattedTextFieldVerifier());
		addRow("������У�����������ı���",intField1);
		//�����Զ����ʽ������
		IPAddressFormatter ipFormatter = new IPAddressFormatter();
		ipFormatter.setOverwriteMode(false);
		//���Զ����ʽ�����󴴽���ʽ���ı���
		JFormattedTextField ipField = new JFormattedTextField(ipFormatter);
		ipField.setValue(new short[]{192,168,4,1});
		addRow("IP��ַ��ʽ",ipField);
		
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
			//�����ֵ������,��ʹ��Arrays��toString()����
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
				//�����ʽ���ı����ֵ
				valueLabel.setText(value.toString());
			}
		});
	}
	public static void main(String[] args){
		new JFormattedTextFieldTest2().init();
	}
	
}

//����У����
class FormattedTextFieldVerifier extends InputVerifier{
	//���������ʧȥ����ʱ,�����÷���
	@Override
	public boolean verify(JComponent input) {
		JFormattedTextField field = (JFormattedTextField)input;
		return field.isEditValid();
	}
}

//���ֹ�����
class NumberFilter extends DocumentFilter{
	public void insertString(FilterBypass fb,int offset,
			String str,AttributeSet attrs)throws BadLocationException
	{
		StringBuilder builder = new StringBuilder(str);
		//�����û�����������ַ�
		filterInt(builder);
		super.insertString(fb, offset, str, attrs);
	}
	
	public void replace(FilterBypass fb,int offset,int length,String string,
			AttributeSet attrs)throws BadLocationException
	{
		if(string != null){
			StringBuilder builder = new StringBuilder(string);
			//�����û��滻�������ַ�
			filterInt(builder);
			string = builder.toString();
		}
		super.replace(fb, offset, length, string, attrs);
	}
	
	//������������,�����з�0-9���ַ�ȫ��ɾ��
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
			throw new ParseException("��IP��ַ��ֵֻ�����ֽ�����",0);
		}
		short[] a = (short[])value;
		if(a.length != 4){
			throw new ParseException("IP��ַ������4������",0);
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
		//����ʽ���ı��ڵ��ַ����Ե��(.)�ֳ�4�ֽ�
		String[] nums = text.split("\\.");
		if(nums.length != 4){
			throw new ParseException("IP��ַ������4������",0);
		}
		short[] a = new short[4];
		for(int i=0;i<4;++i){
			short b = 0;
			try{
				b=Short.valueOf(nums[i]);
			}catch(NumberFormatException e){
				throw new ParseException("IP��ַ����������",0);
			}
			if(b<0 || b>=256){
				throw new ParseException("IP��ֵַֻ����0~255֮��",0);
			}
			a[i] = b;
		}
		return a;
	}
}