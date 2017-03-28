package com.org.config.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;

@Entity
@IdClass(AppConfigKey.class)
public class AppConfig {

    @Id
    private String appcode;

    @Id
    @JsonProperty
    private String version;

    @UpdateTimestamp
    private Timestamp lastModifiedTs;

    @Column
    @JsonRawValue
    private String appConfigJson;

    public AppConfig(){}

    public AppConfig(String appcode, String version) {
        this.appcode = appcode;
        this.version = version;
    }

    public AppConfig(String appcode, String version, String appConfigJson) {
        this(appcode, version);
        this.appConfigJson = appConfigJson;
    }

    public String getAppConfigJson() {
        return appConfigJson;
    }
}
