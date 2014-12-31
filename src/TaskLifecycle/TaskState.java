package TaskLifecycle;

public interface TaskState {
	
	public static final String UNDEFINED = "Undefined";
	public static final String NOT_STARTED = "not started";
	public static final String IN_PROGRESS = "in progress";
	public static final String COMPLETED = "completed";
	
	public static final TaskState Undefined = new BasicState(UNDEFINED);
	public static final TaskState NotStarted = new BasicState(NOT_STARTED);
	public static final TaskState InProgress = new BasicState(IN_PROGRESS);
	public static final TaskState Completed = new BasicState(COMPLETED);



	public TaskState getState();
	public String getDescription();
	public String setDescription(final String description);
//	public long GetStateID(); //might be required for db

}
