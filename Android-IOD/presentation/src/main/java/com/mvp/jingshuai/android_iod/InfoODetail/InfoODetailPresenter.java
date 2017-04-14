package com.mvp.jingshuai.android_iod.InfoODetail;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mvp.jingshuai.android_iod.app.IODApplication;
import com.mvp.jingshuai.android_iod.event.FetchedInfoEvent;
import com.mvp.jingshuai.android_iod.job.BaseJob;
import com.mvp.jingshuai.android_iod.job.Info.FetchInfoJob;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.mvp.jingshuai.data.model.InfoObjectModel;
import com.path.android.jobqueue.JobManager;

import java.util.Date;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;

/**
 * Created by eqruvvz on 12/31/2016.
 */
public class InfoODetailPresenter implements InfoODetailContract.Presenter {

    private final String TAG = "InfoODetailPresenter";


    private JobManager jobManager;
    private String page_infoId = "sintelChicken";

    private final InfoODetailContract.View mInfoODetailView;

    @Nullable
    private String mTaskId;

    @Inject
    JobManager mJobManager;
    @Inject
    Context mAppContext;
    @Inject
    EventBus mEventBus;
    @Inject
    InfoIdal mInfoIdal;



    private String infoId;
    private String vodId;

    public InfoODetailPresenter(@Nullable String taskId,
                                @NonNull InfoODetailContract.View mInfoODetailView){
        this.mTaskId = taskId;

        this.mInfoODetailView = mInfoODetailView;

        mInfoODetailView.setPresenter(this);
        IODApplication.getInstance().getAppComponent().inject(this);
        mEventBus.register(this,0);


    }

    public InfoODetailPresenter(@Nullable String taskId,
                                @NonNull InfoODetailContract.View mInfoODetailView,
                                String infoId,
                                String vodId){
        this.infoId = infoId;
        this.page_infoId = infoId;
        this.vodId = vodId;
        this.mTaskId = taskId;
        this.mInfoODetailView = mInfoODetailView;
        mInfoODetailView.setPresenter(this);
        IODApplication.getInstance().getAppComponent().inject(this);
        mEventBus.register(this,0);
    }


    @Override
    public void start() {
        showDetailedInfoO(infoId,vodId);
    }

    private void showDetailedInfoO(String infoObjectId, String vodId){
        //mBusinessIF.getDetailedInfoObjectById(infoObjectId);
        refreshView();
        FetchInfoJob mFetchInfoJob = new FetchInfoJob(mAppContext,BaseJob.UI_HIGH);
        mFetchInfoJob.setVodId(vodId);
        mFetchInfoJob.setInfoId(infoObjectId);
        mJobManager.addJobInBackground( mFetchInfoJob);

        //mJobManager.addJobInBackground(new FetchFeedJob(mContext.getApplicationContext(), BaseJob.UI_HIGH,1l));
    }


    public void onEventMainThread(FetchedInfoEvent infoEvent) {
        Log.i(TAG, "FetchedInfoEvent isSuccess="+infoEvent.isSuccess());
        Log.i(TAG, "FetchedInfoEvent getInfoName="+infoEvent.getOldest().getInfoName());
        InfoObjectModel mInfoObjectModel = infoEvent.getOldest();
        page_infoId = mInfoObjectModel.getInfoId();
        refreshView();

    }

    private void refreshView(){
        Date mdate = new Date();
        Log.i(TAG,"22222 time="+String.valueOf(mdate.getTime()));
        InfoObjectModel mInfoObjectModel = mInfoIdal.loadByInfoId(page_infoId);
        if(mInfoObjectModel != null){
            mInfoODetailView.showAttribute(mInfoObjectModel.getAttribute());
            mInfoODetailView.showCategory(mInfoObjectModel.getCategory());
            mInfoODetailView.showTitle(mInfoObjectModel.getInfoName());
            mInfoODetailView.showViewCount(String.valueOf(mInfoObjectModel.getViewCount()));
            mInfoODetailView.showDescription(mInfoObjectModel.getDescription());
            mInfoODetailView.showInfoImage(mInfoObjectModel.getImageUrlFull());
        }

    }



    @Override
    public void editTask() {

    }

    @Override
    public void deleteTask() {

    }

    @Override
    public void completeTask() {

    }

    @Override
    public void activateTask() {

    }



    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }


}
