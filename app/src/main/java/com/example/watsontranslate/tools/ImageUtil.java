package com.example.watsontranslate.tools;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.security.cert.PKIXRevocationChecker;

public class ImageUtil {

    private static final String TAG = "ImageUtil";

    public static Bitmap decodeSampledBitmapFromResources(Resources resources, int resId, int targetWidth, int targetHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);

        options.inSampleSize = calculateInSampleSize(options, targetWidth, targetHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, resId, options);
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int targetWidth, int targetHeight) {
        int inSampleSize = 1;
        while (options.outWidth / 2 / inSampleSize > targetWidth
                && options.outHeight / 2 / inSampleSize > targetHeight) {
            inSampleSize *= 2;
        }
        return inSampleSize;
    }

}
