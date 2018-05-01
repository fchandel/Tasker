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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;


public class SetReminderFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private static int year,month,day;
    private static int hour, minute;
    private View view;
    private final int MONTH = 1;
    private final int DAY = 2;
    private final int YEAR = 5;
    private final int TIME = 3;
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

        Bundle args = getArguments();
        String date = getDate(args);
        String time = getTime(args);
        setDateTextView(v, date);
        setTimeTextView(v, time);
        b.setView(v);
        view = v;

        return b.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("TEST", "onCreateView: ");
        View view = inflater.inflate(R.layout.set_reminder_fragment, null);
        Bundle args = getArguments();
        String date = getDate(args);
        String time = getTime(args);
        setDateTextView(view, date);
        setTimeTextView(view,time);

        return view;
    }

    private String getDate(Bundle args) {
        boolean has = (boolean) args.get("hasDueDate");
        if (has) {
            Log.i("TEST", "getDate: " + args.get("dueDate"));
            String dateWithTime = (String) args.get("dueDate");
            String[] dateArrayWithTime = dateWithTime.split(" ");
            String date = dateArrayWithTime[MONTH] + " " + dateArrayWithTime[DAY] + " " + dateArrayWithTime[YEAR];
            return date;
        } else {
            return "";
        }
    }

    private String getTime(Bundle args) {
        boolean has = (boolean) args.get("hasDueDate");
        if (has) {
            Log.i("TEST", "getDate: " + args.get("dueDate"));
            String dateWithTime = (String) args.get("dueDate");
            String[] dateArrayWithTime = dateWithTime.split(" ");
            String time = dateArrayWithTime[TIME];
            return time;
        } else {
            return "";
        }
    }


    private void setDateTextView(View view, String date) {
        final TextView pickDate = view.findViewById(R.id.pickDateTxtView);
        if (date != "") {
            Log.i("TEST", "setDateTextView:" + date);
            pickDate.setText(date);
        }

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.setTargetFragment(SetReminderFragment.this, 0);
                datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
            }
        });

    }

    private void setTimeTextView(View view, String time) {
        final TextView pickTime = view.findViewById(R.id.pickTimeTxtView);

        if (time != "") {
            Log.i("TEST", "setDateTextView:" + time);
            pickTime.setText(time);
        }

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.setTargetFragment(SetReminderFragment.this, 0);
                timePicker.show(getActivity().getSupportFragmentManager(), "timePicker");
            }
        });


    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Log.i("TEST", "onDateSet: dateset running" + year + " " + month + " " + day);
        this.year = year;
        this.month = month;
        this.day = day;

        TextView pickDate = view.findViewById(R.id.pickDateTxtView);
        pickDate.setText(month + "/" + day + "/" + year);

    }
    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        this.hour = hour;
        this.minute = minute;

        TextView pickDate = view.findViewById(R.id.pickTimeTxtView);
        pickDate.setText(hour + ":" + minute);
    }

}
