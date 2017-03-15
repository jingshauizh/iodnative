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
public class ListInfoAdapter extends CommonAdapter<String> {


    public ListInfoAdapter(Context context, List<String> mDatas, int itemLayoutId) {
        super(context, mDatas, itemLayoutId);
        MLog.i("22222222222 mDatas.size="+mDatas.size());
    }


    @Override
    public void convert(ViewHolder helper, String item) {
        helper.setImageResource(R.id.im_view_object, R.drawable.sintelsintel);
        MLog.i("22222222222  setText item="+item);
        helper.setText(R.id.tv_object_title, item);
    }
}
