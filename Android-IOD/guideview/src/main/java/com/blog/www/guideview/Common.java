package com.blog.www.guideview;

import android.graphics.Rect;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by binIoter
 */
class Common {
  /**
   * 设置Component
   */
  static View componentToView(LayoutInflater inflater, Component c) {
    View view = c.getView(inflater);
    final MaskView.LayoutParams lp = new MaskView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT);
    lp.offsetX = c.getXOffset();
    lp.offsetY = c.getYOffset();
    lp.targetAnchor = c.getAnchor();
    lp.targetParentPosition = c.getFitPosition();
    view.setLayoutParams(lp);
    return view;
  }

  /**
   * Rect在屏幕上去掉状态栏高度的绝对位置
   */
  static Rect getViewAbsRect(View view, int parentX, int parentY) {
    int[] loc = new int[2];
    view.getLocationInWindow(loc);

    Rect rect = new Rect();
    rect.set(loc[0], loc[1], loc[0] + view.getMeasuredWidth(), loc[1] + view.getMeasuredHeight());
    rect.offset(-parentX, -parentY);
    return rect;
  }

  /**
   * Rect在屏幕上去掉状态栏高度的绝对位置
   */
  static Rect getViewAbsRectWithinputHeight(View view, int parentX, int parentY, int height) {
    int[] loc = new int[2];
    view.getLocationInWindow(loc);
    Rect mRect = new  Rect();
    view.getLocalVisibleRect(mRect);
    Log.i("TAG","height="+mRect.height()+"width="+mRect.width());

    Log.i("TAG","X="+loc[0]+"Y="+loc[1]+"  height="+height);
    Log.i("TAG","parentX="+parentX+"  parentY="+parentY+"  height="+height);
    Rect rect = new Rect();
    rect.set(loc[0]-height/2, loc[1]-height/2, loc[0] + height/2, loc[1] + height/2);
    rect.offset(-parentX-loc[0], -parentY-loc[1]);
    return rect;
  }

}
