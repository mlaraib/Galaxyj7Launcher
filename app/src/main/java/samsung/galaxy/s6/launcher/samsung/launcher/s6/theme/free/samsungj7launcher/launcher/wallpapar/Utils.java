package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;


import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/29/2016.
 */
public class Utils {
    private String TAG;
    private Context context;
    private PrefManager pref;

    /* renamed from: launcher.wallpapar.Utils.1 */
    class C04261 implements Runnable {
        C04261() {
        }

        public void run() {
            Toast.makeText(Utils.this.context, Utils.this.context.getString(R.string.toast_saved).replace("#", "\"" + Utils.this.pref.getGalleryName() + "\""), Toast.LENGTH_SHORT).show();
        }
    }

    /* renamed from: launcher.wallpapar.Utils.2 */
    class C04272 implements Runnable {
        C04272() {
        }

        public void run() {
            Toast.makeText(Utils.this.context, Utils.this.context.getString(R.string.toast_saved_failed), Toast.LENGTH_SHORT).show();
        }
    }

    public Utils(Context context) {
        this.TAG = Utils.class.getSimpleName();
        this.context = context;
        this.pref = new PrefManager(this.context);
    }





    public int getScreenWidth() {
        Display display = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        try {
            if (VERSION.SDK_INT >= 13) {
                display.getSize(point);
            }
        } catch (NoSuchMethodError e) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
        return point.x;
    }

    public String saveImageToGallary(Bitmap bitmap) {
        File myDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), this.pref.getGalleryName());
        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        if (true) {
            String fname = "Wallpaper-" + new Random().nextInt(10000) + ".jpg";
            File file = new File(myDir, fname);
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream out = new FileOutputStream(file);
                bitmap.compress(CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
                ((Activity) this.context).runOnUiThread(new C04261());
                Log.d(this.TAG, "Wallpaper saved to: " + file.getAbsolutePath());
                return myDir + "/" + fname;
            } catch (Exception e) {
                e.printStackTrace();
                ((Activity) this.context).runOnUiThread(new C04272());
            }
        }
        return null;
    }

    public String saveToPrivateDirectory(Bitmap bitmapImage, String fileName) {
        FileOutputStream fos;
        Exception e;
        File directory = new File(Environment.getExternalStorageDirectory().toString(), "/Android/data/" + this.context.getPackageName() + "/temp");
        boolean success = true;
        if (!directory.exists()) {
            success = directory.mkdirs();
        }
        if (success) {
            try {
                fos = new FileOutputStream(new File(directory, fileName));
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return null;
            }
            try {
                bitmapImage.compress(CompressFormat.PNG, 100, fos);
                fos.close();
                Log.d(this.TAG, "Wallpaper saved to: " + directory.getAbsolutePath() + "/" + fileName);
                return directory.getAbsolutePath() + "/" + fileName;
            } catch (Exception e3) {
                e = e3;
                FileOutputStream fileOutputStream = fos;
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public void setAsWallpaper(Bitmap bitmap, FullScreenViewActivity fullScreenViewActivity) {
        try {
            WallpaperManager.getInstance(this.context).setBitmap(bitmap);
            fullScreenViewActivity.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap loadImageFromStorage(String path, boolean defaultImage, int wallpapar) {
        Options options = new Options();
        int width_tmp = options.outWidth;
        int height_tmp = options.outHeight;
        int scale = 1;
        while (width_tmp / 2 >= 48 && height_tmp / 2 >= 80) {
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }
        options.inSampleSize = scale;
        if (defaultImage) {
            return BitmapFactory.decodeResource(this.context.getResources(), wallpapar, options);
        }
        return BitmapFactory.decodeFile(path, options);
    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immage = image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immage.compress(CompressFormat.PNG, 100, baos);
        return Base64.encodeToString(baos.toByteArray(), 0);
    }
}

