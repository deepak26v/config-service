package com.org.config.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@Entity
public class AppConfig {

    @Id
    private AppConfigKey appConfigKey;

    private String appConfigJson;

    public AppConfig(){}

    public AppConfig(AppConfigKey appConfigKey, String appConfigJson) {
        this.appConfigKey = appConfigKey;
        this.appConfigJson = appConfigJson;
    };

    public String getAppConfigJson() {
        return appConfigJson;
    }

    public void setAppConfigJson(String appConfigJson) {
        this.appConfigJson = appConfigJson;
    }
}
