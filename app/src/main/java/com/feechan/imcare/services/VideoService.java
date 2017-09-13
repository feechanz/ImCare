package com.feechan.imcare.services;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.feechan.imcare.global.Config;
import com.feechan.imcare.utils.VolleyRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Feechan on 8/23/2017.
 */

public class VideoService
{
    public static String ENDPOINT = "/api/video.php";

    public static void getAllVideo(Context appContext, int kdpenyakit, Response.Listener<String> onPostsLoaded, Response.ErrorListener onPostsError) {
        String url = Config.URL+ENDPOINT+"?kdpenyakit="+kdpenyakit;
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError);
        VolleyRequest Vrequest = VolleyRequest.getInstance(appContext);
        Vrequest.addToRequestQueue(request);
    }

    public static void getOneVideo(Context appContext, int id, Response.Listener<String> onPostsLoaded, Response.ErrorListener onPostsError) {
        String url = Config.URL+ENDPOINT+"?novideo="+id;
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError);
        VolleyRequest Vrequest = VolleyRequest.getInstance(appContext);
        Vrequest.addToRequestQueue(request);
    }
}
