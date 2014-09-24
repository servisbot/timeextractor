package com.codeminders.labs.timeextractor.rules.date;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.codeminders.labs.timeextractor.rules.general.GeneralTest;
import com.codeminders.labs.timeextractor.temporal.entites.DayOfWeek;
import com.codeminders.labs.timeextractor.temporal.entites.TemporalExtraction;

public class DayOfWeekRule1Test extends GeneralTest {

	@Test
	public void dayOfWeekRule1Test1() {

		String toPredict = "Sunday";
		List<TemporalExtraction> predicted = service
				.extractDatesAndTimeFromText(toPredict, null);
		assertEquals("Sunday", predicted.get(0).getTemporalExpression());
		assertEquals(DayOfWeek.SU, predicted.get(0).getTemporal().get(0)
				.getStartDate().getDate().getDayOfWeek());

	}

	@Test
	public void dayOfWeekRule1Test2() {

		String toPredict = "Wed";
		List<TemporalExtraction> predicted = service
				.extractDatesAndTimeFromText(toPredict, null);
		assertEquals("Wed", predicted.get(0).getTemporalExpression());
		assertEquals(DayOfWeek.WE, predicted.get(0).getTemporal().get(0)
				.getStartDate().getDate().getDayOfWeek());

	}

}
