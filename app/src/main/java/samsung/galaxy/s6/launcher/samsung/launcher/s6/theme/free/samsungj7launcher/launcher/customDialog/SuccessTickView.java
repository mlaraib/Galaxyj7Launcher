package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customDialog;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import com.android.volley.DefaultRetryPolicy;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/24/2016.
 */
public class SuccessTickView extends View {
    private final float CONST_LEFT_RECT_W;
    private final float CONST_RADIUS;
    private final float CONST_RECT_WEIGHT;
    private final float CONST_RIGHT_RECT_W;
    private final float MAX_RIGHT_RECT_W;
    private final float MIN_LEFT_RECT_W;
    private float mDensity;
    private boolean mLeftRectGrowMode;
    private float mLeftRectWidth;
    private float mMaxLeftRectWidth;
    private Paint mPaint;
    private float mRightRectWidth;

    /* renamed from: launcher.customDialog.SuccessTickView.1 */
    class C04031 extends Animation {
        C04031() {
        }

        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            if (0.54d < ((double) interpolatedTime) && 0.7d >= ((double) interpolatedTime)) {
                SuccessTickView.this.mLeftRectGrowMode = true;
                SuccessTickView.this.mLeftRectWidth = SuccessTickView.this.mMaxLeftRectWidth * ((interpolatedTime - 0.54f) / 0.16f);
                if (0.65d < ((double) interpolatedTime)) {
                    SuccessTickView.this.mRightRectWidth = SuccessTickView.this.MAX_RIGHT_RECT_W * ((interpolatedTime - 0.65f) / 0.19f);
                }
                SuccessTickView.this.invalidate();
            } else if (0.7d < ((double) interpolatedTime) && 0.84d >= ((double) interpolatedTime)) {
                SuccessTickView.this.mLeftRectGrowMode = false;
                SuccessTickView.this.mLeftRectWidth = SuccessTickView.this.mMaxLeftRectWidth * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - ((interpolatedTime - 0.7f) / 0.14f));
                SuccessTickView.this.mLeftRectWidth = SuccessTickView.this.mLeftRectWidth < SuccessTickView.this.MIN_LEFT_RECT_W ? SuccessTickView.this.MIN_LEFT_RECT_W : SuccessTickView.this.mLeftRectWidth;
                SuccessTickView.this.mRightRectWidth = SuccessTickView.this.MAX_RIGHT_RECT_W * ((interpolatedTime - 0.65f) / 0.19f);
                SuccessTickView.this.invalidate();
            } else if (0.84d < ((double) interpolatedTime) && DefaultRetryPolicy.DEFAULT_BACKOFF_MULT >= interpolatedTime) {
                SuccessTickView.this.mLeftRectGrowMode = false;
                SuccessTickView.this.mLeftRectWidth = SuccessTickView.this.MIN_LEFT_RECT_W + ((SuccessTickView.this.CONST_LEFT_RECT_W - SuccessTickView.this.MIN_LEFT_RECT_W) * ((interpolatedTime - 0.84f) / 0.16f));
                SuccessTickView.this.mRightRectWidth = SuccessTickView.this.CONST_RIGHT_RECT_W + ((SuccessTickView.this.MAX_RIGHT_RECT_W - SuccessTickView.this.CONST_RIGHT_RECT_W) * (DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - ((interpolatedTime - 0.84f) / 0.16f)));
                SuccessTickView.this.invalidate();
            }
        }
    }

    public SuccessTickView(Context context) {
        super(context);
        this.mDensity = -1.0f;
        this.CONST_RADIUS = dip2px(1.2f);
        this.CONST_RECT_WEIGHT = dip2px(3.0f);
        this.CONST_LEFT_RECT_W = dip2px(15.0f);
        this.CONST_RIGHT_RECT_W = dip2px(25.0f);
        this.MIN_LEFT_RECT_W = dip2px(3.3f);
        this.MAX_RIGHT_RECT_W = this.CONST_RIGHT_RECT_W + dip2px(6.7f);
        init();
    }

    public SuccessTickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mDensity = -1.0f;
        this.CONST_RADIUS = dip2px(1.2f);
        this.CONST_RECT_WEIGHT = dip2px(3.0f);
        this.CONST_LEFT_RECT_W = dip2px(15.0f);
        this.CONST_RIGHT_RECT_W = dip2px(25.0f);
        this.MIN_LEFT_RECT_W = dip2px(3.3f);
        this.MAX_RIGHT_RECT_W = this.CONST_RIGHT_RECT_W + dip2px(6.7f);
        init();
    }

    private void init() {
        this.mPaint = new Paint();
        this.mPaint.setColor(getResources().getColor(R.color.success_stroke_color));
        this.mLeftRectWidth = this.CONST_LEFT_RECT_W;
        this.mRightRectWidth = this.CONST_RIGHT_RECT_W;
        this.mLeftRectGrowMode = false;
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
        int totalW = getWidth();
        int totalH = getHeight();
        canvas.rotate(45.0f, (float) (totalW / 2), (float) (totalH / 2));
        totalW = (int) (((double) totalW) / 1.2d);
        totalH = (int) (((double) totalH) / 1.4d);
        this.mMaxLeftRectWidth = (((((float) totalW) + this.CONST_LEFT_RECT_W) / 2.0f) + this.CONST_RECT_WEIGHT) - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        RectF leftRect = new RectF();
        if (this.mLeftRectGrowMode) {
            leftRect.left = 0.0f;
            leftRect.right = leftRect.left + this.mLeftRectWidth;
            leftRect.top = (((float) totalH) + this.CONST_RIGHT_RECT_W) / 2.0f;
            leftRect.bottom = leftRect.top + this.CONST_RECT_WEIGHT;
        } else {
            leftRect.right = (((((float) totalW) + this.CONST_LEFT_RECT_W) / 2.0f) + this.CONST_RECT_WEIGHT) - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
            leftRect.left = leftRect.right - this.mLeftRectWidth;
            leftRect.top = (((float) totalH) + this.CONST_RIGHT_RECT_W) / 2.0f;
            leftRect.bottom = leftRect.top + this.CONST_RECT_WEIGHT;
        }
        canvas.drawRoundRect(leftRect, this.CONST_RADIUS, this.CONST_RADIUS, this.mPaint);
        RectF rightRect = new RectF();
        rightRect.bottom = (((((float) totalH) + this.CONST_RIGHT_RECT_W) / 2.0f) + this.CONST_RECT_WEIGHT) - DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        rightRect.left = (((float) totalW) + this.CONST_LEFT_RECT_W) / 2.0f;
        rightRect.right = rightRect.left + this.CONST_RECT_WEIGHT;
        rightRect.top = rightRect.bottom - this.mRightRectWidth;
        canvas.drawRoundRect(rightRect, this.CONST_RADIUS, this.CONST_RADIUS, this.mPaint);
    }

    public float dip2px(float dpValue) {
        if (this.mDensity == -1.0f) {
            this.mDensity = getResources().getDisplayMetrics().density;
        }
        return (this.mDensity * dpValue) + 0.5f;
    }

    public void startTickAnim() {
        this.mLeftRectWidth = 0.0f;
        this.mRightRectWidth = 0.0f;
        invalidate();
        Animation tickAnim = new C04031();
        tickAnim.setDuration(750);
        tickAnim.setStartOffset(100);
        startAnimation(tickAnim);
    }
}
