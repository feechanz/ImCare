package com.feechan.imcare.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.feechan.imcare.global.Config;
import com.feechan.imcare.utils.VolleyRequest;

/**
 * Created by Feechan on 9/12/2017.
 */

public class RsService {
    public static String ENDPOINT = "/api/rs.php";

    public static void getAllRs(Context appContext, Response.Listener<String> onPostsLoaded, Response.ErrorListener onPostsError) {
        String url = Config.URL+ENDPOINT;
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError);
        VolleyRequest Vrequest = VolleyRequest.getInstance(appContext);
        Vrequest.addToRequestQueue(request);
    }
}
