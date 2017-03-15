
package com.mvp.jingshuai.android_iod.view.mask.component;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.blog.www.guideview.Component;
import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailActivity;
import com.mvp.jingshuai.android_iod.R;
import com.mvp.jingshuai.android_iod.adapter.ListInfoAdapter;
import com.mvp.jingshuai.commonlib.adapter.CommonAdapter;
import com.mvp.jingshuai.commonlib.log.MLog;

/**
 * Created by Jingshuai on 2017-02-21.
 */
public class ListComponent implements Component {
    private ListView mListView = null;
    private CommonAdapter mListAdapter = null;
    private XPosition xPosition = XPosition.IN_RIGHT;
    private YPosition yPosition = YPosition.IN_MIDDLE;
    private Integer ItemCount = 0;
    private final static Integer Y_POSITION_TOP_LINE = 300;
    private final static Integer Y_POSITION_BUTTOM_LINE = 200;
    private final  Integer OBJECT_OFFSET_DISTANCE = -50;

    public ListComponent(CommonAdapter pListAdapter) {
        this.mListAdapter = pListAdapter;
        ItemCount = pListAdapter.getCount();
    }
    public ListComponent(CommonAdapter pListAdapter,XPosition xPosition,YPosition yPosition) {
        this.mListAdapter = pListAdapter;
        ItemCount = pListAdapter.getCount();
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    @Override
    public View getView(LayoutInflater inflater) {

       // LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layer_frends_cir, null);
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layer_objects_list, null);
        mListView = (ListView)ll.findViewById(R.id.lv_view_objects);
        if(mListAdapter != null){
            mListView.setAdapter(mListAdapter);
        }
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "引导层被点击了", Toast.LENGTH_SHORT).show();
                Activity pActivity =  (Activity)view.getContext();
                pActivity.startActivity(new Intent(view.getContext(), InfoODetailActivity.class));
            }
        });
        return ll;
    }


    @Override
    public int getAnchor() {
        return Component.ANCHOR_BOTTOM;
    }

    @Override
    public int getFitPosition() {
        return Component.FIT_END;
    }

    @Override
    public int getXOffset() {
        if(xPosition == XPosition.IN_LEFT){
            return OBJECT_OFFSET_DISTANCE*(-2);
        }
        else{
            return OBJECT_OFFSET_DISTANCE;
        }

    }

    @Override
    public int getYOffset() {
        if(yPosition == YPosition.IN_TOP){
            return OBJECT_OFFSET_DISTANCE*0;
        }
        else if (yPosition == YPosition.IN_BUTTOM){
            return OBJECT_OFFSET_DISTANCE*2*ItemCount;
        }
        else{
            return OBJECT_OFFSET_DISTANCE*ItemCount;
        }

    }


    public enum XPosition {
        IN_LEFT,
        IN_RIGHT
    }

    public enum YPosition {
        IN_TOP,
        IN_MIDDLE,
        IN_BUTTOM
    }


    public static XPosition checkXposition(View view, MotionEvent motionEvent){
        Rect mRect = new Rect();
        view.getLocalVisibleRect(mRect);
        int X_pos= (int)motionEvent.getX();
        int Total_X = mRect.width();
        if(X_pos <= Total_X/2){
            return XPosition.IN_LEFT;
        }
        return XPosition.IN_RIGHT;
    }

    public static YPosition checkYposition(View view, MotionEvent motionEvent){
        Rect mRect = new Rect();
        view.getLocalVisibleRect(mRect);
        int Y_pos= (int)motionEvent.getY();
        int Total_Y = mRect.height();
        MLog.i("Total_Y="+Total_Y);
        MLog.i("Y_pos="+Y_pos);
        if(Y_pos <= Y_POSITION_TOP_LINE){
            return YPosition.IN_TOP;
        }
        else if(Y_pos >= Y_POSITION_TOP_LINE && Y_pos <= Total_Y-Y_POSITION_BUTTOM_LINE){
            return YPosition.IN_MIDDLE;
        }
        return YPosition.IN_BUTTOM;
    }


}
