package Task.Planning;

import Task.State.TaskStateException;

import java.util.Date;

/**
 * Created by Mike on 12/02/15.
 */
public class TaskPlanningImpl implements TaskPlanning {

    private Date startDate = null;
    private boolean startLocked = false;
    private Date endDate = null;
    private boolean endLocked = false;

    private boolean allLocked = false;

    private long estimate = 0;
    private long actuals = 0;

    public TaskPlanningImpl(){}


    @Override
    public void setEndBefore(Date date, boolean locked) throws TaskStateException {
        if (allLocked){
            throw new TaskStateException("Attempting to set an End Date on a locked task");
        }
        if(startDate != null && startDate.after(date)){
            throw new TaskStateException("End Date before Start date");
        }
        if(date.before(new Date())){ //This WILL fail if the end date is set to "now"
            throw new TaskStateException("End Date in the past");
        }
        endDate = date;
        endLocked = locked;
    }

    @Override
    public void setEndBefore(Date date) throws TaskStateException {
        this.setEndBefore(date, false);
    }

    @Override
    public Date getEndDate() {
        return this.endDate;
    }

    @Override
    public boolean hasEndDate() {
        return endDate != null;
    }

    @Override
    public boolean immovableEndDate() {
        return endLocked;
    }

    @Override
    public void setEstimate(long days) {
        this.estimate = days;
    }

    @Override
    public long getEstimate() {
        return this.estimate;
    }

    @Override
    public void setActuals(long days) {
        this.actuals = days;
    }

    @Override
    public long getActuals() {
        return this.actuals;
    }

    @Override
    public void lockDates(boolean lockState) {
        this.allLocked = lockState;
    }

    @Override
    public boolean hasLockedDates() {
        return allLocked;
    }

    @Override
    public void setStartAfter(Date date, boolean locked) throws TaskStateException{
        if(allLocked){
            throw new TaskStateException("Attempting to set StartDate on a Locked task");
            }
        if(endDate != null && endDate.before(date)){
            throw new TaskStateException("Start Date after End date");
        }
        startDate = date;
    }

    @Override
    public void setStartAfter(Date date) throws TaskStateException{
        this.setEndBefore(date, false);
    }

    @Override
    public Date getStartDate() {
        return this.startDate;
    }

    @Override
    public boolean hasStartDate() {
        return this.startDate != null;
    }

    @Override
    public boolean immovableStartDate() {
        return this.startLocked;
    }
}
