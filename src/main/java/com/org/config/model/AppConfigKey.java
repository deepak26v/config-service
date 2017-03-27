package com.org.config.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Id;
import java.io.Serializable;

public class AppConfigKey implements Serializable {

    private String version;

    private String appCode;

    public AppConfigKey() {}

    public AppConfigKey(String appcode, String version) {
        this.appCode = appcode;
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}
