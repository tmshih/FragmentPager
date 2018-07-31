package com.tms.fragmentpager.utils;

import android.util.Log;

public class MLog {

    public static final boolean ENABLE_GLOBAL_LOG = true;
    private final boolean enableLocalLog;

    private static String getClassName(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public void v(Object obj, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.v(getClassName(obj), msg);}
    }

    public void d(Object obj, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.d(getClassName(obj), msg);}
    }

    public void i(Object obj, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.i(getClassName(obj), msg);}
    }

    public void w(Object obj, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.w(getClassName(obj), msg);}
    }

    public void e(Object obj, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.e(getClassName(obj), msg);}
    }

    public void v(String tag, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.v(tag, msg);}
    }

    public void d(String tag, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.d(tag, msg);}
    }

    public void i(String tag, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.i(tag, msg);}
    }

    public void w(String tag, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.w(tag, msg);}
    }

    public void e(String tag, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.e(tag, msg);}
    }

    public void v(Class<?> cls, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.v(cls.getSimpleName(), msg);}
    }

    public void d(Class<?> cls, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.d(cls.getSimpleName(), msg);}
    }

    public void i(Class<?> cls, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.i(cls.getSimpleName(), msg);}
    }

    public void w(Class<?> cls, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.w(cls.getSimpleName(), msg);}
    }

    public void e(Class<?> cls, String msg) {
        if (!ENABLE_GLOBAL_LOG) { return;}
        if (enableLocalLog) { Log.e(cls.getSimpleName(), msg);}
    }

    public MLog(boolean enableLocalLog) {
        this.enableLocalLog = enableLocalLog;
    }

    public boolean isLocalEnable() { return enableLocalLog;}
}
