package basic_grammer;

public class BreakTag {
	
	public static void main(String[] args){
		outer:
		for(int i=0 ; i < 5 ; ++i){
			for(int j = 0 ;  j < 3 ; ++j){
				System.out.println("i��ֵΪ:" + i + ", j��ֵΪ:" + j);
				if(j==2){
					//����outer��ǩ����ʶ��ѭ��
					break outer;
				}
			}
		}
		
	}
}
