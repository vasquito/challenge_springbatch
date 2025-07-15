package com.accenture.challenge.utils.helpers;

public class RandomHelper {

    public static Integer generateIntegerRandom(int min, int max) {
        int randomNumber = (int)(Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }

    public static Long generateLongRandom(long min, long max) {
        long randomNumber = (long)(Math.random() * ((max - min) + 1)) + min;
        return randomNumber;
    }
}
