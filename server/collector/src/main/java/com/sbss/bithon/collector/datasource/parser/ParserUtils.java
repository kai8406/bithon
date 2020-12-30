package com.sbss.bithon.collector.datasource.parser;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.joda.time.DateTimeZone;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Author: frank.chen021@outlook.com
 * Date: 2020/11/30 5:52 下午
 */
public class ParserUtils {
    private static final String DEFAULT_COLUMN_NAME_PREFIX = "column_";

    private static final Map<String, DateTimeZone> TIMEZONE_LOOKUP = new HashMap<>();

    static {
        for (String tz : TimeZone.getAvailableIDs()) {
            try {
                TIMEZONE_LOOKUP.put(tz, DateTimeZone.forTimeZone(TimeZone.getTimeZone(tz)));
            } catch (IllegalArgumentException e) {
                // Ignore certain date time zone ids like SystemV/AST4. More here https://confluence.atlassian.com/confkb/the-datetime-zone-id-is-not-recognised-167183146.html
            }
        }
    }

    public static Function<String, Object> getMultiValueFunction(
            final String listDelimiter,
            final Splitter listSplitter
    ) {
        return (input) -> {
            if (input != null && input.contains(listDelimiter)) {
                return StreamSupport.stream(listSplitter.split(input).spliterator(), false)
                        .map(Strings::emptyToNull)
                        .collect(Collectors.toList());
            } else {
                return Strings.emptyToNull(input);
            }
        };
    }

    public static ArrayList<String> generateFieldNames(int length) {
        final ArrayList<String> names = new ArrayList<>(length);
        for (int i = 0; i < length; ++i) {
            names.add(getDefaultColumnName(i));
        }
        return names;
    }

    @VisibleForTesting
    static Set<String> findDuplicates(Iterable<String> fieldNames) {
        Set<String> duplicates = new HashSet<>();
        Set<String> uniqueNames = new HashSet<>();

        for (String fieldName : fieldNames) {
            if (uniqueNames.contains(fieldName)) {
                duplicates.add(fieldName);
            }
            uniqueNames.add(fieldName);
        }

        return duplicates;
    }

    public static void validateFields(Iterable<String> fieldNames) {
        Set<String> duplicates = findDuplicates(fieldNames);
        if (!duplicates.isEmpty()) {
//            throw new ParseException("Duplicate column entries found : %s", duplicates.toString());
        }
    }

    public static String stripQuotes(String input) {
        input = input.trim();
        if (input.charAt(0) == '\"' && input.charAt(input.length() - 1) == '\"') {
            input = input.substring(1, input.length() - 1).trim();
        }
        return input;
    }

    @Nullable
    public static DateTimeZone getDateTimeZone(String timeZone) {
        return TIMEZONE_LOOKUP.get(timeZone);
    }

    /**
     * Return a function to generate default column names.
     * Note that the postfix for default column names starts from 1.
     *
     * @return column name generating function
     */
    public static String getDefaultColumnName(int ordinal) {
        return DEFAULT_COLUMN_NAME_PREFIX + (ordinal + 1);
    }
}