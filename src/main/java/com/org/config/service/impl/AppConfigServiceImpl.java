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

    public void saveAppConfig(AppConfig appConfig) {
        appConfigRepository.save(appConfig);
    }

    public AppConfig getAppConfig(AppConfigKey appConfigKey) {
        return appConfigRepository.findOne(appConfigKey);
    }

    public List<AppConfig> getAllAppConfig(String appCode) {
        return appConfigRepository.findByAppcodeOrderByLastModifiedTsDesc(appCode);
    }
}
