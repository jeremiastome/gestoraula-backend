package com.tip.alumnos.model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static int getClassDays(Date fechaInicio, Date fechaFin) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(fechaInicio);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(fechaFin);

        int classDays = 0;

        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }
        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
                && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++classDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return classDays;
    }
}
