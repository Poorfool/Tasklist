package Task;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import TaskLifecycle.TaskLifecycleTypes;
import TaskLifecycle.TaskStateException;

public class TaskDependencyManagerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
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
		
		Task goHome = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoHome,
				TaskLifecycleTypes.Simple);
		
		Task lightsOut = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				LightsOut,
				TaskLifecycleTypes.Simple);
		
//		Task closeCurtains = TaskFactory.getTaskFactory().createTask(
//				TaskTypes.Simple,
//				CloseCurtains,
//				TaskLifecycleTypes.Simple);
//		
//		Task openCurtains = TaskFactory.getTaskFactory().createTask(
//				TaskTypes.Simple,
//				OpenCurtains,
//				TaskLifecycleTypes.Simple);
		
		Task washFace = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				WashFace,
				TaskLifecycleTypes.Simple);
		
		Task dryFace = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				DryFace,
				TaskLifecycleTypes.Simple);
		
		Task wakeUp = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				WakeUp,
				TaskLifecycleTypes.Simple);
	
		
		Task goToBed = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoToBed,
				TaskLifecycleTypes.Simple);
		
		Task getUp = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GetUp,
				TaskLifecycleTypes.Simple);
		
		Task goToWork = TaskFactory.getTaskFactory().createTask(
				TaskTypes.Simple,
				GoToWork,
				TaskLifecycleTypes.Simple);
		
		try {
			
			assertTrue(goToWork.isExecutable());
			TaskDependencyManager.getManager().addDependentTask(new TaskDependency(getUp, goToBed));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependency(goToBed, lightsOut));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependency(goToBed, dryFace));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependency(dryFace, washFace));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependency(getUp, wakeUp));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependency(goToBed, goHome));
			TaskDependencyManager.getManager().addDependentTask(new TaskDependency(goHome, goToWork));
//			TaskDependencyManager.getManager().addDependency(new TaskDependency(goToWork, getUp));
//			TaskDependencyManager.getManager().addDependency(new TaskDependency(goToWork, goHome));
			
			assertEquals(goToBed.getDependentTasks().size(),3);
			System.out.println("\n\nTesting Assertion");
			assertEquals(goToBed.getAllDependentTasks().size(), 5);
			System.out.println("\n\nTesting Assertion");
			assertEquals(getUp.getAllDependentTasks().size(), 7);
			System.out.println("\n\nTesting Assertion");
			assertEquals(dryFace.getAllDependentTasks().size(), 1);
			assertEquals(goToWork.getAllDependentTasks().size(), 0); 
			assertEquals(getUp.getDependentTasks().size(),2);
			System.out.println("\n\nTesting Assertion 1");
			assertFalse(goToBed.isExecutable());
			System.out.println("\n\nTesting Assertion 2");
			assertFalse(getUp.isExecutable());
			assertFalse(goHome.isExecutable());
			
			assertTrue(lightsOut.isExecutable());
			assertTrue(washFace.isExecutable());
			assertFalse(lightsOut.isComplete());
			lightsOut.advanceState();
			assertTrue(lightsOut.isComplete());
			System.out.println("\n\nTesting Assertion 3");
			assertFalse(lightsOut.isExecutable());
			assertFalse(dryFace.isExecutable());
			System.out.println("\n\nTesting Assertion 4");
			assertFalse(goToBed.isExecutable());
			goToWork.advanceState();
			System.out.println("\n\nTesting Assertion 5");
			assertFalse(goToWork.isExecutable());
			assertTrue(goToWork.isComplete());
			System.out.println("\n\nTesting Assertion 6");
			assertFalse(goToBed.isExecutable());
			System.out.println("\n\nTesting Assertion 7");
			assertTrue(goHome.isExecutable());
			goHome.advanceState();
			assertTrue(lightsOut.isComplete());
			assertTrue(goToWork.isComplete());
			assertTrue(goHome.isComplete());
			System.out.println("\n\nTesting Assertion 8");
			assertFalse(goToBed.isExecutable());
			assertFalse(dryFace.isExecutable());
			washFace.advanceState();
			assertTrue(dryFace.isExecutable());
			dryFace.advanceState();
			assertTrue(goToBed.isExecutable());
			goToBed.advanceState();
			assertFalse(getUp.isExecutable());
			assertTrue(wakeUp.isExecutable());
			wakeUp.advanceState();
			assertTrue(getUp.isExecutable());
			getUp.advanceState();
			assertTrue(getUp.isComplete());
						
		} catch (TaskDependencyException e) {
			e.printStackTrace();
			fail("Task Dependency Exception");
		} catch (TaskStateException f){
			f.printStackTrace();
			fail("Task State Exception");
		}
	}

}
