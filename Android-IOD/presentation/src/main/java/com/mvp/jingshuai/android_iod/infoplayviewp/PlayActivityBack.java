package com.mvp.jingshuai.android_iod.infoplayviewp;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
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
import com.mvp.jingshuai.android_iod.adapter.AdapterModel;
import com.mvp.jingshuai.android_iod.adapter.ListInfoAdapter;
import com.mvp.jingshuai.android_iod.app.IODApplication;
import com.mvp.jingshuai.android_iod.event.FetchedCurrentInfoEvent;
import com.mvp.jingshuai.android_iod.job.BaseJob;
import com.mvp.jingshuai.android_iod.job.Info.GetCurrentObjJob;
import com.mvp.jingshuai.android_iod.view.mask.component.ListComponent;
import com.mvp.jingshuai.commonlib.adapter.CommonAdapter;
import com.mvp.jingshuai.commonlib.log.MLog;
import com.mvp.jingshuai.data.idal.InfoIdal;
import com.mvp.jingshuai.data.model.InfoObjectModel;
import com.path.android.jobqueue.JobManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class PlayActivityBack extends AppCompatActivity implements OnClickListener {

    @BindView(R.id.surface1)
    SurfaceView surface1;
    @BindView(R.id.start)
    Button start;
    @BindView(R.id.stop)
    Button stop;
    @BindView(R.id.pre)
    Button pre;
    @BindView(R.id.ll_view_group)
    LinearLayout  ll_view_group;


    @Inject
    JobManager mJobManager;
    @Inject
    Context mAppContext;
    @Inject
    EventBus mEventBus;
    @Inject
    InfoIdal mInfoIdal;

    private MediaPlayer mediaPlayer1;
     MotionEvent stmotionEvent;

    List<AdapterModel> titleList = null;
    MotionEvent pmotionEvent;
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

        ButterKnife.bind(this);
        mediaPlayer1 = new MediaPlayer();
        //设置播放时打开屏幕
        surface1.getHolder().setKeepScreenOn(true);
        surface1.getHolder().addCallback(new SurfaceViewLis());
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        pre.setOnClickListener(this);


        IODApplication.getInstance().getAppComponent().inject(this);
        mEventBus.register(this, 0);

        Log.i(TAG, "555555  PlayActivity onCreate ");

        //ll_view_group = (LinearLayout) findViewById(R.id.ll_view_group);
        ll_view_group.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(PlayActivityTemp.this, "ViewActivity show", Toast.LENGTH_SHORT).show();
                mediaPlayer1.pause();
            }
        });

        ll_view_group.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Log.i("SimpleGuideViewActivity", "X=" + x + "Y=" + y);
                //Toast.makeText(SimpleGuideViewActivity.this, "On Long Click touch event", Toast.LENGTH_SHORT).show();
                final MotionEvent pmotionEvent = motionEvent;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            int pos = mediaPlayer1.getCurrentPosition();
                            MLog.d("mediaPlayer1.getCurrentPosition pos=" + pos);
                            //应该先拿数据 等数据到了之后再show view
                            //或者 先从本地数据库读取数据,远程的数据拿到了以后再更新。
                            //showGuideView2(pmotionEvent);
                            //server 端 必须改造 返回数据，用jason object 包含 info 数组， 不能直接返回对象数据组
                            stmotionEvent = pmotionEvent;
                            GetCurrentObjJob getCurrentObjJob = new GetCurrentObjJob(mAppContext, BaseJob.UI_HIGH);
                            getCurrentObjJob.setDuration(mediaPlayer1.getCurrentPosition());
                            MLog.i("mediaPlayer1.getCurrentPosition()="+String.valueOf(mediaPlayer1.getCurrentPosition()));
                            mJobManager.addJobInBackground(getCurrentObjJob);
                        }
                    });
                }
                return false;
            }
        });
    }


    public void onEventMainThread(FetchedCurrentInfoEvent infoEvent) {
        Log.i(TAG, "FetchedInfoEvent isSuccess="+infoEvent.isSuccess());
        List<InfoObjectModel> infosList  = infoEvent.getOldest();
        titleList = new ArrayList<AdapterModel>();
        for (InfoObjectModel infoObjectModel:infosList) {
            AdapterModel mAdapterModel = new AdapterModel();
            mAdapterModel.setName(infoObjectModel.getInfoName());
            mAdapterModel.setImageUrl(infoObjectModel.getImageUrlFull());
            mAdapterModel.setInfoObjectId(infoObjectModel.getInfoId());
            titleList.add(mAdapterModel);
        }
        showGuideView2(stmotionEvent);

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




        CommonAdapter mCommonAdapter = new ListInfoAdapter(PlayActivityBack.this,titleList,R.layout.layer_object_list_item);
        //mCommonAdapter.notifyDataSetChanged();
        ListComponent.XPosition xPosition = ListComponent.checkXposition(ll_view_group,motionEvent);
        ListComponent.YPosition yPosition = ListComponent.checkYposition(ll_view_group,motionEvent);

        builder1.addComponent(new ListComponent(mCommonAdapter,xPosition,yPosition));

        guide = builder1.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(PlayActivityBack.this);
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

        String sdCard= Environment.getExternalStorageDirectory().getPath();
        String mediaPath = sdCard+"/Movies/sintel-1024-surround.mp4";
        MLog.d("mediaPath="+mediaPath);
        mediaPlayer1.setDataSource(mediaPath);
        // 把视频输出到SurfaceView上
        mediaPlayer1.setDisplay(surface1.getHolder());
        mediaPlayer1.prepare();
        //mediaPlayer1.seekTo(20000);
       // mediaPlayer1.start();
        mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
            @Override
           public void onPrepared(MediaPlayer mp) {
               // 装载完毕回调
                mp.seekTo(20000);


           }
        });

        mediaPlayer1.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
            public void onSeekComplete(MediaPlayer m) {
                m.start();

            }
        });
    }


    public void play_1() throws IllegalArgumentException, SecurityException,
            IllegalStateException, IOException {
        mediaPlayer1.reset();
        mediaPlayer1.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer1.setDataSource("/mnt/sdcard/通话录音/1.mp4");
        AssetFileDescriptor fd = PlayActivityBack.this.getAssets().openFd("demo.mp4");
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
           // if (postion == 0) {
                try {
                    play();
                    //mediaPlayer1.seekTo(postion);
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

           // }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        MLog.i("PlayActivityBack onResume");
        if(guide!=null){
            guide.dismiss();
        }
        mediaPlayer1.pause();
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