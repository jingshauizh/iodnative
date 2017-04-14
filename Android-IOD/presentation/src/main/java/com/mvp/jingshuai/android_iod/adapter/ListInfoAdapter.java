package com.mvp.jingshuai.android_iod.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.mvp.jingshuai.android_iod.R;
import com.mvp.jingshuai.commonlib.adapter.CommonAdapter;
import com.mvp.jingshuai.commonlib.adapter.ViewHolder;
import com.mvp.jingshuai.commonlib.log.MLog;

import java.util.List;

/**
 * Created by eqruvvz on 3/10/2017.
 */
public class ListInfoAdapter extends CommonAdapter<AdapterModel> {


    public ListInfoAdapter(Context context, List<AdapterModel> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        MLog.i("22222222222 mDatas.size="+mDatas.size());
    }


    @Override
    public void convert(ViewHolder helper, AdapterModel item) {
        helper.setImageByUrl(R.id.im_view_object, item.getImageUrl());
        MLog.i("22222222222  setText item.getImageUrl()="+item.getImageUrl());
        MLog.i("22222222222  setText item="+item.getName());
        helper.setText(R.id.tv_object_title, item.getName());
    }
}
