package com.txg.www.until;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class GetTime {

    /**
     * 修改时间格式
     * @return
     */
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Date = temp.format(date);
        return Date;
    }

    /**
     * 修改时间格式
     * @param banTime
     * @return
     */
    public static String getTime(int banTime) {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, banTime);
        Date time = calendar.getTime();
        SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Date = temp.format(time);
        return Date;
    }

}
