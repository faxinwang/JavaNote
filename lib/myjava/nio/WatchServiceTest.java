package myjava.nio;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

/* NIO.2的Path类提供了如下一个方法来监听文件系统的变化
 * register(WatchService watcher,WatchEvent.Kind<?>...events):用watcher监听该path代表的目录下的文件变化.
 * events参数指定要监听那些类型的事件.一旦使用register方法完成注册后,接下来就可以调用WatchService的如下三个方法来获取被监听
 * 目录的变化事件:
 * WatchKey poll():获取下一个WatchKey,如果没有WatchKey发生就立即放回null;
 * WatchKey poll(long timeout,TimeUnit unit):尝试等待timeout时间去获取下一个WatchKey;
 * WatchKey take():如果没有WatchKey发生就一直等待.
 * 
 */

public class WatchServiceTest {
	//获取文件系统的WatchService
	WatchService watchService=null;
	Path path=null;
	public WatchServiceTest()throws Exception{
		WatchService watchService = FileSystems.getDefault().newWatchService();
		path = Paths.get("./src");
		//为path目录注册文件系统监听器
		path.register(watchService,
				StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);
	}
	
	
	void test1(){
		System.out.println("使用poll()尝试获取WatchKey");
		//使用WatchService的poll方法获取事件信息
		WatchKey key = watchService.poll();
		if(key!=null){
			for(WatchEvent<?> event : key.pollEvents()){
				System.out.println(path+"中的" + event.context() +"发生了" +event.kind() +"事件..." );
			}
		}else{
			System.out.println(path+"中的文件没有发生任何改变!");
		}
	}
	
	void test2(){
		System.out.println("使用poll(long timeout,TimeUnit unit)尝试获取WatchKey");
		WatchKey key = null;
		try{
			key  = watchService.poll(10, TimeUnit.SECONDS);
		}catch(Exception e){
		//	System.out.println("监听过程被打断!");
			System.out.println(e.getMessage());
			//退出函数
		//	return;
		}
		
		if(key!=null){
			for(WatchEvent<?> event : key.pollEvents()){
				System.out.println(path+"中的" + event.context() +"发生了" +event.kind() +"事件..." );
			}
		}else{
			System.out.println(path+"中的文件没有发生任何改变!");
		}
	}
	
	void test3(){
		while(true){
			WatchKey key=null;
			//获取下一个文件变化事件
			try{
				key = watchService.take();
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			if(key!=null){
				for(WatchEvent<?> event : key.pollEvents()){
					System.out.println(path+"中的" + event.context() +"发生了" +event.kind() +"事件..." );
				}
				//重设WatchKey
				boolean valid = key.reset();
				//如果重设失败,退出监听
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
