package Task.Lifecycle;

import Task.State.TaskStateException;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleTaskLifecycleTest extends TestCase {

    TaskLifecycle lifecycle;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        lifecycle = LifecycleFactory.getLifecycleFactory().createTaskLifecycle(TaskLifecycleTypes.Simple);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(lifecycle.getLifecycleType(),TaskLifecycleTypes.Simple);

    }

    @Test
    public void testDefaultOrder() throws Exception {
        assertEquals(lifecycle.getState(), TaskLifecycleState.NotStarted);

    }

    @Test
    public void testCreateLifecycle() throws Exception {
        TaskLifecycle cycle = LifecycleFactory.getLifecycleFactory().createTaskLifecycle(TaskLifecycleTypes.Simple);
        assertTrue(cycle.isValid());

    }

    @Test
    public void testAdvance() throws Exception {
        try {
            lifecycle.setState(TaskLifecycleState.NotStarted);
            assertEquals(lifecycle.advance(), TaskLifecycleState.Completed);
        } catch (TaskStateException e){
            fail("exception thrown during advance");
        }
        try {
            assertEquals(lifecycle.advance(), TaskLifecycleState.Completed);
            fail("exception NOT thrown during advance");
        } catch (TaskStateException e){
            // do nothing
        }
    }

    @Test
    public void testRollback() throws Exception {
        try {
            lifecycle.setState(TaskLifecycleState.Completed);
            assertEquals(lifecycle.rollback(), TaskLifecycleState.NotStarted);
        } catch (TaskStateException e){
            fail("exception thrown during rollback");
        }
        try {
            assertEquals(lifecycle.rollback(), TaskLifecycleState.NotStarted);
            fail("exception NOT thrown during advance");
        } catch (TaskStateException e){
            //do nothing
        }

    }
}