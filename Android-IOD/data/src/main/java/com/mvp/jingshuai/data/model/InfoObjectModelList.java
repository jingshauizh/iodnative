package com.mvp.jingshuai.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raizlabs.android.dbflow.annotation.Column;

import java.util.List;

/**
 * Created by eqruvvz on 4/7/2017.
 */
public class InfoObjectModelList {


    @JsonProperty("vodId")
    String vodId;


    @JsonProperty("duration")
    Integer duration;


    @JsonProperty("infoList")
    List<InfoObjectModel> infoList;




    public List<InfoObjectModel> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InfoObjectModel> infoList) {
        this.infoList = infoList;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }

}
