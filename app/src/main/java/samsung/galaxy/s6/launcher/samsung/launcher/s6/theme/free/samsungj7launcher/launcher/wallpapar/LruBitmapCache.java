package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;


import android.graphics.Bitmap;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.util.LruCache;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.android.volley.toolbox.ImageLoader.ImageCache;


/**
 * Created by iamla on 11/29/2016.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageCache {
    public static int getDefaultLruCacheSize() {
        return ((int) (Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID)) / 8;
    }

    public LruBitmapCache() {
        this(getDefaultLruCacheSize());
    }

    public LruBitmapCache(int sizeInKiloBytes) {
        super(sizeInKiloBytes);
    }

    protected int sizeOf(String key, Bitmap value) {
        return (value.getRowBytes() * value.getHeight()) / AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT;
    }

    public Bitmap getBitmap(String url) {
        return (Bitmap) get(url);
    }

    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }
}
