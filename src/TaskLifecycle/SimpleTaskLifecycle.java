package TaskLifecycle;

import java.util.ArrayList;

import Task.Exemplar;

public class SimpleTaskLifecycle extends AbstractTaskLifecycle {
	
	public SimpleTaskLifecycle(Exemplar e){
		super(e);
	}
	
	public SimpleTaskLifecycle(){
		super();
	}
	
	@Override
	public TaskLifecycleType getType() {
		return TaskLifecycleTypes.Simple;
	}
	
	@Override
	protected final ArrayList<TaskState> defaultOrder(){
		ArrayList<TaskState> defaultOrder = new ArrayList<TaskState>();
		defaultOrder.add(TaskState.NotStarted);
		defaultOrder.add(TaskState.Completed);
		return defaultOrder;		
	}
	
	private SimpleTaskLifecycle(ArrayList<TaskState> order, TaskState existingState) throws TaskStateException{
		super(order, existingState);
	}
	
	@Override
	public TaskLifecycle createLifecycle(TaskState state)
			throws TaskStateException {
		return new SimpleTaskLifecycle(defaultOrder(), state);
	}

	@Override
	public TaskState advance() throws TaskStateException {
		if(this.getState() == TaskState.NotStarted){
			this.setState(TaskState.Completed);
		}else{
			throw new TaskStateException("Advancing Completed Task");
		}
		return this.getState();
	}

	@Override
	public TaskState rollback() throws TaskStateException {
		if(this.getState() == TaskState.Completed){
			this.setState(TaskState.NotStarted);
		} else {
			throw new TaskStateException("Rolled back task not started");
		}
		return this.getState();
	}

}
