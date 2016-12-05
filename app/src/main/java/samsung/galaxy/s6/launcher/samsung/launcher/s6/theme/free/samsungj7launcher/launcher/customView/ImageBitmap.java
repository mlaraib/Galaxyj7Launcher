package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customView;



import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.util.TypedValue;
import com.google.android.gms.common.ConnectionResult;
import java.util.Calendar;
import java.util.Locale;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.BuildConfig;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Settings;
import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants.Utils;


/**
 * Created by iamla on 11/23/2016.
 */
public class ImageBitmap {
    public Bitmap getThumb(Context context, String appName, Drawable icon, boolean isEditMode, boolean isSystemPackage) {
        Bitmap bitmapIcon = drawableToBitmap(icon);
        switch (Settings.getTheme(context)) {
            case ConnectionResult.SUCCESS /*0*/:
                bitmapIcon = drawableToBitmap(icon);
                break;
            case ConnectionResult.SERVICE_MISSING /*1*/:
                bitmapIcon = getCircleBitmap(bitmapIcon);
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                bitmapIcon = getRoundedCornerBitmap(bitmapIcon, (int) getPixelFromD(context, 8));
                break;
        }
        bitmapIcon = Bitmap.createScaledBitmap(bitmapIcon, (int) getPixelFromD(context, 46), (int) getPixelFromD(context, 46), true);
        Bitmap bmp = Bitmap.createBitmap((int) getPixelFromD(context, 70), (int) getPixelFromD(context, 70), Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setTextSize((float) ((int) getPixelFromD(context, 12)));
        paint.setFlags(1);
        paint.setTypeface(Utils.getFont(context));
        canvas.drawRect(new Rect(0, 0, (int) getPixelFromD(context, 70), (int) getPixelFromD(context, 70)), paint);
        paint.setColor(Settings.getFontColor(context));
        paint.setTextAlign(Align.CENTER);
        canvas.drawBitmap(bitmapIcon, (float) ((int) getPixelFromD(context, 12)), (float) ((int) getPixelFromD(context, 6)), paint);
        if (isEditMode && !isSystemPackage) {
            canvas.drawBitmap(getCrossBitmap(context), (float) ((int) getPixelFromD(context, 45)), (float) ((int) getPixelFromD(context, 2)), null);
        }
        if (appName.length() >= 11) {
            appName = appName.substring(0, 9) + "..";
        }
        canvas.drawText(appName, (float) ((int) getPixelFromD(context, 35)), (float) ((int) getPixelFromD(context, 67)), paint);
        if (appName.equalsIgnoreCase("Calendar") || appName.equalsIgnoreCase("S Planner")) {
            Calendar c = Calendar.getInstance();
            int date = c.get(Calendar.DATE);
            paint.setTextSize((float) ((int) getPixelFromD(context, 26)));
            paint.setColor(SupportMenu.CATEGORY_MASK);
            canvas.drawText(date + BuildConfig.FLAVOR, (float) ((int) getPixelFromD(context, 35)), (float) ((int) getPixelFromD(context, 32)), paint);
            String day = c.getDisplayName(Calendar.DATE, Calendar.SHORT, Locale.getDefault());
            paint.setTextSize((float) ((int) getPixelFromD(context, 8)));
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            canvas.drawText(day, (float) ((int) getPixelFromD(context, 35)), (float) ((int) getPixelFromD(context, 40)), paint);
        }
        return bmp;
    }

    private Bitmap getCrossBitmap(Context context) {
        return Bitmap.createScaledBitmap(drawableToBitmap(context.getResources().getDrawable(R.mipmap.ic_launcher)), (int) getPixelFromD(context, 20), (int) getPixelFromD(context, 20), true);
    }

    private float getPixelFromD(Context context, int dp) {
        return TypedValue.applyDimension(1, (float) dp, context.getResources().getDisplayMetrics());
    }

    private Bitmap getCircleBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(SupportMenu.CATEGORY_MASK);
        canvas.drawOval(rectF, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(20, 20, Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = (float) pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public Bitmap getThumb(Context context, String appName, Drawable icon, int index) {
        Bitmap bitmapIcon = drawableToBitmap(icon);
        switch (index) {
            case ConnectionResult.SUCCESS /*0*/:
                bitmapIcon = drawableToBitmap(icon);
                break;
            case ConnectionResult.SERVICE_MISSING /*1*/:
                bitmapIcon = getCircleBitmap(bitmapIcon);
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                bitmapIcon = getRoundedCornerBitmap(bitmapIcon, (int) getPixelFromD(context, 12));
                break;
        }
        bitmapIcon = Bitmap.createScaledBitmap(bitmapIcon, (int) getPixelFromD(context, 40), (int) getPixelFromD(context, 40), true);
        Bitmap bmp = Bitmap.createBitmap((int) getPixelFromD(context, 70), (int) getPixelFromD(context, 70), Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setTextSize((float) ((int) getPixelFromD(context, 12)));
        paint.setFlags(1);
        paint.setTypeface(Utils.getFont(context));
        canvas.drawRect(new Rect(0, 0, (int) getPixelFromD(context, 70), (int) getPixelFromD(context, 70)), paint);
        paint.setColor(Settings.getFontColor(context));
        paint.setTextAlign(Align.CENTER);
        canvas.drawBitmap(bitmapIcon, (float) ((int) getPixelFromD(context, 15)), (float) ((int) getPixelFromD(context, 9)), paint);
        if (appName.length() > 11) {
            appName = appName.substring(0, 9) + "..";
        }
        canvas.drawText(appName, (float) ((int) getPixelFromD(context, 35)), (float) ((int) getPixelFromD(context, 67)), paint);
        return bmp;
    }

    public Bitmap getBottomIconBitmap(Context context, Drawable icon) {
        Bitmap bitmapIcon = drawableToBitmap(icon);
        switch (Settings.getTheme(context)) {
            case ConnectionResult.SUCCESS /*0*/:
                bitmapIcon = drawableToBitmap(icon);
                break;
            case ConnectionResult.SERVICE_MISSING /*1*/:
                bitmapIcon = getCircleBitmap(bitmapIcon);
                break;
            case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED /*2*/:
                bitmapIcon = getRoundedCornerBitmap(bitmapIcon, (int) getPixelFromD(context, 15));
                break;
        }
        bitmapIcon = Bitmap.createScaledBitmap(bitmapIcon, (int) getPixelFromD(context, 80), (int) getPixelFromD(context, 80), true);
        Bitmap bmp = Bitmap.createBitmap((int) getPixelFromD(context, 95), (int) getPixelFromD(context, 95), Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColor(0);
        paint.setTextSize((float) ((int) getPixelFromD(context, 12)));
        paint.setFlags(1);
        paint.setTypeface(Utils.getFont(context));
        canvas.drawRect(new Rect(0, 0, (int) getPixelFromD(context, 95), (int) getPixelFromD(context, 95)), paint);
        paint.setColor(Settings.getFontColor(context));
        paint.setTextAlign(Align.CENTER);
        canvas.drawBitmap(bitmapIcon, (float) ((int) getPixelFromD(context, 10)), (float) ((int) getPixelFromD(context, 7)), paint);
        return bmp;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.graphics.Bitmap createIcon(android.content.Context r19, java.lang.String r20, android.graphics.drawable.Drawable r21) {
        /*
        r18 = this;
        r0 = r18;
        r1 = r21;
        r3 = r0.drawableToBitmap(r1);
        r13 = launcher.constants.Settings.getTheme(r19);
        switch(r13) {
            case 0: goto L_0x01fc;
            case 1: goto L_0x0206;
            case 2: goto L_0x020e;
            default: goto L_0x000f;
        };
    L_0x000f:
        r13 = 46;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r14 = 46;
        r0 = r18;
        r1 = r19;
        r14 = r0.getPixelFromD(r1, r14);
        r14 = (int) r14;
        r15 = 1;
        r3 = android.graphics.Bitmap.createScaledBitmap(r3, r13, r14, r15);
        r13 = 70;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r14 = 70;
        r0 = r18;
        r1 = r19;
        r14 = r0.getPixelFromD(r1, r14);
        r14 = (int) r14;
        r15 = android.graphics.Bitmap.Config.ARGB_8888;
        r4 = android.graphics.Bitmap.createBitmap(r13, r14, r15);
        r6 = new android.graphics.Canvas;
        r6.<init>(r4);
        r12 = new android.graphics.Paint;
        r12.<init>();
        r13 = 0;
        r12.setColor(r13);
        r13 = 12;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r13 = (float) r13;
        r12.setTextSize(r13);
        r13 = 1;
        r12.setFlags(r13);
        r13 = launcher.constants.Utils.getFont(r19);
        r12.setTypeface(r13);
        r13 = new android.graphics.Rect;
        r14 = 0;
        r15 = 0;
        r16 = 70;
        r0 = r18;
        r1 = r19;
        r2 = r16;
        r16 = r0.getPixelFromD(r1, r2);
        r0 = r16;
        r0 = (int) r0;
        r16 = r0;
        r17 = 70;
        r0 = r18;
        r1 = r19;
        r2 = r17;
        r17 = r0.getPixelFromD(r1, r2);
        r0 = r17;
        r0 = (int) r0;
        r17 = r0;
        r13.<init>(r14, r15, r16, r17);
        r6.drawRect(r13, r12);
        r13 = launcher.constants.Settings.getFontColor(r19);
        r12.setColor(r13);
        r13 = android.graphics.Paint.Align.CENTER;
        r12.setTextAlign(r13);
        r13 = 12;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r13 = (float) r13;
        r14 = 6;
        r0 = r18;
        r1 = r19;
        r14 = r0.getPixelFromD(r1, r14);
        r14 = (int) r14;
        r14 = (float) r14;
        r6.drawBitmap(r3, r13, r14, r12);
        r13 = r20.length();
        r14 = 11;
        if (r13 <= r14) goto L_0x00e4;
    L_0x00c8:
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r14 = 0;
        r15 = 9;
        r0 = r20;
        r14 = r0.substring(r14, r15);
        r13 = r13.append(r14);
        r14 = "..";
        r13 = r13.append(r14);
        r20 = r13.toString();
    L_0x00e4:
        r13 = 35;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r13 = (float) r13;
        r14 = 67;
        r0 = r18;
        r1 = r19;
        r14 = r0.getPixelFromD(r1, r14);
        r14 = (int) r14;
        r14 = (float) r14;
        r0 = r20;
        r6.drawText(r0, r13, r14, r12);
        r13 = "Calendar";
        r0 = r20;
        r13 = r0.equalsIgnoreCase(r13);
        if (r13 != 0) goto L_0x0115;
    L_0x010b:
        r13 = "S Planner";
        r0 = r20;
        r13 = r0.equalsIgnoreCase(r13);
        if (r13 == 0) goto L_0x0199;
    L_0x0115:
        r5 = java.util.Calendar.getInstance();
        r13 = 5;
        r7 = r5.get(r13);
        r13 = 26;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r13 = (float) r13;
        r12.setTextSize(r13);
        r13 = -65536; // 0xffffffffffff0000 float:NaN double:NaN;
        r12.setColor(r13);
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r13 = r13.append(r7);
        r14 = "";
        r13 = r13.append(r14);
        r13 = r13.toString();
        r14 = 35;
        r0 = r18;
        r1 = r19;
        r14 = r0.getPixelFromD(r1, r14);
        r14 = (int) r14;
        r14 = (float) r14;
        r15 = 32;
        r0 = r18;
        r1 = r19;
        r15 = r0.getPixelFromD(r1, r15);
        r15 = (int) r15;
        r15 = (float) r15;
        r6.drawText(r13, r14, r15, r12);
        r13 = 7;
        r14 = 2;
        r15 = java.util.Locale.getDefault();
        r8 = r5.getDisplayName(r13, r14, r15);
        r13 = 8;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r13 = (float) r13;
        r12.setTextSize(r13);
        r13 = -16777216; // 0xffffffffff000000 float:-1.7014118E38 double:NaN;
        r12.setColor(r13);
        r13 = 35;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r13 = (float) r13;
        r14 = 44;
        r0 = r18;
        r1 = r19;
        r14 = r0.getPixelFromD(r1, r14);
        r14 = (int) r14;
        r14 = (float) r14;
        r6.drawText(r8, r13, r14, r12);
    L_0x0199:
        r20 = r20.trim();
        r13 = " ";
        r14 = "";
        r0 = r20;
        r20 = r0.replaceAll(r13, r14);
        r10 = new java.io.File;
        r13 = new java.lang.StringBuilder;
        r13.<init>();
        r14 = "Icons";
        r0 = r19;
        r14 = r0.getExternalFilesDir(r14);
        r13 = r13.append(r14);
        r14 = java.io.File.separator;
        r13 = r13.append(r14);
        r0 = r20;
        r13 = r13.append(r0);
        r14 = ".PNG";
        r13 = r13.append(r14);
        r13 = r13.toString();
        r10.<init>(r13);
        r10.createNewFile();	 Catch:{ IOException -> 0x0221 }
        r11 = 0;
        r11 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x0221 }
        r11.<init>(r10);	 Catch:{ IOException -> 0x0221 }
        r13 = android.graphics.Bitmap.CompressFormat.PNG;	 Catch:{ IOException -> 0x0221 }
        r14 = 20;
        r4.compress(r13, r14, r11);	 Catch:{ IOException -> 0x0221 }
        r11.flush();	 Catch:{ IOException -> 0x0221 }
        r11.close();	 Catch:{ IOException -> 0x0221 }
    L_0x01e9:
        r13 = r3.isRecycled();
        if (r13 != 0) goto L_0x01f2;
    L_0x01ef:
        r3.recycle();
    L_0x01f2:
        r13 = r4.isRecycled();
        if (r13 != 0) goto L_0x01fb;
    L_0x01f8:
        r4.recycle();
    L_0x01fb:
        return r4;
    L_0x01fc:
        r0 = r18;
        r1 = r21;
        r3 = r0.drawableToBitmap(r1);
        goto L_0x000f;
    L_0x0206:
        r0 = r18;
        r3 = r0.getCircleBitmap(r3);
        goto L_0x000f;
    L_0x020e:
        r13 = 8;
        r0 = r18;
        r1 = r19;
        r13 = r0.getPixelFromD(r1, r13);
        r13 = (int) r13;
        r0 = r18;
        r3 = r0.getRoundedCornerBitmap(r3, r13);
        goto L_0x000f;
    L_0x0221:
        r9 = move-exception;
        r9.printStackTrace();
        goto L_0x01e9;
        */
        throw new UnsupportedOperationException("Method not decompiled: launcher.customView.ImageBitmap.createIcon(android.content.Context, java.lang.String, android.graphics.drawable.Drawable):android.graphics.Bitmap");
    }
}