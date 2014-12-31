package Task;
import java.util.Collection;


import TaskLifecycle.TaskLifecycle;
import TaskLifecycle.TaskState;
import TaskLifecycle.TaskStateException;


public interface Task {
	public String getToDo();
	public String setToDo(String toDo);
	public long getID();
	//public void setID();  //needs to be immutable so pre-set
	
	public TaskLifecycle getLifecycle();
	public TaskType getTaskType();

	
	public TaskState getStatus();
	public TaskState updateStatus(TaskState newState) throws TaskStateException;
	public void advanceState() throws TaskStateException;
	public void rollbackState() throws TaskStateException;
	public void markComplete() throws TaskStateException;
	public boolean isComplete();

	public boolean hasDependentTasks();
	public int dependentTaskCount();
	public Collection<Task> getDependentTasks();
	public Collection<Task> getAllDependentTasks();
	public void addDependentTasks(Collection<Task> newDependencies) throws TaskDependencyException;
	public void addDependentTask(Task dependentTask) throws TaskDependencyException;

	public boolean isValid();
	public boolean isExecutable();

}
