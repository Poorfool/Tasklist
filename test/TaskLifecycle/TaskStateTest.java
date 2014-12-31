package TaskLifecycle;
import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;




public class TaskStateTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		TaskState state = new BasicState();
		String description = "test";
		assertEquals(state.getDescription(), TaskState.UNDEFINED);
		state.setDescription(description);
		assertEquals(state.getDescription(),description);
	}

}
