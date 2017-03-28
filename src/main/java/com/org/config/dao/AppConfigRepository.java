package com.org.config.dao;

import com.org.config.model.AppConfig;
import com.org.config.model.AppConfigKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppConfigRepository extends JpaRepository<AppConfig, AppConfigKey> {
    /**
     * Retrieve all config of a specific app, ordered by last modified date in descending order
     *
     * @param appcode
     * @return List<AppConfig>
     */
    public List<AppConfig> findByAppcodeOrderByLastModifiedTsDesc(String appcode);
}
