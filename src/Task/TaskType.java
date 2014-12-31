package Task;

public class TaskType {
	
	private String type = null;
	
	public String getType(){
		return this.type;
	}
	
	public TaskType(String type){
		this.type = type;
	}
	
	public boolean compare(TaskType type){
		return this.getType() == type.getType();
	}

}
