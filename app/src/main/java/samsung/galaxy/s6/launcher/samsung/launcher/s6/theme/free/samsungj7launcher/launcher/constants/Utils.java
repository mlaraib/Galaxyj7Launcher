package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.constants;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.StateListDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.StateSet;
import java.util.ArrayList;

/**
 * Created by iamla on 11/23/2016.
 */
public class Utils {
    public static String[] DEVICE_ID;
    static ArrayList<Typeface> typeface;

    static {
        DEVICE_ID = new String[]{"8ffc11f20f5cb889", "9e450707159dca82", "f56cf5bd9f6c9439", "c0be0afcb1c676e7", "71fe2a304ebb6483"};
    }

    public static StateListDrawable convertColorIntoBitmap(int color) {
        Rect rectPressed = new Rect(0, 0, 200, 200);
        Bitmap imagePressed = Bitmap.createBitmap(rectPressed.width(), rectPressed.height(), Config.ARGB_8888);
        Canvas canvas = new Canvas(imagePressed);
        int colorPressed = color;
        Paint paintPressed = new Paint();
        paintPressed.setAntiAlias(true);
        paintPressed.setStyle(Style.FILL);
        paintPressed.setColor(colorPressed);
        canvas.drawRect(rectPressed, paintPressed);
        paintPressed.setColor(-1);
        paintPressed.setStyle(Style.STROKE);
        paintPressed.setStrokeWidth(4.0f);
        canvas.drawRect(rectPressed, paintPressed);
        new RectF().round(rectPressed);
        Rect rectNormal = new Rect(0, 0, 1, 1);
        Bitmap imageNormal = Bitmap.createBitmap(rectNormal.width(), rectNormal.height(), Config.ARGB_8888);
        Canvas canvasNormal = new Canvas(imageNormal);
        int colorNormal = color;
        Paint paintNormal = new Paint();
        paintNormal.setAntiAlias(true);
        paintNormal.setColor(colorNormal);
        canvasNormal.drawRect(rectNormal, paintNormal);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, new BitmapDrawable(imagePressed));
        stateListDrawable.addState(StateSet.WILD_CARD, new BitmapDrawable(imageNormal));
        return stateListDrawable;
    }

    public static boolean isOnline(Context context) {
        NetworkInfo netInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static Typeface getFont(Context context) {
        if (typeface == null) {
            typeface = new ArrayList();
            typeface.add(Typeface.createFromAsset(context.getAssets(), "normal-font.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "bold-font.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "CarroisGothicSC-Regular.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "Clockopia.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "ComingSoon.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "CutiveMono.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "DancingScript-Bold.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "DancingScript-Regular.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "EKLEKTIN.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "F021004D.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "FIRECAT.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "FLINPO__.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "GALLERIN.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "GIGI.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "lightfont.otf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf"));
        }
        return (Typeface) typeface.get(Settings.getFont(context));
    }

    public static Typeface getFont(Context context, int index) {
        if (typeface == null) {
            typeface = new ArrayList();
            typeface.add(Typeface.createFromAsset(context.getAssets(), "normal-font.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "bold-font.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "CarroisGothicSC-Regular.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "Clockopia.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "ComingSoon.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "CutiveMono.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "DancingScript-Bold.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "DancingScript-Regular.ttf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "EKLEKTIN.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "F021004D.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "FIRECAT.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "FLINPO__.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "GALLERIN.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "GIGI.TTF"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "lightfont.otf"));
            typeface.add(Typeface.createFromAsset(context.getAssets(), "Roboto-Bold.ttf"));
        }
        return (Typeface) typeface.get(index);
    }
}

