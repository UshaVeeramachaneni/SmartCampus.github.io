package app.college.smartcampus.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CommonUtils {

    private static SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
    private static SimpleDateFormat monthFormat = new SimpleDateFormat("MMM, yyyy", Locale.getDefault());
    public static SimpleDateFormat realFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static SimpleDateFormat noticeRealFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    public static SimpleDateFormat notificationFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());

    public static boolean isStringNotNull(String string) {
        return string != null && !string.equals("") && string.length() > 0;
    }

    public static String parseDate(String timeStamp) {
        if (timeStamp != null) {
            try {
                Date date = realFormat.parse(timeStamp);
                return format.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String notificationDate(String timeStamp) {
        if (timeStamp != null) {
            try {
                Date date = noticeRealFormat.parse(timeStamp);
                return notificationFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String parseDate(Long timeStamp) {
        if (timeStamp != null) {
            Date date = new Date(timeStamp);
            return monthFormat.format(date);
        }
        return null;
    }
}
