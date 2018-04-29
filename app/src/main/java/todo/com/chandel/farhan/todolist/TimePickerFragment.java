package todo.com.chandel.farhan.todolist;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Farhan on 2018-04-26.
 */

public class TimePickerFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        boolean is24HourView = false;

        return new TimePickerDialog(getContext(), (TimePickerDialog.OnTimeSetListener) getDialog(), hourOfDay,minute, is24HourView);
    }
}
