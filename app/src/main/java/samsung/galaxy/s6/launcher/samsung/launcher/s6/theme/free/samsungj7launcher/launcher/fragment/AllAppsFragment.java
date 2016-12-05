package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment;


import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import com.google.android.gms.common.ConnectionResult;
import java.util.ArrayList;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.adapter.AllAppsAdapter;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.model.AppDetail;


/**
 * Created by iamla on 11/28/2016.
 */
public class AllAppsFragment extends Fragment {
    public static final String ARG_PAGE = "page";
    private static final int SWIPE_MAX_OFF_PATH = 600;
    private static final int SWIPE_MIN_DISTANCE = 100;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    ArrayList<AppDetail> apps;
    AppDetail val$app1;
    GridView dgv;
    private GestureDetector gestureDetector;
    OnTouchListener gestureListener;
    private int mPageNumber;
    public ViewGroup rootView;
    int systemAppCounter;

    /* renamed from: launcher.fragment.AllAppsFragment.1 */
    class C04151 implements OnItemClickListener {
        C04151() {
        }

        public void onItemClick(AdapterView<?> adapterView, View arg1, int position, long arg3) {
            if (!Settings.getIsEditMode(AllAppsFragment.this.getActivity())) {
                try {
                    Log.e("abc", "page no  " + ((AppDetail) AllAppsFragment.this.apps.get(position)).packageName.toString() + "   " + ((AppDetail) AllAppsFragment.this.apps.get(position)).appName.toString());
                    AllAppsFragment.this.getActivity().startActivity(AllAppsFragment.this.getActivity().getPackageManager().getLaunchIntentForPackage(((AppDetail) AllAppsFragment.this.apps.get(position)).packageName.toString()));
                    AllAppsFragment.this.getActivity().overridePendingTransition(AllAppsFragment.this.getAnimation(position), AllAppsFragment.this.getExitAnimation(position));
                } catch (Exception e) {
                    AllAppsFragment.this.AlertDialogbox((AppDetail) AllAppsFragment.this.apps.get(position));
                }
            } else if (((AppDetail) AllAppsFragment.this.apps.get(position)).isSystemPackage) {
                Toast.makeText(AllAppsFragment.this.getActivity(), "System App ", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    AllAppsFragment.this.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + ((AppDetail) AllAppsFragment.this.apps.get(position)).packageName)));
                } catch (Exception e2) {
                    Toast.makeText(AllAppsFragment.this.getActivity(), "App not found ", Toast.LENGTH_SHORT).show();
                    e2.printStackTrace();
                }
            }
        }
    }

    class MyGestureDetector extends SimpleOnGestureListener {
        MyGestureDetector() {
        }

        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > 600.0f) {
                    return false;
                }
                if ((e1.getY() - e2.getY() <= 100.0f || Math.abs(velocityY) <= 200.0f) && e2.getY() - e1.getY() > 100.0f && Math.abs(velocityY) > 200.0f) {
                    ((LauncherActivity) AllAppsFragment.this.getActivity()).showHideMenu();
                }
                return true;
            } catch (Exception e) {
            }
            return true;
        }

        public boolean onDown(MotionEvent e) {
            return false;
        }
    }

    /* renamed from: launcher.fragment.AllAppsFragment.2 */
//    class C07362 implements OnSweetClickListener {
//        final /* synthetic */ AppDetail val$app1;
//
//        C07362(AppDetail appDetail) {
//            this.val$app1 = appDetail;
//        }
//
//        public void onClick(SweetAlertDialog sDialog) {
//            if (Utils.isOnline(AllAppsFragment.this.getActivity().getApplicationContext())) {
//                try {
//                    AllAppsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + this.val$app1.packageName)));
//                } catch (ActivityNotFoundException e) {
//                    try {
//                        AllAppsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + this.val$app1.packageName)));
//                    } catch (Exception e2) {
//                        e2.printStackTrace();
//                    }
//                }
//            } else {
//                Toast.makeText(AllAppsFragment.this.getActivity(), "Internet is not available", 0).show();
//            }
//            sDialog.cancel();
//        }
//    }

    /* renamed from: launcher.fragment.AllAppsFragment.3 */
