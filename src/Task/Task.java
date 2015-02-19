package Task;
import java.util.Date;

import Task.Dependency.TaskDependency;
import Task.Lifecycle.TaskLifecycle;
import Task.State.*;


public interface Task extends TaskDependency, TaskLifecycle { //TODO Serializable??
	public String getToDo();
	public String setToDo(String toDo);
	public long getID(); //immutable ID

	public boolean hasDueDate();
	public Date setDueDate(Date date) throws TaskStateException;
	public Date getDueDate();

	public TaskLifecycle getLifecycle();
	public TaskType getTaskType();

	public boolean isExecutable();

}
