package Task.Planning;

import Task.State.TaskStateException;

import java.util.Date;

/**
 * Created by Mike on 12/02/15.
 */
public interface TaskPlanning {
    public void setStartAfter(Date startDate, boolean immovable) throws TaskStateException;
    public void setStartAfter(Date startDate /* boolean immovable = true */) throws TaskStateException;
    public Date getStartDate();
    public boolean hasStartDate();
    public boolean immovableStartDate();

    public void setEndBefore(Date date, boolean immovable) throws TaskStateException;
    public void setEndBefore(Date date) throws TaskStateException;
    public Date getEndDate();
    public boolean hasEndDate();
    public boolean immovableEndDate();

    public void setEstimate(long days);
    public long getEstimate();
    public void setActuals(long days);
    public long getActuals();

    public void lockDates(boolean lockState);
    public boolean hasLockedDates();


}
