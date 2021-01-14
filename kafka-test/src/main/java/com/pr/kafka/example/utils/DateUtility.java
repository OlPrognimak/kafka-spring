package com.pr.kafka.example.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Oleksandr Prognimak
 * @created 12.01.2021 - 14:14
 */
public final class DateUtility {

    private DateUtility(){

    }

    /**
     *
     * @param dateString
     * @return
     */
    public static Date stringToDate(String dateString){
        if(null == dateString){
            return null;
        }
        try{
            return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        }catch(ParseException ex){
           throw new RuntimeException("Wrong date format: "+dateString, ex);
        }
    }

    /**
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        if(null == date){
            return null;
        }
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        return dateFormat.format(date);
    }
}
