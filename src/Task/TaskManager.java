package Task;

import java.util.HashMap;

/**
 * Created by Mike on 27/01/15.
 */
public class TaskManager {

    TaskFactory factory = null;


    HashMap<String, Task> tasklist = null;
    //TODO this should probably be the task ID

    private static TaskManager manager = null;
    private TaskManager(){
        tasklist = new HashMap<>();
        factory = TaskFactory.getTaskFactory();  //initialises the task factory
    };

    public static TaskManager getManager(){
        if(manager == null){
            manager = new TaskManager();
        }
        return manager;
    }


    protected void put(Task task){
        tasklist.put(task.getToDo(), task);
    }

    public Task get(String name){
        return tasklist.get(name);
    }
    public Task get(long ID){return null;}

    public Task createTask(TaskType type, String name) {
        Task task = factory.createTask(type, name);
        this.put(task);
        return task;
    }

    public int taskCount(){ return tasklist.size();}
}
