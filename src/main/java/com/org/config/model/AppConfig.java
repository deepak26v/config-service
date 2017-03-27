package com.org.config.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Map;

@Entity
@IdClass(AppConfigKey.class)
public class AppConfig implements Serializable {

    private Map<String, String> appConfigJson;

    public AppConfig(Map<String, String> appConfigJson) {
        this.appConfigJson = appConfigJson
    };

    public AppConfig(String appcode, String version) {

    }

    public Map<String, String> getAppConfigJson() {
        return appConfigJson;
    }

    public void setAppConfigJson(Map<String, String> appConfigJson) {
        this.appConfigJson = appConfigJson;
    }
}
