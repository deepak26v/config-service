package com.org.config.service;

import com.org.config.dao.AppConfigDAO;
import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppConfigService {

    @Autowired
    private AppConfigDAO appConfigDAO;

    public void save(AppConfig appConfig) {
        appConfigDAO.save(appConfig);
    }

    public AppConfig getAppConfig(AppConfigKey appConfigKey) {
        return appConfigDAO.findOne(appConfigKey);
    }

    public void findByAppcode(String appCode) {
        //appConfigDAO.findByAppcode(appCode);
    }
}
