package com.feechan.imcare.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.feechan.imcare.R;
import com.feechan.imcare.component.CustomErrorDialog;

/**
 * Created by Feechan on 8/14/2019.
 */
public class DialogUtils {
    private DialogUtils() {
    }

    public static void showErrorMessageDialog(Activity context, String errorMessage) {
        showErrorMessageDialog(context, errorMessage, null);
    }

    public static void showErrorMessageDialog(Activity context, String errorMesssage,
                                              View.OnClickListener clickListener) {
        CustomErrorDialog customErrorDialog = CustomErrorDialog
                .getInstance(errorMesssage, context.getResources()
                        .getString(R.string.ok));
        customErrorDialog.setOnClickListener(clickListener);
        customErrorDialog.show(context.getFragmentManager(), context.getClass().getSimpleName());
    }

    public static Window setDialogNoTitleWindow(Dialog dialog) {
        return setDialogNoTitleWindow(dialog, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public static Window setDialogNoTitleWindow(Dialog dialog, int width, int height) {
        Window window = dialog.getWindow();
        assert window != null;
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setGravity(Gravity.CENTER);
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setLayout(width, height);
        window.setBackgroundDrawable(new ColorDrawable(0));
        return window;
    }
}
