package com.feechan.imcare.global;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.signature.StringSignature;
import com.feechan.imcare.entity.Penyakit;
import com.google.gson.Gson;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Feechan on 8/23/2017.
 */

public class AppHelper {
    private static Gson gson = null;
    public static Gson getGson(){
        if(gson == null){
            gson = new Gson();
        }
        return gson;
    }

    public static void setImageFromUrl(ImageView imageView, String url, Activity activity){
        String[] pathImage = url.split("/",-1);
        String filename = pathImage[pathImage.length-1];
        Log.d("care","Picturename >> "+filename);
        Glide.with(activity)
                .load(url)
                .centerCrop()
                .signature(new StringSignature(filename))
                //.bitmapTransform(new RoundedCornersTransformation(activity, 25, 5))
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        // log exception
                        Log.e("care", "Error loading image", e);
                        return false; // important to return false so the error placeholder can be placed
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(imageView);
    }
}
