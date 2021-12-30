package de.wuespace.telestion.project.daedalus2.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtils {
	/**
	 * Helper function to convert a {@link Date} to a ISO-8601 Date/Time string.
	 *
	 * @param date {@link Date} that should be converted.
	 * @return ISO-8601 Date/Time string representation
	 */
	public static String getISO8601StringForDate(Date date) {
		//noinspection SpellCheckingInspection
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX", Locale.GERMANY);
		dateFormat.setTimeZone(TimeZone.getTimeZone("CET"));
		return dateFormat.format(date);
	}

	private DateUtils() {
	}
}
