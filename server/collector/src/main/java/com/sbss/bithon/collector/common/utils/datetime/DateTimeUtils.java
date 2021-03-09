package com.sbss.bithon.collector.common.utils.datetime;

import org.joda.time.DateTime;

import java.util.concurrent.TimeUnit;

/**
 * @author frankchen
 * @Date 2020-03-24 22:31:38
 */
public class DateTimeUtils {

    public final static long MIN_LENGTH_IN_MILLI = 60 * 1000L;
    public final static long HOUR_LENGTH_IN_MILLI = 3600 * 1000L;
    public final static long DAY_LENGTH_IN_MILLI = 24 * 3600 * 1000L;

    public static long dropMilliseconds(long timestamp) {
        return timestamp / 1000 * 1000;
    }

    public static long align2Minute() {
        return align2Minute(1);
    }

    public static long align2Minute(int minutebase) {
        return System.currentTimeMillis() / (minutebase * MIN_LENGTH_IN_MILLI) * (minutebase * MIN_LENGTH_IN_MILLI);
    }

    public static long getDayStart() {
        return getDayStart(System.currentTimeMillis());
    }

    public static long getDayStart(long milliseconds) {
        return milliseconds / DAY_LENGTH_IN_MILLI * DAY_LENGTH_IN_MILLI;
    }

    public static long minute2MilliSec(int minute) {
        return minute * MIN_LENGTH_IN_MILLI;
    }

    public static long offset(long value, TimeUnit timeUnit) {
        switch (timeUnit) {
            case DAYS:
                return value * 3600L * 24 * 1000;

            case HOURS:
                return value * 3600L * 1000;

            case MINUTES:
                return value * 60 * 1000;

            case SECONDS:
                return value * 1000;

            case MILLISECONDS:
                return 0;

            default:
                throw new RuntimeException("not supported");
        }
    }

    public static long fromISO8601(String dateTime) {
        return DateTime.parse(dateTime).getMillis();
    }
}
