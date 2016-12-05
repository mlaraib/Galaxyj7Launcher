package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customDialog;

import android.graphics.Point;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;
import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.adapter.AllAppsAdapter;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;

/**
 * Created by iamla on 11/24/2016.
 */
public class DropDownMenuHelper {

    /* renamed from: launcher.customDialog.DropDownMenuHelper.1 */
    static class C04001 implements OnItemClickListener {
        final /* synthetic */ PopupWindow val$popupWindow;

        C04001(PopupWindow popupWindow) {
            this.val$popupWindow = popupWindow;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            this.val$popupWindow.dismiss();
        }
    }

    private DropDownMenuHelper() {
    }

    public static void showPopUpWindow(FragmentActivity activity, View view, ArrayList<AppDetail> googleApps) {
        View popUpView = LayoutInflater.from(activity).inflate(R.layout.popup_window, null);
        PopupWindow popupWindow = new PopupWindow(popUpView, -1, -1, true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchable(true);
        int[] location = new int[2];
        Display display = ((WindowManager) activity.getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int screenHeight = size.y;
        view.getLocationOnScreen(location);
        int total = (location[1] + popUpView.getMeasuredHeight()) + 80;
        popupWindow.showAsDropDown(view, 0, -(popUpView.getMeasuredHeight() * 20));
        GridView gridView = (GridView) popUpView.findViewById(R.id.vgv);
        gridView.setAdapter(new AllAppsAdapter(activity, googleApps, false, false));
        gridView.setOnItemClickListener(new C04001(popupWindow));
    }
}

