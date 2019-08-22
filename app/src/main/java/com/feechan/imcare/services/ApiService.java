package com.feechan.imcare.services;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.feechan.imcare.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by Feechan on 8/22/2019.
 */
public class ApiService {
    public static void postResponse(Context appContext, String url, String jsonString, Response.Listener<JSONArray> onPostsLoaded, Response.ErrorListener onPostsError) {
        Log.d("chanz","URL : "+ url);
        Log.d("chanz","Json Str : "+ jsonString);
        JSONArray jsonArray = null;
        try {

            jsonArray = new JSONArray(jsonString);
        }catch (JSONException err){
            Log.d("chanz", err.toString());
        }
        Log.d("chanz","Json Array : "+ jsonArray);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, jsonArray, onPostsLoaded, onPostsError);
        VolleyRequest Vrequest = VolleyRequest.getInstance(appContext);
        Vrequest.addToRequestQueue(request);
    }
}
