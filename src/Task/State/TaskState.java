package Task.State;

import Task.Lifecycle.TaskLifecycle;
import Task.Lifecycle.TaskLifecycleState;

import java.util.Date;

/**
 * Created by Mike on 28/01/15.
 */
public interface TaskState extends TaskLifecycle {

    public void setTaskLifecycle(TaskLifecycle lifecycle);
    public TaskLifecycle getLifecycle();

    public TaskLifecycleState getState();

    public void setTaskLifecycleState(TaskLifecycleState state) throws TaskStateException;
    public void getTaskLifecycleState();

    public void setDueDate(Date date) throws TaskStateException;
    public Date getDueDate();
    public boolean hasDueDate();
    public boolean isOverdue();


}
