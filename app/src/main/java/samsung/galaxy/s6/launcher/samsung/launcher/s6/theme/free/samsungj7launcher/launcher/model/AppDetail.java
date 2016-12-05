package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by iamla on 11/23/2016.
 */
public class AppDetail implements Serializable {
    public Bitmap appBitmap;
    public String appName;
    public Drawable icon;
    public Intent intent;
    public boolean isSystemPackage;
    public String packageName;

    public AppDetail(String packageName, Drawable appIcon, String label, boolean isSystemPackage) {
        this.icon = appIcon;
        this.packageName = packageName;
        this.appName = label;
        this.isSystemPackage = isSystemPackage;
    }
}
