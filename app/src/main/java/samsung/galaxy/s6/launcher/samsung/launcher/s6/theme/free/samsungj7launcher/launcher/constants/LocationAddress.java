package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by iamla on 11/23/2016.
 */
public class LocationAddress {
    private static final String TAG = "LocationAddress";

    /* renamed from: launcher.constants.LocationAddress.1 */
    static class C03991 extends Thread {
        final /* synthetic */ Context val$context;
        final /* synthetic */ Handler val$handler;
        final /* synthetic */ double val$latitude;
        final /* synthetic */ double val$longitude;

        C03991(Context context, double d, double d2, Handler handler) {
            this.val$context = context;
            this.val$latitude = d;
            this.val$longitude = d2;
            this.val$handler = handler;
        }

        public void run() {
            try {
                List<Address> addressList = new Geocoder(this.val$context, Locale.getDefault()).getFromLocation(this.val$latitude, this.val$longitude, 1);
                if (addressList != null && addressList.size() > 0) {
                    Address address = (Address) addressList.get(0);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                        sb.append(address.getAddressLine(i)).append("\n");
                    }
                    String result = sb.toString();
                    Message message = Message.obtain();
                    message.setTarget(this.val$handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        message.setData(bundle);
                        message.sendToTarget();
                    }
                }
            } catch (IOException e) {
                Log.e(LocationAddress.TAG, "Unable connect to Geocoder", e);
            }
        }
    }

    public static void getAddressFromLocation(double latitude, double longitude, Context context, Handler handler) {
        new C03991(context, latitude, longitude, handler).start();
    }
}

