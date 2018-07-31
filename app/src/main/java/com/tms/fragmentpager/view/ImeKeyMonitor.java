package com.tms.fragmentpager.view;

import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Interface for an EditText subclass that can delegate calls to onKeyPreIme up to a registered listener.
 * <p>
 * Used in editable actions to allow for custom back key handling.
 * Specifically, this is used to implement the behavior that dismissing the IME also clears edit text focus.
 */
public interface ImeKeyMonitor {

    /**
     * Listener interface for key events intercepted pre-IME by edit text objects.
     */
    public interface ImeKeyListener {
        /**
         * Callback invoked from EditText's onKeyPreIme method override. Returning true tells the
         * caller that the key event is handled and should not be propagated.
         */
        public abstract boolean onKeyPreIme(EditText editText, int keyCode, KeyEvent event);
    }

    /**
     * Set the listener for this edit text object. The listener's onKeyPreIme method will be
     * invoked from the host edit text's onKeyPreIme method.
     */
    public void setImeKeyListener(ImeKeyListener listener);
}
