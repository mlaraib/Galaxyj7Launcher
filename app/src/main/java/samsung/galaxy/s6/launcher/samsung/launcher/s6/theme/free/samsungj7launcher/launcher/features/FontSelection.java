package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.features;


import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ExploreByTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.adapter.ThemeAdapter;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment.LauncherActivity;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;


/**
 * Created by iamla on 11/24/2016.
 */
public class FontSelection extends FragmentActivity implements OnItemSelectedListener {
    Context context;
    int counter;
    private PageAdapter mAdapter;
    InterstitialAd mInterstitialAd;
    private ViewPager mPager;
    private int mSelectedItem;
    Spinner spinnerAddress;
    String[] transformValues;

    /* renamed from: launcher.features.FontSelection.1 */
    class C04101 extends ArrayAdapter<String> {
        C04101(Context x0, int x1, String[] x2) {
            super(x0, x1, x2);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            ((TextView) v).setGravity(3);
            ((TextView) v).setTypeface(Utils.getFont(FontSelection.this.context));
            return v;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v = super.getDropDownView(position, convertView, parent);
            ((TextView) v).setTextSize(16.0f);
            ((TextView) v).setTypeface(Utils.getFont(FontSelection.this.context, position));
            return v;
        }
    }

    /* renamed from: launcher.features.FontSelection.2 */
    class C07332 extends AdListener {
        C07332() {
        }

        public void onAdLoaded() {
            if (FontSelection.this.mInterstitialAd.isLoaded() && FontSelection.this.mInterstitialAd != null) {
                FontSelection.this.mInterstitialAd.show();
            }
            super.onAdLoaded();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final int[] COLORS;
        private static final String EXTRA_POSITION = "EXTRA_POSITION";
        ThemeAdapter adapter;
        ArrayList<AppDetail> apps;
        GridView dgv;
        ImageView gridItem;
        public ViewGroup rootView;

        static {
            COLORS = new int[]{-13388315, -5609780, -6697984, -17613, -48060};
        }

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            this.rootView = (ViewGroup) inflater.inflate(R.layout.app_grid_layout, container, false);
            int position = getArguments().getInt(EXTRA_POSITION);
            this.dgv = (GridView) this.rootView.findViewById(R.id.vgv);
            this.dgv.setVisibility(View.VISIBLE);
            this.apps = new ArrayList();
            for (int i = 0; i < ((ArrayList) LauncherActivity.appsList.get(0)).size(); i++) {
                this.apps.add(( LauncherActivity.appsList.get(0)).get(i));
            }
            this.adapter = new ThemeAdapter(getActivity(), this.apps);
            this.dgv.setAdapter(this.adapter);
            return this.rootView;
        }
    }

    private static final class PageAdapter extends FragmentPagerAdapter {
        public PageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int position) {
            Bundle bundle = new Bundle();
            bundle.putInt("EXTRA_POSITION", position + 1);
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        public int getCount() {
            if (LauncherActivity.appsList.size() > 0) {
                return 1;
            }
            return 0;
        }
    }

    public FontSelection() {
        this.transformValues = new String[]{"normal-font", "bold-font", "CarroisGothicSC-Regular", "Clockopia", "ComingSoon", "CutiveMono", "DancingScript-Bold", "DancingScript-Regular", "EKLEKTIN", "F021004D", "FIRECAT", "FLINPO__", "GALLERIN", "GIGI", "lightfont", "Roboto-Bold"};
        this.counter = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (VERSION.SDK_INT >= 21) {
            window.addFlags(ExploreByTouchHelper.INVALID_ID);
            window.clearFlags(67108864);
            window.setStatusBarColor(getResources().getColor(17170445));
        }
        setContentView(R.layout.activity_font_selection);
        this.context = this;
        initializeSpinner();
        this.mPager = (ViewPager) findViewById(R.id.container);
        this.mAdapter = new PageAdapter(getSupportFragmentManager());
        this.mPager.setAdapter(this.mAdapter);
        this.mPager.setCurrentItem(0);
    }

    private void initializeSpinner() {
        this.spinnerAddress = (Spinner) findViewById(R.id.spinnerTransform);
        this.spinnerAddress.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new C04101(this.context, R.layout.spinner, this.transformValues);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinnerAddress.setAdapter(adapter);
        this.spinnerAddress.setSelection(Settings.getFont(this.context));
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (this.counter == 3) {
            loadAdd();
        }
        this.counter++;
        Settings.setFont(position, this.context);
        this.mAdapter = new PageAdapter(getSupportFragmentManager());
        this.mPager.setAdapter(this.mAdapter);
        System.gc();
        sendBroadcast(new Intent(LauncherActivity.UPDATE_THEME));
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void loadAdd() {
        if (this.mInterstitialAd == null) {
            this.mInterstitialAd = new InterstitialAd(this);
            this.mInterstitialAd.setAdUnitId("ca-app-pub-8746828918162796/2486175869");
            this.mInterstitialAd.loadAd(new Builder().build());
            this.mInterstitialAd.setAdListener(new C07332());
        }
    }
}

