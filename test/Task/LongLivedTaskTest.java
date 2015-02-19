package Task;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Task.Lifecycle.*;
import Task.State.*;
import java.util.Date;

public class LongLivedTaskTest extends TestCase {
    private LongLivedTask task;

    @Before
    public void setUp() throws Exception {
        Task T = TaskFactory.getTaskFactory().createTask(TaskTypes.LongLived, "Task With Deadline");
        if(T instanceof LongLivedTask){
            task = (LongLivedTask)T;
        }
   }

    @After
    public void tearDown() throws Exception {
        task = null;
    }

    @Test
    public void testLifecycleType() throws Exception{
        assertEquals(task.getLifecycle().getLifecycleType(), TaskLifecycleTypes.InProgress);
    }

    @Test
    public void testTaskType(){
        assertTrue(task.getTaskType() == TaskTypes.LongLived);
    }

    @Test
    public void testSetDueDate(){
        assertFalse(task.hasDueDate());

        Date date = new Date();
        try{
        date.setYear(date.getYear()-1);
        task.setDueDate(date);
        fail("Set Task Date in the past");
        } catch (TaskStateException e) {
            assertEquals(null, task.getDueDate());
        }
        try{
            date = new Date();
            date.setYear(date.getYear()+1);
            task.setDueDate(date);
            assertEquals(task.getDueDate(), date);
        } catch (TaskStateException e) {
            e.printStackTrace();
            fail("setDueDateException");
        }
    }


}