package TaskLifecycle;
import java.util.ArrayList;

import Task.Exemplar;


public class InProgressTaskLifecycle extends AbstractTaskLifecycle{
	
	public InProgressTaskLifecycle(Exemplar e){
		super(e);
	}
	
	@Override
	public TaskLifecycleType getType() {
		return TaskLifecycleTypes.InProgress;
	}
	
	
	protected ArrayList<TaskState> defaultOrder(){
		ArrayList<TaskState> defaultOrder = new ArrayList<TaskState>();
		defaultOrder.add(TaskState.NotStarted);
		defaultOrder.add(TaskState.InProgress);
		defaultOrder.add(TaskState.Completed);
		return defaultOrder;		
	}
	
	
	public InProgressTaskLifecycle(){
		super();
	}
	
	private InProgressTaskLifecycle(ArrayList<TaskState> order, TaskState existingState) throws TaskStateException{
		super(order, existingState);
	}

	@Override
	public TaskState advance() throws TaskStateException {
		if(this.getStates() == null || this.getState() == TaskState.Completed)
			throw new TaskStateException("Attempted to advance completed task");
		return this.setState(this.getStates().get(this.getStates().indexOf(getState())+1));
	}

	@Override
	public TaskState rollback() throws TaskStateException {
		switch(this.getState().getDescription()){
			case TaskState.NOT_STARTED :
				throw new TaskStateException("Rolled back task not started");
		case TaskState.IN_PROGRESS :
				this.setState(TaskState.NotStarted);
			break;
			case TaskState.COMPLETED :
				this.setState(TaskState.InProgress);		
			break;
			default: throw new TaskStateException("Rollback state unknown");
		}
		
		return this.getState();
				
	}

	@Override
	public TaskLifecycle createLifecycle(TaskState state)
			throws TaskStateException {
		return new InProgressTaskLifecycle(defaultOrder(), state);
	}



	
}
