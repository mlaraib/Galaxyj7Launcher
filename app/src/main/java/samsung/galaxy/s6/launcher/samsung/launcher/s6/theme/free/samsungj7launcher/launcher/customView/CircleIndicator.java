package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import com.android.volley.DefaultRetryPolicy;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/24/2016.
 */
public class CircleIndicator extends LinearLayout implements OnPageChangeListener {
    private static final int DEFAULT_INDICATOR_WIDTH = 5;
    private Animator mAnimationIn;
    private Animator mAnimationOut;
    private int mAnimatorResId;
    private int mAnimatorReverseResId;
    private int mCurrentPosition;
    private int mIndicatorBackgroundResId;
    private int mIndicatorHeight;
    private int mIndicatorMargin;
    private int mIndicatorUnselectedBackgroundResId;
    private int mIndicatorWidth;
    private ViewPager mViewpager;

    private class ReverseInterpolator implements Interpolator {
        private ReverseInterpolator() {
        }

        public float getInterpolation(float value) {
            return Math.abs(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT - value);
        }
    }

    public CircleIndicator(Context context) {
        super(context);
        this.mIndicatorMargin = -1;
        this.mIndicatorWidth = -1;
        this.mIndicatorHeight = -1;
        this.mAnimatorResId = R.animator.scale_with_alpha;
        this.mAnimatorReverseResId = 0;
        this.mIndicatorBackgroundResId = R.drawable.white_radius;
        this.mIndicatorUnselectedBackgroundResId = R.drawable.white_radius;
        this.mCurrentPosition = 0;
        init(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mIndicatorMargin = -1;
        this.mIndicatorWidth = -1;
        this.mIndicatorHeight = -1;
        this.mAnimatorResId = R.animator.scale_with_alpha;
        this.mAnimatorReverseResId = 0;
        this.mIndicatorBackgroundResId = R.drawable.white_radius;
        this.mIndicatorUnselectedBackgroundResId = R.drawable.white_radius;
        this.mCurrentPosition = 0;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setGravity(17);
        handleTypedArray(context, attrs);
        checkIndicatorConfig(context);
    }

    private void handleTypedArray(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleIndicator);
            this.mIndicatorWidth = typedArray.getDimensionPixelSize(0, -1);
            this.mIndicatorHeight = typedArray.getDimensionPixelSize(1, -1);
            this.mIndicatorMargin = typedArray.getDimensionPixelSize(2, -1);
            this.mAnimatorResId = typedArray.getResourceId(3, R.animator.scale_with_alpha);
            this.mAnimatorReverseResId = typedArray.getResourceId(4, 0);
            this.mIndicatorBackgroundResId = typedArray.getResourceId(DEFAULT_INDICATOR_WIDTH, R.drawable.white_radius);
            this.mIndicatorUnselectedBackgroundResId = typedArray.getResourceId(6, this.mIndicatorBackgroundResId);
            typedArray.recycle();
        }
    }

    public void configureIndicator(int indicatorWidth, int indicatorHeight, int indicatorMargin) {
        configureIndicator(indicatorWidth, indicatorHeight, indicatorMargin, R.animator.scale_with_alpha, 0, R.drawable.white_radius, R.drawable.white_radius);
    }

    public void configureIndicator(int indicatorWidth, int indicatorHeight, int indicatorMargin, @AnimatorRes int animatorId, @AnimatorRes int animatorReverseId, @DrawableRes int indicatorBackgroundId, @DrawableRes int indicatorUnselectedBackgroundId) {
        this.mIndicatorWidth = indicatorWidth;
        this.mIndicatorHeight = indicatorHeight;
        this.mIndicatorMargin = indicatorMargin;
        this.mAnimatorResId = animatorId;
        this.mAnimatorReverseResId = animatorReverseId;
        this.mIndicatorBackgroundResId = indicatorBackgroundId;
        this.mIndicatorUnselectedBackgroundResId = indicatorUnselectedBackgroundId;
        checkIndicatorConfig(getContext());
    }

    @TargetApi(11)
    private void checkIndicatorConfig(Context context) {
        this.mIndicatorWidth = this.mIndicatorWidth < 0 ? dip2px(5.0f) : this.mIndicatorWidth;
        this.mIndicatorHeight = this.mIndicatorHeight < 0 ? dip2px(5.0f) : this.mIndicatorHeight;
        this.mIndicatorMargin = this.mIndicatorMargin < 0 ? dip2px(5.0f) : this.mIndicatorMargin;
        this.mAnimatorResId = this.mAnimatorResId == 0 ? R.animator.scale_with_alpha : this.mAnimatorResId;
        this.mAnimationOut = AnimatorInflater.loadAnimator(context, this.mAnimatorResId);
        if (this.mAnimatorReverseResId == 0) {
            this.mAnimationIn = AnimatorInflater.loadAnimator(context, this.mAnimatorResId);
            this.mAnimationIn.setInterpolator(new ReverseInterpolator());
        } else {
            this.mAnimationIn = AnimatorInflater.loadAnimator(context, this.mAnimatorReverseResId);
        }
        this.mIndicatorBackgroundResId = this.mIndicatorBackgroundResId == 0 ? R.drawable.white_radius : this.mIndicatorBackgroundResId;
        this.mIndicatorUnselectedBackgroundResId = this.mIndicatorUnselectedBackgroundResId == 0 ? this.mIndicatorBackgroundResId : this.mIndicatorUnselectedBackgroundResId;
    }

    public void setViewPager(ViewPager viewPager) {
        this.mViewpager = viewPager;
        this.mCurrentPosition = this.mViewpager.getCurrentItem();
        createIndicators(viewPager);
        this.mViewpager.removeOnPageChangeListener(this);
        this.mViewpager.addOnPageChangeListener(this);
        onPageSelected(this.mCurrentPosition);
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.mViewpager == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        }
        this.mViewpager.removeOnPageChangeListener(onPageChangeListener);
        this.mViewpager.addOnPageChangeListener(onPageChangeListener);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @TargetApi(11)
    public void onPageSelected(int position) {
        if (this.mViewpager.getAdapter() != null && this.mViewpager.getAdapter().getCount() > 0) {
            if (this.mAnimationIn.isRunning()) {
                this.mAnimationIn.end();
            }
            if (this.mAnimationOut.isRunning()) {
                this.mAnimationOut.end();
            }
            View currentIndicator = getChildAt(this.mCurrentPosition);
            currentIndicator.setBackgroundResource(this.mIndicatorUnselectedBackgroundResId);
            this.mAnimationIn.setTarget(currentIndicator);
            this.mAnimationIn.start();
            View selectedIndicator = getChildAt(position);
            selectedIndicator.setBackgroundResource(this.mIndicatorBackgroundResId);
            this.mAnimationOut.setTarget(selectedIndicator);
            this.mAnimationOut.start();
            this.mCurrentPosition = position;
        }
    }

    public void onPageScrollStateChanged(int state) {
    }

    private void createIndicators(ViewPager viewPager) {
        removeAllViews();
        if (viewPager.getAdapter() != null) {
            int count = viewPager.getAdapter().getCount();
            if (count > 0) {
                addIndicator(this.mIndicatorBackgroundResId, this.mAnimationOut);
                for (int i = 1; i < count; i++) {
                    addIndicator(this.mIndicatorUnselectedBackgroundResId, this.mAnimationIn);
                }
            }
        }
    }

    @TargetApi(11)
    private void addIndicator(@DrawableRes int backgroundDrawableId, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
        }
        View Indicator = new View(getContext());
        Indicator.setBackgroundResource(backgroundDrawableId);
        addView(Indicator, this.mIndicatorWidth, this.mIndicatorHeight);
        LayoutParams lp = (LayoutParams) Indicator.getLayoutParams();
        lp.leftMargin = this.mIndicatorMargin;
        lp.rightMargin = this.mIndicatorMargin;
        Indicator.setLayoutParams(lp);
        animator.setTarget(Indicator);
        animator.start();
    }

    public int dip2px(float dpValue) {
        return (int) ((dpValue * getResources().getDisplayMetrics().density) + 0.5f);
    }
}

