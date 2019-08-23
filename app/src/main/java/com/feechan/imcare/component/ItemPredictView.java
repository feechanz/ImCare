package com.feechan.imcare.component;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feechan.imcare.R;

/**
 * Created by Feechan on 8/23/2019.
 */
public class ItemPredictView extends LinearLayout {
    private TextView keyTextView;
    private EditText valueEditText;

    public ItemPredictView(Context context) {
        super(context);
        commonInit();
    }

    public ItemPredictView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        commonInit();
    }

    public ItemPredictView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        commonInit();
    }

    private void commonInit(){
        View l_view = LayoutInflater.from(getContext()).inflate(R.layout.item_predict_layout, this);
        keyTextView = l_view.findViewById(R.id.keyTextView);
        valueEditText = l_view.findViewById(R.id.valueEditText);

    }

    public String getValue(){
        return valueEditText.getText().toString();
    }
    private String key;
    public String getKey(){
        return key;
    }

    public void setValue(String text){
        valueEditText.setText(text);
    }

    public void setKey(String key){
        this.key = key;
        keyTextView.setText(key);
        valueEditText.setHint(key);
    }
}
