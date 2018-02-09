package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

public class JSpinnerTest {
	final int SPINNER_NUM = 6;
	JFrame jf = new JFrame("΢��������ʾ��");
	Box spinnerBox = new Box(BoxLayout.Y_AXIS);
	JSpinner[] spinners = new JSpinner[SPINNER_NUM];
	JLabel[] valLabels = new JLabel[SPINNER_NUM];
	JButton okBn = new JButton(new ImageIcon("./src/icon/ok.png"));
	public void init(){
		for(int i=0;i<SPINNER_NUM;++i){
			valLabels[i] = new JLabel();
		}
		//----------��ͨJSpinner
		spinners[0] = new JSpinner();
		addSpinner(spinners[0],"��ͨ",valLabels[0]);
		//----------ָ����Сֵ,���ֵ,������JSpinner
		//����һ��SpinnerNumberModel����,ָ����Сֵ,���ֵ,����
		SpinnerNumberModel numModel = new SpinnerNumberModel(3.4,-1.1,4.3,0.1);
		spinners[1] = new JSpinner(numModel);
		addSpinner(spinners[1],"��ֵ��Χ",valLabels[1]);
		//---------ʹ��SpinnerListModel��JSpinner
		String[] books =new String[]{
			"������Java EE��ҵӦ��ʵս","���java����","���android����"
		};
		//ʹ���ַ������鴴��SpinnerListModel����
		SpinnerListModel bookModel = new SpinnerListModel(books);
		//ʹ��SpinnerListModel���󴴽�JSpinner����
		spinners[2] = new JSpinner(bookModel);
		addSpinner(spinners[2],"�ַ�������",valLabels[2]);
		//-----------ʹ������ֵ��ImageIcon��JSpinner
		String src="./src/icon/";
		ArrayList<ImageIcon> icons =new ArrayList<>();
		icons.add(new ImageIcon(src+"save.png"));
		icons.add(new ImageIcon(src+"edit.png"));
		icons.add(new ImageIcon(src+"exit.png"));
		//ʹ��ImageIcon���鴴��SpinnerListModel����
		SpinnerListModel iconModel = new SpinnerListModel(icons);
		//ʹ��SpinnerListModel���󴴽�JSpinner����
		spinners[3] = new JSpinner(iconModel);
		addSpinner(spinners[3],"ͼ������ֵ",valLabels[3]);
		//-------------ʹ��SpinnerDateModel��JSpinner
		//�ֱ��ȡ��ʼʱ��,����ʱ��,��ʼʱ��
		Calendar cal = Calendar.getInstance();
		Date init = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, -3);
		Date start = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, 8);
		Date end =cal.getTime();
		//����һ��SpinnerDateModel����,ָ����Сʱ��,���ʱ��,�ͳ�ʼʱ��
		SpinnerDateModel dateModel = new SpinnerDateModel(init,start,end,Calendar.HOUR_OF_DAY);
		//��SpinnerDateModel���󴴽�JSpinner
		spinners[4] = new JSpinner(dateModel);
		addSpinner(spinners[4],"ʱ�䷶Χ",valLabels[4]);
		//-------------ʹ��DateEditor����ʽ��JSpinner
		dateModel = new SpinnerDateModel();
		spinners[5] = new JSpinner(dateModel);
		//����һ��JSpinner.DateEditor����,���ڶ�ָ����Spinner���и�ʽ��
		JSpinner.DateEditor editor = new JSpinner.DateEditor(spinners[5], "��Ԫyyyy��MM��HHʱ");
		//����ʹ��JSpinner.DateEditor������и�ʽ��
		spinners[5].setEditor(editor);
		addSpinner(spinners[5],"ʹ��DateEditor",valLabels[5]);
		
		//Ϊȷ����ť���һ���¼�������
		okBn.addActionListener(evt ->{
			//ȡ��ÿ��΢����������ֵ,������ֵ��ʾ�ں����JLabel��
			for(int i=0;i<SPINNER_NUM;++i){
				valLabels[i].setText(spinners[i].getValue().toString());
			}
		});
		JPanel bnPanel = new JPanel();
		bnPanel.add(okBn);
		jf.add(spinnerBox);
		jf.add(bnPanel,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	//����һ������,���ڽ���������ӵ�������
	public void addSpinner(JSpinner spinner,String description,JLabel valLabel){
		Box box = new Box(BoxLayout.X_AXIS);
		JLabel desc = new JLabel(description+":");
		desc.setPreferredSize(new Dimension(100,30));
		box.add(desc);
		box.add(spinner);
		valLabel.setPreferredSize(new Dimension(180,30));
		box.add(valLabel);
		spinnerBox.add(box);
	}
	
	public static void main(String[] args){
		new JSpinnerTest().init();
	}
}
