package todo.com.chandel.farhan.todolist;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;

public class AdditionalDetailsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private static Task currentTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_details);

        setupSpinner();
        setupSwitch();
    }

    private void setupSwitch() {
        Switch reminderSwitch = findViewById(R.id.reminderSwitch);
        CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {


                if (isChecked) {
                    currentTask.setReminderSet(true);
                    android.support.v4.app.DialogFragment dialogFragment = new SetReminderFragment();
                    dialogFragment.show(getSupportFragmentManager(), "setReminderFragment");
                } else {
                    currentTask.setReminderSet(false);
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

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        //do code here
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        
    }
}
