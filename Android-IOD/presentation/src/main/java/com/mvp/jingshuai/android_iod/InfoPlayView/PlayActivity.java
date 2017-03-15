package com.mvp.jingshuai.android_iod.InfoPlayView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.res.AssetFileDescriptor;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.blog.www.guideview.Component;
import com.blog.www.guideview.Guide;
import com.blog.www.guideview.GuideBuilder;
import com.mvp.jingshuai.android_iod.R;
import com.mvp.jingshuai.android_iod.adapter.ListInfoAdapter;
import com.mvp.jingshuai.android_iod.view.mask.component.ListComponent;
import com.mvp.jingshuai.commonlib.adapter.CommonAdapter;

public class PlayActivity extends AppCompatActivity implements OnClickListener {

    private SurfaceView surface1;
    private Button start, stop, pre;
    private MediaPlayer mediaPlayer1;
    private MotionEvent stmotionEvent;

    private LinearLayout  ll_view_group;

    Guide guide;

    private int postion = 0;
    private final String TAG = "PlayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置为全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_play);
        surface1 = (SurfaceView) findViewById(R.id.surface1);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);
        pre = (Button) findViewById(R.id.pre);
        mediaPlayer1 = new MediaPlayer();
        //设置播放时打开屏幕
        surface1.getHolder().setKeepScreenOn(true);
        surface1.getHolder().addCallback(new SurfaceViewLis());
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        pre.setOnClickListener(this);

        Log.i(TAG,"555555  PlayActivity onCreate ");

        ll_view_group = (LinearLayout) findViewById(R.id.ll_view_group);
        ll_view_group.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                // Toast.makeText(PlayActivityTemp.this, "ViewActivity show", Toast.LENGTH_SHORT).show();
                mediaPlayer1.pause();
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

        List<String> titleList = new ArrayList<String>();
        titleList.add("object1");
        titleList.add("object2");
        titleList.add("object3");

        CommonAdapter mCommonAdapter = new ListInfoAdapter(PlayActivity.this,titleList,R.layout.layer_object_list_item);

        ListComponent.XPosition xPosition = ListComponent.checkXposition(ll_view_group,motionEvent);
        ListComponent.YPosition yPosition = ListComponent.checkYposition(ll_view_group,motionEvent);

        builder1.addComponent(new ListComponent(mCommonAdapter,xPosition,yPosition));

        guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(PlayActivity.this);
    }



    @Override
    protected void onResume() {
        super.onResume();
//        if(stmotionEvent != null){
//            showGuideView2(stmotionEvent);
//        }
        //Log.i(TAG,"333333  PlayActivity mediaPlayer1.pause ");
        mediaPlayer1.pause();
//        ImageView mImageView = (ImageView)findViewById(R.id.im_view_object2);
//        ImageView mImageView2 = (ImageView)findViewById(R.id.im_view_object);
//
//        if(mImageView != null && mImageView2 != null){
//            mImageView.refreshDrawableState();
//            Drawable mDrawable =mImageView.getDrawable();
//            Log.i(TAG,"333333 1111 PlayActivity mediaPlayer1.pause mDrawable="+mDrawable.toString());
//           // mImageView.setImageDrawable(getResources().getDrawable(R.drawable.sintelsintel));
//            mImageView2.setImageDrawable(mDrawable);
//            //mImageView.setBackground(mDrawable);
//            //mImageView.setBackground(getResources().getDrawable(R.drawable.sintelsintel));
//        }
//        else{
//            Log.i(TAG,"333333 44444 PlayActivity mediaPlayer1.pause mImageView = null=");
//        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                try {
                    play();
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case R.id.pre:
                if (mediaPlayer1.isPlaying()) {
                    mediaPlayer1.pause();
                } else {
                    mediaPlayer1.start();
                }
                break;
            case R.id.stop:
                if (mediaPlayer1.isPlaying())
                    mediaPlayer1.stop();
                break;
            default:
                break;
        }

    }

    public void play() throws IllegalArgumentException, SecurityException,
            IllegalStateException, IOException {
        mediaPlayer1.reset();
        mediaPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer1.setDataSource("/mnt/sdcard/通话录音/1.mp4");
        AssetFileDescriptor fd = PlayActivity.this.getAssets().openFd("demo.mp4");
        mediaPlayer1.setDataSource(fd.getFileDescriptor(),fd.getStartOffset(), fd.getLength());
        // 把视频输出到SurfaceView上
        mediaPlayer1.setDisplay(surface1.getHolder());
        mediaPlayer1.prepare();
        mediaPlayer1.start();
    }

    private class SurfaceViewLis implements SurfaceHolder.Callback {

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (postion == 0) {
                try {
                    play();
                    mediaPlayer1.seekTo(postion);
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SecurityException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    @Override
    protected void onPause() {
        if (mediaPlayer1.isPlaying()) {
            // 保存当前播放的位置
            postion = mediaPlayer1.getCurrentPosition();
            mediaPlayer1.pause();
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"2222222  PlayActivity onDestroy ");
        if (mediaPlayer1.isPlaying())
            mediaPlayer1.stop();
        mediaPlayer1.release();
        super.onDestroy();
    }

}