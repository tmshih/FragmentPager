package com.tms.fragmentpager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

public class AppPreference {
    private static final String KEY_CLIENT_ID = "app_pref_client_id";
    private static final String KEY_CLIENT_NAME = "app_pref_client_name";
    private static final String KEY_SERVER_ADDRESS = "app_pref_server_address";
    private static final String KEY_SERVER_PORT = "app_pref_server_port";
    private static final String VAL_UNDEFINE = "app_value_undefine";
    private static SharedPreferences mSharedPreferences;

    public static void initPrefs(@NonNull Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Empty context!");
        }

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (getClientID().equals(VAL_UNDEFINE)) {setClientID("");}
        if (getClientName().equals(VAL_UNDEFINE)) {setClientName("");}
        if (getServer().equals(VAL_UNDEFINE)) {setServer("");}
        if (getServerPort() == Integer.MIN_VALUE) {setServerPort(80);}
    }

    public static String getClientID() {
        return mSharedPreferences.getString(KEY_CLIENT_ID, VAL_UNDEFINE);
    }

    public static void setClientID(String id) {
        if (id == null) {id = "";}
        mSharedPreferences.edit().putString(KEY_CLIENT_ID, id).commit();
    }

    public static String getClientName() {
        return mSharedPreferences.getString(KEY_CLIENT_NAME, VAL_UNDEFINE);
    }

    public static void setClientName(String name) {
        if (name == null) {name = "";}
        mSharedPreferences.edit().putString(KEY_CLIENT_NAME, name).commit();
    }

    public static String getServer() {
        return mSharedPreferences.getString(KEY_SERVER_ADDRESS, VAL_UNDEFINE);
    }

    public static void setServer(String address) {
        if (address == null) {address = "";}
        mSharedPreferences.edit().putString(KEY_SERVER_ADDRESS, address).commit();
    }

    public static int getServerPort() {
        return mSharedPreferences.getInt(KEY_SERVER_PORT, Integer.MIN_VALUE);
    }

    public static void setServerPort(int port) {
        mSharedPreferences.edit().putInt(KEY_SERVER_PORT, port).commit();
    }

    public static class Validation {
        public static boolean isValidServer() {
            if (getServer().equals(VAL_UNDEFINE) || getServer().isEmpty()) {return false;}
            return true;
        }
    }
}
