package com.mvp.jingshuai.data.mock;

import com.mvp.jingshuai.data.interfaces.IODDataIF;
import com.mvp.jingshuai.data.model.InfoObjectModel;

/**
 * Created by eqruvvz on 1/1/2017.
 */
public class MockDataStorage  implements IODDataIF {
    public static InfoObjectModel getInfoObjectById(String infoId){
        InfoObjectModel mInfoObjectModel = new InfoObjectModel();
        mInfoObjectModel.setAttribute("mock Attribute");
        mInfoObjectModel.setCategory("mock Category");
        mInfoObjectModel.setDescription("mock Description");
        mInfoObjectModel.setInfoId("mock_infoId");
        mInfoObjectModel.setViewCount(888);
        return mInfoObjectModel;
    }

    @Override
    public InfoObjectModel getDetailedInfoObjectById(String infoObjectId) {
        InfoObjectModel mInfoObjectModel = new InfoObjectModel();
        mInfoObjectModel.setAttribute("mock Attribute");
        mInfoObjectModel.setCategory("mock Category");
        mInfoObjectModel.setDescription("mock Description");
        mInfoObjectModel.setInfoId("mock_infoId");
        mInfoObjectModel.setInfoName("mock_info_name");
        mInfoObjectModel.setViewCount(888);
        return mInfoObjectModel;
    }

    @Override
    public void getCurrentInfoObject(long startTime) {

    }
}
