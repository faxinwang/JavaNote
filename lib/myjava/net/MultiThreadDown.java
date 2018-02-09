package myjava.net;

public class MultiThreadDown {

	public static void main(String[] args)throws Exception{
		String src="http://mp.weixin.qq.com/s?__biz=MzIxMDExNjQ2Mg==&tempkey=zcJN6R%2BKE19CMjJl7UgRR%2Ft8kzoC8Na3G0HeQ8bPWcdTAeOSbTiv35qS44DXmFQz9k2ZPsHlqNDRt3ycDXlys6e%2FCLYPO1RZMCHMFAQveIlfYUp%2FCC4Yssw2xpffG4dLfBfxaN9KYOrKUbPQOJqWTg%3D%3D&#rd";
				
		String savePath="./src/img/mulitThreadDown";
		int threadNum=4;
		final DownUtil downUtil = new DownUtil(src,savePath,threadNum);
		downUtil.download();
		new Thread(()->{
			//每隔0.1秒查询一次任务的进度
			//GUI程序中可根据该进度来绘制进度条
			System.out.println("已完成:" + downUtil.getCompleteRate());
			try{
				Thread.sleep(100);
			}catch(Exception e){
				System.out.println(e);
			}
		}).start();;
	}
}
