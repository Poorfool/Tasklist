package Task.Dependency;

import java.util.Collection;
import Task.Task;

/**
 * Created by Mike on 28/01/15.
 */
public interface TaskDependency {
    public boolean hasDependentTasks();
    public int dependentTaskCount();
    public Collection<? extends Task> getDependentTasks();
    public Collection<? extends Task> getAllDependentTasks();
    public void addDependentTasks(Collection<? extends Task> newDependencies) throws TaskDependencyException;
    public void addDependentTask(Task dependentTask) throws TaskDependencyException;
    public void removeDependentTasks(Collection<? extends Task> newDependencies) throws TaskDependencyException;
    public void removeDependentTask(Task dependentTask) throws TaskDependencyException;

}
