package basic_grammer;

public class ContinueTag {
	
	public static void main(String[] args){
		
		outer:
		for(int i = 0 ; i < 5 ; ++i){
			for(int j = 0 ; j < 5 ; ++j){
				System.out.println("i的值为:" + i + ", j的值为:" + j);
				if(j==2){
					//忽略outer标签所指定的循环中本次循环所剩下的语句
					continue outer;
				}
			}
		}
		
	}

}
