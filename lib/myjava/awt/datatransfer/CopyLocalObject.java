package myjava.awt.datatransfer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

class Person {
	
	String name;
	int age;
	public Person(String name,int age){
		this.name = name;
		this.age = age;
	}
	public String toString(){
		return "Person[ name = " + name + ", age = "+age+" ]";
	}
}

class LocalObjectSelection implements Transferable{
	private Object obj;
	public LocalObjectSelection(Object o){
		obj = o;
	//	System.out.println("���캯����:"+o);// ok
	}
	@Override
	public DataFlavor[] getTransferDataFlavors() {

		DataFlavor[] flavors = new DataFlavor[2];
		//��ȡ����װ���������
		Class<? extends Object> clazz = obj.getClass();
	//	System.out.println("clazz.getName()��"+clazz.getName());
		
		String mimeType = "application/x-java-jvm-local-objectref;"
					+ "class=" + clazz.getName();
		
		try{
			//����һ��mimeType���͵�DataFlavor
			flavors[0] = new DataFlavor(mimeType);
			flavors[1] = DataFlavor.stringFlavor;
			return flavors;
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Object getTransferData(DataFlavor flavor) 
			throws UnsupportedFlavorException, IOException {
		if(!isDataFlavorSupported(flavor)){
			throw new UnsupportedFlavorException(flavor);
		}
		if(flavor.equals(DataFlavor.stringFlavor)){
			return obj.toString();
		}
		return obj;
	}
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor){
		return flavor.equals(DataFlavor.stringFlavor) ||
				flavor.getPrimaryType().equals("application") &&
				flavor.getSubType().equals("x-java-jvm-local-objectref") &&
				flavor.getRepresentationClass().isAssignableFrom(obj.getClass());
	}
}

public class CopyLocalObject {
	Frame f = new Frame("���Ʊ��ض���");
	Button copy = new Button("����");
	Button paste = new Button("ճ��");
	TextField name = new TextField(15);
	TextField age =new TextField(15);
	TextArea txts = new TextArea(5,30);
	Panel north = new Panel();
	Panel south = new Panel();
	//�������ؼ�����
	Clipboard clipboard = new Clipboard("copyObject");
	void init(){
		north.add(new Label("����"));
		north.add(name);
		north.add(new Label("����"));
		north.add(age);
		f.add(north, BorderLayout.NORTH);
		f.add(txts);
		south.add(copy);
		south.add(paste);
		f.add(south,BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);
		addActionListeners();
	}
	
	void addActionListeners(){
		//�رմ���
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//Ϊ���ư�ť����¼�������
		copy.addActionListener(event -> copyPerson());
		//Ϊ���ư�ť����¼�������
		paste.addActionListener(event -> {
			try{
				readPerson();
			}catch(Exception e){
				System.out.println("��ȡPersonʧ��!");
				e.printStackTrace();
			}
		});
	}
	
	void copyPerson(){
		//��name,age�ı�������ݴ���Person����
		Person p = new Person(name.getText(),Integer.parseInt(age.getText()));
		//��Person��װ��LocalObjectSelection����
		LocalObjectSelection los = new LocalObjectSelection(p);
		//��LocalObjectSelection������뱾�ؼ�������
		clipboard.setContents(los, null);
	}
	void readPerson()throws Exception{
		//��������Person�������õ�DataFlavor����
		DataFlavor personFlavor = new DataFlavor(
				"application/x-java-jvm-local-objectref;class=Person");
		//ȡ�����ؼ������е�����
		if(clipboard.isDataFlavorAvailable(personFlavor)){
			Person  p = (Person)clipboard.getData(personFlavor);
			txts.append(p.toString());
		}
	}

	
	public static void main(String[] args){
		new CopyLocalObject().init();
	}
}
