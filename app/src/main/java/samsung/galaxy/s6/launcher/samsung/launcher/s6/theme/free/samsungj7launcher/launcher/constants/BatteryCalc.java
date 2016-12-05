package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants;

import android.content.Context;

/**
 * Created by iamla on 11/23/2016.
 */
public class BatteryCalc {
    Context context;

    public BatteryCalc(Context context) {
        this.context = context;
    }

    public int getBatteryRemainingTime(int level) {
        if (level <= 10) {
            return (int) (((double) level) * 6.75d);
        }
        if (level <= 20) {
            return (int) (((double) level) * 8.8d);
        }
        if (level <= 30) {
            return (int) (((double) level) * 9.9d);
        }
        if (level <= 40) {
            return (int) (((double) level) * 10.8d);
        }
        if (level <= 80) {
            return (int) (((double) level) * 14.15d);
        }
        if (level <= 90) {
            return (int) (((double) level) * 15.15d);
        }
        if (level <= 100) {
            return (int) (((double) level) * 17.25d);
        }
        return 0;
    }
}

