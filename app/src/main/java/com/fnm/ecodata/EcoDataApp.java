package com.fnm.ecodata;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by Dev on 13/10/2016.
 */

// This class will be used do all one time initializations for the app
public class EcoDataApp extends Application {
    private static EcoDataApp mInstance;
    private RequestQueue requestQueue;
    public static Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        mInstance = this;
        requestQueue = Volley.newRequestQueue(this);
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
        realm = Realm.getDefaultInstance();
    }

    public static synchronized EcoDataApp getInstance() {
        return mInstance;
    }


    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }
}
