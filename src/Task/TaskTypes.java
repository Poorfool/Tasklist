package Task;

import java.beans.Statement;

public interface TaskTypes {
	
	public static final TaskType Simple = new TaskType("Simple");
	public static final TaskType SubTask = new TaskType("SubTask");
	public static final TaskType LongLived = new TaskType("Long Lived");
	public static final TaskType Claimable = new TaskType("Claimable");

 
}
