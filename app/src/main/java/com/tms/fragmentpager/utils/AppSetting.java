package com.tms.fragmentpager.utils;

/**
 * Created by TsungMu on 2016/11/22.
 */
public class AppSetting {
    /** Application mode. Default should be {@link Mode#USER}. **/
    public enum Mode {
        USER, ENGINEER
    }

    private static Mode appMode = Mode.USER;

    /**
     * Return true if application is engineering:<br>
     * 1. No DID prefix filtering while add Gateway or IPCam.
     */
    public static boolean isEngineering() {
        return appMode.equals(Mode.ENGINEER);
    }

    /**
     * Return current application mode.
     */
    public static Mode getMode() {
        return appMode;
    }

    /**
     * Marks the application mode.
     * @param mode should be one of {@link Mode}.
     */
    public static void setMode(Mode mode) {
        appMode = mode;
    }
}
