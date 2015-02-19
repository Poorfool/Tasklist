package Task;

import java.util.Collection;
import java.util.Date;

import Task.Dependency.TaskDependencyException;
import Task.Dependency.TaskDependencyLink;
import Task.Dependency.TaskDependencyManager;
import Task.Lifecycle.TaskLifecycle;
import Task.Lifecycle.TaskLifecycleState;
import Task.Lifecycle.TaskLifecycleType;
import Task.Lifecycle.UndefinedTaskLifecycle;
import Task.State.*;


public abstract class AbstractTask implements Task{

	private String description = null;
	private long id = -1;//might need a unique key later for db
	private TaskLifecycle lifecycle = null;
	
	protected AbstractTask(){}
	protected AbstractTask(Exemplar e){};
	protected abstract Task createTask(String name);

	@Override
	public abstract TaskType getTaskType();

//	protected AbstractTask(String description){
//		this.id = 1;
//		this.description = description;
//		try{
//			this.lifecycle =  new SimpleTaskLifecycle();
//		} catch (Exception e){
//			System.console().printf("Error creating task " + e.getMessage());
//		}
//
//	}

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

	// Task.Lifecycle access methods
	@Override
	public boolean isValid() {
		return this.lifecycle.isValid();
	}

	@Override
	public TaskLifecycleType getLifecycleType() {
		return this.lifecycle.getLifecycleType();
	}

	@Override
	public boolean isComplete() {
		return lifecycle.isComplete();
	}

	@Override
	public TaskLifecycleState getState() {
		return this.getLifecycle().getState();
	}

	@Override
	public TaskLifecycleState setState(TaskLifecycleState newState) throws TaskStateException {		// TODO Auto-generated method stub
		return this.getLifecycle().setState(newState);
	}

	@Override
	public TaskLifecycleState advance() throws TaskStateException {
		return this.getLifecycle().advance();
		
	}

	@Override
	public TaskLifecycleState markComplete() throws TaskStateException {
		return this.getLifecycle().setState(TaskLifecycleState.Completed);
		
	}
	// dependency access methods
	public boolean hasDependentTasks(){
		return this.dependentTaskCount() != 0;
	}

	@Override
	public Collection<? extends Task> getDependentTasks() {
		return TaskDependencyManager.getManager().getDependentTasks(this);
	}

	@Override
	public Collection<? extends Task> getAllDependentTasks() {
		return TaskDependencyManager.getManager().getAllDependentTasks(this);
	}

	@Override
	public void addDependentTasks(Collection<? extends Task> newDependencies) throws TaskDependencyException {
		for(Task T : newDependencies){
			this.addDependentTask(T);
		}
	}

	@Override
	public void removeDependentTask(Task dependentTask)
			throws TaskDependencyException {
		TaskDependencyManager.getManager().removeDependentTask(new TaskDependencyLink(this, dependentTask));
	}

	@Override
	public void removeDependentTasks(Collection<? extends Task> newDependencies) throws TaskDependencyException {
		for(Task T : newDependencies){
			this.removeDependentTask(T);
		}
	}

	@Override
	public void addDependentTask(Task dependentTask)
			throws TaskDependencyException {
		TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(this, dependentTask));
	}

	@Override
	public TaskLifecycle getLifecycle() {
		return this.lifecycle;
	}


	@Override
	public int dependentTaskCount() {
		Collection<? extends Task> tasks = TaskDependencyManager.getManager().getDependentTasks(this);
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
	public TaskLifecycleState rollback() throws TaskStateException {
		return this.getLifecycle().rollback();
	}


	//Due Date methods
	private Date due = null;
	@Override
	public boolean hasDueDate(){
		return this.due != null;
	}

	@Override
	public Date getDueDate(){
		return this.due;
	}

	@Override
	public Date setDueDate(Date newDeadline) throws TaskStateException {
		Date today = new Date();

		if(newDeadline.after(today)){ //cannot set a due date before now...
			due = newDeadline;
		}else {
			throw new TaskStateException("Due date in the past");
		}
		return due;
	}


	@Override
	public void finalize(){
		Collection<? extends Task> dependencies = this.getDependentTasks();
		if(null!=dependencies){
			try{
				this.removeDependentTasks(dependencies);
			} catch (TaskDependencyException e){
				e.printStackTrace();
			}
		}
	}

}
