package com.smarttmt.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Sysdate {

    public static String getSysdate(String formato) {
        DateFormat dateFormat = new SimpleDateFormat(formato);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static Date getFechaObjectToDate(String formato, Object fecha) {
        LocalDate localDate = LocalDate.parse((CharSequence) fecha, DateTimeFormatter.ofPattern(formato));
        return java.sql.Date.valueOf(localDate);
    }

}
