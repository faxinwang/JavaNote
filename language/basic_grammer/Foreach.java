package basic_grammer;

public class Foreach {
	
	public static void main(String[] args){
		
		String[] books = new String[]{ 
			"轻量级Java EE 企业应用实战",
			"疯狂java讲义",
			"疯狂android讲义"	
		};
		
		//使用foreach循环来遍历数字元素，其中book将会自动迭代每个数组元素
		for(String book:books){
			book = "疯狂Ajax讲义";
			System.out.println(book);
		}
		
		for(String book:books){
			System.out.println(book);
		}
		
	}
	
	
}
