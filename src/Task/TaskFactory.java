package Task;


import java.util.HashMap;
import java.util.Set;

import TaskLifecycle.LifecycleFactory;
import TaskLifecycle.TaskLifecycleType;

public class TaskFactory {
	private static TaskFactory factory = null;
	private HashMap<TaskType, AbstractTask> taskTypes = null;
	
	private TaskFactory(){initialise();}
	
	private void initialise(){
		taskTypes = new HashMap<TaskType, AbstractTask>();
		addTaskType(new SimpleTask(new Exemplar()));
	}
	
	public void addTaskType(AbstractTask task){
		taskTypes.put(task.getTaskType(), task);
	}
	
	public Set<TaskType> getTaskTypes(){
		return this.taskTypes.keySet();
	}
	
	public static TaskFactory getTaskFactory(){
		if(factory == null){
			factory = new TaskFactory();
		}
		return factory;
	}
	
	public Task createTask( 
			TaskType type,
			String name, 
			TaskLifecycleType lifecycle){
		if(this.taskTypes.containsKey(type)){
			return this.taskTypes.get(type).createTask(name, LifecycleFactory.getLifecycleFactory().createTaskLifecycle(lifecycle));
		}		
		return null;
	}

}
