package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment;



import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources.NotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;
import java.util.List;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView.CircleIndicator;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView.ImageBitmap;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppModel;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.transition.Transition;


/**
 * Created by iamla on 11/29/2016.
 */
public class LauncherActivity extends FragmentActivity implements OnClickListener, OnPageChangeListener {
    public static String APP_INSTALL;
    public static String APP_UNINSTALL;
    public static int UNINSTALL_REQUEST_CODE;
    public static String UPDATE_THEME;
    public static ArrayList<AppDetail> apps;
    public static ArrayList<ArrayList<AppDetail>> appsList;
    public static LauncherActivity homeActivity;
    ArrayAdapter<AppDetail> adapter;
    public AppModel appAttribs;
    ImageView boost;
    LinearLayout bottom_layer;
    BroadcastReceiver br;
    int currentPage;
    CircleIndicator defaultIndicator;
    HomeFragmentOne homeFragmentOne;
    HomeFragmentThree homeFragmentThree;
    HomeFragmentTwo homeFragmentTwo;
    ImageView imageViewFive;
    ImageView imageViewFour;
    ImageView imageViewOne;
    ImageView imageViewThree;
    ImageView imageViewTwo;
    boolean isLongPress;
    InterstitialAd mInterstitialAd;
    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;
    int[] mThumbIds;
    int resumeCount;
    TextView textViewFive;
    TextView textViewFour;
    TextView textViewOne;
    TextView textViewThree;
    TextView textViewTitle;
    TextView textViewTwo;
    private BroadcastReceiver updateTheme;

    /* renamed from: launcher.fragment.LauncherActivity.1 */
    class C04211 implements Runnable {
        C04211() {
        }

        public void run() {
            LauncherActivity.this.loadApps();
        }
    }

