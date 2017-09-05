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

public class ArtikelService
{
    public static String ENDPOINT = "/artikel.php";

    public static void getAllArtikel(Context appContext, Response.Listener<String> onPostsLoaded, Response.ErrorListener onPostsError) {
        String url = Config.URL+ENDPOINT;
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError);
        VolleyRequest Vrequest = VolleyRequest.getInstance(appContext);
        Vrequest.addToRequestQueue(request);
    }

    public static void getOneArtikel(Context appContext, int id, Response.Listener<String> onPostsLoaded, Response.ErrorListener onPostsError) {
        String url = Config.URL+ENDPOINT+"?id="+id;
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError);
        VolleyRequest Vrequest = VolleyRequest.getInstance(appContext);
        Vrequest.addToRequestQueue(request);
    }

    public static void getTwitter(Context appContext, Response.Listener<String> onPostsLoaded, Response.ErrorListener onPostsError){
        String url = "https://api.twitter.com/1.1/statuses/home_timeline.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, onPostsLoaded, onPostsError){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "OAuth oauth_consumer_key=\"CpGpyVgvXRh1PPvRuv4MuLXLH\",oauth_token=\"108941758-abtfHREHVsr24OUumdCdZkhgfuzzpdmLgFyP7wrU\",oauth_signature_method=\"HMAC-SHA1\",oauth_timestamp=\"1503453498\",oauth_nonce=\"WzuW73\",oauth_version=\"1.0\",oauth_signature=\"9lHURn%2BvBMMvt1onQMqTOO8HV68%3D\"");
                return params;
            }
        };
        VolleyRequest Vrequest = VolleyRequest.getInstance(appContext);
        Vrequest.addToRequestQueue(request);
    }
}
