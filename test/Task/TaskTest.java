package Task;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import TaskLifecycle.TaskLifecycleTypes;
import TaskLifecycle.TaskState;
import TaskLifecycle.TaskStateException;

public class TaskTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		String GoHome = "Go Home";
		
		Task goHome = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoHome,
				TaskLifecycleTypes.Simple);
		
		assertTrue(goHome != null);
		assertEquals(goHome.getToDo(), GoHome);
		assertEquals(goHome.getTaskType(), TaskTypes.Simple);
		assertEquals(goHome.getLifecycle().getType(), TaskLifecycleTypes.Simple);
		assertEquals(goHome.getStatus(), TaskState.NotStarted);
		assertFalse(goHome.isComplete());
		assertTrue(goHome.isValid());
		assertTrue(goHome.isExecutable());
		try {
			goHome.advanceState();
			assertEquals(goHome.getStatus(), TaskState.Completed);
			assertTrue(goHome.isComplete());
			assertFalse(goHome.isExecutable());
		} catch (TaskStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("TaskStateException thrown on Advance State");
		}
	}

}
