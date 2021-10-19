package com.ecomshop.deskplus.services.utils;

import java.util.Date;

/**
 * Author: Sheik Syed Ali
 * Date: 10 Oct 2021
 */
public class DateTimeUtil {
    public static int daysBetweenDates(Date fromDate, Date toDate){
        int diffDays = -1;
        long fromDateMilli = fromDate.getTime();
        long toDateMilli = toDate.getTime();

        if(fromDateMilli > toDateMilli){
            return diffDays;
        }
        long diff = toDateMilli - fromDateMilli;
        float days = (float) diff / (24 * 60 * 60 * 1000);
        diffDays = (int) days;
        return diffDays;
    }
}
