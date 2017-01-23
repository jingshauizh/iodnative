package com.mvp.jingshuai.android_iod.app;

import android.app.Application;
import android.support.annotation.VisibleForTesting;
import android.util.Log;

import com.mvp.jingshuai.android_iod.di.component.AppComponent;

import com.mvp.jingshuai.android_iod.di.component.DaggerAppComponent;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule;
import com.path.android.jobqueue.JobManager;
import com.path.android.jobqueue.config.Configuration;
import com.path.android.jobqueue.log.CustomLogger;
import com.raizlabs.android.dbflow.config.FlowManager;


/**
 * Created by eqruvvz on 12/30/2016.
 */
public class IODApplication extends Application {
    private static IODApplication instance;
    private JobManager jobManager;

    public IODApplication() {
        instance = this;
    }

    private AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        configureJobManager();
        FlowManager.init(this);
       mAppComponent = DaggerAppComponent.builder()
               .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    @VisibleForTesting
    public void setAppComponent(AppComponent appComponent) {
        mAppComponent = appComponent;
    }


    private void configureJobManager() {
        Configuration configuration = new Configuration.Builder(this)
                .customLogger(new CustomLogger() {
                    private static final String TAG = "JOBS";
                    @Override
                    public boolean isDebugEnabled() {
                        return true;
                    }

                    @Override
                    public void d(String text, Object... args) {
                        Log.d(TAG, String.format(text, args));
                    }

                    @Override
                    public void e(Throwable t, String text, Object... args) {
                        Log.e(TAG, String.format(text, args), t);
                    }

                    @Override
                    public void e(String text, Object... args) {
                        Log.e(TAG, String.format(text, args));
                    }
                })
                .minConsumerCount(1)//always keep at least one consumer alive
                .maxConsumerCount(3)//up to 3 consumers at a time
                .loadFactor(3)//3 jobs per consumer
                .consumerKeepAlive(120)//wait 2 minute
                .build();
        jobManager = new JobManager(this, configuration);
    }

    public JobManager getJobManager() {
        return jobManager;
    }

    public static IODApplication getInstance() {
        return instance;
    }



}
