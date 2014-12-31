package TaskLifecycle;

public class TaskLifecycleType {
	
	private String type = null;
	public TaskLifecycleType(){}
	public TaskLifecycleType(String type){
		this.type = type;
	}
	public String getDescription(){
		return this.type;
	}

}
