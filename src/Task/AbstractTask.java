package Task;

import java.util.Collection;

import TaskLifecycle.SimpleTaskLifecycle;
import TaskLifecycle.TaskLifecycle;
import TaskLifecycle.TaskState;
import TaskLifecycle.TaskStateException;
import TaskLifecycle.UndefinedTaskLifecycle;


public abstract class AbstractTask implements Task {

	private String description = null;
	private long id = -1;//might need a unique key later for db
	private TaskLifecycle lifecycle = null;
	
	protected AbstractTask(){}
	protected AbstractTask(Exemplar e){};
	protected abstract Task createTask(String name, TaskLifecycle lifecycle);
	protected abstract Task createTask(String name);
	
	protected AbstractTask(String description){
		this.id = 1;
		this.description = description;
		try{
			this.lifecycle =  new SimpleTaskLifecycle();
		} catch (Exception e){
			System.console().printf("Error creating task " + e.getMessage());
		}
		
	}

	protected AbstractTask(String description, TaskLifecycle lifecycle){
		this.id = 1;
		this.description = description;
		if(lifecycle == null){
			lifecycle = new UndefinedTaskLifecycle();
		}
		this.lifecycle =  lifecycle;
	}

	@Override
	public String getToDo() {
		return this.description;
	}

	@Override
	public String setToDo( String toDo) {
		this.description = toDo;
		return this.description;
	}

	@Override
	public long getID() {
		return this.id;
	}

	// Lifecycle access methods
	@Override
	public boolean isValid() {
		return this.lifecycle.isValid();
	}

	@Override
	public boolean isComplete() {
		return lifecycle.isComplete();
	}

	public boolean setState(TaskState newState) throws TaskStateException{
		// TODO implement rules governing promotion through states
		this.lifecycle.setState(newState);
		return true;
	}


	@Override
	public TaskState getStatus() {
		return this.getLifecycle().getState();
	}

	@Override
	public TaskState updateStatus(TaskState newState) throws TaskStateException {		// TODO Auto-generated method stub
		return this.getLifecycle().setState(newState);
	}

	@Override
	public void advanceState() throws TaskStateException {
		this.getLifecycle().advance();
		
	}

	@Override
	public void markComplete() throws TaskStateException {
		this.getLifecycle().setState(TaskState.Completed);
		
	}
	// dependency access methods
	public boolean hasDependentTasks(){
		return this.dependentTaskCount() != 0;
	}

	@Override
	public Collection<Task> getDependentTasks() {
		return TaskDependencyManager.getManager().getDependentTasks(this);
	}

	@Override
	public Collection<Task> getAllDependentTasks() {
		return TaskDependencyManager.getManager().getAllDependentTasks(this);
	}

	@Override
	public void addDependentTasks(Collection<Task> newDependencies) throws TaskDependencyException{
		this.addDependentTasks(newDependencies);
	}

	@Override
	public void addDependentTask(Task dependentTask)
			throws TaskDependencyException {
		TaskDependencyManager.getManager().addDependentTask(new TaskDependency(this, dependentTask));
	}

	@Override
	public TaskLifecycle getLifecycle() {
		return this.lifecycle;
	}

	@Override
	public abstract TaskType getTaskType();

	@Override
	public int dependentTaskCount() {
		Collection<Task> tasks = TaskDependencyManager.getManager().getDependentTasks(this);
		if(tasks != null){
			return tasks.size();
		}
		return 0;
	}

	@Override
	public boolean isExecutable(){
		if(this.isComplete()){
			return false;
		}
		return !TaskDependencyManager.getManager().hasExecutableDependentTask(this);
	}
	
	@Override
	public void rollbackState() throws TaskStateException {
		this.getLifecycle().rollback();
	}

}
