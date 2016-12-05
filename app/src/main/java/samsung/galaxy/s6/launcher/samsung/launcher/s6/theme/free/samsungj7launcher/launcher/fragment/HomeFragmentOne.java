package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment;


import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView.AnalogClock;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.features.FontColorSelection;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.features.FontSelection;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.features.ThemeSelection;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.features.TransitionSelection;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.wallpapar.BackgroundSelection;


/**
 * Created by iamla on 11/29/2016.
 */
public class HomeFragmentOne extends Fragment implements OnClickListener {
    Intent RecIntent;
    int brightnessMode;
    private AnalogClock clockView;
    //SweetAlertDialog dialogSuccess;
    Intent intent;
    ImageView ivBluetooth;
    ImageView ivBrightness;
    ImageView ivFontColorSelection;
    ImageView ivFontSelection;
    ImageView ivMore;
    ImageView ivMoreApps;
    ImageView ivRateApp;
    ImageView ivShareApp;
    ImageView ivSound;
    ImageView ivTheme;
    ImageView ivTransitionSelection;
    ImageView ivWallpaper;
    ImageView ivWifi;
    private BroadcastReceiver mBatInfoReceiver;
    AudioManager myAudioManager;
    public ViewGroup rootView;
    TextView tvFive;
    TextView tvFour;
    TextView tvMoreApps;
    TextView tvOne;
    TextView tvRateApp;
    TextView tvShareApp;
    TextView tvThree;
    TextView tvTwo;

    /* renamed from: launcher.fragment.HomeFragmentOne.1 */
    class C04181 extends BroadcastReceiver {
        C04181() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().matches("android.net.wifi.STATE_CHANGE")) {
                HomeFragmentOne.this.isWifion();
            }
            if (intent.getAction().matches("android.bluetooth.adapter.action.STATE_CHANGED")) {
                HomeFragmentOne.this.isBluetoothOn();
            }
            if (intent.getAction().matches("android.media.RINGER_MODE_CHANGED")) {
                HomeFragmentOne.this.isAudioOn();
            }
        }
    }

    /* renamed from: launcher.fragment.HomeFragmentOne.2 */
