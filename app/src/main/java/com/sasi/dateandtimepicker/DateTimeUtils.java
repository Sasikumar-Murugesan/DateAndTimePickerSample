package com.sasi.dateandtimepicker;

import android.util.Log;

import androidx.databinding.ObservableInt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {
    public static final String dateFormat = "MM/dd/yyyy";
    public static final int MINIMUM_DAYS = -2;
    public static final String TIME_FORMAT = "hh:mm aa";
    public static final String DATE_AND_TIME_FORMAT = dateFormat+" "+TIME_FORMAT;

    public static String getDateString(int year, int month, int day) {

        return convertDayAndMonthDigitFormat(month) + "/" + convertDayAndMonthDigitFormat(day) + "/" + year;
    }

    public static Date convertStringToDate(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat(dateFormat).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String convertCalenderToDateString(Calendar mCalendar) {
        Date date = mCalendar.getTime();
        return new SimpleDateFormat(dateFormat).format(date);
    }

    public static long getMinimumDate() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DAY_OF_MONTH, -2);
        return mCalendar.getTimeInMillis();
    }

    public static long getMaximumDate() {
        Calendar mCalendar = Calendar.getInstance();
        return mCalendar.getTimeInMillis();
    }

    public static String getCurrentDateStr() {
        String currentDateStr = new SimpleDateFormat(dateFormat).format(new Date());
        return currentDateStr;
    }

    /*
        public static String get12HoursTimeFormat(Calendar selectedCalender,int hour, int minutes) {
            selectedCalender.set(Calendar.SECOND, 0);
            selectedCalender.set(Calendar.MINUTE, minutes);
            selectedCalender.set(Calendar.HOUR, hour);
            SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
            String formattedTime = timeFormat.format(selectedCalender.getTime()).toString();
            return formattedTime;
        }*/
    public static String get12HoursTimeFormat(int hourOfDay, int minutes) {
        String format = "";
        if (hourOfDay == 0) {

            hourOfDay += 12;

            format = "AM";
        } else if (hourOfDay == 12) {

            format = "PM";

        } else if (hourOfDay > 12) {

            hourOfDay -= 12;

            format = "PM";

        } else {

            format = "AM";
        }
        return hourOfDay + ":" + minutes + " " + format;
    }

    public static int getTimeDiffence(String selectedTimeStr) {
        SimpleDateFormat format = new SimpleDateFormat(TIME_FORMAT);
        Date date1 = null;
        Calendar mCalendar = Calendar.getInstance();
        Calendar mDate2Calender = Calendar.getInstance();
        date1 = mCalendar.getTime();
        Date date2 = null;
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
        try {
            Date dt = df.parse(getCurrentDateStr() + " " + selectedTimeStr);
            mDate2Calender.setTime(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        date2 = mDate2Calender.getTime();
        long mills = date2.getTime() - date1.getTime();
        Log.v("Data1", "" + date1.getTime());
        Log.v("Data2", "" + date2.getTime());
        int hours = (int) (mills / (1000 * 60 * 60));
        int mins = (int) (mills / (1000 * 60)) % 60;

        return hours;

    }

    public static String convertDayAndMonthDigitFormat(int month) {
        String time = String.valueOf(month);
        if (time.length() == 1) {
            String result = "0" + time;
            return result;
        }
        return String.valueOf(month);
    }

}
