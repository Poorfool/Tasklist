package Task;

import Task.Lifecycle.*;

public class LongLivedTask extends AbstractTask{


	public LongLivedTask(Exemplar exemplar) {super(exemplar);}


	protected LongLivedTask(String name){
		super(name, LifecycleFactory.getLifecycleFactory().createTaskLifecycle(TaskLifecycleTypes.InProgress));
	}

	@Override
	protected Task createTask(String name) {
		return new LongLivedTask(name);
	}

	@Override
	public TaskType getTaskType() {
		return TaskTypes.LongLived;
	}
}
