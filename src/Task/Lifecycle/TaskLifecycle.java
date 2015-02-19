package Task.Lifecycle;


import Task.State.TaskStateException;

public interface TaskLifecycle {
	

	public TaskLifecycleType getLifecycleType();

	public TaskLifecycleState getState();
	public TaskLifecycleState setState(TaskLifecycleState newState) throws TaskStateException;

	public boolean isComplete();
	public TaskLifecycleState markComplete() throws TaskStateException;

	public boolean isValid();
	public TaskLifecycleState advance() throws TaskStateException;
	public TaskLifecycleState rollback() throws TaskStateException;

}
