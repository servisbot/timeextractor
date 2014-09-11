package com.codeminders.labs.timeextractor.utils;

import com.codeminders.labs.timeextractor.rules.BaseRule;
import com.codeminders.labs.timeextractor.rules.BetweenTimeEvent;
import com.codeminders.labs.timeextractor.rules.composite.CompositeDayOfWeekTimeOfDay;
import com.codeminders.labs.timeextractor.rules.composite.CompositeTime4RuleMonthAndDayRule0;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekOrderRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule2;
import com.codeminders.labs.timeextractor.rules.date.DayOfWeekRule3;
import com.codeminders.labs.timeextractor.rules.date.DayOrderAndMonthRule1;
import com.codeminders.labs.timeextractor.rules.date.DayOrderAndMonthRule2;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule0;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule2;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule3;
import com.codeminders.labs.timeextractor.rules.date.MonthAndDayRule4;
import com.codeminders.labs.timeextractor.rules.date.MonthAndYearRule1;
import com.codeminders.labs.timeextractor.rules.date.MonthOfYear1;
import com.codeminders.labs.timeextractor.rules.date.YearRule;
import com.codeminders.labs.timeextractor.rules.dateinterval.DayOfWeekIntervalRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.MonthDaysIntervalRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.MonthToMonthRule1;
import com.codeminders.labs.timeextractor.rules.dateinterval.SeasonRules;
import com.codeminders.labs.timeextractor.rules.dateinterval.SeasonRules2;
import com.codeminders.labs.timeextractor.rules.frequency.FrequencyTime;
import com.codeminders.labs.timeextractor.rules.time.Time1Rule;
import com.codeminders.labs.timeextractor.rules.time.Time2Rule;
import com.codeminders.labs.timeextractor.rules.time.Time3Rule;
import com.codeminders.labs.timeextractor.rules.time.Time4Rule;
import com.codeminders.labs.timeextractor.rules.time.TimeOfDayRule;

// factory that returns object of concrete  rule class

public class RulesFactory {

	public BaseRule getBaseRule(Object obj, String name) {

		switch (name) {
		// 2009
		case ("YearRule"):
			return (YearRule) obj;
			// October
		case ("MonthOfYear1"):
			return (MonthOfYear1) obj;
			// October 2012
		case ("MonthAndYearRule1"):
			return (MonthAndYearRule1) obj;
			// July 14
		case ("MonthAndDayRule0"):
			return (MonthAndDayRule0) obj;
			// July 14th 2014
		case ("MonthAndDayRule1"):
			return (MonthAndDayRule1) obj;
			// 14 July
		case ("MonthAndDayRule2"):
			return (MonthAndDayRule2) obj;
			// Sunday
		case ("DayOfWeekRule1"):
			return (DayOfWeekRule1) obj;
			// the 20th of january
		case ("MonthAndDayRule3"):
			return (MonthAndDayRule3) obj;
			// Sunday 16
		case ("DayOfWeekRule2"):
			return (DayOfWeekRule2) obj;
			// Sunday 16 2014
		case ("DayOfWeekRule3"):
			return (DayOfWeekRule3) obj;
			// Jan. 20 2014
		case ("MonthAndDayRule4"):
			return (MonthAndDayRule4) obj;
			// the first Tuesday (of the month)
		case ("DayOfWeekOrderRule1"):
			return (DayOfWeekOrderRule1) obj;
			// the 1st Tuesday (of the month)
		case ("DayOfWeekOrderRule2"):
			return (DayOfWeekOrderRule2) obj;
			// the first of December 2011
		case ("DayOrderAndMonthRule1"):
			return (DayOrderAndMonthRule1) obj;
			// the 1st of December 2011
		case ("DayOrderAndMonthRule2"):
			return (DayOrderAndMonthRule2) obj;
			// 7pm, 11 am
		case ("Time1Rule"):
			return (Time1Rule) obj;
			// 12:22 CET
		case ("Time2Rule"):
			return (Time2Rule) obj;
			// at 5.33 pm CET
		case ("Time3Rule"):
			return (Time3Rule) obj;
			// at 5 CET, at 11
		case ("Time4Rule"):
			return (Time4Rule) obj;
			// autumn
		case ("SeasonRules"):
			return (SeasonRules) obj;
			// autumn 2014
		case ("SeasonRules2"):
			return (SeasonRules2) obj;
			// July 28th-31st
		case ("MonthDaysIntervalRule1"):
			return (MonthDaysIntervalRule1) obj;
			// April through October 2014
		case ("MonthToMonthRule1"):
			return (MonthToMonthRule1) obj;
			// Wed.-Fri.
		case ("DayOfWeekIntervalRule1"):
			return (DayOfWeekIntervalRule1) obj;

			// morning
		case ("TimeOfDayRule"):
			return (TimeOfDayRule) obj;
			// morning
		case ("BetweenTimeEvent"):
			return (BetweenTimeEvent) obj;
			// yearly, weekly, monthly
		case ("FrequencyTime"):
			return (FrequencyTime) obj;

		case ("CompositeTime4RuleMonthAndDayRule0"):
			return (CompositeTime4RuleMonthAndDayRule0) obj;
		case ("CompositeDayOfWeekTimeOfDay"):
			return (CompositeDayOfWeekTimeOfDay) obj;
		}
		return null;
	}

}