package com.codeminders.labs.timeextractor.rules.timeinterval;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;

import com.codeminders.labs.timeextractor.constants.TemporalConstants;
import com.codeminders.labs.timeextractor.entities.Rule;
import com.codeminders.labs.timeextractor.temporal.entities.Temporal;
import com.codeminders.labs.timeextractor.temporal.entities.Time;
import com.codeminders.labs.timeextractor.temporal.entities.TimeDate;
import com.codeminders.labs.timeextractor.temporal.entities.TimeTag;
import com.codeminders.labs.timeextractor.temporal.entities.Type;
import com.codeminders.labs.timeextractor.utils.TemporalBasicCaseParser;
import com.codeminders.labs.timeextractor.utils.TemporalObjectGenerator;
import com.codeminders.labs.timeextractor.utils.Utils;

// until 5.33 am, after 5pm 

public class TimeIntervalRule5 extends Rule {
    private TemporalBasicCaseParser parser;

    protected Locale locale = Locale.US;
    protected double confidence = 0.8;
    private int priority = 4;
    private String rule = "((after|before|until|till|til|by)[\\s]*([01]?[0-9]|2[0-3])(([.|:])([0-5][0-9]))?[\\s]*(([p,P][.]?[m,M][.]?)|([a,A][.]?[m,M][.]?))" + "([\\s]*" + TemporalConstants.TIME_ZONE
            + ")?)";
    {
        parser = new TemporalBasicCaseParser();
    }

    public TimeIntervalRule5() {

    }

    @Override
    public Type getType() {
        return Type.TIME_INTERVAL;

    }

    @Override
    public List<Temporal> getTemporal(String text) {
        Matcher m = Utils.getMatch(rule, text);
        TimeDate start = new TimeDate();
        TimeDate end = new TimeDate();
        Time time = new Time();
        Temporal temporal = null;
        int timezone = 0;

        if (m.group(11) != null) {
            timezone = parser.getTimeZone(m.group(11));
            time.setTimezone(timezone);
        }
        if (m.group(3) != null) {
            int hours = Integer.parseInt(m.group(3));
            hours = Utils.convertTime(hours, m.group(7));
            time.setHours(hours);
        }
        if (m.group(6) != null) {
            time.setMinutes(Integer.parseInt(m.group(6)));
        }
        TimeTag tag = TemporalBasicCaseParser.getTimeTag(m.group(2));
        if (tag == TimeTag.AFTER) {
            start.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, start, null);
        } else {
            end.setTime(time);
            temporal = TemporalObjectGenerator.generateTemporalTime(Type.TIME_INTERVAL, null, end);
        }
        List<Temporal> temporalList = new ArrayList<Temporal>();
        temporalList.add(temporal);
        return temporalList;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Rule o) {
        return super.compare(this, o);
    }

}