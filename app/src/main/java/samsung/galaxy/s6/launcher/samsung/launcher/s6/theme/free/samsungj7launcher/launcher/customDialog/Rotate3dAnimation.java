package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customDialog;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/24/2016.
 */
public class Rotate3dAnimation extends Animation {
    public static final int ROLL_BY_X = 0;
    public static final int ROLL_BY_Y = 1;
    public static final int ROLL_BY_Z = 2;
    private Camera mCamera;
    private float mFromDegrees;
    private float mPivotX;
    private int mPivotXType;
    private float mPivotXValue;
    private float mPivotY;
    private int mPivotYType;
    private float mPivotYValue;
    private int mRollType;
    private float mToDegrees;

    protected static class Description {
        public int type;
        public float value;

        protected Description() {
        }
    }

    Description parseValue(TypedValue value) {
        int i = ROLL_BY_Y;
        Description d = new Description();
        if (value == null) {
            d.type = ROLL_BY_X;
            d.value = 0.0f;
        } else {
            if (value.type == 6) {
                if ((value.data & 15) == ROLL_BY_Y) {
                    i = ROLL_BY_Z;
                }
                d.type = i;
                d.value = TypedValue.complexToFloat(value.data);
            } else if (value.type == 4) {
                d.type = ROLL_BY_X;
                d.value = value.getFloat();
            } else if (value.type >= 16 && value.type <= 31) {
                d.type = ROLL_BY_X;
                d.value = (float) value.data;
            }
            return d;
        }
        d.type = ROLL_BY_X;
        d.value = 0.0f;
        return d;
    }

    public Rotate3dAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mPivotXType = ROLL_BY_X;
        this.mPivotYType = ROLL_BY_X;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Rotate3dAnimation);
        this.mFromDegrees = a.getFloat(ROLL_BY_Y, 0.0f);
        this.mToDegrees = a.getFloat(ROLL_BY_Z, 0.0f);
        this.mRollType = a.getInt(ROLL_BY_X, ROLL_BY_X);
        Description d = parseValue(a.peekValue(3));
        this.mPivotXType = d.type;
        this.mPivotXValue = d.value;
        d = parseValue(a.peekValue(4));
        this.mPivotYType = d.type;
        this.mPivotYValue = d.value;
        a.recycle();
        initializePivotPoint();
    }

    public Rotate3dAnimation(int rollType, float fromDegrees, float toDegrees) {
        this.mPivotXType = ROLL_BY_X;
        this.mPivotYType = ROLL_BY_X;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        this.mRollType = rollType;
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mPivotX = 0.0f;
        this.mPivotY = 0.0f;
    }

    public Rotate3dAnimation(int rollType, float fromDegrees, float toDegrees, float pivotX, float pivotY) {
        this.mPivotXType = ROLL_BY_X;
        this.mPivotYType = ROLL_BY_X;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        this.mRollType = rollType;
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mPivotXType = ROLL_BY_X;
        this.mPivotYType = ROLL_BY_X;
        this.mPivotXValue = pivotX;
        this.mPivotYValue = pivotY;
        initializePivotPoint();
    }

    public Rotate3dAnimation(int rollType, float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        this.mPivotXType = ROLL_BY_X;
        this.mPivotYType = ROLL_BY_X;
        this.mPivotXValue = 0.0f;
        this.mPivotYValue = 0.0f;
        this.mRollType = rollType;
        this.mFromDegrees = fromDegrees;
        this.mToDegrees = toDegrees;
        this.mPivotXValue = pivotXValue;
        this.mPivotXType = pivotXType;
        this.mPivotYValue = pivotYValue;
        this.mPivotYType = pivotYType;
        initializePivotPoint();
    }

    private void initializePivotPoint() {
        if (this.mPivotXType == 0) {
            this.mPivotX = this.mPivotXValue;
        }
        if (this.mPivotYType == 0) {
            this.mPivotY = this.mPivotYValue;
        }
    }

    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.mCamera = new Camera();
        this.mPivotX = resolveSize(this.mPivotXType, this.mPivotXValue, width, parentWidth);
        this.mPivotY = resolveSize(this.mPivotYType, this.mPivotYValue, height, parentHeight);
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float fromDegrees = this.mFromDegrees;
        float degrees = fromDegrees + ((this.mToDegrees - fromDegrees) * interpolatedTime);
        Matrix matrix = t.getMatrix();
        this.mCamera.save();
        switch (this.mRollType) {
            case ROLL_BY_X /*0*/:
                this.mCamera.rotateX(degrees);
                break;
            case ROLL_BY_Y /*1*/:
                this.mCamera.rotateY(degrees);
                break;
            case ROLL_BY_Z /*2*/:
                this.mCamera.rotateZ(degrees);
                break;
        }
        this.mCamera.getMatrix(matrix);
        this.mCamera.restore();
        matrix.preTranslate(-this.mPivotX, -this.mPivotY);
        matrix.postTranslate(this.mPivotX, this.mPivotY);
    }
}

