package state;

/* WorkState
 * 		|---->ForenoonState
 * 		|---->NoonState
 * 		|---->AfteroonState
 * 		|---->EveningState
 * 		|---->SleepingState
 * 		|---->RestState
 * 
 * Work(持有一个WorkState对象，该对象的不同状态使得work具有不同的行为)
 */

//抽象工作状态类，定义了Handle抽象函数
abstract class WorkState{
	public abstract void Handle(Work work);
}

//具体状态类，实现具体状态下的行为逻辑和专业逻辑
class ForenoonState extends WorkState{
	public void Handle(Work work) {
		if(work.getHour() < 12) {
			System.out.println("当前时间:" + work.getHour() + 
					"上午工作，精神百倍");
		}else {
			work.setState(new NoonState());
			work.WriteProgram();
		}
	}
}
class NoonState extends WorkState{
	public void Handle(Work work) {
		if(work.getHour() < 13) {
			System.out.println("当前时间："+work.getHour() + 
					"饿了，午饭，犯困，午休");
		}else {
			work.setState(new AfternoonState());
			work.WriteProgram();
		}
	}
}

class AfternoonState extends WorkState{
	public void Handle(Work work) {
		if(work.getHour() < 17) {
			System.out.println("当前时间：" + work.getHour() + 
					"下午状态还不错，继续努力");
		}else {
			work.setState(new EveningState());
			work.WriteProgram();
		}
	}
}

class EveningState extends WorkState{
	public void Handle(Work work) {
		if(work.taskFinished()) {
			work.setState(new RestState());
			work.WriteProgram();
		}else {
			if(work.getHour() < 21) {
				System.out.println("当前时间：" + work.getHour() + 
						"加班，疲惫至极!");				
			}else {
				work.setState(new SleepingState());
				work.WriteProgram();
			}
		}
	}
}

class SleepingState extends WorkState{
	public void Handle(Work work) {
		System.out.println("当前时间：" + work.getHour() + 
				"不行了，睡着了!");
	}
}

class RestState extends WorkState{
	public void Handle(Work work) {
		System.out.println("当前时间：" + work.getHour() + 
				"工作顺利完成，回家休息!");
	}
}


class Work{
	private WorkState state;
	private int hour;
	private boolean taskFinished;
	public Work() {
		state = new ForenoonState();
		taskFinished = false;
	}
	public void setHour(int hour) {this.hour = hour;}
	public int getHour() { return hour;}
	public void setFinished(boolean finished) {taskFinished = finished;}
	public boolean taskFinished() {return taskFinished;}
	public void setState(WorkState state) {this.state = state;}
	
	public void WriteProgram() {
		state.Handle(this);
	}
}


public class Working {
	public static void main(String[] args) {
		Work emergencyWork = new Work();
		emergencyWork.setHour(9);
		emergencyWork.WriteProgram();
		emergencyWork.setHour(10);
		emergencyWork.WriteProgram();
		emergencyWork.setHour(12);
		emergencyWork.WriteProgram();
		emergencyWork.setHour(13);
		emergencyWork.WriteProgram();
		emergencyWork.setHour(14);
		emergencyWork.WriteProgram();
		emergencyWork.setHour(17);
		
//		emergencyWork.setFinished(true);
		
		emergencyWork.WriteProgram();
		emergencyWork.setHour(19);
		emergencyWork.WriteProgram();
		emergencyWork.setHour(21);
		emergencyWork.WriteProgram();
//		当前时间:9上午工作，精神百倍
//		当前时间:10上午工作，精神百倍
//		当前时间：12饿了，午饭，犯困，午休
//		当前时间：13下午状态还不错，继续努力
//		当前时间：14下午状态还不错，继续努力
//		当前时间：17加班，疲惫至极!
//		当前时间：19加班，疲惫至极!
//		当前时间：21不行了，睡着了!
	}
}
