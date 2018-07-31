package com.tms.fragmentpager.view;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tms.fragmentpager.R;


/**
 * Created by Tm.Shih on 2018/1/23.
 */
public class AtToast extends Toast {

    public AtToast(Context context) {
        super(context);

        setView(View.inflate(context, R.layout.toast_common, null));
    }

    public static Toast makeText(Context context, int resId, int duration) {
        AtToast toast = new AtToast(context);
        toast.setDuration(duration);
        View toastText = toast.getView().findViewById(R.id.toastText);
        if (toastText instanceof TextView) {((TextView) toastText).setText(context.getString(resId));}
        return toast;
    }

    public static Toast makeText(Context context, CharSequence text, int duration) {
        AtToast toast = new AtToast(context);
        toast.setDuration(duration);
        View toastText = toast.getView().findViewById(R.id.toastText);
        if (toastText instanceof TextView) {((TextView) toastText).setText(text);}
        return toast;
    }
}
