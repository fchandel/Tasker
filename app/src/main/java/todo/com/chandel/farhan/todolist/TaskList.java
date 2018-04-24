package todo.com.chandel.farhan.todolist;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Handles a List of Task
 */

public class TaskList implements Iterable {

    List<Task> taskList;
    static TaskList instance = new TaskList();

    private TaskList() {
        this.taskList = new ArrayList<>();
    }

    public static TaskList getInstance() { return instance; }

    public List<Task> getTaskList() {
        return instance.taskList;
    }

    public List<String> getTaskListString() {

        List<String> taskListString = new ArrayList<String>();

        for (int i = 0; i < instance.taskList.size(); i++) {
            taskListString.add(getTask(i));
        }

        return taskListString;
    }

    public void setTaskList(List<Task> taskList) {
        instance.taskList = taskList;
    }

    public void addTask(Task taskToAdd) {
        instance.taskList.add(taskToAdd);
    }

    public int getSize() {
        return instance.taskList.size();
    }

    public void removeTask(int index) {
        validateIndex(index);
        instance.taskList.remove(index);
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= instance.taskList.size()) {
            throw new IllegalArgumentException();
        }
    }

    private String getTask(int index) {
        validateIndex(index);
        String task = instance.taskList.get(index).getTask();
        return task;
    }

    public String getStringList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : instance.taskList) {
            stringBuilder.append(task);
            stringBuilder.append(",");
        }

        return stringBuilder.toString();
    }


    @NonNull
    @Override
    public Iterator iterator() {
        return new Iterate();
    }

    public class Iterate implements Iterator {
        int position;

        public Iterate() {
            position = 0;
        }
        @Override
        public boolean hasNext() {
            System.out.println((position < instance.taskList.size()) + " : condition value" + " position: " + position + " size: " + instance.taskList.size());
            return position < instance.taskList.size();
        }

        @Override
        public Task next() {
            return instance.taskList.get(position++);
        }

        @Override
        public void remove() {
            System.out.println(position + "index at remove()");
            instance.taskList.remove(position);
        }
    }
}
