package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customDialog;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.android.volley.DefaultRetryPolicy;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/24/2016.
 */
public class ProgressWheel extends View {
    private static final String TAG;
    private int barColor;
    private float barExtraLength;
    private boolean barGrowingFromFront;
    private final int barLength;
    private final int barMaxLength;
    private Paint barPaint;
    private double barSpinCycleTime;
    private int barWidth;
    private ProgressCallback callback;
    private RectF circleBounds;
    private int circleRadius;
    private boolean fillRadius;
    private boolean isSpinning;
    private long lastTimeAnimated;
    private boolean linearProgress;
    private float mProgress;
    private float mTargetProgress;
    private final long pauseGrowingTime;
    private long pausedTimeWithoutGrowing;
    private int rimColor;
    private Paint rimPaint;
    private int rimWidth;
    private float spinSpeed;
    private double timeStartGrowing;

    public interface ProgressCallback {
        void onProgressUpdate(float f);
    }

    static class WheelSavedState extends BaseSavedState {
        public static final Creator<WheelSavedState> CREATOR;
        int barColor;
        int barWidth;
        int circleRadius;
        boolean fillRadius;
        boolean isSpinning;
        boolean linearProgress;
        float mProgress;
        float mTargetProgress;
        int rimColor;
        int rimWidth;
        float spinSpeed;

        /* renamed from: launcher.customDialog.ProgressWheel.WheelSavedState.1 */
        static class C04021 implements Creator<WheelSavedState> {
            C04021() {
            }

            public WheelSavedState createFromParcel(Parcel in) {
                return new WheelSavedState(in);
            }

            public WheelSavedState[] newArray(int size) {
                return new WheelSavedState[size];
            }
        }

        WheelSavedState(Parcelable superState) {
            super(superState);
        }

        private WheelSavedState(Parcel in) {
            super(in);
            boolean z;
            boolean z2 = true;
            this.mProgress = in.readFloat();
            this.mTargetProgress = in.readFloat();
            this.isSpinning = in.readByte() != circleRadius;
            this.spinSpeed = in.readFloat();
            this.barWidth = in.readInt();
            this.barColor = in.readInt();
            this.rimWidth = in.readInt();
            this.rimColor = in.readInt();
            this.circleRadius = in.readInt();
            if (in.readByte() != circleRadius) {
                z = true;
            } else {
                z = false;
            }
            this.linearProgress = z;
            if (in.readByte() == circleRadius) {
                z2 = false;
            }
            this.fillRadius = z2;
        }

        public void writeToParcel(Parcel out, int flags) {
            int i;
            int i2 = 1;
            super.writeToParcel(out, flags);
            out.writeFloat(this.mProgress);
            out.writeFloat(this.mTargetProgress);
            out.writeByte((byte) (this.isSpinning ? 1 : 0));
            out.writeFloat(this.spinSpeed);
            out.writeInt(this.barWidth);
            out.writeInt(this.barColor);
            out.writeInt(this.rimWidth);
            out.writeInt(this.rimColor);
            out.writeInt(this.circleRadius);
            if (this.linearProgress) {
                i = 1;
            } else {
                i = 0;
            }
            out.writeByte((byte) i);
            if (!this.fillRadius) {
                i2 = 0;
            }
            out.writeByte((byte) i2);
        }

        static {
            CREATOR = new C04021();
        }
    }

    static {
        TAG = ProgressWheel.class.getSimpleName();
    }

    public ProgressWheel(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.circleRadius = 28;
        this.barWidth = 4;
        this.rimWidth = 4;
        this.barLength = 16;
        this.barMaxLength = 270;
        this.fillRadius = false;
        this.timeStartGrowing = 0.0d;
        this.barSpinCycleTime = 460.0d;
        this.barExtraLength = 0.0f;
        this.barGrowingFromFront = true;
        this.pausedTimeWithoutGrowing = 0;
        this.pauseGrowingTime = 200;
        this.barColor = -1442840576;
        this.rimColor = ViewCompat.MEASURED_SIZE_MASK;
        this.barPaint = new Paint();
        this.rimPaint = new Paint();
        this.circleBounds = new RectF();
        this.spinSpeed = 230.0f;
        this.lastTimeAnimated = 0;
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        this.isSpinning = false;
        parseAttributes(context.obtainStyledAttributes(attrs, R.styleable.ProgressWheel));
    }

