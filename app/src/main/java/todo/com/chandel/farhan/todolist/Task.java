package todo.com.chandel.farhan.todolist;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Task Object to store each to do Task
 */

public class Task {

    private String task;
    private boolean toDelete;
    private int id;
    private static AtomicInteger nextId = new AtomicInteger();

    public int getId() {
        return id;
    }

    public Task(String task) {
        this.id = nextId.incrementAndGet();
        this.task = task;
        this.toDelete = false;

    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public boolean getToDelete() {
        return toDelete;
    }

    public void setToDelete(boolean toDelete) {
        this.toDelete = toDelete;
    }

    @Override
    public String toString() {
        return getTask();
    }
}
