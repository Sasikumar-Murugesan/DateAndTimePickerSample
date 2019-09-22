package com.sasi.dateandtimepicker;

import android.os.Build;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.sasi.dateandtimepicker.databinding.ActivityDateAndTimePickerBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTimePickerActivity extends AppCompatActivity {
    public ActivityDateAndTimePickerBinding binding;
    public DateAndTimePickerViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_date_and_time_picker);
        mViewModel = ViewModelProviders.of(this).get(DateAndTimePickerViewModel.class);
        binding.setViewModel(mViewModel);
        binding.datePicker.setMinDate(DateTimeUtils.getMinimumDate());
        binding.datePicker.setMaxDate(DateTimeUtils.getMaximumDate());
        mViewModel.selectedDateStr.observe(this, new Observer<String>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChanged(String selectedDateStr) {
                mViewModel.formattedDateStr = selectedDateStr;
                Toast.makeText(DateAndTimePickerActivity.this, selectedDateStr, Toast.LENGTH_SHORT).show();
                if (!DateTimeUtils.getCurrentDateStr().equals(mViewModel.formattedDateStr)) {
                    binding.timePicker.setHour(mViewModel.hour);
                    binding.timePicker.setMinute(mViewModel.minutes);
                } else {
                    Calendar mCalendar = Calendar.getInstance();
                    int hours = mCalendar.get(Calendar.HOUR);
                    int minutes = mCalendar.get(Calendar.MINUTE);
                    binding.timePicker.setHour(hours);
                    binding.timePicker.setMinute(minutes);
                }


                binding.timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                        mViewModel.hour = hour;
                        mViewModel.minutes = minute;
                        mViewModel.selectedTimeStr.setValue(DateTimeUtils.get12HoursTimeFormat(hour, minute));

                    }
                });

            }
        });
        mViewModel.selectedTimeStr.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String selectedTimeStr) {
                mViewModel.formattedTimeStr = selectedTimeStr;
                if (checkIsValidTime(selectedTimeStr)) {
                    Toast.makeText(DateAndTimePickerActivity.this, selectedTimeStr, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DateAndTimePickerActivity.this, "Please within 4 hours", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean checkIsValidTime(String selectedTimeStr) {
        if (!DateTimeUtils.getCurrentDateStr().equals(mViewModel.formattedDateStr)) {
            return true;
        } else {
            int timeDiffer = DateTimeUtils.getTimeDiffence(selectedTimeStr);
            if (timeDiffer <= 4) {
                return true;
            }
        }
        return false;
    }


}