//    class C07373 implements OnSweetClickListener {
//        C07373() {
//        }
//
//        public void onClick(SweetAlertDialog sDialog) {
//            sDialog.cancel();
//        }
//    }

    public AllAppsFragment() {
        this.systemAppCounter = 0;
    }

    public static AllAppsFragment create(int pageNumber) {
        AllAppsFragment fragment = new AllAppsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = (ViewGroup) inflater.inflate(R.layout.all_apps_fragment, container, false);
        this.dgv = (GridView) this.rootView.findViewById(R.id.vgv);
        this.dgv.setVisibility(View.VISIBLE);
        this.apps = new ArrayList();
        for (int i = 0; i < ((ArrayList) LauncherActivity.appsList.get(this.mPageNumber)).size(); i++) {
            ((AppDetail) ((ArrayList) LauncherActivity.appsList.get(this.mPageNumber)).get(i)).appBitmap = null;
            this.apps.add((AppDetail) ((ArrayList) LauncherActivity.appsList.get(this.mPageNumber)).get(i));
            if (((AppDetail) ((ArrayList) LauncherActivity.appsList.get(this.mPageNumber)).get(i)).isSystemPackage) {
                this.systemAppCounter++;
            }
        }
        this.dgv.setAdapter(new AllAppsAdapter(getActivity(), this.apps, false, false));
        this.dgv.setOnItemClickListener(new C04151());
        return this.rootView;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    private int getExitAnimation(int position) {
        switch (position) {
            case ConnectionResult.SUCCESS /*0*/:
                return R.anim.transition_app_exit_4x4_0;
            case ConnectionResult.SERVICE_MISSING /*1*/:
                return R.anim.transition_app_exit_4x4_1;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                return R.anim.transition_app_exit_4x4_2;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return R.anim.transition_app_exit_4x4_3;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                return R.anim.transition_app_exit_4x4_4;
            case ConnectionResult.INVALID_ACCOUNT /*5*/:
                return R.anim.transition_app_exit_4x4_5;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                return R.anim.transition_app_exit_4x4_6;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                return R.anim.transition_app_exit_4x4_7;
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                return R.anim.transition_app_exit_4x4_8;
            case ConnectionResult.SERVICE_INVALID /*9*/:
                return R.anim.transition_app_exit_4x4_9;
            case ConnectionResult.DEVELOPER_ERROR /*10*/:
                return R.anim.transition_app_exit_4x4_10;
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                return R.anim.transition_app_exit_4x4_11;
            case R.styleable.WaveLoadingView_wlv_titleTop /*12*/:
                return R.anim.transition_app_exit_4x4_12;
            case ConnectionResult.CANCELED /*13*/:
                return R.anim.transition_app_exit_4x4_13;
            case ConnectionResult.TIMEOUT /*14*/:
                return R.anim.transition_app_exit_4x4_14;
            case ConnectionResult.INTERRUPTED /*15*/:
                return R.anim.transition_app_exit_4x4_15;
            default:
                return 0;
        }
    }

    private int getAnimation(int position) {
        switch (position) {
            case ConnectionResult.SUCCESS /*0*/:
                return R.anim.transition_app_enter_4x4_0;
            case ConnectionResult.SERVICE_MISSING /*1*/:
                return R.anim.transition_app_enter_4x4_1;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                return R.anim.transition_app_enter_4x4_2;
            case ConnectionResult.SERVICE_DISABLED /*3*/:
                return R.anim.transition_app_enter_4x4_3;
            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                return R.anim.transition_app_enter_4x4_4;
            case ConnectionResult.INVALID_ACCOUNT /*5*/:
                return R.anim.transition_app_enter_4x4_5;
            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                return R.anim.transition_app_enter_4x4_6;
            case ConnectionResult.NETWORK_ERROR /*7*/:
                return R.anim.transition_app_enter_4x4_7;
            case ConnectionResult.INTERNAL_ERROR /*8*/:
                return R.anim.transition_app_enter_4x4_8;
            case ConnectionResult.SERVICE_INVALID /*9*/:
                return R.anim.transition_app_enter_4x4_9;
            case ConnectionResult.DEVELOPER_ERROR /*10*/:
                return R.anim.transition_app_enter_4x4_10;
            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                return R.anim.transition_app_enter_4x4_11;
            case R.styleable.WaveLoadingView_wlv_titleTop /*12*/:
                return R.anim.transition_app_enter_4x4_12;
            case ConnectionResult.CANCELED /*13*/:
                return R.anim.transition_app_enter_4x4_13;
            case ConnectionResult.TIMEOUT /*14*/:
                return R.anim.transition_app_enter_4x4_14;
            case ConnectionResult.INTERRUPTED /*15*/:
                return R.anim.transition_app_enter_4x4_15;
            default:
                return 0;
        }
    }

//    public void AlertDialogbox(AppDetail app1) {
//        new SweetAlertDialog(getActivity(), 4).setContentText("Do you want to Install it?").setTitleText("Application Not Installed..!").setCancelText("cancel").setConfirmText("Agree").showCancelButton(true).setCancelClickListener(new C07373()).setConfirmClickListener(new C07362(app1)).setCustomImage(app1.icon).show();
//    }

    public void AlertDialogbox(AppDetail app1){


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());

        // set title
        alertDialogBuilder.setTitle("Do you want to Install it?");

        // set dialog message
        alertDialogBuilder
                .setMessage("Application Not Installed..!")
                .setCancelable(false)
                .setPositiveButton("Agree",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, close
                        // current activity
                        //MainActivity.this.finish();


                        if (Utils.isOnline(AllAppsFragment.this.getActivity().getApplicationContext())) {
                            try {
                                AllAppsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + val$app1.packageName)));
                            } catch (ActivityNotFoundException e) {
                                try {
                                    AllAppsFragment.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + val$app1.packageName)));
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            Toast.makeText(AllAppsFragment.this.getActivity(), "Internet is not available", Toast.LENGTH_SHORT).show();
                        }
                        dialog.cancel();






                    }
                })
                .setNegativeButton("cancel",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                    }
                }).setIcon(app1.icon);

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }



}
