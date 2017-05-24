package com.fnm.ecodata.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by MuhammadMahmoor on 4/30/17.
 */

public class Util {

    public static String getRadius(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("example_text", null);
    }
}
