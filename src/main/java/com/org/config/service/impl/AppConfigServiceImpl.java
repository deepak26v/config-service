package com.org.config.service.impl;

import com.org.config.dao.AppConfigRepository;
import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import com.org.config.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppConfigServiceImpl implements AppConfigService {

    @Autowired
    private AppConfigRepository appConfigRepository;

    public AppConfigServiceImpl() {}

    /**
     * Persist an application's config
     *
     * @param appConfig
     */
    public void saveAppConfig(AppConfig appConfig) {
        appConfigRepository.save(appConfig);
    }

    /**
     * Retrieve an application's config based on appcode and version
     *
     * @param appConfigKey
     * @return
     */
    public AppConfig getAppConfig(AppConfigKey appConfigKey) {
        return appConfigRepository.findOne(appConfigKey);
    }

    /**
     * Retrieve all config of a specific app, ordered by last modified date in descending order
     *
     * @param appCode
     * @return
     */
    public List<AppConfig> getAppConfigs(String appCode) {
        return appConfigRepository.findByAppcodeOrderByLastModifiedTsDesc(appCode);
    }
}
