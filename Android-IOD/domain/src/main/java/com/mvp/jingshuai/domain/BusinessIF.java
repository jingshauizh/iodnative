package com.mvp.jingshuai.domain;

import com.mvp.jingshuai.data.model.InfoObjectModel;

/**
 * Created by eqruvvz on 12/30/2016.
 */
public interface BusinessIF {
    void getCurrentInfoObject(long startTime);
    Boolean getDetailedInfoObjectById(String infoObjectId);
}
