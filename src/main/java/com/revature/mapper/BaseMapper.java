package com.revature.mapper;

import org.apache.commons.lang3.ObjectUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public abstract class BaseMapper {

    private static final DateTimeFormatter GENERAL_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.US);
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a", Locale.US);

    public String mapDateToDto(Instant instant) {
        if (ObjectUtils.isEmpty(instant)) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(GENERAL_DATE_FORMAT);
    }

    public String mapDateTimeToDto(Instant instant) {
        if (ObjectUtils.isEmpty(instant)) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format(DATE_TIME_FORMAT);
    }
}
