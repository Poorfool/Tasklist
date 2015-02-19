package Task.Lifecycle;

import java.util.HashMap;
import java.util.Set;

import Task.Exemplar;
import Task.State.TaskStateException;

public class LifecycleFactory {

	private void initialise(){
		addLifecycle(new SimpleTaskLifecycle(new Exemplar()));
		addLifecycle(new InProgressTaskLifecycle(new Exemplar()));
	}
	
	
	private static LifecycleFactory factory = null;
	
	public static LifecycleFactory getLifecycleFactory(){
		if(factory == null){
			factory = new LifecycleFactory();
		}
		return factory;
	}
	
	private HashMap<TaskLifecycleType, AbstractTaskLifecycle>lifecycles = new HashMap<TaskLifecycleType, AbstractTaskLifecycle>();
	
		
	private LifecycleFactory(){ initialise();}
	
	public void addLifecycle(AbstractTaskLifecycle lifecycle){
		lifecycles.put(lifecycle.getLifecycleType(), lifecycle);
	}
	
	public TaskLifecycle createTaskLifecycle(TaskLifecycleType type){
		if(lifecycles.containsKey(type)){
			return lifecycles.get(type).createLifecycle();
		}
		return null;
			
	} 
	
	public Set<TaskLifecycleType> getLifecycleTypes(){
		return this.lifecycles.keySet();
	}
	
	public TaskLifecycle createTaskLifecycle(TaskLifecycleType type, TaskLifecycleState currentState) throws TaskStateException {
		if(lifecycles.containsKey(type)){
			return lifecycles.get(type).createLifecycle(currentState);
		}else{
			throw new TaskStateException(type + " is not a valid Task Task.Lifecycle");
		}
	}
}
