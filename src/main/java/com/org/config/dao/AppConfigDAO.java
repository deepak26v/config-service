package com.org.config.dao;

import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppConfigDAO extends JpaRepository<AppConfig, AppConfigKey> {}
