package com.org.config.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

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
