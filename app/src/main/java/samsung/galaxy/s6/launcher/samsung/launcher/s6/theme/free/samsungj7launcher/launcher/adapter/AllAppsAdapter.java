package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.io.File;
import java.util.ArrayList;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView.ImageBitmap;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;

/**
 * Created by iamla on 11/23/2016.
 */
public class AllAppsAdapter extends BaseAdapter {
    ArrayList<AppDetail> allAppsList;
    Context context;
    private ViewHolder holder;
    boolean mode;
    boolean reset;

    private class ViewHolder {
        ImageView imageViewApp;
        ImageView imageViewDelete;

        private ViewHolder() {
        }
    }

    public class createIcon extends AsyncTask<Void, Void, Bitmap> {
        ImageView imageView;
        int index;

        public createIcon(int i, ImageView imageView) {
            this.index = i;
            this.imageView = imageView;
        }

        protected Bitmap doInBackground(Void... params) {
            return new ImageBitmap().getThumb(AllAppsAdapter.this.context, String.valueOf(((AppDetail) AllAppsAdapter.this.allAppsList.get(this.index)).appName), ((AppDetail) AllAppsAdapter.this.allAppsList.get(this.index)).icon, AllAppsAdapter.this.mode, ((AppDetail) AllAppsAdapter.this.allAppsList.get(this.index)).isSystemPackage);
        }

        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                this.imageView.setImageBitmap(bitmap);
                ((AppDetail) AllAppsAdapter.this.allAppsList.get(this.index)).appBitmap = bitmap;
            }
        }
    }

    public AllAppsAdapter(Context mContext, ArrayList<AppDetail> allAppsList, boolean mode, boolean reset) {
        this.context = mContext;
        this.allAppsList = allAppsList;
        this.mode = mode;
        this.reset = reset;
    }

    public int getCount() {
        return this.allAppsList.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }

    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService("layout_inflater");
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_app, null);
            this.holder = new ViewHolder();
            this.holder.imageViewApp = (ImageView) convertView.findViewById(R.id.imageViewApp);
            this.holder.imageViewDelete = (ImageView) convertView.findViewById(R.id.imageViewDelete);
            convertView.setTag(this.holder);
        } else {
            this.holder = (ViewHolder) convertView.getTag();
        }
        if (this.reset) {
            ((AppDetail) this.allAppsList.get(i)).appBitmap = null;
            new ImageBitmap().createIcon(this.context, String.valueOf(((AppDetail) this.allAppsList.get(i)).appName), ((AppDetail) this.allAppsList.get(i)).icon);
        }
        String appName = String.valueOf(((AppDetail) this.allAppsList.get(i)).appName);
        if (appName.equalsIgnoreCase("Calendar") || appName.equalsIgnoreCase("S Planner")) {
            new createIcon(i, this.holder.imageViewApp).execute(new Void[0]);
        } else {
            if (appName.length() > 11) {
                appName = appName.substring(0, 9) + "..";
            }
            appName = appName.trim().replaceAll(" ", BuildConfig.FLAVOR);
            File file = new File(this.context.getExternalFilesDir("Icons") + File.separator + appName + ".PNG");
            if (file.exists()) {
                this.holder.imageViewApp.setImageDrawable(Drawable.createFromPath(file.getAbsolutePath()));
            } else if (((AppDetail) this.allAppsList.get(i)).appBitmap != null) {
                this.holder.imageViewApp.setImageBitmap(((AppDetail) this.allAppsList.get(i)).appBitmap);
            } else {
                Log.e("file not found", BuildConfig.FLAVOR + appName);
                new createIcon(i, this.holder.imageViewApp).execute(new Void[0]);
            }
        }
        if (Settings.getIsEditMode(this.context)) {
            this.holder.imageViewDelete.setVisibility(View.GONE);
        } else {
            this.holder.imageViewDelete.setVisibility(View.VISIBLE);
        }
        return convertView;
    }
}

