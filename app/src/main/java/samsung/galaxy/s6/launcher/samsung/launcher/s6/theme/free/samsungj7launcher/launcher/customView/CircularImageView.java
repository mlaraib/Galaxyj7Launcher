package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ExploreByTouchHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.google.android.gms.common.ConnectionResult;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/24/2016.
 */
public class CircularImageView extends ImageView {
    private int borderWidth;
    private int canvasSize;
    private boolean hasBorder;
    private boolean hasSelector;
    private Bitmap image;
    private boolean isSelected;
    private Paint paint;
    private Paint paintBorder;
    private Paint paintSelectorBorder;
    private ColorFilter selectorFilter;
    private int selectorStrokeWidth;
    private BitmapShader shader;

    public CircularImageView(Context context) {
        this(context, null);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.circularImageViewStyle);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paintBorder = new Paint();
        this.paintBorder.setAntiAlias(true);
        this.paintSelectorBorder = new Paint();
        this.paintSelectorBorder.setAntiAlias(true);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.CircularImageView, defStyle, 0);
        this.hasBorder = attributes.getBoolean(0, true);
        this.hasSelector = attributes.getBoolean(3, false);
        if (this.hasBorder) {
            setBorderWidth(attributes.getDimensionPixelOffset(2, (int) ((context.getResources().getDisplayMetrics().density * 2.0f) + 0.5f)));
            setBorderColor(attributes.getColor(1, -1));
        }
        if (this.hasSelector) {
            int defaultSelectorSize = (int) ((context.getResources().getDisplayMetrics().density * 2.0f) + 0.5f);
            setSelectorColor(attributes.getColor(4, 0));
            setSelectorStrokeWidth(attributes.getDimensionPixelOffset(6, defaultSelectorSize));
            setSelectorStrokeColor(attributes.getColor(5, -16776961));
        }
        if (attributes.getBoolean(7, false)) {
            addShadow();
        }
        attributes.recycle();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        requestLayout();
        invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (this.paintBorder != null) {
            this.paintBorder.setColor(borderColor);
        }
        invalidate();
    }

    public void setSelectorColor(int selectorColor) {
        this.selectorFilter = new PorterDuffColorFilter(selectorColor, Mode.SRC_ATOP);
        invalidate();
    }

    public void setSelectorStrokeWidth(int selectorStrokeWidth) {
        this.selectorStrokeWidth = selectorStrokeWidth;
        requestLayout();
        invalidate();
    }

    public void setSelectorStrokeColor(int selectorStrokeColor) {
        if (this.paintSelectorBorder != null) {
            this.paintSelectorBorder.setColor(selectorStrokeColor);
        }
        invalidate();
    }

    public void addShadow() {
        if (VERSION.SDK_INT >= 11) {
            setLayerType(1, this.paintBorder);
        }
        this.paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, ViewCompat.MEASURED_STATE_MASK);
    }

    public void onDraw(Canvas canvas) {
        if (this.image != null && this.image.getHeight() != 0 && this.image.getWidth() != 0) {
            int oldCanvasSize = this.canvasSize;
            this.canvasSize = canvas.getWidth();
            if (canvas.getHeight() < this.canvasSize) {
                this.canvasSize = canvas.getHeight();
            }
            if (oldCanvasSize != this.canvasSize) {
                refreshBitmapShader();
            }
            this.paint.setShader(this.shader);
            int outerWidth = 0;
            int center = this.canvasSize / 2;
            if (this.hasSelector && this.isSelected) {
                outerWidth = this.selectorStrokeWidth;
                center = (this.canvasSize - (outerWidth * 2)) / 2;
                this.paint.setColorFilter(this.selectorFilter);
                canvas.drawCircle((float) (center + outerWidth), (float) (center + outerWidth), ((float) (((this.canvasSize - (outerWidth * 2)) / 2) + outerWidth)) - 4.0f, this.paintSelectorBorder);
            } else if (this.hasBorder) {
                outerWidth = this.borderWidth;
                center = (this.canvasSize - (outerWidth * 2)) / 2;
                this.paint.setColorFilter(null);
                canvas.drawCircle((float) (center + outerWidth), (float) (center + outerWidth), ((float) (((this.canvasSize - (outerWidth * 2)) / 2) + outerWidth)) - 4.0f, this.paintBorder);
            } else {
                this.paint.setColorFilter(null);
            }
            canvas.drawCircle((float) (center + outerWidth), (float) (center + outerWidth), ((float) ((this.canvasSize - (outerWidth * 2)) / 2)) - 4.0f, this.paint);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isClickable()) {
            switch (event.getAction()) {
                case ConnectionResult.SUCCESS /*0*/:
                    this.isSelected = true;
                    break;
                case ConnectionResult.SERVICE_MISSING /*1*/:
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                case ConnectionResult.INTERNAL_ERROR /*8*/:
                    this.isSelected = false;
                    break;
            }
            invalidate();
            return super.dispatchTouchEvent(event);
        }
        this.isSelected = false;
        return super.onTouchEvent(event);
    }

    public void invalidate(Rect dirty) {
        super.invalidate(dirty);
        this.image = drawableToBitmap(getDrawable());
        if (this.shader != null || this.canvasSize > 0) {
            refreshBitmapShader();
        }
    }

    public void invalidate(int l, int t, int r, int b) {
        super.invalidate(l, t, r, b);
        this.image = drawableToBitmap(getDrawable());
        if (this.shader != null || this.canvasSize > 0) {
            refreshBitmapShader();
        }
    }

    public void invalidate() {
        super.invalidate();
        this.image = drawableToBitmap(getDrawable());
        if (this.shader != null || this.canvasSize > 0) {
            refreshBitmapShader();
        }
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == 1073741824) {
            return specSize;
        }
        if (specMode == ExploreByTouchHelper.INVALID_ID) {
            return specSize;
        }
        return this.canvasSize;
    }

    private int measureHeight(int measureSpecHeight) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);
        if (specMode == 1073741824) {
            result = specSize;
        } else if (specMode == ExploreByTouchHelper.INVALID_ID) {
            result = specSize;
        } else {
            result = this.canvasSize;
        }
        return result + 2;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public void refreshBitmapShader() {
        this.shader = new BitmapShader(Bitmap.createScaledBitmap(this.image, this.canvasSize, this.canvasSize, false), TileMode.CLAMP, TileMode.CLAMP);
    }

    public boolean isSelected() {
        return this.isSelected;
    }
}
