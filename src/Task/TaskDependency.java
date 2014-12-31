package Task;

public class TaskDependency {
	private Task task = null;
	private Task dependency = null;
	
	public TaskDependency(Task task, Task dependency){
		this.task = task;
		this.dependency = dependency;
	}
	
	public Task getTask(){
		return this.task;
	}
	
	public Task getDependency(){  //is dependent upon
		return this.dependency;
	}
}
