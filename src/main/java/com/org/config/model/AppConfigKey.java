package com.org.config.model;

import java.io.Serializable;

public class AppConfigKey implements Serializable {

    private String version;

    private String appcode;

    public AppConfigKey(){}

    public AppConfigKey(String appcode, String version) {
        this.version = version;
        this.appcode = appcode;
    }
}
