package todo.com.chandel.farhan.todolist;

import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TaskList taskList = TaskList.getInstance();
    private static final String LIST = "";
    List<Integer> toDelete = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        load();
        refreshView();
        setEnterOnKeyboard();
        setOnClick();
        setOnClickDelete();
        registerContextMenu();
    }

    private void registerContextMenu() {
        ListView taskLW = findViewById(R.id.taskListView);
        registerForContextMenu(taskLW);
    }

    private void load() {
        taskList.getTaskList().clear();
        SharedPreferences sharedPrefs = getSharedPreferences(LIST, 0);
        String listToMake = sharedPrefs.getString("list", "");
        String[] listStringArray = listToMake.split(",");
        if (listStringArray[0] != "") {
            for (String task : listStringArray) {
                addTaskOnLoad(task);
            }
            refreshView();
        }
    }

    private void refreshView() {
        if (taskList.getSize() == 0) {
            TextView textView = findViewById(R.id.emptyList);
            textView.setVisibility(View.VISIBLE);
        }
        if (taskList.getSize() != 0) {
            TextView textView = findViewById(R.id.emptyList);
            textView.setVisibility(View.INVISIBLE);
        }
        ListView taskLW = findViewById(R.id.taskListView);
        List<String> tList = taskList.getTaskListString();
        ArrayAdapter<String> taskArrayAdapter = new ArrayAdapter<String>(this, R.layout.view, tList);
        taskLW.setAdapter(taskArrayAdapter);
    }




    private void setEnterOnKeyboard() {
        final TextInputEditText input = findViewById(R.id.inputTask);
        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    addTask(input);
                    refreshView();
                    input.getText().clear();
                    hideKeyBoard();
                    return true;
                }
                return false;
            }
        });

    }

    private void setOnClick() {
        ListView listView = findViewById(R.id.taskListView);
        final FloatingActionButton delete = findViewById(R.id.deleteBtn);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView checkedTextView = view.findViewById(view.getId());
                checkedTextView.toggle();
                Task task = taskList.getTaskList().get(i);
                System.out.println("ID being added to delete array: " + task.getId());
                toDelete.add(task.getId());
                if (!delete.isShown()) {
                    delete.setVisibility(View.VISIBLE);
                }
                if (toDelete.size() == 0) {
                    delete.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void setOnClickDelete() {
        final FloatingActionButton delete = findViewById(R.id.deleteBtn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTask();
                toDelete.clear();
            }
        });
    }

    private void deleteTask() {
        for (int i = 0; i < toDelete.size(); i++) {
            for (int j = 0; j < taskList.getSize(); j++) {
                if (toDelete.get(i) == taskList.getTaskList().get(j).getId()) {
                    System.out.println(j);
                    taskList.getTaskList().remove(j);
                }
            }
        }
        refreshView();
        save();
    }

    private void addTask(TextInputEditText input) {
        String task = input.getText().toString();
        Task taskToAdd = new Task(task);
        System.out.println(task + "ID: " + taskToAdd.getId());
        taskList.addTask(taskToAdd);
        save();
    }

    private void hideKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    private void save() {
        SharedPreferences sharedPrefs = getSharedPreferences(LIST, 0);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString("list",taskList.getStringList());
        editor.commit();
    }

    private void addTaskOnLoad(String task) {
        Task taskToAdd = new Task(task);
        taskList.addTask(taskToAdd);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        Task task = getTask(item);
        switch (item.getItemId()) {
            case R.id.AdditionalDetails:
                startActivity(AdditionalDetailsActivity.getIntentForLaunch(this, task));
                break;
            default:
                return super.onContextItemSelected(item);
        }

        return true;
    }

    private Task getTask(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = adapterContextMenuInfo.position;
        Task task = taskList.getTaskList().get(position);
        return task;
    }


}
