/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smarttmt.utilities;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Usuario
 */
public class Utilidades {

    public static String getProperties(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("com.smarttmt.wsrestprinx.properties.properties_es");
        return rb.getString(key);
    }

    public static String getSysdate(String formato) {
        DateFormat dateFormat = new SimpleDateFormat(formato);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getStringDate(String formato, String fecha) {
        String fechaRetorno = null;
        if (formato != null && fecha != null) {
            if (!formato.isEmpty() && !fecha.isEmpty()) {
                Date date = null;
                DateFormat formatter = new SimpleDateFormat(formato);
                try {
                    date = formatter.parse(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
                }
                DateFormat dateFormat = new SimpleDateFormat(formato);
                fechaRetorno = dateFormat.format(date);
            }
        }
        return fechaRetorno;
    }

    public static String getSysdate(String formato, Date fecha) {
        DateFormat dateFormat = new SimpleDateFormat(formato, new Locale("es", "ES"));
        System.out.println(dateFormat.format(fecha));
        return dateFormat.format(fecha);

    }

    public static Date getFechaDesdeCadena(String fecha, String formato) {
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat(formato);
            date = formatter.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public static Date getDate(String formato, Date inFecha) {
        java.sql.Date sqlDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
            Date todayWithZeroTime = dateFormat.parse(dateFormat.format(inFecha));
            sqlDate = new java.sql.Date(todayWithZeroTime.getTime());
        } catch (ParseException ex) {

        }
        return sqlDate;
    }

    public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date getDate(Date fecha) {
        java.sql.Date sqlDate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            Date todayWithZeroTime = formatter.parse(formatter.format(fecha));
            sqlDate = new java.sql.Date(todayWithZeroTime.getTime());
            System.out.print("paz: " + sqlDate);

            Date utilDate = fecha;
            sqlDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException ex) {

        }
        return sqlDate;
    }

    public static String getFechaString(String formato, Object fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
        LocalDate localDate1 = LocalDate.parse((CharSequence) fecha, DateTimeFormatter.ofPattern(formato));
        String stringFecha = localDate1.format(formatter);
        return stringFecha;
    }

    public static Date getFechaObjectToDate(String formato, Object fecha) {
        //LocalDate solo se puede imprimir en formato ISO8601 (aaaa-MM-dd). 
        java.sql.Date date;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato, Locale.getDefault());
        LocalDate localDate = LocalDate.parse((CharSequence) fecha, DateTimeFormatter.ofPattern(formato));
        date = java.sql.Date.valueOf(localDate);
        return date;
    }

    public static String getFormatStr(String fechStr, String formaInicial, String formaFinal) {
        String fechaInicial = fechStr;
        SimpleDateFormat formatoInicial = new SimpleDateFormat(formaInicial);
        SimpleDateFormat formatoFinal = new SimpleDateFormat(formaFinal);
        String fechaFinal = null;
        try {
            Date fecha = formatoInicial.parse(fechaInicial);
            fechaFinal = formatoFinal.format(fecha);
            System.out.println(fechaFinal);
        } catch (ParseException e) {
        }
        return fechaFinal;
    }

    //metodo que recibe cualquier formato de fecha y lo parsea a date recibe fecha de tipo string
    public static Date getDateSimpleDateFormat(String formato, String fecha) {
        System.out.println("getDateSimpleDateFormat formato:" + formato + " fecha:" + fecha);
        if (formato != null && !formato.isEmpty() && fecha != null && !fecha.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(formato, new Locale("es", "ES"));
            Date date = null;
            try {
                date = dateFormat.parse(fecha);
            } catch (ParseException e) {
                System.out.println("ParseException:" + e + "fecha:" + fecha);
            }
            return date;
        }
        return null;
    }

    public static boolean isContieneSoloNumero(String strNumero) {
        if (strNumero != null && !strNumero.isEmpty()) {
            return strNumero.matches("[0-9]+");
        }
        return false;
    }

    public static String getEstadoPendientes() {
        return   getListEstadoPendientes().stream().collect(Collectors.joining(","));
    }

    public static List<String> getListEstadoPendientes() {
        return Arrays.asList(
                EnumEstadosPendientes.ACTIVO.getCodigo(),
                EnumEstadosPendientes.ASIGNADO.getCodigo(),
                EnumEstadosPendientes.CONSTRUCION_DESARROLLO.getCodigo(),
                EnumEstadosPendientes.ANALISIS.getCodigo(),
                EnumEstadosPendientes.PRUEBAS.getCodigo(),
                EnumEstadosPendientes.DISENO.getCodigo(),
                EnumEstadosPendientes.DOCUMENTACION.getCodigo(),
                EnumEstadosPendientes.PENDIENTE.getCodigo(),
                EnumEstadosPendientes.PROGRAMADO.getCodigo());
    }

    public static void main(String arg[]) {
        try {
            //System.out.println("fechaRetorno 2:" + Utilidades.getDateSimpleDateFormat("dd-MMM-yyyy", "24-ene-2022"));
            System.out.println(getEstadoPendientes());
        } catch (Exception ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static String getUrlBase(String uri, String path) {
        return uri.replaceFirst(path, "");
    }

}
