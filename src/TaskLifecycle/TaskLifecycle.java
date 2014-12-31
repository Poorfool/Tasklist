package TaskLifecycle;

import java.util.ArrayList;



public interface TaskLifecycle {
	

	public TaskLifecycleType getType();
	public TaskState getState();
	public ArrayList<TaskState> getStates();

	public boolean isComplete();
	public boolean isValid();
	public TaskState advance() throws TaskStateException;
	public TaskState setState(TaskState newState) throws TaskStateException;
	public TaskState rollback() throws TaskStateException;


}
