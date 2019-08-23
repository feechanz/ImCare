package com.feechan.imcare.activity;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.feechan.imcare.R;
import com.feechan.imcare.component.ItemPredictView;
import com.feechan.imcare.services.ApiService;
import com.feechan.imcare.utils.DialogUtils;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

public class SendApiActivity extends AppCompatActivity {

    private EditText urlEditText;
    private EditText rawEditText;
    private ScrollView rawScrollView;
    private ScrollView modelScrollView;
    private LinearLayout modelLinearLayout;
    private Button modelButton;
    private Button rawButton;
    private Button sendButton;
    private Button clearButton;

    private boolean isRaw = false;
    private List<ItemPredictView> itemPredictViews = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_api);
        urlEditText = findViewById(R.id.urlEditText);
        rawEditText = findViewById(R.id.rawEditText);
        sendButton = findViewById(R.id.sendButton);
        clearButton = findViewById(R.id.clearButton);
        rawScrollView = findViewById(R.id.rawScrollView);
        modelScrollView = findViewById(R.id.modelScrollView);
        modelLinearLayout = findViewById(R.id.modelLinearLayout);
        modelButton = findViewById(R.id.modelButton);
        rawButton = findViewById(R.id.rawButton);

        if(getIntent().getExtras() != null) {
            String title = getIntent().getExtras().getString("title");
            String url = getIntent().getExtras().getString("url");
            setTitle(title);
            urlEditText.setText(url);
        }
        rawEditText.setText("[\n" +
                "  {\n" +
                "    \"model_name\": \"new_mayapada_model1\",\n" +
                "    \"Age\": -1,\n" +
                "    \"Sex\": -1,\n" +
                "    \"GLU\": 120,\n" +
                "    \"GLU2J\": -1,\n" +
                "    \"CHOL\": -1,\n" +
                "    \"TRIG\": -1,\n" +
                "    \"HDL\": -1,\n" +
                "    \"LDL\": -1,\n" +
                "    \"UREA\": -1,\n" +
                "    \"CREA\": -1,\n" +
                "    \"UA\": -1,\n" +
                "    \"TP\": -1,\n" +
                "    \"ALB\": -1,\n" +
                "    \"GLOB\": -1,\n" +
                "    \"TBIL\": -1,\n" +
                "    \"DBIL\": -1,\n" +
                "    \"SGOT\": -1,\n" +
                "    \"SGPT\": -1,\n" +
                "    \"GGT\": -1,\n" +
                "    \"ALKF\": -1,\n" +
                "    \"HBA1C\": -1,\n" +
                "    \"CK\": -1,\n" +
                "    \"CKMB\": -1,\n" +
                "    \"LDH\": -1,\n" +
                "    \"TROPK\": -1,\n" +
                "    \"TROPT\": -1,\n" +
                "    \"X33\": -1,\n" +
                "    \"K\": -1,\n" +
                "    \"CL\": -1,\n" +
                "    \"CA\": -1,\n" +
                "    \"MG\": -1,\n" +
                "    \"AMIL\": -1,\n" +
                "    \"LIPAS\": -1\n" +
                "  }\n" +
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

        rawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRaw = true;
                updateTampilan();
            }
        });
        modelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isRaw = false;
                updateTampilan();
            }
        });
        initModelLayout();
    }

    private void initModelLayout(){
        List<String> listField = new ArrayList<>();
        listField.add("model_name");
        listField.add("Age");
        listField.add("Sex");
        listField.add("GLU");
        listField.add("GLU2J");
        listField.add("CHOL");
        listField.add("TRIG");
        listField.add("HDL");
        listField.add("LDL");
        listField.add("UREA");
        listField.add("CREA");
        listField.add("UA");
        listField.add("TP");
        listField.add("ALB");
        listField.add("GLOB");
        listField.add("TBIL");
        listField.add("DBIL");
        listField.add("SGOT");
        listField.add("SGPT");
        listField.add("GGT");
        listField.add("ALKF");
        listField.add("HBA1C");
        listField.add("CK");
        listField.add("CKMB");
        listField.add("LDH");
        listField.add("TROPK");
        listField.add("TROPT");
        listField.add("X33");
        listField.add("K");
        listField.add("CL");
        listField.add("CA");
        listField.add("MG");
        listField.add("AMIL");
        listField.add("LIPAS");

        int idx =0;
        for(String item: listField){
            ItemPredictView view = new ItemPredictView(this);
            view.setKey(item);
            if(idx != 0){
                view.setValue("0");
            }else{
                view.setValue("new_mayapada_model1");
            }

            itemPredictViews.add(view);
            modelLinearLayout.addView(view);
            idx++;
        }
    }

    private void updateTampilan(){
        if(isRaw){
            modelScrollView.setVisibility(View.GONE);
            rawScrollView.setVisibility(View.VISIBLE);
        }else{
            modelScrollView.setVisibility(View.VISIBLE);
            rawScrollView.setVisibility(View.GONE);
        }
    }


    private void sendAction(){
        final ProgressDialog dialog = ProgressDialog.show(this, "",
                "Loading. Please wait...", true);
        dialog.show();
        String jsonArrayString = "";
        if(isRaw) {
            jsonArrayString = rawEditText.getText().toString();
        }else{
            jsonArrayString = "";
            List<HashMap<String,String>> listMap = new ArrayList<>();
            HashMap<String, String> map = new HashMap<>();
            for(ItemPredictView item : itemPredictViews){
                map.put(item.getKey(), item.getValue());
            }
            listMap.add(map);

            Gson gson = new Gson();
            jsonArrayString = gson.toJson(listMap);
        }
        Log.d("chanz","JSON String : "+jsonArrayString);
        ApiService.postResponse(this, urlEditText.getText().toString(), jsonArrayString, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                dialog.dismiss();
                DialogUtils.showErrorMessageDialog(SendApiActivity.this, response.toString());
            }
        }, new Response.ErrorListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("chanz", "error : " + error);
                dialog.dismiss();
                if (error == null || error.networkResponse == null) {
                    return;
                }

                String body = "";
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }

//                Log.d("chanz","Error body : "+ body);
                Log.d("chanz", "Status Code : " + statusCode);
                rawEditText.setText(body);
                DialogUtils.showErrorMessageDialog(SendApiActivity.this, getString(R.string.error_conenction));
            }
        });
    }
}
