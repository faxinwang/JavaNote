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

/* MaskFormatter��ʽ���Ĺ�����Щ������������ʽ,��Ҫ���û��ڸ�ʽ���ı�������������ݱ���ƥ��һ���������ʽ
 * #: �����κ���Ч����
 * ': ת���ַ�,����ת����������ʽ���ַ�,������ƥ��#,��Ӧ��д��'#
 * U: �κ��ַ�,������Сд��ĸӳ��Ϊ��д��ĸ
 * L: �κ��ַ�,�����д�д��ĸӳ��ΪСд��ĸ
 * A: �κ��ַ�������
 * ?: �κ��ַ�
 * *: ����ƥ���κ�����
 * H: �κ�ʮ�������ַ�(0~9,a~f��A~F)
 */

public class JFormattedTextFieldTest {
	private JFrame jf =new JFrame("���Ը�ʽ���ı���");
	private JButton okBn = new JButton("ȷ��");
	//������Ӹ�ʽ���ı����JPanel
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
		//ʹ��NumberFormat��integerInstance����һ��JFromattedTextField����
		fields[0] = new JFormattedTextField(NumberFormat.getIntegerInstance());
		//���ó�ʼֵ
		fields[0].setValue(100);
		addRow("������ʽ�ı���",fields[0]);
		//ʹ��NumberFormatted��currencyInstance����һ��JFormattedTextField����
		fields[1] = new JFormattedTextField(NumberFormat.getCurrencyInstance());
		fields[1].setValue(100.0);
		addRow("���Ҹ�ʽ�ı���",fields[1]);
		//ʹ��Ĭ�ϵ����ڸ�ʽ����һ��JFormattedTextField����
		fields[2]=new JFormattedTextField(DateFormat.getDateInstance());
		fields[2].setValue(new Date());
		addRow("Ĭ�ϵ����ڸ�ʽ��",fields[2]);
		//ʹ��SHORT���͵����ڸ�ʽ����һ��JFormattedTextField����
		//��Ҫ������ϸ����ڸ�ʽ
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		//Ҫ�����ϸ�����ڸ�ʽ�﷨
		format.setLenient(false);
		fields[3] = new JFormattedTextField(format);
		fields[3].setValue(new Date());
		addRow("SHORT���͵����ڸ�ʽ��(�﷨�ϸ�)",fields[3]);
		try{
			//����Ĭ�ϵ�DefaultFormatter����
			DefaultFormatter formatter = new DefaultFormatter();
			//�ر�Override״̬
			formatter.setOverwriteMode(false);
			fields[4]=new JFormattedTextField(formatter);
			//ʹ��DefaultFormatter����ʽ��URL
			fields[4].setValue(new URL("http://www.crazyit.org"));
			addRow("URL:",fields[4]);
		}catch(MalformedURLException e){e.printStackTrace();}
		
		try{
			MaskFormatter formatter = new MaskFormatter("020-########");
			//����ռλ��
			formatter.setPlaceholderCharacter('��');
			fields[5] = new JFormattedTextField(formatter);
			fields[5].setValue("020-28309738");
			addRow("�绰����:",fields[5]);
		}catch(ParseException e){e.printStackTrace();}
		
		JPanel focusLostPanel = new JPanel();
		for(int i=0;i<behaviorLabels.length;++i){
			final int idx = i;
			final JRadioButton radio = new JRadioButton(behaviorLabels[i]);
			//Ĭ��ѡ�еڶ�����ť
			if(i==1){
				radio.setSelected(true);
			}
			focusLostPanel.add(radio);
			bg.add(radio);
			//Ϊ���еĵ�ѡ��ť����¼�������
			radio.addActionListener(evt ->{
				//�����ǰ�õ�ѡ��ť����ѡ��״̬
				if(radio.isSelected()){
					//�������и�ʽ���ı���ʧȥ�������Ϊ
					for(int j=0;j<fields.length;++j){
						fields[j].setFocusLostBehavior(behaviors[idx]);
					}
				}
			});
		}
		focusLostPanel.setBorder(new TitledBorder(new EtchedBorder(),"��ѡ��ʧȥ��������Ϊ"));
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
		//Ϊ"ȷ��"��ť����¼�������
		//���û�������ťʱ,�ı������ʾ�ı����ֵ
		okBn.addActionListener(evt ->{
			Object value = field.getValue();
			//��ʾ��ʽ���ı����ֵ
			valueLabel.setText(value.toString());
		});
	}
	
	public static void main(String[] args){
		new JFormattedTextFieldTest().init();
	}
	
}
