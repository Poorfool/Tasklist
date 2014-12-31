package TaskLifecycle;

import java.util.ArrayList;

import Task.Exemplar;

public abstract class AbstractTaskLifecycle implements TaskLifecycle {
	protected AbstractTaskLifecycle(Exemplar e){}
	protected AbstractTaskLifecycle(){}
	
	@Override
	public abstract TaskLifecycleType getType();
	
	
	protected abstract ArrayList<TaskState> defaultOrder();
	
	private TaskState state = null;
	private ArrayList<TaskState> lifecycle = null;
	
	
	protected AbstractTaskLifecycle(ArrayList<TaskState> order, TaskState existingState) throws TaskStateException{
		
		this.lifecycle = order;
		
		if(this.lifecycle.contains(existingState)){
			this.state = existingState.getState();
		}else{
			throw new TaskStateException("State does not exist in this lifecycle");
		}
	}

	
	@Override
	public TaskState getState(){
		return this.state;
	}
	
	
	@Override
	public ArrayList<TaskState> getStates(){
		return this.lifecycle;
	}

	@Override
	public boolean isComplete() {
			return this.getState() == TaskState.Completed;
	}

	@Override
	public abstract TaskState advance() throws TaskStateException; 

	@Override
	public abstract TaskState rollback() throws TaskStateException;

	@Override
	public TaskState setState(TaskState newState) throws TaskStateException {
		if(this.lifecycle.contains(newState)){
			this.state = newState;
		} else {
			throw new TaskStateException(newState.getDescription() + " - no such state");
		}
		return this.getState();
	}


	@Override
	public boolean isValid() {
		return null != this.getState();
	}


	public TaskLifecycle createLifecycle(){
		try {
			return createLifecycle(TaskState.NotStarted);
		} catch (TaskStateException e) {
			return null;
		}
	}

	public abstract TaskLifecycle createLifecycle(TaskState state) throws TaskStateException;
}
