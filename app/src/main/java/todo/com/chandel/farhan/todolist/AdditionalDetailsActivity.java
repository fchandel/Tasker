package todo.com.chandel.farhan.todolist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AdditionalDetailsActivity extends AppCompatActivity  {

    private static Task currentTask;
    private Switch reminderSwitch;
    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_details);
        logDate();
        setupSpinner();
        setupSwitch();
        setupCancel();
        setupSave();
    }

    private void setupSave() {
        TextView save = findViewById(R.id.save_action);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (reminderSwitch.isChecked()) {
                    if (currentTask.getDueDate() != null) {
                        currentTask.setReminderSet(true);
                    }
                }

                currentTask.setTaskPriority(getPriority());
                Log.i("PRIORITY", "task pri: " + currentTask.getTaskPriority());
                finish();
            }
        });
    }

    private Priority getPriority() {
        Spinner prioritySpinner = findViewById(R.id.prioritySpinner);
        Log.i("PRIORITY", "getPriority: " + Priority.valueOf(prioritySpinner.getSelectedItem().toString()));
        return Priority.valueOf(prioritySpinner.getSelectedItem().toString());
    }

    private void setupCancel() {
        TextView cancel = findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupSwitch() {
        Switch reminderSwitch = findViewById(R.id.reminderSwitch);
        this.reminderSwitch = reminderSwitch;
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {


                if (isChecked) {
                    android.support.v4.app.DialogFragment dialogFragment = new SetReminderFragment();
                    Bundle args = new Bundle();
                    args.putBoolean("hasDueDate", currentTask.isReminderSet());
                    if (currentTask.isReminderSet()) {
                        args.putString("dueDate", currentTask.getDueDate().toString());
                    }
                    dialogFragment.setArguments(args);
                    dialogFragment.show(getSupportFragmentManager(), "setReminderFragment");
                    showReminderSetDate();

//                    currentTask.setReminderSet(true);
                } else {
                    currentTask.setReminderSet(false);
                    hideReminderSetDate();
                }
            }

        };
        reminderSwitch.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private void setupSpinner() {
        Spinner prioritySpinner = findViewById(R.id.prioritySpinner);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.priorityArray,
                android.R.layout.simple_dropdown_item_1line);
        prioritySpinner.setAdapter(arrayAdapter);
    }

    public static Intent getIntentForLaunch(Context context, Task task){
        currentTask = task;
        Intent intent = new Intent(context, AdditionalDetailsActivity.class);
        return intent;
    }


    public static void setDateAndTime(Activity mActivity, int year, int month, int day, int hour, int minute) {
        currentTask.setReminderSet(true);
        Date date = new GregorianCalendar(year,month,day,hour,minute).getTime();
        currentTask.setDueDate(date);
        refreshScreen(mActivity);
    }

    private static void refreshScreen(Activity mActivity) {

        TextView dateSet = mActivity.findViewById(R.id.dateSet);

        if (currentTask.getDueDate() != null) {
            dateSet.setVisibility(View.VISIBLE);
            dateSet.setText(currentTask.getDueDate().toString());
        } else {
            dateSet.setText("Not Set Yet");
        }
    }

//    private static Date makeDate(int year, int month, int day, int hour, int minute) {
//        Date date = new GregorianCalendar(year,month,day,hour,minute).getTime();
//        Log.i("TEST", "makeDate: " + date);
//        return date;
//    }

    private void logDate() {
        Log.i("TEST", "logDate: " + currentTask.getDueDate());
    }

    private void showReminderSetDate() {
        TextView labelDate = findViewById(R.id.dateLabel);
        TextView dateSet = findViewById(R.id.dateSet);

        labelDate.setVisibility(View.VISIBLE);
        dateSet.setVisibility(View.VISIBLE);
        if (currentTask.getDueDate() != null) {
            dateSet.setText(currentTask.getDueDate().toString());
        } else {
            dateSet.setText("Not Set Yet");
        }
    }

    private void hideReminderSetDate() {
        TextView labelDate = findViewById(R.id.dateLabel);
        TextView dateSet = findViewById(R.id.dateSet);

        labelDate.setVisibility(View.INVISIBLE);
        dateSet.setVisibility(View.INVISIBLE);

    }
}
