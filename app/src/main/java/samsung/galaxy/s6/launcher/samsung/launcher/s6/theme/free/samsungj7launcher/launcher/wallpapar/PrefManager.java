package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/**
 * Created by iamla on 11/29/2016.
 */
public class PrefManager {
    private static final String KEY_ALBUMS = "albums";
    private static final String KEY_GALLERY_NAME = "gallery_name";
    private static final String KEY_GOOGLE_USERNAME = "google_username";
    private static final String KEY_NO_OF_COLUMNS = "no_of_columns";
    private static final String PREF_NAME = "AwesomeWallpapers";
    private static final String TAG;
    int PRIVATE_MODE;
    Context _context;
    Editor editor;
    SharedPreferences pref;

    /* renamed from: launcher.wallpapar.PrefManager.1 */
    class C04251 implements Comparator<Category> {
        C04251() {
        }

        public int compare(Category a1, Category a2) {
            return a1.getTitle().compareToIgnoreCase(a2.getTitle());
        }
    }

    public class CustomComparator implements Comparator<Category> {
        public int compare(Category c1, Category c2) {
            return c1.getTitle().compareTo(c2.getTitle());
        }
    }

    static {
        TAG = PrefManager.class.getSimpleName();
    }

    public PrefManager(Context context) {
        this.PRIVATE_MODE = 0;
        this._context = context;
        this.pref = this._context.getSharedPreferences(PREF_NAME, this.PRIVATE_MODE);
    }

    public void setGoogleUsername(String googleUsername) {
        this.editor = this.pref.edit();
        this.editor.putString(KEY_GOOGLE_USERNAME, googleUsername);
        this.editor.commit();
    }

    public String getGoogleUserName() {
        return this.pref.getString(KEY_GOOGLE_USERNAME, AppConst.PICASA_USER);
    }

    public void setNoOfGridColumns(int columns) {
        this.editor = this.pref.edit();
        this.editor.putInt(KEY_NO_OF_COLUMNS, columns);
        this.editor.commit();
    }

    public int getNoOfGridColumns() {
        return this.pref.getInt(KEY_NO_OF_COLUMNS, 3);
    }

    public void setGalleryName(String galleryName) {
        this.editor = this.pref.edit();
        this.editor.putString(KEY_GALLERY_NAME, galleryName);
        this.editor.commit();
    }

    public String getGalleryName() {
        return this.pref.getString(KEY_GALLERY_NAME, AppConst.SDCARD_DIR_NAME);
    }

    public void storeCategories(List<Category> albums) {
        this.editor = this.pref.edit();
        Gson gson = new Gson();
        Log.d(TAG, "Albums: " + gson.toJson((Object) albums));
        this.editor.putString(KEY_ALBUMS, gson.toJson((Object) albums));
        this.editor.commit();
    }

    public List<Category> getCategories() {
        List<Category> albums = new ArrayList();
        if (!this.pref.contains(KEY_ALBUMS)) {
            return null;
        }
        List<Category> albums2 = new ArrayList(Arrays.asList((Category[]) new Gson().fromJson(this.pref.getString(KEY_ALBUMS, null), Category[].class)));
        List<Category> allAlbums = albums2;
        Collections.sort(allAlbums, new C04251());
        albums = albums2;
        return allAlbums;
    }
}