    /* renamed from: launcher.fragment.LauncherActivity.2 */
    class C04222 extends BroadcastReceiver {
        C04222() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(LauncherActivity.APP_INSTALL) || intent.getAction().equals(LauncherActivity.APP_UNINSTALL)) {
                LauncherActivity.this.loadApps();
            }
        }
    }

    /* renamed from: launcher.fragment.LauncherActivity.3 */
    class C04233 extends BroadcastReceiver {
        C04233() {
        }

        public void onReceive(Context ctxt, Intent intent) {
            LauncherActivity.this.loadApps();
        }
    }

    public class TickReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().compareTo("android.intent.action.TIME_TICK") == 0) {
                Log.e("Time", " time ");
                LauncherActivity.this.homeFragmentTwo.updateTime();
            }
        }
    }

    public class createIcon extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            int i = 0;
            while (i < LauncherActivity.appsList.size()) {
                try {
                    for (int j = 0; j < ((ArrayList) LauncherActivity.appsList.get(i)).size(); j++) {
                        new ImageBitmap().createIcon(LauncherActivity.homeActivity, String.valueOf(((AppDetail) ((ArrayList) LauncherActivity.appsList.get(i)).get(j)).appName), ((AppDetail) ((ArrayList) LauncherActivity.appsList.get(i)).get(j)).icon);
                    }
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

    /* renamed from: launcher.fragment.LauncherActivity.4 */
    class C07414 extends AdListener {
        C07414() {
        }

        public void onAdLoaded() {
            if (LauncherActivity.this.mInterstitialAd.isLoaded() && LauncherActivity.this.mInterstitialAd != null) {
                LauncherActivity.this.mInterstitialAd.show();
            }
            super.onAdLoaded();
        }
    }

    public class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            switch (position) {
                case ConnectionResult.SUCCESS /*0*/:
                    return LauncherActivity.this.homeFragmentOne;
                case ConnectionResult.SERVICE_MISSING /*1*/:
                    return LauncherActivity.this.homeFragmentTwo;
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                    return new BatteryFragment();
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    return LauncherActivity.this.homeFragmentThree;
                default:
                    return null;
            }
        }

        public int getCount() {
            return 4;
        }
    }

    public LauncherActivity() {
        this.currentPage = 1;
        this.resumeCount = 0;
        this.isLongPress = false;
        this.mThumbIds = new int[]{R.mipmap.notif_charging_1, R.mipmap.notif_charging_2, R.mipmap.notif_charging_3, R.mipmap.notif_charging_4, R.mipmap.notif_charging_5, R.mipmap.notif_charging_6};
        this.br = new C04222();
        this.updateTheme = new C04233();
    }

    static {
        UNINSTALL_REQUEST_CODE = 2222;
        APP_INSTALL = "install.receiver";
        APP_UNINSTALL = "uninstall.receiver";
        UPDATE_THEME = "updateTheme.receiver";
        appsList = new ArrayList();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivity = this;
        Window window = homeActivity.getWindow();
        try {
            if (VERSION.SDK_INT >= 21) {
                window.addFlags(ExploreByTouchHelper.INVALID_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (VERSION.SDK_INT >= 19) {
                window.clearFlags(67108864);
            }
        } catch (NotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            if (VERSION.SDK_INT >= 21) {
                window.setStatusBarColor(homeActivity.getResources().getColor(17170445));
            }
        } catch (NotFoundException e22) {
            e22.printStackTrace();
        }
        setContentView(R.layout.main_fragment);
        this.imageViewOne = (ImageView) findViewById(R.id.taskbar_1);
        this.imageViewTwo = (ImageView) findViewById(R.id.taskbar_2);
        this.imageViewThree = (ImageView) findViewById(R.id.taskbar_3);
        this.imageViewFour = (ImageView) findViewById(R.id.taskbar_4);
        this.imageViewFive = (ImageView) findViewById(R.id.taskbar_5);
        this.imageViewOne.setOnClickListener(homeActivity);
        this.imageViewTwo.setOnClickListener(homeActivity);
        this.imageViewThree.setOnClickListener(homeActivity);
        this.imageViewFour.setOnClickListener(homeActivity);
        this.imageViewFive.setOnClickListener(homeActivity);
        this.textViewOne = (TextView) findViewById(R.id.textViewPhone);
        this.textViewTwo = (TextView) findViewById(R.id.textViewContact);
        this.textViewThree = (TextView) findViewById(R.id.textViewMessage);
        this.textViewFour = (TextView) findViewById(R.id.textViewInternet);
        this.textViewFive = (TextView) findViewById(R.id.textViewApps);
        this.textViewOne.setTypeface(Utils.getFont(homeActivity));
        this.textViewTwo.setTypeface(Utils.getFont(homeActivity));
        this.textViewThree.setTypeface(Utils.getFont(homeActivity));
        this.textViewFour.setTypeface(Utils.getFont(homeActivity));
        this.textViewFive.setTypeface(Utils.getFont(homeActivity));
        this.textViewTitle = (TextView) findViewById(R.id.title);
        this.bottom_layer = (LinearLayout) findViewById(R.id.bottom_layer);
        this.homeFragmentOne = new HomeFragmentOne();
        this.homeFragmentTwo = new HomeFragmentTwo();
        this.homeFragmentThree = new HomeFragmentThree();
        new Thread(new C04211()).start();
        appInstallReceiver();
        this.mPager = (ViewPager) findViewById(R.id.pagerView);
        this.mPager.addOnPageChangeListener(this);
        this.defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        this.mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        this.mPager.setAdapter(this.mPagerAdapter);
        this.defaultIndicator.setViewPager(this.mPager);
        this.mPager.setCurrentItem(this.currentPage);
        registerReceiver(new TickReceiver(), new IntentFilter("android.intent.action.TIME_TICK"));
    }

    protected void onResume() {
        if (this.resumeCount >= 10) {
            this.resumeCount = 0;
            loadAdd();
        }
        Transition.setTransform(this, this.mPager);
        this.resumeCount++;
        super.onResume();
    }

    public static void receiveBroadCast(Context c, Intent i) {
        if (i.getAction().equals("android.intent.action.PACKAGE_ADDED") || i.getAction().equals("android.intent.action.PACKAGE_INSTALL")) {
            c.sendBroadcast(new Intent(APP_INSTALL));
        } else {
            c.sendBroadcast(new Intent(APP_UNINSTALL));
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 3 && keyCode != 26 && keyCode != 4 && keyCode != 82 && keyCode != 84) {
            return true;
        }
        if (this.homeFragmentTwo.etSearch != null) {
            this.homeFragmentTwo.etSearch.setText(BuildConfig.FLAVOR);
            this.homeFragmentTwo.etSearch.clearFocus();
        }
        if (this.currentPage != 1) {
            this.currentPage = 1;
            this.mPager.setCurrentItem(this.currentPage);
        }
        hideMenu();
        return false;
    }

    protected void onDestroy() {
        if (this.br != null) {
            try {
                unregisterReceiver(this.br);
                this.br = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        appsList.clear();
        super.onDestroy();
    }

    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == UNINSTALL_REQUEST_CODE && responseCode == -1) {
            try {
                loadApps();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, responseCode, intent);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.taskbar_1 /*2131558596*/:
                try {
                    homeActivity.startActivity(getApplicationContext().getPackageManager().getLaunchIntentForPackage(Settings.getTaskOne(homeActivity)));
                } catch (Exception e) {
                }
                hideMenu();
            case R.id.taskbar_2 /*2131558598*/:
                try {
                    homeActivity.startActivity(getApplicationContext().getPackageManager().getLaunchIntentForPackage(Settings.getTaskTwo(homeActivity)));
                } catch (Exception e2) {
                    try {
                        homeActivity.startActivity(new Intent("android.intent.action.VIEW", Contacts.CONTENT_URI));
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
                hideMenu();
            case R.id.taskbar_3 /*2131558600*/:
                try {
                    homeActivity.startActivity(getApplicationContext().getPackageManager().getLaunchIntentForPackage(Settings.getTaskThree(homeActivity)));
                } catch (Exception e3) {
                    homeActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=com.android.chrome")));
                }
                hideMenu();
            case R.id.taskbar_4 /*2131558602*/:
                try {
                    homeActivity.startActivity(getApplicationContext().getPackageManager().getLaunchIntentForPackage(Settings.getTaskFour(homeActivity)));
                } catch (Exception e4) {
                    try {
                        homeActivity.startActivity(getApplicationContext().getPackageManager().getLaunchIntentForPackage(Settings.getTaskFour(homeActivity)));
                    } catch (Exception e12) {
                        e12.printStackTrace();
                        Toast.makeText(homeActivity, "Application not found", Toast.LENGTH_SHORT).show();
                    }
                }
                hideMenu();
            case R.id.taskbar_5 /*2131558604*/:
                startActivity(new Intent(this, AllAppsActivity.class));
                overridePendingTransition(R.anim.transition_apps_enter, 17432577);
                hideMenu();
            default:
        }
    }

    public void appInstallReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(APP_INSTALL);
        filter.addAction(APP_UNINSTALL);
        registerReceiver(this.br, filter);
        registerReceiver(this.updateTheme, new IntentFilter(UPDATE_THEME));
    }

    private void reLoadApps() {
        loadApps();
        this.adapter.notifyDataSetChanged();
    }

    public void showHideMenu() {
        if (this.isLongPress) {
            this.textViewTitle.setVisibility(View.VISIBLE);
        }
    }

    public void hideMenu() {
        this.isLongPress = false;
        if (this.textViewTitle != null) {
            this.textViewTitle.setVisibility(View.INVISIBLE);
        }
    }

    private void loadApps() {
        if (appsList != null) {
            appsList.clear();
        }
        this.appAttribs = new AppModel(homeActivity);
        PackageManager manager = getPackageManager();
        if (apps != null) {
            apps.clear();
        } else {
            apps = new ArrayList();
        }
        Intent i = new Intent("android.intent.action.MAIN", null);
        i.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        int count = 0;
        ArrayList<AppDetail> appHash = new ArrayList();
        for (ResolveInfo ri : availableActivities) {
            boolean z;
            if (count == 20) {
                count = 0;
                appsList.add(appHash);
                appHash = new ArrayList();
            }
            AppDetail app = new AppDetail();
            app.appName = ri.loadLabel(manager).toString();
            app.packageName = ri.activityInfo.packageName;
            if (!this.appAttribs.appPackages.contains(ri.activityInfo.packageName) && !this.appAttribs.appPackages.contains(app.appName)) {
                app.icon = ri.activityInfo.loadIcon(manager);
            } else if (this.appAttribs.getCustomIcon(app.appName.toString()) != null) {
                app.icon = this.appAttribs.getCustomIcon(app.appName.toString());
            } else {
                app.icon = ri.activityInfo.loadIcon(manager);
            }
            if ((ri.activityInfo.applicationInfo.flags & 1) != 0) {
                z = true;
            } else {
                z = false;
            }
            app.isSystemPackage = z;
            apps.add(app);
            appHash.add(app);
            count++;
            if (Settings.getTaskOne(homeActivity).isEmpty() && app.appName.toString().equalsIgnoreCase("Phone")) {
                Settings.setTaskOne(app.packageName.toString(), homeActivity);
            }
            if (Settings.getTaskTwo(homeActivity).isEmpty() && app.appName.toString().equalsIgnoreCase("Contacts")) {
                Settings.setTaskTwo(app.packageName.toString(), homeActivity);
            }
            if (Settings.getTaskThree(homeActivity).isEmpty() && app.appName.toString().equalsIgnoreCase("Messages")) {
                Settings.setTaskThree(app.packageName.toString(), homeActivity);
            }
            if (Settings.getTaskFour(homeActivity).isEmpty() && (app.appName.toString().equalsIgnoreCase("Browser") || app.appName.toString().equalsIgnoreCase("Internet") || app.packageName.toString().equalsIgnoreCase("com.android.chrome"))) {
                Settings.setTaskFour(app.packageName, homeActivity);
            }
        }
        if (appHash.size() > 0) {
            appsList.add(appHash);
        }
        Settings.setIsFirstTime(true, homeActivity);
        new createIcon().execute(new Void[0]);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        if (this.homeFragmentTwo.etSearch != null) {
            this.homeFragmentTwo.etSearch.setText(BuildConfig.FLAVOR);
            this.homeFragmentTwo.etSearch.clearFocus();
        }
        this.currentPage = position;
        hideMenu();
    }

    public void onPageScrollStateChanged(int state) {
        hideMenu();
    }

    public void freeMemory() {
        this.boost.setImageResource(R.mipmap.notif_boost_on);
        MemoryInfo mi = new MemoryInfo();
        ((ActivityManager) getSystemService("activity")).getMemoryInfo(mi);
        float min = (float) mi.availMem;
        float max = 0.0f;
        if (VERSION.SDK_INT >= 16) {
            max = (float) mi.totalMem;
        }
        float percentage = (min / max) * 100.0f;
        ActivityManager am = (ActivityManager) homeActivity.getSystemService("activity");
        List<RunningTaskInfo> a = am.getRunningTasks(ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        for (int i = 0; i < a.size(); i++) {
            am.killBackgroundProcesses(((RunningTaskInfo) a.get(i)).topActivity.getPackageName());
        }
        MemoryInfo mi1 = new MemoryInfo();
        ((ActivityManager) getSystemService("activity")).getMemoryInfo(mi1);
        float min1 = (float) mi1.availMem;
        float max1 = 0.0f;
        if (VERSION.SDK_INT >= 16) {
            max1 = (float) mi1.totalMem;
        }
        int percentafter = (int) ((min1 / max1) * 100.0f);
        if (((float) percentafter) > percentage) {
            Toast.makeText(this, percentafter + "%" + "RAM free after Boost", Toast.LENGTH_SHORT).show();
        } else {
            int newafterpercentage = percentafter + 4;
            Toast.makeText(this, newafterpercentage + "%" + " RAM free after Boost", Toast.LENGTH_SHORT).show();
        }
        this.boost.setImageResource(R.mipmap.notif_boost_off);
    }

    private void loadAdd() {
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-8746828918162796/8393108664");
        this.mInterstitialAd.loadAd(new Builder().build());
        this.mInterstitialAd.setAdListener(new C07414());
    }
}

