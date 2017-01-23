package com.mvp.jingshuai.commonlib.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.Toast;



import java.lang.reflect.Field;

/**
 * Created by Administrator on 16-4-7.
 */
public abstract class ActivityLibBase extends Activity {

    protected static final int SHOW_TIME = 1;
    private ProgressDialog m_ProgressDialog;
    protected int mScreenWidth;
    protected int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 公共属性
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        mScreenWidth = metric.widthPixels;
        mScreenHeight = metric.heightPixels;

        // 定制流程
        setContentView();
        initViews();
        initListeners();
        initData();
    }

    public abstract void setContentView();

    public abstract void initViews();

    public abstract void initListeners();

    public abstract void initData();


    protected void showToast(String text) {
        if (text != null && !text.trim().equals("")) {
            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    protected void openActivity(Class<?> classA)  {
        Intent intent = new Intent();
        intent.setClass(this,classA);
        startActivity(intent);
    }

    protected LayoutInflater getLayouInflater() {
        LayoutInflater _LayoutInflater = LayoutInflater.from(this);
        return _LayoutInflater;
    }

    public void setAlertDialogIsClose(DialogInterface pDialog,Boolean pIsClose)
    {
        try {
            Field _Field = pDialog.getClass().getSuperclass().getDeclaredField("mShowing");
            _Field.setAccessible(true);
            _Field.set(pDialog, pIsClose);
        } catch (Exception e) {
        }
    }




    protected void showProgressDialog(int p_TitleResID,int p_MessageResID) {
        m_ProgressDialog = new ProgressDialog(this);
        m_ProgressDialog.setTitle(getString(p_TitleResID));
        m_ProgressDialog.setMessage(getString(p_MessageResID));
        m_ProgressDialog.show();
    }

    protected void dismissProgressDialog() {
        if(m_ProgressDialog != null)
        {
            m_ProgressDialog.dismiss();
        }
    }

    //provider a method to cancel the network request
    protected abstract boolean cancelRequest();

    @Override
    protected void onPause() {
        cancelRequest();
        super.onPause();
    }
}
