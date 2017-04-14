package com.mvp.jingshuai.android_iod.job.Info;

import android.content.Context;

import com.mvp.jingshuai.android_iod.config.AppConfig;
import com.mvp.jingshuai.android_iod.di.component.AppComponent;
import com.mvp.jingshuai.android_iod.event.FetchedInfoEvent;
import com.mvp.jingshuai.android_iod.job.BaseJob;
import com.mvp.jingshuai.android_iod.job.NetworkException;

import com.mvp.jingshuai.commonlib.log.MLog;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.mvp.jingshuai.data.model.InfoObjectModel;
import com.mvp.jingshuai.data.network.ApiService;
import com.path.android.jobqueue.Params;

import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;

import de.greenrobot.event.EventBus;
import retrofit.Call;
import retrofit.Response;

/**
 * Created by eqruvvz on 1/12/2017.
 */
public class FetchInfoJob extends BaseJob {
    private static final AtomicInteger jobCounter = new AtomicInteger(0);
    private final int id;
    @Inject
    transient ApiService mApiService;

    @Inject
    transient EventBus mEventBus;

    @Inject
    InfoIdal mInfoIdal;


    private String infoId;
    private String vodId;
    private String LANG = "en";


    public FetchInfoJob(Context context, int priority) {
        super(new Params(priority).requireNetwork().groupBy(AppConfig.Network_GROUP));
        id = jobCounter.incrementAndGet();

    }

    public FetchInfoJob(Context context, int priority, String infoId, String vodId) {
        super(new Params(priority).requireNetwork().groupBy(AppConfig.Network_GROUP));
        id = jobCounter.incrementAndGet();

    }

    @Override
    public void inject(AppComponent appComponent) {
        super.inject(appComponent);
        appComponent.inject(this);
    }

    @Override
    public void onAdded() {

    }

    @Override
    public void onRun() throws Throwable {

        final Call<InfoObjectModel> feed;
        MLog.i("infoId="+infoId);
        MLog.i("vodId="+vodId);
        MLog.i("LANG="+LANG);
        feed = mApiService.getInfoObject(infoId,vodId,LANG);

        Response<InfoObjectModel> response = feed.execute();
        if (response.isSuccess()) {
            InfoObjectModel oldest = handleResponse(response.body());
            mEventBus.post(new FetchedInfoEvent(true,  oldest));
        } else {
            throw new NetworkException(response.code());
        }
    }

    private InfoObjectModel handleResponse(InfoObjectModel body) {
        // We could put these two into a transaction but it is OK to save a user even if we could
        // not save their post so we don't care.

        InfoObjectModel oldest = null;
        if (body != null) {
            mInfoIdal.save(body);
            oldest = body;
        }
        return oldest;
    }


    @Override
    protected void onCancel() {

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
