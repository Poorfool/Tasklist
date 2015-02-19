package Task.Lifecycle;

import static org.junit.Assert.*;

import Task.Lifecycle.TaskLifecycle;
import Task.Lifecycle.UndefinedTaskLifecycle;
import Task.State.TaskStateException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UndefinedTaskLifecycleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TaskLifecycle test = new UndefinedTaskLifecycle();
		assertNull(test.getState());
		assertFalse(test.isValid());
		assertFalse(test.isComplete());
		try{
			test.advance();
			fail("Didn't throw TaskStateException");
		} catch (TaskStateException e){
			assertNotNull(e);
		}
	}

}
