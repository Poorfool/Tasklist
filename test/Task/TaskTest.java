package Task;

import Task.Lifecycle.TaskLifecycleState;
import Task.Lifecycle.TaskLifecycleTypes;
import Task.State.TaskStateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskTest {

	private Task goHome;
	private String GoHome = "Go Home";
	@Before
	public void setUp() throws Exception {

		goHome = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoHome);
	}

	@After
	public void tearDown() throws Exception {
		goHome=null;
	}

	@Test
	public void test() {

		assertTrue(goHome != null);
		assertEquals(goHome.getToDo(), GoHome);
		assertEquals(goHome.getTaskType(), TaskTypes.Simple);
		assertEquals(goHome.getLifecycle().getLifecycleType(), TaskLifecycleTypes.Simple);
		assertEquals(goHome.getState(), TaskLifecycleState.NotStarted);
		assertFalse(goHome.isComplete());
		assertTrue(goHome.isValid());
		assertTrue(goHome.isExecutable());
		try {
			goHome.advance();
			assertEquals(goHome.getState(), TaskLifecycleState.Completed);
			assertTrue(goHome.isComplete());
			assertFalse(goHome.isExecutable());
		} catch (TaskStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("TaskStateException thrown on Advance State");
		}
	}

}
