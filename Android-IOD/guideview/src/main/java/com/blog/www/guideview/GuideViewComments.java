package com.blog.www.guideview;

/**
 * Created by eqruvvz on 4/20/2017.
 *
    1 MASKView  是一个viewGroup
    2 findViewById(android.R.id.content) 是 找到 DecorView 的 content  view group
    3 然后 把 MaskView add 到 content view 中 content.addView(mMaskView);
    4 这样就是在 content view 上面增加了一个 蒙层
    5 MaskView 是自定义 viewGroup 可以往里面 添加View

    6 而多个 实现 component interface 的 类 将会是一个一个 的view 被  maskView.addView 添加到 MASK 中
             // Adds the components to the mask view.
             for (Component c : mComponents) {
             maskView.addView(Common.componentToView(activity.getLayoutInflater(), c));
             }
    7


  其他蒙层 思路：

  1  windowsManager.addView   removeView 来添加移除 mask view
 需要 <uses-permissionAndroid:name="android.permission.SYSTEM_ALERT_WINDOW"/>
 http://blog.csdn.net/sundayzhong/article/details/46010739
  2  和这个直接放在 content layout 里面差不多

 *
 *
 */
public class GuideViewComments {
}
