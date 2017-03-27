package com.org.config.model;

import javax.persistence.Id;
import java.io.Serializable;

public class AppConfigKey implements Serializable{

    private Float version;

    private String appCode;

    public AppConfigKey(String appcode, String version) {
        this.appCode = appcode;
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
