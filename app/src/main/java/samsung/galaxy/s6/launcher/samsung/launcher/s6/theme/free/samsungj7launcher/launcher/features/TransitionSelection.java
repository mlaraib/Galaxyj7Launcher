package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.features;


import android.content.Context;
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
import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.BackgroundToForegroundTransformer;
import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.DepthPageTransformer;
import com.ToxicBakery.viewpager.transforms.ForegroundToBackgroundTransformer;
import com.ToxicBakery.viewpager.transforms.RotateDownTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.adapter.TransitionAdapter;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView.ImageBitmap;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment.LauncherActivity;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;


/**
 * Created by iamla on 11/28/2016.
 */
public class TransitionSelection extends FragmentActivity implements OnItemSelectedListener {
    Context context;
    int counter;
    private PageAdapter mAdapter;
    InterstitialAd mInterstitialAd;
    private ViewPager mPager;
    private int mSelectedItem;
    Spinner spinnerAddress;
    String[] transformValues;

    /* renamed from: launcher.features.TransitionSelection.1 */
    class C04141 extends ArrayAdapter<String> {
        C04141(Context x0, int x1, String[] x2) {
            super(x0, x1, x2);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View v = super.getView(position, convertView, parent);
            ((TextView) v).setGravity(3);
            return v;
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            View v = super.getDropDownView(position, convertView, parent);
            ((TextView) v).setTextSize(16.0f);
            return v;
        }
    }

    /* renamed from: launcher.features.TransitionSelection.2 */
    class C07352 extends AdListener {
        C07352() {
        }

        public void onAdLoaded() {
            if (TransitionSelection.this.mInterstitialAd.isLoaded() && TransitionSelection.this.mInterstitialAd != null) {
                TransitionSelection.this.mInterstitialAd.show();
            }
            super.onAdLoaded();
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final int[] COLORS;
        private static final String EXTRA_POSITION = "EXTRA_POSITION";
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
            for (int i = 0; i < ((ArrayList) LauncherActivity.appsList.get(position - 1)).size(); i++) {
                this.apps.add((LauncherActivity.appsList.get(position - 1)).get(i));
            }
            this.dgv.setAdapter(new TransitionAdapter(getActivity(), this.apps));
            return this.rootView;
        }

        public void setData(int index) {
            if (this.dgv != null) {
                for (int i = 0; i < this.apps.size(); i++) {
                    this.gridItem = new ImageView(getActivity());
                    ((AppDetail) this.apps.get(i)).appBitmap = null;
                    ((AppDetail) this.apps.get(i)).appBitmap = new ImageBitmap().getThumb(getActivity(), String.valueOf(((AppDetail) this.apps.get(i)).appName), ((AppDetail) this.apps.get(i)).icon, index);
                    this.gridItem.setImageBitmap(((AppDetail) this.apps.get(i)).appBitmap);
                    this.dgv.addView(this.gridItem);
                }
            }
        }
    }

    private static final class PageAdapter extends FragmentPagerAdapter {
        public PageAdapter(FragmentManager fm) {
            super(fm);
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
                return LauncherActivity.appsList.size();
            }
            return 0;
        }
    }

    public TransitionSelection() {
        this.transformValues = new String[]{"AccordionTransformer", "CubeInTransformer", "CubeOutTransformer", "DepthPageTransformer", "ForegroundToBackgroundTransformer", "BackgroundToForegroundTransformer", "RotateDownTransformer", "RotateUpTransformer", "ScaleInOutTransformer", "TabletTransformer", "ZoomOutSlideTransformer"};
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
        setContentView(R.layout.activity_transition_selection);
        this.context = this;
        this.mAdapter = new PageAdapter(getSupportFragmentManager());
        initializeSpinner();
        this.mPager = (ViewPager) findViewById(R.id.container);
        this.mPager.setAdapter(this.mAdapter);
        this.mPager.setCurrentItem(0);
        this.mPager.setOffscreenPageLimit(2);
    }

    private void initializeSpinner() {
        this.spinnerAddress = (Spinner) findViewById(R.id.spinnerTransform);
        this.spinnerAddress.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapter = new C04141(this.context, R.layout.spinner, this.transformValues);
        adapter.setDropDownViewResource(17367049);
        this.spinnerAddress.setAdapter(adapter);
        this.spinnerAddress.setSelection(Settings.getTransform(this.context));
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        if (this.counter == 4) {
            loadAdd();
        }
        this.counter++;
        switch (position) {
            case ConnectionResult.SUCCESS /*0*/:
                this.mPager.setPageTransformer(true, new AccordionTransformer());
                break;
            case ConnectionResult.SERVICE_MISSING /*1*/:
                this.mPager.setPageTransformer(true, new CubeInTransformer());
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                this.mPager.setPageTransformer(true, new CubeOutTransformer());
                break;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                this.mPager.setPageTransformer(true, new DepthPageTransformer());
                break;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                this.mPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
                break;
            case ConnectionResult.INVALID_ACCOUNT /*5*/:
                this.mPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
                break;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                this.mPager.setPageTransformer(true, new RotateDownTransformer());
                break;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                this.mPager.setPageTransformer(true, new RotateUpTransformer());
                break;
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                this.mPager.setPageTransformer(true, new StackTransformer());
                break;
            case ConnectionResult.SERVICE_INVALID /*9*/:
                this.mPager.setPageTransformer(true, new TabletTransformer());
                break;
            case ConnectionResult.DEVELOPER_ERROR /*10*/:
                this.mPager.setPageTransformer(true, new ZoomOutSlideTransformer());
                break;
        }
        Settings.setTransform(position, this.context);
    }

    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    private void loadAdd() {
        if (this.mInterstitialAd == null) {
            this.mInterstitialAd = new InterstitialAd(this);
            this.mInterstitialAd.setAdUnitId("ca-app-pub-8746828918162796/5439642269");
            this.mInterstitialAd.loadAd(new Builder().build());
            this.mInterstitialAd.setAdListener(new C07352());
        }
    }
}

