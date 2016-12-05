package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.DateAndTime;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.LocationAddress;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.services.AppLocationService;


/**
 * Created by iamla on 11/29/2016.
 */
public class HomeFragmentTwo extends Fragment implements OnClickListener {
    AppLocationService appLocationService;
    EditText etSearch;
    AppDetail val$app1;
    TextView getTvFacebookChat;
    ArrayList<AppDetail> googleApps;
    ImageView ivFacebook;
    ImageView ivGames;
    ImageView ivGoogle;
    ImageView ivMessenger;
    ImageView ivSocialMedia;
    ImageView ivTwitter;
    ImageView ivViewTools;
    ImageView ivWeather;
    ImageView ivWhatsApp;
    public ViewGroup rootView;
    ImageView search;
    TextView tvDate;
    TextView tvDegree;
    TextView tvEight;
    TextView tvFacebook;
    TextView tvFive;
    TextView tvFour;
    TextView tvHour;
    TextView tvLocation;
    TextView tvMinute;
    TextView tvOne;
    TextView tvSeven;
    TextView tvSix;
    TextView tvThree;
    TextView tvTwitter;
    TextView tvTwo;
    TextView tvWhatsapp;

    /* renamed from: launcher.fragment.HomeFragmentTwo.1 */
    class C04191 implements OnKeyListener {
        C04191() {
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 66) {
                HomeFragmentTwo.this.hideSoftKeyboard(HomeFragmentTwo.this.etSearch);
            }
            HomeFragmentTwo.this.etSearch.clearFocus();
            return false;
        }
    }

    /* renamed from: launcher.fragment.HomeFragmentTwo.2 */
    class C04202 implements OnClickListener {
        C04202() {
        }

        public void onClick(View view) {
            HomeFragmentTwo.this.etSearch.requestFocus();
            ((InputMethodManager) HomeFragmentTwo.this.getActivity().getSystemService("input_method")).showSoftInput(HomeFragmentTwo.this.etSearch, 1);
        }
    }

    private class GeocoderHandler extends Handler {
        private GeocoderHandler() {
        }

        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case ConnectionResult.SERVICE_MISSING /*1*/:
                    locationAddress = message.getData().getString("address");
                    break;
                default:
                    locationAddress = null;
                    break;
            }
            HomeFragmentTwo.this.tvLocation.setText(locationAddress);
        }
    }

//    /* renamed from: launcher.fragment.HomeFragmentTwo.3 */
//    class C07393 implements OnSweetClickListener {
//        final /* synthetic */ AppDetail val$app1;
//
//        C07393(AppDetail appDetail) {
//            this.val$app1 = appDetail;
//        }
//
//        public void onClick(SweetAlertDialog sDialog) {
//            if (Utils.isOnline(HomeFragmentTwo.this.getActivity().getApplicationContext())) {
//                try {
//                    HomeFragmentTwo.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.val$app1.packageName)));
//                } catch (ActivityNotFoundException e) {
//                    try {
//                        HomeFragmentTwo.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + this.val$app1.packageName)));
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                }
//            } else {
//                Toast.makeText(HomeFragmentTwo.this.getActivity(), "Internet is not available", Toast.LENGTH_SHORT).show();
//            }
//            sDialog.cancel();
//        }
//    }

    /* renamed from: launcher.fragment.HomeFragmentTwo.4 */
