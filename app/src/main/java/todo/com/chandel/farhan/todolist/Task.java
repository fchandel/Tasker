package todo.com.chandel.farhan.todolist;

import android.renderscript.RenderScript;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Task Object to store each Task
 */

public class Task {

    private String task;
    private int id;
    private Priority taskPriority;
    private Date dueDate;
    private boolean reminderSet;

    private static AtomicInteger nextId = new AtomicInteger();

    public int getId() {
        return id;
    }

    public Task(String task) {
        this.id = nextId.incrementAndGet();
        this.task = task;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean isReminderSet() {
        return reminderSet;
    }

    public void setReminderSet(boolean reminderSet) {
        this.reminderSet = reminderSet;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setTaskPriority(Priority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public Priority getTaskPriority() {
        return taskPriority;
    }

    @Override
    public String toString() {
        return getTask();
    }
}
