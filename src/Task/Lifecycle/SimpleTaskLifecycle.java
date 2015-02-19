package Task.Lifecycle;

import java.util.ArrayList;

import Task.Exemplar;
import Task.State.*;

public class SimpleTaskLifecycle extends AbstractTaskLifecycle {
	
	public SimpleTaskLifecycle(Exemplar e){
		super(e);
	}
	
	public SimpleTaskLifecycle(){
		super();
	}
	
	@Override
	public TaskLifecycleType getLifecycleType() {
		return TaskLifecycleTypes.Simple;
	}
	
	@Override
	protected final ArrayList<TaskLifecycleState> defaultOrder(){
		ArrayList<TaskLifecycleState> defaultOrder = new ArrayList<TaskLifecycleState>();
		defaultOrder.add(TaskLifecycleState.NotStarted);
		defaultOrder.add(TaskLifecycleState.Completed);
		return defaultOrder;		
	}
	
	private SimpleTaskLifecycle(ArrayList<TaskLifecycleState> order, TaskLifecycleState existingState) throws TaskStateException {
		super(order, existingState);
	}
	
	@Override
	public TaskLifecycle createLifecycle(TaskLifecycleState state)
			throws TaskStateException {
		return new SimpleTaskLifecycle(defaultOrder(), state);
	}

	@Override
	public TaskLifecycleState advance() throws TaskStateException {
		if(this.getState() == TaskLifecycleState.NotStarted){
			this.setState(TaskLifecycleState.Completed);
		}else{
			throw new TaskStateException("Advancing Completed Task");
		}
		return this.getState();
	}

	@Override
	public TaskLifecycleState rollback() throws TaskStateException {
		if(this.getState() == TaskLifecycleState.Completed){
			this.setState(TaskLifecycleState.NotStarted);
		} else {
			throw new TaskStateException("Rolled back task not started");
		}
		return this.getState();
	}

}
