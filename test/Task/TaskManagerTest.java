package Task;

import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaskManagerTest extends TestCase {
    TaskManager manager = null;
    Task task = null;

    @Before
    public void setUp() throws Exception {
        manager = TaskManager.getManager();
        task = manager.createTask(TaskTypes.Simple,"Something Simple");
    }

    @After
    public void tearDown() throws Exception {
        manager = null;
    }

    @Test
    public void testGetManager() throws Exception {
        TaskManager TM = TaskManager.getManager();
        assertTrue(TM instanceof TaskManager);
        assertNull(TM.get("test"));
        assertEquals(TM.get("Something Simple"), task);

    }

    @Test
    public void testPut() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testGet1() throws Exception {

    }

    @Test
    public void testCreateTask() throws Exception {
        String local = "Local Test";
        Task test = manager.createTask(TaskTypes.Simple,local);
        assertTrue(test.isValid());
        assertEquals(manager.get(local), test);

    }
}