package Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TaskDependencyManager {
	
	private static TaskDependencyManager manager = null;
	private HashMap<Task, ArrayList<Task>> taskToDependentTasks = null;
	private HashMap<Task, ArrayList<Task>> dependentTasksToTasks = null;
	
	private TaskDependencyManager(){
		this.taskToDependentTasks = new HashMap<Task, ArrayList<Task>>();
		this.dependentTasksToTasks = new HashMap<Task, ArrayList<Task>>();		
	}
	
	public static TaskDependencyManager getManager(){
		if(manager == null){
			manager = new TaskDependencyManager();
		}
		return manager;
	}
	
	public void addDependentTask(TaskDependency dependency) throws TaskDependencyException{
		this.addDependentTask(dependency.getTask(), dependency.getDependency());
	}
	
	private void addDependentTask(Task task, Task isDependentUpon) throws TaskDependencyException{
		
		if(this.getAllDependentTasks(isDependentUpon).contains(task)){
			throw new TaskDependencyException("Circular dependency introduced");
		}
		
		ArrayList<Task> list;
		if(this.taskToDependentTasks.containsKey(task)){
			list = this.taskToDependentTasks.get(task);
	
		}else{
			list = new ArrayList<Task>();
		}
		if(!list.add(isDependentUpon)){
			throw new TaskDependencyException("Task not added to dependency list");
		}
		this.taskToDependentTasks.put(task, list);
		
		if(dependentTasksToTasks.containsKey(isDependentUpon)){
			list = dependentTasksToTasks.get(isDependentUpon);
		}else{
			list = new ArrayList<Task>();
		}
		if(!list.add(task)){
			throw new TaskDependencyException("Task not added to dependentUponList");
		}
		dependentTasksToTasks.put(isDependentUpon, list);
	}
	
	public Collection<Task> addDependentTasks(Task task, Collection<Task> dependencies) throws TaskDependencyException{
		for(Task T : dependencies){
			this.addDependentTask(task, T);
		}
		return this.getDependentTasks(task);
	}
	
	public void removeDependentTask(TaskDependency dependency) throws TaskDependencyException{
		removeDependentTask(dependency.getTask(), dependency.getDependency());
	}
	
	private void removeDependentTask(Task task, Task dependency) throws TaskDependencyException{
		if(this.taskToDependentTasks.containsKey(task) && dependentTasksToTasks.containsKey(dependency)){
			ArrayList<Task> list = this.taskToDependentTasks.get(task);
			if(!list.remove(dependency)){
				throw new TaskDependencyException("Attempting to remove dependencies not present");
			}
			list = this.dependentTasksToTasks.get(dependency);
			if(!list.remove(task)){
				throw new TaskDependencyException("Attempting to remove dependencies not present");
			}
		}else{
			throw new TaskDependencyException("Attempting to remove dependencies not present");
		}
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getDependentTasks(Task task){
		if(this.taskToDependentTasks.get(task) != null){
			return (Collection<Task>) this.taskToDependentTasks.get(task).clone();
		}
		return new ArrayList<Task>();
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Task> getTasksDependentUpon(Task task){
		if(this.dependentTasksToTasks.get(task) != null){
			return (Collection<Task>) this.dependentTasksToTasks.get(task).clone();
		}
		return new ArrayList<Task>();
	}
	
	private boolean hasExecutableDependentTask(Task task, boolean executable){
		Collection<Task> dependents = task.getDependentTasks();
		if(!executable){
			if(null != dependents){
				for(Task T : dependents){
					executable = this.hasExecutableDependentTask(T, executable);
					if(!executable){ 
						//completed leaves so now check task itself is not complete 
						//(it might be an intermediate middle node)
						executable = !T.isComplete();
					}
				}
			}else{	
				//leaf node
				executable = !task.isComplete();
			}
		}
		return executable;
	}
	
	public boolean hasExecutableDependentTask(Task task){
		boolean hasExecutable = false;
		if(task.hasDependentTasks()){
			hasExecutable = hasExecutableDependentTask(task, hasExecutable);
		}
		return hasExecutable;
	}
	
	public Collection<Task> getAllDependentTasks(Task task){
																								System.out.println("All tasks for <"+task.getToDo()+">");
		Collection<Task> dependentTasks = task.getDependentTasks();
		if(dependentTasks != null){
																								System.out.println("Task <"+task.getToDo()+"> has "+dependentTasks.size()+" dependents");
			Collection<Task> dependentSubTasks = new ArrayList<Task>();
			Collection<Task> nullcheck;
			for(Task T : dependentTasks){
																								System.out.println("getting all dependencies for T <"+T.getToDo()+">");
				nullcheck = T.getAllDependentTasks();
				if(nullcheck != null){
																								System.out.println("T <"+T.getToDo()+"> has "+nullcheck.size()+" subtasks");
					dependentSubTasks.addAll(nullcheck);
				}
																								else{System.out.println("T <"+T.getToDo()+"< hos no dependent subtasks");}							
			}
			dependentTasks.addAll(dependentSubTasks);
		}
																								if(dependentTasks!=null){System.out.println("returning Task <"+task.getToDo()+"> with "+dependentTasks.size()+" dependent tasks");}
																								else{System.out.println("returning null for Task <"+task.getToDo()+"> with no dependent tasks");}
		return dependentTasks;
	}
	
}
