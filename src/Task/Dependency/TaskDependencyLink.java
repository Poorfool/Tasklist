package Task.Dependency;

import Task.Task;

public class TaskDependencyLink {
	private Task task = null;
	private Task dependency = null;
	
	public TaskDependencyLink(Task task, Task isDependentUpon){
		this.task = task;
		this.dependency = isDependentUpon;
	}
	
	public Task getTask(){
		return this.task;
	}
	
	public Task getDependency(){  //is dependent upon
		return this.dependency;
	}
}
