package Task;

import TaskLifecycle.TaskLifecycle;

public class SimpleTask extends AbstractTask {

	protected SimpleTask(){super();}
	public SimpleTask(Exemplar e){super(e);}
	protected SimpleTask(String name, TaskLifecycle lifecycle){
		super(name, lifecycle);
	}
	
	protected SimpleTask(String name){
		super(name);
	}
	
	@Override
	protected Task createTask(String name, TaskLifecycle lifecycle) {
		return new SimpleTask(name, lifecycle);
	}

	@Override
	protected Task createTask(String name) {
		return new SimpleTask(name);
	}

	@Override
	public TaskType getTaskType() {
		return TaskTypes.Simple;
	}

}
