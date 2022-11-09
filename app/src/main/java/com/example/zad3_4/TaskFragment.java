package com.example.zad3_4;

import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.annotation.Nullable;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;

public class TaskFragment extends Fragment {

    private static final String ARG_TASK_ID = "ARG_TASK_ID";
    private Task task;
    private TextView nameField;
    private CheckBox taskDoneCheckbox;
    private EditText dateField;
    private Spinner categorySpinner;
    private final Calendar calendar = Calendar.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID taskId = (UUID) getArguments().getSerializable(ARG_TASK_ID);
        task = TaskStorage.getInstance().getTask(taskId);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);
        nameField = view.findViewById(R.id.task_name);
        nameField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                task.setName(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        nameField.setText(task.getName());

        taskDoneCheckbox = view.findViewById(R.id.task_done_checkbox);
        taskDoneCheckbox.setChecked(task.isDone());
        taskDoneCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setDone(isChecked);
        });


        dateField = view.findViewById(R.id.task_date);
        DatePickerDialog.OnDateSetListener date = (view12, year, month, day) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, day);
            setupDateFieldValue(calendar.getTime());
            task.setDate(calendar.getTime());
        };

        dateField.setOnClickListener( view1 ->
                new DatePickerDialog(getContext(), date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                        .show());
        setupDateFieldValue(task.getDate());




        categorySpinner = view.findViewById(R.id.task_category);
        categorySpinner.setAdapter(new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, Category.values()));
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                task.setCategory(Category.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        return view;
    }

    public static TaskFragment newInstance(UUID taskID){
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TASK_ID, taskID);
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(bundle);
        return taskFragment;
    }

    private void setupDateFieldValue(Date date){
        Locale locale = new Locale("pl","PL");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", locale);
        dateField.setText(dateFormat.format(date));
    }

}