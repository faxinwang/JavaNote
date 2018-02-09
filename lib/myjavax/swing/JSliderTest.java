package myjavax.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class JSliderTest {
	JFrame jf = new JFrame("������ʾ��");
	Box sliderBox = new Box(BoxLayout.Y_AXIS);
	JTextField showVal = new JTextField();
	ChangeListener listener;
	public void init(){
		//����һ��������,���ڼ������еĻ�����
		listener = event ->{
			//ȡ���������е�ֵ,�����ı�����ʾ����
			JSlider source =(JSlider)event.getSource();
			showVal.setText("��ǰ��������ֵ:" + source.getValue());
		};
		//----------���һ����ͨ������
		JSlider slider = new JSlider();
		addSlider(slider,"��ͨ������");
		//-----------��ӱ�����Ϊ30�Ļ�����
		slider = new JSlider();
		slider.setExtent(30);
		addSlider(slider,"������Ϊ30");
		//-----------��Ӵ����ο̶ȵĻ�����,�����������ֵ,��Сֵ
		slider = new JSlider(30,200);
		//���û��ƿ̶�
		slider.setPaintTicks(true);
		//�������ο̶ȵļ��
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		addSlider(slider,"�п̶�");
		//-----------��ӻ������ͣ���п̶ȵط��Ļ�����
		slider = new JSlider();
		slider.setSnapToTicks(true);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(25);
		slider.setMinorTickSpacing(5);
		addSlider(slider,"����ͣ�ڿ̶ȴ�");
		//-----------���û�л���Ļ�����
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(30);
		slider.setMinorTickSpacing(15);
		//���ò����ƻ���
		slider.setPaintTrack(false);
		addSlider(slider,"�޻���");
		//------------��ӷ���ת�Ļ�����
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(30);
		slider.setMinorTickSpacing(10);
		//���÷���ת
		slider.setInverted(true);
		addSlider(slider,"����ת");
		//------------��ӻ���Ĭ�Ͽ̶ȵĻ�����
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		//���û��ƿ̶ȱ�ǩ,Ĭ�ϻ�����ֵ�̶ȱ�ǩ
		slider.setPaintLabels(true);
		addSlider(slider,"��ֵ�̶ȱ�ǩ");
		//-----------��ӻ���Label���͵Ŀ̶ȱ�ǩ�Ļ�����
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		//���û��ƿ̶ȱ�ǩ
		slider.setPaintLabels(true);
		Dictionary<Integer,Component> labelTable = new Hashtable<>();
		labelTable.put(0,new JLabel("A"));
		labelTable.put(20, new JLabel("B"));
		labelTable.put(40, new JLabel("C"));
		labelTable.put(60, new JLabel("D"));
		labelTable.put(80, new JLabel("E"));
		labelTable.put(100, new JLabel("F"));
		//ָ���̶ȱ�ǩ,��ǩ��JLabel
		slider.setLabelTable(labelTable);
		addSlider(slider,"JLabel��ǩ");
		//--------------��ӻ���Label���͵Ŀ̶ȱ�ǩ�Ļ�����
		slider = new JSlider();
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(20);
		slider.setMinorTickSpacing(5);
		slider.setPaintLabels(true);
		String src="./src/icon/";
		labelTable = new Hashtable<Integer,Component>();
		labelTable.put(0, new JLabel(new ImageIcon(src+"new.png")));
		labelTable.put(20, new JLabel(new ImageIcon(src+"copy.png")));
		labelTable.put(40, new JLabel(new ImageIcon(src+"paste.png")));
		labelTable.put(60, new JLabel(new ImageIcon(src+"save.png")));
		labelTable.put(80, new JLabel(new ImageIcon(src+"exit.png")));
		//ָ���̶ȱ�ǩ,��ǩ��ImageIcon
		slider.setLabelTable(labelTable);
		addSlider(slider,"Icon��ǩ");
		jf.add(sliderBox);
		jf.add(showVal,BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	
	//����һ������,���ڽ���������ӵ�������
	public void addSlider(JSlider slider,String description){
		slider.addChangeListener(listener);
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(new JLabel(description+":"));
		box.add(slider);
		sliderBox.add(box);
	}
	
	public static void main(String[] args){
		new JSliderTest().init();
	}
}