//    class C07404 implements OnSweetClickListener {
//        C07404() {
//        }
//
//        public void onClick(SweetAlertDialog sDialog) {
//            sDialog.cancel();
//        }
//    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = (ViewGroup) inflater.inflate(R.layout.home_layout_two, container, false);
        this.ivGoogle = (ImageView) this.rootView.findViewById(R.id.imageViewGoogle);
        this.ivSocialMedia = (ImageView) this.rootView.findViewById(R.id.imageViewSocialMedia);
        this.ivViewTools = (ImageView) this.rootView.findViewById(R.id.imageViewTools);
        this.ivGames = (ImageView) this.rootView.findViewById(R.id.imageViewGames);
        this.ivFacebook = (ImageView) this.rootView.findViewById(R.id.imageViewFacebook);
        this.ivMessenger = (ImageView) this.rootView.findViewById(R.id.imageViewMessenger);
        this.ivWhatsApp = (ImageView) this.rootView.findViewById(R.id.imageViewWhatsApp);
        this.ivTwitter = (ImageView) this.rootView.findViewById(R.id.imageViewTwitter);
        this.ivWeather = (ImageView) this.rootView.findViewById(R.id.imageViewWeather);
        this.ivGoogle.setOnClickListener(this);
        this.ivSocialMedia.setOnClickListener(this);
        this.ivViewTools.setOnClickListener(this);
        this.ivGames.setOnClickListener(this);
        this.ivFacebook.setOnClickListener(this);
        this.ivMessenger.setOnClickListener(this);
        this.ivWhatsApp.setOnClickListener(this);
        this.ivTwitter.setOnClickListener(this);
        this.tvFacebook = (TextView) this.rootView.findViewById(R.id.textViewFacebook);
        this.getTvFacebookChat = (TextView) this.rootView.findViewById(R.id.textViewFacebookChat);
        this.tvWhatsapp = (TextView) this.rootView.findViewById(R.id.textViewWhatsapp);
        this.tvTwitter = (TextView) this.rootView.findViewById(R.id.textViewTwitter);
        this.tvHour = (TextView) this.rootView.findViewById(R.id.textViewHour);
        this.tvMinute = (TextView) this.rootView.findViewById(R.id.textViewMinute);
        this.tvDate = (TextView) this.rootView.findViewById(R.id.textViewDate);
        this.tvLocation = (TextView) this.rootView.findViewById(R.id.textViewLocation);
        this.tvDegree = (TextView) this.rootView.findViewById(R.id.textViewDegree);
        this.tvFacebook.setTypeface(Utils.getFont(getActivity()));
        this.getTvFacebookChat.setTypeface(Utils.getFont(getActivity()));
        this.tvWhatsapp.setTypeface(Utils.getFont(getActivity()));
        this.tvTwitter.setTypeface(Utils.getFont(getActivity()));
        this.tvHour.setTypeface(Utils.getFont(getActivity()));
        this.tvMinute.setTypeface(Utils.getFont(getActivity()));
        this.tvDate.setTypeface(Utils.getFont(getActivity()));
        this.tvLocation.setTypeface(Utils.getFont(getActivity()));
        this.tvDegree.setTypeface(Utils.getFont(getActivity()));
        updateTime();
        this.etSearch = (EditText) this.rootView.findViewById(R.id.editTextSearch);
        this.etSearch.setOnKeyListener(new C04191());
        this.etSearch.setOnClickListener(new C04202());
        this.search = (ImageView) this.rootView.findViewById(R.id.imageViewSearch);
        this.search.setOnClickListener(this);
        this.googleApps = new ArrayList();
        this.googleApps.add(new AppDetail("com.facebook.katana", getActivity().getResources().getDrawable(R.mipmap.facebook), "Facebook", false));
        this.googleApps.add(new AppDetail("com.facebook.orca", getActivity().getResources().getDrawable(R.mipmap.facebook_chat), "Facebook Chat", false));
        this.googleApps.add(new AppDetail("com.whatsapp", getActivity().getResources().getDrawable(R.mipmap.whatsapp), "Whatsapp", false));
        this.googleApps.add(new AppDetail("com.twitter.android", getActivity().getResources().getDrawable(R.mipmap.twitter), "Twitter", false));
        this.googleApps.add(new AppDetail("com.facebook.katana", getActivity().getResources().getDrawable(R.mipmap.facebook), "Facebook", false));
        this.googleApps.add(new AppDetail("com.facebook.orca", getActivity().getResources().getDrawable(R.mipmap.facebook_chat), "Facebook Chat", false));
        this.appLocationService = new AppLocationService(getActivity());
        Location location = this.appLocationService.getLocation("gps");
        if (location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            LocationAddress locationAddress = new LocationAddress();
            LocationAddress.getAddressFromLocation(latitude, longitude, getActivity().getApplicationContext(), new GeocoderHandler());
        }
        return this.rootView;
    }

    public void updateTime() {
        try {
            this.tvHour.setText(DateAndTime.hour());
            this.tvMinute.setText(DateAndTime.minute());
            this.tvDate.setText(DateAndTime.date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideSoftKeyboard(EditText input) {
        input.setInputType(0);
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(input.getWindowToken(), 0);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageViewSearch /*2131558569*/:
                if (!this.etSearch.getText().toString().isEmpty()) {
                    try {
                        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
                        intent.setPackage("com.google.android.googlequicksearchbox");
                        intent.putExtra(SearchIntents.EXTRA_QUERY, this.etSearch.getText().toString());
                        startActivity(intent);
                    } catch (Exception e) {
                        try {
                            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://www.google.com/#q=" + this.search)));
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            case R.id.imageViewSocialMedia /*2131558578*/:
                Toast.makeText(getActivity(), "imageViewTwo", Toast.LENGTH_SHORT).show();
            case R.id.imageViewTools /*2131558579*/:
                Toast.makeText(getActivity(), "imageViewThree", Toast.LENGTH_SHORT).show();
            case R.id.imageViewGames /*2131558580*/:
                Toast.makeText(getActivity(), "imageViewFour", Toast.LENGTH_SHORT).show();
            case R.id.imageViewFacebook /*2131558581*/:
                try {
                    getActivity().startActivity(getActivity().getPackageManager().getLaunchIntentForPackage("com.facebook.katana"));
                } catch (Exception e2) {
                    AlertDialogbox(new AppDetail("com.facebook.katana", getActivity().getResources().getDrawable(R.mipmap.facebook), "Facebook", false));
                }
            case R.id.imageViewMessenger /*2131558583*/:
                try {
                    getActivity().startActivity(getActivity().getPackageManager().getLaunchIntentForPackage("com.facebook.orca"));
                } catch (Exception e3) {
                    AlertDialogbox(new AppDetail("com.facebook.orca", getActivity().getResources().getDrawable(R.mipmap.facebook_chat), "Facebook Chat", false));
                }
            case R.id.imageViewWhatsApp /*2131558585*/:
                try {
                    getActivity().startActivity(getActivity().getPackageManager().getLaunchIntentForPackage("com.whatsapp"));
                } catch (Exception e4) {
                    AlertDialogbox(new AppDetail("com.whatsapp", getActivity().getResources().getDrawable(R.mipmap.whatsapp), "Whatsapp", false));
                }
            case R.id.imageViewTwitter /*2131558587*/:
                try {
                    getActivity().startActivity(getActivity().getPackageManager().getLaunchIntentForPackage("com.twitter.android"));
                } catch (Exception e5) {
                    AlertDialogbox(new AppDetail("com.twitter.android", getActivity().getResources().getDrawable(R.mipmap.twitter), "Twitter", false));
                }
            default:
        }
    }

//    public void AlertDialogbox(AppDetail app1) {
//        new SweetAlertDialog(getActivity(), 4).setContentText("Do you want to Install it?").setTitleText("Application Not Installed..!").setCancelText("cancel").setConfirmText("Agree").showCancelButton(true).setCancelClickListener(new C07404()).setConfirmClickListener(new C07393(app1)).setCustomImage(app1.icon).show();
//    }



    public void AlertDialogbox(AppDetail app1){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());
        alertDialogBuilder.setTitle("Do you want to Install it?");
        alertDialogBuilder
                .setMessage("Application Not Installed..!")
                .setCancelable(false)
                .setPositiveButton("Agree",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {




                        if (Utils.isOnline(HomeFragmentTwo.this.getActivity().getApplicationContext())) {
                            try {
                                HomeFragmentTwo.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + val$app1.packageName)));
                            } catch (ActivityNotFoundException e) {
                                try {
                                    HomeFragmentTwo.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + val$app1.packageName)));
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            Toast.makeText(HomeFragmentTwo.this.getActivity(), "Internet is not available", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();



                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).setIcon(app1.icon);
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



    public void onDestroyView() {
        super.onDestroyView();
    }
}

