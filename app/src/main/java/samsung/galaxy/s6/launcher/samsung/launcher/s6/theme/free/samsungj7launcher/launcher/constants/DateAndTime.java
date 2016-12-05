package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants;

import java.util.Calendar;
import java.util.Locale;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;

/**
 * Created by iamla on 11/23/2016.
 */
public class DateAndTime {
    public static String hour() {
        return getHourFormate(Calendar.getInstance().get(Calendar.HOUR));
    }

    public static String minute() {
        return ":" + getMinute_SecondFormate(Calendar.getInstance().get(Calendar.MINUTE));
    }

    public static String date() {
        Calendar cal = Calendar.getInstance();
        int d = cal.get(Calendar.AM_PM);
        String Month = cal.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US);
        String Day = cal.getDisplayName(Calendar.DAY_OF_MONTH, Calendar.SHORT, Locale.US);
        String a = cal.getDisplayName(Calendar.HOUR, Calendar.SHORT, Locale.US);
        return Day + "  " + Month + " " + d;
    }

    private static String getHourFormate(int no) {
        String n = BuildConfig.FLAVOR;
        if (no < 10 && no >= 1) {
            n = String.format("0%s", new Object[]{Integer.valueOf(no)});
        }
        if (no >= 10) {
            n = String.valueOf(no);
        }
        if (no == 0) {
            return String.valueOf(12);
        }
        return n;
    }

    private static String getMinute_SecondFormate(int no) {
        String n = BuildConfig.FLAVOR;
        if (no < 10 && no >= 0) {
            n = String.format("0%s", new Object[]{Integer.valueOf(no)});
        }
        if (no >= 10) {
            return String.valueOf(no);
        }
        return n;
    }
}

