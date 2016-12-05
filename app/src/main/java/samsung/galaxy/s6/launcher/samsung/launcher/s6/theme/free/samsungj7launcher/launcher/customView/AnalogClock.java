package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView;


import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PointerIconCompat;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;

import java.util.Calendar;
import java.util.TimeZone;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/24/2016.
 */
public class AnalogClock extends RelativeLayout {
    private static final int DEGREE_MINUTE = 6;
    private static final int HOUR_TO_HOUR_DEGREE = 30;
    private static final int INVALID_ANGLE = -1;
    private static final int MINUTE_TO_HOUR_DEGREE = 12;
    private boolean isFirstTick;
    private boolean isRunning;
    private Context mContext;
    private int mDialBackgroundResource;
    private Handler mHandler;
    private int mHourAngle;
    private int mHourBackgroundResource;
    private ImageView mHourHand;
    private int mMinuteAngle;
    private int mMinuteBackgroundResource;
    private ImageView mMinuteHand;
    private int mSecondAngle;
    private int mSecondBackgroundResource;
    private ImageView mSecondHand;

    /* renamed from: launcher.customView.AnalogClock.1 */
    class C04071 extends Handler {
        C04071() {
        }

        public void handleMessage(Message msg) {
            if (AnalogClock.this.isRunning) {
                AnalogClock.this.proceed();
                AnalogClock.this.mHandler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    }

    public AnalogClock(Context context) {
        super(context);
        this.isRunning = false;
        this.isFirstTick = true;
        this.mHourAngle = INVALID_ANGLE;
        this.mMinuteAngle = INVALID_ANGLE;
        this.mSecondAngle = INVALID_ANGLE;
        this.mDialBackgroundResource = R.mipmap.c2;
        this.mHourBackgroundResource = R.mipmap.h1;
        this.mMinuteBackgroundResource = R.mipmap.m1;
        this.mSecondBackgroundResource = R.mipmap.s1;
        this.mHandler = new C04071();
        init(context);
    }

    public AnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isRunning = false;
        this.isFirstTick = true;
        this.mHourAngle = INVALID_ANGLE;
        this.mMinuteAngle = INVALID_ANGLE;
        this.mSecondAngle = INVALID_ANGLE;
        this.mDialBackgroundResource = R.mipmap.c2;
        this.mHourBackgroundResource = R.mipmap.h1;
        this.mMinuteBackgroundResource = R.mipmap.m1;
        this.mSecondBackgroundResource = R.mipmap.s1;
        this.mHandler = new C04071();
        setIcons(context, attrs);
        init(context);
    }

    public AnalogClock(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.isRunning = false;
        this.isFirstTick = true;
        this.mHourAngle = INVALID_ANGLE;
        this.mMinuteAngle = INVALID_ANGLE;
        this.mSecondAngle = INVALID_ANGLE;
        this.mDialBackgroundResource = R.mipmap.c2;
        this.mHourBackgroundResource = R.mipmap.h1;
        this.mMinuteBackgroundResource = R.mipmap.m1;
        this.mSecondBackgroundResource = R.mipmap.s1;
        this.mHandler = new C04071();
        setIcons(context, attrs);
        init(context);
    }

    private void setIcons(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LockScreenView);
        this.mDialBackgroundResource = array.getResourceId(0, R.mipmap.c2);
        this.mHourBackgroundResource = array.getResourceId(1, R.mipmap.h1);
        this.mMinuteBackgroundResource = array.getResourceId(2, R.mipmap.m1);
        this.mSecondBackgroundResource = array.getResourceId(3, R.mipmap.s1);
        array.recycle();
    }

