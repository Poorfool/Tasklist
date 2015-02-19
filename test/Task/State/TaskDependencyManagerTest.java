package Task.State;

import static org.junit.Assert.*;


import Task.Dependency.TaskDependencyException;
import Task.Dependency.TaskDependencyLink;
import Task.Dependency.TaskDependencyManager;
import Task.Task;
import Task.TaskFactory;
import Task.TaskTypes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaskDependencyManagerTest {

	Task goHome;
	Task lightsOut;
//		Task closeCurtains;
//		Task openCurtains;
	Task washFace;
	Task dryFace;
	Task wakeUp;
	Task goToBed;
	Task getUp;
	Task goToWork;

	@Before
	public void setUp() throws Exception {
		String GoHome = "Go home";
		String GoToBed = "Go to bed";
		String GetUp = "Get up";
		String GoToWork = "Go to work";
		String LightsOut = "Turn out lights";
//		String CloseCurtains = "Close the curtains";
//		String OpenCurtains = "Open the curtains";
		String WashFace = "Wash face";
		String DryFace = "Dry face";
		String WakeUp = "Wake up";

		goHome = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoHome);

		lightsOut = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				LightsOut);

//		closeCurtains = TaskFactory.getTaskFactory().createTask(
//				TaskTypes.Simple,
//				CloseCurtains);
//
//		openCurtains = TaskFactory.getTaskFactory().createTask(
//				TaskTypes.Simple,
//				OpenCurtains);

		washFace = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				WashFace);

		dryFace = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				DryFace);

		wakeUp = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				WakeUp);

		goToBed = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoToBed);

		getUp = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GetUp);

		goToWork = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoToWork);

		try {
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(getUp, goToBed));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(goToBed, lightsOut));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(goToBed, dryFace));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(dryFace, washFace));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(getUp, wakeUp));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(goToBed, goHome));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(goHome, goToWork));
		} catch (TaskDependencyException e) {
			e.printStackTrace();
			fail("Task Dependency Exception during initialization");
		}

	}

	@After
	public void tearDown() throws Exception {
		goHome = null;
		lightsOut = null;
//		closeCurtains = null;
//		openCurtains = null;
		washFace = null;
		dryFace = null;
		wakeUp = null;
		goToBed = null;
		getUp = null;
		goToWork = null;
	}

	@Test
	public void circularDependencyTest(){
		try {
			TaskDependencyManager.getManager().addDependentTask(new TaskDependencyLink(goToWork, goToBed));
			fail("circular dependency not caught");
		} catch (TaskDependencyException e){
			assertEquals("Circular dependency introduced", e.getMessage());
		}
	}

	@Test
	public void dependentTasksSizeTest() {
		assertEquals(goToBed.getDependentTasks().size(),3);
		assertEquals(goToBed.getAllDependentTasks().size(), 5);
		assertEquals(getUp.getAllDependentTasks().size(), 7);
		assertEquals(dryFace.getAllDependentTasks().size(), 1);
		assertEquals(goToWork.getAllDependentTasks().size(), 0);
		assertEquals(getUp.getDependentTasks().size(),2);

	}

	@Test
	public void isExecutableTest() {

		try {
			assertTrue(goToWork.isExecutable());

			assertFalse(goToBed.isExecutable());
			assertFalse(getUp.isExecutable());
			assertFalse(goHome.isExecutable());
			
			assertTrue(lightsOut.isExecutable());
			assertTrue(washFace.isExecutable());
			assertFalse(lightsOut.isComplete());

			lightsOut.advance();
			assertTrue(lightsOut.isComplete());
			assertFalse(lightsOut.isExecutable());
			assertFalse(dryFace.isExecutable());
			assertFalse(goToBed.isExecutable());

			goToWork.advance();
			assertFalse(goToWork.isExecutable());
			assertTrue(goToWork.isComplete());
			assertFalse(goToBed.isExecutable());
			assertTrue(goHome.isExecutable());

			goHome.advance();
			assertTrue(lightsOut.isComplete());
			assertTrue(goToWork.isComplete());
			assertTrue(goHome.isComplete());
			assertFalse(goToBed.isExecutable());
			assertFalse(dryFace.isExecutable());

			washFace.advance();
			assertTrue(dryFace.isExecutable());

			dryFace.advance();
			assertTrue(goToBed.isExecutable());

			goToBed.advance();
			assertFalse(getUp.isExecutable());
			assertTrue(wakeUp.isExecutable());

			wakeUp.advance();
			assertTrue(getUp.isExecutable());

			getUp.advance();
			assertTrue(getUp.isComplete());
						
		} catch (TaskStateException f){
			f.printStackTrace();
			fail("Task State Exception");
		}
	}

}
