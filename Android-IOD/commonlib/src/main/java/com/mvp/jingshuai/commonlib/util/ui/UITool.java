package com.mvp.jingshuai.commonlib.util.ui;

import android.content.Context;

/**
 * Created by Administrator on 16-4-15.
 */
public class UITool {
    public static int pixelsFromDP( Context pContext,int pixels){
        return (int)(pixels * pContext.getResources().getDisplayMetrics().density + 0.5f);  //return pixels  dp
    }


}
