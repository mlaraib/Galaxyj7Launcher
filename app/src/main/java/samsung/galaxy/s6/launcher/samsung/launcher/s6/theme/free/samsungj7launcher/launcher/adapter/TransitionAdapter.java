package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.adapter;

/**
 * Created by iamla on 11/23/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView.ImageBitmap;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;


public class TransitionAdapter extends BaseAdapter {
    ArrayList<AppDetail> allAppsList;
    Context context;
    private ViewHolder holder;

    private class ViewHolder {
        ImageView imageViewApp;

        private ViewHolder() {
        }
    }

    public TransitionAdapter(Context mContext, ArrayList<AppDetail> allAppsList) {
        this.context = mContext;
        this.allAppsList = allAppsList;
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
            convertView.setTag(this.holder);
        } else {
            this.holder = (ViewHolder) convertView.getTag();
        }
        if (((AppDetail) this.allAppsList.get(i)).appBitmap != null) {
            this.holder.imageViewApp.setImageBitmap(new ImageBitmap().getThumb(this.context, String.valueOf(((AppDetail) this.allAppsList.get(i)).appName), ((AppDetail) this.allAppsList.get(i)).icon, Settings.getTheme(this.context)));
        } else {
            ((AppDetail) this.allAppsList.get(i)).appBitmap = new ImageBitmap().getThumb(this.context, String.valueOf(((AppDetail) this.allAppsList.get(i)).appName), ((AppDetail) this.allAppsList.get(i)).icon, Settings.getTheme(this.context));
            this.holder.imageViewApp.setImageBitmap(((AppDetail) this.allAppsList.get(i)).appBitmap);
        }
        return convertView;
    }
}

