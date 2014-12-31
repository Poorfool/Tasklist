package TaskLifecycle;

import java.util.ArrayList;

import Task.Exemplar;


public class UndefinedTaskLifecycle extends AbstractTaskLifecycle {

	public UndefinedTaskLifecycle(){}
	
	@Override
	public TaskState getState(){
		return null;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TaskState advance() throws TaskStateException {
		throw new TaskStateException("undefined TaskLifecycle");
	}

	@Override
	public TaskState setState(TaskState newState) throws TaskStateException {
		throw new TaskStateException("undefined TaskLifecycle");
	}

	@Override
	public TaskState rollback() throws TaskStateException {
		throw new TaskStateException("undefined TaskLifecycle");
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
	public TaskLifecycle createLifecycle(TaskState state)
			throws TaskStateException {
		return new UndefinedTaskLifecycle();
	}

	@Override
	public TaskLifecycleType getType() {
		return TaskLifecycleTypes.Undefined;
	}

	@Override
	protected ArrayList<TaskState> defaultOrder() {
		return null;
	}

}
