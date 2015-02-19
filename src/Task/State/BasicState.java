package Task.State;


import Task.Lifecycle.TaskLifecycleState;

public class BasicState implements TaskLifecycleState {
	
	private String description;
	
	public BasicState(){
		this.description = TaskLifecycleState.UNDEFINED;
	}
	
	public BasicState(String currentState){
		this.description = currentState;
	}
	
	public BasicState(BasicState state){
		this.description = state.getDescription();
	}
	@Override
	public final TaskLifecycleState getState() {
		return this;
	}

	@Override
	public final String getDescription() {
		return this.description;
	}
	
	@Override
	public String setDescription(final String description){
		this.description = description;
		return this.description;
	}
	

}
