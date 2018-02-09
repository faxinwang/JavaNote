package aboutObjectB;

public class CacheClass {
	private static int MAX_SIZE = 10;
	private static CacheClass[] cache = new CacheClass[MAX_SIZE];
	//��¼����ʵ���ڻ����е�λ��,cache[pos-1]�����»����ʵ��
	private static int pos = 0;
	private final String name;
	private CacheClass(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public static CacheClass valueOf(String name){
		for(int i=0;i<MAX_SIZE;++i){
			//���������ͬʵ������ֱ�ӷ��ظû����ʵ��
			if(cache[i] != null && cache[i].getName().equals(name)){
				return cache[i];
			}
		}
		//������������
		if(pos == MAX_SIZE){
			//�ѻ�����еĵ�һ�����󸲸ǣ����Ѹո����ɵĶ�����ڵ�һ��λ��
			cache[0] = new CacheClass(name);
			pos = 1;
		}else{
			cache[pos++] = new CacheClass(name);
		}
		return cache[pos-1];
	}
	//��дequals()����
	public boolean equals(Object obj){
		if(this==obj) return true;
		if(obj!=null && obj.getClass() == CacheClass.class){
			CacheClass cc = (CacheClass)obj;
			return name.equals(cc.getName());
		}
		return false;
	}
	//��дhashCode()����
	public int hashCode(){
		return name.hashCode();
	}
	
	static void IntegerCacheTest(){
		//�����µ�Integer����
		Integer i1 = new Integer(6);
		//�����µ�Integer���󲢻���
		Integer i2 = Integer.valueOf(6);
		//ֱ�Ӵӻ�����ȡ��Integer����
		Integer i3 = Integer.valueOf(6);
		System.out.println(i1 == i2);//false
		System.out.println(i2 == i3);//true
		//����Integerֻ����-128��127֮���ֵ
		//���200��Ӧ��Integer����û�б�����
		Integer i4 = Integer.valueOf(200);
		Integer i5 = Integer.valueOf(200);
		System.out.println(i4 == i5);//false
	}
	
	public static void main(String[] args){
		CacheClass c1 = CacheClass.valueOf("hello");
		CacheClass c2 = CacheClass.valueOf("hello");
		CacheClass c3 = CacheClass.valueOf("hello world");
		
		System.out.println("c1 == c2 : " + (c1 == c2)); //true
		System.out.println("c1 == c2 : " + (c1 == c3)); //false
		
		IntegerCacheTest();
	}
}
