package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants;

import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;

/**
 * Created by iamla on 11/23/2016.
 */
public class Settings {
    public static boolean getIsFirstTime(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("IsFirstTime", false);
    }

    public static void setIsFirstTime(boolean IsFirstTime, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("IsFirstTime", IsFirstTime);
        editor.commit();
    }

    public static String getTaskOne(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("TaskOne", BuildConfig.FLAVOR);
    }

    public static void setTaskOne(String TaskOne, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("TaskOne", TaskOne);
        editor.commit();
    }

    public static String getTaskTwo(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("TaskTwo", BuildConfig.FLAVOR);
    }

    public static void setTaskTwo(String TaskTwo, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("TaskTwo", TaskTwo);
        editor.commit();
    }

    public static String getTaskThree(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("TaskThree", BuildConfig.FLAVOR);
    }

    public static void setTaskThree(String TaskThree, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("TaskThree", TaskThree);
        editor.commit();
    }

    public static String getTaskFour(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("TaskFour", BuildConfig.FLAVOR);
    }

    public static void setTaskFour(String TaskFour, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("TaskFour", TaskFour);
        editor.commit();
    }

    public static String getUserImage(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString("UserImage", BuildConfig.FLAVOR);
    }

    public static void setUserImage(String UserImage, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString("UserImage", UserImage);
        editor.commit();
    }

    public static int getTheme(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("ThemeIndex", 2);
    }

    public static void setTheme(int Theme, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("ThemeIndex", Theme);
        editor.commit();
    }

    public static int getTransform(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("TransformIndex", 10);
    }

    public static void setTransform(int TransformIndex, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("TransformIndex", TransformIndex);
        editor.commit();
    }

    public static int getFont(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("FontIndex", 0);
    }

    public static void setFont(int FontIndex, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("FontIndex", FontIndex);
        editor.commit();
    }

    public static int getFontColor(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("FontColor", -1);
    }

    public static void setFontColor(int FontColor, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putInt("FontColor", FontColor);
        editor.commit();
    }

    public static String getBackgroundImagePath(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(Constants.BACKGROUND_KEY, null);
    }

    public static void setBackgroundImagePath(String imagePath, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putString(Constants.BACKGROUND_KEY, imagePath);
        editor.commit();
    }

    public static boolean getShowAdd(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("ShowAdd", true);
    }

    public static void setShowAdd(boolean ShowAdd, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("ShowAdd", ShowAdd);
        editor.commit();
    }

    public static boolean getIsRandom(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("IsRandom", true);
    }

    public static void setIsRandom(boolean IsRandom, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("IsRandom", IsRandom);
        editor.commit();
    }

    public static boolean getIsEditMode(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("IsEditMode", false);
    }

    public static void setIsEditMode(boolean IsEditMode, Context context) {
        Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editor.putBoolean("IsEditMode", IsEditMode);
        editor.commit();
    }
}

