package todo.com.chandel.farhan.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Date;

import static android.content.ContentValues.TAG;



public class SetReminderFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private static int year,month,day;
    private static int hour, minute;
    private Spinner spinnerDate;

    @NonNull
    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder b=  new  AlertDialog.Builder(getActivity())
                .setTitle("Set Reminder")
                .setPositiveButton("Save",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // do something...
                                AdditionalDetailsActivity.setDateAndTime(getActivity(), year, month, day, hour, minute);
                            }
                        }
                )
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.dismiss();
                            }
                        }
                );

        LayoutInflater i = getActivity().getLayoutInflater();

        View v = i.inflate(R.layout.set_reminder_fragment,null);
        setSpinnerDate(v);
        setSpinnerTime(v);
        b.setView(v);

        return b.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.set_reminder_fragment, null);
        setSpinnerDate(view);
        setSpinnerTime(view);


        return view;
    }

    private void setSpinnerDate(View view) {
        final Spinner spinnerDate = view.findViewById(R.id.dateSpinner);
        final ArrayAdapter dateAdapter = ArrayAdapter.createFromResource(getContext(),R.array.setDate,android.R.layout.simple_spinner_dropdown_item);
        spinnerDate.setAdapter(dateAdapter);

        spinnerDate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerDate.getSelectedItem().toString().equals("Pick Date")) {
                    DialogFragment datePicker = new DatePickerFragment();
                    datePicker.setTargetFragment(SetReminderFragment.this, 0);
                    datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        this.spinnerDate = spinnerDate;

    }

    private void setSpinnerTime(View view) {
        final Spinner spinnerTime = view.findViewById(R.id.timeSpinner);
        ArrayAdapter timeAdapter = ArrayAdapter.createFromResource(getContext(),R.array.setTime,android.R.layout.simple_spinner_dropdown_item);
        spinnerTime.setAdapter(timeAdapter);
        spinnerTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinnerTime.getSelectedItem().toString().equals("Pick Time")) {
                    DialogFragment timePicker = new TimePickerFragment();
                    timePicker.show(getActivity().getSupportFragmentManager(), "timePicker");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Log.i("TEST", "onDateSet: dateset running" + year + " " + month + " " + day);
        this.year = year;
        this.month = month;
        this.day = day;


//        ((TextView) spinnerDate.getSelectedView()).setText(month + " " + day + ", " + year);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

}
