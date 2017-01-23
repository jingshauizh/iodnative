package com.mvp.jingshuai.domain.dataimpl;

import com.mvp.jingshuai.data.interfaces.IODDataIF;
import com.mvp.jingshuai.data.mock.MockDataStorage;
import com.mvp.jingshuai.data.model.InfoObjectModel;
import com.mvp.jingshuai.domain.BusinessIF;

/**
 * Created by eqruvvz on 1/2/2017.
 */
public class Demainimpl implements BusinessIF {

    private IODDataIF mIODDataIF;

    public Demainimpl(IODDataIF mIODDataIF) {
        this.mIODDataIF = mIODDataIF;
    }

    public Demainimpl() {
        this.mIODDataIF = new MockDataStorage();
    }

    @Override
    public void getCurrentInfoObject(long startTime) {

    }

    @Override
    public Boolean getDetailedInfoObjectById(String infoObjectId) {
        return null;
    }
}
