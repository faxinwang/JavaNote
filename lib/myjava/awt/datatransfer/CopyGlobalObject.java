package myjava.awt.datatransfer;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;

class Dog implements Serializable{

	private static final long serialVersionUID = 1L;
	String name;
	int age;
	public Dog(String name,int age){
		this.name = name;
		this.age = age;
	}
	public String toString(){
		return "Dog[ name = "+ name + ", age = "+ age+ " ]";
	}
}
class SerialSelection implements Transferable{
	private Serializable obj;
	public SerialSelection(Serializable obj){
		this.obj = obj;
	}
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		DataFlavor[] flavors = new DataFlavor[2];
		Class<? extends Serializable> clazz = obj.getClass();
		try{
			flavors[0] = new DataFlavor(DataFlavor.javaSerializedObjectMimeType
					+ ";class=" + clazz.getName());
			flavors[1] = DataFlavor.stringFlavor;
			return flavors;
		}catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return flavor.equals(DataFlavor.stringFlavor) ||
				flavor.getPrimaryType().equals("application") &&
				flavor.getSubType().equals("x-java-serialized-object") &&
				flavor.getRepresentationClass().isAssignableFrom(obj.getClass());
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
}


public class CopyGlobalObject {
	Frame f = new Frame("����ȫ�ֶ���");
	Button copy = new Button("����");
	Button paste = new Button("ճ��");
	TextField name = new TextField(15);
	TextField age =new TextField(15);
	TextArea txts = new TextArea(5,30);
	Panel north = new Panel();
	Panel south = new Panel();
	//����ϵͳ������
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
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
		copy.addActionListener(event -> copyDog());
		//Ϊ���ư�ť����¼�������
		paste.addActionListener(event -> {
			try{
				readDog();
			}catch(Exception e){
				System.out.println("��ȡDogʧ��!");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		});
	}
	
	void copyDog(){
		//��name,age�ı�������ݴ���Dog����
		Dog d = new Dog(name.getText(),Integer.parseInt(age.getText()));
		//��Dog��װ��SerialSelection����
		SerialSelection los = new SerialSelection(d);
		//��SerialSelection������뱾�ؼ�������
		clipboard.setContents(los, null);
	}
	void readDog()throws Exception{
		//��������Dog�������õ�DataFlavor����
		DataFlavor DogFlavor = new DataFlavor(
				DataFlavor.javaSerializedObjectMimeType+";class=Dog");
		//ȡ�����ؼ������е�����
		if(clipboard.isDataFlavorAvailable(DogFlavor)){
			Dog  d = (Dog)clipboard.getData(DogFlavor);
			txts.append(d.toString());
		}
	}
	
	
	public static void main(String[] args){
		new CopyGlobalObject().init();
	}
}
