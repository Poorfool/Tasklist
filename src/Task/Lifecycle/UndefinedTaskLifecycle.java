package Task.Lifecycle;

import java.util.ArrayList;

import Task.Exemplar;
import Task.State.TaskStateException;


public class UndefinedTaskLifecycle extends AbstractTaskLifecycle {

	public UndefinedTaskLifecycle(){}
	
	@Override
	public TaskLifecycleState getState(){
		return null;
	}

	@Override
	public boolean isComplete() {
		return false;
	}

	@Override
	public TaskLifecycleState advance() throws TaskStateException {
		throw new TaskStateException("undefined Task.State");
	}

	@Override
	public TaskLifecycleState setState(TaskLifecycleState newState) throws TaskStateException {
		throw new TaskStateException("undefined Task.State");
	}

	@Override
	public TaskLifecycleState rollback() throws TaskStateException {
		throw new TaskStateException("undefined Task.State");
	}

	@Override
	public boolean isValid() {
		return false;
	}
	
	
	public UndefinedTaskLifecycle(Exemplar e){
		super(e);
	}

	@Override
	public TaskLifecycle createLifecycle(){
		return new UndefinedTaskLifecycle();
	}

	@Override
	public TaskLifecycle createLifecycle(TaskLifecycleState state)
			throws TaskStateException {
		return new UndefinedTaskLifecycle();
	}

	@Override
	public TaskLifecycleType getLifecycleType() {
		return TaskLifecycleTypes.Undefined;
	}

	@Override
	protected ArrayList<TaskLifecycleState> defaultOrder() {
		return null;
	}

}
