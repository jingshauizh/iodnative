package com.mvp.jingshuai.android_iod.di.component;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailActivity;
import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailPresenter;
import com.mvp.jingshuai.android_iod.api.ApiModule;
import com.mvp.jingshuai.android_iod.config.DemoConfig;
import com.mvp.jingshuai.android_iod.di.module.ApplicationModule;
import com.mvp.jingshuai.android_iod.job.Info.FetchInfoJob;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.mvp.jingshuai.data.network.ApiService;
import com.path.android.jobqueue.JobManager;

import javax.inject.Singleton;

import dagger.Component;
import de.greenrobot.event.EventBus;

/**
 * Created by eqruvvz on 1/19/2017.
 */

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface AppComponent {
    ApiService apiService();
    InfoIdal infoIdal();
    EventBus eventBus();
    DemoConfig demoConfig();
    Context appContext();
    SQLiteDatabase database();
    JobManager jobManager();


    //定义注入的方法
    void inject(InfoODetailActivity activity);
    void inject(FetchInfoJob fetchInfoJob);
    void inject(InfoODetailPresenter infoODetailPresenter);

}
