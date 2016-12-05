package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment;


import android.content.res.Resources.NotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
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
import android.widget.Button;
import java.util.Collections;
import java.util.Comparator;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView.CircleIndicator;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.transition.Transition;


/**
 * Created by iamla on 11/28/2016.
 */
public class AllAppsActivity extends FragmentActivity implements OnPageChangeListener, OnClickListener {
    Button btnEdit;
    Button btnSort;
    int currentPage;
    CircleIndicator defaultIndicator;
    private ViewPager mPager;
    private ScreenSlidePagerAdapter mPagerAdapter;

    public class CustomComparator implements Comparator<AppDetail> {
        public int compare(AppDetail o1, AppDetail o2) {
            return o1.appName.compareTo(o2.appName);
        }
    }

    public class ScreenSlidePagerAdapter extends FragmentPagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return AllAppsFragment.create(position);
        }

        public int getCount() {
            if (LauncherActivity.appsList.size() > 0) {
                return LauncherActivity.appsList.size();
            }
            return 0;
        }
    }

    public AllAppsActivity() {
        this.currentPage = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
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
                window.setStatusBarColor(getResources().getColor(17170445));
            }
        } catch (NotFoundException e22) {
            e22.printStackTrace();
        }
        setContentView(R.layout.activity_all_apps);
        this.mPager = (ViewPager) findViewById(R.id.pagerView);
        this.mPager.addOnPageChangeListener(this);
        this.defaultIndicator = (CircleIndicator) findViewById(R.id.indicator_default);
        this.mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        this.mPager.setAdapter(this.mPagerAdapter);
        this.defaultIndicator.setViewPager(this.mPager);
        Transition.setTransform(this, this.mPager);
        this.mPager.setCurrentItem(this.currentPage);
        this.btnEdit = (Button) findViewById(R.id.buttonEdit);
        this.btnSort = (Button) findViewById(R.id.buttonSort);
        this.btnEdit.setOnClickListener(this);
        this.btnSort.setOnClickListener(this);
        this.btnEdit.setTypeface(Utils.getFont(this));
        this.btnSort.setTypeface(Utils.getFont(this));
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    public void onPageSelected(int position) {
        this.currentPage = position;
    }

    public void onPageScrollStateChanged(int state) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonEdit /*2131558493*/:
                if (Settings.getIsEditMode(this)) {
                    Settings.setIsEditMode(false, this);
                } else {
                    Settings.setIsEditMode(true, this);
                }
                this.mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                this.mPager.setAdapter(this.mPagerAdapter);
                this.mPager.setCurrentItem(this.currentPage);
            case R.id.buttonSort /*2131558494*/:
                Collections.sort(LauncherActivity.apps, new CustomComparator());
                for (int i = 0; i < LauncherActivity.apps.size(); i++) {
                    Log.e("Before Sort", BuildConfig.FLAVOR + ((AppDetail) LauncherActivity.apps.get(i)).appName);
                }
                if (Settings.getIsRandom(this)) {
                    Settings.setIsRandom(false, this);
                } else {
                    Settings.setIsRandom(true, this);
                }
                this.mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                this.mPager.setAdapter(this.mPagerAdapter);
                this.mPagerAdapter.notifyDataSetChanged();
            default:
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 3 || keyCode == 26 || keyCode == 4 || keyCode == 82 || keyCode == 84) {
            if (Settings.getIsEditMode(this)) {
                Settings.setIsEditMode(false, this);
                this.mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
                this.mPager.setAdapter(this.mPagerAdapter);
                this.mPager.setCurrentItem(this.currentPage);
                return false;
            }
            super.onBackPressed();
        }
        return true;
    }
}

