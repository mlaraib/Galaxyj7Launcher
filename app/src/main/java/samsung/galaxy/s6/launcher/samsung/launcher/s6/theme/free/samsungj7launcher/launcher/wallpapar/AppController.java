package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;

import android.app.Application;
import com.android.volley.Request;
import android.text.TextUtils;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by iamla on 11/29/2016.
 */
public class AppController extends Application {
    public static final String TAG;
    private static AppController mInstance;
    private ImageLoader mImageLoader;
    LruBitmapCache mLruBitmapCache;
    private RequestQueue mRequestQueue;
    private PrefManager pref;

    static {
        TAG = AppController.class.getSimpleName();
    }

    public void onCreate() {
        super.onCreate();
        mInstance = this;
        this.pref = new PrefManager(this);
    }

    public static synchronized AppController getInstance() {
        AppController appController;
        synchronized (AppController.class) {
            appController = mInstance;
        }
        return appController;
    }

    public PrefManager getPrefManger() {
        if (this.pref == null) {
            this.pref = new PrefManager(this);
        }
        return this.pref;
    }

    public RequestQueue getRequestQueue() {
        if (this.mRequestQueue == null) {
            this.mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return this.mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (this.mImageLoader == null) {
            getLruBitmapCache();
            this.mImageLoader = new ImageLoader(this.mRequestQueue, this.mLruBitmapCache);
        }
        return this.mImageLoader;
    }

    public LruBitmapCache getLruBitmapCache() {
        if (this.mLruBitmapCache == null) {
            this.mLruBitmapCache = new LruBitmapCache();
        }
        return this.mLruBitmapCache;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (this.mRequestQueue != null) {
            this.mRequestQueue.cancelAll(tag);
        }
    }
}

