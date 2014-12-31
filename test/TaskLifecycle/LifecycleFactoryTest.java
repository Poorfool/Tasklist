package TaskLifecycle;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import TaskLifecycle.LifecycleFactory;
import TaskLifecycle.TaskLifecycle;

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
