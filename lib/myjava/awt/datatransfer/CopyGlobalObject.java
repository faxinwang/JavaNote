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
	Frame f = new Frame("复制全局对象");
	Button copy = new Button("复制");
	Button paste = new Button("粘贴");
	TextField name = new TextField(15);
	TextField age =new TextField(15);
	TextArea txts = new TextArea(5,30);
	Panel north = new Panel();
	Panel south = new Panel();
	//创建系统剪贴板
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	void init(){
		north.add(new Label("姓名"));
		north.add(name);
		north.add(new Label("年龄"));
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
		//关闭窗口
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		//为复制按钮添加事件监听器
		copy.addActionListener(event -> copyDog());
		//为复制按钮添加事件监听器
		paste.addActionListener(event -> {
			try{
				readDog();
			}catch(Exception e){
				System.out.println("读取Dog失败!");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		});
	}
	
	void copyDog(){
		//以name,age文本框的内容创建Dog对象
		Dog d = new Dog(name.getText(),Integer.parseInt(age.getText()));
		//将Dog封装成SerialSelection对象
		SerialSelection los = new SerialSelection(d);
		//将SerialSelection对象放入本地剪贴板中
		clipboard.setContents(los, null);
	}
	void readDog()throws Exception{
		//创建保存Dog对象引用的DataFlavor对象
		DataFlavor DogFlavor = new DataFlavor(
				DataFlavor.javaSerializedObjectMimeType+";class=Dog");
		//取出本地剪贴板中的内容
		if(clipboard.isDataFlavorAvailable(DogFlavor)){
			Dog  d = (Dog)clipboard.getData(DogFlavor);
			txts.append(d.toString());
		}
	}
	
	
	public static void main(String[] args){
		new CopyGlobalObject().init();
	}
}
