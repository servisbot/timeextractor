package com.codeminders.labs.timeextractor.rules.date;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.codeminders.labs.timeextractor.constants.MonthOfYear;
import com.codeminders.labs.timeextractor.constants.Type;
import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.temporal.entites.Date;
import com.codeminders.labs.timeextractor.temporal.entites.Temporal;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;

public class DayOrderAndMonthRule2 extends BaseRule {

    protected Locale locale = Locale.US;
    protected double confidence = 0.83;
    private String dayOfMonth;
    private String month;
    private String year;

    public DayOrderAndMonthRule2(String dayOfMonth, String month, String year) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }

    @Override
    public Type getType() {
        return Type.DATE;
    }

    @Override
    public List<Temporal> getTemporal() {
        int dayOfMonth = 0;
        MonthOfYear monthOfYear = null;
        int year = 0;

        monthOfYear = TemporalBasicCaseParser.getMonthOfYear((this.month));
        dayOfMonth = TemporalBasicCaseParser.getDayOfWeekFromOrder((this.dayOfMonth));
        if (this.year != null) {
            year = Integer.parseInt(this.year.trim());
        }

        Date date = new Date();
        date.setMonth(monthOfYear.getValue());
        date.setDay(dayOfMonth);
        date.setYear(year);
        Temporal temporal = TemporalObjectGenerator.generateTemporalDate(type, date);

        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);

        return temporalList;
    }
}