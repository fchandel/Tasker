package todo.com.chandel.farhan.todolist;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

//    @Test
//    public void addTask() {
//        Task task = new Task("test");
//        assertEquals("test", task.getTask());
//    }
//
//    @Test
//    public void addMultipleTask() {
//        TaskList taskList = TaskList.getInstance();
//        for (int i = 0; i < 50; i++) {
//            Task task = new Task(i + "");
//            assertEquals(task.getTask(), i + "");
//            taskList.addTask(task);
//        }
//        assertEquals(50, taskList.getSize());
//        taskList.getTaskList().clear();
//    }
//
//    @Test
//    public void removeTask() {
//        TaskList taskList = TaskList.getInstance();
//        assertEquals(0, taskList.getSize());
//        Task task = new Task("test");
//        taskList.addTask(task);
//
//        assertEquals(1, taskList.getSize());
//
//        taskList.removeTask(0);
//
//        assertEquals(0, taskList.getSize());
//        taskList.getTaskList().clear();
//
//    }
//
//    @Test
//    public void removeMultipleTask() {
//        TaskList taskList = TaskList.getInstance();
//        List<Integer> toDelete = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            Task task = new Task("t" + i);
//            taskList.addTask(task);
//            toDelete.add(task.getId());
//        }
//        assertEquals(taskList.getSize(), 50);
//
//
//
//
//        for (int i = 0; i < toDelete.size(); i++) {
//            for (int j = 0; j < taskList.getSize(); j++) {
//                if (toDelete.get(i) == taskList.getTaskList().get(j).getId()) {
//                    taskList.getTaskList().remove(j);
//                }
//            }
//        }
//
//
////        Iterator iterator = taskList.iterator();
////        while (iterator.hasNext()) {
////            Task task = (Task) iterator.next();
////            if (task.getToDelete()) {
////                iterator.remove();
////            }
////        }
//
//        assertEquals(taskList.getSize(), 0);
//        taskList.getTaskList().clear();
//
//    }
    @Test
    public void test() {
        Task task = new Task("t");
        TaskList.getInstance().addTask(task);
        function(task);
        assertEquals( "t", TaskList.getInstance().getTaskList().get(0).getTask());
    }

    public void function(Task task) {
        task.setTask("a");
    }

}