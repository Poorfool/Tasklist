package Task.Lifecycle;
import static org.junit.Assert.*;

import Task.Lifecycle.LifecycleFactory;
import Task.Lifecycle.TaskLifecycle;
import Task.Lifecycle.TaskLifecycleState;
import Task.Lifecycle.TaskLifecycleTypes;
import Task.State.TaskStateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InProgressTaskLifecycleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TaskLifecycle lifecycle = null;
		try {
			lifecycle = LifecycleFactory.getLifecycleFactory().createTaskLifecycle(TaskLifecycleTypes.InProgress);
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertTrue(lifecycle.getState().getDescription() == "not started");
			
			lifecycle.advance(); //to "in progress"
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertTrue(lifecycle.getState().getDescription() == "in progress");

			lifecycle.setState(TaskLifecycleState.NotStarted);
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskLifecycleState.InProgress);
			assertTrue(lifecycle.getState() == TaskLifecycleState.NotStarted);
						
			lifecycle.advance();
			lifecycle.rollback();
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskLifecycleState.InProgress);
			assertTrue(lifecycle.getState() == TaskLifecycleState.NotStarted);
			
		} catch (TaskStateException e) {
			fail("TastStateException Thrown : " + e.getMessage());
		}
		
		try{
			lifecycle.rollback();  //rollback applied to NotStarted Task
			fail("TastStateException not thrown");			
		} catch(TaskStateException e) {
			assertTrue(e.getMessage() == "Rolled back task not started");
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskLifecycleState.InProgress);
			assertTrue(lifecycle.getState() == TaskLifecycleState.NotStarted);
		}
		
		try{

			lifecycle.advance();  // to in progress
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskLifecycleState.NotStarted);
			assertTrue(lifecycle.getState() == TaskLifecycleState.InProgress);
			
			lifecycle.advance();  // to Completed
			assertTrue(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertTrue(lifecycle.getState() == TaskLifecycleState.Completed);
			assertFalse(lifecycle.getState() == TaskLifecycleState.NotStarted);
			assertFalse(lifecycle.getState() == TaskLifecycleState.InProgress);
								
		} catch (TaskStateException e) {
			fail("Exception Thrown : " + e.getMessage());
			e.printStackTrace();
		}
		
		try {
			lifecycle.advance();
			fail("Exception Thrown");
		} catch (TaskStateException e) {
			assertTrue(e.getMessage() == "Attempted to advance completed task");
		}
		
		try{
			lifecycle.rollback();
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskLifecycleState.NotStarted);
			assertTrue(lifecycle.getState() == TaskLifecycleState.InProgress);
		} catch (TaskStateException e) {
			fail("Exception Thrown : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
