package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;


import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;


/**
 * Created by iamla on 11/29/2016.
 */
public class BackgroundSelection extends Activity {
    private static final String TAG;
    private static final String TAG_ENTRY = "entry";
    private static final String TAG_FEED = "feed";
    private static final String TAG_ID = "id";
    private static final String TAG_IMG_HEIGHT = "height";
    private static final String TAG_IMG_URL = "url";
    private static final String TAG_IMG_WIDTH = "width";
    private static final String TAG_MEDIA_CONTENT = "media$content";
    private static final String TAG_MEDIA_GROUP = "media$group";
    private static final String TAG_T = "$t";
    private static final String bundleAlbumId = "albumId";
    private GridViewAdapter adapter;
    private int columnWidth;
    private GridView gridView;
    private ProgressBar pbLoader;
    private List<Wallpaper> photosList;
    private PrefManager pref;
    private String selectedAlbumId;
    private Utils utils;

    /* renamed from: launcher.wallpapar.BackgroundSelection.3 */
    class C04243 implements OnItemClickListener {
        C04243() {
        }

        public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
            Intent i = new Intent(BackgroundSelection.this, FullScreenViewActivity.class);
            i.putExtra(FullScreenViewActivity.TAG_SEL_IMAGE, (Wallpaper) BackgroundSelection.this.photosList.get(position));
            BackgroundSelection.this.startActivity(i);
        }
    }

    /* renamed from: launcher.wallpapar.BackgroundSelection.1 */
    class C07421 implements Listener<JSONObject> {
        C07421() {
        }

        public void onResponse(JSONObject response) {
            try {
                JSONArray entry = response.getJSONObject(BackgroundSelection.TAG_FEED).getJSONArray(BackgroundSelection.TAG_ENTRY);
                for (int i = 0; i < entry.length(); i++) {
                    JSONObject photoObj = (JSONObject) entry.get(i);
                    JSONArray mediacontentArry = photoObj.getJSONObject(BackgroundSelection.TAG_MEDIA_GROUP).getJSONArray(BackgroundSelection.TAG_MEDIA_CONTENT);
                    if (mediacontentArry.length() > 0) {
                        JSONObject mediaObj = (JSONObject) mediacontentArry.get(0);
                        BackgroundSelection.this.photosList.add(new Wallpaper(photoObj.getJSONObject(BackgroundSelection.TAG_ID).getString(BackgroundSelection.TAG_T) + "&imgmax=d", mediaObj.getString(BackgroundSelection.TAG_IMG_URL), mediaObj.getInt(BackgroundSelection.TAG_IMG_WIDTH), mediaObj.getInt(BackgroundSelection.TAG_IMG_HEIGHT)));
                    }
                }
                BackgroundSelection.this.adapter.notifyDataSetChanged();
                BackgroundSelection.this.pbLoader.setVisibility(View.GONE);
                BackgroundSelection.this.gridView.setVisibility(View.VISIBLE);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(BackgroundSelection.this, BackgroundSelection.this.getString(R.string.msg_unknown_error), 1).show();
            }
        }
    }

    /* renamed from: launcher.wallpapar.BackgroundSelection.2 */
    class C07432 implements ErrorListener {
        C07432() {
        }

        public void onErrorResponse(VolleyError error) {
            Log.e(BackgroundSelection.TAG, "Error: " + error.getMessage());
            Toast.makeText(BackgroundSelection.this, BackgroundSelection.this.getString(R.string.msg_wall_fetch_error), 1).show();
        }
    }

    public BackgroundSelection() {
        this.selectedAlbumId = "6262233240279585841";
    }

    static {
        TAG = BackgroundSelection.class.getSimpleName();
    }

    protected void onCreate(Bundle savedInstanceState) {
        String url;
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        if (VERSION.SDK_INT >= 21) {
            window.addFlags(ExploreByTouchHelper.INVALID_ID);
            window.clearFlags(67108864);
            window.setStatusBarColor(getResources().getColor(17170445));
        }
        setContentView(R.layout.activity_background_selection);
        this.photosList = new ArrayList();
        this.pref = new PrefManager(this);
        if (this.selectedAlbumId == null) {
            url = AppConst.URL_RECENTLY_ADDED.replace("_PICASA_USER_", this.pref.getGoogleUserName());
        } else {
            url = AppConst.URL_ALBUM_PHOTOS.replace("_PICASA_USER_", this.pref.getGoogleUserName()).replace("_ALBUM_ID_", this.selectedAlbumId);
        }
        this.gridView = (GridView) findViewById(R.id.grid_view);
        this.gridView.setVisibility(View.VISIBLE);
        this.pbLoader = (ProgressBar) findViewById(R.id.pbLoader);
        this.pbLoader.setVisibility(View.VISIBLE);
        this.utils = new Utils(this);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(0, url, null, new C07421(), new C07432());
        AppController.getInstance().getRequestQueue().getCache().remove(url);
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        InitilizeGridLayout();
        this.adapter = new GridViewAdapter(this, this.photosList, this.columnWidth);
        this.gridView.setAdapter(this.adapter);
        this.gridView.setOnItemClickListener(new C04243());
    }

    private void InitilizeGridLayout() {
        float padding = TypedValue.applyDimension(1, 4.0f, getResources().getDisplayMetrics());
        this.columnWidth = (int) ((((float) this.utils.getScreenWidth()) - (((float) (this.pref.getNoOfGridColumns() + 1)) * padding)) / ((float) this.pref.getNoOfGridColumns()));
        this.gridView.setNumColumns(this.pref.getNoOfGridColumns());
        this.gridView.setColumnWidth(this.columnWidth);
        this.gridView.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
        this.gridView.setPadding((int) padding, (int) padding, (int) padding, (int) padding);
        this.gridView.setHorizontalSpacing((int) padding);
        this.gridView.setVerticalSpacing((int) padding);
    }
}