    public ProgressWheel(Context context) {
        super(context);
        this.circleRadius = 28;
        this.barWidth = 4;
        this.rimWidth = 4;
        this.barLength = 16;
        this.barMaxLength = 270;
        this.fillRadius = false;
        this.timeStartGrowing = 0.0d;
        this.barSpinCycleTime = 460.0d;
        this.barExtraLength = 0.0f;
        this.barGrowingFromFront = true;
        this.pausedTimeWithoutGrowing = 0;
        this.pauseGrowingTime = 200;
        this.barColor = -1442840576;
        this.rimColor = ViewCompat.MEASURED_SIZE_MASK;
        this.barPaint = new Paint();
        this.rimPaint = new Paint();
        this.circleBounds = new RectF();
        this.spinSpeed = 230.0f;
        this.lastTimeAnimated = 0;
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        this.isSpinning = false;
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int viewWidth = (this.circleRadius + getPaddingLeft()) + getPaddingRight();
        int viewHeight = (this.circleRadius + getPaddingTop()) + getPaddingBottom();
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == 1073741824) {
            width = widthSize;
        } else if (widthMode == ExploreByTouchHelper.INVALID_ID) {
            width = Math.min(viewWidth, widthSize);
        } else {
            width = viewWidth;
        }
        if (heightMode == 1073741824 || widthMode == 1073741824) {
            height = heightSize;
        } else if (heightMode == ExploreByTouchHelper.INVALID_ID) {
            height = Math.min(viewHeight, heightSize);
        } else {
            height = viewHeight;
        }
        setMeasuredDimension(width, height);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setupBounds(w, h);
        setupPaints();
        invalidate();
    }

    private void setupPaints() {
        this.barPaint.setColor(this.barColor);
        this.barPaint.setAntiAlias(true);
        this.barPaint.setStyle(Style.STROKE);
        this.barPaint.setStrokeWidth((float) this.barWidth);
        this.rimPaint.setColor(this.rimColor);
        this.rimPaint.setAntiAlias(true);
        this.rimPaint.setStyle(Style.STROKE);
        this.rimPaint.setStrokeWidth((float) this.rimWidth);
    }

    private void setupBounds(int layout_width, int layout_height) {
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        if (this.fillRadius) {
            this.circleBounds = new RectF((float) (this.barWidth + paddingLeft), (float) (this.barWidth + paddingTop), (float) ((layout_width - paddingRight) - this.barWidth), (float) ((layout_height - paddingBottom) - this.barWidth));
            return;
        }
        int circleDiameter = Math.min(Math.min((layout_width - paddingLeft) - paddingRight, (layout_height - paddingBottom) - paddingTop), (this.circleRadius * 2) - (this.barWidth * 2));
        int xOffset = ((((layout_width - paddingLeft) - paddingRight) - circleDiameter) / 2) + paddingLeft;
        int yOffset = ((((layout_height - paddingTop) - paddingBottom) - circleDiameter) / 2) + paddingTop;
        this.circleBounds = new RectF((float) (this.barWidth + xOffset), (float) (this.barWidth + yOffset), (float) ((xOffset + circleDiameter) - this.barWidth), (float) ((yOffset + circleDiameter) - this.barWidth));
    }

    private void parseAttributes(TypedArray a) {
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        this.barWidth = (int) TypedValue.applyDimension(1, (float) this.barWidth, metrics);
        this.rimWidth = (int) TypedValue.applyDimension(1, (float) this.rimWidth, metrics);
        this.circleRadius = (int) TypedValue.applyDimension(1, (float) this.circleRadius, metrics);
        this.circleRadius = (int) a.getDimension(6, (float) this.circleRadius);
        this.fillRadius = a.getBoolean(7, false);
        this.barWidth = (int) a.getDimension(8, (float) this.barWidth);
        this.rimWidth = (int) a.getDimension(3, (float) this.rimWidth);
        this.spinSpeed = a.getFloat(4, this.spinSpeed / 360.0f) * 360.0f;
        this.barSpinCycleTime = (double) a.getInt(5, (int) this.barSpinCycleTime);
        this.barColor = a.getColor(1, this.barColor);
        this.rimColor = a.getColor(2, this.rimColor);
        this.linearProgress = a.getBoolean(9, false);
        if (a.getBoolean(0, false)) {
            spin();
        }
        a.recycle();
    }

    public void setCallback(ProgressCallback progressCallback) {
        this.callback = progressCallback;
        if (!this.isSpinning) {
            runCallback();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.circleBounds, 360.0f, 360.0f, false, this.rimPaint);
        boolean mustInvalidate = false;
        if (this.isSpinning) {
            mustInvalidate = true;
            long deltaTime = SystemClock.uptimeMillis() - this.lastTimeAnimated;
            float deltaNormalized = (((float) deltaTime) * this.spinSpeed) / 1000.0f;
            updateBarLength(deltaTime);
            this.mProgress += deltaNormalized;
            if (this.mProgress > 360.0f) {
                this.mProgress -= 360.0f;
                runCallback(-1.0f);
            }
            this.lastTimeAnimated = SystemClock.uptimeMillis();
            float from = this.mProgress - 90.0f;
            float length = 16.0f + this.barExtraLength;
            if (isInEditMode()) {
                from = 0.0f;
                length = 135.0f;
            }
            canvas.drawArc(this.circleBounds, from, length, false, this.barPaint);
        } else {
            float oldProgress = this.mProgress;
            if (this.mProgress != this.mTargetProgress) {
                mustInvalidate = true;
                this.mProgress = Math.min(this.mProgress + ((((float) (SystemClock.uptimeMillis() - this.lastTimeAnimated)) / 1000.0f) * this.spinSpeed), this.mTargetProgress);
                this.lastTimeAnimated = SystemClock.uptimeMillis();
            }
            if (oldProgress != this.mProgress) {
                runCallback();
            }
            float offset = 0.0f;
            float progress = this.mProgress;
            if (!this.linearProgress) {
                offset = ((float) (1.0d - Math.pow((double) (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - (this.mProgress / 360.0f)), (double) (2.0f * 2.0f)))) * 360.0f;
                progress = ((float) (1.0d - Math.pow((double) (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - (this.mProgress / 360.0f)), (double) 1073741824))) * 360.0f;
            }
            if (isInEditMode()) {
                progress = 360.0f;
            }
            canvas.drawArc(this.circleBounds, offset - 90.0f, progress, false, this.barPaint);
        }
        if (mustInvalidate) {
            invalidate();
        }
    }

    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == GONE) {
            this.lastTimeAnimated = SystemClock.uptimeMillis();
        }
    }

    private void updateBarLength(long deltaTimeInMilliSeconds) {
        if (this.pausedTimeWithoutGrowing >= 200) {
            this.timeStartGrowing += (double) deltaTimeInMilliSeconds;
            if (this.timeStartGrowing > this.barSpinCycleTime) {
                this.timeStartGrowing -= this.barSpinCycleTime;
                this.pausedTimeWithoutGrowing = 0;
                this.barGrowingFromFront = !this.barGrowingFromFront;
            }
            float distance = (((float) Math.cos(((this.timeStartGrowing / this.barSpinCycleTime) + 1.0d) * 3.141592653589793d)) / 2.0f) + 0.5f;
            if (this.barGrowingFromFront) {
                this.barExtraLength = distance * 254.0f;
                return;
            }
            float newLength = 254.0f * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - distance);
            this.mProgress += this.barExtraLength - newLength;
            this.barExtraLength = newLength;
            return;
        }
        this.pausedTimeWithoutGrowing += deltaTimeInMilliSeconds;
    }

    public boolean isSpinning() {
        return this.isSpinning;
    }

    public void resetCount() {
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        invalidate();
    }

    public void stopSpinning() {
        this.isSpinning = false;
        this.mProgress = 0.0f;
        this.mTargetProgress = 0.0f;
        invalidate();
    }

    public void spin() {
        this.lastTimeAnimated = SystemClock.uptimeMillis();
        this.isSpinning = true;
        invalidate();
    }

    private void runCallback(float value) {
        if (this.callback != null) {
            this.callback.onProgressUpdate(value);
        }
    }

    private void runCallback() {
        if (this.callback != null) {
            this.callback.onProgressUpdate(((float) Math.round((this.mProgress * 100.0f) / 360.0f)) / 100.0f);
        }
    }

    public void setProgress(float progress) {
        if (this.isSpinning) {
            this.mProgress = 0.0f;
            this.isSpinning = false;
            runCallback();
        }
        if (progress > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            progress -= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        } else if (progress < 0.0f) {
            progress = 0.0f;
        }
        if (progress != this.mTargetProgress) {
            if (this.mProgress == this.mTargetProgress) {
                this.lastTimeAnimated = SystemClock.uptimeMillis();
            }
            this.mTargetProgress = Math.min(progress * 360.0f, 360.0f);
            invalidate();
        }
    }

    public void setInstantProgress(float progress) {
        if (this.isSpinning) {
            this.mProgress = 0.0f;
            this.isSpinning = false;
        }
        if (progress > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            progress -= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        } else if (progress < 0.0f) {
            progress = 0.0f;
        }
        if (progress != this.mTargetProgress) {
            this.mTargetProgress = Math.min(progress * 360.0f, 360.0f);
            this.mProgress = this.mTargetProgress;
            this.lastTimeAnimated = SystemClock.uptimeMillis();
            invalidate();
        }
    }

    public Parcelable onSaveInstanceState() {
        WheelSavedState ss = new WheelSavedState(super.onSaveInstanceState());
        ss.mProgress = this.mProgress;
        ss.mTargetProgress = this.mTargetProgress;
        ss.isSpinning = this.isSpinning;
        ss.spinSpeed = this.spinSpeed;
        ss.barWidth = this.barWidth;
        ss.barColor = this.barColor;
        ss.rimWidth = this.rimWidth;
        ss.rimColor = this.rimColor;
        ss.circleRadius = this.circleRadius;
        ss.linearProgress = this.linearProgress;
        ss.fillRadius = this.fillRadius;
        return ss;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof WheelSavedState) {
            WheelSavedState ss = (WheelSavedState) state;
            super.onRestoreInstanceState(ss.getSuperState());
            this.mProgress = ss.mProgress;
            this.mTargetProgress = ss.mTargetProgress;
            this.isSpinning = ss.isSpinning;
            this.spinSpeed = ss.spinSpeed;
            this.barWidth = ss.barWidth;
            this.barColor = ss.barColor;
            this.rimWidth = ss.rimWidth;
            this.rimColor = ss.rimColor;
            this.circleRadius = ss.circleRadius;
            this.linearProgress = ss.linearProgress;
            this.fillRadius = ss.fillRadius;
            this.lastTimeAnimated = SystemClock.uptimeMillis();
            return;
        }
        super.onRestoreInstanceState(state);
    }

    public float getProgress() {
        return this.isSpinning ? -1.0f : this.mProgress / 360.0f;
    }

    public void setLinearProgress(boolean isLinear) {
        this.linearProgress = isLinear;
        if (!this.isSpinning) {
            invalidate();
        }
    }

    public int getCircleRadius() {
        return this.circleRadius;
    }

    public void setCircleRadius(int circleRadius) {
        this.circleRadius = circleRadius;
        if (!this.isSpinning) {
            invalidate();
        }
    }

    public int getBarWidth() {
        return this.barWidth;
    }

    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
        if (!this.isSpinning) {
            invalidate();
        }
    }

    public int getBarColor() {
        return this.barColor;
    }

    public void setBarColor(int barColor) {
        this.barColor = barColor;
        setupPaints();
        if (!this.isSpinning) {
            invalidate();
        }
    }

    public int getRimColor() {
        return this.rimColor;
    }

    public void setRimColor(int rimColor) {
        this.rimColor = rimColor;
        setupPaints();
        if (!this.isSpinning) {
            invalidate();
        }
    }

    public float getSpinSpeed() {
        return this.spinSpeed / 360.0f;
    }

    public void setSpinSpeed(float spinSpeed) {
        this.spinSpeed = 360.0f * spinSpeed;
    }

    public int getRimWidth() {
        return this.rimWidth;
    }

    public void setRimWidth(int rimWidth) {
        this.rimWidth = rimWidth;
        if (!this.isSpinning) {
            invalidate();
        }
    }
}

