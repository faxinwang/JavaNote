package state;

/* WorkState
 * 		|---->ForenoonState
 * 		|---->NoonState
 * 		|---->AfteroonState
 * 		|---->EveningState
 * 		|---->SleepingState
 * 		|---->RestState
 * 
 * Work(����һ��WorkState���󣬸ö���Ĳ�ͬ״̬ʹ��work���в�ͬ����Ϊ)
 */

//������״̬�࣬������Handle������
abstract class WorkState{
	public abstract void Handle(Work work);
}

//����״̬�࣬ʵ�־���״̬�µ���Ϊ�߼���רҵ�߼�
class ForenoonState extends WorkState{
	public void Handle(Work work) {
		if(work.getHour() < 12) {
			System.out.println("��ǰʱ��:" + work.getHour() + 
					"���繤��������ٱ�");
		}else {
			work.setState(new NoonState());
			work.WriteProgram();
		}
	}
}
class NoonState extends WorkState{
	public void Handle(Work work) {
		if(work.getHour() < 13) {
			System.out.println("��ǰʱ�䣺"+work.getHour() + 
					"���ˣ��緹������������");
		}else {
			work.setState(new AfternoonState());
			work.WriteProgram();
		}
	}
}

class AfternoonState extends WorkState{
	public void Handle(Work work) {
		if(work.getHour() < 17) {
			System.out.println("��ǰʱ�䣺" + work.getHour() + 
					"����״̬����������Ŭ��");
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
				System.out.println("��ǰʱ�䣺" + work.getHour() + 
						"�Ӱ࣬ƣ������!");				
			}else {
				work.setState(new SleepingState());
				work.WriteProgram();
			}
		}
	}
}

class SleepingState extends WorkState{
	public void Handle(Work work) {
		System.out.println("��ǰʱ�䣺" + work.getHour() + 
				"�����ˣ�˯����!");
	}
}

class RestState extends WorkState{
	public void Handle(Work work) {
		System.out.println("��ǰʱ�䣺" + work.getHour() + 
				"����˳����ɣ��ؼ���Ϣ!");
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
//		��ǰʱ��:9���繤��������ٱ�
//		��ǰʱ��:10���繤��������ٱ�
//		��ǰʱ�䣺12���ˣ��緹������������
//		��ǰʱ�䣺13����״̬����������Ŭ��
//		��ǰʱ�䣺14����״̬����������Ŭ��
//		��ǰʱ�䣺17�Ӱ࣬ƣ������!
//		��ǰʱ�䣺19�Ӱ࣬ƣ������!
//		��ǰʱ�䣺21�����ˣ�˯����!
	}
}