    private void init(Context con) {
        this.mContext = con;
        this.mHourHand = new ImageView(this.mContext);
        setHourResource(this.mHourBackgroundResource);
        this.mHourHand.setScaleType(ScaleType.CENTER_INSIDE);
        LayoutParams lp = new LayoutParams(INVALID_ANGLE, INVALID_ANGLE);
        lp.addRule(13);
        addView(this.mHourHand, lp);
        this.mMinuteHand = new ImageView(this.mContext);
        setMinuteResource(this.mMinuteBackgroundResource);
        this.mMinuteHand.setScaleType(ScaleType.CENTER_INSIDE);
        lp = new LayoutParams(INVALID_ANGLE, INVALID_ANGLE);
        lp.addRule(13);
        addView(this.mMinuteHand, lp);
        this.mSecondHand = new ImageView(this.mContext);
        setSecondResource(this.mSecondBackgroundResource);
        this.mSecondHand.setScaleType(ScaleType.CENTER_INSIDE);
        lp = new LayoutParams(INVALID_ANGLE, INVALID_ANGLE);
        lp.addRule(13);
        addView(this.mSecondHand, lp);
        setBackgroundResource(this.mDialBackgroundResource);
    }

    public void start() {
        if (!this.isRunning) {
            this.isRunning = true;
            this.isFirstTick = true;
            this.mHandler.sendEmptyMessageDelayed(0, (long) (((int) Calendar.getInstance().getTimeInMillis()) % PointerIconCompat.TYPE_DEFAULT));
        }
    }

    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
        }
    }

    private void proceed() {
        if (this.isRunning) {
            TimeZone tz = TimeZone.getDefault();
            Calendar tempCal = Calendar.getInstance();
            tempCal.setTimeZone(tz);
            int hour = tempCal.get(Calendar.HOUR);
            int min = tempCal.get(Calendar.MINUTE);
            int sec = tempCal.get(Calendar.SECOND);
            int newHourAngle = (hour * HOUR_TO_HOUR_DEGREE) + ((min / MINUTE_TO_HOUR_DEGREE) * DEGREE_MINUTE);
            int newMinuteAngle = min * DEGREE_MINUTE;
            int newSecondAngle = sec * DEGREE_MINUTE;
            if (this.isFirstTick) {
                if (this.mHourAngle == INVALID_ANGLE || this.mMinuteAngle == INVALID_ANGLE || this.mSecondAngle == INVALID_ANGLE) {
                    rotate(this.mHourHand, newHourAngle, newHourAngle);
                    rotate(this.mMinuteHand, newMinuteAngle, newMinuteAngle);
                    rotate(this.mSecondHand, newSecondAngle, newSecondAngle);
                } else {
                    rotate(this.mHourHand, this.mHourAngle, newHourAngle);
                    rotate(this.mMinuteHand, this.mMinuteAngle, newMinuteAngle);
                    rotate(this.mSecondHand, this.mSecondAngle, newSecondAngle);
                }
                this.isFirstTick = false;
            } else {
                if (min == 0 && sec == 0) {
                    rotate(this.mHourHand, newHourAngle - 6, newHourAngle);
                }
                if (sec == 0) {
                    rotate(this.mMinuteHand, newMinuteAngle - 6, newMinuteAngle);
                }
                rotate(this.mSecondHand, newSecondAngle - 6, newSecondAngle);
            }
            this.mHourAngle = newHourAngle;
            this.mMinuteAngle = newMinuteAngle;
            this.mSecondAngle = newSecondAngle;
        }
    }

    private void rotate(ImageView view, int fromAngle, int toAngle) {
        Animation anim = new RotateAnimation((float) fromAngle, (float) toAngle, 1, 0.5f, 1, 0.5f);
        if (Math.abs(toAngle - fromAngle) != DEGREE_MINUTE) {
            anim.setDuration(600);
            anim.setInterpolator(AnimationUtils.loadInterpolator(this.mContext, 17432581));
        } else {
            anim.setDuration(150);
            anim.setInterpolator(AnimationUtils.loadInterpolator(this.mContext, 17432584));
        }
        anim.setFillAfter(true);
        view.startAnimation(anim);
    }

    public void setDialResource(int id) {
        this.mDialBackgroundResource = id;
        setBackgroundResource(this.mDialBackgroundResource);
    }

    public void setHourResource(int id) {
        this.mHourBackgroundResource = id;
        this.mHourHand.setImageResource(id);
    }

    public void setMinuteResource(int id) {
        this.mMinuteBackgroundResource = id;
        this.mMinuteHand.setImageResource(id);
    }

    public void setSecondResource(int id) {
        this.mSecondBackgroundResource = id;
        this.mSecondHand.setImageResource(id);
    }
}

