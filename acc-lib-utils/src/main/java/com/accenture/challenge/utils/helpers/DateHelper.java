package com.accenture.challenge.utils.helpers;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateHelper {

    /**
     * Format Date to string (yyyyMMddHHmmss)
     * @param date
     * @return date format yyyyMMddHHmmss or ""
     */
    public static String formatDateToString(Date date){
        if(ObjectUtils.isNotEmpty(date)){
            return new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        }
        return "";
    }
}
