package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.transition;

/**
 * Created by iamla on 11/23/2016.
 */
import android.content.Context;
import android.support.v4.view.ViewPager;
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
import com.google.android.gms.common.ConnectionResult;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;


public class Transition {
    public static void setTransform(Context context, ViewPager mPager) {
        switch (Settings.getTransform(context)) {
            case ConnectionResult.SUCCESS /*0*/:
                mPager.setPageTransformer(true, new AccordionTransformer());
            case ConnectionResult.SERVICE_MISSING /*1*/:
                mPager.setPageTransformer(true, new CubeInTransformer());
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                mPager.setPageTransformer(true, new CubeOutTransformer());
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                mPager.setPageTransformer(true, new DepthPageTransformer());
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                mPager.setPageTransformer(true, new ForegroundToBackgroundTransformer());
            case ConnectionResult.INVALID_ACCOUNT /*5*/:
                mPager.setPageTransformer(true, new BackgroundToForegroundTransformer());
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                mPager.setPageTransformer(true, new RotateDownTransformer());
            case ConnectionResult.NETWORK_ERROR /*7*/:
                mPager.setPageTransformer(true, new RotateUpTransformer());
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                mPager.setPageTransformer(true, new StackTransformer());
            case ConnectionResult.SERVICE_INVALID /*9*/:
                mPager.setPageTransformer(true, new TabletTransformer());
            case ConnectionResult.DEVELOPER_ERROR /*10*/:
                mPager.setPageTransformer(true, new ZoomOutSlideTransformer());
            default:
        }
    }
}