//    class C07382 implements OnSweetClickListener {
//        C07382() {
//        }
//
//        public void onClick(SweetAlertDialog sweetAlertDialog) {
//            HomeFragmentOne.this.dialogSuccess.dismiss();
//        }
//    }

    public HomeFragmentOne() {
        this.mBatInfoReceiver = new C04181();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = (ViewGroup) inflater.inflate(R.layout.home_layout_one, container, false);
        this.ivWallpaper = (ImageView) this.rootView.findViewById(R.id.imageViewWallpapar);
        this.ivTheme = (ImageView) this.rootView.findViewById(R.id.imageViewTheme);
        this.ivTransitionSelection = (ImageView) this.rootView.findViewById(R.id.imageViewTransitionSelection);
        this.ivFontSelection = (ImageView) this.rootView.findViewById(R.id.imageViewFontSelection);
        this.ivFontColorSelection = (ImageView) this.rootView.findViewById(R.id.imageViewFontColorSelection);
        this.ivMoreApps = (ImageView) this.rootView.findViewById(R.id.imageViewMoreApps);
        this.ivRateApp = (ImageView) this.rootView.findViewById(R.id.imageViewRateApps);
        this.ivShareApp = (ImageView) this.rootView.findViewById(R.id.imageViewShareApps);
        this.ivWifi = (ImageView) this.rootView.findViewById(R.id.imageViewWifi);
        this.ivBluetooth = (ImageView) this.rootView.findViewById(R.id.imageViewBlutooth);
        this.ivSound = (ImageView) this.rootView.findViewById(R.id.imageViewSound);
        this.ivBrightness = (ImageView) this.rootView.findViewById(R.id.imageViewBrightness);
        this.ivMore = (ImageView) this.rootView.findViewById(R.id.imageViewMore);
        this.ivWallpaper.setOnClickListener(this);
        this.ivTheme.setOnClickListener(this);
        this.ivTransitionSelection.setOnClickListener(this);
        this.ivFontSelection.setOnClickListener(this);
        this.ivFontColorSelection.setOnClickListener(this);
        this.ivMoreApps.setOnClickListener(this);
        this.ivRateApp.setOnClickListener(this);
        this.ivShareApp.setOnClickListener(this);
        this.ivWifi.setOnClickListener(this);
        this.ivBluetooth.setOnClickListener(this);
        this.ivSound.setOnClickListener(this);
        this.ivBrightness.setOnClickListener(this);
        this.ivMore.setOnClickListener(this);
        this.tvOne = (TextView) this.rootView.findViewById(R.id.textViewOne);
        this.tvTwo = (TextView) this.rootView.findViewById(R.id.textViewTwo);
        this.tvThree = (TextView) this.rootView.findViewById(R.id.textViewThree);
        this.tvFour = (TextView) this.rootView.findViewById(R.id.textViewFour);
        this.tvFive = (TextView) this.rootView.findViewById(R.id.textViewFive);
        this.tvMoreApps = (TextView) this.rootView.findViewById(R.id.textViewMoreApps);
        this.tvRateApp = (TextView) this.rootView.findViewById(R.id.textViewRateApps);
        this.tvShareApp = (TextView) this.rootView.findViewById(R.id.textViewShareApps);
        this.tvOne.setTypeface(Utils.getFont(getActivity()));
        this.tvTwo.setTypeface(Utils.getFont(getActivity()));
        this.tvThree.setTypeface(Utils.getFont(getActivity()));
        this.tvFour.setTypeface(Utils.getFont(getActivity()));
        this.tvFive.setTypeface(Utils.getFont(getActivity()));
        this.tvMoreApps.setTypeface(Utils.getFont(getActivity()));
        this.tvRateApp.setTypeface(Utils.getFont(getActivity()));
        this.tvShareApp.setTypeface(Utils.getFont(getActivity()));
        this.clockView = (AnalogClock) this.rootView.findViewById(R.id.Analogclock);
        this.clockView.setDialResource(R.mipmap.c2);
        this.clockView.setHourResource(R.mipmap.h1);
        this.clockView.setMinuteResource(R.mipmap.m1);
        this.clockView.setSecondResource(R.mipmap.s1);
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.intent.action.BATTERY_CHANGED");
        filter.addAction("android.intent.action.AIRPLANE_MODE");
        filter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        filter.addAction("gps");
        filter.addAction("android.net.wifi.STATE_CHANGE");
        filter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        filter.addAction("android.media.RINGER_MODE_CHANGED");
        getActivity().registerReceiver(this.mBatInfoReceiver, filter);
        isWifion();
        isBluetoothOn();
        isAudioOn();
        return this.rootView;
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo wifi = connMgr.getNetworkInfo(1);
        NetworkInfo mobile = connMgr.getNetworkInfo(0);
        if (wifi.isAvailable() && wifi.isConnected()) {
            return true;
        }
        if (mobile != null && mobile.isAvailable() && mobile.isConnected()) {
            return true;
        }
        return false;
    }

    public void isWifion() {
        try {
            if (!isOnline(getActivity())) {
                this.ivWifi.setImageResource(R.mipmap.wifi_off);
            }
        } catch (Exception e) {
            this.ivWifi.setImageResource(R.mipmap.wifi_on);
        }
    }

    private void isBluetoothOn() {
        try {
            if (BluetoothAdapter.getDefaultAdapter().isEnabled()) {
                this.ivBluetooth.setImageResource(R.mipmap.bluetooth_on);
            }
        } catch (Exception e) {
            this.ivBluetooth.setImageResource(R.mipmap.bluetooth_off);
        }
    }

    public void isAudioOn() {
        try {
            this.myAudioManager = (AudioManager) getActivity().getSystemService("audio");
            int mod = this.myAudioManager.getRingerMode();
            if (mod == 2) {
                this.ivSound.setImageResource(R.mipmap.sound_normal);
            } else if (mod == 0) {
                this.ivSound.setImageResource(R.mipmap.sound_silent);
            } else if (mod == 1) {
                this.ivSound.setImageResource(R.mipmap.sound_vibrate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onResume() {
        super.onResume();
        this.clockView.start();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewWifi /*2131558545*/:
                WifiManager wifi = (WifiManager) getActivity().getSystemService("wifi");
                if (wifi.isWifiEnabled()) {
                    wifi.setWifiEnabled(false);
                    this.ivWifi.setImageResource(R.mipmap.wifi_off);
                    return;
                }
                wifi.setWifiEnabled(true);
                this.ivWifi.setImageResource(R.mipmap.wifi_on);
            case R.id.imageViewBlutooth /*2131558546*/:
                BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (mBluetoothAdapter.isEnabled()) {
                    mBluetoothAdapter.disable();
                    this.ivBluetooth.setImageResource(R.mipmap.bluetooth_off);
                    return;
                }
                mBluetoothAdapter.enable();
                this.ivBluetooth.setImageResource(R.mipmap.bluetooth_on);
            case R.id.imageViewSound /*2131558547*/:
                this.myAudioManager = (AudioManager) getActivity().getSystemService("audio");
                int mod = this.myAudioManager.getRingerMode();
                Vibrator mVibrator = (Vibrator) getActivity().getSystemService("vibrator");
                boolean hasVibrator = false;
                if (VERSION.SDK_INT >= 11) {
                    hasVibrator = mVibrator.hasVibrator();
                }
                if (hasVibrator) {
                    if (mod == 2) {
                        this.myAudioManager.setRingerMode(0);
                        this.ivSound.setImageResource(R.mipmap.sound_silent);
                        Toast.makeText(getActivity(), "Now in Silent Mode", Toast.LENGTH_SHORT).show();
                    } else if (mod == 0) {
                        this.myAudioManager.setRingerMode(1);
                        this.ivSound.setImageResource(R.mipmap.sound_vibrate);
                        Toast.makeText(getActivity(), "Now in Vibrate Mode", Toast.LENGTH_SHORT).show();
                    } else if (mod == 1) {
                        this.myAudioManager.setRingerMode(2);
                        this.ivSound.setImageResource(R.mipmap.sound_normal);
                        Toast.makeText(getActivity(), "Now in Ringing Mode", Toast.LENGTH_SHORT).show();
                    }
                } else if (mod == 2) {
                    this.myAudioManager.setRingerMode(0);
                    this.ivSound.setImageResource(R.mipmap.sound_silent);
                    Toast.makeText(getActivity(), "Now in Silent Mode", Toast.LENGTH_SHORT).show();
                } else if (mod == 0) {
                    this.myAudioManager.setRingerMode(2);
                    this.ivSound.setImageResource(R.mipmap.sound_normal);
                    Toast.makeText(getActivity(), "Now in Ringing Mode", Toast.LENGTH_SHORT).show();
                }
            case R.id.imageViewBrightness /*2131558548*/:
                try {
                    this.brightnessMode = System.getInt(getActivity().getContentResolver(), "screen_brightness_mode");
                } catch (SettingNotFoundException e) {
                    e.printStackTrace();
                }
                if (this.brightnessMode == 0) {
                    try {
                        System.putInt(getActivity().getContentResolver(), "screen_brightness_mode", 1);
                        this.ivBrightness.setImageResource(R.mipmap.auto_brightness_on);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                } else if (this.brightnessMode == 1) {
                    try {
                        System.putInt(getActivity().getContentResolver(), "screen_brightness_mode", 0);
                        this.ivBrightness.setImageResource(R.mipmap.auto_brightness_off);
                    } catch (Exception e22) {
                        e22.printStackTrace();
                    }
                }
            case R.id.imageViewMore /*2131558549*/:
                try {
                    this.intent = new Intent("android.intent.action.VIEW");
                    this.intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=MobizeoApps"));
                    startActivity(this.intent);
                } catch (Exception e222) {
                    e222.printStackTrace();
                }
            case R.id.imageViewWallpapar /*2131558552*/:
                if (Utils.isOnline(getActivity())) {
                    startActivity(new Intent(getActivity(), BackgroundSelection.class));
                } else {
                    show();
                }
            case R.id.imageViewTheme /*2131558554*/:
                startActivity(new Intent(getActivity(), ThemeSelection.class));
            case R.id.imageViewTransitionSelection /*2131558556*/:
                startActivity(new Intent(getActivity(), TransitionSelection.class));
            case R.id.imageViewFontSelection /*2131558558*/:
                startActivity(new Intent(getActivity(), FontSelection.class));
            case R.id.imageViewFontColorSelection /*2131558560*/:
                startActivity(new Intent(getActivity(), FontColorSelection.class));
            case R.id.imageViewMoreApps /*2131558562*/:
                this.intent = new Intent("android.intent.action.VIEW");
                this.intent.setData(Uri.parse("https://play.google.com/store/apps/developer?id=SoftizonApps"));
                startActivity(this.intent);
            case R.id.imageViewRateApps /*2131558564*/:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + getActivity().getPackageName())));
            case R.id.imageViewShareApps /*2131558566*/:
                this.intent = new Intent("android.intent.action.SEND");
                this.intent.setType("text/plain");
                this.intent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/developer?id=MobizeoApps&hl=en");
                this.intent.putExtra("android.intent.extra.SUBJECT", "Check out this app!");
                startActivity(Intent.createChooser(this.intent, "Share link to..."));
            default:
        }
    }

//    private void show() {
//        this.dialogSuccess = new SweetAlertDialog(getActivity(), 1);
//        this.dialogSuccess.setTitleText("No Internet Connection!");
//        this.dialogSuccess.setContentText("Background requires a working internet connection.").setConfirmClickListener(new C07382());
//        this.dialogSuccess.setCancelable(true);
//        this.dialogSuccess.show();
//    }



    public void show(){


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle("No Internet Connection!");

        // set dialog message
        alertDialogBuilder
                .setMessage("Background requires a working internet connection.").setCancelable(true).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                dialog.dismiss();
            }
        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


}

