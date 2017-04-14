package com.mvp.jingshuai.android_iod.job.Info;

import android.content.Context;

import com.mvp.jingshuai.android_iod.config.AppConfig;
import com.mvp.jingshuai.android_iod.di.component.AppComponent;
import com.mvp.jingshuai.android_iod.event.FetchedCurrentInfoEvent;
import com.mvp.jingshuai.android_iod.event.FetchedInfoEvent;
import com.mvp.jingshuai.android_iod.job.BaseJob;
import com.mvp.jingshuai.android_iod.job.NetworkException;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.mvp.jingshuai.data.model.InfoObjectModel;
import com.mvp.jingshuai.data.model.InfoObjectModelList;
import com.mvp.jingshuai.data.network.ApiService;
import com.path.android.jobqueue.Params;

import java.util.List;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by eqruvvz on 4/7/2017.
 */
public class GetCurrentObjJob extends BaseJob {

    @Inject
    transient ApiService mApiService;

    @Inject
    transient EventBus mEventBus;

    @Inject
    InfoIdal mInfoIdal;



    private Integer duration = 0;

    public GetCurrentObjJob(Context context, int priority) {
        super(new Params(priority).requireNetwork().groupBy(AppConfig.Network_GROUP));

    }
    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {
        final Call<InfoObjectModelList> feed;

        feed = mApiService.getCurrentObjects("IOD_SINTEL",this.duration, "en");

        Response<InfoObjectModelList> response = feed.execute();
        if (response.isSuccess()) {
            List<InfoObjectModel> oldest = handleResponse(response.body());
            mEventBus.post(new FetchedCurrentInfoEvent(true,  oldest));
        } else {
            throw new NetworkException(response.code());
        }
    }

    @Override
    protected void onCancel() {

    }


    @Override
    public void inject(AppComponent appComponent) {
        super.inject(appComponent);
        appComponent.inject(this);
    }



    private List<InfoObjectModel> handleResponse(InfoObjectModelList body) {
        // We could put these two into a transaction but it is OK to save a user even if we could
        // not save their post so we don't care.

        List<InfoObjectModel> infosList = null;
        if (body != null) {
            infosList = body.getInfoList();
//            for (InfoObjectModel infoObjectModel:infosList ) {
//                mInfoIdal.save(infoObjectModel);
//            }
        }
        return infosList;
    }


    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

}
