package TaskLifecycle;
public class TaskStateException extends Exception{		
	private static final long serialVersionUID = 1L;
	public TaskStateException(){
		super();
	}
	public TaskStateException(String text){
		super(text);
	}
}
	
