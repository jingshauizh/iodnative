package com.mvp.jingshuai.data.model;

import com.mvp.jingshuai.data.storage.IODDatabase;
import com.mvp.jingshuai.data.util.Validation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mvp.jingshuai.data.util.ValidationFailedException;
import org.apache.commons.lang3.StringUtils;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by eqruvvz on 12/28/2016.
 */

@Table(databaseName = IODDatabase.NAME)
public class DeviceModel extends BaseModel implements Validation {

    @Column
    @PrimaryKey(autoincrement = false)
    @JsonProperty("deviceId")
     String deviceId;

    @Column
    @JsonProperty("clientId")
     String clientId;

    @Column
    @JsonProperty("clientIdType")
     String clientIdType;

    @Column
    @JsonProperty("modelId")
     String modelId;

    @Column
    @JsonProperty("deviceName")
     String deviceName;

    @Column
    @JsonProperty("managed")
     Boolean managed;

    @Column
    @JsonProperty("lpvr")
     Boolean lpvr;



    @Override
    public void validate() {
        if (StringUtils.isEmpty(deviceId)) {
            throw new ValidationFailedException("invalid deviceId");
        }
        if (StringUtils.isEmpty(clientId)) {
            throw new ValidationFailedException("invalid clientId");
        }
        if (StringUtils.isEmpty(clientIdType)) {
            throw new ValidationFailedException("invalid clientIdType");
        }
        if (StringUtils.isEmpty(deviceName)) {
            throw new ValidationFailedException("invalid deviceName");
        }
    }

    public Boolean getLpvr() {
        return lpvr;
    }

    public void setLpvr(Boolean lpvr) {
        this.lpvr = lpvr;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientIdType() {
        return clientIdType;
    }

    public void setClientIdType(String clientIdType) {
        this.clientIdType = clientIdType;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Boolean getManaged() {
        return managed;
    }

    public void setManaged(Boolean managed) {
        this.managed = managed;
    }


}
