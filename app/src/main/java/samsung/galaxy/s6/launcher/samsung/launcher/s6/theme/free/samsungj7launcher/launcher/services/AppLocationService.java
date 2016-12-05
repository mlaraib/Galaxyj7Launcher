package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.services;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;


/**
 * Created by iamla on 11/23/2016.
 */
public class AppLocationService extends Service implements LocationListener {
    private static final long MIN_DISTANCE_FOR_UPDATE = 10;
    private static final long MIN_TIME_FOR_UPDATE = 120000;
    Location location;
    protected LocationManager locationManager;

    public AppLocationService(Context context) {
        this.locationManager = (LocationManager) context.getSystemService("location");
    }

    public Location getLocation(String provider) {
        if (this.locationManager.isProviderEnabled(provider)) {
            this.locationManager.requestLocationUpdates(provider, MIN_TIME_FOR_UPDATE, 10.0f, this);
            if (this.locationManager != null) {
                this.location = this.locationManager.getLastKnownLocation(provider);
                return this.location;
            } else if (!(ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ContextCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0)) {
                return null;
            }
        }
        return null;
    }

    public void onLocationChanged(Location location) {
    }

    public void onProviderDisabled(String provider) {
    }

    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }
}
