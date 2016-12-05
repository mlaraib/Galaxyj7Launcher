package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.MenuStructure;

/**
 * Created by iamla on 11/21/2016.
 */
public class NavigationDrawerAdapter extends BaseAdapter {
    Context context;
    List<MenuStructure> itemList;

    public NavigationDrawerAdapter(Context context, List<MenuStructure> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public int getCount() {
        return this.itemList.size();
    }

    public Object getItem(int position) {
        return this.itemList.get(position);
    }

    public long getItemId(int position) {
        return (long) ((MenuStructure) this.itemList.get(position)).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.menu_structure, null);
        TextView menuItem = (TextView) convertView.findViewById(R.id.menuName);
        ImageView menuImage = (ImageView) convertView.findViewById(R.id.menuImg);
        MenuStructure item = (MenuStructure) this.itemList.get(position);
        menuItem.setText(item.getCategoryName());
        menuItem.setTypeface(Utils.getFont(this.context));
        menuImage.setImageResource(item.getCategoryImage());
        if (position == 6 || position == 0) {
            convertView.setPadding(0, 0, 0, 0);
            menuImage.setVisibility(View.VISIBLE);
            menuItem.setGravity(17);
            menuItem.setTextSize(18.0f);
            menuItem.setPadding(0, 0, 0, 0);
            convertView.setBackgroundColor(Color.parseColor("#000000"));
        }
        return convertView;
    }
}
