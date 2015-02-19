package Task.Lifecycle;

import static org.junit.Assert.*;

import Task.Lifecycle.LifecycleFactory;
import Task.Lifecycle.TaskLifecycle;
import Task.Lifecycle.TaskLifecycleTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LifecycleFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
			TaskLifecycle lifecycle = LifecycleFactory.getLifecycleFactory().createTaskLifecycle(TaskLifecycleTypes.Simple);
			assertTrue(lifecycle.isValid());
	}

}
