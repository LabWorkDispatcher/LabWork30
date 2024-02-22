package com.example.laba30.utils;

import android.content.Intent;
import android.util.Log;
import android.widget.DatePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laba30.data.Date;

import java.util.Calendar;

public class Utils {
    public static void moveToActivity(AppCompatActivity currActivity, Intent intent) {
        currActivity.startActivity(intent);
        currActivity.finish();
    }

    public static Date getDateThroughOffset(int pos) {
        Calendar mCalendar = Calendar.getInstance();
        if (pos != 0) {
            mCalendar.add(Calendar.DATE, pos);
        }
        return new Date(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH)+1, mCalendar.get(Calendar.DAY_OF_MONTH));
    }

    public static void setMinDate(DatePicker datePicker, int minPos) {
        Calendar minCalendar = Calendar.getInstance();
        Date minDate = getDateThroughOffset(minPos);
        minCalendar.set(minDate.year, minDate.month-1, minDate.day);
        datePicker.setMinDate(minCalendar.getTimeInMillis());
    }

    public static void setMaxDate(DatePicker datePicker, int maxPos) {
        Calendar maxCalendar = Calendar.getInstance();
        Date maxDate = getDateThroughOffset(maxPos);
        maxCalendar.set(maxDate.year, maxDate.month-1, maxDate.day);
        datePicker.setMaxDate(maxCalendar.getTimeInMillis());
    }

    public static int getCalendarPos(Date date, int minPos, int maxPos) {
        for (int i = minPos; i <= maxPos; i++) {
            Date comparisonDate = getDateThroughOffset(i);
            if (comparisonDate.year == date.year && comparisonDate.month == date.month && comparisonDate.day == date.day) {
                return i;
            }
        }
        throw (new RuntimeException("Utils: Given date wasn't found in the supplied range."));
    }
}
