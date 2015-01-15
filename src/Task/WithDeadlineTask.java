package Task;

import TaskLifecycle.LifecycleFactory;
import TaskLifecycle.TaskLifecycleTypes;
import TaskLifecycle.TaskStateException;

import java.util.Date;

public class WithDeadlineTask extends AbstractTask{

	private Date due = null;

	public boolean hasDueDate(){
		return this.due != null;
	}

	public Date getDueDate(){
		return this.due;
	}

	public Date setDueDate(Date newDeadline) throws TaskStateException {
		Date today = new Date();
		if(null!= this.due && newDeadline.before(today)){
			due = newDeadline;
		}else {
			throw new TaskStateException("Deadline in the past");
		}
		return due;
	}

	protected WithDeadlineTask(String name){
		super(name, LifecycleFactory.getLifecycleFactory().createTaskLifecycle(TaskLifecycleTypes.InProgress));
	}

	@Override
	protected Task createTask(String name) {
		return new WithDeadlineTask(name);
	}

	@Override
	public TaskType getTaskType() {
		return TaskTypes.WithDeadline;
	}
}
