package com.mvp.jingshuai.android_iod.InfoODetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.mvp.jingshuai.android_iod.R;

import com.mvp.jingshuai.commonlib.util.ActivityUtils;

public class InfoODetailActivity extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "TASK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getSupportActionBar() != null) {
            // 默认左上角按钮可以点击
            getSupportActionBar().setHomeButtonEnabled(true);
            // 默认显示左上角返回按钮
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setContentView(R.layout.activity_info_odetail);

        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);

        InfoODetailFragment mInfoODetailFragment = (InfoODetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);

        if (mInfoODetailFragment == null) {
            mInfoODetailFragment = InfoODetailFragment.newInstance(taskId);

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    mInfoODetailFragment, R.id.contentFrame);
        }
        Intent intent = getIntent();
        String infoId = intent.getStringExtra("infoId");
        String vodId = intent.getStringExtra("vodId");
        // Create the presenter
        InfoODetailPresenter mInfoODetailPresenter = new InfoODetailPresenter(
                taskId,
                mInfoODetailFragment,
                infoId,
                vodId);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // 添加返回过渡动画.
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


}
