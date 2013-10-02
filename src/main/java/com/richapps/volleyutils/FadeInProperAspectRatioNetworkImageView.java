package com.richapps.volleyutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by ericrichardson on 8/29/13.
 */
public class FadeInProperAspectRatioNetworkImageView extends FadeInNetworkImageView{
    boolean loaded = false;
    public FadeInProperAspectRatioNetworkImageView(Context context) {
        super(context);
    }

    public FadeInProperAspectRatioNetworkImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FadeInProperAspectRatioNetworkImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        if(bitmap == null){
            return;
        }
        if(loaded && getHeight() == bitmap.getHeight()){
            super.setImageBitmap(bitmap);
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int bounding = getMeasuredWidth();

        // Determine how much to scale: the dimension requiring less scaling is
        // closer to the its side. This way the image always stays inside your
        // bounding box AND either x/y axis touches it.
        float xScale = ((float) bounding) / width;
        float yScale = ((float) bounding) / height;
        float scale = (xScale <= yScale) ? xScale : yScale;

        // Create a matrix for the scaling and add the scaling data
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);

        // Create a new bitmap and convert it to a format understood by the ImageView
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        width = bitmap.getWidth();
        height = bitmap.getHeight();

        TransitionDrawable td = new TransitionDrawable(new Drawable[]{
                new ColorDrawable(android.R.color.transparent),
                new BitmapDrawable(getContext().getResources(), bitmap)
        });
        setImageDrawable(td);
        td.startTransition(FADE_IN_TIME_MS);
        // Now change ImageView's dimensions to match the scaled image
        ViewGroup.LayoutParams params = getLayoutParams();
        params.width = width;
        params.height = height;
        setLayoutParams(params);
    }
}
