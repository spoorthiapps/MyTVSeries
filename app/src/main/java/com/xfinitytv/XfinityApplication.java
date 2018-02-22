package com.xfinitytv;

import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

public class XfinityApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initializeAppDependencies();
        activiateActiveAndroid();
    }

    private void initializeAppDependencies() {
        XfinityDependencyModule module = new XfinityDependencyModule();
        XfinityObjectGraph.initialize(module);
    }

    private void activiateActiveAndroid() {
        com.activeandroid.util.Log.setEnabled(true);

        Configuration.Builder builder = new Configuration.Builder(getApplicationContext());
        builder.setDatabaseName("xfinitydb");
        builder.setDatabaseVersion(1);
        Configuration config = builder.create();
        ActiveAndroid.initialize(config);
    }

    @Override
    public void onTerminate() {
        ActiveAndroid.dispose();
        super.onTerminate();
    }
}
