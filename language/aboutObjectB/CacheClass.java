package aboutObjectB;

public class CacheClass {
	private static int MAX_SIZE = 10;
	private static CacheClass[] cache = new CacheClass[MAX_SIZE];
	//纪录缓存实例在缓存中的位置,cache[pos-1]是最新缓存的实例
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
			//如果已有相同实例，则直接返回该缓存的实例
			if(cache[i] != null && cache[i].getName().equals(name)){
				return cache[i];
			}
		}
		//如果缓冲池已满
		if(pos == MAX_SIZE){
			//把缓存池中的第一个对象覆盖，即把刚刚生成的对象放在第一个位置
			cache[0] = new CacheClass(name);
			pos = 1;
		}else{
			cache[pos++] = new CacheClass(name);
		}
		return cache[pos-1];
	}
	//重写equals()方法
	public boolean equals(Object obj){
		if(this==obj) return true;
		if(obj!=null && obj.getClass() == CacheClass.class){
			CacheClass cc = (CacheClass)obj;
			return name.equals(cc.getName());
		}
		return false;
	}
	//重写hashCode()方法
	public int hashCode(){
		return name.hashCode();
	}
	
	static void IntegerCacheTest(){
		//生成新的Integer对象
		Integer i1 = new Integer(6);
		//生成新的Integer对象并缓存
		Integer i2 = Integer.valueOf(6);
		//直接从缓存中取出Integer对象
		Integer i3 = Integer.valueOf(6);
		System.out.println(i1 == i2);//false
		System.out.println(i2 == i3);//true
		//由于Integer只缓存-128到127之间的值
		//因此200对应的Integer对象没有被缓存
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
