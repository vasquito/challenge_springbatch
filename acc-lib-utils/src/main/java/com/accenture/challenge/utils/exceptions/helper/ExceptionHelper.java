package com.accenture.challenge.utils.exceptions.helper;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class ExceptionHelper {

    /**
     *
     * @return
     */
    public static long generateIdReference() {
        ZonedDateTime nowUTC = ZonedDateTime.now(ZoneOffset.UTC);
        return nowUTC.toEpochSecond();
    }

    /**
     *
     * @param idReference
     * @param message
     * @return
     */
    public static String generateMessage(long idReference, String message) {
        return "idReference="+ idReference + ", message: " + message;
    }
}
