package com.pr.kafka.example.utils;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Oleksandr Prognimak
 * @created 12.01.2021 - 14:29
 */
class DateUtilityTest {

    @Test
    void stringToDate() {
        String testStrDate = "20/01/2020";
        Date date = DateUtility.stringToDate(testStrDate);
        assertNotNull(date);
    }

    @Test
    void dateToString() {
    }
}