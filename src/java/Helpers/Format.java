package Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Patricio
 */
public class Format {

    public static String date(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
        String formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);

        return formattedDate;
    }

    public static String dateDD(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
        String formattedDate = new SimpleDateFormat("dd").format(date);

        return formattedDate;
    }

    public static String dateMonth(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
        String formattedDate = new SimpleDateFormat("MM").format(date);

        int month = Integer.parseInt(formattedDate);
        String monthString;
        switch (month) {
            case 1:
                monthString = "Enero";
                break;
            case 2:
                monthString = "Febrero";
                break;
            case 3:
                monthString = "Marzo";
                break;
            case 4:
                monthString = "Abril";
                break;
            case 5:
                monthString = "Mayo";
                break;
            case 6:
                monthString = "Junio";
                break;
            case 7:
                monthString = "Julio";
                break;
            case 8:
                monthString = "Agosto";
                break;
            case 9:
                monthString = "Septiembre";
                break;
            case 10:
                monthString = "Octubre";
                break;
            case 11:
                monthString = "Noviembre";
                break;
            case 12:
                monthString = "Diciembre";
                break;
            default:
                monthString = "Mes inválido";
                break;
        }

        return monthString;
    }

    public static String dateYYYYMMDD(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

        return formattedDate;
    }

    public static String currentDate() {

        Calendar d = new GregorianCalendar();

        int year = d.get(Calendar.YEAR);
        int month = d.get(Calendar.MONTH) + 1; //0 == january
        int day = d.get(Calendar.DAY_OF_MONTH);
        int hour = d.get(Calendar.HOUR_OF_DAY);
        int minute = d.get(Calendar.MINUTE);
        int second = d.get(Calendar.SECOND);

        String sMonth = (month < 10) ? "0" + month : "" + month;
        String sDay = (day < 10) ? "0" + day : "" + day;
        String sHour = (hour < 10) ? "0" + hour : "" + hour;
        String sMinute = (minute < 10) ? "0" + minute : "" + minute;
        String sSecond = (second < 10) ? "0" + second : "" + second;

        String cd = year + "-" + sMonth + "-" + sDay + "T" + sHour + ":" + sMinute;

        return cd;
    }
    
     public static String currentDateYYMMDDDD() {

        Calendar d = new GregorianCalendar();

        int year = d.get(Calendar.YEAR);
        int month = d.get(Calendar.MONTH) + 1; //0 == january
        int day = d.get(Calendar.DAY_OF_MONTH);
        int hour = d.get(Calendar.HOUR_OF_DAY);
        int minute = d.get(Calendar.MINUTE);
        int second = d.get(Calendar.SECOND);

        String sMonth = (month < 10) ? "0" + month : "" + month;
        String sDay = (day < 10) ? "0" + day : "" + day;
        String sHour = (hour < 10) ? "0" + hour : "" + hour;
        String sMinute = (minute < 10) ? "0" + minute : "" + minute;
        String sSecond = (second < 10) ? "0" + second : "" + second;

        String cd = year + "-" + sMonth + "-" + sDay;

        return cd;
    }

    public static String capital(String text) {
        String[] words = text.split("\\s+");
        StringBuilder textFormatted = new StringBuilder();

        for (String word : words) {
            textFormatted.append(word.substring(0, 1).toUpperCase()
                    .concat(word.substring(1, word.length())
                    .toLowerCase()).concat(" "));
        }

        return textFormatted.toString();
    }

    public static String dateDDMMYYYY(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(date);

        return formattedDate;
    }
    
    public static String date2DDMMYYYY(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("dd/MM/yyyy").parse(d);
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

        return formattedDate;
    }

    public static String dateDdMmYyyyTHrMmSs(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(d);
        String formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(date);

        return formattedDate;
    }

    public static String dateYYYYMMDDmmss(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        return formattedDate;
    }

    public static String dateYYYYMMDDTmmss(String d) throws ParseException {

        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(d);
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        return formattedDate;
    }

    public boolean validarUrl(String url_image) {
        url_image.toLowerCase();
        boolean correcto = false;
        if (((url_image).matches(".*\\.jpg")) || ((url_image).matches(".*\\.png")) || ((url_image).matches(".*\\.bmp"))) {
            correcto = true;
        }
        return correcto;
    }
}
