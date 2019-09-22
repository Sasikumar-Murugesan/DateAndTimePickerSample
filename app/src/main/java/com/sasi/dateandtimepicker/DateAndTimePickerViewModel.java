package com.sasi.dateandtimepicker;

import android.app.Application;
import android.os.Build;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTimePickerViewModel extends AndroidViewModel {
    public Application mApplication;
    public MutableLiveData<Date> selectedDate = new MutableLiveData<>();
    public MutableLiveData<String> selectedDateStr = new MutableLiveData<>();
    public MutableLiveData<String> selectedTimeStr = new MutableLiveData<>();

    public final ObservableInt year = new ObservableInt();
    public final ObservableInt month = new ObservableInt();
    public final ObservableInt day = new ObservableInt();

    public static int hour;
    public static int minutes;

    public Calendar selectedCalender = Calendar.getInstance();
    public String formattedDateStr="";
    public String formattedTimeStr="";
    public DateAndTimePickerViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;

        Calendar calendar = Calendar.getInstance();
        setCalenderData(calendar);
    }


    public void dateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year.set(year);
        this.month.set(monthOfYear);
        this.day.set(dayOfMonth);
        selectedCalender.set(Calendar.MONTH, monthOfYear);
        selectedCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        selectedCalender.set(Calendar.YEAR, year);
        int month=monthOfYear+1;
        selectedDateStr.setValue(DateTimeUtils.getDateString(year, month, dayOfMonth));
    }

    public void updateTimeOnclick(View v) {
        editMode();
    }
    public void editMode() {
        Calendar mCalendar = Calendar.getInstance();
        try {
            Date previousDate = new SimpleDateFormat(DateTimeUtils.DATE_AND_TIME_FORMAT).parse("09/21/2019 08:54 AM");
            mCalendar.setTime(previousDate);
            setCalenderData(mCalendar);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public void setCalenderData(Calendar calendar)
    {
        year.set(calendar.get(Calendar.YEAR));
        month.set(calendar.get(Calendar.MONTH));
        day.set(calendar.get(Calendar.DAY_OF_MONTH));
        selectedCalender = calendar;
        selectedDate.setValue(calendar.getTime());
        formattedDateStr=DateTimeUtils.getCurrentDateStr();

        hour=calendar.get(Calendar.HOUR);
        minutes=calendar.get(Calendar.MINUTE);
        selectedDateStr.setValue(DateTimeUtils.getDateString(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DAY_OF_MONTH)));

    }


}
