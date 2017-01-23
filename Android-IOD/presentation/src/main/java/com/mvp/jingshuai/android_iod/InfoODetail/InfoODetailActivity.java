package com.mvp.jingshuai.android_iod.InfoODetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mvp.jingshuai.android_iod.R;

import com.mvp.jingshuai.commonlib.util.ActivityUtils;

public class InfoODetailActivity extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "TASK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_odetail);

        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);

        InfoODetailFragment mInfoODetailFragment = (InfoODetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (mInfoODetailFragment == null) {
            mInfoODetailFragment = InfoODetailFragment.newInstance(taskId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mInfoODetailFragment, R.id.contentFrame);
        }

        // Create the presenter
        new InfoODetailPresenter(
                taskId,
                mInfoODetailFragment);

    }


}
