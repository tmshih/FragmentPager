package com.tms.fragmentpager.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Tm.Shih on 2017/6/30.
 */

public class AtDialog extends Dialog {
    private final String TAG = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
    private boolean isHideSystemUI = true;

    public AtDialog(@NonNull Context context) {
        super(context);
    }

    public AtDialog(@NonNull Context context, @StyleRes int theme) {
        super(context, theme);
    }

    protected AtDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void show() {
        //Set the dialog to not focusable (makes navigation ignore us adding the window)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

        super.show();

        hideSystemUI();

        //Clear the not focusable flag from the window
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    /**
     * Hiding the SystemUI (status & navigation bar).
     */
    public void hideSystemUI() {
        if (!isHideSystemUI) {
            return;
        }

        Log.d(TAG, "hideSystemUI():");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN |
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}
