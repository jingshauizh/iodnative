package com.mvp.jingshuai.data.interfaces;

import com.mvp.jingshuai.data.model.InfoObjectModel;

/**
 * Created by eqruvvz on 1/2/2017.
 */
public interface IODDataIF {
    InfoObjectModel getDetailedInfoObjectById(String infoObjectId);
    void getCurrentInfoObject(long startTime);
}
