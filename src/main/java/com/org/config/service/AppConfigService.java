package com.org.config.service;

import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import java.util.List;

public interface AppConfigService {

    public void saveAppConfig(AppConfig appConfig);

    public AppConfig getAppConfig(AppConfigKey appConfigKey);

    public List<AppConfig> getAllAppConfig(String appCode);
}
