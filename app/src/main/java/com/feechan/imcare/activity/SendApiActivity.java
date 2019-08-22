package com.feechan.imcare.activity;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.feechan.imcare.R;
import com.feechan.imcare.services.ApiService;
import com.feechan.imcare.utils.DialogUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class SendApiActivity extends AppCompatActivity {
    private EditText urlEditText;
    private EditText rawEditText;
    private Button sendButton;
    private Button clearButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_api);
        urlEditText = findViewById(R.id.urlEditText);
        rawEditText = findViewById(R.id.rawEditText);
        sendButton = findViewById(R.id.sendButton);
        clearButton = findViewById(R.id.clearButton);
        if(getIntent().getExtras() != null) {
            String title = getIntent().getExtras().getString("title");
            String url = getIntent().getExtras().getString("url");
            setTitle(title);
            urlEditText.setText(url);
        }
        rawEditText.setText("[\n" +
                "\t{\n" +
                "\t\t\"model_name\": \"model_1\",\n" +
                "\t\t\"sepal length (cm)\": 5.1,\n" +
                "\t\t\"sepal width (cm)\": 3.5,\n" +
                "\t\t\"petal length (cm)\": 1.4,\n" +
                "\t\t\"petal width (cm)\": 0.2\n" +
                "\t},\n" +
                "\t{\n" +
                "\t\t\"model_name\": \"model_1\",\n" +
                "\t\t\"sepal length (cm)\": 3.1,\n" +
                "\t\t\"sepal width (cm)\": 1.5,\n" +
                "\t\t\"petal length (cm)\": 4.4,\n" +
                "\t\t\"petal width (cm)\": 0.6\n" +
                "\t}\n" +
                "]");

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rawEditText.setText("");
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendAction();
            }
        });
    }

    private void sendAction(){
        ApiService.postResponse(this, urlEditText.getText().toString(), rawEditText.getText().toString(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                DialogUtils.showErrorMessageDialog(SendApiActivity.this, response.toString());
            }
        }, new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("chanz","error : "+ error);
                if (error == null || error.networkResponse == null) {
                    return;
                }

                String body = "";
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }

//                Log.d("chanz","Error body : "+ body);
                Log.d("chanz","Status Code : " + statusCode);
                rawEditText.setText(body);
                DialogUtils.showErrorMessageDialog(SendApiActivity.this, getString(R.string.error_conenction));
            }
        });
    }
}
