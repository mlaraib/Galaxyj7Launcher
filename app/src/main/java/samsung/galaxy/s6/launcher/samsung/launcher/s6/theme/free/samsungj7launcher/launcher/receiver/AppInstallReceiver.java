package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.receiver;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment.LauncherActivity;


/**
 * Created by iamla on 11/23/2016.
 */
public class AppInstallReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        LauncherActivity.receiveBroadCast(context, intent);
    }
}
