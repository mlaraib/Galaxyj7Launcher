package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;

/**
 * Created by iamla on 11/23/2016.
 */
public class MyPrefferences {
    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    public MyPrefferences(Context context) {
        this.context = context;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        this.editor = this.sharedPreferences.edit();
    }

    public boolean isPluggedState() {
        return this.sharedPreferences.getBoolean("key", true);
    }

    public void setPluggedState(boolean value) {
        this.editor.putBoolean("key", value).commit();
    }

    public boolean isNotifyState() {
        return this.sharedPreferences.getBoolean("notify", true);
    }

    public void setNotifyState(boolean value) {
        this.editor.putBoolean("notify", value).commit();
    }

    public boolean isModesExists() {
        return this.sharedPreferences.getBoolean("exists", false);
    }

    public void setModesExists(boolean value) {
        this.editor.putBoolean("exists", value).commit();
    }

    public String getModeName() {
        return this.sharedPreferences.getString("modeNem", BuildConfig.FLAVOR);
    }

    public void setModeName(String modeName) {
        this.editor.putString("modeNem", modeName).commit();
    }

    public boolean get100Percent() {
        return this.sharedPreferences.getBoolean("100", true);
    }

    public void set100Percent(boolean value) {
        this.editor.putBoolean("100", value).commit();
    }

    public boolean get80Percent() {
        return this.sharedPreferences.getBoolean("80", false);
    }

    public void set80Percent(boolean value) {
        this.editor.putBoolean("80", value).commit();
    }

    public boolean get60Percent() {
        return this.sharedPreferences.getBoolean("60", false);
    }

    public void set60Percent(boolean value) {
        this.editor.putBoolean("60", value).commit();
    }

    public boolean get50Percent() {
        return this.sharedPreferences.getBoolean("50", false);
    }

    public void set50Percent(boolean value) {
        this.editor.putBoolean("50", value).commit();
    }

    public boolean get30Percent() {
        return this.sharedPreferences.getBoolean("30", false);
    }

    public void set30Percent(boolean value) {
        this.editor.putBoolean("30", value).commit();
    }

    public boolean get20Percent() {
        return this.sharedPreferences.getBoolean("20", true);
    }

    public void set20Percent(boolean value) {
        this.editor.putBoolean("20", value).commit();
    }

    public boolean get10Percent() {
        return this.sharedPreferences.getBoolean("10", false);
    }

    public void set10Percent(boolean value) {
        this.editor.putBoolean("10", value).commit();
    }

    public void setBatteryLevel(int val) {
        this.editor.putInt("battery_level", val).commit();
    }

    public int getBatteryLevel() {
        return this.sharedPreferences.getInt("battery_level", -1);
    }

    public void setBatteryRemainingTime(int time) {
        this.editor.putInt("remain", time).commit();
    }

    public int getBatteryRemainingTime() {
        return this.sharedPreferences.getInt("remain", -1);
    }

    public void setResourceTime(int time) {
        this.editor.putInt("resource", time).commit();
        Log.e("resource time", time + BuildConfig.FLAVOR);
    }

    public int getResourceTime() {
        return this.sharedPreferences.getInt("resource", 0);
    }

    public void setGPS(boolean value) {
        this.editor.putBoolean("gps", value).commit();
    }

    public boolean isGPSEnabled() {
        return this.sharedPreferences.getBoolean("gps", false);
    }

    public void setFligh(boolean value) {
        this.editor.putBoolean("flight", value).commit();
    }

    public boolean isFlighDisabled() {
        return this.sharedPreferences.getBoolean("flight", true);
    }

    public void setOptimizeLevel(int value) {
        this.editor.putInt("opt_level", value).commit();
    }

    public int getOptimizeLevel() {
        return this.sharedPreferences.getInt("opt_level", -1);
    }

    public void setModeValue(int value) {
        this.editor.putInt("mode_value", value).commit();
    }

    public int getModeValue() {
        return this.sharedPreferences.getInt("mode_value", 0);
    }

    public void setAppOtimize(boolean value) {
        this.editor.putBoolean("isAppOtimize", value).commit();
    }

    public boolean isAppOtimize() {
        return this.sharedPreferences.getBoolean("isAppOtimize", false);
    }

    public void setBootComplete(boolean value) {
        this.editor.putBoolean("isBootComplete", value).commit();
    }

    public boolean isBootComplete() {
        return this.sharedPreferences.getBoolean("isBootComplete", false);
    }

    public void setAirPlaneRead(boolean value) {
        this.editor.putBoolean("setAirPlaneRead", value).commit();
    }

    public boolean isAirPlaneRead() {
        return this.sharedPreferences.getBoolean("setAirPlaneRead", false);
    }
}

