package com.tms.fragmentpager.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * A custom EditText that satisfies the IME key monitoring requirements.
 * <p>
 * Design based on android.support.v17.leanback.widget.GuidedActionEditText
 */
public class AtEditText extends AppCompatEditText implements ImeKeyMonitor {

    private ImeKeyListener mKeyListener;

    public AtEditText(Context context) {
        super(context);
    }

    public AtEditText(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.editTextStyle);
    }

    public AtEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImeKeyListener(ImeKeyListener listener) {
        mKeyListener = listener;
    }

    @Override
    public boolean onKeyPreIme(int keyCode, android.view.KeyEvent event) {
        boolean result = false;
        if (mKeyListener != null) {
            result = mKeyListener.onKeyPreIme(this, keyCode, event);
        }
        if (!result) {
            result = super.onKeyPreIme(keyCode, event);
        }
        return result;
    }
}
