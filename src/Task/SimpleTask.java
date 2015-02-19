package Task;

import Task.Lifecycle.*;

public class SimpleTask extends AbstractTask {

	protected SimpleTask() {
		super();
	}

	public SimpleTask(Exemplar e) {
		super(e);
	}

	protected SimpleTask(String name) {
		super(name, LifecycleFactory.getLifecycleFactory().createTaskLifecycle(TaskLifecycleTypes.Simple));
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