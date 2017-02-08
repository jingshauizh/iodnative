package com.mvp.jingshuai.android_iod.InfoPlayView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.mvp.jingshuai.android_iod.R;
import com.mvp.jingshuai.android_iod.view.mask.component.SimpleComponent;

public class PlayActivityTemp extends AppCompatActivity {
    private LinearLayout ll_nearby, ll_view_group;

    Guide guide;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        // 设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_play_activity_temp);
        ll_view_group = (LinearLayout) findViewById(R.id.ll_view_group);
        ll_view_group.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
               // Toast.makeText(PlayActivityTemp.this, "ViewActivity show", Toast.LENGTH_SHORT).show();
            }
        });

        ll_view_group.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view,MotionEvent motionEvent) {
                float x= motionEvent.getX();
                float y = motionEvent.getY();
                Log.i("SimpleGuideViewActivity","X="+x+"Y="+y);
                //Toast.makeText(SimpleGuideViewActivity.this, "On Long Click touch event", Toast.LENGTH_SHORT).show();
                final MotionEvent pmotionEvent = motionEvent;
                if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    view.post(new Runnable() {
                        @Override public void run() {
                            showGuideView2(pmotionEvent);
                        }
                    });
                }
                return false;
            }
        });

    }

    public void showGuideView2(MotionEvent motionEvent) {
        final GuideBuilder builder1 = new GuideBuilder();
        builder1.setTargetView(ll_view_group, (int)motionEvent.getX(),(int)motionEvent.getY())
                .setAlpha(150)
                .setHighTargetGraphStyle(Component.CIRCLE)
                .setOverlayTarget(false)
                .setExitAnimationId(android.R.anim.fade_out)
                .setOutsideTouchable(false);
        builder1.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override public void onShown() {
            }

            @Override public void onDismiss() {
                Log.i("SimpleGuideViewActivity","SimpleGuideViewActivity onDismiss ");
            }
        });

        builder1.addComponent(new SimpleComponent());

        Guide guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(PlayActivityTemp.this);
    }
}
