//package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customDialog;
//
//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.drawable.Drawable;
//import android.os.Build.VERSION;
//import android.os.Bundle;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.WindowManager.LayoutParams;
//import android.view.animation.AlphaAnimation;
//import android.view.animation.Animation;
//import android.view.animation.Animation.AnimationListener;
//import android.view.animation.AnimationSet;
//import android.view.animation.Transformation;
//import android.widget.Button;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.TextView;
//import com.android.volley.DefaultRetryPolicy;
//import java.util.List;
//
//import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
//
//
///**
// * Created by iamla on 11/24/2016.
// */
//public class SweetAlertDialog extends Dialog implements OnClickListener {
//    public static final int CUSTOM_IMAGE_TYPE = 4;
//    public static final int ERROR_TYPE = 1;
//    public static final int NORMAL_TYPE = 0;
//    public static final int PROGRESS_TYPE = 5;
//    public static final int SUCCESS_TYPE = 2;
//    public static final int WARNING_TYPE = 3;
//    private int mAlertType;
//    private Button mCancelButton;
//    private OnSweetClickListener mCancelClickListener;
//    private String mCancelText;
//    private boolean mCloseFromCancel;
//    private Button mConfirmButton;
//    private OnSweetClickListener mConfirmClickListener;
//    private String mConfirmText;
//    private String mContentText;
//    private TextView mContentTextView;
//    private ImageView mCustomImage;
//    private Drawable mCustomImgDrawable;
//    private View mDialogView;
//    private FrameLayout mErrorFrame;
//    private Animation mErrorInAnim;
//    private ImageView mErrorX;
//    private AnimationSet mErrorXInAnim;
//    private AnimationSet mModalInAnim;
//    private AnimationSet mModalOutAnim;
//    private Animation mOverlayOutAnim;
//    private FrameLayout mProgressFrame;
//    private ProgressHelper mProgressHelper;
//    private boolean mShowCancel;
//    private boolean mShowContent;
//    private Animation mSuccessBowAnim;
//    private FrameLayout mSuccessFrame;
//    private AnimationSet mSuccessLayoutAnimSet;
//    private View mSuccessLeftMask;
//    private View mSuccessRightMask;
//    private SuccessTickView mSuccessTick;
//    private String mTitleText;
//    private TextView mTitleTextView;
//    private FrameLayout mWarningFrame;
//
//    /* renamed from: launcher.customDialog.SweetAlertDialog.1 */
//    class C04051 implements AnimationListener {
//
//        /* renamed from: launcher.customDialog.SweetAlertDialog.1.1 */
//        class C04041 implements Runnable {
//            C04041() {
//            }
//
//            public void run() {
//                if (SweetAlertDialog.this.mCloseFromCancel) {
//                    super.cancel();
//
//                } else {
//                    super.dismiss();
//                }
//            }
//        }
//
//        C04051() {
//        }
//
//        public void onAnimationStart(Animation animation) {
//        }
//
//        public void onAnimationEnd(Animation animation) {
//            SweetAlertDialog.this.mDialogView.setVisibility(View.GONE);
//            SweetAlertDialog.this.mDialogView.post(new C04041());
//        }
//
//        public void onAnimationRepeat(Animation animation) {
//        }
//    }
//
//    /* renamed from: launcher.customDialog.SweetAlertDialog.2 */
//    class C04062 extends Animation {
//        C04062() {
//        }
//
//        protected void applyTransformation(float interpolatedTime, Transformation t) {
//            LayoutParams wlp = SweetAlertDialog.this.getWindow().getAttributes();
//            wlp.alpha = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - interpolatedTime;
//            SweetAlertDialog.this.getWindow().setAttributes(wlp);
//        }
//    }
//
//    public interface OnSweetClickListener {
//        void onClick(SweetAlertDialog sweetAlertDialog);
//    }
//
//    public SweetAlertDialog(Context context) {
//        this(context, NORMAL_TYPE);
//    }
//
//    public SweetAlertDialog(Context context, int alertType) {
//        super(context, R.style.alert_dialog);
//        setCancelable(true);
//        setCanceledOnTouchOutside(false);
//        this.mProgressHelper = new ProgressHelper(context);
//        this.mAlertType = alertType;
//        this.mErrorInAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.error_frame_in);
//        this.mErrorXInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.error_x_in);
//        if (VERSION.SDK_INT <= 10) {
//            List<Animation> childAnims = this.mErrorXInAnim.getAnimations();
//            int idx = NORMAL_TYPE;
//            while (idx < childAnims.size() && !(childAnims.get(idx) instanceof AlphaAnimation)) {
//                idx += ERROR_TYPE;
//            }
//            if (idx < childAnims.size()) {
//                childAnims.remove(idx);
//            }
//        }
//        this.mSuccessBowAnim = OptAnimationLoader.loadAnimation(getContext(), R.anim.success_bow_roate);
//        this.mSuccessLayoutAnimSet = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.success_mask_layout);
//        this.mModalInAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_in);
//        this.mModalOutAnim = (AnimationSet) OptAnimationLoader.loadAnimation(getContext(), R.anim.modal_out);
//        this.mModalOutAnim.setAnimationListener(new C04051());
//        this.mOverlayOutAnim = new C04062();
//        this.mOverlayOutAnim.setDuration(120);
//    }
//
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.alert_dialog);
//        this.mDialogView = getWindow().getDecorView().findViewById(16908290);
//        this.mTitleTextView = (TextView) findViewById(R.id.title_text);
//        this.mContentTextView = (TextView) findViewById(R.id.content_text);
//        this.mErrorFrame = (FrameLayout) findViewById(R.id.error_frame);
//        this.mErrorX = (ImageView) this.mErrorFrame.findViewById(R.id.error_x);
//        this.mSuccessFrame = (FrameLayout) findViewById(R.id.success_frame);
//        this.mProgressFrame = (FrameLayout) findViewById(R.id.progress_dialog);
//        this.mSuccessTick = (SuccessTickView) this.mSuccessFrame.findViewById(R.id.success_tick);
//        this.mSuccessLeftMask = this.mSuccessFrame.findViewById(R.id.mask_left);
//        this.mSuccessRightMask = this.mSuccessFrame.findViewById(R.id.mask_right);
//        this.mCustomImage = (ImageView) findViewById(R.id.custom_image);
//        this.mWarningFrame = (FrameLayout) findViewById(R.id.warning_frame);
//        this.mConfirmButton = (Button) findViewById(R.id.confirm_button);
//        this.mCancelButton = (Button) findViewById(R.id.cancel_button);
//        this.mProgressHelper.setProgressWheel((ProgressWheel) findViewById(R.id.progressWheel));
//        this.mConfirmButton.setOnClickListener(this);
//        this.mCancelButton.setOnClickListener(this);
//        setTitleText(this.mTitleText);
//        setContentText(this.mContentText);
//        setCancelText(this.mCancelText);
//        setConfirmText(this.mConfirmText);
//        changeAlertType(this.mAlertType, true);
//    }
//
//    private void restore() {
//        this.mCustomImage.setVisibility(8);
//        this.mErrorFrame.setVisibility(8);
//        this.mSuccessFrame.setVisibility(8);
//        this.mWarningFrame.setVisibility(8);
//        this.mProgressFrame.setVisibility(8);
//        this.mConfirmButton.setVisibility(NORMAL_TYPE);
//        this.mConfirmButton.setBackgroundResource(R.drawable.blue_button_background);
//        this.mErrorFrame.clearAnimation();
//        this.mErrorX.clearAnimation();
//        this.mSuccessTick.clearAnimation();
//        this.mSuccessLeftMask.clearAnimation();
//        this.mSuccessRightMask.clearAnimation();
//    }
//
//    private void playAnimation() {
//        if (this.mAlertType == ERROR_TYPE) {
//            this.mErrorFrame.startAnimation(this.mErrorInAnim);
//            this.mErrorX.startAnimation(this.mErrorXInAnim);
//        } else if (this.mAlertType == SUCCESS_TYPE) {
//            this.mSuccessTick.startTickAnim();
//            this.mSuccessRightMask.startAnimation(this.mSuccessBowAnim);
//        }
//    }
//
//    private void changeAlertType(int alertType, boolean fromCreate) {
//        this.mAlertType = alertType;
//        if (this.mDialogView != null) {
//            if (!fromCreate) {
//                restore();
//            }
//            switch (this.mAlertType) {
//                case ERROR_TYPE /*1*/:
//                    this.mErrorFrame.setVisibility(NORMAL_TYPE);
//                    break;
//                case SUCCESS_TYPE /*2*/:
//                    this.mSuccessFrame.setVisibility(NORMAL_TYPE);
//                    this.mSuccessLeftMask.startAnimation((Animation) this.mSuccessLayoutAnimSet.getAnimations().get(NORMAL_TYPE));
//                    this.mSuccessRightMask.startAnimation((Animation) this.mSuccessLayoutAnimSet.getAnimations().get(ERROR_TYPE));
//                    break;
//                case WARNING_TYPE /*3*/:
//                    this.mConfirmButton.setBackgroundResource(R.drawable.red_button_background);
//                    this.mWarningFrame.setVisibility(NORMAL_TYPE);
//                    break;
//                case CUSTOM_IMAGE_TYPE /*4*/:
//                    setCustomImage(this.mCustomImgDrawable);
//                    break;
//                case PROGRESS_TYPE /*5*/:
//                    this.mProgressFrame.setVisibility(NORMAL_TYPE);
//                    this.mConfirmButton.setVisibility(8);
//                    break;
//            }
//            if (!fromCreate) {
//                playAnimation();
//            }
//        }
//    }
//
//    public int getAlerType() {
//        return this.mAlertType;
//    }
//
//    public void changeAlertType(int alertType) {
//        changeAlertType(alertType, false);
//    }
//
//    public String getTitleText() {
//        return this.mTitleText;
//    }
//
//    public SweetAlertDialog setTitleText(String text) {
//        this.mTitleText = text;
//        if (!(this.mTitleTextView == null || this.mTitleText == null)) {
//            this.mTitleTextView.setText(this.mTitleText);
//        }
//        return this;
//    }
//
//    public SweetAlertDialog setCustomImage(Drawable drawable) {
//        this.mCustomImgDrawable = drawable;
//        if (!(this.mCustomImage == null || this.mCustomImgDrawable == null)) {
//            this.mCustomImage.setVisibility(NORMAL_TYPE);
//            this.mCustomImage.setImageDrawable(this.mCustomImgDrawable);
//        }
//        return this;
//    }
//
//    public SweetAlertDialog setCustomImage(int resourceId) {
//        return setCustomImage(getContext().getResources().getDrawable(resourceId));
//    }
//
//    public String getContentText() {
//        return this.mContentText;
//    }
//
//    public SweetAlertDialog setContentText(String text) {
//        this.mContentText = text;
//        if (!(this.mContentTextView == null || this.mContentText == null)) {
//            showContentText(true);
//            this.mContentTextView.setText(this.mContentText);
//        }
//        return this;
//    }
//
//    public boolean isShowCancelButton() {
//        return this.mShowCancel;
//    }
//
//    public SweetAlertDialog showCancelButton(boolean isShow) {
//        this.mShowCancel = isShow;
//        if (this.mCancelButton != null) {
//            this.mCancelButton.setVisibility(this.mShowCancel ? NORMAL_TYPE : 8);
//        }
//        return this;
//    }
//
//    public boolean isShowContentText() {
//        return this.mShowContent;
//    }
//
//    public SweetAlertDialog showContentText(boolean isShow) {
//        this.mShowContent = isShow;
//        if (this.mContentTextView != null) {
//            this.mContentTextView.setVisibility(this.mShowContent ? NORMAL_TYPE : 8);
//        }
//        return this;
//    }
//
//    public String getCancelText() {
//        return this.mCancelText;
//    }
//
//    public SweetAlertDialog setCancelText(String text) {
//        this.mCancelText = text;
//        if (!(this.mCancelButton == null || this.mCancelText == null)) {
//            showCancelButton(true);
//            this.mCancelButton.setText(this.mCancelText);
//        }
//        return this;
//    }
//
//    public String getConfirmText() {
//        return this.mConfirmText;
//    }
//
//    public SweetAlertDialog setConfirmText(String text) {
//        this.mConfirmText = text;
//        if (!(this.mConfirmButton == null || this.mConfirmText == null)) {
//            this.mConfirmButton.setText(this.mConfirmText);
//        }
//        return this;
//    }
//
//    public SweetAlertDialog setCancelClickListener(OnSweetClickListener listener) {
//        this.mCancelClickListener = listener;
//        return this;
//    }
//
//    public SweetAlertDialog setConfirmClickListener(OnSweetClickListener listener) {
//        this.mConfirmClickListener = listener;
//        return this;
//    }
//
//    protected void onStart() {
//        this.mDialogView.startAnimation(this.mModalInAnim);
//        playAnimation();
//    }
//
//    public void cancel() {
//        dismissWithAnimation(true);
//    }
//
//    public void dismissWithAnimation() {
//        dismissWithAnimation(false);
//    }
//
//    private void dismissWithAnimation(boolean fromCancel) {
//        this.mCloseFromCancel = fromCancel;
//        this.mConfirmButton.startAnimation(this.mOverlayOutAnim);
//        this.mDialogView.startAnimation(this.mModalOutAnim);
//    }
//
//    public void onClick(View v) {
//        if (v.getId() == R.id.cancel_button) {
//            if (this.mCancelClickListener != null) {
//                this.mCancelClickListener.onClick(this);
//            } else {
//                dismissWithAnimation();
//            }
//        } else if (v.getId() != R.id.confirm_button) {
//        } else {
//            if (this.mConfirmClickListener != null) {
//                this.mConfirmClickListener.onClick(this);
//            } else {
//                dismissWithAnimation();
//            }
//        }
//    }
//
//    public ProgressHelper getProgressHelper() {
//        return this.mProgressHelper;
//    }
//}
//
