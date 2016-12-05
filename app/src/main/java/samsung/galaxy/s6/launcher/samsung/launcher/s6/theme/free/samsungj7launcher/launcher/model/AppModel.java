package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;

/**
 * Created by iamla on 11/23/2016.
 */
public class AppModel {
    public ArrayList<String> appPackages;
    private Context f617c;
    public ArrayList<AppDetail> customAppList;

    public AppModel(Context c) {
        this.f617c = c;
        getAppListFirstFrag();
        getPackages();
    }

    private void getPackages() {
        this.appPackages = new ArrayList();
        for (int i = 0; i < this.customAppList.size(); i++) {
            this.appPackages.add(((AppDetail) this.customAppList.get(i)).packageName);
            this.appPackages.add(((AppDetail) this.customAppList.get(i)).appName);
        }
    }

    public Drawable getCustomIcon(String packageName) {
        int i = 0;
        while (i < this.customAppList.size()) {
            if (((AppDetail) this.customAppList.get(i)).appName.toString().equalsIgnoreCase(packageName) || ((AppDetail) this.customAppList.get(i)).packageName.toString().equalsIgnoreCase(packageName)) {
                return ((AppDetail) this.customAppList.get(i)).icon;
            }
            i++;
        }
        return null;
    }

    public void getAppListFirstFrag() {
        this.customAppList = new ArrayList();
        this.customAppList.add(new AppDetail("com.google.android.apps.messaging", this.f617c.getResources().getDrawable(R.mipmap.message), "Messages", true));
        AppDetail app = new AppDetail("com.android.settings", this.f617c.getResources().getDrawable(R.mipmap.settings), "Settings", true);
        app.intent = new Intent("com.android.settings");
        this.customAppList.add(app);
        this.customAppList.add(new AppDetail(GooglePlayServicesUtil.GOOGLE_PLAY_STORE_PACKAGE, this.f617c.getResources().getDrawable(R.mipmap.store), "Play", true));
        this.customAppList.add(new AppDetail("com.facebook.katana", this.f617c.getResources().getDrawable(R.mipmap.facebook), "Facebook", false));
        this.customAppList.add(new AppDetail("com.android.chrome", this.f617c.getResources().getDrawable(R.mipmap.browser), "Internet", true));
        this.customAppList.add(new AppDetail("com.whatsapp", this.f617c.getResources().getDrawable(R.mipmap.whatsapp), "Whatsapp", false));
        this.customAppList.add(new AppDetail("com.google.android.apps.photos", this.f617c.getResources().getDrawable(R.mipmap.gallery), "Album", true));
        this.customAppList.add(new AppDetail("com.google.android.GoogleCamera", this.f617c.getResources().getDrawable(R.mipmap.camera), "Camera", true));
        this.customAppList.add(new AppDetail("com.google.android.deskclock", this.f617c.getResources().getDrawable(R.mipmap.clock), "Clock", true));
        this.customAppList.add(new AppDetail("com.samsung.everglades.video", this.f617c.getResources().getDrawable(R.mipmap.videos), "Video", true));
        this.customAppList.add(new AppDetail("com.facebook.orca", this.f617c.getResources().getDrawable(R.mipmap.facebook_chat), "FB Chat", false));
        this.customAppList.add(new AppDetail("com.google.android.music", this.f617c.getResources().getDrawable(R.mipmap.music), "Music", true));
        this.customAppList.add(new AppDetail("com.twitter.android", this.f617c.getResources().getDrawable(R.mipmap.twitter), "Twitter", false));
        this.customAppList.add(new AppDetail("com.google.android.apps.maps", this.f617c.getResources().getDrawable(R.mipmap.maps), "Maps", true));
        this.customAppList.add(new AppDetail("com.android2.calculator3", this.f617c.getResources().getDrawable(R.mipmap.calculator), "Calculator", true));
        this.customAppList.add(new AppDetail("com.android.contacts", this.f617c.getResources().getDrawable(R.mipmap.contacts), "Contacts", false));
        this.customAppList.add(new AppDetail("com.google.android.googlequicksearchbox", this.f617c.getResources().getDrawable(R.mipmap.google), "Google", true));
        this.customAppList.add(new AppDetail("com.android.calendar", this.f617c.getResources().getDrawable(R.mipmap.calendar), "Calendar", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.calendar), "S Planner", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.browser), "Internet", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.calculator), "Calculator", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.camera), "Camera", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.clock), "Clock", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.facebook), "Facebook", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.facebook_chat), "Messenger", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.gallery), "Gallery", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.google), "Google", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.maps), "Maps", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.music), "Music", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.phone), "Phone", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.videos), "Video", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.twitter), "Twitter", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.message), "Messaging", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.shealth), "S Health", false));
        this.customAppList.add(new AppDetail(BuildConfig.FLAVOR, this.f617c.getResources().getDrawable(R.mipmap.store), "Play Store", false));
    }
}
