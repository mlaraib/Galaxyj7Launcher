package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.customDialog;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.animation.Animation;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;



/**
 * Created by iamla on 11/24/2016.
 */
public class OptAnimationLoader {
    public static Animation loadAnimation(Context context, int id) throws NotFoundException {
        NotFoundException rnf;
        XmlResourceParser parser = null;
        try {
            parser = context.getResources().getAnimation(id);
            Animation createAnimationFromXml = createAnimationFromXml(context, parser);
            if (parser != null) {
                parser.close();
            }
            return createAnimationFromXml;
        } catch (XmlPullParserException ex) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(ex);
            throw rnf;
        } catch (IOException ex2) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(ex2);
            throw rnf;
        } catch (Throwable th) {
            if (parser != null) {
                parser.close();
            }
        }

    return null;

    }

    private static Animation createAnimationFromXml(Context c, XmlPullParser parser) throws XmlPullParserException, IOException {
        return createAnimationFromXml(c, parser, null, Xml.asAttributeSet(parser));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static android.view.animation.Animation createAnimationFromXml(android.content.Context r11, org.xmlpull.v1.XmlPullParser r12, android.view.animation.AnimationSet r13, android.util.AttributeSet r14) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
        r10 = 2;
        r9 = 1;
        r0 = 0;
        r1 = r12.getDepth();
    L_0x0007:
        r4 = r12.next();
        r5 = 3;
        if (r4 != r5) goto L_0x0014;
    L_0x000e:
        r5 = r12.getDepth();
        if (r5 <= r1) goto L_0x00be;
    L_0x0014:
        if (r4 == r9) goto L_0x00be;
    L_0x0016:
        if (r4 != r10) goto L_0x0007;
    L_0x0018:
        r2 = r12.getName();
        r5 = "set";
        r5 = r2.equals(r5);
        if (r5 == 0) goto L_0x0035;
    L_0x0024:
        r0 = new android.view.animation.AnimationSet;
        r0.<init>(r11, r14);
        r5 = r0;
        r5 = (android.view.animation.AnimationSet) r5;
        createAnimationFromXml(r11, r12, r5, r14);
    L_0x002f:
        if (r13 == 0) goto L_0x0007;
    L_0x0031:
        r13.addAnimation(r0);
        goto L_0x0007;
    L_0x0035:
        r5 = "alpha";
        r5 = r2.equals(r5);
        if (r5 == 0) goto L_0x0043;
    L_0x003d:
        r0 = new android.view.animation.AlphaAnimation;
        r0.<init>(r11, r14);
        goto L_0x002f;
    L_0x0043:
        r5 = "scale";
        r5 = r2.equals(r5);
        if (r5 == 0) goto L_0x0051;
    L_0x004b:
        r0 = new android.view.animation.ScaleAnimation;
        r0.<init>(r11, r14);
        goto L_0x002f;
    L_0x0051:
        r5 = "rotate";
        r5 = r2.equals(r5);
        if (r5 == 0) goto L_0x005f;
    L_0x0059:
        r0 = new android.view.animation.RotateAnimation;
        r0.<init>(r11, r14);
        goto L_0x002f;
    L_0x005f:
        r5 = "translate";
        r5 = r2.equals(r5);
        if (r5 == 0) goto L_0x006d;
    L_0x0067:
        r0 = new android.view.animation.TranslateAnimation;
        r0.<init>(r11, r14);
        goto L_0x002f;
    L_0x006d:
        r5 = java.lang.Class.forName(r2);	 Catch:{ Exception -> 0x0092 }
        r6 = 2;
        r6 = new java.lang.Class[r6];	 Catch:{ Exception -> 0x0092 }
        r7 = 0;
        r8 = android.content.Context.class;
        r6[r7] = r8;	 Catch:{ Exception -> 0x0092 }
        r7 = 1;
        r8 = android.util.AttributeSet.class;
        r6[r7] = r8;	 Catch:{ Exception -> 0x0092 }
        r5 = r5.getConstructor(r6);	 Catch:{ Exception -> 0x0092 }
        r6 = 2;
        r6 = new java.lang.Object[r6];	 Catch:{ Exception -> 0x0092 }
        r7 = 0;
        r6[r7] = r11;	 Catch:{ Exception -> 0x0092 }
        r7 = 1;
        r6[r7] = r14;	 Catch:{ Exception -> 0x0092 }
        r0 = r5.newInstance(r6);	 Catch:{ Exception -> 0x0092 }
        r0 = (android.view.animation.Animation) r0;	 Catch:{ Exception -> 0x0092 }
        goto L_0x002f;
    L_0x0092:
        r3 = move-exception;
        r5 = new java.lang.RuntimeException;
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = "Unknown animation name: ";
        r6 = r6.append(r7);
        r7 = r12.getName();
        r6 = r6.append(r7);
        r7 = " error:";
        r6 = r6.append(r7);
        r7 = r3.getMessage();
        r6 = r6.append(r7);
        r6 = r6.toString();
        r5.<init>(r6);
        throw r5;
    L_0x00be:
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: launcher.customDialog.OptAnimationLoader.createAnimationFromXml(android.content.Context, org.xmlpull.v1.XmlPullParser, android.view.animation.AnimationSet, android.util.AttributeSet):android.view.animation.Animation");
    }
}

