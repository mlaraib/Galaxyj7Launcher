package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher;

import android.content.res.Resources;
import android.os.Build;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


public class Splash extends AppCompatActivity {

    private static final int REQUEST_CODE_CONTACTS = 23;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Window window = getWindow();
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                window.addFlags(ExploreByTouchHelper.INVALID_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (Build.VERSION.SDK_INT >= 19) {
                window.clearFlags(67108864);
            }
        } catch (Resources.NotFoundException e2) {
            e2.printStackTrace();
        }
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
            }
        } catch (Resources.NotFoundException e22) {
            e22.printStackTrace();
        }

        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_CONTACTS /*23*/:
                if (grantResults.length > 0 && grantResults[0] == 0 && grantResults[1] == 0 && grantResults[2] == 0) {
                    //startActivity(new Intent(this, LauncherActivity.class));
                    finish();
                    return;
                }
                Toast.makeText(this, "Permission required", Toast.LENGTH_SHORT).show();
                finish();
            default:
        }
    }
}
