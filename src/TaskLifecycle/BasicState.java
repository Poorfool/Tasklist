package TaskLifecycle;



public class BasicState implements TaskState {
	
	private String description;
	
	public BasicState(){
		this.description = TaskState.UNDEFINED;
	}
	
	public BasicState(String currentState){
		this.description = currentState;
	}
	
	public BasicState(BasicState state){
		this.description = state.getDescription();
	}
	@Override
	public final TaskState getState() {
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
