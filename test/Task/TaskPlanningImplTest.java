package Task;

import Task.Planning.TaskPlanningImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/** 
* TaskPlanningImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>Feb 13, 2015</pre> 
* @version 1.0 
*/ 
public class TaskPlanningImplTest { 

    private TaskPlanningImpl planning = null;
@Before
public void before() throws Exception {
    planning = new TaskPlanningImpl();
} 

@After
public void after() throws Exception {
    planning = null;
} 

/** 
* 
* Method: setEndBefore(Date date, boolean locked) 
* 
*/ 
@Test
public void testSetEndBeforeForDateLocked() throws Exception {
    Date d1 = new Date();
} 

/** 
* 
* Method: setEndBefore(Date date) 
* 
*/ 
@Test
public void testSetEndBeforeDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getEndDate() 
* 
*/ 
@Test
public void testGetEndDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: hasEndDate() 
* 
*/ 
@Test
public void testHasEndDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: immovableEndDate() 
* 
*/ 
@Test
public void testImmovableEndDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setEstimate(long days) 
* 
*/ 
@Test
public void testSetEstimate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getEstimate() 
* 
*/ 
@Test
public void testGetEstimate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setActuals(long days) 
* 
*/ 
@Test
public void testSetActuals() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getActuals() 
* 
*/ 
@Test
public void testGetActuals() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: lockDates(boolean lockState) 
* 
*/ 
@Test
public void testLockDates() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: hasLockedDates() 
* 
*/ 
@Test
public void testHasLockedDates() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setStartAfter(Date date, boolean locked) 
* 
*/ 
@Test
public void testSetStartAfterForDateLocked() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: setStartAfter(Date date) 
* 
*/ 
@Test
public void testSetStartAfterDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getStartDate() 
* 
*/ 
@Test
public void testGetStartDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: hasStartDate() 
* 
*/ 
@Test
public void testHasStartDate() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: immovableStartDate() 
* 
*/ 
@Test
public void testImmovableStartDate() throws Exception { 
//TODO: Test goes here... 
} 


} 
