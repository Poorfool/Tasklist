package Task.State;

import static org.junit.Assert.*;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import Task.Lifecycle.*;

public class TaskStateTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TaskLifecycleState state = new BasicState();
		String description = "test";
		assertEquals(state.getDescription(), TaskLifecycleState.UNDEFINED);
		state.setDescription(description);
		assertEquals(state.getDescription(),description);
	}

}
