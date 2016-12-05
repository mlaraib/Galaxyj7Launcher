package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;

import org.json.JSONException;
import org.json.JSONObject;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/29/2016.
 */
public class FullScreenViewActivity extends Activity implements OnClickListener {
    private static final String TAG;
    private static final String TAG_ENTRY = "entry";
    private static final String TAG_IMG_HEIGHT = "height";
    private static final String TAG_IMG_URL = "url";
    private static final String TAG_IMG_WIDTH = "width";
    private static final String TAG_MEDIA_CONTENT = "media$content";
    private static final String TAG_MEDIA_GROUP = "media$group";
    public static final String TAG_SEL_IMAGE = "selectedImage";
    //SweetAlertDialog dialogSuccess;
    private ImageView fullImageView;
    private LinearLayout llDownloadWallpaper;
    private LinearLayout llSetWallpaper;
    InterstitialAd mInterstitialAd;
    //SweetAlertDialog pDialog;
    Dialog dialog;
    private ProgressBar pbLoader;
    private Wallpaper selectedPhoto;
    private Utils utils;

    private class SetWallpapar extends AsyncTask<Bitmap, Void, Boolean> {
        private SetWallpapar() {
        }

        protected void onPreExecute() {
            super.onPreExecute();
            FullScreenViewActivity.this.showDialog();
        }

        protected Boolean doInBackground(Bitmap... arg0) {
            boolean result;
            try {
                WallpaperManager.getInstance(FullScreenViewActivity.this).setBitmap(arg0[0]);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
            return Boolean.valueOf(result);
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result.booleanValue()) {
                FullScreenViewActivity.this.showSucess();
            } else {
                FullScreenViewActivity.this.showError();
            }
        }
    }

    /* renamed from: launcher.wallpapar.FullScreenViewActivity.1 */
    class C07441 extends AdListener {
        C07441() {
        }

        public void onAdLoaded() {
            if (FullScreenViewActivity.this.mInterstitialAd.isLoaded() && FullScreenViewActivity.this.mInterstitialAd != null) {
                FullScreenViewActivity.this.mInterstitialAd.show();
            }
            super.onAdLoaded();
        }
    }

    /* renamed from: launcher.wallpapar.FullScreenViewActivity.2 */
    class C07452 implements Listener<JSONObject> {

        /* renamed from: launcher.wallpapar.FullScreenViewActivity.2.1 */
        class C07841 implements ImageListener {
            final /* synthetic */ int val$height;
            final /* synthetic */ int val$width;

            C07841(int i, int i2) {
                this.val$width = i;
                this.val$height = i2;
            }

            public void onErrorResponse(VolleyError arg0) {
                Toast.makeText(FullScreenViewActivity.this.getApplicationContext(), FullScreenViewActivity.this.getString(R.string.msg_wall_fetch_error), 1).show();
            }

            public void onResponse(ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    FullScreenViewActivity.this.fullImageView.setImageBitmap(response.getBitmap());
                    FullScreenViewActivity.this.adjustImageAspect(this.val$width, this.val$height);
                    FullScreenViewActivity.this.pbLoader.setVisibility(View.VISIBLE);
                    FullScreenViewActivity.this.llSetWallpaper.setVisibility(View.VISIBLE);
                    FullScreenViewActivity.this.llDownloadWallpaper.setVisibility(View.VISIBLE);
                }
            }
        }

        C07452() {
        }

        public void onResponse(JSONObject response) {
            Log.d(FullScreenViewActivity.TAG, "Image full resolution json: " + response.toString());
            try {
                JSONObject mediaObj = (JSONObject) response.getJSONObject(FullScreenViewActivity.TAG_ENTRY).getJSONObject(FullScreenViewActivity.TAG_MEDIA_GROUP).getJSONArray(FullScreenViewActivity.TAG_MEDIA_CONTENT).get(0);
                String fullResolutionUrl = mediaObj.getString(FullScreenViewActivity.TAG_IMG_URL);
                int width = mediaObj.getInt(FullScreenViewActivity.TAG_IMG_WIDTH);
                int height = mediaObj.getInt(FullScreenViewActivity.TAG_IMG_HEIGHT);
                Log.d(FullScreenViewActivity.TAG, "Full resolution image. url: " + fullResolutionUrl + ", w: " + width + ", h: " + height);
                AppController.getInstance().getImageLoader().get(fullResolutionUrl, new C07841(width, height));
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(FullScreenViewActivity.this.getApplicationContext(), FullScreenViewActivity.this.getString(R.string.msg_unknown_error), 1).show();
            }
        }
    }

    /* renamed from: launcher.wallpapar.FullScreenViewActivity.3 */
    class C07463 implements ErrorListener {
        C07463() {
        }

        public void onErrorResponse(VolleyError error) {
            Log.e(FullScreenViewActivity.TAG, "Error: " + error.getMessage());
            Toast.makeText(FullScreenViewActivity.this.getApplicationContext(), FullScreenViewActivity.this.getString(R.string.msg_wall_fetch_error), 1).show();
        }
    }
