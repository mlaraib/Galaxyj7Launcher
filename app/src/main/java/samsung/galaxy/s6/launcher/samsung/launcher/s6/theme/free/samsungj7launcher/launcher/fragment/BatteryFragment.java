package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.android.gms.common.ConnectionResult;
import java.text.DecimalFormat;

import me.itangqi.waveloadingview.WaveLoadingView;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.BatteryCalc;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.MyPrefferences;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;


/**
 * Created by iamla on 11/29/2016.
 */
public class BatteryFragment extends Fragment {
    IntentFilter batteryFilter;
    TextView batteryPercentLabel;
    TextView batteryPercentValue;
    BroadcastReceiver batteryReceiver;
    IntentFilter filter;
    TextView healthLabel;
    TextView healthValue;
    int levelBattery;
    WaveLoadingView mWaveLoadingView;
    MyPrefferences myPrefferences;
    MyPrefferences mypref;
    BroadcastReceiver powerReceiver;
    TextView technologyLabel;
    TextView technologyValue;
    TextView tempValue;
    TextView voltLabel;
    TextView voltValue;

    /* renamed from: launcher.fragment.BatteryFragment.1 */
    class C04161 extends BroadcastReceiver {
        C04161() {
        }

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int level = intent.getIntExtra("level", 0);
            if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                BatteryFragment.this.batteryPercentLabel.setText("CHARGING TIME LEFT");
                String hour = BatteryFragment.this.getHour(level);
                BatteryFragment.this.batteryPercentValue.setText(hour + " H " + BatteryFragment.this.getMin(level) + " M");
                BatteryFragment.this.mWaveLoadingView.setCenterTitle(BatteryFragment.this.mypref.getBatteryLevel() + " %");
                if (level == 100) {
                    BatteryFragment.this.mWaveLoadingView.setCenterTitle("CHARGING COMPLETE");
                } else {
                    BatteryFragment.this.mWaveLoadingView.setCenterTitle("CHARGING");
                }
            } else if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                BatteryFragment.this.batteryPercentLabel.setText("BATTERY TIME LEFT");
                new MyPrefferences(context).setBatteryRemainingTime((new MyPrefferences(context).getResourceTime() + new MyPrefferences(context).getModeValue()) + new BatteryCalc(context).getBatteryRemainingTime(BatteryFragment.this.myPrefferences.getBatteryLevel()));
                BatteryFragment.this.getHoursAndMin(BatteryFragment.this.myPrefferences.getBatteryRemainingTime());
                BatteryFragment.this.mWaveLoadingView.setCenterTitle(BatteryFragment.this.levelBattery + " %");
            }
        }
    }

    /* renamed from: launcher.fragment.BatteryFragment.2 */
    class C04172 extends BroadcastReceiver {
        C04172() {
        }

        public void onReceive(Context context, Intent intent) {
            int health = intent.getIntExtra("health", 0);
            BatteryFragment.this.levelBattery = intent.getIntExtra("level", 0);
            BatteryFragment.this.mWaveLoadingView.setProgressValue(BatteryFragment.this.levelBattery);
            String healthResult = null;
            switch (health) {
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                    healthResult = "GOOD";
                    break;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    healthResult = "OVERHEAT";
                    break;
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                    healthResult = "DEAD";
                    break;
                case ConnectionResult.INVALID_ACCOUNT /*5*/:
                    healthResult = "OVER VOLTAGE";
                    break;
                case ConnectionResult.NETWORK_ERROR /*7*/:
                    healthResult = "COLD";
                    break;
            }
            switch (intent.getIntExtra("plugged", 0)) {
                case ConnectionResult.SERVICE_MISSING /*1*/:
                case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                    BatteryFragment.this.batteryPercentLabel.setText("CHARGING TIME LEFT");
                    String hour = BatteryFragment.this.getHour(BatteryFragment.this.levelBattery);
                    BatteryFragment.this.batteryPercentValue.setText(hour + " H " + BatteryFragment.this.getMin(BatteryFragment.this.levelBattery) + " M");
                    BatteryFragment.this.mWaveLoadingView.setCenterTitle(BatteryFragment.this.levelBattery + " %");
                    if (BatteryFragment.this.levelBattery != 100) {
                        BatteryFragment.this.mWaveLoadingView.setCenterTitle("CHARGING");
                        break;
                    } else {
                        BatteryFragment.this.mWaveLoadingView.setCenterTitle("CHARGING COMPLETE");
                        break;
                    }
                default:
                    BatteryFragment.this.batteryPercentLabel.setText("BATTERY TIME");
                    new MyPrefferences(context).setBatteryRemainingTime((new MyPrefferences(context).getResourceTime() + new MyPrefferences(context).getModeValue()) + new BatteryCalc(context).getBatteryRemainingTime(BatteryFragment.this.levelBattery));
                    BatteryFragment.this.getHoursAndMin(BatteryFragment.this.myPrefferences.getBatteryRemainingTime());
                    BatteryFragment.this.mWaveLoadingView.setCenterTitle(BatteryFragment.this.levelBattery + " %");
                    break;
            }
            String technology = intent.getExtras().getString("technology");
            float temp = ((float) intent.getIntExtra("temperature", 0)) / 10.0f;
            float tempInFahrenheit = ((9.0f * temp) / 5.0f) + 32.0f;
            float voltage = ((float) intent.getIntExtra("voltage", 0)) / 1000.0f;
            BatteryFragment.this.healthValue.setText(healthResult);
            BatteryFragment.this.tempValue.setText(String.valueOf(temp) + "\u00b0" + "C" + " / " + new DecimalFormat("0.00").format((double) tempInFahrenheit) + "\u00b0" + "F");
            BatteryFragment.this.voltValue.setText(voltage + " V");
            BatteryFragment.this.technologyValue.setText(technology);
        }
    }

    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(this.powerReceiver);
        getActivity().unregisterReceiver(this.batteryReceiver);
    }

    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(this.batteryReceiver, this.batteryFilter);
        getActivity().registerReceiver(this.powerReceiver, this.filter);
        Log.e("on Resume", getClass().getName());
        this.mWaveLoadingView.setWaveColor(Settings.getFontColor(getContext()));
        this.mWaveLoadingView.setBorderColor(Settings.getFontColor(getContext()));
    }

    private void getHoursAndMin(int time) {
        long hours = (long) Math.floor((double) (time / 60));
        long minutes = (long) (time % 60);
        DecimalFormat formatter = new DecimalFormat("00");
        this.batteryPercentValue.setText(formatter.format(hours) + "h " + formatter.format(minutes) + "m Remaining");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_battery, container, false);
        this.myPrefferences = new MyPrefferences(getActivity());
        this.mWaveLoadingView = (WaveLoadingView) view.findViewById(R.id.waveLoadingView);
        this.mWaveLoadingView.setShapeType(WaveLoadingView.ShapeType.CIRCLE);
        this.mWaveLoadingView.setBorderWidth(10.0f);
        this.mWaveLoadingView.setAmplitudeRatio(60);
        initTextViews(view);
        this.mypref = new MyPrefferences(getActivity());
        this.filter = new IntentFilter();
        this.filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        this.filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        this.powerReceiver = new C04161();
        this.batteryFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
        this.batteryReceiver = new C04172();
        return view;
    }

    private String getHour(int level) {
        return new DecimalFormat("00").format((long) Math.floor((double) (((int) (((float) (100 - level)) * 2.2f)) / 60)));
    }

    private String getMin(int level) {
        return new DecimalFormat("00").format(((long) ((int) (((float) (100 - level)) * 2.2f))) % 60);
    }

    private void initTextViews(View view) {
        this.healthLabel = (TextView) view.findViewById(R.id.healthLabel);
        this.healthLabel.setTypeface(Utils.getFont(getActivity()));
        this.voltLabel = (TextView) view.findViewById(R.id.voltageLabel);
        this.voltLabel.setTypeface(Utils.getFont(getActivity()));
        this.technologyLabel = (TextView) view.findViewById(R.id.technologyLabel);
        this.technologyLabel.setTypeface(Utils.getFont(getActivity()));
        this.healthValue = (TextView) view.findViewById(R.id.healthValue);
        this.healthValue.setTypeface(Utils.getFont(getActivity()));
        this.tempValue = (TextView) view.findViewById(R.id.tempratureValue);
        this.tempValue.setTypeface(Utils.getFont(getActivity()));
        this.voltValue = (TextView) view.findViewById(R.id.voltageValue);
        this.voltValue.setTypeface(Utils.getFont(getActivity()));
        this.technologyValue = (TextView) view.findViewById(R.id.technologyValue);
        this.technologyValue.setTypeface(Utils.getFont(getActivity()));
        this.batteryPercentLabel = (TextView) view.findViewById(R.id.batteryPercentLabel);
        this.batteryPercentLabel.setTypeface(Utils.getFont(getActivity()));
        this.batteryPercentValue = (TextView) view.findViewById(R.id.batteryPercentValues);
        this.batteryPercentValue.setTypeface(Utils.getFont(getActivity()));
    }
}

