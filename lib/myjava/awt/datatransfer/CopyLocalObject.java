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
	//	System.out.println("构造函数中:"+o);// ok
	}
	@Override
	public DataFlavor[] getTransferDataFlavors() {

		DataFlavor[] flavors = new DataFlavor[2];
		//获取被封装对象的类型
		Class<? extends Object> clazz = obj.getClass();
	//	System.out.println("clazz.getName()："+clazz.getName());
		
		String mimeType = "application/x-java-jvm-local-objectref;"
					+ "class=" + clazz.getName();
		
		try{
			//创建一个mimeType类型的DataFlavor
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
	Frame f = new Frame("复制本地对象");
	Button copy = new Button("复制");
	Button paste = new Button("粘贴");
	TextField name = new TextField(15);
	TextField age =new TextField(15);
	TextArea txts = new TextArea(5,30);
	Panel north = new Panel();
	Panel south = new Panel();
	//创建本地剪贴板
	Clipboard clipboard = new Clipboard("copyObject");
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
		copy.addActionListener(event -> copyPerson());
		//为复制按钮添加事件监听器
		paste.addActionListener(event -> {
			try{
				readPerson();
			}catch(Exception e){
				System.out.println("读取Person失败!");
				e.printStackTrace();
			}
		});
	}
	
	void copyPerson(){
		//以name,age文本框的内容创建Person对象
		Person p = new Person(name.getText(),Integer.parseInt(age.getText()));
		//将Person封装成LocalObjectSelection对象
		LocalObjectSelection los = new LocalObjectSelection(p);
		//将LocalObjectSelection对象放入本地剪贴板中
		clipboard.setContents(los, null);
	}
	void readPerson()throws Exception{
		//创建保存Person对象引用的DataFlavor对象
		DataFlavor personFlavor = new DataFlavor(
				"application/x-java-jvm-local-objectref;class=Person");
		//取出本地剪贴板中的内容
		if(clipboard.isDataFlavorAvailable(personFlavor)){
			Person  p = (Person)clipboard.getData(personFlavor);
			txts.append(p.toString());
		}
	}

	
	public static void main(String[] args){
		new CopyLocalObject().init();
	}
}