//
//    /* renamed from: launcher.wallpapar.FullScreenViewActivity.4 */
//    class C07474 implements OnSweetClickListener {
//        C07474() {
//        }
//
//        public void onClick(SweetAlertDialog sweetAlertDialog) {
//            FullScreenViewActivity.this.dialogSuccess.dismiss();
//            FullScreenViewActivity.this.finish();
//        }
//    }
//
//    /* renamed from: launcher.wallpapar.FullScreenViewActivity.5 */
//    class C07485 implements OnSweetClickListener {
//        C07485() {
//        }
//
//        public void onClick(SweetAlertDialog sweetAlertDialog) {
//            FullScreenViewActivity.this.dialogSuccess.dismiss();
//            FullScreenViewActivity.this.finish();
//        }
//    }

    static {
        TAG = FullScreenViewActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (VERSION.SDK_INT >= 21) {
            window.addFlags(ExploreByTouchHelper.INVALID_ID);
            window.clearFlags(67108864);
            window.setStatusBarColor(getResources().getColor(17170445));
        }
        setContentView(R.layout.activity_full_screen_view);
        this.fullImageView = (ImageView) findViewById(R.id.imgFullscreen);
        this.llSetWallpaper = (LinearLayout) findViewById(R.id.llSetWallpaper);
        this.llDownloadWallpaper = (LinearLayout) findViewById(R.id.llDownloadWallpaper);
        this.pbLoader = (ProgressBar) findViewById(R.id.pbLoader);
        this.utils = new Utils(getApplicationContext());
        this.llSetWallpaper.setOnClickListener(this);
        this.llDownloadWallpaper.setOnClickListener(this);
        this.llSetWallpaper.getBackground().setAlpha(70);
        this.llDownloadWallpaper.getBackground().setAlpha(70);
        this.selectedPhoto = (Wallpaper) getIntent().getSerializableExtra(TAG_SEL_IMAGE);
        if (this.selectedPhoto != null) {
            fetchFullResolutionImage();
        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.msg_unknown_error), Toast.LENGTH_SHORT).show();
        }
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId("ca-app-pub-8746828918162796/6916375464");
        this.mInterstitialAd.loadAd(new Builder().build());
        this.mInterstitialAd.setAdListener(new C07441());
    }

    private void fetchFullResolutionImage() {
        String url = this.selectedPhoto.getPhotoJson();
        this.pbLoader.setVisibility(View.VISIBLE);
        this.llSetWallpaper.setVisibility(View.VISIBLE);
        this.llDownloadWallpaper.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(0, url, null, new C07452(), new C07463());
        AppController.getInstance().getRequestQueue().getCache().remove(url);
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    @SuppressLint({"NewApi"})
    private void adjustImageAspect(int bWidth, int bHeight) {
        LayoutParams params = new LayoutParams(-1, -1);
        if (bWidth != 0 && bHeight != 0) {
            int sHeight;
            if (VERSION.SDK_INT >= 13) {
                Display display = getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                sHeight = size.y;
            } else {
                sHeight = getWindowManager().getDefaultDisplay().getHeight();
            }
            int new_width = (int) Math.floor((((double) bWidth) * ((double) sHeight)) / ((double) bHeight));
            params.width = new_width;
            params.height = sHeight;
            Log.d(TAG, "Fullscreen image new dimensions: w = " + new_width + ", h = " + sHeight);
            this.fullImageView.setLayoutParams(params);
        }
    }

    public void onClick(View v) {
        Bitmap bitmap;
        switch (v.getId()) {
            case R.id.llSetWallpaper /*2131558502*/:
                bitmap = ((BitmapDrawable) this.fullImageView.getDrawable()).getBitmap();
                new SetWallpapar().execute(new Bitmap[]{bitmap});
            case R.id.llDownloadWallpaper /*2131558503*/:
                bitmap = ((BitmapDrawable) this.fullImageView.getDrawable()).getBitmap();
            default:
        }
    }

//    private void showDialog() {
//        this.pDialog = new SweetAlertDialog(this, 5);
//        this.pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
//        this.pDialog.setTitleText("Plz wait...");
//        this.pDialog.setCancelable(false);
//        this.pDialog.show();
//    }

    public void showDialog(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Please wait...");
        alertDialogBuilder
                .setCancelable(false);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }







//    private void showSucess() {
//        if (this.pDialog != null && this.pDialog.isShowing()) {
//            this.pDialog.dismiss();
//        }
//        this.dialogSuccess = new SweetAlertDialog(this, 2);
//        this.dialogSuccess.setTitleText("Background changed successfully...!!");
//        this.dialogSuccess.setConfirmClickListener(new C07474());
//        this.dialogSuccess.setCancelable(true);
//        this.dialogSuccess.show();
//    }






    public void showSucess(){

        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Background changed successfully...!!");
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }





//
//    private void showError() {
//        if (this.dialog != null && this.dialog.isShowing()) {
//            this.dialog.dismiss();
//        }
//        this.dialogSuccess = new SweetAlertDialog(this, 1);
//        this.dialogSuccess.setTitleText("Background changed successfully...!!");
//        this.dialogSuccess.setConfirmClickListener(new C07485());
//        this.dialogSuccess.setCancelable(true);
//        this.dialogSuccess.show();
//    }



    public void showError(){
        if (this.dialog != null && this.dialog.isShowing()) {
            this.dialog.dismiss();
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Background changed successfully...!!");
        alertDialogBuilder
                .setCancelable(true)
                .setPositiveButton("Confirm",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.dismiss();
                        finish();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }




}


