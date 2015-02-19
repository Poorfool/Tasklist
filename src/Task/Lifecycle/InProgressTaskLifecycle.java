package Task.Lifecycle;
import java.util.ArrayList;

import Task.Exemplar;
import Task.State.*;


public class InProgressTaskLifecycle extends AbstractTaskLifecycle {
	
	public InProgressTaskLifecycle(Exemplar e){
		super(e);
	}
	
	@Override
	public TaskLifecycleType getLifecycleType() {
		return TaskLifecycleTypes.InProgress;
	}
	
	
	protected ArrayList<TaskLifecycleState> defaultOrder(){
		ArrayList<TaskLifecycleState> defaultOrder = new ArrayList<TaskLifecycleState>();
		defaultOrder.add(TaskLifecycleState.NotStarted);
		defaultOrder.add(TaskLifecycleState.InProgress);
		defaultOrder.add(TaskLifecycleState.Completed);
		return defaultOrder;		
	}
	
	
	public InProgressTaskLifecycle(){
		super();
	}
	
	private InProgressTaskLifecycle(ArrayList<TaskLifecycleState> order, TaskLifecycleState existingState) throws TaskStateException {
		super(order, existingState);
	}

	@Override
	public TaskLifecycleState advance() throws TaskStateException {
		if(this.getStates() == null || this.getState() == TaskLifecycleState.Completed)
			throw new TaskStateException("Attempted to advance completed task");
		return this.setState(this.getStates().get(this.getStates().indexOf(getState())+1));
	}

	@Override
	public TaskLifecycleState rollback() throws TaskStateException {
		switch(this.getState().getDescription()){
			case TaskLifecycleState.NOT_STARTED :
				throw new TaskStateException("Rolled back task not started");
		case TaskLifecycleState.IN_PROGRESS :
				this.setState(TaskLifecycleState.NotStarted);
			break;
			case TaskLifecycleState.COMPLETED :
				this.setState(TaskLifecycleState.InProgress);
			break;
			default: throw new TaskStateException("Rollback state unknown");
		}
		
		return this.getState();
				
	}

	@Override
	public TaskLifecycle createLifecycle(TaskLifecycleState state)
			throws TaskStateException {
		return new InProgressTaskLifecycle(defaultOrder(), state);
	}



	
}
