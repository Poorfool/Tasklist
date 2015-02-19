package Task.Lifecycle;

import java.util.ArrayList;

import Task.Exemplar;
import Task.State.TaskStateException;

public abstract class AbstractTaskLifecycle implements TaskLifecycle {
	protected AbstractTaskLifecycle(Exemplar e){}
	protected AbstractTaskLifecycle(){}
	
	@Override
	public abstract TaskLifecycleType getLifecycleType();
	
	
	protected abstract ArrayList<TaskLifecycleState> defaultOrder();
	
	private TaskLifecycleState state = null;
	private ArrayList<TaskLifecycleState> lifecycle = null;
	
	
	protected AbstractTaskLifecycle(ArrayList<TaskLifecycleState> order, TaskLifecycleState existingState) throws TaskStateException {
		
		this.lifecycle = order;
		
		if(this.lifecycle.contains(existingState)){
			this.state = existingState.getState();
		}else{
			throw new TaskStateException("State does not exist in this lifecycle");
		}
	}

	
	@Override
	public TaskLifecycleState getState(){
		return this.state;
	}

	protected ArrayList<TaskLifecycleState> getStates(){
		return this.lifecycle;
	}

	@Override
	public boolean isComplete() {
			return this.getState() == TaskLifecycleState.Completed;
	}

	@Override
	public TaskLifecycleState markComplete() throws TaskStateException{
		return this.setState(TaskLifecycleState.Completed);
	}

	@Override
	public abstract TaskLifecycleState advance() throws TaskStateException;

	@Override
	public abstract TaskLifecycleState rollback() throws TaskStateException;

	@Override
	public TaskLifecycleState setState(TaskLifecycleState newState) throws TaskStateException {
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
			return createLifecycle(TaskLifecycleState.NotStarted);
		} catch (TaskStateException e) {
			return null;
		}
	}

	public abstract TaskLifecycle createLifecycle(TaskLifecycleState state) throws TaskStateException;
}
