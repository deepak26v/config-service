package com.org.config.service;

import com.org.config.dao.AppConfigDAO;
import com.org.config.model.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppConfigService {

    @Autowired
    private AppConfigDAO appConfigDAO;

    public void save(AppConfig appConfig) {
        appConfigDAO.save(appConfig);
    }
}
