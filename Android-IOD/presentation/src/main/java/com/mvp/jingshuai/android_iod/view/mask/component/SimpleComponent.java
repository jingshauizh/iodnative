package com.mvp.jingshuai.android_iod.view.mask.component;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blog.www.guideview.Component;
import com.mvp.jingshuai.android_iod.InfoODetail.InfoODetailActivity;
import com.mvp.jingshuai.android_iod.InfoPlayView.PlayActivityTemp;
import com.mvp.jingshuai.android_iod.R;




/**
 * Created by binIoter on 16/6/17.
 */
public class SimpleComponent implements Component {

  @Override
  public View getView(LayoutInflater inflater) {

    LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layer_frends_cir, null);
    ll.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //Toast.makeText(view.getContext(), "引导层被点击了", Toast.LENGTH_SHORT).show();
        Activity pActivity =  (Activity)view.getContext();
        printDebug(pActivity);
        pActivity.startActivity(new Intent(view.getContext(), InfoODetailActivity.class));
      }
    });
    return ll;
  }

  private void printDebug( Activity pActivity ){
//    ImageView mImageView = (ImageView)pActivity.findViewById(R.id.im_view_object2);
//
//    if(mImageView != null){
//      Drawable mDrawable =mImageView.getDrawable();
//      Log.i("SimpleComponent","333333  PlayActivity printDebug mDrawable="+mDrawable.toString());
//      //mImageView.setBackground(getResources().getDrawable(R.drawable.sintelsintel));
//    }
//    else{
//      Log.i("SimpleComponent","333333  PlayActivity printDebug mImageView = null=");
//    }
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
    return 50;
  }

  @Override
  public int getYOffset() {
    return 0;
  }
}
