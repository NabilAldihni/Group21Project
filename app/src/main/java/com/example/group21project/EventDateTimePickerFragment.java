package com.example.group21project;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EventDateTimePickerFragment extends Fragment {
    private TextView timePreview;
    // WARNING: DatePickerDialog stores months in the range 0-11, while LocalDateTIme uses 1-12
    private LocalDateTime time;

    public EventDateTimePickerFragment() {
        LocalTime closestHour = LocalTime.now().plusHours(1).withMinute(0);
        time = LocalDateTime.of(LocalDate.now(), closestHour);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_date_time_picker, container, false);

        Button setTimeButton = view.findViewById(R.id.eventSetTimeButton);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String setTimeButtonText = arguments.getString("setTimeButtonText");
            setTimeButton.setText(setTimeButtonText);
        }

        setTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePickerDialog(view.getContext());
            }
        });

        timePreview = view.findViewById(R.id.eventTimePreview);
        timePreview.setText(EventListItem.getFormattedTimeString(time));

        return view;
    }

    private void openDatePickerDialog(Context context) {
        LocalDate today = LocalDate.now();

        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        LocalDate date = LocalDate.of(year, month + 1, dayOfMonth);
                        time = LocalDateTime.of(date, LocalTime.MIDNIGHT);
                        openTimePickerDialog(view.getContext());
                    }
                },
                today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth());

        datePickerDialog.updateDate(today.getYear(), today.getMonthValue() - 1, today.getDayOfMonth());
        datePickerDialog.show();
    }

    private void openTimePickerDialog(Context context) {
        LocalTime nextHour = LocalTime.now().plusHours(1);
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time = LocalDateTime.of(time.toLocalDate(), LocalTime.MIDNIGHT);
                        time = time.plusHours(hourOfDay);
                        time = time.plusMinutes(minute);
                        timePreview.setText(EventListItem.getFormattedTimeString(time));
                    }
                },
                nextHour.getHour(), 0, DateFormat.is24HourFormat(context));
        timePickerDialog.show();
    }

    public LocalDateTime getPickerTime() {
        return time;
    }
}