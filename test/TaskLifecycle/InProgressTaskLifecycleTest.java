package TaskLifecycle;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import TaskLifecycle.LifecycleFactory;
import TaskLifecycle.TaskLifecycle;
import TaskLifecycle.TaskLifecycleTypes;

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

			lifecycle.setState(TaskState.NotStarted);
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskState.InProgress);
			assertTrue(lifecycle.getState() == TaskState.NotStarted);
						
			lifecycle.advance();
			lifecycle.rollback();
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskState.InProgress);
			assertTrue(lifecycle.getState() == TaskState.NotStarted);
			
		} catch (TaskStateException e) {
			fail("TastStateException Thrown : " + e.getMessage());
		}
		
		try{
			lifecycle.rollback();  //rollback applied to NotStarted Task
			fail("TastStateException not thrown");			
		} catch(TaskStateException e) {
			assertTrue(e.getMessage() == "Rolled back task not started");
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskState.InProgress);
			assertTrue(lifecycle.getState() == TaskState.NotStarted);
		}
		
		try{

			lifecycle.advance();  // to in progress
			assertFalse(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertFalse(lifecycle.getState() == TaskState.NotStarted);
			assertTrue(lifecycle.getState() == TaskState.InProgress);
			
			lifecycle.advance();  // to Completed
			assertTrue(lifecycle.isComplete());
			assertTrue(lifecycle.isValid());
			assertTrue(lifecycle.getState() == TaskState.Completed);
			assertFalse(lifecycle.getState() == TaskState.NotStarted);
			assertFalse(lifecycle.getState() == TaskState.InProgress);
								
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
			assertFalse(lifecycle.getState() == TaskState.NotStarted);
			assertTrue(lifecycle.getState() == TaskState.InProgress);
		} catch (TaskStateException e) {
			fail("Exception Thrown : " + e.getMessage());
			e.printStackTrace();
		}
	}

}
