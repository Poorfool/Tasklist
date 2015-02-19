package Task.Lifecycle;

import Task.State.BasicState;

public interface TaskLifecycleState {
	
	public static final String UNDEFINED = "Undefined";
	public static final String NOT_STARTED = "not started";
	public static final String IN_PROGRESS = "in progress";
	public static final String COMPLETED = "completed";
	
	public static final TaskLifecycleState NotStarted = new BasicState(NOT_STARTED);
	public static final TaskLifecycleState InProgress = new BasicState(IN_PROGRESS);
	public static final TaskLifecycleState Completed = new BasicState(COMPLETED);
	public static final TaskLifecycleState Undefined = new BasicState(UNDEFINED);



	public TaskLifecycleState getState();
	public String getDescription();
	public String setDescription(final String description);
//	public long GetStateID(); //might be required for db

}
