package com.mvp.jingshuai.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mvp.jingshuai.data.storage.IODDatabase;
import com.mvp.jingshuai.data.util.Validation;
import com.mvp.jingshuai.data.util.ValidationFailedException;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by eqruvvz on 12/28/2016.
 */

@Table(databaseName = IODDatabase.NAME)
public class SceneModel extends BaseModel implements Validation {

    @Column
    @PrimaryKey(autoincrement = false)
    @JsonProperty("vodId")
    String vodId;

    @Column
    @PrimaryKey(autoincrement = false)
    @JsonProperty("vodId")
    String infoObjectId;

    @Column
    @PrimaryKey(autoincrement = false)
    @JsonProperty("startTime")
    int startTime;

    @Column
    @JsonProperty("endTime")
    int endTime;

    @Column
    @JsonProperty("sceneName")
    String sceneName;

    @Column
    @JsonProperty("imageUrl")
    String imageUrl;


    public String getVodId() {
        return vodId;
    }

    public void setVodId(String vodId) {
        this.vodId = vodId;
    }

    public String getInfoObjectId() {
        return infoObjectId;
    }

    public void setInfoObjectId(String infoObjectId) {
        this.infoObjectId = infoObjectId;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getSceneName() {
        return sceneName;
    }

    public void setSceneName(String sceneName) {
        this.sceneName = sceneName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public void validate() {
        if (StringUtils.isEmpty(vodId)) {
            throw new ValidationFailedException("invalid vodId");
        }
        if (StringUtils.isEmpty(imageUrl)) {
            throw new ValidationFailedException("invalid imageUrl");
        }
        if (StringUtils.isEmpty(sceneName)) {
            throw new ValidationFailedException("invalid description");
        }
        if (startTime <1 || startTime <= endTime) {
            throw new ValidationFailedException("invalid startTime");
        }
        if (endTime <1 ) {
            throw new ValidationFailedException("invalid endTime");
        }

    }
}
