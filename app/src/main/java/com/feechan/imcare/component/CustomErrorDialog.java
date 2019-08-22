package com.feechan.imcare.component;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.feechan.imcare.R;
import com.feechan.imcare.utils.DialogUtils;


/**
 * Created by Feechan on 8/14/2019.
 */
public class CustomErrorDialog extends DialogFragment {

    private static final String DIALOG_MESSAGE = "DIALOG_MESSAGE";

    private static final String POSITIVE_BUTTON_TITLE = "POSITIVE_BUTTON_TITLE";

    Button btnDialogButton;

    TextView tvDialogContent;


    private DialogInterface.OnShowListener dialogShowListener;

    private View.OnClickListener onClickListener;

    public static CustomErrorDialog getInstance(String alertContent,
                                                String alertButtonName) {
        CustomErrorDialog customErrorDialog = new CustomErrorDialog();
        Bundle bundle = new Bundle();
        bundle.putString(DIALOG_MESSAGE, alertContent);
        bundle.putString(POSITIVE_BUTTON_TITLE, alertButtonName);
        customErrorDialog.setArguments(bundle);
        return customErrorDialog;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_error, container, false);
        btnDialogButton = view.findViewById(R.id.btn_dialog_error_ensure);
        tvDialogContent = view.findViewById(R.id.tv_dialog_error_message);
        initView();
        return view;
    }

    private void initView() {
        if (getArguments() == null) return;
        String dialogMessage = getArguments().getString(DIALOG_MESSAGE);
        tvDialogContent.setText(dialogMessage);
        String buttonName = getArguments().getString(POSITIVE_BUTTON_TITLE);
        btnDialogButton.setText(buttonName);
        btnDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onViewClicked(view);
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        DialogUtils.setDialogNoTitleWindow(dialog);
        dialog.setCanceledOnTouchOutside(false);
        if (dialogShowListener != null) {
            dialog.setOnShowListener(dialogShowListener);
        }
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onViewClicked(View view) {
        this.dismiss();
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }
}
