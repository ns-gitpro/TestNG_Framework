package commonUtilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static commonUtilities.PrintUtility.*;

public class DateLibrary
{
    private DateLibrary()
    {

    }
    static String defaultPattern = "dd-MM-yyyy";
    public static String getCurrentDate(String expectedFormat)
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(expectedFormat);
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
    }

    public static Date convertStringToDate(String dateString, String expectedFormat)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(expectedFormat);
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static LocalDate convertStringToLocalDate(String dateString, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateString, formatter);
    }

    public static String convertDateToString(Date date, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(date);
    }

    public static boolean checkIfDatesAreEqual(Date date1, Date date2)
    {
        long start = System.currentTimeMillis();
        boolean areEqual = false;
        String strDate1 = null;
        String strDate2 = null;
        try
        {
            areEqual = date1.equals(date2);
            if (areEqual==true)
            {
                strDate1 = convertDateToString(date1,defaultPattern);
                strDate2 = convertDateToString(date2,defaultPattern);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                logPass(printWaitTime("checkIfDatesAreEqual():",totalTime,"Date : "+strDate1+" is equal to date :"+strDate2));
            }
            else
            {
                strDate1 = convertDateToString(date1,defaultPattern);
                strDate2 = convertDateToString(date2,defaultPattern);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                logFail(printWaitTime("checkIfDatesAreEqual():",totalTime,"Date : "+strDate1+" is NOT equal to date :"+strDate2));
            }

        }
        catch (Exception e)
        {
            strDate1 = convertDateToString(date1,defaultPattern);
            strDate2 = convertDateToString(date2,defaultPattern);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logFail(printWaitTime("checkIfDatesAreEqual():",totalTime,e.getMessage()));
        }
        return areEqual;
    }

    public static boolean checkIfDatesAreEqual(String date1, String date2)
    {
        long start = System.currentTimeMillis();
        boolean areEqual = false;
        Date dtDate1 = null;
        Date dtDate2 = null;

        try
        {
            areEqual = dtDate1.equals(dtDate2);
            if(areEqual==true)
            {
                dtDate1 = convertStringToDate(date1,defaultPattern);
                dtDate2 = convertStringToDate(date2,defaultPattern);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                logPass(printWaitTime("checkIfDatesAreEqual():",totalTime,"Date : "+date1+" is equal to date :"+date2));
            }
            else
            {
                dtDate1 = convertStringToDate(date1,defaultPattern);
                dtDate2 = convertStringToDate(date2,defaultPattern);
                long finish = System.currentTimeMillis();
                long totalTime = finish - start;
                logFail(printWaitTime("checkIfDatesAreEqual():",totalTime,"Date : "+date1+" is NOT equal to date :"+date2));
            }
        }
        catch (Exception e)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logFail(printWaitTime("checkIfDatesAreEqual():",totalTime,e.getMessage()));
        }
        return areEqual;
    }

    public static long getDifferenceInDates(LocalDate date1, LocalDate date2)
    {
        long start = System.currentTimeMillis();
        long difference = Long.parseLong(null);
        try
        {
            difference = ChronoUnit.DAYS.between(date1, date2);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logPass(printWaitTime("getDifferenceInDates():",totalTime,"Difference Among Dates : "+difference));

        }
        catch (Exception e)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logPass(printWaitTime("getDifferenceInDates():",totalTime,e.getMessage()));
        }
        return difference;
    }

    public static long getDifferenceInDates(String date1, String date2)
    {

        long difference = Long.parseLong(null);
        long start = System.currentTimeMillis();
        try
        {
            LocalDate Ldate1 = convertStringToLocalDate(date1,defaultPattern);
            LocalDate Ldate2 = convertStringToLocalDate(date2,defaultPattern);
            difference = getDifferenceInDates(Ldate1, Ldate2);
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logPass(printWaitTime("getDifferenceInDates():",totalTime,"Difference Among Dates : "+difference));

        }
        catch (Exception e)
        {
            long finish = System.currentTimeMillis();
            long totalTime = finish - start;
            logPass(printWaitTime("getDifferenceInDates():",totalTime,e.getMessage()));
        }
        return difference;
    }

}
