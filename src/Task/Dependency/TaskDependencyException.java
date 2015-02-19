package Task.Dependency;

public class TaskDependencyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TaskDependencyException(){
		super();
	}
	public TaskDependencyException(String text){
		super(text);
	}

}
