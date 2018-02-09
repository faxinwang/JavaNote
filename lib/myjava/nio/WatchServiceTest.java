package myjava.nio;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

/* NIO.2��Path���ṩ������һ�������������ļ�ϵͳ�ı仯
 * register(WatchService watcher,WatchEvent.Kind<?>...events):��watcher������path�����Ŀ¼�µ��ļ��仯.
 * events����ָ��Ҫ������Щ���͵��¼�.һ��ʹ��register�������ע���,�������Ϳ��Ե���WatchService������������������ȡ������
 * Ŀ¼�ı仯�¼�:
 * WatchKey poll():��ȡ��һ��WatchKey,���û��WatchKey�����������Ż�null;
 * WatchKey poll(long timeout,TimeUnit unit):���Եȴ�timeoutʱ��ȥ��ȡ��һ��WatchKey;
 * WatchKey take():���û��WatchKey������һֱ�ȴ�.
 * 
 */

public class WatchServiceTest {
	//��ȡ�ļ�ϵͳ��WatchService
	WatchService watchService=null;
	Path path=null;
	public WatchServiceTest()throws Exception{
		WatchService watchService = FileSystems.getDefault().newWatchService();
		path = Paths.get("./src");
		//ΪpathĿ¼ע���ļ�ϵͳ������
		path.register(watchService,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
	}
	
	
	void test1(){
		System.out.println("ʹ��poll()���Ի�ȡWatchKey");
		//ʹ��WatchService��poll������ȡ�¼���Ϣ
		WatchKey key = watchService.poll();
		if(key!=null){
			for(WatchEvent<?> event : key.pollEvents()){
				System.out.println(path+"�е�" + event.context() +"������" +event.kind() +"�¼�..." );
			}
		}else{
			System.out.println(path+"�е��ļ�û�з����κθı�!");
		}
	}
	
	void test2(){
		System.out.println("ʹ��poll(long timeout,TimeUnit unit)���Ի�ȡWatchKey");
		WatchKey key = null;
		try{
			key  = watchService.poll(10, TimeUnit.SECONDS);
		}catch(Exception e){
		//	System.out.println("�������̱����!");
			System.out.println(e.getMessage());
			//�˳�����
		//	return;
		}
		
		if(key!=null){
			for(WatchEvent<?> event : key.pollEvents()){
				System.out.println(path+"�е�" + event.context() +"������" +event.kind() +"�¼�..." );
			}
		}else{
			System.out.println(path+"�е��ļ�û�з����κθı�!");
		}
	}
	
	void test3(){
		while(true){
			WatchKey key=null;
			//��ȡ��һ���ļ��仯�¼�
			try{
				key = watchService.take();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			if(key!=null){
				for(WatchEvent<?> event : key.pollEvents()){
					System.out.println(path+"�е�" + event.context() +"������" +event.kind() +"�¼�..." );
				}
				//����WatchKey
				boolean valid = key.reset();
				//�������ʧ��,�˳�����
				if(!valid) break;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		WatchServiceTest test = new WatchServiceTest();
	//	test.test1();
		test.test2();
		test.test3();
	}
}
