package Task;


import java.util.HashMap;
import java.util.Set;

public class TaskFactory {
	private static TaskFactory factory = null;
	private HashMap<TaskType, AbstractTask> taskTypes = null;
	
	private TaskFactory(){initialise();}
	
	private void initialise(){
		taskTypes = new HashMap<TaskType, AbstractTask>();
		addTaskType(new SimpleTask(new Exemplar()));
		addTaskType(new LongLivedTask(new Exemplar()));
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
	
//	public Task createTask(
//			TaskType type,
//			String name,
//			TaskLifecycleType lifecycle){
//		if(this.taskTypes.containsKey(type)){
//			return this.taskTypes.get(type).createTask(name);
//		}
//		return null;
//	}

	public Task createTask(String name){
		return this.taskTypes.get(TaskTypes.Simple).createTask(name);
	}

	public Task createTask(
			TaskType type,
			String name){
		if(this.taskTypes.containsKey(type)){
			return this.taskTypes.get(type).createTask(name);
		}
		return null;
	}
}
