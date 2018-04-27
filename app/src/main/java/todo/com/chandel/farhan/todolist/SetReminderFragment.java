package todo.com.chandel.farhan.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import static android.content.ContentValues.TAG;

/**
 * Created by Farhan on 2018-04-25.
 */

public class SetReminderFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
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
                    datePicker.show(getActivity().getSupportFragmentManager(), "datePicker");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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


}
