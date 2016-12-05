package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import java.util.ArrayList;
import java.util.List;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/29/2016.
 */
public class GridViewAdapter extends BaseAdapter {
    private Activity _activity;
    ImageLoader imageLoader;
    private int imageWidth;
    private LayoutInflater inflater;
    private List<Wallpaper> wallpapersList;

    public GridViewAdapter(Activity activity, List<Wallpaper> wallpapersList, int imageWidth) {
        this.wallpapersList = new ArrayList();
        this.imageLoader = AppController.getInstance().getImageLoader();
        this._activity = activity;
        this.wallpapersList = wallpapersList;
        this.imageWidth = imageWidth;
    }

    public int getCount() {
        return this.wallpapersList.size();
    }

    public Object getItem(int position) {
        return this.wallpapersList.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (this.inflater == null) {
            this.inflater = (LayoutInflater) this._activity.getSystemService("layout_inflater");
        }
        if (convertView == null) {
            convertView = this.inflater.inflate(R.layout.grid_item_photo, null);
        }
        if (this.imageLoader == null) {
            this.imageLoader = AppController.getInstance().getImageLoader();
        }
        NetworkImageView thumbNail = (NetworkImageView) convertView.findViewById(R.id.thumbnail);
        Wallpaper p = (Wallpaper) this.wallpapersList.get(position);
        thumbNail.setScaleType(ScaleType.CENTER_CROP);
        thumbNail.setLayoutParams(new LayoutParams(this.imageWidth, (int) (((double) this.imageWidth) * 1.6d)));
        thumbNail.setImageUrl(p.getUrl(), this.imageLoader);
        return convertView;
    }
}

