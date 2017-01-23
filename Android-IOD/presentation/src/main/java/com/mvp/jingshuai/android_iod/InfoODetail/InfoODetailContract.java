package com.mvp.jingshuai.android_iod.InfoODetail;

import com.mvp.jingshuai.android_iod.BasePresenter;
import com.mvp.jingshuai.android_iod.BaseView;

/**
 * Created by eqruvvz on 12/31/2016.
 */
public class InfoODetailContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showInfoImage(String imageUrl);

        void hideTitle();

        void showTitle(String title);

        void hideAttribute();

        void showAttribute(String attribute);

        void hideCategory();

        void showCategory(String category);

        void hideViewCount();

        void showViewCount(String viewCount);

        void hideDescription();

        void showDescription(String description);



        boolean isActive();
    }

    interface Presenter extends BasePresenter {

        void editTask();

        void deleteTask();

        void completeTask();

        void activateTask();
    }
}
