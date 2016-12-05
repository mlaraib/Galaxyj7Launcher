package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.features;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RadioButton;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.adapter.ThemeAdapter;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment.LauncherActivity;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;

/**
 * Created by iamla on 11/24/2016.
 */
public class ThemeSelection extends Activity {
    ThemeAdapter adapter;
    ArrayList<AppDetail> apps;
    Context context;
    GridView dgv;
    ImageView gridItem;
    InterstitialAd mInterstitialAd;
    RadioButton radioCircle;
    RadioButton radioNone;
    RadioButton radioSquare;

    /* renamed from: launcher.features.ThemeSelection.1 */
    class C04111 implements OnCheckedChangeListener {
        C04111() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            System.gc();
            if (isChecked) {
                Settings.setTheme(0, ThemeSelection.this.context);
                ThemeSelection.this.adapter = new ThemeAdapter(ThemeSelection.this, ThemeSelection.this.apps);
                ThemeSelection.this.dgv.setAdapter(ThemeSelection.this.adapter);
                ThemeSelection.this.sendBroadcast(new Intent(LauncherActivity.UPDATE_THEME));
                ThemeSelection.this.loadAdd();
            }
        }
    }

    /* renamed from: launcher.features.ThemeSelection.2 */
    class C04122 implements OnCheckedChangeListener {
        C04122() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                Settings.setTheme(1, ThemeSelection.this.context);
                ThemeSelection.this.adapter = new ThemeAdapter(ThemeSelection.this, ThemeSelection.this.apps);
                ThemeSelection.this.dgv.setAdapter(ThemeSelection.this.adapter);
                ThemeSelection.this.sendBroadcast(new Intent(LauncherActivity.UPDATE_THEME));
                ThemeSelection.this.loadAdd();
            }
        }
    }

    /* renamed from: launcher.features.ThemeSelection.3 */
    class C04133 implements OnCheckedChangeListener {
        C04133() {
        }

        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (isChecked) {
                Settings.setTheme(2, ThemeSelection.this.context);
                ThemeSelection.this.adapter = new ThemeAdapter(ThemeSelection.this, ThemeSelection.this.apps);
                ThemeSelection.this.dgv.setAdapter(ThemeSelection.this.adapter);
                ThemeSelection.this.sendBroadcast(new Intent(LauncherActivity.UPDATE_THEME));
                ThemeSelection.this.loadAdd();
            }
        }
    }

    /* renamed from: launcher.features.ThemeSelection.4 */
    class C07344 extends AdListener {
        C07344() {
        }

        public void onAdLoaded() {
            if (ThemeSelection.this.mInterstitialAd.isLoaded() && ThemeSelection.this.mInterstitialAd != null) {
                ThemeSelection.this.mInterstitialAd.show();
            }
            super.onAdLoaded();
        }
    }

    public ThemeSelection() {
        this.mInterstitialAd = null;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (VERSION.SDK_INT >= 21) {
            window.addFlags(ExploreByTouchHelper.INVALID_ID);
            window.clearFlags(67108864);
            window.setStatusBarColor(getResources().getColor(17170445));
        }
        setContentView(R.layout.activity_theme_selection);
        this.context = this;
        this.dgv = (GridView) findViewById(R.id.vgv);
        this.dgv.setVisibility(View.VISIBLE);
        this.apps = new ArrayList();
        for (int i = 0; i < ((ArrayList) LauncherActivity.appsList.get(1)).size(); i++) {
            this.apps.add((LauncherActivity.appsList.get(1)).get(i));
        }
        this.radioNone = (RadioButton) findViewById(R.id.radioNone);
        this.radioCircle = (RadioButton) findViewById(R.id.radioCircle);
        this.radioSquare = (RadioButton) findViewById(R.id.radioSquare);
        switch (Settings.getTheme(this.context)) {
            case ConnectionResult.SUCCESS /*0*/:
                this.radioNone.setChecked(true);
                break;
            case ConnectionResult.SERVICE_MISSING /*1*/:
                this.radioCircle.setChecked(true);
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                this.radioSquare.setChecked(true);
                break;
        }
        this.adapter = new ThemeAdapter(this, this.apps);
        this.dgv.setAdapter(this.adapter);
        this.radioNone.setOnCheckedChangeListener(new C04111());
        this.radioCircle.setOnCheckedChangeListener(new C04122());
        this.radioSquare.setOnCheckedChangeListener(new C04133());
    }

    private void loadAdd() {
        if (this.mInterstitialAd == null) {
            this.mInterstitialAd = new InterstitialAd(this);
            this.mInterstitialAd.setAdUnitId("ca-app-pub-8746828918162796/3962909066");
            this.mInterstitialAd.loadAd(new Builder().build());
            this.mInterstitialAd.setAdListener(new C07344());
        }
    }

    protected void onPause() {
        super.onPause();
        System.gc();
    }
}

